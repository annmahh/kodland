package ru.sfedu.kodland.api;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import ru.sfedu.kodland.model.*;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.Collections.singletonList;
import static ru.sfedu.kodland.Constants.*;
import static ru.sfedu.kodland.model.Result.SUCCESS;
import static ru.sfedu.kodland.model.Result.UNSUCCESS;
import static ru.sfedu.kodland.utils.ConfigurationUtil.getConfigurationEntry;

public class CSVDataProvider implements IDataProvider {
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class.getName());

    @Override
    public Result addEmployee(Employee obj) {
        log.info("Start addEmployee(Employee obj)");
        log.debug(obj.toString());
        return getEmployeeById(Math.toIntExact(obj.getId())).
                map(o -> UNSUCCESS).
                orElseGet(() -> execute(singletonList(obj), CSV_EMPLOYEE, true));
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        log.info("Start getEmployeeById(Integer id))");
        log.debug(id);
        return getAll(Employee.class, CSV_EMPLOYEE)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updEmployee(Employee obj) {
        log.info("Start updEmployee(Employee obj)");
        log.debug(obj.toString());
        return getEmployeeById(Math.toIntExact(obj.getId()))
                .map(o -> {
                    delEmployeeById(Math.toIntExact(o.getId()));
                    return execute(singletonList(obj), CSV_EMPLOYEE, true);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result delEmployeeById(Integer id) {
        log.info("Start delEmployeeById(Integer id)");
        log.debug(id);
        return getEmployeeById(id)
                .map(o -> {
                    List<Employee> list = getAll(Employee.class, CSV_EMPLOYEE)
                            .stream()
                            .filter(i -> !i.getId().equals(id))
                            .collect(Collectors.toList());
                    return execute(list, CSV_EMPLOYEE, false);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result addCandidate(Candidate obj) {
        log.info("Start addCandidate(Candidate obj)");
        log.debug(obj.toString());
        return getCandidateById(Math.toIntExact(obj.getId()))
                .map(o -> UNSUCCESS)
                .orElseGet(() -> execute(singletonList(obj), CSV_CANDIDATE, true));
    }

    @Override
    public Optional<Candidate> getCandidateById(Integer id) {
        log.info("Start getCandidateById(Integer id)");
        log.debug(id);
        return getAll(Candidate.class, CSV_CANDIDATE)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updCandidate(Candidate obj) {
        log.info("Start updCandidate(Candidate obj)");
        log.debug(obj.toString());
        return getCandidateById(Math.toIntExact(obj.getId()))
                .map(o -> {
                    delCandidateById(Math.toIntExact(o.getId()));
                    return execute(singletonList(obj), CSV_CANDIDATE, true);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result delCandidateById(Integer id) {
        log.info("Start delCandidateById(Integer id)");
        log.debug(id);
        return getCandidateById(id)
                .map(o -> {
                    List<Candidate> list = getAll(Candidate.class, CSV_CANDIDATE)
                            .stream()
                            .filter(i -> !i.getId().equals(id))
                            .collect(Collectors.toList());
                    return execute(list, CSV_CANDIDATE, false);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result addJob(Job obj) {
        log.info("Start addJob(Job obj)");
        log.debug(obj.toString());
        return getJobById(obj.getId())
                .map(o -> UNSUCCESS)
                .orElseGet(() -> execute(singletonList(obj), CSV_JOB, true));
    }

    @Override
    public Optional<Job> getJobById(Integer id) {
        log.info("Start getJobById(Integer id)");
        log.debug(id);
        return getAll(Job.class, CSV_JOB)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updJob(Job obj) {
        log.info("Start updJob(Job obj)");
        log.debug(obj.toString());
        return getJobById(obj.getId())
                .map(o -> {
                    delJobById(o.getId());
                    return execute(singletonList(obj), CSV_JOB, true);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result delJobById(Integer id) {
        log.info("Start delJobById(Integer id");
        log.debug(id);
        return getJobById(id)
                .map(o -> {
                    List<Job> list = getAll(Job.class, CSV_JOB)
                            .stream()
                            .filter(i -> !i.getId().equals(id))
                            .collect(Collectors.toList());
                    return execute(list, CSV_JOB, false);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result addDepartment(Department obj) {
        log.info("Start addDepartment(Department obj)");
        log.debug(obj.toString());
        return getDepartmentById(obj.getId())
                .map(o -> UNSUCCESS)
                .orElseGet(() -> execute(singletonList(obj), CSV_DEPARTMENT, true));
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        log.info("Start getDepartmentById(Integer id)");
        log.debug(id);
        return getAll(Department.class, CSV_DEPARTMENT)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updDepartment(Department obj) {
        log.info("Start updDepartment(Department obj)");
        log.debug(obj.toString());
        return getDepartmentById(obj.getId())
                .map(o -> {
                    delDepartmentById(o.getId());
                    return execute(singletonList(obj), CSV_DEPARTMENT, true);
                }).orElse(UNSUCCESS);
    }

    @Override
    public Result delDepartmentById(Integer id) {
        log.info("Start delDepartmentById(Integer id)");
        log.debug(id);
        return getDepartmentById(id)
                .map(o -> {
                    List<Department> list = getAll(Department.class, CSV_DEPARTMENT)
                            .stream()
                            .filter(i -> !i.getId().equals(id))
                            .collect(Collectors.toList());
                    return execute(list, CSV_DEPARTMENT, false);
                }).orElse(UNSUCCESS);
    }

    protected <T> List<T> getAll(Class<T> tClass, String key) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(getConfigurationEntry(key)));
            List<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                    .withType(tClass)
                    .build()
                    .parse();
            csvReader.close();
            return csvToBean;
        } catch (Exception exception) {
            log.error(exception);
        }
        return new ArrayList<>();
    }

    protected <T> Result execute(List<T> list, String key, boolean append) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(getConfigurationEntry(key), append));
            new StatefulBeanToCsvBuilder<T>(csvWriter)
                    .build()
                    .write(list);
            csvWriter.close();
            return SUCCESS;
        } catch (Exception exception) {
            log.error(exception);
        }
        return UNSUCCESS;
    }

    @Override
    public void recruiting(Integer candidateId) {
        log.info("Start recruiting(Integer candidateId)");
        log.debug(candidateId);
        Candidate candidate = getCandidateById(candidateId).get();

        Scanner in = new Scanner(System.in);
        Integer testingScore = in.nextInt();
        Integer interviewingScore = in.nextInt();

        switch (testingScore) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> candidate.setTestingResult(testing(testingScore));
            default -> {
                log.error("testingScore must be from 0 to 10");
                return;
            }
        }

        switch (interviewingScore) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> candidate.setInterviewingResult(interviewing(interviewingScore));
            default -> {
                log.error("interviewingScore must be from 0 to 10");
                return;
            }
        }

        updCandidate(candidate);
        if (candidate.getTestingResult() == true && candidate.getInterviewingResult() == true) {
            onboarding(candidateId);
        } else
            offboarding(candidateId);
    }

    @Override
    public void onboarding(Integer candidateId) {
        log.info("Start onboarding(Integer candidateId)");
        log.debug(candidateId);
        Candidate candidate = getCandidateById(candidateId).get();
        candidate.setOfferResult(true);
        updCandidate(candidate);

        Scanner in = new Scanner(System.in);
        Integer departmentId = in.nextInt();

        Employee employee = new Employee(candidate.getId(), candidate.getName(), candidate.getGender(), candidate.getAge(), candidate.getJobId(), departmentId, new Date().toString(), null );
        addEmployee(employee);
    }

    @Override
    public void offboarding(Integer candidateId) {
        log.info("Start offboarding(Integer candidateId)");
        log.debug(candidateId);
        Candidate candidate = getCandidateById(candidateId).get();
        candidate.setOfferResult(false);
        updCandidate(candidate);
    }

    @Override
    public Boolean testing(Integer testingScore) {
        log.info("Start testing(Integer testingScore)");
        log.debug(testingScore);
        boolean testingResult = switch (testingScore) {
            case 7, 8, 9, 10 -> true;
            default -> false;
        };
        return testingResult;
    }

    @Override
    public Boolean interviewing(Integer interviewingScore) {
        log.info("Start interviewing(Integer interviewingScore)");
        log.debug(interviewingScore);
        boolean interviewingResult = switch (interviewingScore) {
            case 9, 10 -> true;
            default -> false;
        };
        return interviewingResult;
    }

    @Override
    public void analyzeByOperation(String operationType) {
        log.info("Start analyzeByOperation(String operationType)");
        log.debug(operationType);
        switch (operationType) {
            case "OFFER" -> {log.info("analyzePercentByOffer = " + analyzePercentByOffer() + "%");}
            case "COUNT" -> {log.info("analyzeCountByAge = " + analyzeCountByAge());}
            default -> {
                log.error(operationType + "does not exist");
            }
        }
    }

    @Override
    public Integer analyzePercentByOffer() {
        log.info("Start analyzePercentByOffer()");
        int ifTrue = (int) getAll(Candidate.class, CSV_CANDIDATE)
                .stream()
                .filter((o) -> o.getOfferResult() == true)
                .count();

        int ifFalse = (int) getAll(Candidate.class, CSV_CANDIDATE)
                .stream()
                .filter((o) -> o.getOfferResult() == false)
                .count();

        return ifTrue * 100 / (ifTrue + ifFalse);
    }

    @Override
    public Integer analyzeCountByAge() {
        log.info("Start analyzeAverageByAge");
        int count = (int) getAll(Candidate.class, CSV_CANDIDATE)
                .stream()
                .filter((o) -> o.getAge() >= 18)
                .filter((o) -> (o.getGender() == true && o.getAge() < 55) || (o.getGender() == false && o.getAge() < 60))
                .count();
        return count;
    }
}