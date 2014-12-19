
public class TicTacToeLogic {
	private int[][] table;
	private int numEmptyFields;
	private String winner; 
	private boolean player; // if true player is 'X' if false player is 'O'
	private boolean gameOver;
	
	/**
	 * creates the table
	 * @param size int size of the table
	 */
	public TicTacToeLogic(int size) {
		this.table = new int[size][size];
		this.winner = "Tie";
		this.numEmptyFields = size * size;
		this.player = true;
		this.gameOver = false;
		initTable(); 
	}
	
	/**
	 * creates table with default elements -1
	 */
	private void initTable() {
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table.length; j++) {
				table[i][j] = -1;
			}
		}
	}
	
	/**
	 * checks if the cell in the table is occupied
	 * @throws IndexOutOfBoundsException("Coordinates are not valid! ")
	 * @throws IllegalArgumentException("The cell is already occupied. ")
	 * @param x int first coordinate in the table, row
	 * @param y int second coordinate in the table, columns
	 * @throws IndexOutOfBoundsException("Coordinates are not valid! ")
	 * @return true or false
	 */
	public boolean setCell(int x, int y) {
		int value;
		if(x <= 0 || y <= 0 || x >= table.length || y >= table.length)
			throw new IndexOutOfBoundsException("Coordinates are not valid! ");
		if(table[x][y] != -1)
			throw new IllegalArgumentException("The cell is already occupied. ");
		if(player == true)
			value = 1; //X
		else
			value = 2; //O
		table[x][y] = value;
		numEmptyFields--;
		player = !player;
		gameOver = isOver();
		return gameOver;
	}
	
	/**
	 * checks if the game is over, if all of the cells are occupied
	 * @return true or false
	 */
	public boolean isOver() {
		if(checkRows() == true)
			return true;
		if(checkColumns() == true)
			return true;
		if(numEmptyFields == 0)
			return true;
		if(checkDiagonals() == true)
			return true;
		return false;
	}
	
	/**
	 * checks if all rows in the table are occupied
	 * @return true or false
	 */
	private boolean checkRows() {
		for(int i = 0; i < table.length; i++) {
			if(checkRow(i) == true)
				return true;
		}
		return false;
	}
	
	/**
	 * checks if the row is occupied and if so are cells all the same
	 * if the cells are the same, then we call method setWinner
	 * @param index int index of the cell in the table
	 * @return true or false
	 */
	private boolean checkRow(int index) {
		int[] row = table[index];
		if(row[0] == -1)
			return false;
		for(int i = 1; i < table.length; i++) {
			if(row[i] != row[i-1])
				return false;
		}
		setWinner(row[0]);
		return true;
	}
	
	/**
	 * checks if all columns in the table are occupied
	 * @return true or false
	 */
	private boolean checkColumns() {
		for(int i = 0; i < table.length; i++) {
			if(checkColumn(i) == true)
				return true;
		}
		return false;
	}
	
	/**
	 * checks if the column is occupied and if so are cells all the same
	 * if the cells are the same, then we call method setWinner
	 * @param index int index of the cell in the table
	 * @return true or false
	 */
	private boolean checkColumn(int index) {
		if(table[0][index] == -1)
			return false;
		for(int i = 1; i < table.length; i++)
			if(table[i-1][index] != table[i][index])
				return false;
		setWinner(table[0][index]);
		return true;
	}
	
	/**
	 * checks if the diagonals are occupied
	 * @return true or false
	 */
	private boolean checkDiagonals() {
		int i = 0;
			if(checkDiagonal(i) == true)
				return true;
		i = table.length - 1;
			if(checkDiagonal(i) == true)
				return true;
		return false;
	}
	
	/**
	 * checks if the diagonal is occupied and  if so are cells all the same
	 * if the cells are the same, then we call method setWinner
	 * @param index int index of the cell in the table
	 * @return true or false
	 */
	private boolean checkDiagonal(int index) {
		if(table[index][index] == -1)
			return false;
		if(index == 0) {
		for(int i = 1; i < table.length; i++)
				if(table[i][i] != table[i-1][i-1])
					return false;
		setWinner(table[0][0]);
		return true;
		}
		else {
			int i = 1;
			int j = table.length - 2;
			while(i < table.length) {
				if(table[i][j] != table[i-1][j+1])
					return false;
				i++;
				j--;
			}
			setWinner(table[0][table.length-1]);
			return true;
		}
	}
	
	/**
	 * sets the winner of the game
	 * @param value int value of the cell
	 * @return winner
	 */
	private String setWinner(int value) {
		if(value == 1)
			this.winner = "The winner is player 'X'!";
		else
			this.winner = "The winner is player 'O'!";
		return winner;
	}

}
