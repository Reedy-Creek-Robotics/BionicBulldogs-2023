Opmodes = {
	{
		name = "main",
		path = "path.path",
		start = function(self, recognition)
			servos.setPos(0.5)
		end,
		markers = {
			function(self)
				servos.setPos(0)
				servos.setPos2(1)
				telem.addData("Lua", "made it to marker :)")
        telem.update()
			end,
			function(self)
				servos.setPos(1)
				servos.setPos2(0)
			end,
		},
	},
}
