/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.service;

import iviettech.project.flightbooking.repository.IBooking;
import iviettech.project.flightbooking.repository.ITicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component("ScheduleManager")
public class ScheduleManager {

    @Autowired
    private ITicket ticketDb;
    @Autowired
    private IBooking bookingDb;
    
    public void changeTicketStatusWhenTimeOut() {
        ticketDb.updateTicketsWhenHoldingTimeOut();
    }
    
    public void changeBookingAndTicketStatusAfterExpire() {
        bookingDb.updateBookingStatusWhenExpire();
        ticketDb.updateTicketStatusWhenExpire();
    }
    

}
