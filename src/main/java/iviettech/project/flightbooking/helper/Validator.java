/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Validator {
    public static boolean isValidDateFormat(String pattern, String value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(value);
            return value.equals(sdf.format(date));
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public static boolean isValidEmail (String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        return email.matches(regex);
    }
    
    public static boolean isReturnDateAfterDepartDate(String departDateParam, String returnDateParam) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            Date departDate = sdf.parse(departDateParam);
            Date returnDate = sdf.parse(returnDateParam);
            return (returnDate.compareTo(departDate) >= 0);
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public static boolean isValidMobilePhone (String phoneNumber) {
        String regex = "\\d{10}";
        return phoneNumber.matches(regex);
    }
    
    public static boolean isValidAccountNo (String accountNo) {
        String regex = "\\d{16}";
        return accountNo.matches(regex);
    }
    
    public static boolean isRequired(String value) {
        if(null == value || value.isEmpty()) {
            return false;
        } else {
            return value.trim().length() > 0;
        }
    }

    public static boolean isValidDateFormat(String dateString) {
        return isValidDateFormat("dd/mm/yyyy", dateString);
    }
    
   
    

}
