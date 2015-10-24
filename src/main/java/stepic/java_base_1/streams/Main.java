package stepic.java_base_1.streams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        StringReader stringReader = new StringReader("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.");
        justDoIt2(new BufferedReader(stringReader));
    }

    private static void justDoIt2(BufferedReader bufferedReader) {
        bufferedReader.lines()
                .map(String::toLowerCase)
                .map(z -> z
                        .chars()
                        .map(x -> Character.isLetterOrDigit(x) ? x : (int) ' ').mapToObj(x -> (char) x)
                        .map(x -> x + "")
                        .reduce((x, y) -> x + y)
                        .get())
                .flatMap(s -> Arrays.asList(s.split(" ")).stream())
                .collect(Collectors.groupingBy(String::hashCode))
                .values().stream()
                .sorted((list_1, list_2) -> list_2.size() - list_1.size())
                .sorted((list_1, list_2) -> list_1.size() == list_2.size() ? list_1.get(0).compareTo(list_2.get(0)) : 0)
                .limit(11)
                .forEach(x -> System.out.println(x.get(0)));
    }

    private static String removeNoise(String value) {
        return value
                .chars()
                .map(x -> Character.isLetterOrDigit(x) ? x : (int) ' ').mapToObj(x -> (char) x)
                .map(x -> x + "")
                .reduce((x, y) -> x + y)
                .get();
    }

    public static void justDoIt(InputStream io) throws Exception {
        new BufferedReader(new InputStreamReader(io, "UTF-8")).lines()
                .map(String::toLowerCase)
                .map(Main::removeNoise)
                .flatMap(s -> Arrays.asList(s.split(" ")).stream())
                .collect(Collectors.toCollection(ArrayList::new)).stream().collect(Collectors.groupingBy(String::hashCode))
                .values()
                .stream()
                .sorted((list_1, list_2) -> list_2.size() - list_1.size())
                .sorted((list_1, list_2) -> list_1.size() == list_2.size() ? list_1.get(0).compareTo(list_2.get(0)) : 0)
                .forEach(x -> System.out.println(x.get(0)));
    }
}
