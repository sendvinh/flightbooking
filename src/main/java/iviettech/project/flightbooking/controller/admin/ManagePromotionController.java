/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller.admin;

import iviettech.project.flightbooking.entity.Promotion;
import iviettech.project.flightbooking.repository.IPromotion;
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
@RequestMapping(value = "/admin/managePromotion")
public class ManagePromotionController {
    @Autowired
    private IPromotion promotionDb;

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String goToManagePromotionPage(Model model) {  
        return "Management/Promotion/managePromotion";
    }
    
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String searchPromotion(Model model, @RequestParam(value = "description") String description) {  
        List<Promotion> listPromotion = (List<Promotion>) promotionDb.findByDescriptionContaining(description);
        model.addAttribute("listPromotion", listPromotion);
        return "Management/Promotion/managePromotion";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String addPromotion(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "Management/Promotion/promotionForm";
    }
    
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addPromotion(@ModelAttribute(value = "promotion") Promotion promotion) {
        promotionDb.save(promotion);
        return "redirect:/admin/managePromotion/";
    }
    
    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
    public String updatePromotion(Model model, @PathVariable long id) {
        model.addAttribute("promotion", promotionDb.findOne(id));
        return "Management/Promotion/promotionForm";
    }
    
    @RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.POST)
    public String updatePromotion(@ModelAttribute(value = "promotion") Promotion promotion) {
        promotionDb.save(promotion);
        return "redirect:/admin/managePromotion/";
    }
    
    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public String deletePromotion(Model model, @PathVariable long id) {
        promotionDb.delete(id);
        return "redirect:/admin/managePromotion/";
    }
}
