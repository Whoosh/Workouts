package stepic.java_base_1.little_tasks;

import java.math.BigInteger;

public class Main extends A{

    @Override
    public BigInteger test(int a){
        return null;
    }

    public static void main(String[] args) throws Exception {
        String[] roles = {"Городничий", "Аммос Федорович", "Артемий Филиппович", "Лука Лукич"};
        String[] text = {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
            if(roles != null){
                System.out.println("1");
            }
        String[] roles2 = {"A", "AA", "AAA"};
        String[] text2 = {"AAA: 1A", "AA: 2AA", "A: 3AAA"};

        System.out.println(printTextPerRole(roles2, text2));
    }

    private static String printTextPerRole(String[] roles, String[] textLines) {
        java.util.LinkedHashMap<String, StringBuilder> buffer = new java.util.LinkedHashMap<>();
        StringBuilder result = new StringBuilder(10_000_000);
        for (String role : roles) buffer.put(role + ":", new StringBuilder(500_000));
        for (int i = 0; i < textLines.length; i++) {
            for (String role : buffer.keySet()) {
                if (textLines[i].indexOf(role)==0) {
                    buffer.put(role, buffer.get(role)
                            .append("\n").append(i + 1).append(")").append(textLines[i].substring(role.length())));
                    break;
                }
            }
        }
        for (java.util.Map.Entry<String, StringBuilder> entry : buffer.entrySet()) {
            result.append(entry.getKey());
            result.append(entry.getValue());
            result.append("\n\n");
        }
        return result.toString();
    }

    private static int[] foo(int[] a1, int[] a2) {
        int[] a3 = new int[a1.length + a2.length];
        if (a1.length == 0) return a2;
        if (a2.length == 0) return a1;
        for (int i = 0, k = 0, j = 0; j < a3.length; ) {
            while (i < a1.length && a1[i] <= a2[k]) a3[j++] = a1[i++];
            while (i < a1.length && k < a2.length && a2[k] <= a1[i]) a3[j++] = a2[k++];
            if (i == a1.length) while (k < a2.length) a3[j++] = a2[k++];
            if (k == a2.length) while (i < a1.length) a3[j++] = a1[i++];
        }
        return a3;
    }

    public static BigInteger foo(int value) {
        if (BigInteger.valueOf(value).equals(BigInteger.ONE)) return BigInteger.valueOf(value);
        return BigInteger.valueOf(value).multiply(foo(BigInteger.valueOf(value).subtract(BigInteger.ONE).intValue()));
    }
}
