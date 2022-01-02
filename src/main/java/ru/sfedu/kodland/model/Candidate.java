package ru.sfedu.kodland.model;

import com.opencsv.bean.CsvBindByPosition;

public class Candidate extends Person {

    @CsvBindByPosition(position = 5)
    private Boolean testingResult;
    @CsvBindByPosition(position = 6)
    private Boolean interviewingResult;
    @CsvBindByPosition(position = 7)
    private Boolean offerResult;

    public Candidate() {
    }

    public Candidate(Integer id, String name, Boolean gender, Integer age, Integer jobId, Boolean testingResult, Boolean interviewingResult, Boolean offerResult) {
        super(id, name, gender, age, jobId);
        this.testingResult = testingResult;
        this.interviewingResult = interviewingResult;
        this.offerResult = offerResult;
    }

    public Boolean getTestingResult() { return testingResult; }
    public void setTestingResult(Boolean testingResult) { this.testingResult = testingResult; }

    public Boolean getInterviewingResult() { return interviewingResult; }
    public void setInterviewingResult(Boolean interviewingResult) { this.interviewingResult = interviewingResult; }
    
    public Boolean getOfferResult() { return offerResult; }
    public void setOfferResult(Boolean offerResult) { this.offerResult = offerResult; }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name +
                ", gender='" + gender +
                ", age='" + age +
                ", jobId=" + jobId +
                ", testingResult=" + testingResult +
                ", interviewingResult=" + interviewingResult +
                ", offerResult=" + offerResult +
                '}';
    }
}
