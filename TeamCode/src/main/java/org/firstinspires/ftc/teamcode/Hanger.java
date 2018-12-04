package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.util.subsystem.Command;
import org.firstinspires.ftc.teamcode.util.subsystem.Subsystem;

public class Hanger extends Subsystem
{
    private OpMode mode;
    private DcMotor motor;
    private Gamepad gpad;

    public Hanger(OpMode mode)
    {
        super("Hanger");

        this.mode = mode;
    }

    @Override
    protected void init()
    {
        motor = mode.hardwareMap.dcMotor.get("hanger");
        gpad = mode.gamepad2;
    }

    public Command getTeleopCommand()
    {
        return new Command() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public void init() {
                motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                motor.setPower(0);
            }

            @Override
            public void update() {
                double power = 0;
                if (gpad.a) power = 1;
                else if (gpad.b) power = -1;

                motor.setPower(power);
            }

            @Override
            public String getName() {
                return "Teleop Command";
            }
        };
    }

}
