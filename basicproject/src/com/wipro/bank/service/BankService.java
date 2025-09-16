package com.wipro.bank.service;

import com.wipro.bank.acc.RDAccount;
import com.wipro.bank.exception.BankValidationException;

public class BankService {
    public boolean validateData(int principle, int tenure, int age, String gender) {
        try {
            if (principle >= 500
                && (tenure == 5 || tenure == 10)
                && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))
                && (age > 1 && age < 100)) {

                return true;
            } else {
                throw new BankValidationException();
            }
        } catch (BankValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return false; 
        }
    }
    public void calculate(float principle,int tenure,int age,String gender)
    {
    	if (validateData((int)principle, tenure, age, gender))
    	{
    	   RDAccount account = new RDAccount(tenure, principle); 
    		account.setInterest(age, gender);
    		System.out.println(account.calculateInterest());
    		System.out.println(account.calculateAmountDeposited());
    		System.out.println(account.calculateMaturityAmount(principle, principle));
    		
    	}
    }
}
