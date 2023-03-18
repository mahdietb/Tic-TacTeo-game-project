import java.util.*;

public class Main {
    static Scanner get;
    static ArrayList<Integer> arraylist;
    static ArrayList<Integer> randomList;
    static Random rand;

    public static void main(String[] args) {

        get = new Scanner(System.in);
        rand = new Random();


        computerMovesList();

        staterMenu();


    }

    /**
     * This Method
     */
    public static void computerMovesList() {
        int n = 16;
        arraylist = new ArrayList<>(n);
        for (int i = 0; i < 16; i++) {
            arraylist.add(i);
        }
        Collections.shuffle(arraylist);
    }

    /**
     * This Method get cell numbers and put right character in a place
     * ,also it checks the limitation of number we can choose
     *
     * @param firstPlayer  first player character
     * @param secondPlayer second player character
     * @param move         each move in game
     * @param matrix       game board matrix
     * @param array        to save the movement
     */

    public static void printBoardForPlayer(String firstPlayer, String secondPlayer, int move, String[][] matrix, int[] array) {


        int length = 3;
        int flag = 0;

        while (flag == 0) {
            if ((move >= 0) && (move < 16)) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {

                        if ((move == ((4 * i) + j)) && (!Objects.equals(matrix[i][j], "#")) && (!Objects.equals(matrix[i][j], secondPlayer)) && (!Objects.equals(matrix[i][j], firstPlayer))) {
                            matrix[i][j] = firstPlayer;//
                            array[length] = move;
                            flag = 1;
                        } else if ((move == ((4 * i) + j)) && ((Objects.equals(matrix[i][j], secondPlayer)) || (Objects.equals(matrix[i][j], "#")) || (Objects.equals(matrix[i][j], firstPlayer)))) {
                            System.out.print("please try another number this one is already taken =");
                            move = get.nextInt();
                        }

                    }
                }
            } else {
                System.out.print("your number is not valid try another one =");
                move = get.nextInt();
            }

