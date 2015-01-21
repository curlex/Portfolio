import java.util.Random;

public class Dice {
	private int [] dice;
	private int [] doubleDice  ;
	private Random randomThrow;
	
	public Dice(){
		randomThrow = new Random();
		dice = new int[2];
		doubleDice = new int [4] ;
	}
	public void rollDice(int turnCounter) {
		do{
			dice[0] = randomThrow.nextInt(6)+1;
			dice[1] = randomThrow.nextInt(6)+1;
		}while(dice[0]==dice[1] && turnCounter==0);
		if(dice[0]==dice[1])getDouble();
		
		
	}
	public void getDouble(){
		int j = 0;
		for(int i=0;i<doubleDice.length;i++){
			doubleDice[i] = dice[j];
			if(i==1)j++;
		}	
	}
	
    public int [] getDice(){
    	
    	if(dice[0] == dice[1] ) return doubleDice;	
    	else return dice;
	}
    
	public void printDice(){
		int[] n ;
		if(dice[0]==dice[1]) n = doubleDice; 
		else n = dice;
		for(int i = 0; i < n.length;i++)System.out.println("Dice:"+n[i]);
		
	}
}