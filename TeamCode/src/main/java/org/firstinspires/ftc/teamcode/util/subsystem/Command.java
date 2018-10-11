package org.firstinspires.ftc.teamcode.util.subsystem;

public interface Command
{
    boolean isFinished();
    void init();
    void update();
    String getName();
}
