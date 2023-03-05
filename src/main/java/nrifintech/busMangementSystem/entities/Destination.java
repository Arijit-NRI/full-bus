package nrifintech.busMangementSystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class Destination {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;

    
    float latitude;
    float longitude;
    
    // other fields, constructors, getters and setters

}

//public class Destination {
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany(mappedBy = "destinations")
//    private Set<Route> routes = new HashSet<>();
//    
//    float latitude;
//    float longitude;
//
//
//    // other fields, constructors, getters and setters
//}
