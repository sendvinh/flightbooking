/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.securityService;

import iviettech.project.flightbooking.entity.Role;
import iviettech.project.flightbooking.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
     
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userService.findByUserName(userName);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found"); 
        }
        boolean accountisEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
            return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            accountisEnabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(Role role : user.getListRole()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
            System.out.print("role :" + role.getRoleName().toString());
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
}
