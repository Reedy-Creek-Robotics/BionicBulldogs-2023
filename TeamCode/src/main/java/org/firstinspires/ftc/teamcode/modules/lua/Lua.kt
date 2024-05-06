package org.firstinspires.ftc.teamcode.modules.lua

import android.os.Environment
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence
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
	private var servo: Servo? = null;
	private var servo2: Servo? = null;
	
	private var trajectory: TrajectorySequence? = null;
	private var lrr: LuaRoadRunner? = null;
	
	constructor(a: LinearOpMode)
	{
		opmode = a;
		if(opmode.hardwareMap.servo.contains("servo"))
		{
			servo = opmode.hardwareMap.servo.get("servo");
			servo2 = opmode.hardwareMap.servo.get("servo2");
		}
	}
	
	external fun init(): Array<String>;
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
	
	fun setPos(pos: Double)
	{
		if(servo != null)
		{
			servo?.position = pos;
		}
	}
	
	fun setPos2(pos: Double)
	{
		if(servo2 != null)
		{
			servo2?.position = pos;
		}
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