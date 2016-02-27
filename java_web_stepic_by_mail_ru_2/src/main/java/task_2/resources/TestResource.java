package task_2.resources;


public class TestResource {

    private String name;
    private int age;

    public TestResource(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TestResource() {
        this.name = "";
        this.age = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "TestResource{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
