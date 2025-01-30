
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Team15_Lab1 {

    public static void main(String[] args) {
        ChessPiece[] chessPieces = new ChessPiece[6];
        try {
            File file = new File("input.txt");
            try (Scanner scanner = new Scanner(file)) {
                int count = 0;
                while (scanner.hasNextLine()) {
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

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter position to move pieces (e.g. e2, c3, h8): ");
            String toPosition = scanner.nextLine();
            while (!validPosition(toPosition)) {
                System.out.println("That position is not on the chess board. Try again.");
                toPosition = scanner.nextLine();
            }
            for (int i = 0; i < chessPieces.length; i++) {
                switch (chessPieces[i].piece) {
                    case "King": 
                        kingMovement(chessPieces[i].initPos, toPosition);
                        break;
                    case "Rook": 
                        rookMovement(chessPieces[i].initPos, toPosition);
                        break;
                    case "Pawn": 
                        pawnMovement(chessPieces[i].initPos, toPosition);
                        break;
                    case "Knight": 
                        knightMovement(chessPieces[i].initPos, toPosition);
                        break;
                    case "Queen": 
                        queenMovement(chessPieces[i].initPos, toPosition);
                        break;
                    case "Bishop": 
                        bishopMovement(chessPieces[i].initPos, toPosition);
                        break;
                    default:
                        System.out.println("Unknown Piece");
                }
            }
            scanner.close();
        }
    }

    public static boolean validPosition(String toPos) {
        if (toPos.length() != 2){
            return false;
        }
        return (toPos.charAt(0) >= 'a' && toPos.charAt(0) <= 'h' && toPos.charAt(1) >= '1' && toPos.charAt(1) <= '8');
    }
    private static void kingMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        int initPosY = Character.getNumericValue(pos.charAt(1));
        
        char posX = toPos.charAt(0);
        int posY = Character.getNumericValue(toPos.charAt(1));

        
        if (initPosX == posX && initPosY == posY){
            System.out.println("The king is already in the coordinate you want to move to");
            return;
        }
        
        if ((Math.abs(initPosX - posX) == 1 && initPosY == posY) || 
                (Math.abs(initPosY - posY) == 1 && initPosX == posX) || 
                (Math.abs(initPosX - posX) == 1 && Math.abs(initPosY - posY) == 1)){
            System.out.println("The move is acceptable for the King piece");
        }
        else{
            System.out.println("The move is not acceptable for the King piece");
        }
    }
    private static void rookMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        int initPosY = Character.getNumericValue(pos.charAt(1));
        
        char posX = toPos.charAt(0);
        int posY = Character.getNumericValue(toPos.charAt(1));

        if (initPosX == posX && initPosY == posY){
            System.out.println("The rook is already in the coordinate you want to move to");
            return;
        }
        
        if (initPosX == posX || initPosY == posY) {
            System.out.println("The move is acceptable");
        }
        else{
            System.out.println("The move is not acceptable for the Rook piece");
        }
    }
    
    private static void pawnMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        return;
    }
    private static void knightMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        return;
    }
    private static void queenMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        return;
    }
    private static void bishopMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        return;
    }
}

class ChessPiece {

    String piece;
    String team;
    String initPos;

    ChessPiece(String piece, String team, String initPos) {
        this.piece = piece;
        this.team = team;
        this.initPos = initPos;
    }
}
