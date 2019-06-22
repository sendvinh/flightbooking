/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller;

import iviettech.project.flightbooking.entity.Station;
import iviettech.project.flightbooking.repository.IStation;
import iviettech.project.flightbooking.service.EmailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */
@Controller
public class HomeController {
    @Autowired
    private IStation stationDb;

    @RequestMapping(value = {"/","/home" }, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        return "Home/_home";
    }
}
