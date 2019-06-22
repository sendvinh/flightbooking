/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.service;

import iviettech.project.flightbooking.entity.Bank;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class UtilitiesService {
    public static String formatVND(double number) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.'); 
        DecimalFormat df = new DecimalFormat("###,###,###", otherSymbols);
        return df.format(number);
    }  
    
    public static double afterTax(double cost) {
        double tax = 0.1;
        return Math.round((1+tax) * cost);
    }
    
    public static double tax(double cost) {
        double tax = 0.1;
        return Math.round(tax * cost);
    }
    
    public static String changeFormatStringDate(String stringDate) {
        String[] parts = stringDate.split("/");
        return parts[2]+"-"+parts[1]+"-"+parts[0];
    }
    
    //pattern = 'yyyy-MM-dd'
    public static Date convertStringToDate(String pattern,String stringDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    public static boolean convertStringToBoolean(String string) {
        if(string.equalsIgnoreCase("true")){
            return true;
        } else { 
            return false;
        }
    }
    
    public static List<Boolean> convertListStringToListBoolean(String[] listString) {
        List<Boolean> listBoolean = new ArrayList<>();
        for(String string : listString) {
            listBoolean.add(Boolean.valueOf(string));
        }
        return listBoolean;
    }
    
    public static String convertDateToString(Date date) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if(null != date) {
            return simpleDateFormat.format(date);
        } else {
            return "";
        }
    }
    
    public static String createBookingCode() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        String randomString = RandomStringUtils.randomAlphanumeric(4);
        return today + "/" + randomString;
    }
    
    public static String createPaymentCode() {
        return String.valueOf(System.currentTimeMillis()).substring(5);
    }
    
    public static List<Bank> createListBank() {
        List<Bank> listBank = new ArrayList<>();
        listBank.add(new Bank("VCB", "NH TMCP Ngoai thuong Viet Nam"));
        listBank.add(new Bank("BIDV", "NH TMCP dau tu va phat trien Viet Nam"));
        listBank.add(new Bank("AGRI", "NH nong nghiep va phat trien nong thon"));
        listBank.add(new Bank("ACB", "NH TMCP A Chau"));
        listBank.add(new Bank("ABB", "NH TMCP An Binh"));
        listBank.add(new Bank("SCB", "NH TMCP Sai Gon"));
        return listBank;
    }
    
    public static List<Integer> createListMonth() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i);
        }
        return list;
    }
    
    public static List<Integer> createListYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> list = new ArrayList<>();
        for (int i = -10; i <= 10; i++) {
            list.add(year + i);
        }
        return list;
    }
}
