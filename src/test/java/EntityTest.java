import ru.sfedu.kodland.model.Employee;
import ru.sfedu.kodland.model.Person;

public class EntityTest {
    protected Employee getEmployee() { return new Employee(1,"NovitskayaAI",true,16,1,1,"01.01.2020",null); }
    protected Employee getEmployeeForUpd() {return new Employee(1,"NovitskayaAI_UPD",true,23, 1,1,"01.01.2020","01.12.2021"); }
}