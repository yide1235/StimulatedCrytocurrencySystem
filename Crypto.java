
// CLASS: crypto
//
// Author: yide ma,7796951
//
// REMARKS: What is the purpose of this class? 
// a crypto currency class, when new a crypto objects,
// it contains a linked list which is its own blockchain.
//
//-----------------------------------------
public class Crypto extends Item{
	private String name;
	private String symbol;
	private int amount;
	public  List blockChain;//a blockchain for each cryptocurrencies.

	public Crypto (String name,String symbol,int amount){
		this.name=name;
		this.symbol=symbol;
		this.amount=amount;
        blockChain=new List();//a blockchain list for each crypto.
    }
	
	public String getName() {
		return name;
	}
	public int getAmount() {
		return amount;
	}
	
	public void changeAmount(int a) {
		amount+=a;
	}

	
	public boolean equals(Item other) {
		boolean result=false;
		if(other instanceof Crypto) {
			result= symbol.equals(((Crypto)other).getSymbol());
		}

		return result;
		

	}
	 
	
	
	
	public String toString() {
		return name+" "+symbol+" "+amount;
	}
	
	public String getSymbol() {
		return symbol;
	}

	
	public int hashCode() {
		
		return 0;
	}
}