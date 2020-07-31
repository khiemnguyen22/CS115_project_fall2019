package Splitwise;

import java.util.*;
public class Application {
	public static void main (String []args)
	{
		// friends:
			Customer friend1=new Customer(new User("friend1","1234"),100, new Customer[10]);
			Customer friend2=new Customer(new User("friend2","4567"),60, new Customer[10]);
			Customer friend3=new Customer(new User("friend3","7890"),90,new Customer[10]);
			Customer[] suggestedFriends= {friend1, friend2, friend3};

		//creating the user
		Scanner scan=new Scanner(System.in);
		System.out.println("Create an account");
		System.out.print("Input user name: ");
		String uName=scan.nextLine();
		System.out.print("Input password: ");
		String uPass=scan.nextLine();
		User u1=new User(uName,uPass);
		System.out.println("Your ID: "+ u1.getID());

		//Login
		String loginPass;
		int inputID;
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("number of login attempts: "+u1.getAttempts());
			System.out.print("input ID: ");
			inputID=sc.nextInt();
			System.out.print("input password: ");
			loginPass=sc.next();
		} while(u1.login(inputID,loginPass)==false && u1.getIsLocked()==false);
		Customer[] friends=new Customer[100];
		Customer c1=new Customer(u1,100,friends);

		if(u1.login(inputID, loginPass)==true)
		{
			boolean continue1=true;
			while(continue1)
			{
			Scanner scan1= new Scanner(System.in);
			int action;
			System.out.println("what do you want to do?");
			System.out.println(" 1. Add a friend");
			System.out.println(" 2. Delete a friend");
			System.out.println(" 3. check balance");
			System.out.println(" 4. return purchases handled");
			System.out.println(" 5. change password");
			System.out.println(" 6. add a purchase");
			action=scan1.nextInt();
			switch(action)
			{
			case 1:
			{
				int nullAlert=0;
				Customer[] tempSuggestedFriends;
				if (suggestedFriends.length>0)
				{
					tempSuggestedFriends=new Customer[suggestedFriends.length-1];
				}
				else
				{
					System.out.println("friends not found");
					break;
				}
				for (int i=0;i<suggestedFriends.length;i++)
				{	
					if(suggestedFriends[i]==null)
					{
						nullAlert++;
					}
					else
					System.out.println("suggested friends: "+ suggestedFriends[i].getUser().getName()+ ", ID: "+suggestedFriends[i].getUser().getID());
				}
				Scanner s=new Scanner(System.in);
				System.out.print("To add a friend, enter that user ID: ");
				int friendID=s.nextInt();
				int countFriend=0;
				for(int i=0,k=0;i<suggestedFriends.length;i++)
				{
					if(suggestedFriends[i].getUser().getID()==friendID)
						{
						c1.addFriend(suggestedFriends[i]);
						suggestedFriends[i].addFriend(c1);
						countFriend++;
						}
					else if(k<(suggestedFriends.length-1))
						tempSuggestedFriends[k++]=suggestedFriends[i];
				}
		
				if(countFriend!=0)
				{
				suggestedFriends=tempSuggestedFriends;
				c1.checkFriendList();
				//System.out.println("suggested friends: "+suggestedFriends.length);
				}
				else
				{
					System.out.println("User ID not found");
					break;
				}
				break;
			}
			case 2:
			{
				int countFriend=0;
				Scanner s=new Scanner(System.in);
				c1.checkFriendList();
				if(c1.getFriendListEmpty()==true)
				{
					break;
				}
				System.out.print("To remove a friend, enter that user ID: ");
				int friendID=s.nextInt();
				Customer[] newSuggestedFriend1=new Customer[suggestedFriends.length+1];
				for(int j=0;j<suggestedFriends.length;j++)
				{
					newSuggestedFriend1[j]=suggestedFriends[j];
				}
				for(int i=0;i<c1.getCountFriend();i++)
				{
					if(c1.getFriendList()[i].getUser().getID()==friendID)
					{
						newSuggestedFriend1[suggestedFriends.length]=c1.getFriendList()[i];
						c1.deleteFriend(c1.getFriendList()[i]);
						countFriend++;
						break;
			
					}
				}
				if(countFriend!=0)
				{
				suggestedFriends=newSuggestedFriend1;
				c1.checkFriendList();
				}
				else
				{
					System.out.println("User ID not found");
				}
				break;
			}
			case 3:
			{
				System.out.println("your balance: "+c1.checkBalance());
				break;
			}
			case 4:
			{
				if(c1.summarize().equals(""))
				{
					System.out.println("no transaction made");
				}
				else
				System.out.println(c1.summarize());
				break;
			}
			case 5:
			{
				c1.getUser().setPassword();
			}
			case 6:
			{
				Scanner s=new Scanner(System.in);
				System.out.print("name of items/services: ");
				String item=s.next();
				System.out.print("Amount: ");
				double amount=s.nextDouble();
				System.out.print("Payment owner (enter ID. IF your friendlist is empty, enter your ID): ");
				int paymentID=s.nextInt();
				int numberOfFriends=0;
				Customer[] newfriendsInvolved=new Customer[c1.getCountFriend()];
				int continue3=0,k=0;
				if(c1.getCountFriend()!=0)
				{
				if(paymentID!=c1.getUser().getID())
				{
					for(int i=0;i<c1.getCountFriend();i++)
					{
						if(paymentID==c1.getFriendList()[i].getUser().getID())
						{
							newfriendsInvolved[k++]=c1.getFriendList()[i];
						}
					}
					numberOfFriends++;
					continue3++;
				}
				for(int i=0;i<c1.getCountFriend();i++)
				{
					Scanner scan2=new Scanner(System.in);
					System.out.print("input friendsID (0 for no other friends involved in the purchase): ");
					int friendsID=scan2.nextInt();
					if(friendsID==0)
					{
						continue3++;
						break;
					}
					continue3++;
					for(int j=0;j<c1.getCountFriend();j++)
					{
						if(friendsID==c1.getFriendList()[j].getUser().getID())
						{
							newfriendsInvolved[k++]=c1.getFriendList()[j];
							numberOfFriends++;
						}


					}
					System.out.print("more friends (1:yes,2: no): ");
					int continue2=scan2.nextInt();
					if(continue2!=1)
					{
						break;
					}
				}

				if(numberOfFriends!=continue3)
				{
					System.out.println("cannot find friends");
				}
				}
				int purchaseCount=0;
				Customer[] friendsInvolved=newfriendsInvolved;
				Purchase p1;
				if(paymentID==c1.getUser().getID())
				{
					p1=new Purchase(c1,item,amount);
					c1.purchase(p1);
					c1.updateBalance(p1, numberOfFriends, friendsInvolved);
					purchaseCount++;
				}
				else
				{
					for(int i=0;i<c1.getCountFriend();i++)
					{
						if(c1.getFriendList()[i].getUser().getID()==paymentID)
						{
							p1=new Purchase(c1.getFriendList()[i],item,amount);
							c1.purchase(p1);
							c1.updateBalance(p1, numberOfFriends, friendsInvolved);
							purchaseCount++;
						}
					}
				}
				if (purchaseCount==0)
				{
					System.out.println("Invalid information");
					break;
				}
				System.out.println("add purchase successful");
				System.out.println("number of friends shared: "+numberOfFriends);
				System.out.println("your remaining balance: "+c1.checkBalance());
					
				break;
				
			}
			default:
				continue1=false;
				break;
			}
			int checkContinue;
			System.out.print("continue(1:yes,0:No): ");
			checkContinue=scan1.nextInt();
			if(checkContinue!=1)
			{
				continue1=false;
			}
			else
				continue1=true;
			}
		}
	}
}
