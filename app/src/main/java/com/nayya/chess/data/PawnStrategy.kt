package com.nayya.chess.data

import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceStrategy

object PawnStrategy : PieceStrategy {

    /**
     * Метод возвращает список доступных позиций, куда может походить пешка.
     */
    override fun calcAvailablePositions(
        from: ChessPosition, // текущую позицию пешки на шахматной доске
        field: List<List<ChessPiece?>>, // двумерный список, представляющий все фигуры на доске
    ): List<ChessPosition> {

        val validPositions = mutableListOf<ChessPosition>() //  список в который будут добавляться все доступные ходы пешки.

        // стартовая позиция (Пешка, для определения цвета)
        val chessPiece = field[from.row][from.col] ?: throw IllegalStateException("Нет фигуры")

        // ввели направление пешки (Если пешка черного цвета, то направление движения вперед (+1 по вертикали), если белого, то назад (-1 по вертикали).)
        val colDiff = if (chessPiece.colorChess == ColorChess.BLACK) 1 else -1

        // проверяет, является ли текущая позиция пешки ее начальной позицией
        val isStartPosition = isStartPosition(from, colDiff)


        /**
         * вычисляются позиции на одну и две клетки вперед от текущей позиции пешки. Затем
         * проверяется, являются ли эти позиции валидными (находятся ли они на доске) и пустыми
         * (нет фигур). Если одношаговая позиция валидна и пуста, она добавляется в validPositions.
         * Если это начальная позиция пешки, и двухшаговая позиция также валидна и пуста, она
         * также добавляется в validPositions.
         */
        val oneStep = ChessPosition(from.row + colDiff, from.col)
        val twoStep = ChessPosition(from.row + (colDiff * 2), from.col)
        if (isValidPosition(oneStep) && isPositionEmpty(oneStep, field)) {
            validPositions.add(oneStep)
            if (isStartPosition && isValidPosition(twoStep) && isPositionEmpty(twoStep, field)) {
                validPositions.add(twoStep)
            }
        }

        // проверка на наличее фигур которые можно "съесть"
        val leftAttack = ChessPosition(from.row + colDiff, from.col + 1)
        val rightAttack = ChessPosition(from.row + colDiff, from.col - 1)

        //  в лево
        if (isValidPosition(leftAttack) && isPositionEnemy(
                leftAttack,
                chessPiece.colorChess,
                field
            )
        ) {
            validPositions.add(leftAttack) //  возвращается список validPositions, содержащий все доступные ходы для данной пешки.

        }
        // в право
        if (isValidPosition(rightAttack) && isPositionEnemy(
                rightAttack,
                chessPiece.colorChess,
                field
            )
        ) {
            validPositions.add(rightAttack) //  возвращается список validPositions, содержащий все доступные ходы для данной пешки.
        }

        return validPositions
    }

    /**
     * метод который проверяет, является ли текущая позиция пешки ее начальной
     * позицией. Это необходимо, чтобы определить, может ли пешка сделать ход в две клетки вперед.
     */
    private fun isStartPosition(position: ChessPosition, diff: Int): Boolean {
        return if (diff > 0) {
            position.row == 1
        } else {
            position.row == 6
        }
    }

    /**
     * метод должен проверять, что позиция pos находится в пределах шахматной доски.
     */
    private fun isValidPosition(pos: ChessPosition): Boolean {
        return pos.row in 0..7 && pos.col in 0..7
    }

    /**
     * метод проверяет,  пуста ли заданная позиция chessPosition на шахматной доске field, то есть,
     * нет ли на ней никакой фигуры.
     */
    private fun isPositionEmpty(
        chessPosition: ChessPosition,
        field: List<List<ChessPiece?>>,
    ): Boolean {
        return field[chessPosition.row][chessPosition.col] == null
    }

    /**
     * метод проверяет, содержит ли заданная позиция chessPosition на доске field фигуру противника
     * по отношению к цвету color. Сначала проверяется, что позиция не пуста, и если это так, то
     * проверяется, что цвет фигуры на этой позиции отличается от color.
     */
    private fun isPositionEnemy(
        chessPosition: ChessPosition,
        color: ColorChess,
        field: List<List<ChessPiece?>>,
    ): Boolean {
        if (isPositionEmpty(chessPosition, field)) return false
        return field[chessPosition.row][chessPosition.col]?.colorChess != color
    }
}