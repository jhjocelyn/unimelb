/*Project C
 Author: Hui Jiang
 */
import java.io.Serializable;
import java.util.Scanner;
public class NimHumanPlayer extends NimPlayer implements Serializable {
	
	
	@Override
	public int removeStone(int currentStone,int upperBound){ // to remove stone, upper bound and number of stones must be given
        boolean done =false;
        int numOfRemove=0;
        while(!done){           //if no valid removal, continue the loop
        	try{
        		printStone(currentStone);
        		System.out.println(this.getGivenName()+"'s turn - remove how many?");
        		String numOfRemoval=Nimsys.sc.nextLine();
        		numOfRemove = Integer.parseInt(numOfRemoval);
                System.out.println();
                int upper= Math.min(upperBound,currentStone);
            		if(numOfRemove>upper||numOfRemove==0){ // error occurs if number of stones to remove is greater than the upper bound
            			throw new Exception("Invalid move. You must remove between 1 and " + upper + " stones.\n");		
            		}else{
            			done = true;
            		}
        	}catch(Exception e){
    			System.out.println(e.getMessage());
    		}
        }
        return currentStone-=numOfRemove; 
	}
}
