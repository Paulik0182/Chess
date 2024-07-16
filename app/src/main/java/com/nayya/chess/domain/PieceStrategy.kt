package com.nayya.chess.domain

interface PieceStrategy {

    fun calcAvailablePositions(
        from: ChessPosition,
        field: List<List<ChessPiece?>>,
    ): List<ChessPosition>

}