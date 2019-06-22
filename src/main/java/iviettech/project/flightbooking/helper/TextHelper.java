package iviettech.project.flightbooking.helper;

import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.enumeration.BookingState;
import iviettech.project.flightbooking.enumeration.PaymentMethod;
import iviettech.project.flightbooking.service.UtilitiesService;

public class TextHelper {
    public static String createBookingInfo(Booking booking) {
        String bookingInfo = "Booking code: " + booking.getBookingCode() + "\n" +
                             "Number of ticket: " + booking.getListTicket().size() + "\n" +
                             "Total cost: " + UtilitiesService.formatVND(booking.getTotalCost()) + "\n" +
                             "Payment method: " + booking.getPaymentMethod()+ "\n" +
                             "Payment status: " + booking.getPaymentStatus()+ "\n";
        if (PaymentMethod.PAY_LATER.equals(booking.getPaymentMethod())) {
            bookingInfo = bookingInfo + 
                             "Payment code: " + booking.getPaymentCode()+ "\n";
        }
        if (PaymentMethod.PAY_LATER.equals(booking.getPaymentMethod()) && 
                BookingState.IN_PROGRESS.equals(booking.getState())) {
            bookingInfo = bookingInfo + 
                             "Payment expire: " + booking.getExpireTime()+ "\n";
        }
        String detailLink = "You can check your booking at link: http://localhost:8084/flightbooking/searchBooking";
        return bookingInfo + detailLink;    
    }
    
    public static String createEmailSubjectForBooking () {
        return "GREEN AIRLINE - BOOKING SUCCESSFUL";
    }

}
