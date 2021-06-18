package tictactoe;
import java.util.*;


public class tictactoe  {
	private static Object[][] board = new Object[3][3];
	private static int turns = 0;

	public static void createBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = "-";
			}
		}
	}
		
	public static void printBoard() {
		for (int row = 0; row < board.length; row++) {
			System.out.println(Arrays.toString(board[row]));
		}
	}
	
	public static void printPositionBoard() {
		int[][] positionBoard = new int[3][3];
		int count = 1;
		
		
		for (int i = 0; i < positionBoard.length; i++) {
			for (int j = 0; j < positionBoard[i].length; j++) {
				positionBoard[i][j] = count;
				count++;
			}
		}
		
		for (int row = 0; row < positionBoard.length; row++) {
			System.out.println(Arrays.toString(positionBoard[row]));
		}
	}
	
	public static boolean checkInput(int input) {
		if (input < 1 || input > 9) {
			System.out.println("You entered a number out of bounds please try again.");
			System.out.println();
			System.out.println();
			return false;
		}
		return true;
	}
	
	public static int[] coordinates(int input) {
		int[] coordinates = new int[2];
		coordinates[0] = input / 3;
		coordinates[1] = input % 3;
		
		return coordinates;
	}
	
	public static boolean isTaken(int[] coordinates, Object[][] board) {
		int row = coordinates[0];
		int col = coordinates[1];
		
		if (!board[row][col].equals("-")) {
			System.out.println("This position is already taken.");
			return true;
		}
		
		return false;
	}
	
	public static void addToBoard(int[] coords, Object[][] board, boolean user) {
		board[coords[0]][coords[1]] = currentUser(user);
	}

	public static String currentUser(boolean user) {
		if (user == true)
			return "x";
		else return "o";
	}
	
	public static boolean isWin(boolean user, Object[][] board) {
		if (checkRow(user, board)) {
			printBoard();
			return true;
		}
		
		if (checkCol(user, board)) {
			printBoard();
			return true;
		}
		
		if (checkDiag(user, board)) {
			printBoard();
			return true;
		}
		
		return false;
	}
	
	public static boolean checkRow(boolean user, Object[][] board) {
	
		for (int i = 0; i < board.length; i++) {
			boolean completeRow = true;
			for (int j = 0; j < board[i].length; j++) {
				if (!board[i][j].equals(currentUser(user))) 
						completeRow = false;;
			}
			
			if (completeRow == true)
				return true;
		}
		
		return false;
		
	}
	
	public static boolean checkCol(boolean user, Object[][]board) {
		for (int i = 0; i < board.length; i++) {
			boolean completeCol = true;
			for (int j = 0; j < board[i].length; j++) {
				if (!board[j][i].equals(currentUser(user))) 
						completeCol = false;;
			}
			
			if (completeCol == true)
				return true;
		}
		
		return false;
	}
	
	public static boolean checkDiag(boolean user, Object[][]board) {
		if (board[0][0].equals(currentUser(user)) && board[1][1].equals(currentUser(user)) && board[2][2].equals(currentUser(user)))
				return true;
		if (board[0][2].equals(currentUser(user)) && board[1][1].equals(currentUser(user)) && board[2][0].equals(currentUser(user)))
				return true;
		
		return false;
	}
	
	public static void start() {
		createBoard();		
		Scanner kb = new Scanner(System.in);
		boolean user = false;
		
		while (true) {
			if (turns == 9) {
				System.out.println("The game is tied.");
				break;
			}
			printBoard();
			System.out.println("Enter a position 1 through 9.");
			int input = kb.nextInt() -1;
			
			if (!checkInput(input + 1)) {
				continue;
			} 	
			
			if (isTaken(coordinates(input), board)) {
				System.out.println("Please try again.");
				System.out.println();
				System.out.println();
				continue;
			} else {
				user = !user;
				addToBoard(coordinates(input), board, user);
				if (isWin(user,board)) {
					System.out.println(currentUser(user).toUpperCase() + " has won!");
					break;
				}
				
				turns++;
			}
		}		
	}
	

	
	public static void quit() {
		System.out.println("You have quit.");
		System.out.println("Thanks for playing");
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome users! This is a game of tic tac toe.");
		System.out.println("To mark a spot you must enter a position of 1 through 9.");
		System.out.println("Here is a board that will show you the positions");
		printPositionBoard();
		System.out.println();
		System.out.println();
		System.out.println("Enter \"r\" when ready. Enter anything else if you are not.");
		
		Scanner kb = new Scanner(System.in);
		
		if (kb.nextLine().equals("r")) {
			start();
		} else 
			quit();
	}
}
