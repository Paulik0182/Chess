package com.nayya.chess.data

import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceStrategy

// Для фигуры "Королева"
object QueenStrategy : PieceStrategy {

    override fun calcAvailablePositions(
        from: ChessPosition, // текущую позицию Королевы на шахматной доске
        field: List<List<ChessPiece?>>, // двумерный список, представляющий все фигуры на доске
    ): List<ChessPosition> {
        val validPositions = mutableListOf<ChessPosition>()
        val chessPiece = field[from.row][from.col] ?: throw IllegalStateException("Нет фигуры")

        // Проверяем ходы по диагонали
        // Верхняя левая диагональ
        var row = from.row - 1
        var col = from.col - 1
        while (isValidPosition(ChessPosition(row, col))) {
            if (isPositionEmpty(ChessPosition(row, col), field)) {
                validPositions.add(ChessPosition(row, col))
            } else if (isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(row, col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
            row--
            col--
        }

        // Верхняя правая диагональ
        row = from.row - 1
        col = from.col + 1
        while (isValidPosition(ChessPosition(row, col))) {
            if (isPositionEmpty(ChessPosition(row, col), field)) {
                validPositions.add(ChessPosition(row, col))
            } else if (isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(row, col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
            row--
            col++
        }

        // Нижняя левая диагональ
        row = from.row + 1
        col = from.col - 1
        while (isValidPosition(ChessPosition(row, col))) {
            if (isPositionEmpty(ChessPosition(row, col), field)) {
                validPositions.add(ChessPosition(row, col))
            } else if (isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(row, col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
            row++
            col--
        }

        // Нижняя правая диагональ
        row = from.row + 1
        col = from.col + 1
        while (isValidPosition(ChessPosition(row, col))) {
            if (isPositionEmpty(ChessPosition(row, col), field)) {
                validPositions.add(ChessPosition(row, col))
            } else if (isPositionEnemy(ChessPosition(row, col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(row, col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
            row++
            col++
        }

        // Проверяем ходы по вертикали
        for (r in from.row - 1 downTo 0) {
            if (isPositionEmpty(ChessPosition(r, from.col), field)) {
                validPositions.add(ChessPosition(r, from.col))
            } else if (isPositionEnemy(ChessPosition(r, from.col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(r, from.col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
        }

        for (r in from.row + 1..7) {
            if (isPositionEmpty(ChessPosition(r, from.col), field)) {
                validPositions.add(ChessPosition(r, from.col))
            } else if (isPositionEnemy(ChessPosition(r, from.col), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(r, from.col))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
        }

        // Проверяем ходы по горизонтали
        for (c in from.col - 1 downTo 0) {
            if (isPositionEmpty(ChessPosition(from.row, c), field)) {
                validPositions.add(ChessPosition(from.row, c))
            } else if (isPositionEnemy(ChessPosition(from.row, c), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(from.row, c))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
            }
        }

        for (c in from.col + 1..7) {
            if (isPositionEmpty(ChessPosition(from.row, c), field)) {
                validPositions.add(ChessPosition(from.row, c))
            } else if (isPositionEnemy(ChessPosition(from.row, c), chessPiece.colorChess, field)) {
                validPositions.add(ChessPosition(from.row, c))
                break // Встретили фигуру, прекращаем проверку
            } else {
                break // Встретили фигуру своего цвета, прекращаем проверку
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
        if (isValidPosition(chessPosition) && isPositionEmpty(chessPosition, field)) return false
        return field[chessPosition.row][chessPosition.col]?.colorChess != color
    }
}