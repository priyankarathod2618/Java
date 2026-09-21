interface Switchable {

    void on();

    void off();

    default void toggle() {
        System.out.println("Toggling device...");
    }
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

@FunctionalInterface
interface SwitchPolicy {

    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl {

    public static void main(String[] args) {

        // Creating devices
        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("Toggling all devices:");

        for (Switchable device : devices) {
            device.toggle();
        }

        System.out.println();

        SwitchPolicy anonymousPolicy = new SwitchPolicy() {

            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 8 && hour <= 22;
            }
        };

        SwitchPolicy lambdaPolicy =
                (device, hour) -> hour >= 6 && hour <= 23;

        int hour = 20;

        System.out.println("Current hour: " + hour);

        System.out.println(
            "Anonymous class policy: "
            + anonymousPolicy.maySwitchOn(devices[0], hour)
        );

        System.out.println(
            "Lambda policy: "
            + lambdaPolicy.maySwitchOn(devices[1], hour)
        );

        System.out.println();

        for (Switchable device : devices) {

            if (lambdaPolicy.maySwitchOn(device, hour)) {
                device.on();
            } else {
                device.off();
            }
        }
    }
}