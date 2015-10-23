package stepic.java_base_1.io_streams;

import java.io.*;
import java.net.*;
//3e52348RR
public class EchoListener {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(500);
        Socket client = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        int received;
        while ((received = in.read())!=-1){
            System.out.println(received);
        }
    }
}
