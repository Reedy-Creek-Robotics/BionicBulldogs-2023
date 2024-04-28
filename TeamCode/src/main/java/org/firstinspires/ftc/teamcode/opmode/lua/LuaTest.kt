package org.firstinspires.ftc.teamcode.opmode.lua

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.modules.lua.Lua

@Autonomous
class LuaTest : OpMode
{
	constructor()
	{
	}
	
	override fun init()
	{
		val lua = Lua();
		lua.dothing();
	}
	
	override fun loop()
	{
	
	}
}