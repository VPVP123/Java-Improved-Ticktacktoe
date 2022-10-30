
public class Controller {
	private View[] view;
	private RuleEngine engine;
	private int rows = 3;
	private int cols = 3;
	private boolean multiGui = true;
	private int winNum = 1;
	
	public Controller() {
		this.engine = new RuleEngine(this, this.rows, this.cols); 
		
		if(multiGui) {
			winNum = engine.getPlayerCount();
			view = new View[winNum];
			for(int i = 0; i < winNum; i++) {
				this.view[i] = new View(this, this.rows, this.cols);
				this.view[i].playerPanel(i+1, this.engine.getPlayerSymbol(i));
			}
		}else {
			view = new View[winNum];
			this.view[0] = new View(this, this.rows, this.cols);
		}
		
		this.engine.setBoard(rows, cols);
	}
	
	
	public void changeSize(int r, int c) {
		for(int i = 0; i < winNum; i++) {
			this.view[i].RemoveView();
			this.view[i] = new View(this, r, c);
			this.view[i].playerPanel(i+1, this.engine.getPlayerSymbol(i));
		}
		this.engine.setBoard(r, c);
		this.rows = r;
		this.cols = c;
	}
	
	public void reset() {
		for(int i = 0; i < winNum; i++) {
			this.view[i].RemoveView();
			this.view[i] = new View(this, this.rows, this.cols);
			this.view[i].playerPanel(i+1, this.engine.getPlayerSymbol(i));
		}
		this.engine.setBoard(this.rows, this.cols);
	}
	
	public void enabledPanel(int playerId) {
		for(int i = 0; i < winNum; i++) {
			if(multiGui) {
			if(i == playerId) {
				this.view[i].enableWindow();
			}else {
				this.view[i].disableWindow();
			}
			}
		}
	}

	
	public void unitClicked(int r_, int c_) {
		if(view[0].getTextFromButton(r_, c_).isEmpty()) {
			this.engine.moveMade(r_, c_, this.engine.getPlayersTurn());
		}else {
			return;
		}
	}
	
	public void changeText(int r_, int c_, char player) {
		
		for(int i = 0; i < winNum; i++) {
			view[i].setTextFromButton(r_, c_, String.valueOf(player));
		}
	}
	
	public void playerWon(int player) {
		
		for(int i = 0; i < winNum; i++) {
			view[i].changeLabelText("Player " + String.valueOf(player) + " (" + engine.getPlayerSymbol(player-1) + ") won!");
		}
	}
		
	
	public void playersTurn(String playerString) {
		
		for(int i = 0; i < winNum; i++) {
			view[i].changeLabelText(playerString);
		}
		
	}
	
}


