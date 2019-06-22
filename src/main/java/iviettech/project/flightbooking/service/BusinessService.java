/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.service;

import iviettech.project.flightbooking.entity.BankAccount;
import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.entity.Flight;
import iviettech.project.flightbooking.entity.PassengerType;
import iviettech.project.flightbooking.entity.Promotion;
import iviettech.project.flightbooking.entity.Ticket;
import iviettech.project.flightbooking.enumeration.BookingState;
import iviettech.project.flightbooking.enumeration.PaymentMethod;
import iviettech.project.flightbooking.enumeration.PaymentState;
import iviettech.project.flightbooking.enumeration.SeatState;
import iviettech.project.flightbooking.repository.IBankAccount;
import iviettech.project.flightbooking.repository.IBooking;
import iviettech.project.flightbooking.repository.ICustomer;
import iviettech.project.flightbooking.repository.IFlight;
import iviettech.project.flightbooking.repository.IPassengerType;
import iviettech.project.flightbooking.repository.IPromotion;
import iviettech.project.flightbooking.repository.ITicket;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
public class BusinessService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private IFlight flightDb;
    @Autowired
    private IPromotion promotionDb;
    @Autowired
    private ICustomer customerDb;
    @Autowired
    private IPassengerType passengerTypeDb;
    @Autowired
    private ITicket ticketDb;
    @Autowired
    private IBooking bookingDb;
    @Autowired
    private IBankAccount bankAccountDb;
       
    public boolean isTicketsStillInState(List<Ticket> listTicket, SeatState seatState) {
        boolean t = true;
        for (int i = 0; i < listTicket.size(); i++) {
            long ticketId = listTicket.get(i).getTicketId();
            if (ticketDb.isTicketStillInState(ticketId, seatState) == false) {
                t = false;
                break;
            }
        }
        return t;
    }
    
    public boolean updateTicketAfterChoose(long ticketId) {
        return ticketDb.updateTicketAfterChooseSeat(ticketId) == 1;
    }
    
       
    public boolean updateTicketAfterRelease(long ticketId) {
        return ticketDb.updateTicketAfterReleaseSeat(ticketId) == 1;
    }
      
    
    public void fetchPassengerTypeForTickets(List<Ticket> listTicket){
        for (int i = 0; i < listTicket.size(); i++) {
            Ticket ticket = listTicket.get(i);
            PassengerType pt = passengerTypeDb.findOne(ticket.getPassengerType().getId());
            ticket.setPassengerType(pt);
        }

    }
    
    public void getHoldingTimeAndRemoveFromCartIfTimeOut (List<Ticket> listTicket){
        for (int i = 0; i < listTicket.size(); i++) {
            BigInteger timeOut = ticketDb.getTimeOutInSeconds(listTicket.get(i).getTicketId());
            if(timeOut == null) {
                listTicket.remove(i);
                i--;
            } else {
                listTicket.get(i).setTimeOutSecond(timeOut.longValue()+ 4);
            }           
        }
    }
    
    public boolean getHoldingTimeAndChangeStatusInCartIfTimeOut (List<Ticket> listTicket){
        boolean t = false;
        for (int i = 0; i < listTicket.size(); i++) {
            BigInteger timeOut = ticketDb.getTimeOutInSeconds(listTicket.get(i).getTicketId());
            if(timeOut == null) {
                listTicket.get(i).setStatus(SeatState.AVAILABLE);
                listTicket.get(i).setTimeOutSecond(0);
                t = true;
            } else {
                listTicket.get(i).setTimeOutSecond(timeOut.longValue()+ 4);
            }           
        }
        return t;
    }
    
    public void fetchFlightForTicket(Ticket ticket) {
            Flight flight = flightDb.getFlightOfTicket(ticket.getTicketId());
            List<Promotion> listPromotion = promotionDb.getPromotionOfFlight(flight.getFlightId());
            flight.setListPromotion(listPromotion);
            ticket.setFlight(flight);
    }
    
    public void fetchFlightForTickets(List<Ticket> listTicket) {
        for (int i = 0; i < listTicket.size(); i++) {
            Ticket ticket = listTicket.get(i);
            Flight flight = flightDb.getFlightOfTicket(ticket.getTicketId());
            List<Promotion> listPromotion = promotionDb.getPromotionOfFlight(flight.getFlightId());
            flight.setListPromotion(listPromotion);
            ticket.setFlight(flight);
        }
    }
    
    
