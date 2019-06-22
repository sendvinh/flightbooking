/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.Aircraft;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IAircraft extends CrudRepository<Aircraft, String>{
    List<Aircraft> findByAircraftCodeContainingOrBrandNameContaining(String aircraftCode, String brandName);
    
}
