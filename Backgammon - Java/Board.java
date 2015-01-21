import java.util.ArrayList;
import java.util.Random;

/*** @author Asma Almaksy, Assem Tursyngaliyeva  ,Akmaral Akhanova
 * Student Number: 11364941 - 12254874  - 12250343
 *
 */
public class Board {
	final char Black = 'B';
	final char White = 'W';
	private Pip[] pips;  
	private String [] Bar;    
	private String [] OFF ;
	char[] ErrorStatus;
	int turnCounter= 0;

	public Board(){
		this.pips = new Pip [25];  
		this.Bar =  new String[3];    
		this.OFF =  new String[3];
	}
	//Initialises board and displays it
	public void initialiseBoard(){
		for( int k = 0; k < Bar.length ; k++ ){
			Bar[k]="BAR";
			OFF[k]="";
		}
		for( int i = 1; i < pips.length ; i++ ){	
			if(i==1) pips[i] = new Pip('W',2);
			else if(i==6) pips[i]=new Pip('B',5);
			else if(i==8) pips[i]=new Pip('B',3);
			else if(i==12)pips[i]=new Pip('W',5);
			else if(i==13)pips[i]=new Pip('B',5);
			else if(i==17)pips[i]=new Pip('W',3);
			else if(i==19)pips[i]=new Pip('W',5);
			else if(i==24)pips[i]=new Pip('B',2);
			else pips[i]=new Pip();
		}
		turnCounter = 0 ;
	}
	public void DisplayBoard(char player){
		if(player==Black)DisplayBlackPerspective();
		else if(player==White)DisplayWhitePerspective();
		else DisplayBlackPerspective();
	}
	public void DisplayBlackPerspective(){
		System.out.println();
		displayNumbers(13,25);     	  //Displays the board numbers
		System.out.println();
		for( int i = 13 ; i < 25 ; i++){
			if(i == 19) System.out.print("\t"+Bar[0]+"\t\t");
			pips[i].printContent();
			System.out.print("\t");
			if(i == 24) System.out.print(OFF[0]);
		}
		System.out.println();
		for(int i = 12;i > 0;i--){
			if(i == 6) System.out.print("\t"+Bar[1]+"\t\t");
			pips[i].printContent();
			System.out.print("\t");
			if(i==1) System.out.print(OFF[1]);
		}
		System.out.println();
		displayNumbers(12,1);	           //Displays the board numbers
		System.out.println();
		System.out.println("Quit to quit \tPass to pass \tPipnumber-Dicevalue  Pipnumber-Dicevalue\n0 to move bar ");
	}
	public void DisplayWhitePerspective(){
		System.out.println();
		displayNumbers(12,1);	           //Displays the board numbers
		System.out.println();
		for(int i = 12;i > 0;i--){
			if(i == 6) System.out.print("\t"+Bar[1]+"\t\t");
			pips[i].printContent();
			System.out.print("\t");
			if(i==1) System.out.print(OFF[1]);
		}
		System.out.println();
		for( int i = 13 ; i < 25 ; i++){
			if(i == 19) System.out.print("\t"+Bar[0]+"\t\t");
			pips[i].printContent();
			System.out.print("\t");
			if(i == 24) System.out.print(OFF[0]);
		}
		System.out.println();
		displayNumbers(13,25);     	  //Displays the board numbers
		System.out.println();
		System.out.println("Quit to quit \tPass to pass \tPipnumber-Dicevalue  Pipnumber-Dicevalue");
	}
	public ArrayList<PossibleMoves[]> findPossiblemoves(int[] dice ,char player){
		PossibleMoves [] moves,moves2;             
		Pip[] p = pips;
		moves = possibleMoves(dice[0],player); 	//stores possible moves for first dice value
		moves2 = possibleMoves(dice[1],player); 	//stores possible moves for first dice value
		ArrayList<PossibleMoves[]> y = new ArrayList<PossibleMoves[]>() ;
		y.add(moves);                            //adds first set using dice one value of possible moves
		for(int i=0;i<moves.length;i++){
			Board temp = new Board();
			temp.initialiseBoard();
			temp.pips= p ;
			y.add( temp.possibleMoves(dice[1] , player));    //adds possible moves for combination of a move from dice[0]  with dice[1]
		}
		Board [] temp2 = new Board[moves2.length];
		y.add(moves2);         //adds second dice value moves onto arraylist
		for(int i=0;i<moves2.length;i++){
			temp2[i] = new Board();
			temp2[i].initialiseBoard();
			temp2[i].pips = this.pips; 
			y.add( temp2[i].possibleMoves(dice[0] , player)) ;
		}
		//Remove duplicates
		for(int i=0;i<	y.size();i++)	{
			for(int j = 0; j < y.get(i).length;j++){
				for(int h = (i+1) ; h < y.size() ; h++){
					for(int k = 0 ; k < y.get(h).length;k++){
						if(	y.get(i)[j].to == y.get(h)[k].to && 	y.get(i)[j].from  == y.get(h)[k].from ){
							y.get(h)[k].removeMove(); //removes duplicates
						}
					}
				}
			}
		}
		//Print moves
		/*
		for(int i=0;i<y.size();i++){
			for(int h=0;h<y.get(i).length;h++){
				if(y.get(i)[h].moveStatus == true){ //if the move was not removed 
					y.get(i)[h].printMove();  //if move is still stored in the element in the arraylist
				}
			}
		}
		System.out.println();*/
		return y;
	}
 	public void displayNumbers(int initial,int limit){
		if( limit == 25 ){
			for( int i = initial; i < limit ; i++){
				if(i==19) System.out.print("\tBAR\t\t");
				System.out.print(i+"\t");
				if(i==24) System.out.print("OFF");
			}
		}
		else
			for(int i = initial ; i >= limit ; i--){
				if( i < 10 ){
					if( i == 6 ) System.out.print("\tBAR\t\t");
					System.out.print("0"+i+"\t");
					if( i == 1 ) System.out.print("OFF\t");
				}
				else System.out.print(i+"\t");
			}
	}
	//Takes Dice and Positions to play and checks if they are valid
	public boolean moveChecker(char player,int [] dice,int [] positions){	
		if(positions[0] == 'p'){   					// if the player passes
			return true;
		}
		else  if( positions[0] == 'q' || positions[0]=='Q' )  return true;             //if the player quits
		else if(dice.length>4 ){
			if(positions[1]==dice[0]&&positions[3]==dice[0] && positions[5]==dice[0] && positions[7]==dice[0] ){
				return makeMove(player,dice,positions);
			}
			else return false;
		}
		else if( (  positions[1]==dice[0] && positions[3]==dice[1]   )   ||   (  positions[1] == dice[1] &&  positions[3]==dice[0]   ) ){
			return makeMove(player,dice,positions);
		}
		else return false;
	}

