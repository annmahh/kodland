package ru.sfedu.kodland;

public class Constants {

    public static final String CSV_CANDIDATE = "csvCandidate";
    public static final String CSV_EMPLOYEE = "csvEmployee";
    public static final String CSV_JOB = "csvJob";
    public static final String CSV_DEPARTMENT = "csvDepartment";

    public static final String XML_CANDIDATE = "xmlCandidate";
    public static final String XML_EMPLOYEE = "xmlEmployee";
    public static final String XML_JOB = "xmlJob";
    public static final String XML_DEPARTMENT = "xmlDepartment";

    public static final String DB_DRIVER = "dbDriver";
    public static final String DB_CONNECT = "dbConnect";
    public static final String DB_USER = "dbUser";
    public static final String DB_PASS = "dbPass";

    public static final String INSERT_CANDIDATE = "insert into candidate values(%d, '%s', %d, %d, %d, %d, %d, %d);";
    public static final String UPDATE_CANDIDATE = "update candidate set name=%s, gender=%d, age=%d, jobId=%d, testingResult=%d, interviewingResult=%d, offerResult=%d where id=%d;";
    public static final String SELECT_CANDIDATE = "select id, name, gender, age, jobId, testingResult, interviewingResult, offerResult from candidate where id=%d;";
    public static final String DELETE_CANDIDATE = "delete from candidate where id=%d;";

    public static final String INSERT_EMPLOYEE = "insert into employee values(%d, '%s', %d, %d, %d, %d, '%s', '%s');";
    public static final String UPDATE_EMPLOYEE = "update employee set name=%s, gender=%d, age=%d, jobId=%d, departmentId=%d, dateStart=%s, dateEnd=%s where id=%d;";
    public static final String SELECT_EMPLOYEE = "select id, name, gender, age, jobId, departmentId, dateStart, dateEnd from employee where id=%d;";
    public static final String DELETE_EMPLOYEE = "delete from employee where id=%d;";

    public static final String INSERT_JOB = "insert into job values(%d, '%s', '%s', '%s', %d);";
    public static final String UPDATE_JOB = "update job set grade=%s, area=%s, position=%s, salary=%d where id=%d;";
    public static final String SELECT_JOB = "select id, name, age, experience from job where id=%d;";
    public static final String DELETE_JOB = "delete from job where id=%d;";

    public static final String INSERT_DEPARTMENT = "insert into department values(%d, '%s');";
    public static final String UPDATE_DEPARTMENT = "update department set name=%s where id=%d;";
    public static final String SELECT_DEPARTMENT = "select id, name from department where id=%d;";
    public static final String DELETE_DEPARTMENT = "delete from department where id=%d;";

    public static final String MONGO_DB="mongoDb";
    public static final String MONGO_DB_TEST="mongoDbTest";

    public enum EnumOfDataProvider {
        CSV, XML, JDBC
    }

    public enum EnumOfModel {
        CANDIDATE, EMPLOYEE, JOB, DEPARTMENT, METHOD
    }

    public enum Operation {
        CREATE, READ, UPDATE, DELETE, RECRUITE, ANALIZE
    }
}
