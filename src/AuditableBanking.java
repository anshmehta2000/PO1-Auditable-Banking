import java.util.Scanner;
public class AuditableBanking {

  /**
   * Adds a transaction group to an array of transaction groups. If the allTransactions array is
   * already full then this method will do nothing other than return allTransactionCount.
   * 
   * @param newTransactions is the new transaction group being added (perfect size).
   * @param allTransactions is the collection that newTransactions is being added to (oversize).
   * @param allTransactionsCount is the number of transaction groups within allTransactions 
   *        (before newTransactions is added.
   * @return the number of transaction groups within allTransactions after newTransactions is added.
   */
  public static int submitTransactions(int[] newTransactions, int[][] allTransactions,
      int allTransactionsCount) {
  allTransactions[allTransactionsCount] = newTransactions;
    return allTransactionsCount + 1;
  }
  
  public static int processCommand(String command, int[][] allTransactions, int allTransactionsCount) {
	if(allTransactionsCount == allTransactions.length ) {
		return -1;
	}
	command = command.trim();
	String[] commandArr = command.split(" ");
	int[] c = new int[commandArr.length ]; 
	for(int i = 0; i < commandArr.length; i++) {
		c[i] = Integer.parseInt(commandArr[i]);
	}
	if(c[0] < 0 || c[0] > 2) {
		return -1;
	}
	allTransactionsCount = submitTransactions(c, allTransactions, allTransactionsCount);
	if(command.toUpperCase().charAt(0) == 'B') {
		int balance = calculateCurrentBalance(allTransactions, allTransactionsCount);
		System.out.println("Your balance is " + balance + "$");
	}
	else if (command.toUpperCase().charAt(0) == 'O') {
		int overdrafts = calculateNumberOfOverdrafts(allTransactions, allTransactionsCount);
		System.out.println("Your number of overdrafts is "+ overdrafts + "$");
	}
	else if(command.toUpperCase().charAt(0) == 'Q') {
		System.out.println("============ Thank you for using this App!!!! ============");
		
		System.exit(0);
	}
    return allTransactionsCount;
  }
  public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
	  int balance = 0;
	  for (int i = 0; i < allTransactionsCount; i++) {
		  for (int j = 1; j <allTransactions[i].length; j++) {
			  if (allTransactions[i][0] == 0) {
			  
				  if (allTransactions[i][j] == 0)
					  balance--;
				  else
					  balance++;
			  }
		  
		      else if (allTransactions[i][0] == 1) {
		    	  balance += allTransactions[i][j];
		      }
		      else if (allTransactions[i][0] == 2) {
		    	  balance -= (20*allTransactions[i][1] + 40*allTransactions[i][2] + 80*allTransactions[i][3] + 100*allTransactions[i][4]);
		    	  break;
		      }
		      else
		    	  return -1;
	  }
	  }
	  return balance;
	}
  public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
	  int balance = 0;
	  int overdrafts = 0;
	  
	  for (int i = 0; i < allTransactionsCount; i++) {
		  for (int j = 1; j <allTransactions[i].length; j++) {
			  if (allTransactions[i][0] == 0) {
			  
				  if (allTransactions[i][j] == 0) {
					  balance--;
					  if (balance < 0)
						  overdrafts++;
				  }
				  else
					  balance++;
			  }
		  
		      else if (allTransactions[i][0] == 1) {
		    	  balance += allTransactions[i][j];
		    	  if (balance < 0)
		    		  overdrafts++;
		      }
		      else if (allTransactions[i][0] == 2) {
		    	  balance -= (20*allTransactions[i][1] + 40*allTransactions[i][2] + 80*allTransactions[i][3] + 100*allTransactions[i][4]);
		    	  if (balance < 0)
		    		  overdrafts++;
		    	  break;
		      }
		      else
		    	  return -1;
	  }
	  }
	  return overdrafts;
	}
  public static int main(String[] args) {
	  int allTransactionsCount = 0;
	  int[][] allTransactions = new int[200][];
	  System.out.println("========== Welcome to the Auditable Banking App ==========");
	  while(true) {
		  System.out.print("COMMAND MENU:\n" + 
		  		"  Submit a Transaction (enter sequence of integers separated by spaces)\n" + 
		  		"  Show Current [B]alance\n" + 
		  		"  Show Number of [O]verdrafts\n" + 
		  		"  [Q]uit Program\n" + 
		  		"ENTER COMMAND: ");
		  Scanner scnr = new Scanner(System.in);
		  allTransactionsCount = processCommand(scnr.next(), allTransactions, allTransactionsCount);
		  scnr.close();
	  }
	  
  }
}
  
