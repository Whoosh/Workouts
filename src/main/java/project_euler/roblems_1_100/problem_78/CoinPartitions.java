package project_euler.roblems_1_100.problem_78;

/**
 * Created by whoosh on 4/4/16.
 */
public class CoinPartitions {
    public static void main(String[] args) {
        int N = 1000000;
        int[] map = new int[N + 2];
        map[0] = 0;
        map[1] = 1;
        int sum = 0;
        int index = 1;
        int g;
        int[] sign = new int[]{-1, 1, 1, -1};
        for (int n = 2; n <= N + 1; n++, index = 1, sum = 0, g = 0) {
            g = getGeneralizedOfK(index);
            while (g < n) {
                sum += map[n - g] * sign[index % 4];
                index++;
                g = getGeneralizedOfK(index);
            }
            map[n] = sum % 1000000;
            if (map[n] == 0) {
                System.out.println(n - 1);
                break;
            }
        }
    }

    public static int getGeneralizedOfK(int indexOfG) {
        if ((indexOfG & 1) == 0) {
            return (-indexOfG / 2) * (3 * (-indexOfG / 2) - 1) / 2;
        } else {
            return ((indexOfG / 2) + 1) * ((3 * ((indexOfG / 2) + 1)) - 1) / 2;
        }
    }
}
