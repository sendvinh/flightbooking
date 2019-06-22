/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;


import iviettech.project.flightbooking.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrator
 */
public interface IUser extends CrudRepository<User, Integer>{
    User findById(int id);
    User findByUserName(String userName);
    
}
