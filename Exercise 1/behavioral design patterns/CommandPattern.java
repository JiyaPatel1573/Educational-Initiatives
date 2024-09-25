// Command Interface
interface Command {
    void execute();
}

// Receiver 1 (Light)
class Light {
    public void on() {
        System.out.println("The light is ON");
    }

    public void off() {
        System.out.println("The light is OFF");
    }
}

// Receiver 2 (Fan)
class Fan {
    public void on() {
        System.out.println("The fan is ON");
    }

    public void off() {
        System.out.println("The fan is OFF");
    }
}

// Concrete Command 1 (LightOnCommand)
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

// Concrete Command 2 (FanOffCommand)
class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.off();
    }
}

// Invoker (Remote Control)
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Test the Command Pattern
public class CommandPattern{
    public static void main(String[] args) {
        // Create receivers
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        // Create commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command fanOff = new FanOffCommand(ceilingFan);

        // Set commands in invoker (remote control)
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();

        // Turn off the fan
        remote.setCommand(fanOff);
        remote.pressButton();
    }
}
