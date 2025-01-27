package chess.MovesCalc;

import chess.*;
import jdk.jfr.Percentage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int [][] locations = {{1,0},{0,1},{-1,0},{0,-1}};

        for(int[] location : locations){
            int row = position.getRow();
            int col = position.getColumn();

            while(true){
                row += location[0];
                col += location[1];

                if(row < 1 || row > 8 || col < 1 || col > 8){
                    break;
                }

                ChessPosition newPosition = new ChessPosition(row, col);
                ChessPiece newPiece = board.getPiece(newPosition);

                if(newPiece == null){
                    moves.add(new ChessMove(position, newPosition, null));
                }
                else if (newPiece.getTeamColor() != myColor) {
                    moves.add(new ChessMove(position, newPosition, null));
                    break;
                }
                else{
                    break;
                }

            }
        }
        return moves;
    }
}
