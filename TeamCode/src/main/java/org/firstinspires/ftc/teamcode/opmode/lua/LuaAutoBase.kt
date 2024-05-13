package org.firstinspires.ftc.teamcode.opmode.lua

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.modules.lua.Lua
import org.firstinspires.ftc.teamcode.modules.lua.TestModule

abstract class LuaAutoBase : LinearOpMode()
{
	override fun runOpMode()
	{
		telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry);
		val lua = Lua(this);
		telemetry.addLine("initing lua");
		telemetry.update();

		val obj = TestModule(this);
		lua.addObject(obj);
		
		telemetry.addLine("initing lua2");
		telemetry.update();

		lua.init();

		val str = getOpmodeName();
		telemetry.clearAll();
		telemetry.addLine("building path");
		telemetry.update();

		lua.initRR(str);

		telemetry.clearAll();
		telemetry.addLine("inited"); 
		telemetry.update();

		waitForStart();

		telemetry.clearAll();
		telemetry.update();

		lua.startRR(str, 2);
	}

	abstract fun getOpmodeName(): String;
}