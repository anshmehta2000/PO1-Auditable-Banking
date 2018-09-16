import java.util.Arrays;
public class AuditableBankingTests {
	public static boolean testProcessCommand() {
		String command1 = "0 1 0 1 1 1 0 1";
		String command2 = "1 -2 3 0 -10 ";
		
		int[][] allTransactions = new int[100][];
		allTransactions[0] = new int[] {0,1,1,1};
		allTransactions[1] = new int[] {2,1,2,1,0};
		if(AuditableBanking.processCommand(command1, allTransactions, 2) == 3 && AuditableBanking.processCommand(command2, allTransactions, 3) == 4) {
			if(allTransactions[2][0] == 0 && allTransactions[2][1] == 1 && allTransactions[3][0] == 1 && allTransactions[3][1] == -2) {
					System.out.println("First Transaction succesfully found to be " + allTransactions[2][1]);
					System.out.println("Second Transaction succesfully found to be " + allTransactions[3][1]);
					return true;
			}
		}
		else {
			System.out.println("Error");
			return false;
		}
			
		
		
		return false;
	}
	public static boolean testCalculateCurrentBalance() {
		  int [][] allTransactions = {{0, 1, 0, 1, 1, 1, 0},
				  					  {1, 200, -5, 12, 2, -1},
				  					  {2, 1, 0, 1, 1}
				                     };
		  if (AuditableBanking.calculateCurrentBalance(allTransactions, 3) == 10) {
			  System.out.println("Balance was correctly found to be 10");
			  return true;
		  }
		  else
			  System.out.println("Error");
		  return false;
		}
	public static boolean testCalculateNumberOfOverdrafts() {
		  boolean foundProblem = false;
		  int[][] transactions = new int[][] {
		    {1,10,-20,+30,-20,-20}, // +2 overdrafts (ending balance:  -20)
		    {0,1,1,1,0,0,1,1,1,1},  // +2 overdrafts (ending balance:  -15)
		    {1,115},                // +0 overdrafts (ending balance: +100)
		    {2,3,1,0,1},            // +1 overdrafts (ending balance: -100)
		  };
		  
		  // test with a single transaction of the Integer Amount encoding
		  int transactionCount = 1;    
		  int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		  if(overdrafts != 2) {
		    System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: "+Arrays.deepToString(transactions));
		    foundProblem = true;
		  } else
		    System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");
		 
		  // test with four transactions: including one of each encoding
		  transactionCount = 4;
		  overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		  if(overdrafts != 5) {
		    System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: "+Arrays.deepToString(transactions));
		    foundProblem = true;
		  } else
		    System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");
		  
		  return !foundProblem;
		}
	
}