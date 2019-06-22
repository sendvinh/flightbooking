/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */
@Controller
public class ManagerController {
    
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String showSearchForm(Model model) {       
        return "Management/admin";
    } 
    
    @RequestMapping(value = {"/customer"}, method = RequestMethod.GET)
    public String showManageFlightPage(Model model) {       
        return "Management/customer";
    }
}
