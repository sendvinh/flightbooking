/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller.admin;

import iviettech.project.flightbooking.entity.Aircraft;
import iviettech.project.flightbooking.repository.IAircraft;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin/manageAircraft")
public class ManageAircraftController {
    @Autowired
    private IAircraft aircraftDb;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String goToManageAircraftPage(Model model) {  
        return "Management/Aircraft/manageAircraft";
    }
    
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String findAircraft(Model model, @RequestParam(value = "aircraft") String aicraftCode) { 
        List<Aircraft> listAircraft = (List<Aircraft>) aircraftDb.findByAircraftCodeContainingOrBrandNameContaining(aicraftCode, aicraftCode);
        model.addAttribute("listAircraft", listAircraft);
        return "Management/Aircraft/manageAircraft";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addAircraft(Model model) { 
        model.addAttribute("aircraft", new Aircraft());
        return "Management/Aircraft/aircraftForm";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addAircraft(Model model, @ModelAttribute(value = "aircraft") Aircraft aircraft) { 
        aircraftDb.save(aircraft);
        return "redirect:/admin/manageAircraft/";
    }
    
    @RequestMapping(value = "/edit/{aircraftCode}", method = RequestMethod.GET)
    public String updateAircraft(Model model, @PathVariable String aircraftCode) {
        model.addAttribute("aircraft", aircraftDb.findOne(aircraftCode));
        return "Management/Aircraft/aircraftForm";
    }
    
    @RequestMapping(value = "/edit/{aircraftCode}", method = RequestMethod.POST)
    public String updateAircraft(@ModelAttribute(value = "aircraft") Aircraft aircraft, @PathVariable String aircraftCode) {
        aircraftDb.delete(aircraftCode);
        aircraftDb.save(aircraft);
        return "redirect:/admin/manageAircraft/";
    }
    
    @RequestMapping(value = "/delete/{aircraftCode}", method = RequestMethod.GET)
    public String deleteStation(@PathVariable String aircraftCode) {
        aircraftDb.delete(aircraftCode);
        return "redirect:/admin/manageAircraft/";
    }
}
