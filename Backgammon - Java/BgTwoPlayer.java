
public class BgTwoPlayer {
	HumanPlayer Black ;
	HumanPlayer White ;
	HumanPlayer Current;
	char player;
	int turnCounter = 0;
	public static void main(String[] args){
		BgTwoPlayer twoPlayerGame = new BgTwoPlayer();
		twoPlayerGame.play();
	}
	public  BgTwoPlayer(){
		Black  = new HumanPlayer('B');
		White = new HumanPlayer('W');
	}
	public void playerDetermine(int [] dice){
		if(turnCounter==0){
			if(dice[0]>dice[1]){
				player =  Black.getPlayerId();
			}
			else player = White.getPlayerId();
			System.out.println("Player who starts first is: "+player);
		}
		else {
			if(player== 'B') player =  White.getPlayerId();
			else  player = Black.getPlayerId();
			System.out.println("Player: "+player);
		}
		if(player =='B')Current = Black;
		else if( player=='W')Current = White;
}
	public void play(){
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
			move = Current.Play( newDice.getDice().length );          //gets input for move and stores in p
			if(move[0]!='q' ||move[0]!='Q'){
				 if(Mover(move,testBoard,newDice,Current)==false)move[0]='q';
				}
			turnCounter++;
			testBoard.countCheckerAtHome(player);
		}while(move[0]!='q' && testBoard.gameOver()==false);            //As long as you didnt quit the game or someone won
		//Call method for winning result gammon, backgammon , single
		System.out.println("Thank you for playing! ");
	}
	
	public boolean Mover(int [] move,Board b,Dice d,HumanPlayer P){
		boolean valid = false;
		valid = b.moveChecker(player, d.getDice(), move);
		while( valid == false && move[0]!='q'){                      //If the move was invalid and it was not quit
			move =  Current.Play( d.getDice().length );						//ask for new input 
			if(move[0] == 'q')	return false;   								//if player quits or passes exit loop
			valid = b.moveChecker(player, d.getDice(), move);
		/*if(move[0]=='p' &&valid==false ){
				while(valid==false &&move[0]!='q'){
				move = P.Onemove();
					if(move[1]==d.getDice()[0]||move[1]==d.getDice()[1]){
						valid = b.makeMove(player, d.getDice(), move);
					}
				}
				if(move[0]=='q')return false;
				else return true;
			}
		}*/
			if(move[0]=='q')return false;
		}
		return true;
}
}

