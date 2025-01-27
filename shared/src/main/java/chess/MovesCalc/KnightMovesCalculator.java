package chess.MovesCalc;

import chess.*;
import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int [][] locations = {{1,2},{1,-2},{2,1},{2,-1},{-2,1},{-2,-1},{-1,2},{-1,-2}};

        for(int[] location : locations){
            int row = position.getRow();
            int col = position.getColumn();

            row += location[0];
            col += location[1];

            if(row >= 1 && row <= 8 && col >= 1 && col <= 8){
                ChessPosition newPosition = new ChessPosition(row, col);
                ChessPiece newPiece = board.getPiece(newPosition);

                if(newPiece == null){
                    moves.add(new ChessMove(position, newPosition, null));
                }
                else if (newPiece.getTeamColor() != myColor) {
                    moves.add(new ChessMove(position, newPosition, null));
                }
            }
        }
        return moves;
    }
}
