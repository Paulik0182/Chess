package com.nayya.chess.domain

data class ChessPiece (
    val id: Int,
    val type: PieceType,
    var position: ChessPosition,
    var isSelected: Boolean = false
)