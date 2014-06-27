import java.util.Observer;
import java.util.Observable;

public class Controller implements Observer {
	
	private GameBoard view = null;
	private Lights model = null;
	public Controller(GameBoard view, Lights model)
	{
		this.view = view;
		this.model = model;
	}
	public void update(Observable obs, Object obj)
	{
		if(obs == view)
		{
			for(int row = 0; row < model.getGameSize(); row++)
			{
				for(int col = 0; col < model.getGameSize(); col++)
				{
					if(view.getLight(row, col) != model.getLightState(row, col))
					{
						model.toggleLight(row, col);
					}
				}
			}
		}
		if(obs == model)
		{
			for(int row = 0; row < model.getGameSize(); row++)
			{
				for(int col = 0; col < model.getGameSize(); col++)
				{
					view.setLight(row, col, model.getLightState(row, col));
				}
			}
		}
	}
}
