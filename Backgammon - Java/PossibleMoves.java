
public class PossibleMoves {
	int from;
	int to;
	int DiceValue;
	char player;
	boolean moveStatus = true;
	
	public PossibleMoves (){
		from = -1;                //number that will never moved 
		to = -6;                    //no position off the board will be more than 6 places off
		player = 0;             
		DiceValue = 0;
	}
	public PossibleMoves(int from,int to,int diceval,char player){
		this.from = from;
		this.to = to;
		this.player = player;
		this.DiceValue = diceval;
		this.moveStatus = true;
	}
	public int[] getMove(){
		int[] Move = new int[2];
		Move[0] = from ;
		Move[1] = to ;
		return  Move;
	}
	public void removeMove(){
		moveStatus = false;
	}
	public void printMove(){
		System.out.println("Dice: "+DiceValue+" : " + from+" - "+ to+"  " );
	}
	
}
