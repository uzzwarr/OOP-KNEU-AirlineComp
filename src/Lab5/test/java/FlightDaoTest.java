import Lab5.Airplane;
import Lab5.FlightDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlightDaoTest {

    @Test
    public void testSaveAirplane(){
        FlightDao dao = new FlightDao();
        Airplane testPlane = new Airplane();
        testPlane.setModel("Mriya");
        testPlane.setCapacity(0);

        dao.save(testPlane);

        assertNotNull(testPlane.getId(), "The aircraft ID must not be null after saving!");
    }
}
