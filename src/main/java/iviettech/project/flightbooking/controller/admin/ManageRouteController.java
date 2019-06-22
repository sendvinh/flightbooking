/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller.admin;

import iviettech.project.flightbooking.entity.FlightRoute;
import iviettech.project.flightbooking.entity.Station;
import iviettech.project.flightbooking.repository.IFlightRoute;
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
@RequestMapping(value = "/admin/manageRoute")
public class ManageRouteController {
    @Autowired
    private IFlightRoute routeDb;
    @Autowired
    private IStation stationDb;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String goToManageRoutePage(Model model) {  
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        return "Management/Route/manageRoute";
    }
    
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String findManageRoute(Model model, @RequestParam(value = "departure") String departure, 
                @RequestParam(value = "arrival") String arrival) { 
        List<Station> listStation = (List<Station>) stationDb.findAll();
        List<FlightRoute> listRoute = (List<FlightRoute>) routeDb.findByStation(departure, arrival);
        model.addAttribute("listStation", listStation);
        model.addAttribute("listRoute", listRoute);
        return "Management/Route/manageRoute";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addRoute(Model model, @ModelAttribute(value = "flightRoute") FlightRoute flightRoute) { 
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        model.addAttribute("flightRoute", new FlightRoute());
        return "Management/Route/routeForm";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addRoute(@ModelAttribute(value = "flightRoute") FlightRoute flightRoute) { 
        routeDb.save(flightRoute);
        return "redirect:/admin/manageRoute/";
    }
    
    @RequestMapping(value = "/edit/{flightRouteId}", method = RequestMethod.GET)
    public String updateRoute(Model model, @PathVariable long flightRouteId) {
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        model.addAttribute("route", routeDb.findOne(flightRouteId));
        return "Management/Route/routeForm";
    }
    
    @RequestMapping(value = "/edit/{flightRouteId}", method = RequestMethod.POST)
    public String updateRoute(@ModelAttribute(value = "flightRoute") FlightRoute flightRoute) {
        routeDb.save(flightRoute);
        return "redirect:/admin/manageRoute/";
    }
    
    @RequestMapping(value = "/delete/{flightRouteId}", method = RequestMethod.GET)
    public String deleteRoute(@PathVariable long flightRouteId) {
        routeDb.delete(routeDb.findOne(flightRouteId));
        return "redirect:/admin/manageRoute/";
    }
}
