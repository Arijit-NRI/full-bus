package nrifintech.busMangementSystem.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.User;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BusDto {
    private int id;
    private String name;
    private int numberOfSeats;
}