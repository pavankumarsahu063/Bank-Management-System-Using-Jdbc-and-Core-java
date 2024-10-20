package BankManagementSystem;

import java.awt.geom.Arc2D.Double;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class BankMethods {
	private final static String url="jdbc:mysql://localhost:3306/accounts";
	private final static String userName="root";
	private final static String password="Pavan@123";
	
	static Scanner sc=new Scanner(System.in);
	
	public static void DisplayUi() {
	        System.out.println("1. Create Account");
	        System.out.println("2. Check Balance");
	        System.out.println("3. Deposit Money");
	        System.out.println("4. Withdraw Money");
	        System.out.println("5. Transfer Money");
	        System.out.println("6. Display Account Details");
	        System.out.println("7. Update Account Details");
	        System.out.println("8. Delete Account");
	        System.out.println("0. Exit");
	}
	
	public static void CreateAccount() {
		Connection con=null;
		PreparedStatement stm=null;
		
		try {
			

			con=DriverManager.getConnection(url,userName,password);
			String sqlQuery="INSERT INTO account_details(account_number,account_holder_name ,balance,account_phoneNumber)VALUES(?,?,?,?)";
			
			stm=con.prepareStatement(sqlQuery);
			
			System.out.println("Enter Account number");
			String acountNumber=sc.next();
			stm.setString(1,acountNumber);
			System.out.println("Enter account holder name");
			sc.nextLine();
			String accountHolderName=sc.nextLine();
			
			stm.setString(2, accountHolderName);
			System.out.println("Add some balance");
			double balance=sc.nextDouble();
			stm.setDouble(3, balance);
			System.out.println("Enter Your Phone Number");
			String phoneNumber=sc.next();
			stm.setString(4, phoneNumber);
			int rs=stm.executeUpdate();
			
			if(rs>0) {
				System.out.println("Account Created Suessfully");
			}
			else {
				System.out.println("please Enter valid data");
			}
			con.close();
			stm.close();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	public static void checkBalance() {
	
	
		try {
			Connection con=DriverManager.getConnection(url,userName,password);
			
			String query="SELECT balance FROM account_details WHERE account_number=?";
			PreparedStatement smt=con.prepareStatement(query);
			System.out.println("Please enter your Account Number");
			String accountNumber=sc.next();
			smt.setString(1, accountNumber);
			
			ResultSet rs=smt.executeQuery();
			boolean isPresent=false;
			while(rs.next()) {
				String returnBalnce=rs.getString("balance");
				 isPresent=true;
				 System.out.println("Your account balance is: "+returnBalnce);
				
			}
			if(!isPresent) {
				System.out.println("please check your AAccount number");
			}
			con.close();
			smt.close();
			rs.close();
			
		}
		catch(SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
	}
	
	public static void dipositeAmout() {
		try {
			Connection con=DriverManager.getConnection(url,userName,password);
			System.out.println("Please Enter Your Account Number");
			String accountNumber=sc.next();
			System.out.println("Enter The amount you want to deposite");
			double despositeAmout=sc.nextDouble();
			
			String qry="SELECT balance FROM account_details WHERE account_number=?";
			PreparedStatement pre=con.prepareStatement(qry);
			pre.setString(1, accountNumber);
			ResultSet res=pre.executeQuery();
			double currentBalance=0;
			if(res.next()) {
				currentBalance+=res.getDouble("balance");
				
			}
			else {
				System.out.println("Please Enter valid accounnt number");
			}
			
			double updatedBalance=despositeAmout+currentBalance;
			String updatedQuery="UPDATE account_details SET balance=? WHERE account_number = ?";
			PreparedStatement upstm=con.prepareStatement(updatedQuery);
			upstm.setDouble(1, updatedBalance);
			upstm.setString(2, accountNumber);
			
			int effect=upstm.executeUpdate();
			
			if(effect>0) {
				System.out.println("Dipisite Suessfully your currentbalnce is "+updatedBalance);
			}
			else {
				System.out.println("Unable to deposite");
			}
			
		}
		catch(SQLException e) {
			
		}
	}
	
	
	public static void withdrawBalance() {
		try {
			Connection con=DriverManager.getConnection(url,userName,password);
			
			System.out.println("Please Enter Your Account Number");
			String accoundNumber=sc.next();
			System.out.println("please Enter money you want to withdraw");
			double amount=sc.nextDouble();
			
			String balaneInAccount="SELECT balance FROM account_details WHERE account_number=?";
			
			PreparedStatement pre=con.prepareStatement(balaneInAccount);
			pre.setString(1, accoundNumber);
			
			ResultSet res=pre.executeQuery();
			boolean isFound=false;
			double balanceAmout=0;
			if(res.next()) {
				balanceAmout=res.getDouble("balance");
				isFound=true;
				
			}
			if(!isFound) {
				System.out.println("please Enter valid Account Number");
			}
			
			
			if(amount > balanceAmout) {
				System.out.println("Insufficient balance. Your current balance is: " + balanceAmout);
	            return;
			}
			double updatedBalance=balanceAmout-amount;
			
			String updatedQuery="UPDATE account_details SET balance=? WHERE account_number = ?";
			PreparedStatement upstm=con.prepareStatement(updatedQuery);
			upstm.setDouble(1,updatedBalance);
			upstm.setString(2, accoundNumber);
			
			int rowsEffected=upstm.executeUpdate();
			if(rowsEffected>0) {
				System.out.println("Withdrawal Successful.You Current Balance: "+updatedBalance);
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	
	public static void dispalyDetails() {
		try {
			Connection con=DriverManager.getConnection(url,userName,password);
			
			System.out.println("Please Enter Your Account Number");
			String accoundNumber=sc.next();
			String sqlquery="SELECT * FROM account_details WHERE account_number=?";
			
			PreparedStatement pre=con.prepareStatement(sqlquery);
			pre.setString(1, accoundNumber);
			ResultSet res=pre.executeQuery();
			
			if(res.next()) {
				System.out.println(res.getString("account_number")+"   "+res.getString("account_holder_name")+"   "+res.getDouble("balance")+"  "+res.getString("account_phoneNumber"));
			}
			else {
				System.err.println("Enter valid account number");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void deleteAccount() {
		try {
			Connection con=DriverManager.getConnection(url,userName,password);
			System.out.println("Please Enter Your Acount Number");
			String accountNumber=sc.next();
			String sqlQuery="DELETE FROM account_details WHERE account_number = ?\r\n"
					+ "";
			
			PreparedStatement pre=con.prepareStatement(sqlQuery);
			pre.setString(1,accountNumber);
			
			int effect=pre.executeUpdate();
			if(effect>0) {
				System.out.println("Account Deleted !!");
			}
			else {
				System.out.println("Please check Your Account Number Once");
			}
			
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
	}
	
	public static void updateAccountDetails() {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
			 con=DriverManager.getConnection(url,userName,password);
			 
			 System.out.println("Please Enter Your Account Number ");
			 String accountNumber=sc.nextLine();
			 
			System.out.println("What you want to update");
			System.out.println("1 .Account Holder Name");
			System.out.println("2 .Account Holder Phone Number");
			int choice=sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Please Enter Your Name :");
				String NewaccountHolderName=sc.next();
				String sqlQuery="UPDATE account_details SET account_holder_name = ? WHERE account_number = ?";
				
				stmt=con.prepareStatement(sqlQuery);
				
				stmt.setString(1, NewaccountHolderName);
				stmt.setString(2,accountNumber);
				
				int effect=stmt.executeUpdate();
				
				if(effect>0) {
					System.out.println("Updated sucessfully");
				}
				else {
					System.out.println("Check Your Account Number");
				}
			
				con.close();
				
				stmt.close();
				
				break;
			case 2:
				
				System.out.println("Pleaase Enter Your new Phone Number");
				String phoneNumber=sc.next();
				String sqlQuery1="UPDATE account_details SET account_phoneNumber = ? WHERE account_number = ?";
				
				stmt=con.prepareStatement(sqlQuery1);
				stmt.setString(1,phoneNumber);
				stmt.setString(2, accountNumber);
				
				int res=stmt.executeUpdate();
				if(res>0) {
					System.out.println("Phone NUmber Updated");
				}
				else {
					System.out.println("please check your account number");
				}
				
				
				break;

			default:
				System.out.println("Sorry we dont have that kind of update");
				break;
			}
			
			
		}
		catch(SQLException e) {
			System.out.println("Error in updating "+e.getMessage());
		}
	}
	
//	public static void 
	

}
