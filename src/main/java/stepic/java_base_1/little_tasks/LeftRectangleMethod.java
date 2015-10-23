package stepic.java_base_1.little_tasks;

public class LeftRectangleMethod {

    public static void main(String[] args) {
        double a = 5, b = 10;
        double n = 100000000;
        double w = (b - a) / n;
        double result = f(w)*w;
        double iter = w+a;
        for (int i = 1; i < n-1; i++,iter+=w) {
            result += f(iter)*w;
        }
        System.out.println(result);
    }

    public static double f(double x) {
        return Math.cos(x);
    }


}
