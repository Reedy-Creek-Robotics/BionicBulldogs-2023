package org.firstinspires.ftc.teamcode.opmode.lua

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.modules.lua.Lua
@Autonomous
class LuaRoadRunnerTest : LinearOpMode()
{
	override fun runOpMode()
	{
		val lua = Lua(this);
		lua.init();
		lua.RRInit("main");
		waitForStart();
		lua.startRR("main");
	}
}