package Lab6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TicketDaoTest {

    @Test
    public void testSaveValidTicket() {
        TicketDao dao = new TicketDao();
        Ticket ticket1 = new Ticket("67A", 1488.69);

        dao.save(ticket1);

        assertNotNull(ticket1.getId(), "The ticket ID must not be empty after being saved to the database!");
    }

    @Test
    public void testSaveInvalidTicketThrowsException() {
        TicketDao dao = new TicketDao();
        Ticket badTicket = new Ticket("52B", -100.0);


        assertThrows(Exception.class, () -> {
            dao.save(badTicket);
        });
    }

    @Test
    public void testFindByHqlQuery() {
        TicketDao dao = new TicketDao();
        dao.save(new Ticket("99C", 2000.0));


        List<Ticket> tickets = dao.findByHqlQuery("FROM Ticket");

        assertNotNull(tickets, "The list must not be null");
        assertFalse(tickets.isEmpty(), "The list of tickets must not be empty");
    }
}
