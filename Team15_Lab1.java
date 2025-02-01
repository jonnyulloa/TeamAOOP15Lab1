
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
                    case "King": kingMovement(chessPieces[i].initPos, toPosition);
                    case "Rook": rookMovement(chessPieces[i].initPos, toPosition);
                    case "Pawn": pawnMovement(chessPieces[i].initPos, toPosition);
                    case "Knight": knightMovement(chessPieces[i].initPos, toPosition);
                    case "Queen": queenMovement(chessPieces[i].initPos, toPosition);
                    case "Bishop": bishopMovement(chessPieces[i].initPos, toPosition);
                    default:
                        System.out.println("Unknown Piece");
                }
            }
            scanner.close();
        }
    }

    public static boolean validPosition(String toPos) {
        return toPos.charAt(0) >= 'a' && toPos.charAt(0) <= 'h' && toPos.charAt(1) >= 1 && toPos.charAt(1) <= 8 && toPos.length() == 2;
    }
    static void kingMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        if (initPosX + 1 == posX || initPosX - 1 == posX || initPosY + 1 == posY || initPosY + 1 == posY) {
            System.out.println("");
        }
        return;
    }
    static void rookMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        if (initPosX == posX || initPosY == posY) {
            System.out.println("");
        }
        return;
    }
    static void pawnMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        if ((initPosX == posX && initPosY + 1 == posY) || (initPosX == posX && initPosY + 2 == posY && initPosY == '2')) {
            System.out.println("");
        }
        return;
    }
    static void knightMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        if ((initPosX + 2 == posX && initPosY + 1 == posY) ||
            (initPosX + 2 == posX && initPosY - 1 == posY) ||
            (initPosX + 1 == posX && initPosY + 2 == posY) ||
            (initPosX + 1 == posX && initPosY - 2 == posY) ||
            (initPosX - 2 == posX && initPosY + 1 == posY) ||
            (initPosX - 2 == posX && initPosY - 1 == posY) ||
            (initPosX - 1 == posX && initPosY + 2 == posY) ||
            (initPosX - 1 == posX && initPosY - 2 == posY)) {
            System.out.println("");
        }
        return;
    }
    static void queenMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        if (initPosX == posX || initPosY == posY || initPosX + (posY - initPosY) == posX || initPosX - (posY - initPosY) == posX) {
            System.out.println("");
        }
        return;
    }
    static void bishopMovement(String pos, String toPos) {
        char initPosX = pos.charAt(0);
        char initPosY = pos.charAt(1);
        char posX = toPos.charAt(0);
        char posY = toPos.charAt(1);
        if (initPosX + (posY - initPosY) == posX || initPosX - (posY - initPosY) == posX) {
            System.out.println("");
        }
        return;
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
