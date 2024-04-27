package org.firstinspires.ftc.teamcode.modules.lua

import android.util.Log

class Lua
{
	companion object
	{
		init
		{
			System.loadLibrary("ftcrobotcontroller");
		}
	}
	
	fun log(string: String)
	{
		Log.d("Lua", string);
	}
	
	external fun dothing();
}