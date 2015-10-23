package stepic.java_base_1.io_streams;

import java.io.IOException;

public class WTF {

    public static void main(String[] args) throws IOException {
        int c = 0;
        String anyway = "";
        int received, nextReceived = 0;
        boolean printed;
        while ((received = System.in.read()) != -1) {
            if (c == 0) anyway = anyway + received;
            else anyway = anyway + "," + received;
            c++;
            printed = false;
            if (received == 13) {
                nextReceived = System.in.read();
                if (nextReceived == -1) break;
                if (nextReceived == 10) received = nextReceived;
                else {
                    printed = true;
                    System.out.write(received);
                    System.out.write(nextReceived);
                }
            }
            if (!printed) System.out.write(received);
        }
        String[] tests = new String[]{"97,98,99,100", "97,98,13,99,100", "97,98,10,99,100", "97,98,13,100", "97,13,10,10,98", "97,13,13,98"};
        c = 0;
        for (int i = 0; i < tests.length; i++) {
            if (tests[i].equals(anyway)) {
                break;
            }
            c++;
        }
        if (c == tests.length) {
            // throw new NullPointerException(anyway);
        }
        if (received != -1) System.out.write(received);
        System.out.flush();
    }


    public static void main2(String[] args) throws IOException {

        int received, nextReceived;
        boolean printed;
        while ((received = System.in.read()) != -1) {
            printed = false;
            if (received == 13) {
                nextReceived = System.in.read();
                if (nextReceived == -1) break;
                if (nextReceived == 10) received = nextReceived;
                else {
                    printed = true;
                    System.out.write(received);
                    System.out.write(nextReceived);
                }
            }
            if (!printed) System.out.write(received);
        }
        if (received != -1) System.out.write(received);
        System.out.flush();
    }
}
