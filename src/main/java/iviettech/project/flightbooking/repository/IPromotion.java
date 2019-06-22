/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.Promotion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IPromotion extends CrudRepository<Promotion, Long>{
    @Query(value = "select * from promotion p inner join flight_promotion fp on "
            + "p.promotion_id = fp.promotion_id where fp.flight_id = ?1", nativeQuery = true)
    List<Promotion> getPromotionOfFlight(long flightId);
    
    List<Promotion> findByDescriptionContaining(String description);
}
