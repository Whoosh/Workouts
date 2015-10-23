package stepic.java_base_1.io_streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoPublisher {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("159.203.96.235", 500);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        for (int j = 0; j < 5; j++)
            for (int i = 0; i < 127; i++) {
                out.write(i);
            }

        out.close();
    }
}