            springboard(matrix);
        }
    }


    /**
     * This Methode checks the possibility of wining for every player
     *
     * @param player character of each player "X" or "O"
     * @param matrix game board matrix
     * @param move   every movement of each player
     * @return flag to stop the while loop if one of the player wins
     */

    static int check(String player, String[][] matrix, int move) {
        int flag = 0;
        int index;
        int column = 0;
        int row = 0;
        int countForRow = 0;
        int countForColumn = 0;
        int sumForColumn = 0;
        int sumForRow = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (move == ((4 * i) + j)) {
                    row = i;
                    column = j;


                }
            }
        }

        for (int k = 0; k < 4; k++) {
            if (Objects.equals(matrix[row][k], player)) {
                index = (4 * row) + k;
                sumForRow += index;
                countForRow++;

            }
        }
        if ((countForRow == 3) && ((sumForRow % 3) == 0)) {
            flag = 1;
            System.out.println(player + "wins");
        } else if (countForRow == 4) {
            flag = 1;
            System.out.println(player + "wins");
        } else {
            for (int h = 0; h < 4; h++) {
                if (Objects.equals(matrix[h][column], player)) {
                    index = (4 * h) + column;
                    sumForColumn += index;
                    countForColumn++;
                }
            }

            if ((countForColumn == 3) && ((sumForColumn % 3) == 0)) {
                flag = 1;
                System.out.println(player + " wins");
            } else if (countForColumn == 4) {

                flag = 1;
                System.out.println(player + "wins");
            } else {
                if ((Objects.equals(matrix[0][0], player)) && (Objects.equals(matrix[1][1], player)) && (Objects.equals(matrix[2][2], player))) {
                    flag = 1;
                    System.out.println(player + " wins");

                } else if ((Objects.equals(matrix[1][1], player)) && (Objects.equals(matrix[2][2], player)) && (Objects.equals(matrix[3][3], player))) {
                    flag = 1;
                    System.out.println(player + " wins");
                } else if ((Objects.equals(matrix[0][3], player)) && (Objects.equals(matrix[1][2], player)) && (Objects.equals(matrix[2][1], player))) {
                    flag = 1;
                    System.out.println(player + " wins");
                } else if ((Objects.equals(matrix[1][2], player)) && (Objects.equals(matrix[2][1], player)) && (Objects.equals(matrix[3][0], player))) {
                    flag = 1;
                    System.out.println(player + " wins");
                } else if ((Objects.equals(matrix[1][3], player)) && (Objects.equals(matrix[2][2], player)) && (Objects.equals(matrix[3][1], player))) {
                    flag = 1;
                    System.out.println(player + " wins");
                } else if ((Objects.equals(matrix[0][2], player)) && (Objects.equals(matrix[1][1], player)) && (Objects.equals(matrix[2][0], player))) {

                    flag = 1;
                    System.out.println(player + " wins");
                } else if ((Objects.equals(matrix[0][1], player)) && (Objects.equals(matrix[1][2], player)) && (Objects.equals(matrix[2][3], player))) {
                    flag = 1;
                    System.out.println(player + " wins");
                } else if ((Objects.equals(matrix[1][0], player)) && (Objects.equals(matrix[2][1], player)) && (Objects.equals(matrix[3][2], player))) {
                    flag = 1;
                    System.out.println(player + " wins");
                }
            }
        }
        return flag;
    }


    /**
     * print game board after blocking three cells
     *
     * @param matrixBoard matrix of game board
     */
    public static void printBoard(String[][] matrixBoard) {
        int[] array;
        array = giveRandomMove();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int y = (4 * i) + j;

                if (y == array[0] || y == array[1] || y == array[2]) {
                    matrixBoard[i][j] = "#";
                } else {
                    String s = String.valueOf(y);
                    matrixBoard[i][j] = s;
                }
            }
        }

        springboard(matrixBoard);
    }


    /**
     * This Method is for printing movements of computer
     *
     * @param matrixBoard matrix of game
     * @param move        computer`s every move in arraylist
     * @return the move that computer made
     */

    public static int printForComputer(String[][] matrixBoard, int move) {
        System.out.println("it is computer`s turn =");

        int flag = 0;

        while (flag == 0) {
            move = arraylist.get(0);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((move == (4 * i) + j) && (!Objects.equals(matrixBoard[i][j], "#") && !Objects.equals(matrixBoard[i][j], "X") && !Objects.equals(matrixBoard[i][j], "O"))) {

                        matrixBoard[i][j] = "O";
                        arraylist.remove(0);
                        flag = 1;
                    } else if ((move == ((4 * i) + j)) && (Objects.equals(matrixBoard[i][j], "#") || Objects.equals(matrixBoard[i][j], "X") || Objects.equals(matrixBoard[i][j], "O"))) {

                        arraylist.remove(0);

                    }
                }
            }
        }
        springboard(matrixBoard);
        return move;
    }


    /**
     * THis Method get an arraylist and shuffle it that we can have an array of messed up numbers
     * ,so we can access to un duplicated random numbers for blocks
     *
     * @return array of messed up numbers
     */
    public static int[] giveRandomMove() {
        int[] array = new int[16];
        int n = 16;
        randomList = new ArrayList<>(n);
        for (int i = 0; i < 16; i++) {
            randomList.add(i);
        }
        Collections.shuffle(randomList);
        for (int i = 0; i < 16; i++) {
            array[i] = randomList.get(i);
        }

        return array;

    }


    /**
     * @param matrix This Method get matrix and print if for the first time
     */
    public static void springboard(String[][] matrix) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                System.out.printf("| %-2s ", matrix[i][j]);
            }
            System.out.println();
            if (i < 3) {
                System.out.println("------------------");
            }
        }
        System.out.println();
    }


    public static void staterMenu() {

        int x = 0;

        System.out.println();

        while (x != 3) {
            System.out.println();
            System.out.println("welcome to Tic-Tac-Teo game");

            System.out.println("please choose your competitor");


            System.out.println("1)Human");
            System.out.println("2)Computer");
            System.out.println("3)Exit");
            System.out.print("your choice =");
            x = get.nextInt();
            switch (x) {
                case 1:
                    chooseHuman();
                    break;
                case 2:

                    chooseComputer();
                    break;

                case 3:
                    break;


            }
        }

    }


    /**
     * This Method is for two player
     */
    public static void chooseHuman() {
        String returnKey;

        int countForX = 0;
        int countForO = 0;
        int[] array = new int[16];
        String firstPlayer;
        String secondPlayer;
        String[][] matrix = new String[4][4];
        int flag = 0;


        printBoard(matrix);

        while (flag == 0) {
            firstPlayer = "X";
            secondPlayer = "O";
            int firstPlayerCheck;
            int secondPlayerCheck;

            System.out.println("firstPlayer it is your turn ");
            System.out.print("please choose a cell =");
            int cellNumber = get.nextInt();
            countForX++;
            System.out.println();
            printBoardForPlayer(firstPlayer, secondPlayer, cellNumber, matrix, array);
            firstPlayerCheck = check(firstPlayer, matrix, cellNumber);
            if (firstPlayerCheck == 1) {
                System.out.println("If you want to play again press R otherwise press another alphabet key to get back to menu");
                System.out.print("returnKey =");
                returnKey = get.next();
                if (Objects.equals(returnKey, "R")) {
                    chooseHuman();
                } else {
                    flag = 1;
                }

            } else {

                if ((countForO + countForX) == 13) {
                    System.out.println("game became  tie");
                    System.out.println("If you want to play again press R otherwise press another alphabet key to get back to menu");
                    System.out.print("returnKey =");
                    returnKey = get.next();
                    if (Objects.equals(returnKey, "R")) {

                        chooseHuman();
                    } else {
                        flag = 1;
                    }


                } else {


                    System.out.println("secondPlayer it is your turn ");
                    System.out.print("please choose a cell =");
                    int cellNumber2 = get.nextInt();
                    countForO++;
                    System.out.println();
                    printBoardForPlayer(secondPlayer, firstPlayer, cellNumber2, matrix, array);
                    secondPlayerCheck = check(secondPlayer, matrix, cellNumber2);
                    if (secondPlayerCheck == 1) {
                        System.out.println("If you want to play again press R otherwise press another alphabet key to get back to menu");
                        System.out.print("returnKey =");
                        returnKey = get.next();
                        if (Objects.equals(returnKey, "R")) {
                            chooseHuman();
                        } else {
                            flag = 1;
                        }
                    }
                }
            }
        }
    }


    /**
     * This Method is for computer and human option
     */

    public static void chooseComputer() {
        int countForComputer = 0;
        String returnKey;
        String[][] matrix = new String[4][4];
        int[] array = new int[16];
        int countForPlayer = 0;
        int flag = 0;
        int playerMove;
        int computer1;
        int moveForComputer = 0;
        int playerCheck;
        int computerCheck;

        String computer = "O";
        String player = "X";
        printBoard(matrix);
        while (flag == 0) {

            System.out.println("it is your turn ");
            System.out.print("please choose a cell =");
            playerMove = get.nextInt();
            countForPlayer++;
            System.out.println();
            printBoardForPlayer(player, computer, playerMove, matrix, array);
            playerCheck = check(player, matrix, playerMove);
            if (playerCheck == 1) {
                System.out.println("If you want to play again press R otherwise press another alphabet key to get back to menu");
                System.out.print("returnKey =");
                returnKey = get.next();
                if (Objects.equals(returnKey, "R")) {
                    chooseComputer();
                } else {
                    flag = 1;
                }
            } else {


                if ((countForComputer + countForPlayer) == 13) {
                    System.out.println("game became  tie");
                    System.out.println("If you want to play again press R otherwise press another alphabet key to get back to menu");
                    System.out.print("returnKey =");
                    returnKey = get.next();
                    if (Objects.equals(returnKey, "R")) {
                        chooseComputer();
                    } else {
                        flag = 1;
                    }


                } else {

                    computer1 = printForComputer(matrix, moveForComputer);
                    countForComputer++;

                    computerCheck = check(computer, matrix, computer1);
                    if (computerCheck == 1) {
                        System.out.println("If you want to play again press R otherwise press another alphabet key to get back to menu");
                        System.out.print("returnKey =");
                        returnKey = get.next();
                        System.out.println();
                        if (Objects.equals(returnKey, "R")) {
                            chooseComputer();
                        } else {
                            flag = 1;
                        }

                    }


                }
            }
        }
    }
}

