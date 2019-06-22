/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.controller;

import iviettech.project.flightbooking.entity.BankAccount;
import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.repository.IBankAccount;
import iviettech.project.flightbooking.service.BusinessService;
import iviettech.project.flightbooking.service.EmailService;
import iviettech.project.flightbooking.service.UtilitiesService;
import iviettech.project.flightbooking.helper.Validator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/paymentGateway")
public class PaymentController {
    @Autowired
    private IBankAccount bankAccountDb;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UtilitiesService utilService;
    
    @GetMapping("/")
    public String payment(Model model, HttpServletRequest request) {
        Booking booking = (Booking) request.getSession().getAttribute("booking");
        if(booking!=null) {
            model.addAttribute("listBank", UtilitiesService.createListBank());
            model.addAttribute("listMonth", UtilitiesService.createListMonth());
            model.addAttribute("listYear", UtilitiesService.createListYear());
            model.addAttribute("utilService", utilService);
            model.addAttribute("outOfSession", false);
        } else {
            model.addAttribute("outOfSession", true);
        }
        return "Payment/_payment";
    }
    
    @PostMapping("/pay")
    public String pay(HttpServletRequest request,
            @SessionAttribute(value = "booking", required = false) Booking booking,
            RedirectAttributes atb) {
        if(!bankAccountDataIsValid(request,atb)) {
            return "redirect:/paymentGateway/";
        }
        String bankCode = request.getParameter("bankCode");
        String accountNo = request.getParameter("accountNo");
        String owner = request.getParameter("owner");
        int expMonth = Integer.parseInt(request.getParameter("expMonth"));
        int expYear = Integer.parseInt(request.getParameter("expYear"));
        BankAccount bankAccount = bankAccountDb.findByBankCodeAndAccountNoAndOwnerAndExpireMonthAndExpireYear
                                                (bankCode, accountNo, owner, expMonth, expYear);
        //payment flow
        if (bankAccount == null) {
            atb.addFlashAttribute("errorMessage", "Do not exist this account!");
            return "redirect:/paymentGateway/";
        } else {
            if (bankAccount.getBalance() >= booking.getTotalCost()) {
                atb.addFlashAttribute("booking", booking);
                if(businessService.finishPayment(booking, bankAccount)) {
                    request.getSession().invalidate();
                    emailService.sendSuccessBookingEmail(booking);
                    return "redirect:/booking/bookingSuccess";
                } else {
                    request.getSession().invalidate();
                    atb.addFlashAttribute("errorMessage", "Your booking is already paid!");
                    return "redirect:/paymentGateway/";
                }
            } else {
                atb.addFlashAttribute("errorMessage", "Account's balance is not enough!");
                return "redirect:/paymentGateway/";
            }
        }
    }
    
    
    public boolean bankAccountDataIsValid (HttpServletRequest request, RedirectAttributes atb) {
        String accountNo = request.getParameter("accountNo");
        String owner = request.getParameter("owner");
        boolean t = true;
        if(!Validator.isRequired(accountNo)) {
            atb.addFlashAttribute("accountNoError", "Account number is required");
            t = false;
        } else {
            if(!Validator.isValidAccountNo(accountNo)) {
                atb.addFlashAttribute("accountNoError", "Account number have 16 digits");
                t = false;
            }
        }
        if(!Validator.isRequired(owner)) {
            atb.addFlashAttribute("ownerError", "Owner is required");
            t = false;
        }
        return t;
    }
}
