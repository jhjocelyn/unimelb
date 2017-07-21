/*Project C
 Author: Hui Jiang
 */
import java.util.*;
import java.text.DecimalFormat;  
import java.lang.ArrayIndexOutOfBoundsException;
import java.io.*;

public class Nimsys {
	static Scanner sc = new Scanner(System.in);
	public final static int maximum = 100;  //the maximum number of players is 100
	private NimPlayer[] players=new NimPlayer[maximum];
	

	//method1: execute corresponding methods by different commands
	public void CMDLine(){

		System.out.println();
		System.out.print("$");
		String command = sc.nextLine();
		String kw = getKeyword(command);
	    String[] paras=getParameters(command);
	    
	   
		try{
			if(kw.equals("addplayer")){
				addPlayer(paras[0],paras[1],paras[2],"human");
			}else if(kw.equals("addaiplayer")){
				addPlayer(paras[0],paras[1],paras[2],"AI");
			}else if(kw.equals("removeplayer")){
				if (paras==null){
					removeAllPlayers();
					
				}else{
					removePlayer(paras[0]);
				}
			}else if(kw.equals("editplayer")){
				editPlayer(paras[0],paras[1],paras[2]);
			}else if(kw.equals("resetstats")){
				if (paras==null){
					resetAllStats();
				}else{
					resetStats(paras[0]);
				}
			}else if(kw.equals("displayplayer")){
				if (paras==null){
					displayAllPlayer();
				}else{
					displayPlayer(paras[0]);
				}
			}else if(kw.equals("rankings")){
				if (paras==null){
					ranking("desc");
				}else{
					ranking(paras[0]);
				}
			}else if(kw.equals("startgame")){
				startGame(paras[0],paras[1],paras[2],paras[3]);
			}else if(kw.equals("exit")){
				writeData();
				System.out.println();
				System.exit(0);
			}else{
				throw new IOException("'"+kw+"'"+" is not a valid command.");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Incorrect number of arguments supplied to command.");
	
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	//method2: to capture the keyword of the command
	private String getKeyword(String input){
		return (input.split(" ")[0]);
	}
	
	//method3: to capture the parameters of the command
	private String[] getParameters(String input){
		String[] paras;
		String[] cut = input.split(" ");
        if (cut.length==2){
        	return paras = cut[1].split(",");
        }else{
        	return paras=null;
        }
	}
	
	//method4: to find the index behind the last user
	private int findTail(){
		if (players==null){
			return 0;
		}else{
			updatePlayers();
			int tail=0;
			for (int index=0;index<players.length;index++){
				if (players[index]==null){
					tail=index;
					break;
				}
			}
		return tail;
		}
		
	}
	
	//method5:to check the Existences of users
	private boolean userExistsOrNot(String userName){
		for (int index=0;index<findTail();index++){
			if (userName.equals(players[index].getUserName())){
				return true;
			}
		}
		return false;
	}
	
	//method6: to find the user's index in the array
	private int findUser(String userName){
		int index;
		for (index=0;index<findTail();index++){
			if (userName.equals(players[index].getUserName())){
				break;
			}
		}
		return index;
	}
	
	//method7: to delete the removed users and maintain the consistency of the array
	private void updatePlayers(){
		for (int i=0;i<99;i++){
			for (int j=i+1;j<100;j++){
				if (players[i]==null && players[j]!=null){
					players[i]=players[j];
					players[j]=null;
				}
	        }
		}
	}
	
	//function 1------add players
	public void addPlayer(String userName, String familyName, String givenName,String type){
		if (!userExistsOrNot(userName)){
			int index=findTail();
			if (index>maximum){
				System.out.println("Error! The maximum number of players is 100.");
			}else{
				if (type.equals("human")){
					players[index]=new NimHumanPlayer();
				}else{
					players[index]=new NimAIPlayer();
				}
				players[index].setName(userName, familyName, givenName);
				players[index].resetGame();
				players[index].resetWin();
			}
		}else{
			System.out.println("The player already exists.");
		}
	}
	
	
	//function 2-1------remove specific player
	public void removePlayer(String userName){
			if (userExistsOrNot(userName)){
				int index = findUser(userName);
				players[index]=null;	
			}else{
				System.out.println("The player does not exist.");
			}
			updatePlayers();
		}
		
	//function 2-2------remove all players
	public void removeAllPlayers(){
		System.out.println("Are you sure you want to remove all players? (y/n)");
		String anwser = sc.nextLine();
		if (anwser.equals("y")){
			//for (int index=0;index<findTail();index++){
			   for(int i=0;i<maximum;i++){
			       players[i]=null;
			   }
			//}
		}
	}
	
	//function 3------edit player
	public void editPlayer(String userName,String newFamilyName, String newGivenName){
		if(userExistsOrNot(userName)){
			int index = findUser(userName);
			players[index].setName(userName, newFamilyName, newGivenName);
		}else{
			System.out.println("The player does not exist.");
		}
	}
	
	//function 4-1: reset a specific statistics
	public void resetStats(String userName){
		if(userExistsOrNot(userName)){
			int index=findUser(userName);
			players[index].resetGame();
			players[index].resetWin();
		}else{
			System.out.println("The player does not exist.");
		}
	}
	
	//function 4-2: reset all users'statistics
	public void resetAllStats(){
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		String answer=sc.nextLine();
		if (answer.equals("y")){
			for(int index=0; index<findTail();index++){
				players[index].resetGame();
				players[index].resetWin();
			}
		}else{
			CMDLine();
		}
	}
	
	//function 5-1: display specific player
	public void displayPlayer(String userName){
		if(userExistsOrNot(userName)){
			int index = findUser(userName);
			System.out.println(players[index].getUserName() + "," + players[index].getGivenName() +","
					+ players[index].getFamilyName() + "," + players[index].getNumOfGame() + " games," +
					players[index].getNumOfWin() + " wins"); 
		}else{
			System.out.println("The player does not exist.");
		}
	}
	
	//function 5-2: display all players alphabetically
	public void displayAllPlayer(){
		String [] alphabetic = new String[findTail()];
		for(int index=0;index<findTail();index++){
			alphabetic[index]=players[index].getUserName();
		}
		Arrays.sort(alphabetic);
		for (int i=0;i<findTail();i++){
			int index=findUser(alphabetic[i]);
			System.out.println(players[index].getUserName() + "," + players[index].getGivenName() +","
					+ players[index].getFamilyName() + "," + players[index].getNumOfGame() + " games," +
					players[index].getNumOfWin() + " wins"); 
		}	
	}

	//function 6: rankings
	public void ranking(String order){
		if (order.equals("desc")){
			rankDESC();
			printOutOrder();
		}else if (order.equals("asc")){
			rankASC();
			printOutOrder();
		}
	}
	
	//function6-1: ranking in descending order
	private void rankDESC(){
		for (int i=0;i<findTail()-1;i++){
			for (int j=i+1;j<findTail();j++){
				if (players[i].getWinRatio()<players[j].getWinRatio()){
					NimPlayer tempt = players[i];
					players[i]=players[j];
					players[j]=tempt;
				}else if (players[i].getWinRatio()==players[j].getWinRatio()){
					String[] names ={players[i].getUserName(),players[j].getUserName()};
					Arrays.sort(names);
					if(!names[0].equals(players[i].getUserName())){
						NimPlayer tempt = players[i];
						players[i]=players[findUser(names[0])];
					    players[j]=tempt;
					  }					
				}
			}		
	    }	
	}
	
	//function6-2: ranking in ascending order
	private void rankASC(){
		for (int i=0;i<findTail()-1;i++){
			for (int j=i+1;j<findTail();j++){
				if (players[j].getWinRatio()<players[i].getWinRatio()){
					NimPlayer tempt = players[i];
					players[i]=players[j];
					players[j]=tempt;
				}else if(players[i].getWinRatio()==players[j].getWinRatio()){
					String[] names ={players[i].getUserName(),players[j].getUserName()};
					Arrays.sort(names);
					if(!names[0].equals(players[i].getUserName())){
						NimPlayer tempt = players[i];
						players[i]=players[findUser(names[0])];
					    players[j]=tempt;
				    }
				}
			}		
	    }
	}

	
	
	//function6-3: print out no more than 10 players
	private void printOutOrder(){
		int lastOne=0;
		if (findTail()>10){
			lastOne=10;
		}else{
			lastOne=findTail();
		}
		for (int index=0;index<lastOne;index++){
			int winRatio = (int) Math.round(players[index].getWinRatio());
			String wr=String.valueOf(winRatio)+"%";
			String fullName=players[index].getGivenName()+" "+players[index].getFamilyName();
			String numOfGame = new DecimalFormat("00").format(players[index].getNumOfGame());
			System.out.printf("%-5s| %s games | %s\n",wr,numOfGame,fullName);
		}
	}
	
	
	//function 7: start a game
	public void startGame(String numOfStone, String upperStone , String userName1, String userName2){
		
		int currentStone=Integer.parseInt(numOfStone);
		int upperBound=Integer.parseInt(upperStone);
		
		int index1=findUser(userName1);
		int index2= findUser(userName2);
		
		boolean isPlayer1Exits=userExistsOrNot(userName1);
		boolean isPlayer2Exits=userExistsOrNot(userName2);
		
		if(isPlayer1Exits&isPlayer2Exits){
			NimGame game = new NimGame();
			game.gamePlaying(currentStone,upperBound, players[index1], players[index2]);
			//String str = sc.nextLine();  //this line is used to receive the "/n" caused by NextInt(). =_=...
		}else if (!(isPlayer1Exits)&!(isPlayer2Exits)){
			System.out.println("Both of the players do not exist.");
		}else{
			System.out.println("One of the players does not exist.");
		}
	}
	
	public void writeData(){
		try{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("players.dat"));
			outputStream.writeObject(players);
			outputStream.close();
		}catch(IOException e){
			System.out.println("Error writing to file");
			System.exit(0);
		}
	}
	
	public void readData(){
		try{
			File fileObject = new File("players.dat");
			if (fileObject.exists()){
				ObjectInputStream inputstream = new ObjectInputStream(new FileInputStream(fileObject));
				players = (NimPlayer[])inputstream.readObject();
				inputstream.close();
			}
		}catch(ClassNotFoundException e){
			System.out.println("Problem with file input");		
			System.exit(0);
		}catch(IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		    System.exit(0);
		}
	}
	
	

	// the entrance of the whole program
	public static void main(String[] args){
		Nimsys nimsys = new Nimsys();
		System.out.println("Welcome to Nim");
		nimsys.readData();
		while(true){
			nimsys.CMDLine();
		}
	}
}







