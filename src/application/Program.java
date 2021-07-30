package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {
	
	/* TODO All "error" exceptions are listed in order 
	 * of creation "Ex: Error 1, 2, 3, 4...: ". 
	 * To facilitate the search for errors.*/
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();
				System.out.println("Jogo de xadrez");
				UI.printMatch(chessMatch);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
	
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
	
				ChessPiece capturePiece = chessMatch.performChessMove(source, target);
			} 
			catch (ChessException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Type ENTER TO CONTINUE PLAYING.");
				sc.nextLine();
			}
			catch (InputMismatchException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Type ENTER TO CONTINUE PLAYING.");
				sc.nextLine();
			}
		}
	}
}
