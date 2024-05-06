package org.firstinspires.ftc.teamcode.opmode.lua

import android.os.Environment
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.File
import java.io.InputStreamReader
import java.net.ServerSocket


@TeleOp
class FileSync : LinearOpMode()
{
	override fun runOpMode()
	{
		waitForStart();
		val server = ServerSocket(6969);
		val socket = server.accept();
		var str = "";
		val reader = BufferedReader(InputStreamReader(socket.getInputStream()));
		val sb = StringBuilder();
		var line: String?;
		while(reader.readLine().also { line = it } != null) sb.append(line).append("\n");
		str = sb.toString();
		var path = "";
		var data = "";
		var d = false;
		for(s in str)
		{
			if(s == '?')
			{
				d = true;
				continue;
			}
			if(s.toInt() == 1)
			{
				val file = File(Environment.getExternalStorageDirectory(), path);
				val c = file.outputStream();
				c.write(data.toByteArray());
				c.close();
				path = "";
				data = "";
				d = false;
				continue;
			}
			if(!d)
			{
				path += s;
			}
			else
			{
				data += s;
			}
		}
	}
}