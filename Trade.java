import java.util.Objects;

// CLASS: Trade
//
// Author: ydie ma, 7796951
//
// REMARKS: What is the purpose of this class? 
// a trade class with its own information
// and hascode.
//
//-----------------------------------------
public class Trade extends Item{
	private String trader1;
	private String trader2;
	private String currency1;
	private String currency2;
	private int amount1;
	private int amount2;
	private int hash;
	
	
	public Trade(String trader1,String trader2,String currency1,
			int amount1,String currency2,int 
			amount2
			) {
		this.trader1=trader1;
		this.trader2=trader2;
		this.currency1=currency1;
		this.currency2=currency2;
		this.amount1=amount1;
		this.amount2=amount2;
	

	}

	
	public boolean equals(Item other) {
		
		return false;
	}

	
	public int hashCode() {
		
		return hash;
	}
	
	public void setHash(Node a) {
		hash=Objects.hash(a);
	}
	

	

	public String toString () {
		String a="TRADE "+trader1+" "
				+trader2+" "
				+currency1+" "
				+currency2+" "
				+amount1+" "
				+amount2+" ";
		return a;
	}
}

