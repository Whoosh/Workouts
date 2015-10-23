package project_euler.roblems_1_100.problem_59;

import project_euler.sub_code.FileFunctions;

import java.util.ArrayList;

/**
 * https://projecteuler.net/problem=59
 */

public class XORdecryption {

    private static final String PATH = "/home/whoosh/IdeaProjects/ProjectEuler/src/main/java/problems_1_100/problem_59/text.txt";
    private static final int KEY_LEN = 3;
    private static String possiblyWord = " and ";

    public static void main(String[] args) {
        final String text = FileFunctions.getFileLines(PATH).get(0);
        final ArrayList<Character> chars = new ArrayList<>(text.length() / 2);
        String foundedKey = null;
        for (String s : text.split("[,]")) chars.add((char) (Integer.parseInt(s)));
        for (int i = 0; i < chars.size() - possiblyWord.length(); i++) {
            foundedKey = foundKey(chars, i);
            if (isKeyContainsOneSelf(foundedKey)) {
                foundedKey = foundedKey.substring(0, KEY_LEN);
                break;
            }
        }
        int result = 0, i = 0;
        for (Character aChar : chars) result += aChar ^ foundedKey.charAt(i++ % KEY_LEN);
        System.out.println(foundedKey);
        System.out.println(result);
    }

    private static boolean isKeyContainsOneSelf(String lastFounded) {
        if (lastFounded == null) return false;
        for (int i = KEY_LEN, k = 0; i < lastFounded.length(); i++, k++) {
            if (lastFounded.charAt(k) != lastFounded.charAt(i)) return false;
        }
        return true;
    }

    private static String foundKey(ArrayList<Character> chars, int index) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0, key; i < possiblyWord.length(); i++) {
            key = findKey(chars.get(index++), possiblyWord.charAt(i));
            if (key != 0) result.append((char) key);
            else return null;
        }
        return result.toString();
    }

    private static char findKey(char val, char expected) {
        for (char i = 'a'; i <= 'z'; i++)
            if (expected == (char) (val ^ i)) return i;
        return 0;
    }
}
