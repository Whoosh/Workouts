package random_tasks;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by whoosh on 2/10/16.
 */
public class User {
    private int fieldOne = 0;
    public Long fieldSecond = 5L;
    private RunnableFuture<Integer> integerRunnableFuture;
    public ArrayList<User> users = new ArrayList<>();

    public User(int a, long b) {
        fieldOne = a;
        fieldSecond = b;
    }

    public void setToUsers(User user) {
        users.add(user);
    }

    public static void main(String[] args) throws Exception {
        User lastUser = new User(0, 0);
        User bufferedUser;
        Long s = 1l;
        for (int i = 1; i < 10; i++) {
            bufferedUser = new User(i, i);
            lastUser.setToUsers(bufferedUser);
            lastUser = bufferedUser;
        }
        ArrayList<User> gg = new ArrayList<>();
        String typeName = gg.getClass().toGenericString();
        System.out.println(typeName);
//        Class.forName()
        pageWriter(new OutputStreamWriter(System.out), gg);
        Constructor<?>[] constructors = User.class.getConstructors();
        for (Constructor<?> constructor : constructors) {
            constructor.newInstance();
        }
    }


    private static <T> void pageWriter(Writer out, List<T> users) {
        System.out.println(Arrays.toString(users.getClass().getTypeParameters()));
    }
}
