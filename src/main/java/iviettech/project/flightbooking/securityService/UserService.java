/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.securityService;

import iviettech.project.flightbooking.entity.User;
import iviettech.project.flightbooking.repository.IUser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service("userService")
@Transactional
public class UserService {
    @Autowired
    private IUser userDb;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDb.save(user);
    }
     
    public User findById(int id) {
        return userDb.findById(id);
    }
 
    public User findByUserName(String userName) {
        return userDb.findByUserName(userName);
    }
}

