package ru.sfedu.kodland.mongo;

import ru.sfedu.kodland.mongo.MongoDBDataProvider;
import ru.sfedu.kodland.model.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

public abstract class AbstractDataProvider {
    private final MongoDBDataProvider provider = new MongoDBDataProvider();

    public AbstractDataProvider() throws IOException {}

    public void saveToLog(HistoryContent historyContent, String string) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
    provider.insertRecord(historyContent, string);
    }
   
    public abstract Result addEmployee(Employee obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Optional<Employee> getEmployeeById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result updEmployee(Employee obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result delEmployeeById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    
    public abstract Result addCandidate(Candidate obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Optional<Candidate> getCandidateById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result updCandidate(Candidate obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result delCandidateById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    
    public abstract Result addJob(Job obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Optional<Job> getJobById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result updJob(Job obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result delJobById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    
    public abstract Result addDepartment(Department obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Optional<Department> getDepartmentById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result updDepartment(Department obj) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Result delDepartmentById(Integer id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException; 
}