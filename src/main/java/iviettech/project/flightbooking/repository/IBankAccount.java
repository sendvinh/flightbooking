/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IBankAccount extends CrudRepository<BankAccount,Long>{
    BankAccount findByAccountNo(String accountNo);
    
    BankAccount findByBankCodeAndAccountNo(String bankCode, String accountNo);
    
    BankAccount findByBankCodeAndAccountNoAndOwnerAndExpireMonthAndExpireYear
        (String bankCode, String accountNo, String owner, int expMonth, int expYear);
}
