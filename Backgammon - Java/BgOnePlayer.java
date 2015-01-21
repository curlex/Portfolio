import java.util.ArrayList;


public class BgOnePlayer {
	HumanPlayer Human;
	RandomPlayer Computer;
	char player;
	int turnCounter =0;
	public static void main (String[] args) {
		BgOnePlayer game = new BgOnePlayer();
		System.out.println("Welcome to Backgammon game!");
		game.gameStart();
	}
	
	public BgOnePlayer() {
		
		Human = new HumanPlayer('W');
		Computer = new RandomPlayer('B');
	}
	
	public void playerDetermine(int[] dice) {
		if(turnCounter==0) {
			if(dice[0]>dice[1]) {
				player = Human.getPlayerId();
			}
			else player=Computer.getPlayerId();
			System.out.println("First starts is: " + player);
			
		}
		else {
			if(player=='B') player =  'W';
			else  player = 'B';
			System.out.println("Player: "+player);
		}
	}
	public void gameStart() {
		//Board game = new Board();
		int [] move = new int[8];
		Board testBoard = new Board();
		Dice newDice = new Dice();
		testBoard.initialiseBoard();
		
		do{
			newDice.rollDice(turnCounter);
			playerDetermine(newDice.getDice());
			testBoard.DisplayBoard(player);           
			newDice.printDice();
			testBoard.findPossiblemoves(newDice.getDice(), player);
			ArrayList<PossibleMoves[]> y = (ArrayList<PossibleMoves[]>) testBoard.findPossiblemoves(newDice.getDice(),player);
			for(int i=0;i<y.size();i++){
				for(int h=0;h<y.get(i).length;h++){
					if(y.get(i)[h].moveStatus == true){ //if the move was not removed 
						y.get(i)[h].printMove();  //if move is still stored in the element in the arraylist
					}
				}
			}
			if(player=='B')	move = Computer.Play( newDice.getDice(),testBoard );          //gets input for move and stores in p
			else move = Human.Play( newDice.getDice().length );          //gets input for move and stores in p
			
			if(move[0]!='q' ||move[0]!='Q'){
				 if(Mover(move,testBoard,newDice) == false ) move[0]='q';
			}
			turnCounter++;
			testBoard.countCheckerAtHome(player);
		}while(move[0]!='q' && testBoard.gameOver(player)==false);            //As long as you didnt quit the game or someone won
		//Call method for winning result gammon, backgammon , single
		System.out.println("Thank you for playing! ");
		
	}
	
	public boolean Mover(int [] move,Board b,Dice d){
		boolean valid = false;
		valid = b.moveChecker(player, d.getDice(), move);
		while( valid == false && move[0]!='q'){                      //If the move was invalid and it was not quit
			if(player =='B')move = Computer.Play( d.getDice(),b );    
			else move =  Human.Play( d.getDice().length );						//ask for new input 
			if(move[0] == 'q')	return false;   								//if player quits or passes exit loop
			valid = b.moveChecker(player, d.getDice(), move);
			if(move[0]=='q')return false;
		}
		return true;
}
}