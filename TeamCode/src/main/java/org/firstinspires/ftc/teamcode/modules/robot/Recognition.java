/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.modules.robot;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

/*
 * This sample demonstrates a basic (but battle-tested and essentially
 * 100% accurate) method of detecting the skystone when lined up with
 * the sample regions over the first 3 stones.
 */
public class Recognition
{
    OpenCvWebcam webcam;
    SkystoneDeterminationPipeline pipeline;


    static int target = 300;

    public Recognition(LinearOpMode opmode)
    {
        /**
         * NOTE: Many comments have been omitted from this sample for the
         * sake of conciseness. If you're just starting out with EasyOpenCv,
         * you should take a look at {@link InternalCamera1Example} or its
         * webcam counterpart, {@link WebcamExample} first.
         */

        int cameraMonitorViewId = opmode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opmode.hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(opmode.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        webcam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        webcam.setViewportRenderingPolicy(OpenCvWebcam.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                Log.d("OPENCV", "WEBCAM STARTED STREAMING");
                webcam.startStreaming(640,480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

    public CameraStreamSource getCamera(){
        return webcam;
    }

    public SkystoneDeterminationPipeline.ElementPosition getBarcode(){
        return pipeline.getAnalysis();
    }
    public int getNZ1(){
        return pipeline.nonZero1;
    }
    public int getNZ2(){
        return pipeline.nonZero2;
    }
    public int getNZ3(){
        return pipeline.nonZero3;
    }
    public int getHue1() {
        return pipeline.hue1;
    }
    public int getHue2() {
        return pipeline.hue2;
    }
    public int getHue3() {
        return pipeline.hue3;
    }
    public boolean getInitialized(){
        Log.d("PIPELINE", String.valueOf(pipeline.getInitialized()));
        return pipeline.getInitialized();
    };

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the skystone position
         */
        public enum ElementPosition
        {
            Left,
            Center,
            Right,
            UNKNOWN
        }

        /*
         * Some color constants
         */
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
        Mat inRangeMat = new Mat();
        int nonZero1, nonZero2, nonZero3;

        private volatile boolean isInitialized = false;
        int hue1, hue2, hue3;

        // Volatile since accessed by OpMode thread w/o synchronization
        private volatile ElementPosition position = ElementPosition.UNKNOWN;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToHSVMat(Mat input)
        {
            Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);
            //Core.inRange(hsvMat, LOW_RED, HIGH_RED, inRangeMat);
        }

        @Override
        public void init(Mat firstFrame)
        {

            inputToHSVMat(firstFrame);

//            region1 = inRangeMat.submat(new Rect(region1_pointA, region1_pointB));
//            region2 = inRangeMat.submat(new Rect(region2_pointA, region2_pointB));
//            region3 = inRangeMat.submat(new Rect(region3_pointA, region3_pointB));
            region1 = hsvMat.submat(new Rect(region1_pointA, region1_pointB));
            region2 = hsvMat.submat(new Rect(region2_pointA, region2_pointB));
            region3 = hsvMat.submat(new Rect(region3_pointA, region3_pointB));

        }

        @Override
        public Mat processFrame(Mat input)
        {
            isInitialized = true;
            Log.d("OPENCV", "INITIALIZED");
            inputToHSVMat(input);

            Mat inRangeMat1 = new Mat();
            Core.inRange(region1, LOW_RED, HIGH_RED, inRangeMat1);
            Mat inRangeMat2 = new Mat();
            Core.inRange(region2, LOW_RED, HIGH_RED, inRangeMat2);
            Mat inRangeMat3 = new Mat();
            Core.inRange(region3, LOW_RED, HIGH_RED, inRangeMat3);

            hue1 = (int) Core.mean(region1).val[0];
            hue2 = (int) Core.mean(region2).val[0];
            hue3 = (int) Core.mean(region3).val[0];

            nonZero1 = (int) Core.countNonZero(inRangeMat1);
            nonZero2 = (int) Core.countNonZero(inRangeMat2);
            nonZero3 = (int) Core.countNonZero(inRangeMat3);

            /*
             * Draw a rectangle showing sample region 1 on the screen.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            /*
             * Draw a rectangle showing sample region 2 on the screen.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region2_pointA, // First point which defines the rectangle
                    region2_pointB, // Second point which defines the rectangle
                    RED, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            /*
             * Draw a rectangle showing sample region 3 on the screen.
             * Simply a visual aid. Serves no functional purpose.
             */
            Imgproc.rectangle(
                    input, // Buffer to draw on
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
                        input, // Buffer to draw on
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
                        input, // Buffer to draw on
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
                        input, // Buffer to draw on
                        region3_pointA, // First point which defines the rectangle
                        region3_pointB, // Second point which defines the rectangle
                        GREEN, // The color the rectangle is drawn in
                        3); // Negative thickness means solid fill
            }
            else{
                position = ElementPosition.UNKNOWN;
            }

            /*
             * Render the 'input' buffer to the viewport. But note this is not
             * simply rendering the raw camera feed, because we called functions
             * to add some annotations to this buffer earlier up.
             */
            return input;
        }

        /*
         * Call this from the OpMode thread to obtain the latest analysis
         */
        public ElementPosition getAnalysis()
        {
            return position;
        }

        public boolean getInitialized() {
            return isInitialized;
        }
    }
}