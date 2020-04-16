import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main (String [] args){
        //Initialize Game Board
        char [] [] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        printGameBoard(gameBoard);
        System.out.println("Enter your placement location (1-9, Left to Right & Top to Bottom)");

        //MAIN GAME LOOP
        while(true) {
            //User Input
            Scanner input = new Scanner(System.in);
            System.out.println("-----------------------");
            System.out.println("Your Move Captain!");

            //User Placement
            if (input.hasNextInt()) {
                int playerPosition = input.nextInt();
                //Check if positions are already filled, if so wait for next user integer
                while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                    System.out.println("Position Taken!");
                    playerPosition = input.nextInt();
                }
                //check if within domain
                if ((playerPosition > 0) && (playerPosition < 10)) {
                    placePiece(gameBoard, playerPosition, "player");
                    printGameBoard(gameBoard);
                } else {
                    System.out.println("Integer 1 through 9 only!");
                }
            }
            //Check Winner, After Player
            String result = checkWinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            System.out.println("-----------------------");

            //CPU Placement
            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            //Check if positions are already filled, if so generate another integer
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = random.nextInt(9) + 1;
            }
            System.out.println("CPU's Move!");
            placePiece(gameBoard, cpuPosition, "cpu");
            printGameBoard(gameBoard);

            //Check Winner, After CPU
            result = checkWinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }

        }
    }

    public static void printGameBoard (char [] [] gameBoard){
        for(char[] row : gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece (char [][] gameBoard, int position, String user){

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(position);
        }
        else if(user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position){
            case 1:
                gameBoard [0][0] = symbol;
                break;
            case 2:
                gameBoard [0][2] = symbol;
                break;
            case 3:
                gameBoard [0][4] = symbol;
                break;
            case 4:
                gameBoard [2][0] = symbol;
                break;
            case 5:
                gameBoard [2][2] = symbol;
                break;
            case 6:
                gameBoard [2][4] = symbol;
                break;
            case 7:
                gameBoard [4][0] = symbol;
                break;
            case 8:
                gameBoard [4][2] = symbol;
                break;
            case 9:
                gameBoard [4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(){

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List across1 = Arrays.asList(1, 5, 9);
        List across2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);

        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);

        winning.add(across1);
        winning.add(across2);

        for(List golden : winning){
            if(playerPositions.containsAll(golden)){
                return "Congratulations you have Won!";
            }
            else if(cpuPositions.containsAll(golden)){
                return "CPU Win's!";
            }
            else if(playerPositions.size()+cpuPositions.size() == 9){
                return "CAT's Game!";
            }
        }
        return "";
    }
}
