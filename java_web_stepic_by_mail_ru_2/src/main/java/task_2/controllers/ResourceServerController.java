package task_2.controllers;

import task_2.resources.TestResource;

/**
 * Created by whoosh on 2/26/16.
 */
public class ResourceServerController implements ResourceServerControllerMBean {

    private TestResource testResource;

    public ResourceServerController() {
        testResource = new TestResource();
    }

    @Override
    public void setResource(TestResource resource) {
        this.testResource = resource;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return this.testResource.getName();
    }

    @Override
    public int getAge() {
        return this.testResource.getAge();
    }

    @Override
    public void setAge(int age) {

    }
}
