package Lab6;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The seat number cannot be left blank!")
    private String seatNumber;

    @Min(value = 0, message = "The ticket price cannot be less than zero!")
    private double price;

    public Ticket() {}

    public Ticket(String seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
