/**
 * @author Asma Almaksy, Assem Tursyngaliyeva  ,Akmaral Akhanova
 * Student Number: 11364941 - 12254874  - 12250343
 *
 */
import java.util.ArrayList;
import java.util.Scanner;	
public class HumanPlayer {
char player;
String command ="";
private Scanner in;
public HumanPlayer(char player){
	this.player =player;
}
public char getPlayerId(){
	return player;
}
public int[] Play (int size){
	if(size==4){
		int [] doublePlay1 = new int [4];
		int [] doublePlay2 = new int [4];
		int j = 0;
		System.out.println("Deuce! You can make four moves.");
		doublePlay1 = getPositions();
		if(doublePlay1[0] == 'Q' ||doublePlay1[0] == 'q' ) return doublePlay1;
		doublePlay2 = getPositions();
		int [] doublePlay = new int [doublePlay2.length + doublePlay1.length];
		for(int i=0;i<doublePlay.length;i++){
			if(i < doublePlay1.length )  doublePlay[i] = doublePlay1[i];
			else{
				doublePlay[i]=doublePlay2[j];
				j++;
			}
			//System.out.print(doublePlay[i]+"  ");
		}
		return doublePlay;
	}
	else{
		int [] positions = new int[4];
		positions = getPositions();
		return positions;
	}
	
}
public int[] Onemove(){
	int [] position = new int [2];
	boolean success = true;
	String temp=  " ";	
	in = new Scanner(System.in);
	System.out.println("Please, enter your positions:");
	do{
		temp = in.nextLine();
		if(temp.matches("[1-9][0-9]{0,1}[-][1-6]")){
			if(!temp.substring(0, 2).contains("-")  &&  Integer.parseInt(temp.substring(0, 2))  < 26){
				position[0] = Integer.parseInt(temp.substring(0, 2));
				position[1] = Character.getNumericValue(temp.charAt(3));
			}
			else if(temp.charAt(1) == '-'){
				position[0] = Character.getNumericValue(temp.charAt(0));
				position[1] = Character.getNumericValue(temp.charAt(2));
			}	
		}
		else if(temp.equalsIgnoreCase("quit")||temp.equalsIgnoreCase("pass")){   //If Quit or Pass Command
			if(temp.charAt(0)=='q'  || temp.charAt(0)=='Q') position[0] = 'q';
			else position[0] = 'p';
			success = true;
		}
		else {
			System.out.println("Please enter your move in the form:\nPosition-By");
			success  =  false;
		}
	}while(!success);

	//System.out.println("Hey, Im success:\n" + position[0] + "\n"+position[1]+"\n"+position[2]+"\n"+position[3]); 
	return position;
}
public int [] getPositions(){
	int [] position = new int [4];
	boolean success = true;
	String restOfcontent = "";
	String temp=  " ";	
	in = new Scanner(System.in);
	System.out.println("Please, enter your positions:");
	do{
		temp = in.nextLine();
		if(temp.matches("([0-9]{1,2}( )*-( )*[1-6])(( )+[0-9]{1,2}( )*-( )*[1-6]){0,3}")){
			if(!temp.substring(0, 2).contains("-")  &&  Integer.parseInt(temp.substring(0, 2))  < 26){
				position[0] = Integer.parseInt(temp.substring(0, 2));
				position[1] = Character.getNumericValue(temp.charAt(3));
				restOfcontent = temp.substring(4, temp.length());
			}
			else if(temp.charAt(1) == '-'){
				position[0] = Character.getNumericValue(temp.charAt(0));
				position[1] = Character.getNumericValue(temp.charAt(2));
				restOfcontent = temp.substring(3, temp.length());
			}
			restOfcontent = restOfcontent.trim();
			if(restOfcontent.length()>0){
				if(restOfcontent.charAt(2)=='-' ){
					position[2] = Integer.parseInt(restOfcontent.substring(0, 2));
					position[3] = Character.getNumericValue(restOfcontent.charAt(3));
					if(position[2] >26){
						success = false;
					}
					else success = true;
				}
				else if(restOfcontent.charAt(1) == '-'){
					position[2] =  Character.getNumericValue(restOfcontent.charAt(0));
					position[3] =  Character.getNumericValue(restOfcontent.charAt(2));
					success = true;
				}
				else success = false;
			}
			if(restOfcontent.length()==0 ){
				success= false;
			}
		}
		else if(temp.equalsIgnoreCase("quit")||temp.equalsIgnoreCase("pass")){   //If Quit or Pass Command
			if(temp.charAt(0)=='q'  || temp.charAt(0)=='Q') position[0] = 'q';
			else position[0] = 'p';
			success = true;
		}
		else {
			System.out.println("Please enter your move in the form:\nPosition-By  Position-By");
			success  =  false;
			restOfcontent = "";           //Resets String for new input
		}
	}while(!success);

	//System.out.println("Hey, Im success:\n" + position[0] + "\n"+position[1]+"\n"+position[2]+"\n"+position[3]); 
	return position;
}
}
