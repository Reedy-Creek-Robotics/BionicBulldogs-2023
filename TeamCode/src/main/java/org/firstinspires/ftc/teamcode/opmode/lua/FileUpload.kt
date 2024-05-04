package org.firstinspires.ftc.teamcode.opmode.lua

import android.os.Environment
import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import java.io.File

@Autonomous
class FileUpload : LinearOpMode
{
	constructor()
	{

	}

	override fun runOpMode()
	{
		waitForStart();
		val file = File(Environment.getExternalStorageDirectory(), FileUploadPrams::path.get());
		val s = file.outputStream();
		s.write(FileUploadPrams::data.get().toByteArray());
		s.close();
	}
}