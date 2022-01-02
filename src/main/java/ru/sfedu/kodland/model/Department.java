package ru.sfedu.kodland.model;

import com.opencsv.bean.CsvBindByPosition;

public class Department {

    @CsvBindByPosition(position = 0)
    private Integer id;
    @CsvBindByPosition(position = 1)
    private String name;

    public Department() {
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
