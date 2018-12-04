package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Teleop", group = "Teleop")
public class TeleopOpMode extends OpMode
{
    private DriveTrain dt = new DriveTrain(this, gamepad1, gamepad2, telemetry);
    private Hanger hanger = new Hanger(this);

    @Override
    public void init()
    {
        telemetry.addLine("Init");
        dt.start();
        hanger.start();
    }

    public void start()
    {
        telemetry.addLine("Start");
        dt.setCommand(dt.getTeleopCommand());
        hanger.setCommand(hanger.getTeleopCommand());
    }

    @Override
    public void loop()
    {

    }

    @Override
    public void stop()
    {
        dt.setCommand(null);
        hanger.setCommand(null);
    }
}
