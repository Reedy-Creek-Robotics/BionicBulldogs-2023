package org.firstinspires.ftc.teamcode.opmode.lua

import android.os.Environment
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.ServerSocket

class FileSync
{
	constructor()
	{
		val t = Thread{
			kotlin.run {
				
				val server = ServerSocket(6969);
				Log.d("FServer", "server started");
				while(true)
				{
					val socket = server.accept();
					Log.d("FServer", "got message");
					var str = "";
					val reader = BufferedReader(InputStreamReader(socket.getInputStream()));
					val sb = StringBuilder();
					var line: String?;
					while(reader.readLine().also { line = it } != null) sb.append(line)
						.append("\n");
					str = sb.toString();
					var path = "";
					var data = "";
					var d = false;
					for(s in str)
					{
						if(s == '?' && !d)
						{
							d = true;
							continue;
						}
						if(s.toInt() == 1)
						{
							val a = path.split('/');
							var folders = "";
							for(i in 0..a.size - 2)
							{
								folders += a[i];
								if(i < a.size - 2)
								{
									folders += '/';
								}
							}
							Log.d("FServer", folders);
							if(!File(Environment.getExternalStorageDirectory(), folders).mkdirs())
							{
								Log.d("FServer", "could not create folders at $folders");
							}
							val file = File(Environment.getExternalStorageDirectory(), path);
							if(!file.exists())
							{
								file.createNewFile();
							}
							val c = file.outputStream();
							c.write(data.toByteArray());
							c.close();
							path = "";
							data = "";
							d = false;
							continue;
						}
						if(!d)
						{
							path += s;
						}
						else
						{
							data += s;
						}
					}
					Log.d("FServer", "done");
					socket.close();
				}
			}
		};
		t.start();
	}
}