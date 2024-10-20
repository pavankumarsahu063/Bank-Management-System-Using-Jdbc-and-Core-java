package BankManagementSystem;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Bank {
	
	
	
	
	public static void main(String[] args) throws Exception{
	    Scanner sc=new Scanner(System.in);
					while(true) {
					BankMethods.DisplayUi();
					int input=sc.nextInt();
		        	switch (input) {
					case 1:{
						BankMethods.CreateAccount();
						break;
					}
					case 2:
						BankMethods.checkBalance();
						break;
						
					case 3:
						BankMethods.dipositeAmout();
						break;
						
					case 4:
						BankMethods.withdrawBalance();
						break;
					case 5:
						
						break;
					case 6:
						BankMethods.dispalyDetails();
						break;
					case 7:
						BankMethods.updateAccountDetails();
						break;
					case 8:
						BankMethods.deleteAccount();
						break;
					case 0:
					{
						System.out.println("Exiting...........");
						return;
					}

					default:
						System.out.println("Please  Enter valid Input");
					}
		        }
		}
		
		
		
			
	}


