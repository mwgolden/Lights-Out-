import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.util.Observable;

public class GameBoard extends Observable {
	
	private JButton[][] buttons;
	private FlowLayout layout;
	private JFrame window;
	private Icon offLight; 
	private Icon onLight;
	
	public GameBoard(int lightNums)
	{
		window = new JFrame("Lights Out!");
		layout = new FlowLayout();
		window.setLayout(layout);
		layout.setHgap(0);
		layout.setVgap(0);
		
		buttons = new JButton[lightNums][lightNums];
		offLight = new ImageIcon(getClass().getResource("OFF.png"));
		onLight = new ImageIcon(getClass().getResource("ON.png"));
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		for(int row = 0; row < lightNums; row++)
		{
			for(int col = 0; col < lightNums; col++)
			{
				buttons[row][col] = new JButton();
				buttons[row][col].setIcon(offLight);
				buttons[row][col].setBorderPainted(false);
				buttons[row][col].setBackground(Color.WHITE);
				buttons[row][col].addActionListener(buttonHandler);
				window.add(buttons[row][col]);
			}
		}
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(425, 580);
		window.setVisible(true);
	}
	private void updateState()
	{
		setChanged();
		notifyObservers();
	}
	public void setLight(int row, int col, boolean on)
	{
		if(on)
		{
			buttons[row][col].setIcon(onLight);
		}
		else buttons[row][col].setIcon(offLight);
	}
	public boolean getLight(int row, int col)
	{
		if(buttons[row][col].getIcon() == onLight)
		{
			return true;
		}
		else return false;
	}
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent toggleButton)
		{
			JButton button = (JButton)toggleButton.getSource();
			if(button.getIcon() == onLight)
			{
				button.setIcon(offLight);
			}
			else
			{
				button.setIcon(onLight);
			}
			updateState();
		}
	}
	
	public static void main(String args[])
	{
		new GameBoard(5);
		
	}

}
