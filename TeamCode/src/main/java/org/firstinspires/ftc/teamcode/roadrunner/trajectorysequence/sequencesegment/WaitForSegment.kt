package org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.sequencesegment

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.trajectory.TrajectoryMarker

class WaitForSegment : SequenceSegment
{
	private val pos: Pose2d = Pose2d(0.0, 0.0, 0.0);
	constructor(pose: Pose2d, markers: List<TrajectoryMarker>) : super(1.0, pose, pose, markers);
	fun update(done: Boolean = false)
	{
		if(done)
		{
			duration = 0.0;
		}
		else
		{
			duration += 1.0;
		}
	}
}