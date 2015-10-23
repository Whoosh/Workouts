package stepic.java_base_1.io_streams;

import java.io.*;

public class CheckSumOfStream {
    public static void main(String[] args) throws IOException {
        byte[] bytes = {0x33, 0x45, 0x01};
        System.out.println(checkSumOfStream(new ByteArrayInputStream(bytes)));
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int sum = 0, received;
        while ((received = inputStream.read()) != -1) sum = Integer.rotateLeft(sum,1)^received;
        return sum;
    }

}
