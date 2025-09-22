package com.wipro.bank.main;
import java.util.Scanner;

import com.wipro.bank.service.BankService;

public class MainClass {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int tenure=sc.nextInt();
		float priniple=sc.nextFloat();
		int age=sc.nextInt();
		String gender=sc.next();
		BankService bs=new BankService();
		bs.calculate(priniple, tenure, age, gender);
		
	}

	
	
	
}
