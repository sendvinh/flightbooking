/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import iviettech.project.flightbooking.enumeration.BookingState;
import iviettech.project.flightbooking.enumeration.PaymentMethod;
import iviettech.project.flightbooking.enumeration.PaymentState;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Administrator
 */
@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private long bookingId;
    
    @Column(name = "BOOKING_CODE")
    private String bookingCode;

    @Column(name = "BOOKING_TIME")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingTime;

    @Column(name = "EXPIRE_TIME")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;
    
    @Column(name = "PAYMENT_METHOD")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Column(name = "PAYMENT_CODE")
    private String paymentCode;
    
    @Column(name = "PAYMENT_STATUS")
    @Enumerated(EnumType.STRING)
    private PaymentState paymentStatus;
    
    @Column(name = "TOTAL_COST")
    private double totalCost;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private BookingState state;
    
    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<Ticket> listTicket;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public Booking() {
    }

    public Booking(Date bookingTime, Date expireTime, BookingState state, List<Ticket> listTicket, Customer customer) {
        this.bookingTime = bookingTime;
        this.expireTime = expireTime;
        this.state = state;
        this.listTicket = listTicket;
        this.customer = customer;
    }
    
    
    public Booking(Date bookingTime, Date expireTime,BookingState state, Customer customer) {
        this.bookingTime = bookingTime;
        this.expireTime = expireTime;
        this.state = state;
        this.customer = customer;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }
    
    

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public PaymentState getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentState paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Ticket> getListTicket() {
        return listTicket;
    }

    public void setListTicket(List<Ticket> listTicket) {
        this.listTicket = listTicket;
    }
    
    public void calculateCost(){
        double sum = 0;
        for (int i = 0; i < listTicket.size(); i++) {
            double price = listTicket.get(i).getPrice();
            sum = sum + price;
        }
        setTotalCost(sum);
    }
}
