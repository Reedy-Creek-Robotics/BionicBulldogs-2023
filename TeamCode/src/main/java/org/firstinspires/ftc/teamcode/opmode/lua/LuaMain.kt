package org.firstinspires.ftc.teamcode.opmode.lua

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.modules.lua.Lua

@Autonomous
class LuaMain: LinearOpMode
{
	constructor(){}
	override fun runOpMode(){
		val lua = Lua(this);
		lua.init();
		waitForStart();
		lua.start("main");
	}
}