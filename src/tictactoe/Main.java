package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int availableCells = 9;
    private static boolean xCurrentTurn = true;
    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        Scanner lector = new Scanner(System.in);
        char[][] gameMatrix = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        printGameState(gameMatrix);
        boolean gameNotFinished = true;
        String state;
        do {
            updateGrid(gameMatrix, lector, xCurrentTurn);
            //to switch the turn between the player 'X' and the player 'O'
            //X player is the first to play
            xCurrentTurn = !xCurrentTurn;
            printGameState(gameMatrix);
            state = checkGameState(gameMatrix);
            switch (state) {
                case "X wins":
                case "O wins":
                case "Draw":
                    gameNotFinished = false;
                    break;
            }
        } while (gameNotFinished);
        System.out.println(state);
    }

    /*

    public static char[][] generateGameMatrix(String state) {
        char[][] gameMatrix = new char[3][3];
        int count = 0;
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                gameMatrix[i][j] = state.charAt(count);
                count++;
            }
        }
        return gameMatrix;
    }

     */

    public static void printGameState(char[][] gameMatrix) {
        System.out.println("---------");
        for (int i = 0; i < gameMatrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < gameMatrix[i].length; j++) {
                System.out.print(gameMatrix[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String checkGameState(char[][] gameMatrix) {
        int xInARow = 0;
        int oInARow = 0;
            //for the rows
            for (int i = 0; i < gameMatrix.length; i++) {
                for (int j = 0; j < gameMatrix[i].length; j++) {
                    if (gameMatrix[i][j] == 'X') {
                        xInARow++;
                    } else if (gameMatrix[i][j] == 'O') {
                        oInARow++;
                    }
                }
                if (xInARow == 3) {
                    return "X wins";
                } else if (oInARow == 3) {
                    return "O wins";
                } else {
                    xInARow = 0;
                    oInARow = 0;
                }
            }
            //for the columns
            for (int i = 0; i < gameMatrix.length; i++) {
                for (int j = 0; j < gameMatrix[i].length; j++) {
                    if (gameMatrix[j][i] == 'X') {
                        xInARow++;
                    } else if (gameMatrix[j][i] == 'O') {
                        oInARow++;
                    }
                }
                if (xInARow == 3) {
                    return "X wins";
                } else if (oInARow == 3) {
                    return "O wins";
                } else {
                    xInARow = 0;
                    oInARow = 0;
                }
            }
            //for the diagonal
            for (int i = 0; i < gameMatrix.length; i++) {
                if (gameMatrix[i][i] == 'X') {
                    xInARow++;
                } else if (gameMatrix[i][i] == 'O') {
                    oInARow++;
                }
            }

            if (xInARow == 3) {
                return "X wins";
            } else if (oInARow == 3) {
                return "O wins";
            } else {
                xInARow = 0;
                oInARow = 0;
            }

            //for the inverse diagonal
            for (int i = gameMatrix[0].length - 1, j = 0; i >= 0; i--, j++) {
                if (gameMatrix[i][j] == 'X') {
                    xInARow++;
                } else if (gameMatrix[i][j] == 'O') {
                    oInARow++;
                }
            }

            if (xInARow == 3) {
                return "X wins";
            } else if (oInARow == 3) {
                return "O wins";
            }

            if (availableCells > 0) {
                return "Game not finished";
            } else {
                return "Draw";
            }

    }

    /*

    public static String showGameState(char[][] gameMatrix) {
        boolean xWins = false;
        boolean oWins = false;
        int xCounter = 0;
        int xInARow = 0;
        int oCounter = 0;
        int oInARow = 0;
        int availableCellsCounter = 0;

        //to check the impossible condition
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[i][j] == 'X') {
                    xCounter++;
                } else if (gameMatrix[i][j] == 'O') {
                    oCounter++;
                } else {
                    availableCellsCounter++;
                }
            }
        }

        if (xCounter - oCounter >= 2 || oCounter - xCounter >= 2) {
            return "Impossible";
        }

        //for the rows
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[i][j] == 'X') {
                    xInARow++;
                } else if (gameMatrix[i][j] == 'O') {
                    oInARow++;
                }
            }
            if (xInARow == 3) {
                xWins = true;
            } else if (oInARow == 3) {
                oWins = true;
            } else {
                xInARow = 0;
                oInARow = 0;
            }
        }
        //for the columns
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[j][i] == 'X') {
                    xInARow++;
                } else if (gameMatrix[j][i] == 'O') {
                    oInARow++;
                }
            }
            if (xInARow == 3) {
                xWins = true;
            } else if (oInARow == 3) {
                oWins = true;
            } else {
                xInARow = 0;
                oInARow = 0;
            }
        }
        //for the diagonal
        for (int i = 0; i < gameMatrix.length; i++) {
            if (gameMatrix[i][i] == 'X') {
                xInARow++;
            } else if (gameMatrix[i][i] == 'O') {
                oInARow++;
            }
        }

        if (xInARow == 3) {
            xWins = true;
        } else if (oInARow == 3) {
            oWins = true;
        } else {
            xInARow = 0;
            oInARow = 0;
        }

        //for the inverse diagonal
        for (int i = gameMatrix[0].length - 1, j = 0; i >= 0 ; i--, j++) {
            if (gameMatrix[i][j] == 'X') {
                xInARow++;
            } else if (gameMatrix[i][j] == 'O') {
                oInARow++;
            }
        }

        if (xInARow == 3) {
            xWins = true;
        } else if (oInARow == 3) {
            oWins = true;
        }

        if (xWins && oWins) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (availableCellsCounter > 0) {
            return "Game not finished";
        } else {
            return "Draw";
        }
    }

     */

    public static void updateGrid(char[][] gameMatrix, Scanner scanner, boolean xCurrentTurn) {
        do {
            System.out.print("Enter the coordinates: ");
            try {
                int verticalCoord = scanner.nextInt();
                int horizontalCoord = scanner.nextInt();
                if ((verticalCoord < 1 || verticalCoord > 3) || (horizontalCoord < 1 || horizontalCoord > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (gameMatrix[verticalCoord - 1][horizontalCoord - 1] != 'X' && gameMatrix[verticalCoord - 1][horizontalCoord - 1] != 'O') {
                        if (xCurrentTurn) {
                            gameMatrix[verticalCoord - 1][horizontalCoord - 1] = 'X';
                            availableCells--;
                        } else {
                            gameMatrix[verticalCoord - 1][horizontalCoord - 1] = 'O';
                            availableCells--;
                        }
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } catch (InputMismatchException ime) {
                System.out.println("You should enter numbers!");
            }

        } while (true);
    }
}
