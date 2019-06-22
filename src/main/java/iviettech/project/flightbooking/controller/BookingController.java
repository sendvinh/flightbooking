package iviettech.project.flightbooking.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.entity.Customer;
import iviettech.project.flightbooking.entity.Flight;
import iviettech.project.flightbooking.entity.ModelWrapper;
import iviettech.project.flightbooking.entity.Station;
import iviettech.project.flightbooking.entity.Ticket;
import iviettech.project.flightbooking.enumeration.PaymentMethod;
import iviettech.project.flightbooking.enumeration.SeatState;
import iviettech.project.flightbooking.helper.EntityHelper;
import iviettech.project.flightbooking.repository.IFlight;
import iviettech.project.flightbooking.repository.IPassengerType;
import iviettech.project.flightbooking.repository.IStation;
import iviettech.project.flightbooking.repository.ITicket;
import iviettech.project.flightbooking.service.BusinessService;
import iviettech.project.flightbooking.service.EmailService;
import iviettech.project.flightbooking.service.UtilitiesService;
import iviettech.project.flightbooking.helper.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("BookingController")
@RequestMapping(value = "/booking")
public class BookingController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IFlight flightDb;
    @Autowired
    private IStation stationDb;
    @Autowired
    private ITicket ticketDb;
    @Autowired
    private UtilitiesService utilService;
    @Autowired
    private IPassengerType passengerTypeDb;

    @RequestMapping(value = {"/searchFlight"}, method = RequestMethod.GET)
    public String showSearchForm(Model model) {
        List<Station> listStation = (List<Station>) stationDb.findAll();
        model.addAttribute("listStation", listStation);
        return "Booking/searchFlight";
    }

    @RequestMapping(value = {"/seatOption"}, method = RequestMethod.POST)
    public String showSeatOptionPage(HttpServletRequest request, Model model, RedirectAttributes atb) {
        if (!isDateValid(request, model, atb)) {
            return "redirect:/";
        }
        removeTicketInCartWhenClickDeleteButton(request, model);
        chooseSeatFunction(request, model);
        getHoldingTimeAndRemoveFromCartIfTimeOut(request, model);
        showFlightsAndSeats(request, model);
        return "Booking/Seat/_seat";
    }
    
    @RequestMapping(value = {"/seatOption"}, method = RequestMethod.GET)
    public String showSeatOptionPageGetMethod(HttpServletRequest request, Model model, RedirectAttributes atb) {
        if(request.getSession().getAttribute("tripType")==null){
            return "Booking/Result/outOfSession";
        } else {
            getHoldingTimeAndRemoveFromCartIfTimeOut(request, model);
            showFlightsAndSeats(request, model);
            return "Booking/Seat/_seat";
        }
    }

    public boolean isDateValid(HttpServletRequest request, Model model, RedirectAttributes atb) {
        boolean t = true;
        String tripType = request.getParameter("tripType");
        if (tripType != null) {
            String departDateParam = request.getParameter("departDate");
            if (!Validator.isRequired(departDateParam)) {
                atb.addFlashAttribute("departDateError", "Depart date is required");
                t = false;
            } else {
                if (!Validator.isValidDateFormat(departDateParam)) {
                    atb.addFlashAttribute("departDateError", "Must be dd/mm/yyyy format");
                    t = false;
                }
            }
            if (tripType.equalsIgnoreCase("roundTrip")) {
                String returnDateParam = request.getParameter("returnDate");
                if (!Validator.isRequired(returnDateParam)) {
                    atb.addFlashAttribute("returnDateError", "Return date is equired");
                    t = false;
                } else {
                    if (!Validator.isValidDateFormat(returnDateParam)) {
                        atb.addFlashAttribute("returnDateError", "Must be dd/mm/yyyy format");
                        t = false;
                    } else {
                        if (!Validator.isReturnDateAfterDepartDate(departDateParam, returnDateParam)) {
                            atb.addFlashAttribute("returnDateError", "Return date must be after depart date");
                            t = false;
                        }
                    }
                }
            }
        }
        return t;
    }

    public void showFlightsAndSeats(HttpServletRequest request, Model model) {
        ///////////////////////////////////////////////////////////////////////////
        //CODE BLOCK BELOW IS GET SEARCHED FLIGHTS DATA AND ADD TO MODEL, SESSION//
        ///////////////////////////////////////////////////////////////////////////
        String tripType;
        List<Flight> listDepartFlight;
        List<Flight> listReturnFlight;
        //click next button at search flight page
        if (request.getParameter("tripType") != null) {
            tripType = request.getParameter("tripType");
            String departure = request.getParameter("departure");
            String arrival = request.getParameter("arrival");
            String departDate = UtilitiesService.changeFormatStringDate(request.getParameter("departDate"));
            listDepartFlight = (List<Flight>) flightDb.searchFlight(departDate, departure, arrival);
            if (tripType.equalsIgnoreCase("roundTrip")) {
                String returnDate = UtilitiesService.changeFormatStringDate(request.getParameter("returnDate"));
                listReturnFlight = (List<Flight>) flightDb.searchFlight(returnDate, arrival, departure);
            } else {
                listReturnFlight = null;
            }
        } else {
            //click to choose flight, ticket or click back button at passenger page
            tripType = (String) request.getSession().getAttribute("tripType");
            listDepartFlight = (List<Flight>) request.getSession().getAttribute("listDepartFlight");
            listReturnFlight = (List<Flight>) request.getSession().getAttribute("listReturnFlight");
        }
        //add data to viewer and session
        model.addAttribute("listDepartFlight", listDepartFlight);
//            request.getSession().setAttribute("tripType", tripType);
        request.getSession().setAttribute("listDepartFlight", listDepartFlight);
        if (tripType.equalsIgnoreCase("roundTrip")) {
            model.addAttribute("listReturnFlight", listReturnFlight);
            request.getSession().setAttribute("listReturnFlight", listReturnFlight);
        }

        ///////////////////////////////////////////////////////
        //CODE BLOCK BELOW IS GET SEATS DATA AND ADD TO MODEL//
        ///////////////////////////////////////////////////////
        long departFlightId;
        long returnFlightId;
        //list seat only shows when existing searched flight
        if (listDepartFlight.size() > 0) {
            //load page without choosing flight
            if (request.getParameter("departFlight") == null) {
                //first time load page when click next from search flight page
                if (request.getParameter("tripType") != null) {
                    departFlightId = listDepartFlight.get(0).getFlightId();
                } else {
                    departFlightId = (Long) request.getSession().getAttribute("departFlightId");
                }
            } else {//load page when choosing flight
                String departFlightIdStr = (String) request.getParameter("departFlight");
                departFlightId = Long.parseLong(departFlightIdStr);
            }
            model.addAttribute("listDepartTicket", ticketDb.getAllTicketOfFlight(departFlightId));
            model.addAttribute("ticketDb", ticketDb);
            request.getSession().setAttribute("departFlightId", departFlightId);
            request.getSession().setAttribute("departFlight", flightDb.findOne(departFlightId));
        }
        if (tripType.equalsIgnoreCase("roundTrip")) {
            if (listReturnFlight.size() > 0) {
                if (request.getParameter("returnFlight") == null) {
                    if (request.getParameter("tripType") != null) {
                        returnFlightId = listReturnFlight.get(0).getFlightId();
                    } else {
                        returnFlightId = (Long) request.getSession().getAttribute("returnFlightId");
                    }
                } else {
                    String returnFlightIdStr = (String) request.getParameter("returnFlight");
                    returnFlightId = Long.parseLong(returnFlightIdStr);
                }
                model.addAttribute("listReturnTicket", ticketDb.getAllTicketOfFlight(returnFlightId));
                request.getSession().setAttribute("returnFlightId", returnFlightId);
                request.getSession().setAttribute("returnFlight", flightDb.findOne(returnFlightId));
            }
        }
    }

    public void chooseSeatFunction(HttpServletRequest request, Model model) {
        String tripType;
        if (request.getParameter("tripType") != null) {
            tripType = request.getParameter("tripType");
            //clear all chosen tickets in session if any when searching flight
            request.getSession().invalidate();
        } else {
            tripType = (String) request.getSession().getAttribute("tripType");
        }
        request.getSession().setAttribute("tripType", tripType);

        String departNotif = "";
        String availableDepartSeat = request.getParameter("availableDepartSeat");
        String temporaryDepartSeat = request.getParameter("temporaryDepartSeat");
        List<Ticket> listChooseDepartTicket = (List<Ticket>) request.getSession().getAttribute("listChooseDepartTicket");
        if (listChooseDepartTicket == null) {
            listChooseDepartTicket = new ArrayList<>();
        }
        //click on temporary chosen seat to make seat available again
        if (temporaryDepartSeat != null) {
            long temporarySeatId = Long.parseLong(temporaryDepartSeat);
            //change seat status then remove from list in session
            if (businessService.updateTicketAfterRelease(temporarySeatId)) {
                EntityHelper.removeTicketFromList(temporarySeatId, listChooseDepartTicket);
            }
        }
        //click on available seat to choose seat, maximum is 5 seats
        if (availableDepartSeat != null) {
            if (listChooseDepartTicket.size() < 5) {
                long departSeatId = Long.parseLong(availableDepartSeat);
                if (businessService.updateTicketAfterChoose(departSeatId)) {
                    Ticket chooseTicket = ticketDb.findOne(departSeatId);
                    businessService.fetchFlightForTicket(chooseTicket);
                    listChooseDepartTicket.add(chooseTicket);
                }
                departNotif = "";
            } else {
                departNotif = "Your can only book for maximum 5 tickets for each way";
            }
        }
        model.addAttribute("departNotif", departNotif);
        request.getSession().setAttribute("listChooseDepartTicket", listChooseDepartTicket);
        //similar to code above, this code is for return seats
        if (tripType.equalsIgnoreCase("roundTrip")) {
            String returnNotif = "";
            String availableReturnSeat = request.getParameter("availableReturnSeat");
            String temporaryReturnSeat = request.getParameter("temporaryReturnSeat");
            List<Ticket> listChooseReturnTicket = (List<Ticket>) request.getSession().getAttribute("listChooseReturnTicket");
            if (listChooseReturnTicket == null) {
                listChooseReturnTicket = new ArrayList<>();
            }
            if (temporaryReturnSeat != null) {
                long temporarySeatId = Long.parseLong(temporaryReturnSeat);
                //change seat status then add to list in session
                if (businessService.updateTicketAfterRelease(temporarySeatId)) {
                    EntityHelper.removeTicketFromList(temporarySeatId, listChooseReturnTicket);
                }
            }
            if (availableReturnSeat != null) {
                if (listChooseReturnTicket.size() < 5) {
                    long returnSeatId = Long.parseLong(availableReturnSeat);
                    if (businessService.updateTicketAfterChoose(returnSeatId)) {
                        Ticket chooseTicket = ticketDb.findOne(returnSeatId);
                        businessService.fetchFlightForTicket(chooseTicket);
                        listChooseReturnTicket.add(chooseTicket);
                    }
                    returnNotif = "";
                } else {
                    returnNotif = "Your can only book for maximum 5 tickets for each way";
                }
            }
            model.addAttribute("returnNotif", returnNotif);
            request.getSession().setAttribute("listChooseReturnTicket", listChooseReturnTicket);
        }
    }

    @RequestMapping(value = {"/passengerInfo"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String showPassengerForm(Model model, HttpServletRequest request,
            @SessionAttribute(value = "tripType", required = false) String tripType,
            @SessionAttribute(value = "listChooseDepartTicket", required = false) List<Ticket> listChooseDepartTicket,
            @SessionAttribute(value = "listChooseReturnTicket", required = false) List<Ticket> listChooseReturnTicket) {
        if(request.getSession().getAttribute("tripType")==null){
            return "Booking/Result/outOfSession";
        }
        removeTicketInCartWhenClickDeleteButton(request, model);
        getHoldingTimeAndChangeStatusInCartIfTimeOut(request, model, tripType, listChooseDepartTicket, listChooseReturnTicket);
        model.addAttribute("listPassengerType", passengerTypeDb.findAll());
        ModelWrapper modelWrapper = new ModelWrapper();
        if (!listChooseDepartTicket.isEmpty()) {
            modelWrapper.setListDepartTicket(listChooseDepartTicket);
        }
        if (tripType.equalsIgnoreCase("roundTrip")) {
            if (!listChooseReturnTicket.isEmpty()) {
                modelWrapper.setListReturnTicket(listChooseReturnTicket);
            }
        }
        modelWrapper.addCustomer();
        model.addAttribute("modelWrapper", modelWrapper);
        return "Booking/Passenger/_passenger";
    }

    @RequestMapping(value = {"/confirm"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String confirm(Model model, HttpServletRequest request, RedirectAttributes atb,
            @ModelAttribute("modelWrapper") ModelWrapper modelWrapper,
            @SessionAttribute(value = "tripType", required = false) String tripType,
            @SessionAttribute(value = "listChooseDepartTicket", required = false) List<Ticket> listChooseDepartTicket,
            @SessionAttribute(value = "listChooseReturnTicket", required = false) List<Ticket> listChooseReturnTicket) {
        if(request.getSession().getAttribute("tripType")==null){
            return "Booking/Result/outOfSession";
        }
        if (!isModelWrapperValid(model, request, modelWrapper, tripType, atb)) {
            return "redirect:/booking/passengerInfo";
        }
        removeTicketInCartWhenClickDeleteButton(request, model);
        getHoldingTimeAndChangeStatusInCartIfTimeOut(request, model, tripType, listChooseDepartTicket, listChooseReturnTicket);
        double bookingCost = 0;
        if (!listChooseDepartTicket.isEmpty()) {
            if(modelWrapper.getListDepartTicket()!=null) {
                EntityHelper.setModelToSessionTickets(modelWrapper.getListDepartTicket(), listChooseDepartTicket);
                businessService.fetchPassengerTypeForTickets(listChooseDepartTicket);
            }    
            bookingCost = EntityHelper.sumOfPriceOfTicket(bookingCost, listChooseDepartTicket);
        }
        if (tripType.equalsIgnoreCase("roundTrip")) {
            if (!listChooseReturnTicket.isEmpty() && modelWrapper.getListReturnTicket()!=null) {
                if(modelWrapper.getListReturnTicket()!=null) {
                    EntityHelper.setModelToSessionTickets(modelWrapper.getListReturnTicket(), listChooseReturnTicket);
                    businessService.fetchPassengerTypeForTickets(listChooseReturnTicket);
                } 
                bookingCost = EntityHelper.sumOfPriceOfTicket(bookingCost, listChooseReturnTicket);
            }
        }
        model.addAttribute("utilService", utilService);
        model.addAttribute("bookingCost", bookingCost);
        if (modelWrapper.getCustomer() != null) {
            request.getSession().setAttribute("customer", modelWrapper.getCustomer());
        }
        return "Booking/Confirm/_confirm";
    }

    public boolean isModelWrapperValid(Model model, HttpServletRequest request,
            @ModelAttribute("modelWrapper") ModelWrapper modelWrapper,
            @SessionAttribute(value = "tripType", required = false) String tripType,
            RedirectAttributes atb) {
        boolean t = true;
        if (null != modelWrapper) {
            if (null != modelWrapper.getListDepartTicket()
                    && !modelWrapper.getListDepartTicket().isEmpty()) {
                for (int i = 0; i < modelWrapper.getListDepartTicket().size(); i++) {
                    Ticket modelTicket = modelWrapper.getListDepartTicket().get(i);
                    if (!Validator.isRequired(modelTicket.getFirstName())) {
                        atb.addFlashAttribute("departFirstNameError" + i, "First name is required");
                        t = false;
                    }
                    if (!Validator.isRequired(modelTicket.getLastName())) {
                        atb.addFlashAttribute("departLastNameError" + i, "Last name is required");
                        t = false;
                    }
                    if (modelTicket.getDateOfBirth() == null) {
                        atb.addFlashAttribute("departDOBError" + i, "Wrong date format");
                        t = false;
                    }
                    if (modelTicket.isAttachedInfant()) {
                        if (!Validator.isRequired(modelTicket.getInfantName())) {
                            atb.addFlashAttribute("departInfantError" + i, "Infant name is required");
                            t = false;
                        }
                    }
                }
            }
            if (tripType.equalsIgnoreCase("roundTrip")) {
                if (null != modelWrapper.getListReturnTicket()
                        && !modelWrapper.getListReturnTicket().isEmpty()) {
                    for (int i = 0; i < modelWrapper.getListReturnTicket().size(); i++) {
                        Ticket modelTicket = modelWrapper.getListReturnTicket().get(i);
                        if (!Validator.isRequired(modelTicket.getFirstName())) {
                            atb.addFlashAttribute("returnFirstNameError" + i, "First name is required");
                            t = false;
                        }
                        if (!Validator.isRequired(modelTicket.getLastName())) {
                            atb.addFlashAttribute("returnLastNameError" + i, "Last name is required");
                            t = false;
                        }
                        if (modelTicket.getDateOfBirth() == null) {
                            atb.addFlashAttribute("returnDOBError" + i, "Wrong date format");
                            t = false;
                        }
                        if (modelTicket.isAttachedInfant()) {
                            if (!Validator.isRequired(modelTicket.getInfantName())) {
                                atb.addFlashAttribute("returnInfantError" + i, "Infant name is required");
                                t = false;
                            }
                        }
                    }
                }
            }
            Customer customer = modelWrapper.getCustomer();
            if (null != customer) {
                if (!Validator.isRequired(customer.getFirstName())) {
                    atb.addFlashAttribute("customerFirstNameError", "First name is required");
                    t = false;
                }
                if (!Validator.isRequired(customer.getLastName())) {
                    atb.addFlashAttribute("customerLastNameError", "Last name is required");
                    t = false;
                }
                if (!Validator.isRequired(customer.getEmail())) {
                    atb.addFlashAttribute("customerEmailError", "Email is required");
                    t = false;
                } else {
                    if (!Validator.isValidEmail(customer.getEmail())) {
                        atb.addFlashAttribute("customerEmailError", "Wrong email format");
                        t = false;
                    }
                }
                if (!Validator.isRequired(customer.getPhone())) {
                    atb.addFlashAttribute("customerPhoneError", "Phone number is required");
                    t = false;
                } else {
                    if (!Validator.isValidMobilePhone(customer.getPhone())) {
                        atb.addFlashAttribute("customerPhoneError", "Wrong phone format");
                        t = false;
                    }
                }
            }

        }
        return t;
    }

    @RequestMapping(value = {"/finishBooking"}, method = RequestMethod.POST)
    public String payment(Model model, HttpServletRequest request, RedirectAttributes atb,
            @SessionAttribute(value = "customer", required = false) Customer customer,
            @SessionAttribute(value = "tripType", required = false) String tripType,
            @SessionAttribute(value = "listChooseDepartTicket", required = false) List<Ticket> listChooseDepartTicket,
            @SessionAttribute(value = "listChooseReturnTicket", required = false) List<Ticket> listChooseReturnTicket) {

        getHoldingTimeAndChangeStatusInCartIfTimeOut(request, model, tripType, listChooseDepartTicket, listChooseReturnTicket);
        List<Ticket> listBookedTicket = new ArrayList<>();
        if (!listChooseDepartTicket.isEmpty()) {
            listBookedTicket.addAll(listChooseDepartTicket);
        }
        if (tripType.equalsIgnoreCase("roundTrip")) {
            if (!listChooseReturnTicket.isEmpty()) {
                listBookedTicket.addAll(listChooseReturnTicket);
            }
        }
        String paymentMethod = request.getParameter("paymentMethod");
        Booking booking = EntityHelper.createBooking(customer, listBookedTicket, paymentMethod);
        if (businessService.finishBooking(booking)) {
            request.getSession().invalidate();
            if (PaymentMethod.PAY_ONLINE.getName().equals(paymentMethod)) {
                request.getSession().setAttribute("booking", booking);
                return "redirect:/paymentGateway/";
            }
            if (PaymentMethod.PAY_LATER.getName().equals(paymentMethod)) {
                atb.addFlashAttribute("booking", booking);
                emailService.sendSuccessBookingEmail(booking);
                return "redirect:/booking/bookingSuccess";
            }
            return "redirect:/";
        } else {
            return "redirect:/booking/bookingError";
        }
    }

    @GetMapping("bookingSuccess")
    public String bookSuccess(Model model, HttpServletRequest request,
        @ModelAttribute("booking")Booking booking) {
        if(booking.getBookingCode() != null) {
            model.addAttribute("utilService", utilService);
            model.addAttribute("booking", booking);
        } 
        return "Booking/Result/success";
    }

    @GetMapping("bookingError")
    public String bookError(Model model, HttpServletRequest request) {
        return "Booking/Result/error";
    }

    public void getHoldingTimeAndRemoveFromCartIfTimeOut(HttpServletRequest request, Model model) {
        String tripType = (String) request.getSession().getAttribute("tripType");
        List<Ticket> listChooseDepartTicket = (List<Ticket>) request.getSession().getAttribute("listChooseDepartTicket");
        if (listChooseDepartTicket != null && !listChooseDepartTicket.isEmpty()) {
            businessService.getHoldingTimeAndRemoveFromCartIfTimeOut(listChooseDepartTicket);
        }
        if (tripType != null && tripType.equalsIgnoreCase("roundTrip")) {
            List<Ticket> listChooseReturnTicket = (List<Ticket>) request.getSession().getAttribute("listChooseReturnTicket");
            if (listChooseReturnTicket != null && !listChooseReturnTicket.isEmpty()) {
                businessService.getHoldingTimeAndRemoveFromCartIfTimeOut(listChooseReturnTicket);
            }
        }
    }

    public boolean getHoldingTimeAndChangeStatusInCartIfTimeOut(HttpServletRequest request, Model model,
            @SessionAttribute(value = "tripType", required = false) String tripType,
            @SessionAttribute(value = "listChooseDepartTicket", required = false) List<Ticket> listChooseDepartTicket,
            @SessionAttribute(value = "listChooseReturnTicket", required = false) List<Ticket> listChooseReturnTicket) {
        boolean departTicketIsTimeOut = false;
        boolean returnTicketIsTimeOut = false;
        if (listChooseDepartTicket != null && !listChooseDepartTicket.isEmpty()) {
            departTicketIsTimeOut = businessService.getHoldingTimeAndChangeStatusInCartIfTimeOut(listChooseDepartTicket);
        }
        if (tripType != null && tripType.equalsIgnoreCase("roundTrip")) {
            if (listChooseReturnTicket != null && !listChooseReturnTicket.isEmpty()) {
                returnTicketIsTimeOut = businessService.getHoldingTimeAndChangeStatusInCartIfTimeOut(listChooseReturnTicket);
            }
        }
        if ((departTicketIsTimeOut || returnTicketIsTimeOut) == true) {
            model.addAttribute("timeOut", true);
        }
        return (departTicketIsTimeOut || returnTicketIsTimeOut);
    }

    public void removeTicketInCartWhenClickDeleteButton(HttpServletRequest request, Model model) {
        String deleteDepartTicket = request.getParameter("deleteDepartTicket");
        String deleteReturnTicket = request.getParameter("deleteReturnTicket");
        if (deleteDepartTicket != null) {
            List<Ticket> listChooseDepartTicket = (List<Ticket>) request.getSession().getAttribute("listChooseDepartTicket");
            long deleteDepartTicketId = Long.parseLong(deleteDepartTicket);
            EntityHelper.removeTicketFromList(deleteDepartTicketId, listChooseDepartTicket);
            businessService.updateTicketAfterRelease(deleteDepartTicketId);
        }
        if (deleteReturnTicket != null) {
            List<Ticket> listChooseReturnTicket = (List<Ticket>) request.getSession().getAttribute("listChooseReturnTicket");
            long deleteReturnTicketId = Long.parseLong(deleteReturnTicket);
            EntityHelper.removeTicketFromList(deleteReturnTicketId, listChooseReturnTicket);
            businessService.updateTicketAfterRelease(deleteReturnTicketId);
        }
    }
}
