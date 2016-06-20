package project_euler.sub_code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileFunctions {

    public static List<String> getFileLines(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public static List<String> getFileLines(String path, Class<?> clazz) {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(clazz.getClassLoader().getResource(path).getFile())))) {
            return reader.lines().collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

}
