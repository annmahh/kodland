package ru.sfedu.kodland.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.kodland.model.*;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import static ru.sfedu.kodland.Constants.*;
import static ru.sfedu.kodland.model.Result.*;
import static ru.sfedu.kodland.utils.ConfigurationUtil.getConfigurationEntry;

public class JDBCDataProvider implements IDataProvider {
    private static final Logger log = LogManager.getLogger(JDBCDataProvider.class);

    @Override
    public Result addEmployee(Employee obj) {
        final String sql = String.format(INSERT_EMPLOYEE, obj.getId(), obj.getName(), obj.getGender(), obj.getAge(), obj.getJobId(), obj.getDepartmentId(), obj.getDateStart(), obj.getDateEnd());
        return getEmployeeById(Math.toIntExact(obj.getId())).
                map(o -> UNSUCCESS).
                orElseGet(() -> execute(sql));
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        Employee employee = null;
        try {
            ResultSet resultSet = select(String.format(SELECT_EMPLOYEE, id));
            if (resultSet != null && resultSet.next()) {
            }
        } catch (Exception exception) {
            log.error(exception);
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public Result updEmployee(Employee obj) {
        final String sql = String.format(UPDATE_EMPLOYEE, obj.getId(), obj.getName(), obj.getGender(), obj.getAge(), obj.getJobId(), obj.getDepartmentId(), obj.getDateStart(), obj.getDateEnd());
        return getEmployeeById(Math.toIntExact(obj.getId()))
                .map(o -> execute(sql))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result delEmployeeById(Integer id) {
        return getEmployeeById(id)
                .map(o -> execute(String.format(DELETE_EMPLOYEE, id)))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result addCandidate(Candidate obj) {
        final String sql = String.format(INSERT_CANDIDATE, obj.getId(), obj.getName(), obj.getGender(), obj.getAge(), obj.getJobId(), obj.getTestingResult(), obj.getInterviewingResult(), obj.getOfferResult());
        return getCandidateById(Math.toIntExact(obj.getId())).
                map(o -> UNSUCCESS).
                orElseGet(() -> execute(sql));
    }

    @Override
    public Optional<Candidate> getCandidateById(Integer id) {
        Candidate candidate = null;
        try {
            ResultSet resultSet = select(String.format(SELECT_CANDIDATE, id));
            if (resultSet != null && resultSet.next()) {
            }
        } catch (Exception exception) {
            log.error(exception);
        }
        return Optional.ofNullable(candidate);
    }

    @Override
    public Result updCandidate(Candidate obj) {
        final String sql = String.format(UPDATE_CANDIDATE, obj.getId(), obj.getName(), obj.getGender(), obj.getAge(), obj.getJobId(), obj.getTestingResult(), obj.getInterviewingResult(), obj.getOfferResult());
        return getCandidateById(Math.toIntExact(obj.getId()))
                .map(o -> execute(sql))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result delCandidateById(Integer id) {
        return getCandidateById(id)
                .map(o -> execute(String.format(DELETE_CANDIDATE, id)))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result addJob(Job obj) {
        final String sql = String.format(INSERT_JOB, obj.getId(), obj.getGrade(), obj.getArea(), obj.getPosition(), obj.getSalary());
        return getJobById(Math.toIntExact(obj.getId())).
                map(o -> UNSUCCESS).
                orElseGet(() -> execute(sql));
    }

    @Override
    public Optional<Job> getJobById(Integer id) {
        Job job = null;
        try {
            ResultSet resultSet = select(String.format(SELECT_JOB, id));
            if (resultSet != null && resultSet.next()) {
            }
        } catch (Exception exception) {
            log.error(exception);
        }
        return Optional.ofNullable(job);
    }

    @Override
    public Result updJob(Job obj) {
        final String sql = String.format(UPDATE_JOB, obj.getId(), obj.getGrade(), obj.getArea(), obj.getPosition(), obj.getSalary());
        return getJobById(Math.toIntExact(obj.getId()))
                .map(o -> execute(sql))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result delJobById(Integer id) {
        return getJobById(id)
                .map(o -> execute(String.format(DELETE_JOB, id)))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result addDepartment(Department obj) {
        final String sql = String.format(INSERT_DEPARTMENT, obj.getId(), obj.getName());
        return getDepartmentById(Math.toIntExact(obj.getId())).
                map(o -> UNSUCCESS).
                orElseGet(() -> execute(sql));    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        Department department = null;
        try {
            ResultSet resultSet = select(String.format(SELECT_DEPARTMENT, id));
            if (resultSet != null && resultSet.next()) {
            }
        } catch (Exception exception) {
            log.error(exception);
        }
        return Optional.ofNullable(department);    }

    @Override
    public Result updDepartment(Department obj) {
        final String sql = String.format(UPDATE_DEPARTMENT, obj.getId(), obj.getName());
        return getDepartmentById(Math.toIntExact(obj.getId()))
                .map(o -> execute(sql))
                .orElse(UNSUCCESS);
    }

    @Override
    public Result delDepartmentById(Integer id) {
        return getDepartmentById(id)
                .map(o -> execute(String.format(DELETE_DEPARTMENT, id)))
                .orElse(UNSUCCESS);    }

    protected Connection connection() throws Exception {
        Class.forName(getConfigurationEntry(DB_DRIVER));
        Connection connection = DriverManager.getConnection(
                getConfigurationEntry(DB_CONNECT),
                getConfigurationEntry(DB_USER),
                getConfigurationEntry(DB_PASS)
        );
        connection.setAutoCommit(true);
        return connection;
    }

    protected Result execute(String sql) {
        try {
            PreparedStatement statement = connection().prepareStatement(sql);
            statement.executeUpdate();
            connection().close();
            return SUCCESS;
        } catch (Exception exception) {
            log.error(exception);
            return UNSUCCESS;
        }
    }

    protected ResultSet select(String sql) {
        try {
            PreparedStatement statement = connection().prepareStatement(sql);
            connection().close();
            return statement.executeQuery();
        } catch (Exception exception) {
            log.error(exception);
            return null;
        }
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
        // not supported yet
        return null;
    }

    @Override
    public Integer analyzeCountByAge() {
        log.info("Start analyzeCountByAge()");
        // not supported yet
        return null;
    }
}
