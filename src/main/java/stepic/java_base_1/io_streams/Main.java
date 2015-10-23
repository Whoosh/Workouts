package stepic.java_base_1.io_streams;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        withoutClone();
    }

    private static void withoutClone() {
        int received, nextReceived;
        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{123, 0, 13, 10, 10, 13});
        boolean printed;
        while ((received = stream.read()) != -1) {
            printed = false;
            if (received == 13) {
                nextReceived = stream.read();
                if (nextReceived == -1) break;
                if (nextReceived == 10) received = nextReceived;
                else {
                    printed = true;
                    System.out.println(received);
                    System.out.println(nextReceived);
                }
            }
            if (!printed) System.out.println(received);
        }
        if (received != -1) System.out.println(received);
        System.out.flush();
    }

    private static void withoutClonePub() throws IOException {
        int received, nextReceived;
        while ((received = System.in.read()) != -1) {
            if (received == 13) {
                nextReceived = System.in.read();
                if (nextReceived == -1) break;
                if (nextReceived == 10) received = nextReceived;
                if (nextReceived == 13) System.out.println(received);
            }
            System.out.write(received);
        }
        if (received != -1) System.out.write(received);
        System.out.flush();
    }

    private static void first() {
        int received, nextReceived, innerReceived;
        received = nextReceived = innerReceived = 0;
        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 10});
        int dCount = 0;
        boolean hadClones = false;
        while ((received = stream.read()) != -1) {
            hadClones = false;
            if (received == 13) {
                nextReceived = stream.read();
                if (nextReceived == -1) break;
                if (nextReceived == 10) received = nextReceived;
                if (nextReceived == 13) {
                    dCount = 2;
                    hadClones = true;
                    while ((innerReceived = stream.read()) == 13) dCount++;
                    if (innerReceived == -1) {
                        for (int i = 0; i < dCount; i++) System.out.println(13);
                        break;
                    }
                    if (innerReceived == 10) {
                        received = innerReceived;
                    } else {
                        for (int i = 0; i < dCount; i++) System.out.println(13);
                        received = innerReceived;
                    }
                }
            }
            System.out.println(received);
        }
        if (!hadClones && received != -1) System.out.println(received);
        System.out.flush();
    }

    public static void main2(String[] args) throws IOException {
        int received, nextReceived, innerReceived;
        int dCount = 0;
        boolean hadClones = false;
        while ((received = System.in.read()) != -1) {
            hadClones = false;
            if (received == 13) {
                nextReceived = System.in.read();
                if (nextReceived == -1) break;
                if (nextReceived == 10) received = nextReceived;
                if (nextReceived == 13) {
                    dCount = 2;
                    hadClones = true;
                    while ((innerReceived = System.in.read()) == 13) dCount++;
                    if (innerReceived == -1) {
                        for (int i = 0; i < dCount; i++) System.out.write(13);
                        break;
                    }
                    if (innerReceived == 10) {
                        received = innerReceived;
                    } else {
                        for (int i = 0; i < dCount; i++) System.out.write(13);
                        received = innerReceived;
                    }
                }
            }
            System.out.write(received);
        }
        if (!hadClones && received != -1) System.out.write(received);
        System.out.flush();
    }
}
