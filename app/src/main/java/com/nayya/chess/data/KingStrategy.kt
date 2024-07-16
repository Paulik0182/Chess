package com.nayya.chess.data

import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceStrategy

// Для фигуры "Король"
object KingStrategy : PieceStrategy {

    override fun calcAvailablePositions(
        from: ChessPosition,
        field: List<List<ChessPiece?>>,
    ): List<ChessPosition> {
        val validPositions = mutableListOf<ChessPosition>()
        val chessPiece = field[from.row][from.col] ?: throw IllegalStateException("Нет фигуры")

        // Проверяем ходы по диагонали
        var row = from.row - 1
        var col = from.col - 1
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        row = from.row - 1
        col = from.col + 1
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        row = from.row + 1
        col = from.col - 1
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        row = from.row + 1
        col = from.col + 1
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        // Проверяем ходы по вертикали
        row = from.row - 1
        col = from.col
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        row = from.row + 1
        col = from.col
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        // Проверяем ходы по горизонтали
        row = from.row
        col = from.col - 1
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        row = from.row
        col = from.col + 1
        if (isValidPosition(ChessPosition(row, col)) && (isPositionEmpty(
                ChessPosition(row, col),
                field
            ) || isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field))
        ) {
            validPositions.add(ChessPosition(row, col))
        }

        // TODO: Реализовать логику рокировки

        return validPositions
    }

    private fun isValidPosition(pos: ChessPosition): Boolean {
        return pos.row in 0..7 && pos.col in 0..7
    }

    private fun isPositionEmpty(
        chessPosition: ChessPosition,
        field: List<List<ChessPiece?>>,
    ): Boolean {
        return field[chessPosition.row][chessPosition.col] == null
    }

    private fun isPositionEnemy(
        chessPosition: ChessPosition,
        color: ColorChess,
        field: List<List<ChessPiece?>>,
    ): Boolean {
        if (isValidPosition(chessPosition) && isPositionEmpty(chessPosition, field)) return false
        return field[chessPosition.row][chessPosition.col]?.colorChess != color
    }
}