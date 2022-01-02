package ru.sfedu.kodland.model;

import com.opencsv.bean.CsvBindByPosition;

public class Employee extends Person {

    @CsvBindByPosition(position = 5)
    private Integer departmentId;
    @CsvBindByPosition(position = 6)
    protected String dateStart;
    @CsvBindByPosition(position = 7)
    protected String dateEnd;

    public Employee() {
    }
    

    public Employee(Integer id, String name, Boolean gender, Integer age, Integer jobId, Integer departmentId, String dateStart, String dateEnd) {
        super(id, name, gender, age, jobId);
        this.departmentId = departmentId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

    public String getDateStart() { return dateStart; }
    public void setDateStart(String dateStart) { this.dateStart = dateStart; }
    
    public String getDateEnd() { return dateEnd; }
    public void setDateEnd(String dateEnd) { this.dateEnd = dateEnd; }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name +
                ", gender='" + gender +
                ", age='" + age +
                ", jobId=" + jobId +
                ", departmentId=" + departmentId +
                ", dateStart='" + dateStart +
                ", dateEnd='" + dateEnd +
                '}';
    }
}
