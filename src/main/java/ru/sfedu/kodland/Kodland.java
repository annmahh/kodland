package ru.sfedu.kodland;

import ru.sfedu.kodland.api.IDataProvider;
import ru.sfedu.kodland.api.CSVDataProvider;
import ru.sfedu.kodland.api.XMLDataProvider;
import ru.sfedu.kodland.api.JDBCDataProvider;
import ru.sfedu.kodland.Constants.EnumOfDataProvider;
import ru.sfedu.kodland.Constants.EnumOfModel;
import ru.sfedu.kodland.Constants.Operation;
import ru.sfedu.kodland.model.Candidate;
import ru.sfedu.kodland.model.Employee;
import ru.sfedu.kodland.model.Job;
import ru.sfedu.kodland.model.Department;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Kodland {
    private static final Logger log = LogManager.getLogger(Kodland.class);
    protected static String model;
    protected static IDataProvider provider;
    static String[] s;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");

        try {
            provider = selectedDataProvider(EnumOfDataProvider.valueOf(s[0].toUpperCase()));
            model = selectedBean(EnumOfModel.valueOf(s[1].toUpperCase()));
        } catch (Exception exception) {
            log.error(exception);
        }
    }

    public static IDataProvider selectedDataProvider(EnumOfDataProvider enumOfDataProvider) {
        switch (enumOfDataProvider) {
            case CSV:
                return new CSVDataProvider();
            case XML:
                return new XMLDataProvider();
            case JDBC:
                return new JDBCDataProvider();
            default:
                throw new IllegalStateException(enumOfDataProvider + "does not exist");
        }
    }

    public static String selectedBean(EnumOfModel enumOfModel) {
        switch (enumOfModel) {
            case CANDIDATE:
                return handlerOfCandidate();
            case EMPLOYEE:
                return handlerOfEmployee();
            case JOB:
                return handlerOfJob();
            case DEPARTMENT:
                return handlerofDepartment();
            case METHOD:
                return handlerofMethod();
            default:
                throw new IllegalStateException(enumOfModel + "does not exist");
        }
    }

    // csv candidate create id name gender age jobId testingResult interviewingResult offerResult
    public static String handlerOfCandidate() {
        Candidate candidate = new Candidate(Integer.parseInt(s[3]), s[4], Boolean.parseBoolean(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Boolean.parseBoolean(s[8]), Boolean.parseBoolean(s[9]), Boolean.parseBoolean(s[10]));
        switch (Operation.valueOf(s[2].toUpperCase())) {
            case CREATE:
                provider.addCandidate(candidate);
            case READ:
                provider.getCandidateById(Integer.parseInt(s[3]));
            case UPDATE:
                provider.updCandidate(candidate);
            case DELETE:
                provider.delCandidateById(Integer.parseInt(s[3]));
            default:
                throw new IllegalStateException(Operation.valueOf(s[2].toUpperCase()) + "does not exist");
        }
    }

    // csv employee create id name gender age jobId departmentId dateStart dateEnd
    public static String handlerOfEmployee() {
        Employee employee = new Employee(Integer.parseInt(s[3]), s[4], Boolean.parseBoolean(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]), Integer.parseInt(s[8]), s[9], s[10]);;
        switch (Operation.valueOf(s[2].toUpperCase())) {
            case CREATE:
                provider.addEmployee(employee);
            case READ:
                provider.getEmployeeById(Integer.parseInt(s[3]));
            case UPDATE:
                provider.updEmployee(employee);
            case DELETE:
                provider.delEmployeeById(Integer.parseInt(s[3]));
            default:
                throw new IllegalStateException(Operation.valueOf(s[2].toUpperCase()) + "does not exist");
        }
    }

    //csv job create id grade area position salary
    public static String handlerOfJob() {
        Job job = new Job(Integer.parseInt(s[3]), s[4], s[5], s[6], Integer.parseInt(s[7]));
        switch (Operation.valueOf(s[2].toUpperCase())) {
            case CREATE:
                provider.addJob(job);
            case READ:
                provider.getJobById(Integer.parseInt(s[3]));
            case UPDATE:
                provider.updJob(job);
            case DELETE:
                provider.delJobById(Integer.parseInt(s[3]));
            default:
                throw new IllegalStateException(Operation.valueOf(s[2].toUpperCase()) + "does not exist");
        }
    }

    //csv department create id name
    public static String handlerofDepartment() {
        Department department = new Department(Integer.parseInt(s[3]), s[4]);
        switch (Operation.valueOf(s[2].toUpperCase())) {
            case CREATE:
                provider.addDepartment(department);
            case READ:
                provider.getDepartmentById(Integer.parseInt(s[3]));
            case UPDATE:
                provider.updDepartment(department);
            case DELETE:
                provider:
                provider.delDepartmentById(Integer.parseInt(s[3]));
            default:
                throw new IllegalStateException(Operation.valueOf(s[2].toUpperCase()) + "does not exist");
        }
    }

    // csv method recruite candidateId // csv method analize operationType
    public static String handlerofMethod() {
        switch (Operation.valueOf(s[2].toUpperCase())) {
            case RECRUITE:
                provider.recruiting(Integer.parseInt(s[3]));
            case ANALIZE:
                provider.analyzeByOperation(s[3].toUpperCase());
            default:
                throw new IllegalStateException(Operation.valueOf(s[2].toUpperCase()) + "does not exist");
        }
    }
}
