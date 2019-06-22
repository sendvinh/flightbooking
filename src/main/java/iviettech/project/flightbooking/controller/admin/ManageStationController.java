/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller.admin;

import iviettech.project.flightbooking.entity.Station;
import iviettech.project.flightbooking.repository.IStation;
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
@RequestMapping(value = "/admin/manageStation")
public class ManageStationController {
    @Autowired
    private IStation stationDb;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String showManageStationPage(Model model) {       
        return "Management/Station/manageStation";
    }
    
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String showStation(Model model, @RequestParam(value = "station") String station) {  
        List<Station> listStation = stationDb.findByStationCodeContainingOrStationNameContaining(station, station);
       
        model.addAttribute("listStation", listStation);
        return "Management/Station/manageStation";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStation(Model model) {
        model.addAttribute("station", new Station());
        return "Management/Station/stationForm";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute(value = "station") Station station) {
        stationDb.save(station);
        return "redirect:/admin/manageStation/";
    }
    
    @RequestMapping(value = "/edit/{stationCode}", method = RequestMethod.GET)
    public String updateStation(Model model, @PathVariable String stationCode) {
        model.addAttribute("station", stationDb.findOne(stationCode));
        return "Management/Station/stationForm";
    }
    
    @RequestMapping(value = "/edit/{stationCode}", method = RequestMethod.POST)
    public String updateStation(@ModelAttribute(value = "station") Station station, @PathVariable String stationCode) {
        stationDb.delete(stationCode);
        stationDb.save(station);
        return "redirect:/admin/manageStation/";
    }
    
    @RequestMapping(value = "/delete/{stationCode}", method = RequestMethod.GET)
    public String deleteStation(@PathVariable String stationCode) {
        stationDb.delete(stationCode);
        return "redirect:/admin/manageStation/";
    }
}
