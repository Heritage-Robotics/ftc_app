package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.subsystem.Command;
import org.firstinspires.ftc.teamcode.util.subsystem.Subsystem;

@TeleOp (name = "Teleop", group = "Teleop")
public class TeleopOpMode extends OpMode
{
    private DriveTrain dt = new DriveTrain(this, gamepad1, gamepad2, telemetry);
    private Hanger hanger = new Hanger(this);
    private Flicker flicker = new Flicker();

    private FlickPos pos = FlickPos.STOWED;

    private enum FlickPos
    {
        STOWED(0), EXTENDED(1);

        private final double pos;
        FlickPos(double pos) { this.pos = pos; }
        public double get() { return pos; }
    }

    private class Flicker extends Subsystem
    {
        private Servo servo = hardwareMap.servo.get("flicker");

        public Flicker() { super("Flicker"); }

        @Override
        protected void init() { }

        public Command setPos(final double pos)
        {
            return new Command() {
                @Override
                public boolean isFinished() {
                    return pos == servo.getPosition();
                }

                @Override
                public void init() {
                    servo.setPosition(pos);
                }

                @Override
                public void update() {

                }

                @Override
                public String getName() {
                    return "SetPos";
                }
            };
        }
    }

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
        FlickPos lastPos = null;
        if (gamepad2.a) pos = FlickPos.STOWED;
        else if (gamepad2.b) pos = FlickPos.EXTENDED;

        if (lastPos != pos) {
            flicker.setCommand(flicker.setPos(pos.get()));
        }
    }

    @Override
    public void stop()
    {
        dt.setCommand(null);
        hanger.setCommand(null);
    }
}
