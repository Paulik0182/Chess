package com.nayya.chess.domain

import com.nayya.chess.data.KnightStrategy
import com.nayya.chess.data.PawnStrategy

enum class PieceType (val strategy: PieceStrategy) {
    PAWN(PawnStrategy),
    ROOK(PawnStrategy),
    KNIGHT(KnightStrategy),
    BISHOP(PawnStrategy),
    QUEEN(PawnStrategy),
    KING(PawnStrategy)
}