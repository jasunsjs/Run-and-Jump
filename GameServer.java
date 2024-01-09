import java.net.*;
import java.io.*;

public class GameServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public GameServer(int port){
        try{
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("connected");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        String readLine = "";
        while(!readLine.equals("close")){
            try {
                output.writeUTF("hi");
                readLine = input.readUTF();
                System.out.println(readLine);
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        try {
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
