import java.util.ArrayList;
import java.util.Random;

/**
 * @author Asma Almaksy, Assem Tursyngaliyeva  ,Akmaral Akhanova
 * Student Number: 11364941 - 12254874  - 12250343
 */

// import java.util.Scanner;
public class RandomPlayer {
	char player;
	int[] chosenPlay;  // value for the move  == diceVal
	
	public RandomPlayer( char player){
		this.player = player;
	}
    
	public char getPlayerId(){
		return player;
	}
    
	public int[] getPlay() {
	 return chosenPlay ;
	}
	public int [] Play (int [] dice,Board p){
		Random r = new Random();
		int i,j;
		ArrayList<PossibleMoves[]> y = (ArrayList<PossibleMoves[]>) p.findPossiblemoves(dice,player);
		do{
			i = r.nextInt(y.size());
			j = r.nextInt(y.get(i).length);
		}while(!(y.get(i)[j].moveStatus));
		if(dice.length>2)	chosenPlay = new int[8];
		else chosenPlay = new int[4];
		chosenPlay[0] = y.get(i)[j].from;
		chosenPlay[1] = y.get(i)[j].DiceValue;
		y.get(i)[j].moveStatus=false;
		System.out.println("Computer plays:  ");
		y.get(i)[j].printMove();
		do{
			i = r.nextInt(y.size());
			j = r.nextInt(y.get(i).length);
		}while(!(y.get(i)[j].moveStatus));
		chosenPlay[2] = y.get(i)[j].from;
		chosenPlay[3] = y.get(i)[j].DiceValue;
		y.get(i)[j].printMove();
		y.get(i)[j].moveStatus=false;
		if(dice.length>2){
			do{
				i = r.nextInt(y.size());
				j = r.nextInt(y.get(i).length);
			}while(!(y.get(i)[j].moveStatus));
			chosenPlay[4] = y.get(i)[j].from;
			chosenPlay[5] = y.get(i)[j].DiceValue;
			y.get(i)[j].moveStatus=false;
			System.out.println("Computer plays:  ");
			y.get(i)[j].printMove();
			do{
				i = r.nextInt(y.size());
				j = r.nextInt(y.get(i).length);
			}while(!(y.get(i)[j].moveStatus));
			chosenPlay[6] = y.get(i)[j].from;
			chosenPlay[7] = y.get(i)[j].DiceValue;
			y.get(i)[j].printMove();
			y.get(i)[j].moveStatus=false;
		}
		return chosenPlay;
	}
}