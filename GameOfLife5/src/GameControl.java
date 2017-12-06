import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class GameControl extends JPanel implements ActionListener 
{
	public static int gridSize = 25;
	public JPanel panel = new JPanel();
	public int[][] back = new int [gridSize] [gridSize];
	public int [][] backCopy= new int [gridSize][gridSize];
	//All forms need a unique ID - ignore this!!!
	private static final long serialVersionUID = 1862962349L;
	//we are going to have four buttons
	private JButton startbutton,endbutton,exitbutton, ResetButton;
	//for this version of the program we are going to have two labels
	private JLabel Counter;
	//the timer is for updating the labels at a regular interval 
	private Timer timer;
	private int IterationCounter;
	public JButton [][] grid = new JButton[gridSize][gridSize];
	//constructor for our form
	public GameControl() 
	{
		//set up the four buttons

		ResetButton = new JButton("Reset");
		//centre the text vertically
		ResetButton.setVerticalTextPosition(AbstractButton.CENTER);
		//centre the text horizontally
		ResetButton.setHorizontalTextPosition(AbstractButton.CENTER);
		//short cut key of 'S'
		ResetButton.setMnemonic(KeyEvent.VK_S);
		//create the event/action 'Start' when clicked
		ResetButton.setActionCommand("Reset");


		startbutton = new JButton("     Start    ");
		//centre the text vertically
		startbutton.setVerticalTextPosition(AbstractButton.CENTER);
		//centre the text horizontally
		startbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		//short cut key of 'S'
		startbutton.setMnemonic(KeyEvent.VK_S);
		//create the event/action 'Start' when clicked
		startbutton.setActionCommand("Start");


		endbutton = new JButton("Pause");
		//centre the text vertically
		endbutton.setVerticalTextPosition(AbstractButton.CENTER);
		//centre the text horizontally
		endbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		//short cut key of 'T'
		endbutton.setMnemonic(KeyEvent.VK_T);
		//create the event/action 'Stop' when clicked
		endbutton.setActionCommand("Stop");
		//initially the stop button is disabled
		endbutton.setEnabled(false);


		exitbutton = new JButton("Exit");
		//centre the text vertically
		exitbutton.setVerticalTextPosition(AbstractButton.CENTER);
		//centre the text horizontally
		exitbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		//short cut key of 'X'
		exitbutton.setMnemonic(KeyEvent.VK_X);
		//create the event/action 'Exit' when clicked
		exitbutton.setActionCommand("Exit");

		//listen for actions from the three buttons
		//the current instance of 'this' class will process the actions for the buttons 
		startbutton.addActionListener(this);
		endbutton.addActionListener(this);
		exitbutton.addActionListener(this);
		ResetButton.addActionListener(this);
		//this is the text that is displayed when we hover the mouse over the buttons
		startbutton.setToolTipText("Click this button to Start running the Game...");
		endbutton.setToolTipText("Click this button to Pause logging...");
		exitbutton.setToolTipText("This button is the only way you can exit this application...");
		ResetButton.setToolTipText("This button is sets another random grid...");

		gridMaker();
		for(int i = 0; i < gridSize; i++)
		{
			for(int j = 0; j < gridSize; j++)
			{
				grid[i][j] = new JButton();
				grid[i][j].setOpaque(true);
				grid[i][j].setBackground(Color.BLACK);
				grid[i][j].setPreferredSize(new Dimension(20, 20));
			}

		}
		repaint();
		//this adds the Buttons, Labels and more components to the Form
		add(panel);
		add(startbutton);
		add(endbutton);
		add(exitbutton);
		add(ResetButton);
		Counter= new JLabel("IterationCount=" + IterationCounter);
		add(Counter);
		//create a timer every second (1000 m/s)
		//the current instance of 'this' class will process the actions for the timer
		timer = new Timer(1000, this);
		//this creates the event/action 'Timer' every second
		timer.setActionCommand("Timer");
		//start the timer immediately 
		timer.setInitialDelay(0);

	}



	
	//Every time a button is clicked, a method has to run
	public void actionPerformed(ActionEvent e) 
	{
		//it sees if the 'Start' button has been pressed event
		if (e.getActionCommand().equals("Start")) 
		{
			//this logs that the 'start' button has been press
			System.out.println("Start pressed");
			//disable the 'Start' button
			startbutton.setEnabled(false);
			//the 'Stop' button will then be enabled once the start button has been pressed
			endbutton.setEnabled(true);
			//this starts the 'Timer'
			timer.start();
		}
		//it sees if the 'Stop' button has been pressed event
		if (e.getActionCommand().equals("Stop"))
		{
			//this logs that the 'stop' button has been press
			System.out.println("Pause pressed");
			//enable the 'Start' button
			startbutton.setEnabled(true);
			//the 'Stop' button will then be enabled once the start button has been pressed
			endbutton.setEnabled(false);
			//this then stops the 'Timer'
			timer.stop();
		}

		//checks for the 'Exit' action
		if (e.getActionCommand().equals("Exit"))
		{
			//this logs that the 'exit' button has been press
			System.out.println("Exit");
			//then stops the timer
			timer.stop();
			//get the parent JFrame of this panel
			JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
			//hide the Window
			parent.setVisible(false);
			//get rid of the Window
			parent.dispose();
		}    	
		if(e.getActionCommand().equals("Timer")){
			cellsAlive();
			repaint();
			IterationCounter++;
			System.out.println("Count");
		}
		if(e.getActionCommand().equals("Reset")){
			gridMaker();// this helps generator the random grid 
			repaint();// this this helps paint in the random grid with the set colours in the grid maker method
			IterationCounter = 0;
		}
	}

	
	@Override // this lets me override a parent panel 
	//this is used to help paint over the buttons
		public void paint(Graphics g){
			super.paint(g);
			Counter.setText("Iteration Count =  " + IterationCounter);
			for(int i = 0; i < gridSize; i++)
			{
				for(int j = 0; j < gridSize; j++)
				{
					if (back[i][j] == 0)
					{
						grid[i][j].setBackground(Color.BLACK);
					}
					else if (back[i][j] == 1)
					{
						grid[i][j].setBackground(Color.ORANGE);
					}
					panel.add(grid[i][j]);


				}
			}

		}
	
	// this is the thing that sets the grid for the cells
	public void gridMaker()
	{
		panel.setLayout(new GridLayout(gridSize, gridSize,2,2));
		{
			for(int i = 0; i < gridSize; i++)
			{
				for(int j = 0; j < gridSize; j++)
				{
					back[i][j] = (int) (Math.random() * 2);
				}
				}
			}

	}



	
	//this is the rule that is used to see if the cells are dead or alive 
	public void cellsAlive() {
		int i;
		int j;
		for(i = 0; i < gridSize; ++i){
			for(j = 0; j < gridSize; ++j){
				if(back[i][j]== 0 && amountAlive(i,j)== 3){
					backCopy[i][j]=1;//dead to alive
				}
				else if(back[i][j] == 1 && (amountAlive (i,j)<2|| amountAlive (i,j)>3)){
					backCopy[i][j]= 0;//alive to dead
				}
				else{
					backCopy[i][j]= back[i][j];//keeping the same if state isn't changed
				}
			}
		}
		//updating from back from bound
		for(i = 0; i < gridSize; ++i){
			for(j = 0; j < gridSize; ++j){
				back[i][j]= backCopy[i][j];

			}

		}
	}
	public int amountAlive(int x,int y){//cell location when i create the grid, x is rows, y is columns
		int amountAlive= 0;
		for(int i = -1; i < 2; ++i){//i and j values iterate around neighbouring cells
			for(int j = -1;j < 2; ++j){
				if(!(i==0 && j==0)){
					if(back[bound(x,i)][bound(y,j)]==1){
						amountAlive++;
					}
				}
			}
		}
		return amountAlive;

	}
	public int bound(int i, int x){
		if(i+x < 0){
			x = gridSize-1;//if its off the grid size, then it will set it to the other side
		}
		else if(i+x >= gridSize){
			x = 0;
		}
		else{
			x= x+ i;
		}
		return x;
	}
}
