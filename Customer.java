package Splitwise;

public class Customer {
	private User u;
	private double balance;
	private Customer[] friends;
	private boolean friendlistEmpty=false;
	private String transactions;
	private int countFriend=0;
	
	public Customer(User u, double balance,Customer[] friends)
	{
		this.u=u;
		if(balance==0)
		{
			this.balance=0;
		}
		else this.balance=balance;
		Customer[] tempFriends=new Customer[friends.length];
		for(int i=0;i<friends.length;i++)
		{
			tempFriends[i]=friends[i];
		}
		this.friends=tempFriends;
		countFriend=0;
		transactions="";
	}
	public User getUser()
	{
		return u;
	}
	public void addFriend (Customer c)
	{
		Customer[] newFriendList=new Customer[countFriend+1];
		for (int i=0;i<countFriend;i++)
		{
			newFriendList[i]=friends[i];
		}
		newFriendList[countFriend]=c;
		friends=newFriendList;
		countFriend++;

	}
	public void checkFriendList()
	{
		int nullAlert=0;
		for(int i=0;i<friends.length;i++)
		{ 
			if(friends[i]==null)
			{
				nullAlert++;
				continue;
			}
			else
			System.out.println("your Friend: "+friends[i].getUser().getName()+", ID "+friends[i].getUser().getID());
		}
		if(nullAlert==friends.length)
		{
			System.out.println("friendList empty");
			friendlistEmpty=true;
		}
	}
	public boolean getFriendListEmpty()
	{
		return friendlistEmpty;
	}
	public int getCountFriend()
	{
		return countFriend;
	}
	public void deleteFriend(Customer c)
	{
		Customer[] newFriendsList= new Customer[countFriend-1];
		for (int i=0,k=0;i<countFriend;i++)
		{
			if (friends[i]==c)
			{
				continue;
			}
			else 
				newFriendsList[k++]=friends[i];
		}
		friends=newFriendsList;
		countFriend--;
	}
	public Customer[] getFriendList()
	{
		Customer[] newFriendList= new Customer[friends.length];
		for(int i=0;i<friends.length;i++)
		{
			newFriendList[i]=friends[i];
		}
		return newFriendList;
	}
	public double checkBalance()
	{
		return balance;
	}
	public void purchase(Purchase p)
	{
		p.getCustomer().balance-=p.getAmount();
	}
	public void updateBalance(Purchase p, int numberOfFriends,Customer[] friendsInvolved)
	{
		if(numberOfFriends!=0)
		{
		double amountSplited=(p.getAmount())/(numberOfFriends+1);
		p.getCustomer().balance+=amountSplited*(numberOfFriends);
		if(p.getCustomer().getUser().getID()!=u.getID())
		{
			balance-=amountSplited;
		}
		for(int i=0;i<numberOfFriends;i++)
		{
			if(friendsInvolved[i].getUser().getID()==p.getCustomer().getUser().getID())
			{
				continue;
			}
			else
			friendsInvolved[i].balance-=amountSplited;
		}
		if(p.getCustomer().getUser().getID()==u.getID())
		{
			transactions+= "user paid "+amountSplited +" dollars "+ " for "+p.getItem()+ " \n";
		}
		else
			transactions+="user owed "+amountSplited+" dollars to "+p.getCustomer().getUser().getName()+", ID: "+p.getCustomer().getUser().getID()+" for "+p.getItem()+ " \n";
	}
		else
			transactions+= "user paid "+p.getAmount() +" dollars "+ " for "+p.getItem()+ " \n";
	}
	public String summarize()
	{
		return transactions;
	}
}
