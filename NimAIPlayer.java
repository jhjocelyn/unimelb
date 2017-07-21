/*Project C
 Author: Hui Jiang
 */
import java.io.Serializable;
import java.util.Scanner;


public class NimAIPlayer extends NimPlayer implements Serializable{
	private static final long serialVersionUID=4025579485695886621L;
	
	@Override
	public int removeStone(int currentStone,int upperBound){
	    printStone(currentStone);
		System.out.println(this.getGivenName()+"'s turn - remove how many?\n");
		int originalStone = currentStone;
		int max= (currentStone-1)/(upperBound+1);      //the maximum value of k (starts with 0,1,2...)
		for(int k=0;k<=max;k++){
		    int leftStone = k*(upperBound+1)+1;       //the number of left stones = k*(upper bound+)+1
		    int stoneRemoved = currentStone-leftStone;   //the number of stone removed
			if((stoneRemoved)<=upperBound){             // if the number of stone removed is less than bound
				currentStone = leftStone;              // update number of current stones
				break;                                 // stop for loop
			}
		}
		if (currentStone==originalStone){            // if no strategy can be adopted
			currentStone--;                         // just remove one stone
		}  
		return currentStone;                       
	}
}
