/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller.admin;

import iviettech.project.flightbooking.entity.Flight;
import iviettech.project.flightbooking.entity.Promotion;
import iviettech.project.flightbooking.entity.SeatType;
import iviettech.project.flightbooking.entity.Station;
import iviettech.project.flightbooking.helper.EntityHelper;
import iviettech.project.flightbooking.repository.IAircraft;
import iviettech.project.flightbooking.repository.IFlight;
import iviettech.project.flightbooking.repository.IFlightRoute;
import iviettech.project.flightbooking.repository.IPromotion;
import iviettech.project.flightbooking.repository.ISeatType;
import iviettech.project.flightbooking.repository.IStation;
import iviettech.project.flightbooking.repository.ITicket;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/admin/manageFlight")
public class ManageFlightController {
    @Autowired
    private IFlight flightDb;
    
    @Autowired
    private IAircraft aircraftDb;
    
    @Autowired
    private IFlightRoute flightRouteDb;
    
    @Autowired
    private IStation stationDb;
    
    @Autowired
    private ISeatType seatTypeDb;
    
    @Autowired
    private ITicket ticketDb;
    
    @Autowired
    private IPromotion promotionDb;
    
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String goToManageAircraftPage(Model model) {  
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        return "Management/Flight/manageFlight";
    }
    
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String findAircraft(Model model, @RequestParam(value = "departure") String departure,
                                        @RequestParam(value = "arrival") String arrival,
                                        @RequestParam(value = "date") String date) { 
        List<Flight> listFlight = (List<Flight>) flightDb.searchFlight(date, departure, arrival);
        List<SeatType> listSeatType = (List<SeatType>) seatTypeDb.findAll();
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        model.addAttribute("listFlight", listFlight);
        model.addAttribute("ticketDb", ticketDb);
        model.addAttribute("listSeatType", listSeatType);
        return "Management/Flight/manageFlight";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addAircraft(Model model) { 
        model.addAttribute("aircraft", new Flight());
        addAttributeToForm(model);
        return "Management/Flight/flightForm";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addAircraft(Model model,HttpServletRequest request, @ModelAttribute(value = "flight") Flight flight) { 
        String[] listPromotionId = request.getParameterValues("promotionId");
        List<Promotion> listPromotion = EntityHelper.createListPromotionFromListPrmotionId(promotionDb, listPromotionId);
        flight.setListPromotion(listPromotion);
        flightDb.save(flight);
        return "redirect:/admin/manageFlight/";
    }
    
    @RequestMapping(value = "/edit/{flightId}", method = RequestMethod.GET)
    public String updateAircraft(Model model, @PathVariable long flightId) {
        model.addAttribute("flight", flightDb.findOne(flightId));
        addAttributeToForm(model);
        return "Management/Flight/flightForm";
    }
    
    @RequestMapping(value = "/edit/{flightId}", method = RequestMethod.POST)
    public String updateAircraft(@ModelAttribute(value = "flight") Flight flight) {
        return "redirect:/admin/manageAircraft/";
    }
    
    public void addAttributeToForm(Model model) {
        model.addAttribute("listAircraft", aircraftDb.findAll());
        model.addAttribute("listFlightRoute", flightRouteDb.findAllByOrderByDepartureAsc());
        model.addAttribute("listPromotion", promotionDb.findAll());
    }
}
