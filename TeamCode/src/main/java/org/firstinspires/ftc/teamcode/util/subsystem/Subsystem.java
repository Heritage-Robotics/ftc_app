package org.firstinspires.ftc.teamcode.util.subsystem;

public abstract class Subsystem extends Thread
{
    private Thread _thread;
    private String _subsystemName;

    private Command _currentCommand = null;
    private boolean _hasCommandInit = false;
    private boolean _isEnabled = false;
    private boolean _isSubsystemActive = false;

    protected abstract void init();

    public Subsystem(String subsystemName) { _subsystemName = subsystemName; }

    public synchronized void setCommand(Command command)
    {
        _hasCommandInit = false;
        _currentCommand = command;
        System.out.println("[" + _subsystemName + "] Command is set to " + command.getName());
    }

    protected synchronized void runCommand()
    {
        if (_currentCommand == null)
        {
            _isSubsystemActive = false;
            return;
        }

        _isSubsystemActive = true;

        if (!_hasCommandInit)
        {
            _currentCommand.init();
            _hasCommandInit = true;
        }

        if (!_currentCommand.isFinished())
            _currentCommand.update();
        else
            _currentCommand = null;

    }

    @Override
    public void run()
    {
        while (_isEnabled)
        {
            runCommand();
            try {
                Thread.sleep(5, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start()
    {
        if (_thread != null)
        {
            _thread = new Thread(this, _subsystemName);
            _isEnabled = true;
            init();
            _thread.start();
        }
    }

    public boolean isSubsystemActive() { return _isSubsystemActive; }
}
