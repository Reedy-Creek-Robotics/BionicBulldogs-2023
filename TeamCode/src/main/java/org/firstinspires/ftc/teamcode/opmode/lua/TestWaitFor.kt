package org.firstinspires.ftc.teamcode.opmode.lua

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.modules.lua.LuaSettings
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive

@Autonomous
class TestWaitFor : LinearOpMode()
{
	override fun runOpMode()
	{
		val drive = SampleMecanumDrive(hardwareMap);
		val seq = drive.trajectorySequenceBuilder(Pose2d(0.0, 0.0, 0.0));
		seq.forward(4.0);
		val seg = seq.waitFor(Pose2d(20.0, 20.0, 0.0));
		seq.forward(4.0);
		val traj = seq.build();
		
		waitForStart();
		
		drive.followTrajectorySequenceAsync(traj);
		while(opModeIsActive() || drive.isBusy)
		{
			drive.update();
			seg.update(LuaSettings.defultRecognition == 1);
		}
	}
}