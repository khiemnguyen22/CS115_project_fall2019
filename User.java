package Splitwise;
import java.util.*;
public class User {
	private int id;
	private String password;
	private String name;
	private boolean islocked=false;
	private int numberOfAttempts=1;
	public static int counter=0;

	public User (String n, String pass)
	{
		name=n;
		password=pass;
		counter++;
		id=counter;
		numberOfAttempts=1;
	}
	public String getName()
	{
		return name;
	}
	public int getID()
	{
		return id;
	}
	public boolean getIsLocked()
	{
		return islocked;
	}
	public boolean login(int id, String pass)
	{
		if (islocked==true|| numberOfAttempts>=3){
			System.out.println("your acount is locked");
			islocked=true;
			numberOfAttempts=0;
			return false;
		}
		else
		{
			if (this.id==id && password.equals(pass))
			{
				numberOfAttempts=0;
				return true;
			}
			else 
			{
				System.out.println("invalid information, please re-enter");
				numberOfAttempts++;
				return false;
			}
		}
	}
	public int getAttempts()
	{
		return numberOfAttempts;
	}
	public void setPassword()
	{
		Scanner scan= new Scanner(System.in);
		System.out.println("To change your password, ");
		System.out.print("enter your ID: ");
		int tempID=scan.nextInt();
		System.out.print("Enter old password: ");
		String tempPass=scan.next();
		if (login(tempID,tempPass))
		{
			String newPass,reenterNewPass;
			do {
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter new Password: ");
			newPass=sc.next();
			System.out.print("Re-enter new Password: ");
			reenterNewPass=sc.next();
			if(!newPass.equals(reenterNewPass))
			{
				System.out.println("new password not matched, enter again");
				numberOfAttempts++;
			}
			if(numberOfAttempts>=3)
			{
				System.out.println("change password failed");
				break;
			}
			} while(!newPass.equals(reenterNewPass));
			password=newPass;
			System.out.println("Success! Your new password: "+ password);
		}
		else
			System.out.println("change password failed");
	}
}
