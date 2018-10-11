package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Teleop", group = "Teleop")
public class TeleopOpMode extends OpMode
{
    private DriveTrain dt = new DriveTrain(this, gamepad1, gamepad2, telemetry);

    @Override
    public void init()
    {
        telemetry.addLine("Init");
        dt.start();
    }

    public void start()
    {
        telemetry.addLine("Start");
        dt.setCommand(dt.getTeleopCommand());
    }

    @Override
    public void loop()
    {

    }
}
