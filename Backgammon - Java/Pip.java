public class Pip {
	int pipNumber ;
	int checkerNumber ;
	char player ;
	
	public Pip(){
		this.player = 0;
		this.checkerNumber=0;
	}
	public Pip(char nPlayer, int numberOfCheckers) {
		 player = nPlayer;
		 checkerNumber = numberOfCheckers;
	}

	public char getPlayer() {
		return player;
	}
	public int getCheckerNumber () {
		return checkerNumber;
	}
	public int getPip() {
		return pipNumber;
	}

	public void addChecker (int checker) {
		checkerNumber += checker;
	}
	public void changePlayer(char playerChange){
		player = playerChange;
	}
	public void printContent(){
		if(player=='0'){
			System.out.print("0");
		}
		else{
			System.out.print(player);
			System.out.print(checkerNumber);
		}
	}
	public void removePlayer(){
		player = '0';      
	}
	public void removeChecker() {
		checkerNumber--;
		if(checkerNumber<=0){
			player = '0';
			checkerNumber = 0;
		}
	}

}

