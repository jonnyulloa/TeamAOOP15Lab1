//Derek Gamboa, Jonathan Ulloa, Ugyen Dorji
//This program takes in a user input, which should be between a-h and 1-8 to ensure its inside of the chess board
//The program then takes the input and checks for each chess piece if the move is acceptable
//The move is based on each chess piece's rules for moving and where the chess piece is originally on the board

//Changelog
//1/27/2025 Derek: completed check movement methods for pawn, king, rook, and knight
//2/4/2025 Derek: Added comments explaining the methods
// 1/24/25 Jonathan: Added file reader, user input, and outline for chess class and chess piece movments
// 2/3/25 Jonathan: Simplified code

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Team15_Lab1 {

    public static void main(String[] args) {
        //Create a new class of chessPiece to brreak down our problem and assign
        //each chess piece with its corresponding attributes
        ChessPiece[] chessPieces = new ChessPiece[6];
        try { // Jonathan, Derek, Ugyen
            File file = new File("input.txt");
            try (Scanner scanner = new Scanner(file)) {
                int count = 0;
                while (scanner.hasNextLine()) {
                    // Create a file reader and insert the values from the input text file
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    chessPieces[count] = new ChessPiece(values[0], values[1], values[2] + values[3]);
                    count++;
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        }

        // Derek, Jonathan, Ugyen
        try (Scanner scanner = new Scanner(System.in)) {
            //This section gives instructions for the user to follow
            //And it takes the input which it will check if its valid using our validPos method
            System.out.println("First char must be a - h.\nSecond char must be 1-8.\nEnter position to move pieces: ");
            String toPos = scanner.nextLine();
            while (!validPos(toPos)) {
                System.out.println("That position is not on the chess board. Try again.");
                toPos = scanner.nextLine();
            }
            //If the input is valid, the next section will take the values from the class Chesspiece and 
            //input the values to be used in the checkMove method. This is done for each chess piece
            for (ChessPiece i : chessPieces) {
                if (validPos(i.initPos)) {
                    checkMove(i.piece, i.initPos.charAt(0), i.initPos.charAt(1), toPos.charAt(0), toPos.charAt(1));
                } else {
                    System.out.println("The initial position of " + i.initPos + " for the " + i.piece + " is not a valid position.");
                }
            }
            scanner.close();
        }
    }

    // Jonathan, Derek
    public static boolean validPos(String toPos) {
        return (toPos.length() == 2 && 
            toPos.charAt(0) >= 'a' && 
            toPos.charAt(0) <= 'h' && 
            toPos.charAt(1) >= '1' && 
            toPos.charAt(1) <= '8');
    }

    // Derek, Jonathan, Ugyen
    static void checkMove(String piece, char initPosX, char initPosY, char toPosX, char toPosY) {
        //Set out initial values to be used
        boolean print = false;
        String initPosXY = initPosX + "" + initPosY;
        String toPosXY = toPosX + "" + toPosY;
        //This if statement checks if the chess piece is already in the coordinate that the user wants to move to
        if (initPosXY.equals(toPosXY)) {
            System.out.println(piece + " is already in " + toPosXY);
            return;
        }
        //The program uses a switch case section to account for all the chess pieces possible
        switch (piece) {
            case "King": // Derek, Ugyen
                //The King can only move one square horizontally, vertically, or diagonally
                print = (Math.abs(initPosX - toPosX) == 1 && initPosY == toPosY) || //Check Horizontal move
                        (Math.abs(initPosY - toPosY) == 1 && initPosX == toPosX) || //Check Vertical Move
                        (Math.abs(initPosX - toPosX) == 1 && Math.abs(initPosY - toPosY) == 1); //Check diagonal move
                break;
            case "Rook": // Derek, Ugyen
                //The Rook can only move vertically or horizontally, with no limits on squares
                print = (initPosX == toPosX || initPosY == toPosY); //Check horizontal move first then vertical
                break;
            case "Pawn": // Derek, Jonathan
                //The pawn can only move one square forward or two squares forward if its the first move its made in the game
                print = initPosX == toPosX && (toPosY - initPosY == 1 || (initPosY == 2 && toPosY == 4)); //First check if its move forward once or if its move forward twice at the beginning of the game
                break;
            case "Knight": // Derek, Ugyen
                //The knight can move one square in one direction then two squares at an angle
                print = (Math.abs(toPosY - initPosY) == 2 && Math.abs(toPosX - initPosX) == 1) ||
                        (Math.abs(toPosX - initPosX) == 2 && Math.abs(toPosY - initPosY) == 1); //Check whether the knight moved once in one direction then twice in the other
                                                                                                //Using absolute value, we can check both directions
                break;
            case "Queen": // Jonathan, Ugyen
                //The queen can move vertically, horizontally, and diagonally with no limits on the number of squares
                print = (Math.abs(toPosY - initPosY) == Math.abs(toPosX - initPosX) || 
                        (initPosX == toPosX) || (initPosY == toPosY)); //First check if the queen moved vertically, horizontally, then vertically
                break;
            case "Bishop": // Derek, Jonathan
                //The Bishop can move vertically any number of squares
                print = (Math.abs(toPosY - initPosY) == Math.abs(toPosX - initPosX)); //Using absolute value, the number of squares moved in the horizontal must equal 
                                                                                    // the number of squares moved in the vertical 
                break;
        }
        System.out.println(piece + " at " + initPosXY + (print ? " can " : " cannot ") + "move to " + toPosXY);  
    }

    // Jonathan, Derek, Ugyen
    static class ChessPiece {
        //Our attributes that will be assigned from the text file
        String piece;
        String team;
        String initPos;

        //Constructor
        public ChessPiece(String piece, String team, String initPos) {
            this.piece = piece;
            this.team = team;
            this.initPos = initPos;
        }
    }
}
