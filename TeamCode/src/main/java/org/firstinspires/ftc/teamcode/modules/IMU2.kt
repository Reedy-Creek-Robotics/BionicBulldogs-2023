package org.firstinspires.ftc.teamcode.modules

import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties
import com.qualcomm.robotcore.hardware.configuration.annotations.I2cDeviceType
import com.qualcomm.robotcore.util.TypeConversion

@I2cDeviceType
@DeviceProperties(name = "Adafruit imu", description = "imu", xmlTag = "imu")
class IMU2 : I2cDeviceSynchDevice<I2cDeviceSynch>
{
	constructor(client: I2cDeviceSynch, owned: Boolean) : super(client, owned)
	{
		deviceClient.i2cAddress = I2cAddr(0b1101010);
		registerArmingStateCallback(false);
		deviceClient.readWindow = I2cDeviceSynch.ReadWindow(
			Register.TempL.v, 2, I2cDeviceSynch.ReadMode.REPEAT
		);
		deviceClient.engage();
	}
	
	fun getTemp(): Short
	{
		val lower = readByte(Register.TempL);
		val upper = readByte(Register.TempH);
		val temp = ByteArray(2);
		temp[0] = lower;
		temp[1] = upper;
		return TypeConversion.byteArrayToShort(temp);
	}
	
	override fun getManufacturer(): HardwareDevice.Manufacturer
	{
		return HardwareDevice.Manufacturer.Adafruit;
	}
	
	override fun doInitialize(): Boolean
	{
		return true;
	}
	
	override fun getDeviceName(): String
	{
		return "I2C imu";
	}
	
	private enum class Register
	{
		TempL(0x20),
		TempH(0x21);
		
		constructor(a: Int)
		{
			v = a;
		}
		
		val v: Int;
	}
	
	private fun readShort(reg: Register): Short
	{
		return TypeConversion.byteArrayToShort(deviceClient.read(reg.v));
	}
	
	private fun readByte(reg: Register): Byte
	{
		return deviceClient.read(reg.v)[0];
	}
}