package org.firstinspires.ftc.teamcode.modules.drive;

import org.firstinspires.ftc.teamcode.config.XDriveConfig;

public interface HDrive {
    void init(XDriveConfig config);
    void update(float forward, float strafe, float rotate);
    void updateFR(float forward, float strafe, float rotate);
    void updateFR(float forward, float strafe, float rotate, float heading);
}
