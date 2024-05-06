package org.firstinspires.ftc.teamcode.opmode.lua

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.modules.lua.Lua

abstract class LuaAutoBase : LinearOpMode()
{
	override fun runOpMode()
	{
		val lua = Lua(this);
		telemetry.addLine("initing lua");
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