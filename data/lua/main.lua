opmodes = {
	{
		name = "main",
		path = "paths/path.path",
		start = function(self, recognition)
			servos.setPos(0.5)
			self.servoPosition = 0.5
			self.recognition = recognition
			print(recognition)
		end,
		markers = {
			function(self)
				servos.setPos(1)
				self.servoPosition = 1
			end,
			function(self)
				servos.setPos(0)
				self.servoPosition = 0
			end,
		},
	},
}
