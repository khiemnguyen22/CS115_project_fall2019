package Splitwise;

public class Purchase {
	private Customer c;
	private String item;
	private double amount;
	
	public Purchase(Customer c,String item, double amount)
	{
		this.c=c;
		this.item=item;
		if (amount<0)
		{
			amount=0;
		}
		else
		this.amount=amount;
		
	}
	
	public String toString()
	{
		int userID=c.getUser().getID();
		return "Customer " +userID +"paid "+ amount+" dollars "+" for " +item;
	}
	public double getAmount()
	{
		return amount;
	}
	public Customer getCustomer()
	{
		return c;
	}
	public String getItem()
	{
		return item;
	}

}
