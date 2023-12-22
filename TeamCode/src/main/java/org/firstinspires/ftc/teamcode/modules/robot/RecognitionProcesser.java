package org.firstinspires.ftc.teamcode.modules.robot;

import android.graphics.Canvas;
import android.util.Log;

import org.checkerframework.checker.initialization.qual.Initialized;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class RecognitionProcesser implements VisionProcessor {

    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar RED = new Scalar(255, 0, 0);
    static final Scalar PURPLE = new Scalar(255, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);
    static final Scalar LOW_RED = new Scalar(140, 0, 20);
    static final Scalar HIGH_RED = new Scalar(180, 255, 255);
    static final Scalar LOW_BLUE = new Scalar(90, 180, 20);
    static final Scalar HIGH_BLUE = new Scalar(130, 255, 255);

    /*
     * The core values which define the location and size of the sample regions
     */
    public static Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(15,75);
    public static Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(275,50);
    public static Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(490,75);
    static int REGION_WIDTH = 120;
    static int REGION_HEIGHT = 100;

    /*
     * Points which actually define the sample region rectangles, derived from above values
     *
     * Example of how points A and B work to define a rectangle
     *
     *   ------------------------------------
     *   | (0,0) Point A                    |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                  Point B (70,50) |
     *   ------------------------------------
     *
     */
    Point region1_pointA = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x,
            REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region2_pointA = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x,
            REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pointB = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region3_pointA = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x,
            REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pointB = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    /*
     * Working variables
     */
    Mat region1, region2, region3;
    Mat hsvMat = new Mat();
    int nonZero1, nonZero2, nonZero3;

    private ElementPosition position = ElementPosition.UNKNOWN;
    private boolean initialized = false;

    public ElementPosition getPosition() {
        return position;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public int getNonZero1() {
        return nonZero1;
    }

    public int getNonZero2() {
        return nonZero2;
    }

    public int getNonZero3() {
        return nonZero3;
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, hsvMat, Imgproc.COLOR_RGB2HSV);
        if( !initialized ) {
            region1 = hsvMat.submat(new Rect(region1_pointA, region1_pointB));
            region2 = hsvMat.submat(new Rect(region2_pointA, region2_pointB));
            region3 = hsvMat.submat(new Rect(region3_pointA, region3_pointB));

            this.initialized = true;
            Log.d("OPENCV", "INITIALIZED");
        }
        Mat inRangeMat1 = new Mat();
        Core.inRange(region1, LOW_RED, HIGH_RED, inRangeMat1);
        Mat inRangeMat2 = new Mat();
        Core.inRange(region2, LOW_RED, HIGH_RED, inRangeMat2);
        Mat inRangeMat3 = new Mat();
        Core.inRange(region3, LOW_RED, HIGH_RED, inRangeMat3);

        nonZero1 = (int) Core.countNonZero(inRangeMat1);
        nonZero2 = (int) Core.countNonZero(inRangeMat2);
        nonZero3 = (int) Core.countNonZero(inRangeMat3);

        /*
         * Draw a rectangle showing sample region 1 on the screen.
         * Simply a visual aid. Serves no functional purpose.
         */
        Imgproc.rectangle(
                frame, // Buffer to draw on
                region1_pointA, // First point which defines the rectangle
                region1_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        /*
         * Draw a rectangle showing sample region 2 on the screen.
         * Simply a visual aid. Serves no functional purpose.
         */
        Imgproc.rectangle(
                frame, // Buffer to draw on
                region2_pointA, // First point which defines the rectangle
                region2_pointB, // Second point which defines the rectangle
                RED, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        /*
         * Draw a rectangle showing sample region 3 on the screen.
         * Simply a visual aid. Serves no functional purpose.
         */
        Imgproc.rectangle(
                frame, // Buffer to draw on
                region3_pointA, // First point which defines the rectangle
                region3_pointB, // Second point which defines the rectangle
                PURPLE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines


        /*
         * Find the max of the 3 averages
         */
        int maxOneTwo = Math.max(nonZero1, nonZero2);
        int max = Math.max(maxOneTwo, nonZero3);

        /*
         * Now that we found the max, we actually need to go and
         * figure out which sample region that value was from
         */
        if(max == nonZero1) // Was it from region 1?
        {
            position = ElementPosition.Left; // Record our analysis
            Log.d("OPENCV", "LEFT-TOP");

            /*
             * Draw a solid rectangle on top of the chosen region.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    frame, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    3); // Negative thickness means solid fill
        }
        else if(max == nonZero2) // Was it from region 2?
        {
            position = ElementPosition.Center; // Record our analysis
            Log.d("OPENCV", "CENTER-MID");

            /*
             * Draw a solid rectangle on top of the chosen region.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    frame, // Buffer to draw on
                    region2_pointA, // First point which defines the rectangle
                    region2_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    3); // Negative thickness means solid fill
        }
        else if(max == nonZero3) // Was it from region 3?
        {
            position = ElementPosition.Right; // Record our analysis
            Log.d("OPENCV", "RIGHT-LOW");

            /*
             * Draw a solid rectangle on top of the chosen region.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    frame, // Buffer to draw on
                    region3_pointA, // First point which defines the rectangle
                    region3_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    3); // Negative thickness means solid fill
        }
        else{
            position = ElementPosition.UNKNOWN;
            Log.d("OPENCV", "UNKNOWN");
        }

        /*
         * Render the 'input' buffer to the viewport. But note this is not
         * simply rendering the raw camera feed, because we called functions
         * to add some annotations to this buffer earlier up.
         */
        return frame;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }
}
