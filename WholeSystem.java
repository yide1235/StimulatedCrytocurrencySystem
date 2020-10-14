// CLASS: WholeSystem
//
// Author: yide ma, 7796951
//
// REMARKS: What is the purpose of this class? 
// A class that contains a linked list of 
// investor , and a linked list contain crypto currencies.
// for each crypto currency in the crypto currency linked list, it
// contain a linked list which is its own block chain.
//
//-----------------------------------------
public class WholeSystem{
	
	private List investorList;
	
	private List cryptoList;
	
	public WholeSystem() {

        investorList=new List();
        
        cryptoList=new List();
	}
	
	public List getInvestorList() {
		return investorList;
	}
	
	public List getCryptoList() {
		return cryptoList;
	}
	
	
	public void receive(String line) {
		
		String[] tokens= line.split(" ");
		
	    if(tokens[0].equals("#")) {
            System.out.println(line);
            
		}
		else if(tokens[0].equals("NEW")){
			this.addInvestor(tokens[1],tokens[2],tokens[3],Integer.parseInt(tokens[4]));
		}
		else if(tokens[0].equals("CRYPTO")) {
			this.addCrypto(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
		}
		else if(tokens[0].equals("MINE")){
            this.addMine(tokens[1],tokens[2],Integer.parseInt(tokens[3]));
		}
		else if(tokens[0].equals("TRADE")){
            this.trade(tokens[1],tokens[2],tokens[3],Integer.parseInt(tokens[4]),
            		tokens[5],Integer.parseInt(tokens[6]));
		}
		else if(tokens[0].equals("REPORT")){
			
            this.report1(tokens[1]);
		}

		else if(tokens[0].equals("CRYPORT")){
            this.cryport(tokens[1]);
		}


		else if(tokens[0].equals("END")){
			System.out.println("DONE.");
			
		}
	}
	
	
	//------------------------------------------------------
    // addInvestor
    //
    // PURPOSE:    add node contains investor to the instance 
	//variable of the my Wholesystem object.
    // PARAMETERS:
    //     parameter of an inesvtor
    //     
    //     
    // Returns: void.
    //------------------------------------------------------	

	public void addInvestor(String name1,String name2,String ID,int amount){
		String name=name1+" "+name2;
		Investor other=new Investor(name,ID,amount);
		if(investorList.getTop()==null) {
		investorList.add(new Node(other));
		System.out.print("\nSuccess. \n");
		System.out.print(other);
		
		}
		else {//use to check if it is already in the list.
			boolean found=false;
			
			Node curr=investorList.getTop();
			
			while(!found&&curr!=null) {
				
				if(curr.getItem().equals(other)) {
					found=true;
					
				}
				
				curr=curr.getLink();	
				
				
			}
			if(found) {
				System.out.print("\nDuplicate.\n");
				System.out.println(other);
			}
			else{
				investorList.add(new Node(other));
				System.out.print("\nSuccess.\n");
				System.out.println(other);
			}
			
		}
	}


	//------------------------------------------------------
    // addcrypto
    //
    // PURPOSE:    add code conatins crypto currency to 
	// the instance variable of wholeSystem objects. 
    // PARAMETERS:
    //     parameters of a crypto currency
    //     
    //     
    // Returns: void.
    //------------------------------------------------------
	public void addCrypto(String name1,String name2,int amount){
		Crypto other=new Crypto(name1,name2,amount);
		if(cryptoList.getTop()==null) {
		cryptoList.add(new Node(other));
		System.out.print("\nSuccess. \n");
		System.out.print(other);
		}
		else {//use to check if it is already in the list.
			boolean found=false;
			
			Node curr=cryptoList.getTop();
			
			while(!found&&curr!=null) {
				
				if(curr.getItem().equals(other)) {
					found=true;
					
				}
				
				curr=curr.getLink();	
				
				
			}
			if(found) {
				System.out.print("\nDuplicate. \n");
				System.out.println(other);
			}
			else{
				cryptoList.add(new Node(other));
				System.out.print("\nSuccess. \n");
				System.out.println(other);
			}
			
		}
	}


	
	//------------------------------------------------------
    // addmine
    //
    // PURPOSE:    add mien objects to the corresponding blockchain of 
	// corresponding crypto currency node and change the values of crypto 
	// currency of that inesvtor.
	//
    // PARAMETERS:
    //     userID ,symbol, anount.
    //     
    //     
    // Returns: void.
    //------------------------------------------------------
	public void addMine(String userID,String symbol, int amount) {
		
		Mine other=new Mine(userID,symbol,amount);
		
		boolean foundInInvestors=travInvList(userID);
		
		boolean foundInCrypto=travCrypList(symbol);
		
		boolean insufficient=false;
		
		Node currA=cryptoList.getTop();//to find where the crypto currency is.
		while(currA!=null) {
			if(((Crypto) currA.getItem()).getSymbol().equals(symbol)) {
				break;
			}
			currA=currA.getLink();
		}//now currA should point to where it need to be putted in.
		if(currA!=null) {
		if(((Crypto)currA.getItem()).getAmount()<amount) {
			insufficient=true;
		}
		}
		
		
		if(!foundInInvestors) {System.out.println("\nInvestor not found.\n");}
		else if(!foundInCrypto||insufficient) {System.out.println("\nInsufficient Currency.\n");}
		else {
			
			
			Node curr=cryptoList.getTop();//to find where the crypto currency is.
			while(curr!=null) {
				if(((Crypto) curr.getItem()).getSymbol().equals(symbol)) {
					break;
				}
				curr=curr.getLink();
			}//now curr should point to where it need to be putted in.
			
			
			
			//set hash code for this node when add it.
			Node add=new Node(other);
			((Crypto)curr.getItem()).blockChain.add(add);
			((Mine)add.getItem()).setHash(add);
			
			
			System.out.println("\nMINE Success. "+" add to Bloackchain of Crypto  "
					+((Crypto)curr.getItem()).getSymbol()+"\n");
			((Crypto)curr.getItem()).changeAmount(-amount);
			
			//now change the amount of that crypto in investor.
			String cryptoName=((Crypto)curr.getItem()).getName();
			
			Node curr1=investorList.getTop();//find the Node which the investor should increase.
			
			
			
			while(curr1!=null) {
				if(((Investor) curr1.getItem()).getID().equals(userID)) {
					break;
				}
				curr1=curr1.getLink();
			}
			
			
			
			

			//now curr1 is the where the investor is in the investorList.add crypto to the portfolio of this investor.
			List c=((Investor)curr1.getItem()).portfolio;
		
			if(c.getTop()==null) {
				c.add(new Node(new Crypto(cryptoName,symbol,amount)));//portfolio for this investor is empty.
			}
			else {
				
			boolean foundI=false;
			
				
			Node curr2=c.getTop();
			while(curr2.getLink()!=null) {
				if(((Crypto) curr2.getItem()).getSymbol().equals(symbol)) {
					
					foundI=true;break;
				}
				curr2=curr2.getLink();
			}
			
			if(foundI) {
			//now curr2 is point to the node in portfolio linked list of an investor,
			((Crypto)curr2.getItem()).changeAmount(amount);}
			else {
				c.add(new Node(new Crypto(cryptoName,symbol,amount)));
			}
			}
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
	}

	
	//------------------------------------------------------
    // travInvList
    //
    // PURPOSE:   helper method for knowing if conatins in investor linked list. 
    // PARAMETERS:
    //     
    //     userID
    //     
    // Returns: boolean
    //------------------------------------------------------
	public boolean travInvList( String userID) {
		boolean found=false;
		
		
		
		
		Node curr=investorList.getTop();
		
		while(!found&&curr!=null) {
			if(((Investor) curr.getItem()).getID().equals(userID)) {
				found=true;
			}
			curr=curr.getLink();
		}
		return found;
		
	}
	
	
	
	//------------------------------------------------------
    // tranvCryptoList
    //
    // PURPOSE:      helper method for knowing if conatins in cryptoCurrency linked list.
    // PARAMETERS:
    //     
    //     
    //     
    // Returns: boolean
    //------------------------------------------------------
	public boolean travCrypList(String symbol) {
		boolean found=false;
		
		Node curr=cryptoList.getTop();
		
		while(!found&&curr!=null) {
			if(((Crypto) curr.getItem()).getSymbol().equals(symbol)) {
				found=true;
				
			}
			curr=curr.getLink();
		}
		
		
		return found;
	}
	
	
	
	
	//------------------------------------------------------
    // myMethod
    //
    // PURPOSE:    add trade objects in the block chain of 
	// corresponding crypto currency and change the crypto currency
	// amount of that 2 trader2.
    // PARAMETERS:
    //     string,string,string ,int,string, int
    //     
    //     
    // Returns:void
    //------------------------------------------------------	
	public void trade(String trader1,String trader2,String currency1,
			int amount1,String currency2, int amount2) {
	
		//add a trade object to the blockchain of a node currency list
	    
		
		
		
		boolean canAdd=false;
		
		
		
			Node trade1=foundInInvestorList(trader1);
			Node trade2=foundInInvestorList(trader2);

				
				
				
				
				
				
			
			if(trade1!=null&&trade2!=null) {
				
				if(((Investor)trade1.getItem()).getID().equals(((Investor)trade2.getItem()).getID())){
					System.out.println("\nSame investor.\n");
				}
				else {
				
				
				
				
				/*there is three comdition,
				 * trader1 use cash, trader2 use crypto
				 * trader1 use crypto, trader2 use cash,
				 * trader1 and trader2 both use crypto.
				 */
		       if(currency1.equals("CAD")&&!currency2.equals("CAD"))
		    	   //trader1 use cash ,trader2 use crypto
		       {
		        //for cash change.

		    	   Node currencyFor1=foundInPortfolio(trade1,currency2);
		    	   //currency2 crypto position in trader1 portfolio. 
                   Node currencyFor2=foundInPortfolio(trade2,currency2);
                  
                   //currency for the 2 list in the portlist of that special investor.
                   
                   
                   if(currencyFor2==null) {
                	   System.out.println("\nCurrency not found.\n");
                   }
                   else if(
                		   ((Crypto)currencyFor2.
                		  getItem()).getAmount()<amount2
		              ||((Investor)trade1.getItem()).getCash()<amount1) {
                	  System.out.println("\nInsufficient currency.\n");
                   }
                   else {//actually change.
                	  
                	  canAdd=true;//means can add trade.
                	   
                	   
                	  //for cash change
                	  ((Investor)trade1.getItem()).changeCash(-amount1);
                	  ((Investor)trade2.getItem()).changeCash(amount1);
                	  ((Crypto)currencyFor2.getItem()).changeAmount(-amount2);
                	  if(currencyFor1==null) //cannot found in trader1 portfolio.
                	  {
                		  String name=((Crypto)currencyFor2.getItem()).getName();
                		  ((Investor)trade1.getItem()).portfolio.add(new Node
                				  (new Crypto(name,currency2,amount2)));
                	  }
                	  else {
                		  ((Crypto)currencyFor1.getItem()).changeAmount(amount1);
                	  }
                	  
                   }
			
		        }
		     
		       
		        else if(!currency1.equals("CAD")&&currency2.equals("CAD"))
		        	//trader1 use crypto ,trader2 use cash.
		        {
		        	
	                  Node currencyFor1=foundInPortfolio(trade1,currency1);
	                  Node currencyFor2=foundInPortfolio(trade2,currency1);
	          
                	  
	                  if(currencyFor1==null) {
	                	  System.out.println("\nCurrency not found.\n");
	                  }
	                  else if(
	                	  ((Crypto)currencyFor1.
	                  
	                		  getItem()).getAmount()<amount1
	                		  ||((Investor)trade2.getItem()).getCash()<amount2) {
	                	  System.out.println("\nInsufficient currency.\n");
	                  }
	                  else {//actually change.
	                	  
	                	  canAdd=true;//means can add trade.
	                	  
	                	  
	                	  ((Investor)trade1.getItem()).changeCash(amount2);
	                	  ((Investor)trade2.getItem()).changeCash(-amount2);
	                	  ((Crypto)currencyFor1.getItem()).changeAmount(-amount1);
	                	   if(currencyFor2==null) //cannot found in trader2 portfolio.
	                	  {
	                		  String name=((Crypto)currencyFor1.getItem()).getName();
	                		  ((Investor)trade2.getItem()).portfolio.add(new Node
	                				  (new Crypto(name,currency1,amount1)));
	                	  }
	                	  else {
	                		  ((Crypto)currencyFor2.getItem()).changeAmount(amount2);
	                	  }
	                  }
		        }
		       
		       
		       
		       
		       
		       
		       
		       
		        else {//for situation that they both using crypto currencies.
		        	Node currencyFor1=foundInPortfolio(trade1,currency1);
		        	//currency in trade1.
		        	Node currencyFor1a=foundInPortfolio(trade1,currency2);
		        	
		        	Node currencyFor2=foundInPortfolio(trade2,currency2);
		        	//currency in trade2.
		        	Node currencyFor2a=foundInPortfolio(trade2,currency1);
		        	if(currencyFor1==null||currencyFor2==null) {
		        		System.out.println("\nCurrency not found.\n");
		        	}
		        	else if(((Crypto)currencyFor1.getItem()).getAmount()<amount1
		        			||((Crypto)currencyFor2.getItem()).getAmount()<amount2
		        			
		        			) {
		        		System.out.println("\nInsufficient currency.\n");
		        	}
		        	else {//actually change.
		        		
		        		canAdd=true;//means can add trade.
		        		
		        		
	                	  if(currencyFor1a==null) //cannot found in trader1 portfolio.
	                	  {
	                		  String name=((Crypto)currencyFor2.getItem()).getName();
	                		  ((Investor)trade1.getItem()).portfolio.add(new Node
	                				  (new Crypto(name,currency2,amount2)));
	                	  }
	                	  else {
	                		  ((Crypto)currencyFor1.getItem()).changeAmount(amount1);
	                	  }
	                	  ((Crypto)currencyFor2.getItem()).changeAmount(-amount2);
	                	  
	                	  if(currencyFor2a==null) //cannot found in trader2 portfolio.
	                	  {
	                		  String name=((Crypto)currencyFor1.getItem()).getName();
	                		  ((Investor)trade2.getItem()).portfolio.add(new Node
	                				  (new Crypto(name,currency1,amount1)));
	                	  }
	                	  else {
	                		  ((Crypto)currencyFor2.getItem()).changeAmount(amount2);
	                	  }
	                	  ((Crypto)currencyFor1.getItem()).changeAmount(-amount1);
	                	  
	                	  
		        	}
		        }
				}
			}
			
			else {// if else statement for not found.
				System.out.println("\nInvestor Not Found \n");
			}
			
			
			

			if(canAdd) {
				
				Trade other=new Trade(trader1,trader2,currency1,amount1,
						currency2,amount2);
				Node curr1=cryptoList.getTop();//to find where the crypto currency is.
				while(curr1.getLink()!=null) {
					if(((Crypto) curr1.getItem()).getSymbol().equals(currency1)) {
						break;
					}
					curr1=curr1.getLink();
				}//now curr should point to where it need to be putted in.
				
				//need to add to both of the 2 block chain of the 2 currencyies in the cryptolist.
				
				
				Node add1=new Node(other);
				((Crypto)curr1.getItem()).blockChain.add(add1);
				
				//set hashcode when add a trade objects.
				((Trade)add1.getItem()).setHash(add1);
				
				System.out.println("\nTRADE Success. "+"add to Bloackchain of Crypto  "
						+((Crypto)curr1.getItem()).getSymbol()+"\n");
				
				
				
				Node curr2=cryptoList.getTop();//to find where the crypto currency is.
				while(curr2.getLink()!=null) {
					if(((Crypto) curr2.getItem()).getSymbol().equals(currency2)) {
						break;
					}
					curr2=curr2.getLink();
				}//now curr should point to where it need to be putted in.
				
				
				Node add2=new Node(other);
				((Crypto)curr2.getItem()).blockChain.add(add2);
				
				
				//set hashcode when add a trade objects.
				((Trade)add2.getItem()).setHash(add2);
				System.out.println("\nSuccess. "+"add to Bloackchain of Crypto  "
						+((Crypto)curr2.getItem()).getSymbol()+"\n");
			}
			
			
			
		
			
	    
		
	}
	
	
	
	//------------------------------------------------------
    // foundInvestorLIst
    //
    // PURPOSE:    helper method.
	//return the corresponding inevstor in the investor linked list.
    // PARAMETERS:
    //     string
    //     
    //     
    // Returns: node
    //------------------------------------------------------
	
	public Node  foundInInvestorList(String trader) {
		Node result=null;
		
		Node curr=investorList.getTop();
		
		
		
		
		while(curr!=null) {
			
			if(((Investor)curr.getItem()).getID().equals(trader)) {
				
				break;
				
				
				
			}
			
			curr=curr.getLink();
			
		}
		
		result=curr;
	 
		
		
		
		return result;
		
	}
	
	
	//------------------------------------------------------
    // foundInvestorLIst
    //
    // PURPOSE:    return the corresponding node conatins investor 
	// in the portfolio of that investor in the investor linked list.
    // PARAMETERS:
    //     node ,string
    //     
    //     
    // Returns: node
    //------------------------------------------------------
	public Node foundInPortfolio(Node trade,String currency) {
		Node result=null;
		boolean found=false;
		Node curr=((Investor)trade.getItem()).portfolio.getTop();
		
		if(((Investor)trade.getItem()).portfolio.getTop()!=null) {
		
		
		
		while(curr!=null&&!found) {
			
			if(((Crypto)curr.getItem()).getSymbol().equals(currency)) {
				found=true;
				break;
				
				
				
			}
			
			curr=curr.getLink();
			
		}
		if(found) {
		result=curr;}
		
		}
	 
		
		
		
		return result;
	}
	
	//------------------------------------------------------
    // foundInvestorLIst
    //
    // PURPOSE:    return the corresponding node in the crypto linked list.
    // PARAMETERS:
    //     
    //     String
    //     
    // Returns: node
    //------------------------------------------------------
	public Node foundInCryptoList(String symbol) {
		Node result=null;
		Node curr=cryptoList.getTop();
		
		
		
		
		while(curr.getLink()!=null) {
			
			if(((Crypto)curr.getItem()).getSymbol().equals(symbol)) {
				
				break;
				
				
				
			}
			
			curr=curr.getLink();
			
		}
		
		result=curr;
		
		
		return result;
	}
	
	
	//------------------------------------------------------
    // cryport
    //
    // PURPOSE:    report information of a crypto currency.
    // PARAMETERS:
    //     
    //     String
    //     
    // Returns: void
    //------------------------------------------------------
	public void cryport(String symbol) {
		Node currency=foundInCryptoList(symbol);
		if(currency==null) {
			System.out.println("\nCurrency not found.\n");
		}
		else {
			System.out.println("\nCryptocurrency is: "+
		((Crypto)currency.getItem()).getName()+" "
		+((Crypto)currency.getItem()).getSymbol()+" "
		+"avaliable units is: "+
		((Crypto)currency.getItem()).getAmount()+"\n");
		}
		
		Node curr=cryptoList.getTop();
		while(curr.getLink()!=null) {
			if(((Crypto)curr.getItem()).getSymbol().equals(symbol))break;
			curr=curr.getLink();
		}
		
		List blockChainA=((Crypto)curr.getItem()).blockChain;
		//print each node in that blockchain now.
		Node curr1=blockChainA.getTop();
		while(curr1!=null) {
			if(curr1.getItem() instanceof Mine)
			System.out.println("\n"+((Mine)curr1.getItem()+"\n"));
			if(curr1.getItem() instanceof Trade)
			System.out.println("\n"+((Trade)curr1.getItem())+"\n");
            curr1=curr1.getLink();
		}
		
		verify(blockChainA);
		
		
		
		
		
		
		
		
	}
	
	
	//------------------------------------------------------
    // verify
    //
    // PURPOSE:  verify the hashcode if it is equal as when add a new mine or
	//trader object.     not done yet.
    // PARAMETERS:
    //     
    //     List
    //     
    // Returns: void
    //------------------------------------------------------
	public void verify(List blockChain) {
		
	}
	
	
	//------------------------------------------------------
    // report
    //
    // PURPOSE:   report the information of an investor.
    // PARAMETERS:
    //     
    //     String
    //     
    // Returns: void
    //------------------------------------------------------
	public void report1(String a) {
		
		if(this.investorList.getTop()==null) {
			System.out.println("\nNot found.\n");
		}
		
		else {boolean found=false;
		Node curr=investorList.getTop();
		while(curr!=null) {
			if(((Investor)curr.getItem()).getID().contentEquals(a)) {
				found=true;
				break;
			}
			curr=curr.getLink();
		}
		if(found) {
		List c=((Investor)curr.getItem()).portfolio;
		
		Node curra=c.getTop();
		System.out.println("\n"+((Investor)curr.getItem()).getID()+" :\n");
		while(curra!=null) {
			System.out.println((Crypto)curra.getItem());
			curra=curra.getLink();
		}
		}else {
			System.out.println("\nNot found.\n");
		}
		}
		
		
	}
	
	
	
	
	
}//end of WholeSystem class
