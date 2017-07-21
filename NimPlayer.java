/*Project C
 Author: Hui Jiang
 */
import java.util.Scanner;
import java.io.Serializable;
import java.math.*;

public abstract class NimPlayer implements Serializable{
	private static final long serialVersionUID = 819127835977590228L;// to avoid local class incompatible exception
	private String userName;
	private String givenName;
	private String familyName;
	private float winRatio;
	private int numOfGame;
	private int numOfWin;

	//getters
	public String getUserName(){
		return this.userName;	
	}
	
	public String getGivenName(){
		return this.givenName;
	}
	
	public String getFamilyName(){
		return this.familyName;
	}
	
	public int getNumOfGame(){
		return numOfGame;
	}
	
	public int getNumOfWin(){
		return numOfWin;
	} 
	
	public float getWinRatio(){
    	if (getNumOfGame()==0){
    		return 0;
    	}else{
    	float wins = getNumOfWin();
    	float games = getNumOfGame();
    	float winRatio=(wins/games)*100;
    	return winRatio;
    	}
    }
	
	//setter
	public void setName(String userName, String newFamilyName, String newGivenName){
		this.userName = userName;
		this.familyName = newFamilyName;
		this.givenName = newGivenName;
	}
	public void setGame(int numOfGame){
		this.numOfGame = numOfGame;
	}
	public void setWin(int numOfWin){
		this.numOfWin = numOfWin;
	}
		
	//update the number of games and number of wins
	public void updateGame(){
		this.numOfGame++;
	}
	
    public void updateWin(){
    	this.numOfWin++;
    }
    
    //reset the the number of games and number of wins
    public void resetGame(){
    	this.numOfGame = 0;
    }
    public void resetWin(){
    	this.numOfGame = 0;
    }
    
    public void printStone(int stoneNum){
		System.out.print(stoneNum + " stones left:");
		for (int i=0;i<stoneNum;i++){
			System.out.print(" *");
		}
		System.out.println();
	}
    
    //method1: to remove a valid number of stones
    public abstract int removeStone(int currentStone,int upperBound);
    
	
}







