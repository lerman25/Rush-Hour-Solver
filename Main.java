import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		try {
			board.readBoard_file(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board.print_board();
		
	}
}
