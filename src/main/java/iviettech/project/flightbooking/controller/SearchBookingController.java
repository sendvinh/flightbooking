/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller;

import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.repository.IBooking;
import iviettech.project.flightbooking.service.BusinessService;
import iviettech.project.flightbooking.service.UtilitiesService;
import iviettech.project.flightbooking.helper.Validator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Administrator
 */
@Controller("SearchBookingController")
public class SearchBookingController {
    @Autowired
    private IBooking bookingDb;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private UtilitiesService utilService;
    
    @GetMapping("searchBooking")
    public String searchBooking(Model model, RedirectAttributes atb,
            @RequestParam(value = "bookingCode", required = false) String bookingCode,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone) {
        if(bookingCode==null && email==null && phone==null) {
            return "Booking/Search/searchBooking";
        } else {
            if(!searchBookingDataIsValid(atb, bookingCode, email, phone)){
                return "redirect:/searchBooking";
            }
            Booking booking = bookingDb.getBooking(bookingCode, email, phone);
            if (booking == null) {
                model.addAttribute("errorMsg", "Invalid booking information!");
            } else {
                model.addAttribute("utilService", utilService);
                model.addAttribute("booking", booking);
            }
            return "Booking/Search/searchBooking";
        }
    }

    @PostMapping("searchBooking/pay")
    public String payAfterSearch (Model model, HttpServletRequest request, RedirectAttributes atb,
            @RequestParam(name = "bookingId", required = false) long bookingId,
            @RequestParam(name = "paymentMethod", required = false) String paymentMethod){
        Booking booking = bookingDb.findOne(bookingId);
        businessService.updatePayment(booking, paymentMethod);
        if (paymentMethod.equalsIgnoreCase("PAY_ONLINE")) {
            request.getSession().setAttribute("booking", booking);
            return "redirect:/paymentGateway/";
        } 
        if (paymentMethod.equalsIgnoreCase("PAY_LATER")) {
            atb.addFlashAttribute("booking", booking);
            request.getSession().invalidate();
            return "redirect:/searchBooking";
        }
        return "redirect:/";
    }
    
    public boolean searchBookingDataIsValid (RedirectAttributes atb,
            @RequestParam(value = "bookingCode", required = false) String bookingCode,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone) {

        boolean t = true;
        if(!Validator.isRequired(bookingCode)) {
            atb.addFlashAttribute("bookingCodeError", "Booking code is required");
            t = false;
        } 
        if(!Validator.isRequired(email)) {
            atb.addFlashAttribute("emailError", "Email is required");
            t = false;
        } else {
            if(!Validator.isValidEmail(email)) {
                atb.addFlashAttribute("emailError", "Wrong email format");
                t = false;
            }
        }
        if(!Validator.isRequired(phone)) {
            atb.addFlashAttribute("phoneError", "Phone is required");
            t = false;
        } else {
            if(!Validator.isValidMobilePhone(phone)) {
                atb.addFlashAttribute("phoneError", "Phone number have 10 digits");
                t = false;
            }
        }
        return t;
    }
        

}
