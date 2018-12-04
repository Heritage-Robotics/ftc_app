package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class DropAuton extends OpMode
{
    private DriveTrain dt = new DriveTrain(this, gamepad1, gamepad2, telemetry);
    private Hanger hang = new Hanger(this);

    @Override
    public void init() {
        dt.start();
        hang.start();
    }

    @Override
    public void start() {
        dt.setCommand(null);
        hang.setCommand(hang.setPosition(90));
    }

    @Override
    public void loop() {

    }
}
