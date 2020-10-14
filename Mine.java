import java.util.Objects;

// CLASS: Mine
//
// Author: ydie ma, 7796951
//
// REMARKS: What is the purpose of this class? 
// a mine class that contains its own information and 
// hashcode .
//
//-----------------------------------------
public class Mine extends Item{
    private String userID;
    private String symbol;
    private int amount;
    private int hash;
    
    
    
    
    public String getUserID() {
    	return userID;
    }
    public String getSymbol() {
    	return symbol;
    }
    
    public int getAmount() {
    	return amount;
    }
    
	public Mine(String userID, String symbol, int amount) {
		this.userID=userID;
		this.symbol=symbol;
		this.amount=amount;
		
		
		
		
		
	}
	
	public boolean equals(Item other) //useless for mine class.
	{
       return false;
	
	}
	
	public void setHash(Node a) {
		hash=Objects.hash(a);
	}

	
	public int hashCode() {
		
		return hash;
	}
	
	public String toString() {
		String a="MINE"+userID+" "+symbol+" "+amount;
		return a;
	}
	

	
}
