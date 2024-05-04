opmodes = {   
	{     
		name = "main",     
		path = "path.path",     
		start = function()       
			setPos(0.5);     
		end,     
		markers = {       
			function()         
				setPos(1);       
			end,       
			function()         
				setPos(0);       
			end     
		}   
	}
}