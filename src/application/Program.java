package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();
				System.out.println("Jogo de xadrez");
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
	
				System.out.println();
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
	
				ChessPiece capturePiece = chessMatch.performChessMove(source, target);
			} 
			catch (ChessException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Digite ENTER PARA CONTINUAR JOGANDO");
				sc.nextLine();
			}
			catch (InputMismatchException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Digite ENTER PARA CONTINUAR JOGANDO");
				sc.nextLine();
			}
		}
	}
}
