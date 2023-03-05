package nrifintech.busMangementSystem.entities;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    int total_destinations;
    
    int start_destination_id;
    int end_destination_id;
    
}
//@Entity
//public class Route {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany
//    @JoinTable(
//        name = "route_destination",
//        joinColumns = @JoinColumn(name = "route_id"),
//        inverseJoinColumns = @JoinColumn(name = "destination_id")
//    )
//    private Set<Destination> destinations = new HashSet<>();
//}
