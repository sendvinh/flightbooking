/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ModelWrapper {
    private List<Ticket> listDepartTicket;
    private List<Ticket> listReturnTicket;
    private Customer customer;
    
    public ModelWrapper() {
    }
    
    public ModelWrapper(List<Ticket> listDepartTicket) {
        this.listDepartTicket = listDepartTicket;
    }
    
    public ModelWrapper(List<Ticket> listDepartTicket, List<Ticket> listReturnTicket) {
        this.listDepartTicket = listDepartTicket;
        this.listReturnTicket = listReturnTicket;
    }

    public List<Ticket> getListDepartTicket() {
        return listDepartTicket;
    }

    public void setListDepartTicket(List<Ticket> listDepartTicket) {
        this.listDepartTicket = listDepartTicket;
    }

    public List<Ticket> getListReturnTicket() {
        return listReturnTicket;
    }

    public void setListReturnTicket(List<Ticket> listReturnTicket) {
        this.listReturnTicket = listReturnTicket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void addListDepartTicket(List<Ticket> listDepartTicket) {
        this.listDepartTicket = new ArrayList<>(listDepartTicket);
    }
    
    public void addListReturnTicket(List<Ticket> listReturnTicket) {
        this.listReturnTicket = new ArrayList<>(listReturnTicket);
    }
    
    public void addCustomer () {
        this.customer = new Customer();
    }

    public void removeDepartTicket(long ticketId){
        for (int i = 0; i < listDepartTicket.size(); i++) {
            Ticket ticket = listDepartTicket.get(i);
            if(ticket.getTicketId() == ticketId){
                listDepartTicket.remove(ticket);
            }
        }
    }
    
    public void removeReturnTicket(long ticketId){
        for (int i = 0; i < listReturnTicket.size(); i++) {
            Ticket ticket = listReturnTicket.get(i);
            if(ticket.getTicketId() == ticketId){
                listReturnTicket.remove(ticket);
            }
        }
    }
    
    public void addDepartTicket(Ticket ticket){
        listDepartTicket.add(ticket);
    }
    
    public void addReturnTicket(Ticket ticket){
        listReturnTicket.add(ticket);
    }
    
    
    public int size(){
        return listDepartTicket.size() + listReturnTicket.size();
    }
    
    public boolean isEmpty() {
        return listDepartTicket.isEmpty() && listReturnTicket.isEmpty();
    }
}
