package com.nayya.chess.data

import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceStrategy

// Для фигуры "Ладья"
object RookStrategy : PieceStrategy {

    /**
     * Метод возвращает список доступных позиций, куда может походить пешка.
     */
    override fun calcAvailablePositions(
        from: ChessPosition, // текущую позицию Коня на шахматной доске
        field: List<List<ChessPiece?>>, // двумерный список, представляющий все фигуры на доске
    ): List<ChessPosition> {
        val validPositions = mutableListOf<ChessPosition>()
        val chessPiece = field[from.row][from.col] ?: throw IllegalStateException("Нет фигуры")

        // Проверяем доступные ходы по вертикали
        for (row in from.row - 1 downTo 0) {
            if (isPositionEmpty(ChessPosition(row, from.col), field)) {
                validPositions.add(ChessPosition(row, from.col))
            } else if (isPositionEnemy(ChessPosition(row, from.col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(row, from.col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
        }

        for (row in from.row + 1..7) {
            if (isPositionEmpty(ChessPosition(row, from.col), field)) {
                validPositions.add(ChessPosition(row, from.col))
            } else if (isPositionEnemy(ChessPosition(row, from.col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(row, from.col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
        }

        // Проверяем доступные ходы по горизонтали
        for (col in from.col - 1 downTo 0) {
            if (isValidPosition(ChessPosition(from.row, col)) && isPositionEmpty(ChessPosition(from.row, col), field)) {
                validPositions.add(ChessPosition(from.row, col))
            } else if (isValidPosition(ChessPosition(from.row, col)) && isPositionEnemy(ChessPosition(from.row, col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(from.row, col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета или позиция вне доски, прекращаем проверку
            }
        }

        for (col in from.col + 1..7) {
            if (isValidPosition(ChessPosition(from.row, col)) && isPositionEmpty(ChessPosition(from.row, col), field)) {
                validPositions.add(ChessPosition(from.row, col))
            } else if (isValidPosition(ChessPosition(from.row, col)) && isPositionEnemy(ChessPosition(from.row, col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(from.row, col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета или позиция вне доски, прекращаем проверку
            }
        }

        return validPositions
    }

    private fun isValidPosition(pos: ChessPosition): Boolean {
        return pos.row in 0..7 && pos.col in 0..7
    }

    private fun isPositionEmpty(
        chessPosition: ChessPosition,
        field: List<List<ChessPiece?>>
    ): Boolean {
        return field[chessPosition.row][chessPosition.col] == null
    }

    private fun isPositionEnemy(
        chessPosition: ChessPosition,
        color: ColorChess,
        field: List<List<ChessPiece?>>
    ): Boolean {
        if (isValidPosition(chessPosition) && isPositionEmpty(chessPosition, field)) return false
        return field[chessPosition.row][chessPosition.col]?.colorChess != color
    }
}