//    public boolean changeTicketStatusAfterChooseSeat(List<Ticket> listTicket) {
//        if (isTicketsStillInState(listTicket, SeatState.AVAILABLE)) {
//            changeTicketsStatus(listTicket, SeatState.AVAILABLE, SeatState.TEMPORARY);
//            setExpireTimeForTemporaryTicket(listTicket);
//            return true;
//        } else {
//            //another session chose tickets
//            return false;
//        }
//    }
//    
//    public boolean changeTicketStatusWhenChooseSeatAgain(List<Ticket> listTicket) {
//        if (isTicketsStillInState(listTicket, SeatState.TEMPORARY)) {
//            changeTicketsStatus(listTicket, SeatState.TEMPORARY, SeatState.AVAILABLE);
//            clearExpireTimeForTicket(listTicket);
//            return true;
//        } else {
//            //session is timeout and seat is auto change to available
//            return false;
//        }
//        
//    }
//    
    public void checkStatusAndRemoveTimeOutTicketFromCart(List<Ticket> listTicket) {
        for (int i = 0; i < listTicket.size(); i++) {
            Ticket ticket = listTicket.get(i);
            if(!ticketDb.isTicketStillInState(ticket.getTicketId(), SeatState.TEMPORARY)) {
                listTicket.remove(i);
            }
        }
    }
    
    public void updatePayment(Booking booking, String paymentMethod) {
        if(PaymentMethod.PAY_LATER.getName().equals(paymentMethod)) {
            String paymentCode = UtilitiesService.createPaymentCode();
            bookingDb.updatePayment(booking.getBookingId(), paymentMethod, paymentCode);
            booking.setPaymentCode(paymentCode);
        } 
        if(PaymentMethod.PAY_ONLINE.getName().equals(paymentMethod)) {
            bookingDb.updatePayment(booking.getBookingId(), paymentMethod, null);
            booking.setPaymentCode(null);
        }
        booking.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
    }

    public void saveTickets(Booking booking) {
        for (int i = 0; i < booking.getListTicket().size(); i++) {
            Ticket ticket = booking.getListTicket().get(i);
            ticket.setBooking(booking);
            ticket.setStatus(SeatState.BOOKED);
            ticket.setExpireTime(null);
            ticketDb.save(ticket);
        }
    }


    @Transactional
    public boolean finishBooking(Booking booking) {
        if(isTicketsStillInState(booking.getListTicket(),SeatState.TEMPORARY)){
            customerDb.save(booking.getCustomer());
            booking = bookingDb.save(booking);
//            bookingDb.setBookingTimeAndExpireTime(booking.getBookingId());
            saveTickets(booking);
            return true;
        } else {
            //session is timeout and seat is auto change to available
            return false;
        }
    }

    @Transactional
    public boolean finishPayment(Booking booking, BankAccount bankAccount) {
        if(bookingDb.isPaymentStillInState(booking.getBookingId(), PaymentState.WAIT_FOR_PAY)) {
            bankAccount.setBalance(bankAccount.getBalance() - booking.getTotalCost());
            bankAccountDb.save(bankAccount);
            booking.setState(BookingState.SUCCESS);
            booking.setPaymentStatus(PaymentState.PAID);
            bookingDb.save(booking);
            return true;
        } else {
            //booking is already paid
            return false;
        }
        
    }
    
//    @Transactional
//    public void pay( paymentInfo, BankAccount bankAccount) {
//        bankAccount.setBalance(bankAccount.getBalance() - paymentInfo.getAmount());
//        bankAccountDb.save(bankAccount);
//
//    }
}
