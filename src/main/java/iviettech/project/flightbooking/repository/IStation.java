/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.Station;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IStation extends CrudRepository<Station, String> {
    List<Station> findByStationCodeContainingOrStationNameContaining(String stationCode, String stationName);
}
