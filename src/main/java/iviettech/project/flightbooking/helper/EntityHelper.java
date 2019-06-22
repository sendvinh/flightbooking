/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.helper;

import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.entity.Customer;
import iviettech.project.flightbooking.entity.Flight;
import iviettech.project.flightbooking.entity.ModelWrapper;
import iviettech.project.flightbooking.entity.Promotion;
import iviettech.project.flightbooking.entity.Ticket;
import iviettech.project.flightbooking.enumeration.BookingState;
import iviettech.project.flightbooking.enumeration.PaymentMethod;
import iviettech.project.flightbooking.enumeration.PaymentState;
import iviettech.project.flightbooking.repository.IPromotion;
import iviettech.project.flightbooking.service.UtilitiesService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EntityHelper {    
    public static void setModelToSessionTickets(List<Ticket> listModelTickets, List<Ticket> listSessionTickets){
        for (int i = 0; i < listSessionTickets.size(); i++) {
            Ticket modelTicket = listModelTickets.get(i);
            Ticket sessionTicket = listSessionTickets.get(i);
            sessionTicket.setPassengerType(modelTicket.getPassengerType());
            sessionTicket.setFirstName(modelTicket.getFirstName().trim().replaceAll(" +", " "));
            sessionTicket.setLastName(modelTicket.getLastName().trim().replaceAll(" +", " "));
            sessionTicket.setDateOfBirth(modelTicket.getDateOfBirth());
            sessionTicket.setGender(modelTicket.getGender());
            sessionTicket.setAttachedInfant(modelTicket.isAttachedInfant());
            sessionTicket.setInfantName(modelTicket.getInfantName().trim().replaceAll(" +", " "));
        }
    }
    
    
    public static void removeTicketFromList (long ticketId, List<Ticket> listTicket){
        for (int i = 0; i < listTicket.size(); i++) {
            Ticket ticket = listTicket.get(i);
            if(ticket.getTicketId() == ticketId){
                listTicket.remove(i);
            }
        }
    } 
    
    public static void setFlightForTickets(Flight flight,List<Ticket> listTicket){
        for(int i = 0; i < listTicket.size(); i++) {
            Ticket ticket = listTicket.get(i);
            ticket.setFlight(flight);
        }
    }  

    public static Booking createBooking(Customer customer, List<Ticket> listBookedTicket, String paymentMethod) {
        Booking booking = new Booking();
//        calculate expire time
        Date bookingTime = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(bookingTime);
        calender.add(Calendar.HOUR_OF_DAY, 24);
        Date twentyFourHourAfterBook = calender.getTime();
        Date departTime = listBookedTicket.get(0).getFlight().getDepartTime();
        Date expireTime;
        if(twentyFourHourAfterBook.compareTo(departTime) < 0) {
            expireTime = twentyFourHourAfterBook;
        } else {
            calender.setTime(departTime);
            calender.add(Calendar.HOUR_OF_DAY, -6);
            expireTime = calender.getTime();
        }
        booking.setBookingTime(bookingTime);
        booking.setExpireTime(expireTime);
        booking.setState(BookingState.IN_PROGRESS);
        booking.setBookingCode(UtilitiesService.createBookingCode());
        booking.setCustomer(customer);
        booking.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
        booking.setPaymentStatus(PaymentState.WAIT_FOR_PAY);
        if(PaymentMethod.PAY_LATER.getName().equals(paymentMethod)) {
            booking.setPaymentCode(UtilitiesService.createPaymentCode());
        }
        booking.setListTicket(listBookedTicket);
        booking.calculateCost();
        return booking;
    }
    
    public static double sumOfPriceOfTicket(double tempCost, List<Ticket> listTicket) {
        for (int i = 0; i < listTicket.size(); i++) {
            Ticket ticket = listTicket.get(i);
            tempCost += ticket.calculateTicketPrice();
        }
        return tempCost;
    }
    
    public static ArrayList<Promotion> createListPromotionFromListPrmotionId(IPromotion prmotionDb, String[] listPromotionId) {
        List<Long> listPromotionIdLong = new ArrayList<>();
        for (int i = 0; i < listPromotionId.length; i++) {
            Long promotionLongId = Long.parseLong(listPromotionId[i]);
            listPromotionIdLong.add(promotionLongId);
        }
        ArrayList<Promotion> listPromotion = new ArrayList<>();
        for (int i = 0; i < listPromotionId.length; i++) {
            Promotion p = prmotionDb.findOne(listPromotionIdLong.get(i));
            listPromotion.add(p);
        }
        return listPromotion;
    }
}