	//checks if there are opponent's checkers on home board and counts 
	public boolean oppositeBoardCheck (char player2) { 
		int checkerCounter = 0;
		int initial = 0 ,  limit = 0; 
		if(player2 == White){
			initial = 1;
			limit = 7;
		}
		else if(player2 == Black){
			initial = 19;
			limit =25;
		}
		for(int i = initial; i < limit ; i++){
			if(pips[i].getPlayer() == player2  &&  pips[i].getCheckerNumber() > 0 )      checkerCounter +=pips[i].getCheckerNumber();
			if(checkerCounter == 15) {
				System.out.println("you opponent has checkers on your home board!");
				return true;
			}
		}
		return false;
	}

 	public boolean bearOffCheck( char player,int fromPosition){
 		if(player==Black){
 			for(int i= 6; i > fromPosition;i--){  //loops for all the pips that are bigger than the fromPoition 
 				if(pips[i].checkerNumber>0){//Means you have to move this checker
 					return false;
 				}
 			}
 			return true;
	 	}
 		else{
 			for(int i=19;i<fromPosition;i++){  //loops for all the pips that are bigger than the fromPoition 
 				if(pips[i].checkerNumber>0){//Means you have to move this checker
 					return false;
 				}
 			}
 			return true;
 		}
 
 	}

