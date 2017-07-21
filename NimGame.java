/*Project C
Author: Hui Jiang 
*/
public class NimGame{
	private int currentStone;
	private int upperBound;
    private NimPlayer player1;
    private NimPlayer player2;
    
    //method1: to play a game by knowing initial number of stones, upper bound stone of removal and two players.
    public void gamePlaying(int stoneNum, int upperStone, NimPlayer playerA, NimPlayer playerB){
    	
    	this.currentStone = stoneNum;
    	this.upperBound = upperStone;
    	this.player1 = playerA;
    	this.player2 = playerB;
    	
    	System.out.println();
    	System.out.println("Initial stone count: " + currentStone);
    	System.out.println("Maximum stone removal: " + upperBound);
    	System.out.println("Player 1: " + player1.getGivenName() + " " + player1.getFamilyName());
    	System.out.println("Player 2: " + player2.getGivenName() + " " + player2.getFamilyName());
    	System.out.println();
    	
    	while(currentStone>0){
        	currentStone = player1.removeStone(currentStone, upperBound);
    		if (currentStone > 0){
    			currentStone = player2.removeStone(currentStone, upperBound);
    				if (currentStone==0){
    					System.out.println("Game Over");//player 1 wins the game
    					System.out.println(player1.getGivenName()+" "+player1.getFamilyName()+" wins!");	 
    					player1.updateWin();
    					player1.updateGame();
    					player2.updateGame();
    					break;
    				}
    		}else{
    			System.out.println("Game Over");   //player2 wins the game
    			System.out.println(player2.getGivenName()+" "+player2.getFamilyName()+" wins!");
    			player2.updateWin();
    			player1.updateGame();
				player2.updateGame();
    		}	
    	}   
    }
    
   
    
}
    
    
	
   
	

