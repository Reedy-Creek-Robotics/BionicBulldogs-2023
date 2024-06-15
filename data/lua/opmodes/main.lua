---@type Opmode
local opmode = {
	name = "main",
	path = "testPath.path",
	markers = {},
}

---@param recognition number
function opmode:start(recognition)
	servos.setPos(0)
	servos.setPos2(0.5)
end

function opmode.markers:grab()
	servos.setPos(1)
	delay(1)
	servos.setPos(0)
end

function opmode.markers:drop()
	servos.setPos2(1)
	delay(1)
	servos.setPos2(0)
	delay(1)
	servos.setPos2(0.5)
end