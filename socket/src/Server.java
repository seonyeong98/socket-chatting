import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try{
            serverSocket = new ServerSocket(5000);
            System.out.println("server ready");
            socket = serverSocket.accept();
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    String name;

    Sender(Socket socket) {
        this.socket = socket;
        try{
            out = new DataOutputStream(socket.getOutputStream());
            name = "["+socket.getInetAddress() + ":" + socket.getPort() + "]";
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (out != null) {
            try{
                out.writeUTF(name + sc.nextLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Receiver extends Thread {
    Socket socket;
    DataInputStream in;

    Receiver(Socket socket) {
        this.socket = socket;
        try{
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (in != null) {
            try{
                System.out.println(in.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


