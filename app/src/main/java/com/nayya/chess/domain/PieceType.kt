package com.nayya.chess.domain

enum class PieceType (val strategy: PieceStrategy) {
    PAWN(PawnStrategy),
    ROOK(PawnStrategy),
    KNIGHT(PawnStrategy),
    BISHOP(PawnStrategy),
    QUEEN(PawnStrategy),
    KING(PawnStrategy)
}