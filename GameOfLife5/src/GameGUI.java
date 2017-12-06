import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

//This class creates our Game Control Form!
public class GameGUI implements Runnable
{
	public void run() 
	{
		int gridSize = GameControl.gridSize;
	     //this create and set up the Window
        JFrame frame = new JFrame("Conway’s Game of Life by Ahmed Arshe");
        //this always the user to exit the game through the "X"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create and set up the content Pane based on our Game Control class
        GameControl newContentPane = new GameControl();
        //all content Panes must be opaque, meaning that it should be transparent 
        newContentPane.setOpaque(true);
        //this then put in the pane in the Window
        frame.setContentPane(newContentPane);
        //this sets the frame size so that when the game runs, it would open properly so that you can see the grid without the user maximising 
        int vBound = gridSize*20 + gridSize*2 +100;
        int hBound = gridSize*20 + gridSize*2 +100;
        frame.setBounds(50,50,vBound, hBound);
        frame.setVisible(true);
    }
}