package ru.sfedu.kodland.api;

import ru.sfedu.kodland.model.*;

import java.util.Optional;

public interface IDataProvider {

    Result addEmployee(Employee obj);
    Optional<Employee> getEmployeeById(Integer id);
    Result updEmployee(Employee obj);
    Result delEmployeeById(Integer id);

    Result addCandidate(Candidate obj);
    Optional<Candidate> getCandidateById(Integer id);
    Result updCandidate(Candidate obj);
    Result delCandidateById(Integer id);

    Result addJob(Job obj);
    Optional<Job> getJobById(Integer id);
    Result updJob(Job obj);
    Result delJobById(Integer id);

    Result addDepartment(Department obj);
    Optional<Department> getDepartmentById(Integer id);
    Result updDepartment(Department obj);
    Result delDepartmentById(Integer id);
    
    void recruiting(Integer candidateId);
    void onboarding(Integer candidateId);
    void offboarding(Integer candidateId);
    Boolean testing(Integer testingScore);
    Boolean interviewing(Integer interviewingScore);
    
    void analyzeByOperation(String operationType);
    Integer analyzePercentByOffer();
    Integer analyzeCountByAge();
}