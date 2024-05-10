package org.firstinspires.ftc.teamcode.modules.lua

import android.os.Environment
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence
import java.util.Objects
import java.util.concurrent.TimeUnit

class Lua
{
	companion object
	{
		init
		{
			System.loadLibrary("ftcrobotcontroller");
		}
	}
	
	val opmode: LinearOpMode;
	private var trajectory: TrajectorySequence? = null;
	private var lrr: LuaRoadRunner? = null;
	
	constructor(a: LinearOpMode)
	{
		opmode = a;
	}
	
	external fun init(): Array<String>;
	external fun addObject(thing: Any);
	external fun start(name: String, recognition: Int);
	external fun stop();
	
	fun initRR(name: String)
	{
		lrr = LuaRoadRunner(opmode);
		lrr?.buildPath(name);
		trajectory = lrr?.getTrajectory();
	}
	
	fun startRR(name: String, recognition: Int)
	{
		start(name, recognition);
		lrr?.drive?.followTrajectorySequence(trajectory);
	}
	
	fun isRR(): Boolean
	{
		return trajectory != null;
	}
	
	fun getDataDir(): String
	{
		return Environment.getExternalStorageDirectory().toString();
	}
	
	fun print(string: String)
	{
		Log.d("Lua", string);
	}
	
	fun err(msg: String)
	{
		Log.e("Lua", msg);
		opmode.telemetry.clearAll();
		opmode.telemetry.addData("Lua Error", msg);
		opmode.telemetry.update();
		opmode.terminateOpModeNow();
	}
	
	fun telem(label: String, msg: String)
	{
		opmode.telemetry.addData(label, msg);
	}
	
	fun updateTelem()
	{
		opmode.telemetry.update();
	}
	

	fun delay(time: Double): Boolean
	{
		val e = ElapsedTime();
		e.reset();
		while(e.time(TimeUnit.SECONDS) < time && opmode.opModeIsActive());
		
		return !opmode.opModeIsActive();
	}
	
	fun isActive(): Boolean
	{
		return opmode.opModeIsActive();
	}
}