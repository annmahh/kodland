package ru.sfedu.kodland.model;

import com.opencsv.bean.CsvBindByPosition;

public class Job {

    @CsvBindByPosition(position = 0)
    private Integer id;
    @CsvBindByPosition(position = 1)
    private String grade;
    @CsvBindByPosition(position = 2)
    private String area;
    @CsvBindByPosition(position = 3)
    private String position;
    @CsvBindByPosition(position = 4)
    private Integer salary;

    public Job() {
    }

    public Job(Integer id, String grade, String area, String position, Integer salary) {
        this.id = id;
        this.grade = grade;
        this.area = area;
        this.position = position;
        this.salary = salary;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getGrade() {  return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Integer getSalary() {return salary;}
    public void setSalary(Integer salary) {this.salary = salary;}

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", grade='" + grade +
                ", area='" + area +
                ", position='" + position +
                ", salary='" + salary +
                '}';
    }
}