import java.util.ArrayList;


public class AiTester {

	public static void main(String[] args) {
		Board gameBoard = new Board();
		Dice gameDice = new Dice();
		boolean finished = false;
		int player1,player2;
		
		do{
			 player1 = gameDice.rollDieEach();
		}while(player1 == Dice.DRAW);	
		
		player2 = gameBoard.opposingPlayer(player1);
		
		System.out.print("Player 1 is ");
		gameBoard.displayChecker(player1);
		System.out.println();
		gameBoard.displayBoard(player1);
		System.out.println();
		
		System.out.print("\n\nPlayer 2 is ");
		gameBoard.displayChecker(player2);
		System.out.println();
		gameBoard.displayBoard(player2);
		System.out.println();
		
		System.out.println("\n");
		AiPlayer ai1 = new AiPlayer(player1, gameBoard, gameDice);
		AiPlayer ai2 = new AiPlayer(player2, gameBoard, gameDice);
		
		while(!finished) {
			gameDice.rollDice();
			gameDice.displayDice(player1);
			finished = gameBoard.doPlay(player1,ai1.getPlay());
			gameBoard.displayBoard(player1);
			System.out.println();
			
			gameDice.rollDice();
			gameDice.displayDice(player2);
			finished = gameBoard.doPlay(player2,ai2.getPlay());
			gameBoard.displayBoard(player2);
			System.out.println();
		
			if(finished){
				gameBoard.displayResult();
			}
			
		}
		
	}

}
