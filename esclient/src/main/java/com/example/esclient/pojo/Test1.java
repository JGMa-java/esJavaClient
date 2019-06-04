package com.example.esclient.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by Majg on 2019-05-29
 **/
@Document(indexName = "test1",type = "doc")
public class Test1 {
    private String first_name;
    private String last_name;
    private String age;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
