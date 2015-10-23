package project_euler.sub_code;

import java.lang.reflect.Method;
import java.util.stream.Stream;

public interface Starter {

    default void start()  {
        try {
            Class<? extends Starter> aClass = getClass();
            Starter starter = aClass.newInstance();
            Stream.of(aClass.getMethods())
                    .filter(method -> method.isAnnotationPresent(StartWithLazzyBenchmark.class))
                    .forEachOrdered(method -> runner(starter, method));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    default void runner(Object starter, Method method) {
        try {
            System.out.println("Method name - " + method.getName());
            method.setAccessible(true);
            checkTime(method, starter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    default void checkTime(Method method, Object clazzObject) throws Exception {
        long startTime = System.currentTimeMillis();
        method.invoke(clazzObject);
        long endTime = System.currentTimeMillis();
        System.out.println("Exe time: " + (endTime - startTime) + " ms");
    }
}
