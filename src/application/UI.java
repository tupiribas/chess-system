package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	//public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	//public static final String ANSI_PURPLE = "\u001B[35m";
	//public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	//public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	//public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	//public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	//public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	//public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	//public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	//public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String CLEAR = "\033[H\033[2J";

	public static void clearScreen() {
		System.out.println(CLEAR);
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine().toLowerCase();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException(
					"ERRO cod.:09>>> Error reading ChessPosition. Valid values are from a1 to h8.");
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_GREEN + (8 - i) + " " + ANSI_RESET);
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println(ANSI_GREEN + "  a b c d e f g h" + ANSI_RESET);
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPiece(captured);
		System.out.println();
		System.out.println("Number of shifts: " + chessMatch.getTurn());
		if (!chessMatch.isCheckMate()) {
			System.out.print("Waiting player: ");
			if (chessMatch.getCurrentPlayer() == Color.WHITE) {
				System.out.println(ANSI_WHITE + chessMatch.getCurrentPlayer() + ANSI_RESET);
			} else {
				System.out.println(ANSI_YELLOW + chessMatch.getCurrentPlayer() + ANSI_RESET);
			}
			if (chessMatch.isCheck()) {
				System.out.println(ANSI_RED + "CHECK!" + ANSI_RESET);
			}
		} else {
			System.out.println(ANSI_RED + "CHECKMATE!" + ANSI_RESET);
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
		if (chessMatch.isCastling()) {
			System.out.println("MOVIMENT CASTLING");
		}
		if (chessMatch.isEnPassant()) {
			System.out.println("MOVIMENT EN PASSANT");
		}
		if (chessMatch.isPromotedConfirmation()) {
			System.out.println("MOVIMENT PROMOTED");
		}
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_GREEN + (8 - i) + " " + ANSI_RESET);
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println(ANSI_GREEN + "  a b c d e f g h" + ANSI_RESET);
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_PURPLE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	private static void printCapturedPiece(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.YELLOW)
				.collect(Collectors.toList());
		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Yellow: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}

}
