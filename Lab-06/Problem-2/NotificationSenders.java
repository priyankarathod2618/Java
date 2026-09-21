@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class EmailSender implements Notifier, Urgent {

    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSSender implements Notifier {

    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class NotificationSenders {

    public static void main(String[] args) {

        // Lambda senders
        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        Notifier[] senders = {
            email,
            sms
        };

        String message = "Lab assignment is due tomorrow.";

        System.out.println("Broadcasting message:");

        for (Notifier sender : senders) {
            sender.send(message);
        }

        System.out.println();

        Notifier urgentEmail = new EmailSender();
        Notifier normalSMS = new SMSSender();

        Notifier[] allSenders = {
            urgentEmail,
            normalSMS
        };

        System.out.println("Sending normal message:");

        for (Notifier sender : allSenders) {
            sender.send(message);
        }

        System.out.println();

        System.out.println("Sending urgent notifications:");

        for (Notifier sender : allSenders) {

            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send("URGENT: " + message);
            }
        }
    }
}
