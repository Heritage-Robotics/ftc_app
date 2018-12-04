package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.subsystem.Command;
import org.firstinspires.ftc.teamcode.util.subsystem.Subsystem;

public class DriveTrain extends Subsystem
{
    private final DcMotor.ZeroPowerBehavior DEFAULT_ZERO_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;

    private DcMotor _leftA, /*_leftB,*/ _rightA/*, _rightB*/;
    private OpMode _opmode;
    private Gamepad gpad1, gpad2;
    private Telemetry telemetry;

    public DriveTrain(OpMode mode, Gamepad gpad1, Gamepad gpad2, Telemetry telemetry)
    {
        super("DriveTrain");
        this._opmode = mode;
        this.gpad1 = gpad1;
        this.gpad2 = gpad2;
        this.telemetry = telemetry;
    }

    @Override
    protected void init()
    {
        HardwareMap map = _opmode.hardwareMap;
        _leftA = map.dcMotor.get("left_rear_drive");
//        _leftB = map.dcMotor.get("leftB");
        _rightA = map.dcMotor.get("right_rear_drive");
//        _rightB = map.dcMotor.get("rightB");
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
                reset();
                setMotors(0, 0);
                telemetry.addLine("Teleop Init");
            }

            @Override
            public void update() {
                telemetry.addLine("Teleop Update");
                setMotors(-gpad1.left_stick_y, -gpad1.right_stick_y);
            }

            @Override
            public String getName() {
                return "Teleop";
            }
        };
    }

    public Command driveDistance()
    {
        return new Command() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public void init() {

            }

            @Override
            public void update() {

            }

            @Override
            public String getName() {
                return "Drive Distance";
            }
        };
    }

    private void setMotors(double leftPower, double rightPower)
    {
        _leftA.setPower(leftPower);
//        _leftB.setPower(leftPower);
        _rightA.setPower(rightPower);
//        _rightB.setPower(rightPower);
    }

    private void reset()
    {
        setMotors(0, 0);
        _leftA.setZeroPowerBehavior(DEFAULT_ZERO_POWER_BEHAVIOR);
        _rightA.setZeroPowerBehavior(DEFAULT_ZERO_POWER_BEHAVIOR);
    }
}