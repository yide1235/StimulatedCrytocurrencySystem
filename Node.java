import java.util.Objects;

// CLASS: Node
//
// Author: yide ma, 7796951
//
// REMARKS: What is the purpose of this class? 
// a node class for a linked list structure.
//
//-----------------------------------------
public class Node{
	 private Item item;
	
	 private Node link;
	
	
	public Node(Item i) 
	{
		this.item=i;
		this.link=null;
	}

	public int hashCode() {

		return Objects.hash(item,link);
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setLink(Node link) {
		
		this.link=link;
		
	}
	
	public Node getLink() {
		return link;
	}
	
	public void changeItem(Item a) {
		item=a;
	}

}//end of Node class.