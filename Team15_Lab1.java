import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Team15_Lab1 {

    public static void main(String[] args) {
        ChessPiece[] chessPieces = new ChessPiece[6];
        try { // Jonathan, Derek, Ugyen
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

        // Derek, Jonathan, Ugyen
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("First char must be a - h.\nSecond char must be 1-8.\nEnter position to move pieces: ");
            String toPos = scanner.nextLine();
            while (!validPos(toPos)) {
                System.out.println("That position is not on the chess board. Try again.");
                toPos = scanner.nextLine();
            }
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
        boolean print = false;
        String initPosXY = initPosX + "" + initPosY;
        String toPosXY = toPosX + "" + toPosY;
        if (initPosXY.equals(toPosXY)) {
            System.out.println(piece + " is already in " + toPosXY);
            return;
        }
        switch (piece) {
            case "King": // Derek
                print = (Math.abs(initPosX - toPosX) == 1 && initPosY == toPosY) ||
                        (Math.abs(initPosY - toPosY) == 1 && initPosX == toPosX) || 
                        (Math.abs(initPosX - toPosX) == 1 && Math.abs(initPosY - toPosY) == 1); 
                break;
            case "Rook": // Derek
                print = (initPosX == toPosX || initPosY == toPosY);
                break;
            case "Pawn": // Derek, Jonathan
                print = initPosX == toPosX && (toPosY - initPosY == 1 || (initPosY == 2 && toPosY == 4));
                break;
            case "Knight": // Derek, Ugyen
                print = (Math.abs(toPosY - initPosY) == 2 && Math.abs(toPosX - initPosX) == 1) ||
                        (Math.abs(toPosX - initPosX) == 2 && Math.abs(toPosY - initPosY) == 1);
                break;
            case "Queen": // Jonathan
                print = (Math.abs(toPosY - initPosY) == Math.abs(toPosX - initPosX) || 
                        (initPosX == toPosX) || (initPosY == toPosY));
                break;
            case "Bishop": // Derek, Jonathan
                print = (Math.abs(toPosY - initPosY) == Math.abs(toPosX - initPosX));
                break;
        }
        System.out.println(piece + " at " + initPosXY + (print ? " can " : " cannot ") + "move to " + toPosXY);  
    }

    // Jonathan, Derek, Ugyen
    static class ChessPiece {

        String piece;
        String team;
        String initPos;

        public ChessPiece(String piece, String team, String initPos) {
            this.piece = piece;
            this.team = team;
            this.initPos = initPos;
        }
    }
}
