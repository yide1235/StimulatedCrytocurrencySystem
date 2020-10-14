// CLASS: Investor
//
// Author: yide ma,7796951
//
// REMARKS: What is the purpose of this class? 
// An inevestor list, it contains its own information, 
// and contains a linked list that is the currency lists of 
//this investor.
//
//-----------------------------------------
public class Investor extends Item{
	private String name;
	private String ID;
	private int cash;
	List portfolio;

	public Investor(String name,String ID,int cash){
		this.name=name;
		this.ID=ID;
		this.cash=cash;
		portfolio=new List();//list of cryptocurrencies and its amount.
	}
	
	public String toString() {
		return name+" "+ID+" "+cash;
	}
	
	public String getID() {
		return ID;
	}
	
	public int getCash() {
		return cash;
	}
	public void changeCash(int amount) {
		cash+=amount;
	}

	
	public boolean equals(Item other) {
		boolean result=false;
		if(other instanceof Investor) {
			result= this.ID.equals(((Investor)other).getID());
		}
        
		return result;
		
	}

	
	public int hashCode() {
		
		return 0;
	}


	
}
