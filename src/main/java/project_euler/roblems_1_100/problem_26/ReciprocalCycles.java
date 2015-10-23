package project_euler.roblems_1_100.problem_26;

import project_euler.sub_code.CustomMathFunctions;

/**
 * https://projecteuler.net/problem=26
 * Сначало было решено через BigIntegers и String // говнокод,медленно,блевал.
 * Пока думал и гуглил о решении поиска повторений в последовательности из остатоков без строк, нашёл ->
 * http://www.mathblog.dk/project-euler-26-find-the-value-of-d-1000-for-which-1d-contains-the-longest-recurring-cycle/
 * Понравилась идея распределения по массиву, где в качестве индекса имеет место быть значение последовательности.
 * Добавлена проверка на простоту делителя, т.к это гарантирует наличие циклического остатка.
 */

public class ReciprocalCycles {

    private final static int D = 1000;

    public static void main(String[] args) {
        System.out.println(findF());
    }

    private static int findF() {
        int sequenceLength = 0, result = 0, position, reminder;
        int[] allocationMap;

        for (int divisor = D; divisor > 1; divisor--) {
            if (!CustomMathFunctions.isPrime(divisor)) continue;
            if (sequenceLength >= divisor) break;
            allocationMap = new int[divisor];
            position = 0;
            reminder = 1;
            while (allocationMap[reminder] == 0 && reminder != 0) {
                allocationMap[reminder] = position++;
                reminder = (reminder *= 10) % divisor;
            }
            result = position - allocationMap[reminder];
            if (result > sequenceLength) {
                sequenceLength = result;
                result = divisor;
            }
        }
        return result;
    }

}
