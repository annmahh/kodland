package ru.sfedu.kodland.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.kodland.model.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.random;
import static ru.sfedu.kodland.Constants.*;
import static ru.sfedu.kodland.model.Result.SUCCESS;
import static ru.sfedu.kodland.model.Result.UNSUCCESS;
import static ru.sfedu.kodland.utils.ConfigurationUtil.getConfigurationEntry;

public class XMLDataProvider implements IDataProvider {
    private static final Logger log = LogManager.getLogger(XMLDataProvider.class);

    @Override
    public Result addEmployee(Employee obj) {
        List<Employee> list = getAll(Employee.class, XML_EMPLOYEE);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            list.add(obj);
            return execute(list, XML_EMPLOYEE);
        }
        return UNSUCCESS;
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return getAll(Employee.class, XML_EMPLOYEE)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updEmployee(Employee obj) {
        List<Employee> list = getAll(Employee.class, XML_EMPLOYEE);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            return UNSUCCESS;
        }
        list.removeIf(o -> o.getId().equals(obj.getId()));
        list.add(obj);
        return execute(list, XML_EMPLOYEE);
    }

    @Override
    public Result delEmployeeById(Integer id) {
        return getEmployeeById(id).map(o -> {
            List<Employee> list = getAll(Employee.class, XML_EMPLOYEE)
                    .stream()
                    .filter(i -> !i.getId().equals(id)).collect(Collectors.toList());
            return execute(list, XML_EMPLOYEE);
        }).orElse(UNSUCCESS);
    }

    @Override
    public Result addCandidate(Candidate obj) {
        List<Candidate> list = getAll(Candidate.class, XML_CANDIDATE);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            list.add(obj);
            return execute(list, XML_CANDIDATE);
        }
        return UNSUCCESS;
    }

    @Override
    public Optional<Candidate> getCandidateById(Integer id) {
        return getAll(Candidate.class, XML_CANDIDATE)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updCandidate(Candidate obj) {
        List<Candidate> list = getAll(Candidate.class, XML_CANDIDATE);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            return UNSUCCESS;
        }
        list.removeIf(o -> o.getId().equals(obj.getId()));
        list.add(obj);
        return execute(list, XML_CANDIDATE);
    }

    @Override
    public Result delCandidateById(Integer id) {
        return getCandidateById(id).map(o -> {
            List<Candidate> list = getAll(Candidate.class, XML_CANDIDATE)
                    .stream()
                    .filter(i -> !i.getId().equals(id)).collect(Collectors.toList());
            return execute(list, XML_CANDIDATE);
        }).orElse(UNSUCCESS);
    }

    @Override
    public Result addJob(Job obj) {
        List<Job> list = getAll(Job.class, XML_JOB);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            list.add(obj);
            return execute(list, XML_JOB);
        }
        return UNSUCCESS;
    }

    @Override
    public Optional<Job> getJobById(Integer id) {
        return getAll(Job.class, XML_JOB)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updJob(Job obj) {
        List<Job> list = getAll(Job.class, XML_JOB);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            return UNSUCCESS;
        }
        list.removeIf(o -> o.getId().equals(obj.getId()));
        list.add(obj);
        return execute(list, XML_JOB);
    }

    @Override
    public Result delJobById(Integer id) {
        return getJobById(id).map(o -> {
            List<Job> list = getAll(Job.class, XML_JOB)
                    .stream()
                    .filter(i -> !i.getId().equals(id)).collect(Collectors.toList());
            return execute(list, XML_JOB);
        }).orElse(UNSUCCESS);
    }

    @Override
    public Result addDepartment(Department obj) {
        List<Department> list = getAll(Department.class, XML_DEPARTMENT);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            list.add(obj);
            return execute(list, XML_DEPARTMENT);
        }
        return UNSUCCESS;
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        return getAll(Department.class, XML_DEPARTMENT)
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Result updDepartment(Department obj) {
        List<Department> list = getAll(Department.class, XML_DEPARTMENT);
        if (list.stream().noneMatch(o -> o.getId().equals(obj.getId()))) {
            return UNSUCCESS;
        }
        list.removeIf(o -> o.getId().equals(obj.getId()));
        list.add(obj);
        return execute(list, XML_DEPARTMENT);
    }

    @Override
    public Result delDepartmentById(Integer id) {
        return getDepartmentById(id).map(o -> {
            List<Department> list = getAll(Department.class, XML_DEPARTMENT)
                    .stream()
                    .filter(i -> !i.getId().equals(id)).collect(Collectors.toList());
            return execute(list, XML_DEPARTMENT);
        }).orElse(UNSUCCESS);           }

    protected <T> Result execute(List<T> list, String key) {
        try {
            FileWriter fileWriter = new FileWriter(getConfigurationEntry(key));
            Serializer serializer = new Persister();
            serializer.write(new XMLContainer<T>(list), fileWriter);
            fileWriter.close();
            return SUCCESS;
        } catch (Exception exception) {
            log.error(exception);
            return UNSUCCESS;
        }
    }

    protected <T> List<T> getAll(Class<T> tClass, String key) {
        try {
            FileReader fileReader = new FileReader(getConfigurationEntry(key));
            Serializer serializer = new Persister();

            XMLContainer<T> container = serializer.read(XMLContainer.class, fileReader);
            fileReader.close();

            return nullToList(container.getContainer());
        } catch (Exception exception) {
            log.error(exception);
            return new ArrayList<>();
        }
    }

    private static <T> List<T> nullToList(List<T> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
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
        int ifTrue = (int) getAll(Candidate.class, XML_CANDIDATE)
                .stream()
                .filter((o) -> o.getOfferResult() == true)
                .count();

        int ifFalse = (int) getAll(Candidate.class, XML_CANDIDATE)
                .stream()
                .filter((o) -> o.getOfferResult() == false)
                .count();

        return ifTrue * 100 / (ifTrue + ifFalse);
    }

    @Override
    public Integer analyzeCountByAge() {
        log.info("Start analyzeAverageByAge");
        int count = (int) getAll(Candidate.class, XML_CANDIDATE)
                .stream()
                .filter((o) -> o.getAge() >= 18)
                .filter((o) -> (o.getGender() == true && o.getAge() < 55) || (o.getGender() == false && o.getAge() < 60))
                .count();
        return count;
    }
}
