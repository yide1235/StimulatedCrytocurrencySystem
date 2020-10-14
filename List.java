// CLASS: List
//
// Author: yide ma, 7796951
//
// REMARKS: What is the purpose of this class? 
// it is a list class that is the only data sructure in 
// my code, contains a node top and hashcode for this list.
//
//-----------------------------------------
public class List{
	
	 private Node top;
	
	 int hashCode;//hashCode for each cryptocurrencies.other list dont need for this.

	
	public List() {
		
		top=null;
		
	    hashCode=0;
		
	}
	
	public void add(Node a) {
		
		if(top==null) {
			
		top=a;
			
		}
		else { /*list for investor list is a stack ADT sturcture.
			if want to find the most previous one need to travese 
			from top to the last one, last one is the most previous one.*/
         a.setLink(top);
         top=a;
		}
	}
	
	public Node getTop(){
		return top;
	}
}//end of List class.

