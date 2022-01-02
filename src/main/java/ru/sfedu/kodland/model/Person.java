package ru.sfedu.kodland.model;

import com.opencsv.bean.CsvBindByPosition;

public class Person {

    @CsvBindByPosition(position = 0)
    protected Integer id;
    @CsvBindByPosition(position = 1)
    protected String name;
    @CsvBindByPosition(position = 2)
    protected Boolean gender;
    @CsvBindByPosition(position = 3)
    protected Integer age;
    @CsvBindByPosition(position = 4)
    protected Integer jobId;

    public Person() {
    }

    public Person(Integer id, String name, Boolean gender, Integer age, Integer jobId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.jobId = jobId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getGender() { return gender; }
    public void setGender(Boolean gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }    

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name +
                ", gender='" + gender +
                ", age='" + age +
                ", jobId=" + jobId +
                '}';
    }
}