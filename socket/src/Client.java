import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            String serverIp = "172.30.1.8";
            Socket socket = new Socket(serverIp, 5000);
            System.out.println("connected");
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
