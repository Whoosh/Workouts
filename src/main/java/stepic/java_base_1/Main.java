package stepic.java_base_1;

public class Main {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
        get1();
    }

    private static void get1() {
        get2();
    }

    private static void get2() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length < 3) return null;
        for (int i = 0; i < stackTrace.length; i++) {
            if (stackTrace[i].getMethodName().equals("getCallerClassAndMethodName")) {
                return stackTrace[i + 2].getClassName() + "#" + stackTrace[i + 2].getMethodName();
            }
        }
        return null;
    }
}

