package com.nayya.chess.domain

import com.nayya.chess.data.BishopStrategy
import com.nayya.chess.data.KnightStrategy
import com.nayya.chess.data.PawnStrategy
import com.nayya.chess.data.QueenStrategy
import com.nayya.chess.data.RookStrategy

enum class PieceType (val strategy: PieceStrategy) {
    PAWN(PawnStrategy),
    ROOK(RookStrategy),
    KNIGHT(KnightStrategy),
    BISHOP(BishopStrategy),
    QUEEN(QueenStrategy),
    KING(PawnStrategy)
}