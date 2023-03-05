package nrifintech.busMangementSystem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;


import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;


public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    @Query("SELECT t FROM Ticket t WHERE t.status = 'waiting' ORDER BY t.createdAt ASC")
    List<Ticket> findByBusIdAndStatusOrderByCreatedAtDesc(int busId, String status);
    
   

    
    @Query("SELECT t FROM Ticket t WHERE t.createdAt < ?1")
    List<Ticket> findByCreatedAtBefore(Date date);

    @Query("SELECT t FROM Ticket t WHERE t.status = 'confirmed' AND t.user.id=?1")
	List<Ticket> findConfirmedTicketByUser(int id);



    @Query("SELECT t FROM Ticket t WHERE t.user.id =?1 ")
	List<Ticket> findByUserId(int userId);



    @Query("SELECT t FROM Ticket t")
    List<Ticket> findByCreatedToday(@Param("current_date") Date current_date);



    
    
 
//    
//    @Modifying
//    @Transactional
//    @Query("SELECT * FROM Ticket WHERE status = "confirmed',nativeQuery = true);
    
    
}
