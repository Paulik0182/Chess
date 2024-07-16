package com.nayya.chess.data

import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceStrategy

// Для фигуры "Конь"
object KnightStrategy : PieceStrategy {

    /**
     * Метод возвращает список доступных позиций, куда может походить пешка.
     */
    override fun calcAvailablePositions(
        from: ChessPosition, // текущую позицию Коня на шахматной доске
        field: List<List<ChessPiece?>>, // двумерный список, представляющий все фигуры на доске
    ): List<ChessPosition> {
        val validPositions = mutableListOf<ChessPosition>()
        val chessPiece = field[from.row][from.col] ?: throw IllegalStateException("Нет фигуры")

        // Возможные смещения для хода коня
        val offsets = listOf(
            Pair(-2, -1), Pair(-2, 1), Pair(-1, -2), Pair(-1, 2),
            Pair(1, -2), Pair(1, 2), Pair(2, -1), Pair(2, 1)
        )

        offsets.forEach { (rowDiff, colDiff) ->
            val toRow = from.row + rowDiff
            val toCol = from.col + colDiff

            // Проверяем, что позиция находится на доске
            if (isValidPosition(ChessPosition(toRow, toCol))) {
                // Если позиция пустая или на ней стоит фигура противника, добавляем ее в список доступных ходов
                if (isPositionEmpty(ChessPosition(toRow, toCol), field) ||
                    isPositionEnemy(ChessPosition(toRow, toCol), chessPiece.colorChess, field)
                ) {
                    validPositions.add(ChessPosition(toRow, toCol))
                }
            }
        }

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
        if (isPositionEmpty(chessPosition, field)) return false
        return field[chessPosition.row][chessPosition.col]?.colorChess != color
    }
}