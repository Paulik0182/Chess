package com.nayya.chess.ui.chess_field

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceType

class ChessFieldViewModel(
    private val context: Context,
    private val arrayButtons: ArrayList<ArrayList<ImageView>>,
) : ViewModel() {

    var activeColor = ColorChess.WHITE
    private var selectedPiece: ChessPiece? = null
    private var selectedFrom: ChessPosition? = null

    // поле для хранения подсвеченных позиций
    val highlightedPositions = MutableLiveData<List<ChessPosition>>()
    val completingMove = MutableLiveData<Boolean>(false)

    private val _field: ArrayList<ArrayList<ChessPiece?>> = arrayListOf(
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null),
        arrayListOf(null, null, null, null, null, null, null, null)
    )

    private val necropolis: ArrayList<ChessPiece> = arrayListOf()

    val field: LiveData<ArrayList<ArrayList<ChessPiece?>>> = MutableLiveData(_field)

    init {
        generateStartPositionBlack()
        generateStartPositionWhite()
    }

    fun move(from: ChessPosition, to: ChessPosition) {
        val piece = _field[from.row][from.col]
        val victim = _field[to.row][to.col]

        if (piece == null) throw IllegalStateException("Нет фигуры ${from.row}, ${from.col}")

        if (piece.colorChess != activeColor) throw IllegalStateException("Вы не можите ходить фигурой другого цвета")

        if (victim != null && victim.colorChess == piece.colorChess) throw IllegalStateException("Нельзя есть фигуру своего цвета")

        if (victim != null) necropolis.add(victim)

        _field[to.row][to.col] = piece

        _field[from.row][from.col] = null


        activeColor = if (activeColor == ColorChess.WHITE) {
            ColorChess.BLACK
        } else {
            ColorChess.WHITE
        }

        notifyFieldChanged()
    }

    private fun generateStartPositionBlack() {
        val blackKnight = ChessPiece(
            id = 1,
            type = PieceType.KNIGHT,
            position = null,
            colorChess = ColorChess.BLACK
        )

        val blackRook = ChessPiece(
            id = 2,
            type = PieceType.ROOK,
            position = null,
            colorChess = ColorChess.BLACK
        )

        val blackQueen = ChessPiece(
            id = 3,
            type = PieceType.QUEEN,
            position = null,
            colorChess = ColorChess.BLACK
        )

        val blackKing = ChessPiece(
            id = 4,
            type = PieceType.KING,
            position = null,
            colorChess = ColorChess.BLACK
        )

        val blackBishop = ChessPiece(
            id = 5,
            type = PieceType.BISHOP,
            position = null,
            colorChess = ColorChess.BLACK
        )

        val blackPawn = ChessPiece(
            id = 6,
            type = PieceType.PAWN,
            position = null,
            colorChess = ColorChess.BLACK
        )

        _field[0][1] = blackKnight // b8
        _field[0][6] = blackKnight // g8

        _field[0][0] = blackRook // a8
        _field[0][7] = blackRook // h8

        _field[0][3] = blackQueen // d8
        _field[0][4] = blackKing // e8

        _field[0][2] = blackBishop // c8
        _field[0][5] = blackBishop // f8

        _field[1][0] = blackPawn // a7
        _field[1][1] = blackPawn // b7
        _field[1][2] = blackPawn // c7
        _field[1][3] = blackPawn // d7
        _field[1][4] = blackPawn // c7
        _field[1][5] = blackPawn // f7
        _field[1][6] = blackPawn // g7
        _field[1][7] = blackPawn // h7

        notifyFieldChanged()
    }

    private fun generateStartPositionWhite() {
        val whiteKnight = ChessPiece(
            id = 7,
            type = PieceType.KNIGHT,
            position = null,
            colorChess = ColorChess.WHITE
        )

        val whiteRook = ChessPiece(
            id = 8,
            type = PieceType.ROOK,
            position = null,
            colorChess = ColorChess.WHITE
        )

        val whiteQueen = ChessPiece(
            id = 9,
            type = PieceType.QUEEN,
            position = null,
            colorChess = ColorChess.WHITE
        )

        val whiteKing = ChessPiece(
            id = 10,
            type = PieceType.KING,
            position = null,
            colorChess = ColorChess.WHITE
        )

        val whiteBishop = ChessPiece(
            id = 11,
            type = PieceType.BISHOP,
            position = null,
            colorChess = ColorChess.WHITE
        )

        val whitePawn = ChessPiece(
            id = 12,
            type = PieceType.PAWN,
            position = null,
            colorChess = ColorChess.WHITE
        )

        _field[7][1] = whiteKnight // b1
        _field[7][6] = whiteKnight // g1

        _field[7][0] = whiteRook // a1
        _field[7][7] = whiteRook // h1

        _field[7][3] = whiteQueen // d1
        _field[7][4] = whiteKing // e1

        _field[7][2] = whiteBishop // c1
        _field[7][5] = whiteBishop // f1

        _field[6][0] = whitePawn // a2
        _field[6][1] = whitePawn // b2
        _field[6][2] = whitePawn // c2
        _field[6][3] = whitePawn // d2
        _field[6][4] = whitePawn // c2
        _field[6][5] = whitePawn // f2
        _field[6][6] = whitePawn // g2
        _field[6][7] = whitePawn // h2

        notifyFieldChanged()
    }

    fun onPositionSelected(clickPosition: ChessPosition) {
        if (selectedPiece == null || selectedFrom == null) {
            // Выберите фигуру для хода
            selectPiece(clickPosition)

            completingMove.value = false
        } else {
            // Переместите фигуру
            if (canPawnMove(clickPosition)) {
                try {
                    move(selectedFrom!!, clickPosition)
                    selectedPiece = null
                    selectedFrom = null

                    completingMove.value = true
                } catch (e: IllegalStateException) {
                    // Обработка ошибок при ходе
                    println(e.message)
                }
            } else {
                // Ход невозможен
                selectedPiece
            }
        }
    }

    private fun selectPiece(clickPosition: ChessPosition) {
        val piece = field.value?.get(clickPosition.row)?.get(clickPosition.col)
        if (piece != null && piece.colorChess == activeColor) {
            selectedPiece = piece
            selectedFrom = clickPosition

            // Получаем доступные ходы для выбранной фигуры
            val availableMoves = selectedPiece?.type?.strategy?.calcAvailablePositions(
                selectedFrom!!,
                _field
            )

            // Если у фигуры нет доступных ходов, сбрасываем выбор
            if (availableMoves.isNullOrEmpty()) {
                selectedPiece = null
                selectedFrom = null
                Toast.makeText(context, "Нет доступных ходов", Toast.LENGTH_SHORT).show()
            } else {
                highlightedPositions.postValue(availableMoves)
            }

        } else {
            if (piece != null) {
                Toast.makeText(context, "Сейчас не ваш ход", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Выберите фигуру", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun notifyFieldChanged() {
        (field as MutableLiveData<ArrayList<ArrayList<ChessPiece?>>>).value = _field
    }

    /**
     * По задумке метод должен проверять - может ли пешка совершить ход с позиции from на позицию to.
     * Он возвращает true, если ход является допустимым, и false в противном случае.
     *
     * Основные шаги метода:
     *
     * Получает пешку, стоящую на позиции from, и проверяет, что она принадлежит активному игроку.
     * Вычисляет разницу между строками (rowDiff) и столбцами (colDiff) между from и to.
     * Возвращает true, если:
     * Пешка делает первый ход на 1 или 2 клетки вперед, и промежуточные клетки пусты (isRowEmpty(from.row, from.row + 2)).
     * Пешка делает ход на 1 клетку вперед, и клетка назначения пуста.
     * Пешка берет фигуру противника по диагонали.
     * В противном случае возвращает false.
     */
    private fun canPawnMove(to: ChessPosition): Boolean {
        highlightedPositions.value?.forEach {
            if (it == to) return true
        }
        return false
    }

}