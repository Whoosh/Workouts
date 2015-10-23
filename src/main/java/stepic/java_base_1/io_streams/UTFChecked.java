package stepic.java_base_1.io_streams;


import java.io.*;
import java.nio.channels.Channel;
import java.util.Scanner;

public class UTFChecked {

    public static void main(String[] args) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(("-1e3\n "
                + "18 .111 11bbb").getBytes());
        doIt(System.in);
        Channel s;
        

    }


    private static void doIt(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int buffer;
        StringBuilder stringBuilder = new StringBuilder();
        while ((buffer = inputStreamReader.read()) != -1) {
            stringBuilder.append((char) buffer);
        }
        StringReader stringReader = new StringReader(stringBuilder.toString());
        Scanner scanner = new Scanner(stringReader).useDelimiter(" |\\n");
        Double sum = 0.0;
        while (scanner.hasNext()) {
            try {
                Double d = Double.parseDouble(scanner.next());
                // String s = d.toString();
                //   int index = s.indexOf('.');
                sum += d;
                //   if (index > 0 && s.length() - index >= 6) sum += Double.valueOf(s.substring(0, index + 7));
                //else sum += d;
            } catch (Exception ignored) {

            }
        }
        String sumString = sum.toString();
        int size = 6 - sumString.substring(sumString.indexOf('.') + 1).length();
        //System.out.println(size);
        for (int i = 0; i < size; i++) {
            sumString += "0";
        }
        System.out.print(sumString);
    }


}
