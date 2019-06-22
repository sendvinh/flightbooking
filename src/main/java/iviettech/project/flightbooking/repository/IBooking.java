/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.enumeration.BookingState;
import iviettech.project.flightbooking.enumeration.PaymentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Repository
public interface IBooking extends JpaRepository<Booking,Long> {
    @Query(value = "select * from booking b "
            + "inner join customer c on "
            + "b.customer_id = c.customer_id "
            + "where b.booking_code = ?1 "
            + "and c.email = ?2 and c.phone = ?3", nativeQuery = true)
    Booking getBooking(String bookingCode, String email, String phone);
    
    @Query("select case when COUNT(b) > 0 "
            + "then true else false end from Booking b "
            + "where b.bookingId = ?1 and b.state = ?2")
    boolean isBookingStillInState(long bookingId, BookingState state); 
    
    @Query("select case when COUNT(b) > 0 "
            + "then true else false end from Booking b "
            + "where b.bookingId = ?1 and b.paymentStatus = ?2")
    boolean isPaymentStillInState(long bookingId, PaymentState state); 
    
    @Transactional
    @Modifying
    @Query(value =  "update booking set STATE = 'CANCEL', TOTAL_COST = 0 " 
            + "where PAYMENT_STATUS='WAIT_FOR_PAY' AND EXPIRE_TIME < now()", nativeQuery = true)
    int updateBookingStatusWhenExpire();

    @Transactional
    @Modifying
    @Query(value = "update booking set PAYMENT_METHOD = ?2, PAYMENT_CODE = ?3 where BOOKING_ID = ?1", nativeQuery = true)
    int updatePayment(long bookingId, String paymentMethod, String paymentCode);
    
    @Transactional
    @Modifying
    @Query(value =  "UPDATE booking " +
                    "SET EXPIRE_TIME = " +
                    "IF((SELECT MIN(DEPART_TIME) " +
                    "FROM flight INNER JOIN ticket ON flight.FLIGHT_ID = ticket.FLIGHT_ID " +
                    "WHERE ticket.BOOKING_ID = ?1 ) > DATE_ADD(NOW(), INTERVAL 24 HOUR), " +
                    "DATE_ADD(NOW(), INTERVAL 24 HOUR), " +
                    "DATE_SUB((SELECT MIN(DEPART_TIME) " +
                              "FROM flight INNER JOIN ticket ON flight.FLIGHT_ID = ticket.FLIGHT_ID " +
                              "WHERE ticket.BOOKING_ID = ?1 ), INTERVAL 6 HOUR)), " +
                    "BOOKING_TIME = NOW() " +
                    "WHERE booking.BOOKING_ID = ?1", nativeQuery = true)
    int setBookingTimeAndExpireTime(long bookingId);
}
