package chess.MovesCalc;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessGame.TeamColor myColor = board.getPiece(position).getTeamColor();
        int promotionRow;
        int direction;

        if(myColor == ChessGame.TeamColor.WHITE){
            direction = 1;
            promotionRow = 8;
        }
        else{
            direction = -1;
            promotionRow = 1;
        }


//        one step forward
        ChessPosition oneStepForward = new ChessPosition(position.getRow() + direction, position.getColumn());
        ChessPiece onePieceForward = board.getPiece(oneStepForward);
        if(onePieceForward == null){
            if(oneStepForward.getRow() >= 1 && oneStepForward.getRow() <= 8 && oneStepForward.getColumn() >= 1 && oneStepForward.getColumn() <= 8){
                if(oneStepForward.getRow() == promotionRow){
                    for(ChessPiece.PieceType promotion : ChessPiece.PieceType.values()){
                        if(promotion != ChessPiece.PieceType.PAWN && promotion != ChessPiece.PieceType.KING){
                            moves.add(new ChessMove(position, oneStepForward, promotion));

                        }
                    }
                }
                else{
                    moves.add(new ChessMove(position, oneStepForward, null));
                }
            }
        }

//        two step and cowboy boogie
        if(onePieceForward == null){
            if(myColor == ChessGame.TeamColor.WHITE && position.getRow() == 2 || myColor == ChessGame.TeamColor.BLACK && position.getRow() == 7){
                ChessPosition twoStepFoward = new ChessPosition(position.getRow() + direction * 2, position.getColumn());
                if(board.getPiece(oneStepForward) == null && board.getPiece(twoStepFoward) == null){
                    moves.add(new ChessMove(position, twoStepFoward, null));
                }
            }
        }

//        let's do this one more time, capture time
        int[] adjacent = {-1,1};
        for(int adj : adjacent){
            ChessPosition capture = new ChessPosition(position.getRow() + direction, position.getColumn() + adj);
            if(capture.getRow() >= 1 && capture.getRow() <= 8 && capture.getColumn() >= 1 && capture.getColumn() <= 8){
                ChessPiece capturedPiece = board.getPiece(capture);
                if(capturedPiece != null && myColor != capturedPiece.getTeamColor()){
                    if(capture.getRow() == promotionRow){
                        for(ChessPiece.PieceType promotion : ChessPiece.PieceType.values()){
                            if(promotion != ChessPiece.PieceType.PAWN && promotion != ChessPiece.PieceType.KING){
                                moves.add(new ChessMove(position, capture, promotion));

                            }
                        }
                    }
                    else {
                        moves.add(new ChessMove(position, capture, null));
                    }
                }
            }
        }

        return moves;
    }
}