	public boolean makeMove(char player, int [] dice, int [] positions){
		int [] move = new int [2] ;
		if(dice.length==4){
			 return makeDoubleMove( player,dice, positions);
		}
		else if( player == Black ){
			move[0] = positions[0] - positions[1];  
			if(positions[2]!=0){
				move[1] = positions[2] - positions[3];
			}
		}
		else{
			move[0] = positions[0] + positions[1];  
			if(positions[2]!=0){
				move[1] = positions[2] + positions[3];
			}
		}
		boolean [] validMoves = new boolean[2];
		int valid=0;
		if(positions[2]!=0){
			Board temp = new Board();
			temp.initialiseBoard();
			temp.pips = pips;
			for(int i = 0;i<move.length;i++){ //checks moves and makes them on temp board
				validMoves[i] = temp.checkPosition(positions[i*2],move[i],player);  //checks if move is valid
				if(validMoves[i]==true){ 
					temp.addTo(positions[i*2],move[i],player); //makes the move
					valid++;      //counter for how many moves are valid
				}
				else{
					System.out.println("Moving "+positions[i*2]+" to " +move[i] +" - was not successful");
					//return false;
				}
			}
			if(valid == (validMoves.length-1)){          //if all the moves are valid
				for(int k=0;k<move.length;k++){
					addTo(positions[k*2],move[k],player);  //make them on real board
				}
			}
			return true;
		}

		return false;
	}
	//Checks if the player can move the checker he wants to move and the to position
	public boolean checkPosition(int fromPosition,int toPosition,char player){
		if(BarChecker(player)==true && fromPosition==0){
			if(player==Black){
				int dice = fromPosition - toPosition;
				if(pips[25-dice].getPlayer()==player ||pips[25-dice].getCheckerNumber()==1 ||pips[25-dice].getPlayer()==0){
					return true;
				}
				else return false;
			}
			else {
				int dice = fromPosition + toPosition;
				if(pips[dice].getPlayer()==player ||pips[dice].getCheckerNumber()==1 ||pips[dice].getPlayer()==0){
					return true;
				}
				else return false;
			}
		}
		else if  (pips[ fromPosition].getPlayer() == player)  {
			if (  toPosition > 24  || toPosition <=0){
				if(bearOffCheck(player,fromPosition)) return true;
				else return false;
			}
			else if(pips[ toPosition ].getPlayer() == player   ||   pips[ toPosition ].getCheckerNumber()== 0 ||   pips[ toPosition ].getCheckerNumber() == 1  ){
				if(BarChecker(player)==true){
					System.out.println("You have a checker on the bar.");
					return false;
				}
				else return true;
			}
			else {
				System.out.println("Invalid move for you:"+ player);
				return false ;
			}
		}
		else {
			System.out.println("\nYou can't  move this checker player: "+player);
			return false;
		}
	}
	public boolean makeDoubleMove(char player,int []dice,int [] positions){
		int[] move = new int [4];
		if(player == Black){
			move[0] = positions[0] - positions[1];
			move[1] = positions[2] - positions[3];
			move[2] = positions[4] - positions[5] ;
			move[3] = positions[6] - positions[7];
		}
		else if ( player == White ){
			move[0] = positions[0] + positions[1];  
			move[1] = positions[2] + positions[3];
			move[2] = positions[4] + positions[5] ;
			move[3] = positions[6] + positions[7];
		}
		boolean [] validMoves = new boolean[4];
		Board temp = new Board();
		temp.initialiseBoard();
		temp.pips = pips;
		int valid =0;
		for(int i = 0;i<move.length;i++){
			validMoves[i] = temp.checkPosition(positions[i*2],move[i],player);
			if(validMoves[i]==true){
				temp.addTo(positions[i*2],move[i],player );
				valid++;
			}
			else{
				System.out.println("Moving "+positions[i*2]+" to " +move[i] +" - was not successful");
				//return false;
			}
		}
		if(valid == validMoves.length){
			for(int k=0 ; k<move.length;k++){
				addTo(positions[k*2],move[k],player );
			}
		}
		return true ;
}
	//Takes 2 variables and the  player name and makes the moves
	public void makeMoves(int from,int to,char player){
		int i;
		if(player == Black) i = 0;
		else i = 1;
	
		//If the other player is here and has one checker
		if (  pips[ to ].getCheckerNumber() == 1   &&   pips[ to ].getPlayer() != player   ){
			pips[from].removeChecker() ;     							  //remove 1 checker from pip
			if ( !Bar[i].equalsIgnoreCase( "BAR" )   ){ 					//Check if that players bar is not empty
				String temp = Bar[i].substring(1)  ;       					//get int value
				int j   =  Integer.parseInt(  temp  ) ;        
				j++;                                                                  				//Increase if he already has a checker on the bar
				if(player ==Black)	{
					Bar[0] = "W" + j;                 	//Add that players name with the number of checkers
				}
				else {
					Bar[1] = "B" + j;                                        		 			//Add that players name with the number of checkers
				}
			}
			else{        									//Else if Bar is empty   add the players name and one checker
				if(player ==Black)	{	
					Bar[0]  =  "W" + 1; 
				}              
				else{ 
					Bar[1] = "B" + 1;                              
				}    		 		
			}
			pips[to].removeChecker();
			pips[to].changePlayer(player);					 //add player name
		    pips[to].addChecker(1) ;          					 //add a checker
		}
		else {
			if(from!=0)	pips[from].removeChecker() ;     						  //remove 1 checker from pip
			pips[to].changePlayer(player);					 //add player name
		    pips[to].addChecker(1) ;          					 //add a checker
		}
	}
	public int moveCheckerOffBar(char player,int to,int from){
		if(player==Black){
			int dice= -to ;
			if ( !Bar[1].equalsIgnoreCase( "BAR" )   ){ 					//Check if that players bar is not empty
				String temp = Bar[1].substring(1)  ;       					//get int value
				int j   =  Integer.parseInt(  temp  ) ;        
				j--;                                                                  				//Increase if he already has a checker on the bar
				if(j==0)	{
					Bar[1] = "BAR";                 	//Add that players name with the number of checkers
				}
				else {
					Bar[1] = "B" + j;                                        		 			//Add that players name with the number of checkers
				}
			}
			return 25-dice;
		}
		else {
			int dice = to;
			if ( !Bar[0].equalsIgnoreCase( "BAR" )   ){ 					//Check if that players bar is not empty
				String temp = Bar[0].substring(1)  ;       					//get int value
				int j   =  Integer.parseInt(  temp  ) ;        
				j--;                                                                  				//Increase if he already has a checker on the bar
				if(j==0)	{
					Bar[0] = "BAR";                 	//Add that players name with the number of checkers
				}
				else {
					Bar[0] = "B" + j;                                        		 			//Add that players name with the number of checkers
				}
			}
			return dice;
		}
	}
	private void addTo( int from,  int to, char player ){
		if(from == 0)to = moveCheckerOffBar(player,to,from);
		if( to < 25  &&  to > 0  ) makeMoves(from,to,player);
		//if the player is bearing off
		else if((to >= 25 || to <=  0  ) &&  countCheckerAtHome(player)){
			if(bearOffCheck (player,from)){
					pips[from].removeChecker();              //remove checker from the position
					if(player==Black){
						if(OFF[1]!=""){                                         //If this players off is not empty
							String temp = OFF[1].substring(1);   //Get the int part
							int i = Integer.parseInt(temp);
							i++;                                                     //Increase   checkers
							OFF[1] = "B" + i;                                  //Add player name and number of checkers
						}
						else OFF[1] = "B"+ 1;                         //Otherwise its empty so we add just one checker
					}
					else if(player==White){                         
						if(OFF[0]!=""){
							String temp = OFF[0].substring(1);
							int i = Integer.parseInt(temp); 
							i++;
							OFF[0] = "W" + i;                                  //Add player name and number of checkers
						}
						else OFF[0] = "W" + 1;                             //Otherwise its empty so we add just one checker
					}
			}
		}
	}
	public boolean BarChecker(char player){
		int i ;
		if(player==Black)i =1;
		else i = 0;
		if(Bar[i].equals("BAR"))return false;
		else return Character.getNumericValue(Bar[i].charAt(1)) > 0 ?true :false ;
	}
	//returns true if all the players checkers are home
	public boolean countCheckerAtHome(char player) {
		int checkerCounter = 0;
		int initial = 0 ,  limit = 0; 
		if(player == White){
			initial = 19;
			limit = 25;
		}
		else if(player == Black){
			initial = 1;
			limit =7;
		}
		for(int i = initial; i < limit ; i++){
			if(pips[i].getPlayer() == player  &&  pips[i].getCheckerNumber() > 0 )      checkerCounter +=pips[i].getCheckerNumber();
			if(checkerCounter == 15) {
				System.out.println("All checkers are home! YOU CAN START BEARING OFF! ");
				return true;
			}
		}
		return false;
	}
	//If a player bears off all his checkers
	public boolean gameOver(char player){
		int i=0;
		//int j=1;
		char player2 = Black;
		if(player==Black) {
			i=1;
			//j=0;
			player2 = White;
		}
		if(OFF[i] !="") {
			if( Integer.parseInt( OFF[i].substring(1) ) == 15 ){ 
				//checks if there are opponent's checkers on home board and there are opponent's checkers on the bar 
				if(oppositeBoardCheck(player2) == true && BarChecker(player2) == true){
					System.out.println("A BACKGAMMON!!!");
				}
				//checks if there are opponent's checkers on home board only
				else if(countCheckerAtHome(player2) != true) {
					System.out.println("A GAMMON!!!");
				}
					//otherwise counts as a single game
				else System.out.println("A SINGLE!!!");
					
			}
			else return false;
				
		}
		else return false;
	return false;		
}
	public PossibleMoves[] possibleMoves(int diceVal,char player){
 		PossibleMoves[] p = new PossibleMoves[100] ;
 		int j=0;
 		if(player==Black){
	 		for(int i=0;i<pips.length;i++){
	 	 		//System.out.print(i+" ");
	 			if(i == 0){               //if player is moving pip from the bar
	 				if(BarChecker(player)==true){
	 					if(pips[25-diceVal].getPlayer()==player || pips[25-diceVal].getCheckerNumber()==1 || pips[25-diceVal].getCheckerNumber()==0){
	 						p[j] = new PossibleMoves(i,25-diceVal,diceVal,player);
	 						j++;
	 						//return true; 
	 					}
	 				}
	 			}
	 			else if(pips[i].getPlayer()==player){   //if the pip has this players checkers 
	 	 			if(i-diceVal>0 ){            //if the position we are moving to is greater than 0 ie on the board
	 	 				if(pips[i-diceVal].getPlayer()==player || pips[i-diceVal].getCheckerNumber()==1 || pips[i-diceVal].getCheckerNumber()==0){
	 	 					p[j] = new PossibleMoves(i,i-diceVal,diceVal,player);
		 					j++;
	 	 					//return true;
	 	 				}
	 	 			}
	 	 			else if(i-diceVal<=0){      //if the position we are moving to is off the board i.e bearing off
	 	 				if(countCheckerAtHome(player)==true){     //if all checkers are in inner board
	 	 					p[j] = new PossibleMoves(i,i-diceVal,diceVal,player);
		 					j++;
	 	 				}
	 	 			}
	 			}
	 		}
	 		PossibleMoves[] m = new PossibleMoves[j]; 
 			//System.out.println("Possible moves for dice :" +diceVal +"\nNumber of moves:"+j);
	 		for(int i=0;i<j;i++){
	 			m[i] = p[i];
	 			//m[i].printMove();
	 		}
	 		return m;
 		}
 		else{
	 		for(int i=1;i<pips.length;i++){
	 			if(i == 0){               //if player is moving pip from the bar
	 				if(BarChecker(player)==true){              //if he has checker on bar
	 					if(pips[diceVal].getPlayer()==player || pips[diceVal].getCheckerNumber()==1 || pips[diceVal].getCheckerNumber()==0){
	 						p[j] = new PossibleMoves(i,diceVal,diceVal,player); //store move
	 						j++;
	 					}
	 				}
	 			}
	 			
	 			else if(pips[i].getPlayer()==player){
	 	 			if(i+diceVal<25 ){
	 	 				if(pips[i+diceVal].getPlayer()==player || pips[i+diceVal].getCheckerNumber()==1 || pips[i+diceVal].getCheckerNumber()==0){
	 	 					p[j] = new PossibleMoves(i,i+diceVal,diceVal,player);
		 					j++;
	 	 				}
	 	 			}
	 	 			else{            //if the position we are moving to is not on the board
	 	 				if(countCheckerAtHome(player)==true){     //if all checkers are in inner board
	 	 					p[j] = new PossibleMoves(i,i+diceVal,diceVal,player);
		 					j++;
	 	 				}
	 	 			}
		 		}
	 		}
 		//	System.out.println("Possible moves for dice :" +diceVal+"\nNumber of moves:"+j);
	 		PossibleMoves[] m = new PossibleMoves[j];   //make new array to send exact array size of elements
	 		for(int i=0;i<j;i++){
	 			m[i] = p[i];
	 		//	m[i].printMove();
	 		}
	 		return m;
 		}
 	}
}




