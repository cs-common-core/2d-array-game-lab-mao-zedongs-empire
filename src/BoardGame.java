package src;
import java.util.Scanner;
public class BoardGame {
    private String[][] board;
    private int playerRow;
    private int playerCol;
    private boolean player;
    Scanner scanner;
    
    public BoardGame(int rows, int cols) {
        board = new String[rows][cols];
        // Initialize the board with empty spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = "-";
            }
        }
        player = true;
        scanner = new Scanner(System.in);
    }
    
    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void play(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("What piece do you want to move, enter coordinates (columnletter, row number)?");
        
        char columnLetter = input.charAt(0);
        int currentColumn = (int)(columnLetter - 'A'); 
        int currentRow = Integer.parseInt(input.substring(2)) - 1; 
        
        input = scanner.nextLine();
        System.out.println("Where do you want to move it, enter coordinates (columnletter, row number)?");
        int movedColumn = (int)(columnLetter - 'A'); 
        int movedRow = Integer.parseInt(input.substring(2)) - 1;
        
        if (validMove(currentColumn, currentRow, movedColumn, movedRow) == true){
            board[movedColumn][movedRow] = board[currentColumn][currentRow];
            board[currentColumn][currentRow] = "-";
            
            if (player = true){
                if(board[(currentColumn + movedColumn)/2][(currentRow + movedRow)/2].equals("b") || board[(currentColumn + movedColumn)/2][(currentRow + movedRow)/2].equals("B")){
                    board[(currentColumn + movedColumn)/2][(currentRow + movedRow)/2] = "-";
                }
            }   else {
                board[movedColumn][movedRow] = board[currentColumn][currentRow];
                board[currentColumn][currentRow] = "-";
            }

            if (player = false){
                if(board[(currentColumn + movedColumn)/2][(currentRow + movedRow)/2].equals("w") || board[(currentColumn + movedColumn)/2][(currentRow + movedRow)/2].equals("W")){
                    board[(currentColumn + movedColumn)/2][(currentRow + movedRow)/2] = "-";
                }
                else {
                    board[movedColumn][movedRow] = board[currentColumn][currentRow];
                    board[currentColumn][currentRow] = "-";
                }
            }
                
        }else{
            System.out.println("Invalid move. Try again.");
            play();
        }
    }

    public boolean validMove(int currentColumn, int currentRow, int movedColumn, int movedRow){
        if(!board[movedColumn][movedRow].equals("-") || movedColumn > 7 || movedColumn < 0 || movedRow > 7 || movedRow < 0){
            return false;
        }else if(Math.abs(movedRow-currentRow)==1 && Math.abs(movedColumn-movedColumn)==1){
            if(board[movedColumn][movedRow].equals("w") || board[movedColumn][movedRow].equals("b")){
                if(!(Math.abs(movedColumn-currentColumn)==1) && !(movedRow == currentRow+1)){
                    return false;
                }
            }else if(board[movedColumn][movedRow].equals("B") || board[movedColumn][movedRow].equals("B")){
                if(!(Math.abs(movedColumn-currentColumn)==1) && !(Math.abs(currentRow-movedRow)==1)){
                    return false;
                }
            }
        }else if(Math.abs(currentRow-movedRow)>2 || Math.abs(currentColumn-movedColumn)>2){
            return false;
        }else if(board[currentColumn][currentRow].equals("w")||board[currentColumn][currentRow].equals("W")){
            if(board[(currentColumn+movedColumn)/2][(currentRow+movedRow)/2].equals("b")||board[(currentColumn+movedColumn)/2][(currentRow+movedRow)/2].equals("B")){
                return true;
            }
        }else{
            if(board[(currentColumn+movedColumn)/2][(currentRow+movedRow)/2].equals("w")||board[(currentColumn+movedColumn)/2][(currentRow+movedRow)/2].equals("W")){
                return true;
            }
        }
        return true;
    }

    // Students will implement these methods
    public void movePlayer(String direction) {
        // TODO: Implement player movement (up, down, left, right)
    }
    
    public boolean isGameOver() {
        int counterOne = 0;
        int counterTwo = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals("w") || board[i][j].equals("W")) {
                    counterOne++;
                } else if (board[i][j].equals("b") || board[i][j].equals("B")) {
                    counterTwo++;
                }
            }
        }

        if (counterOne == 0 || counterTwo == 0) {
            return true;
        }
        return false;
    }
} 