package task_2.controllers;

import task_2.resources.TestResource;

/**
 * Created by whoosh on 2/26/16.
 */
public interface ResourceServerControllerMBean {

    void setResource(TestResource resource);

    void setName(String name);

    String getName();

    int getAge();

    void setAge(int age);
}
