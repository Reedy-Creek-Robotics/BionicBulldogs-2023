package org.firstinspires.ftc.teamcode.opmode.lua

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.modules.Ui.UI
import org.firstinspires.ftc.teamcode.modules.lua.Lua

@Autonomous
class LuaTest : LinearOpMode
{
	private var opmodes: Array<String>? = null;
	private var ui: UI = UI();
	private var selected: String = "";
	var lua: Lua? = null;

	constructor()
	{
	}

	override fun runOpMode()
	{
		lua = Lua(this);
		opmodes = lua?.init();
		ui.init(telemetry, gamepad1)
		while(opModeInInit())
		{
			if(selected == "")
			{
				val iter = opmodes?.iterator();
				if(iter != null)
				{
					ui.label("select opmode");
					for(s in iter)
					{
						if(ui.button(s))
						{
							selected = s;
						}
					}
				}
			}
			else
			{
				ui.label("$selected selected");
			}
			ui.update();
		}
		lua?.start(selected);
		lua?.stop();
	}
}