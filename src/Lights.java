import java.util.Random;
import java.util.Observable;

public class Lights extends Observable {
	
	private Light[][] lightMatrix;
	private Random onOff = new Random();
	private int gameSize;
	
	private class Light
	{
		private boolean lswitch;
		
		private Light(boolean lswitch)
		{
			this.lswitch = lswitch;
		}
		
		private void toggleSwitch()
		{
			if(lswitch == true)
			{
				lswitch = false;
			}
			else lswitch = true;
		}
		private boolean getState()
		{
			return lswitch;
		}
	}
	
	public Lights(int gameSize)
	{
		this.gameSize = gameSize;
		lightMatrix = new Light[gameSize][gameSize];
		for(int row = 0; row < 5; row++)
		{
			for(int col = 0; col < 5; col++)
			{
				lightMatrix[row][col] = new Light(onOff.nextBoolean());
			}
		}
	}
	public void toggleLight(int row, int col)
	{
		lightMatrix[row][col].toggleSwitch();
		if(row > 0)
		{
			lightMatrix[(row - 1)][col].toggleSwitch();
		}
		if(col > 0)
		{
			lightMatrix[row][(col - 1)].toggleSwitch();
		}
		if(row < 4)
		{
			lightMatrix[(row + 1)][col].toggleSwitch();
		}
		if(col < 4)
		{
			lightMatrix[row][(col + 1)].toggleSwitch();
		}
		setChanged();
		notifyObservers();
	}
	public boolean getLightState(int row, int col)
	{
		return lightMatrix[row][col].getState();
	}
	public int getGameSize()
	{
		return gameSize;
	}
	public void consoleOutput()
	{
		for(int row = 0; row < 5; row++)
		{
			for(int col = 0; col < 5; col++)
			{
				System.out.print(" " + lightMatrix[row][col].getState() + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}
	
	public static void main(String args[])
	{
		Lights game = new Lights(5);
		game.consoleOutput();
		game.toggleLight(0, 0);
		game.consoleOutput();
		game.toggleLight(0, 4);
		game.consoleOutput();
		game.toggleLight(4, 0);
		game.consoleOutput();
		game.toggleLight(4, 4);
		game.consoleOutput();
		game.toggleLight(2, 2);
		game.consoleOutput();
		game.toggleLight(4, 2);
		game.consoleOutput();
		game.toggleLight(0, 2);
		game.consoleOutput();
		
	}
	
}
