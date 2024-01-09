import java.net.*;
import java.io.*;

public class ClientConnect{
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    
    
    public ClientConnect(String address, int port){
        try {
            socket = new Socket(address, port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        String readLine = "";
        while(readLine.equals("close")){
            try{
                readLine = input.readUTF();
                output.writeUTF(readLine);
            }
            catch(Exception e){
            }
        }
        
        try{
            socket.close();
            input.close();
            output.close();
        }
        catch(Exception e){
        }
    }
    
    public static void main(String args[]){
        ClientConnect playerTwo = new ClientConnect("localhost", 5000);
    }
    
    
}