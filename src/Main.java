
public class Main {

	public static void main(String[] args) {
		Lights game = new Lights(5);
		GameBoard board = new GameBoard(5);
		Controller controller = new Controller(board, game);
		board.addObserver(controller);
		game.addObserver(controller);
		
		for(int row = 0; row < 5; row++)
		{
			for(int col = 0; col < 5; col++)
			{
				board.setLight(row, col, game.getLightState(row, col));
			}
		}

	}

}
