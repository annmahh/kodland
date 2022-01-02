import org.junit.Test;
import ru.sfedu.kodland.api.IDataProvider;
import ru.sfedu.kodland.api.CSVDataProvider;
import ru.sfedu.kodland.model.*;
import static ru.sfedu.kodland.model.Result.SUCCESS;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.*;

public class CSVDataProviderTest extends EntityTest {
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class.getName());
    protected IDataProvider provider = new CSVDataProvider();

     Result result;
     Employee employee = getEmployee();
     Employee employeeUpd = getEmployeeForUpd();
     Optional<Employee> optionalEmployee;

    @Test
    public void CSVTestCrud() {
        log.info(employee.toString());
        log.info(employeeUpd.toString());

        optionalEmployee = provider.getEmployeeById(employee.getId());
        System.out.printf("GetEmployeeById[NEGATIVE]: %s\n", optionalEmployee.toString());
        assertFalse(optionalEmployee.isPresent());

        result = provider.delEmployeeById(employee.getId());
        System.out.printf("DelEmployeeById[NEGATIVE]: %s\n", result.name());
        assertFalse(optionalEmployee.isPresent());

        result = provider.updEmployee(employeeUpd);
        System.out.printf("UpdEmployee[NEGATIVE]: %s\n", result.name());
        assertNotEquals(result, SUCCESS);

        result = provider.addEmployee(employee);
        System.out.printf("AddEmployee[POSITIVE]: %s\n", result.name());
        assertEquals(result, SUCCESS);

        result = provider.updEmployee(employeeUpd);
        System.out.printf("UpdEmployee[POSITIVE]: %s\n", result.name());
        assertEquals(result, SUCCESS);

        optionalEmployee = provider.getEmployeeById(employee.getId());
        System.out.printf("GetEmployeeById[POSITIVE]: %s\n", optionalEmployee.toString());
        assertTrue(optionalEmployee.isPresent());

        result = provider.addEmployee(employee);
        System.out.printf("AddEmployee[NEGATIVE]: %s\n", result.name());
        assertNotEquals(result, SUCCESS);

        result = provider.delEmployeeById(employee.getId());
        System.out.printf("DelEmployeeById[POSITIVE]: %s\n", result.name());
        assertTrue(optionalEmployee.isPresent());

        optionalEmployee = provider.getEmployeeById(employee.getId());
        System.out.printf("GetEmployeeById[CHECK_DELETE]: %s\n", optionalEmployee.isEmpty());
        assertTrue(optionalEmployee.isEmpty());
    }
}