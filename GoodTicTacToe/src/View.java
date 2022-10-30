import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;



public class View {
	Controller controller;
	JFrame frame;
	int rows = 3;
	int cols = 3;
	char[][] board;
	JButton buttons[][];
	JLabel myLabel;
	JTextField inputRowField;
	JTextField inputColField;
	JPanel myButtonPanel;
	JLabel playerText;
	
	public int getRowsFromTextField() {
		return Integer.parseInt(inputRowField.getText());
	}
	
	public int getColsFromTextField() {
		return Integer.parseInt(inputColField.getText());
	}
	
	public void playerPanel(int playerId, char playerChar) {
		playerText.setText("Player " + playerId + " (" + playerChar + ") GUI");
	}
	
	public void disableWindow() {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++){
				final int _r = r;
				final int _c = c;
				buttons[r][c].setEnabled(false);
			}
		}
	}
	
	public void enableWindow() {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++){
				final int _r = r;
				final int _c = c;
				buttons[r][c].setEnabled(true);
			}
		}
	}
	
	
	
	
    public View(Controller _controller, int rows, int cols){
    	this.buttons = new JButton[rows][cols];
    	this.controller = _controller;
    	this.rows = rows;
    	this.cols = cols;
    	
    	
    	frame = new JFrame("GoodTicTacToe");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(new Dimension(650, 450));
    	myButtonPanel = new JPanel();
    
    	myButtonPanel.setLayout(new GridLayout(rows,cols));
    	
    	
    	
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++){
				final int _r = r;
				final int _c = c;
				JButton button = buttons[r][c] = new JButton();
				button.setPreferredSize(new Dimension(50, 50));
				button.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						controller.unitClicked(_r, _c);
						}
					});
				myButtonPanel.add(button);
				}
			}
		
		
    
    	JPanel myTextPanel = new JPanel();
    	myTextPanel.setLayout(new GridLayout(1,1));
    	myTextPanel.setPreferredSize(new Dimension(150,50));
    	myLabel = new JLabel("Player 1's (A) turn", SwingConstants.CENTER);
    	myTextPanel.add(myLabel);
    	
    	
    	
    	
    	JPanel playerPanel = new JPanel();
    	playerPanel.setLayout(new GridLayout(1,1));
    	playerPanel.setPreferredSize(new Dimension(150,50));
    	playerText = new JLabel(" ", SwingConstants.CENTER);
    	playerPanel.add(playerText);
    	
    
    	
    	//
    	
    	JPanel sizePanel = new JPanel();
    	sizePanel.setLayout(new GridLayout(1,1));
    	sizePanel.setPreferredSize(new Dimension(750,5));
    	
    	JLabel changeSizeText = new JLabel("Change size: ", SwingConstants.LEFT);
    	sizePanel.add(changeSizeText);
    	
    	JLabel changeRowText = new JLabel("Change Row:", SwingConstants.LEFT);
    	sizePanel.add(changeRowText);

    	inputRowField = new JTextField(SwingConstants.RIGHT);
        inputRowField.setColumns(1);
        sizePanel.add(inputRowField);
    	
    	JLabel changeColText = new JLabel("Change Columns:", SwingConstants.LEFT);
    	sizePanel.add(changeColText);
    	
    	inputColField = new JTextField(SwingConstants.RIGHT);
        inputColField.setColumns(1);
        sizePanel.add(inputColField);
        
        JButton sizeButton = new JButton();
        sizeButton.setPreferredSize(new Dimension(50, 50));
        sizeButton.setText("Change Size");
        sizePanel.add(sizeButton);
        sizeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changeSize(getRowsFromTextField(), getColsFromTextField());
				frame.setVisible(false);
				frame.dispose();
			}
		});
        
       
        
        //
        
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new GridLayout(1,1));
        resetPanel.setPreferredSize(new Dimension(600,5));
    	
    	JButton resetButton = new JButton();
    	resetButton.setPreferredSize(new Dimension(50, 50));
    	resetButton.setText("ResetGame");
    	
    	resetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.reset();
			}
		});

    	
    	
    	resetPanel.add(resetButton);
        
        
        //
        
        
    	
    	
 
  
       
    	
    	
    	//
    	
    	
    	JPanel myMainPanel = new JPanel();
    	myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
    	myMainPanel.add(myButtonPanel);
    	myMainPanel.add(myTextPanel);
    	myMainPanel.add(playerPanel);
    	myMainPanel.add(sizePanel);
    	myMainPanel.add(resetPanel);
   
    	frame.getContentPane().add(myMainPanel);
    	

	    
	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	    
		
		
	    }
    
    
    public void RemoveView() {
		frame.setVisible(false);
		frame.dispose();
    }
    
    
    public String getTextFromButton(int r, int c) {
    	String text = buttons[r][c].getText();
    	return text;
    }
    
    public void changeLabelText(String text) {
    	myLabel.setText(text);
    }
    
    public void setTextFromButton(int r, int c, String textString) {
    	buttons[r][c].setText(textString);
    }
    
}
