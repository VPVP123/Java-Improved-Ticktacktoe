
public class RuleEngine {

	private int playersTurn = 0;
	private int playerAmmount = 2; //set amount of players
	private char[] symbols = new char[playerAmmount];
	private Controller controller;
	private int rows;
	private int cols;
	private static int charToWin = 3; //how many in a row to win
	private static char[][] board;
	private boolean gameover = false;
	
	RuleEngine(Controller controller, int r, int c){
		this.controller = controller;
		this.board = new char[r][c];
		for(int i = 0; i < playerAmmount; i++) {
			symbols[i] = (char)(65+i);
		}
		
	}
	

	public char getPlayerSymbol(int playerId) {
		return symbols[playerId];
	}
	
	
	public int getPlayerCount() {
		return this.playerAmmount;
	}
	
	
	public int getPlayersTurn() {
		return playersTurn;
	}
	
	public void getBoard(char[][] board) {
		this.board = board;
	}
	
	
	public void moveMade(int r_, int c_, int playersTurn) {
		if(gameover) {
			return;
		}
			controller.changeText(r_, c_, symbols[playersTurn]);
       		board[r_][c_] = symbols[playersTurn];
       		if(checkIfWinv2(r_, c_, board, symbols[playersTurn])) {
       			controller.playerWon(playersTurn + 1);
       			gameover = true;
       			return;
       		}
		 	this.playersTurn = (playersTurn + 1) % playerAmmount;
		 	controller.enabledPanel(this.playersTurn);
		 	
    		controller.playersTurn("Player " + (this.playersTurn + 1) + "'s " + (symbols[this.playersTurn]) + " turn");
    		return;
	}
	
	
	public boolean checkIfWinv2(int r_, int c_, char[][] board, char playerChar) {
		int inARow = 0;
		boolean win = false;
		
		
		//Win by row
		for(int i = 0; i < board.length && !win; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == playerChar)
					inARow++;
				if(board[i][j] != playerChar)
					inARow = 0;
				if(inARow >= charToWin)
					return true;
			}
		}
		
		inARow = 0;
		
		
		//Win by col
		for(int j = 0; j < board[0].length && !win; j++) {
			for(int i = 0; i < board.length; i++) {
				if(board[i][j] == playerChar)
					inARow++;
				if(board[i][j] != playerChar)
					inARow = 0;
				if(inARow >= charToWin)
					return true;
				}
		}
		
		
		inARow = 0;
		

		//DIAGONAL DOWNRIGHT AND UP LEFT
			for(int i = c_, j = r_; i < board.length && j < board[0].length; i++, j++) {
				if(board[j][i] == playerChar)
					inARow++;
				if(board[j][i] != playerChar)
					break;
				if(inARow >= charToWin)
					return true;
			}
			for(int i = c_-1, j = r_-1; i >= 0 && j >= 0; i--, j--) {
				if(board[j][i] == playerChar)
					inARow++;
				if(board[j][i] != playerChar)
					inARow = 0;
				if(inARow >= charToWin)
					return true;
			}
		
		inARow = 0;
		
		//DIAGONAL UP RIGHT DOWNLEFT 

			for(int i = c_, j = r_; i < board.length && j >= 0; i++, j--) {
				if(board[j][i] == playerChar)
					inARow++;
				if(board[j][i] != playerChar)
					break;
				if(inARow >= charToWin)
					return true;
			}
			for(int i = c_-1, j = r_+1; i >= 0 && j < board.length; i--, j++) {
				if(board[j][i] == playerChar)
					inARow++;
				if(board[j][i] != playerChar)
					inARow = 0;
				if(inARow >= charToWin)
					return true;
			}
		
		return win;
	}
	
	
	public void setBoard(int rows, int cols) {
		this.playersTurn = 0;
		this.gameover = false;
		controller.enabledPanel(playersTurn);
		this.board = new char[rows][cols];
		this.rows = rows;
		this.cols = cols;
		
		
		for(int x = 0; x < rows; x++)
			for(int y = 0; y < cols; y++) {
				board[x][y] = ' ';
			}
	}
	
	
	
	
	
	
	
	
	
}
