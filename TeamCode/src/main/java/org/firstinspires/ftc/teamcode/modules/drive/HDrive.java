package org.firstinspires.ftc.teamcode.modules.drive;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface HDrive {
    void init(HardwareMap hmap);
    void update(float forward, float strafe, float rotate);
    void updateFR(float forward, float strafe, float rotate, float heading);
}
