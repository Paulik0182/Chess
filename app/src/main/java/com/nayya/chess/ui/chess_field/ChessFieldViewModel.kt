package com.nayya.chess.ui.chess_field

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceType

class ChessFieldViewModel : ViewModel() {

    private var activeColor = ColorChess.WHITE
    private var selectedPiece: ChessPiece? = null
    private var selectedFrom: ChessPosition? = null

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

    fun onPositionSelected(to: ChessPosition) {
        if (selectedPiece == null || selectedFrom == null) {
            // Выбор фигуры для хода
            selectPiece(to)
        } else {
            // Перемещение фигуры
            try {
                move(selectedFrom!!, to)
                selectedPiece = null
                selectedFrom = null
            } catch (e: IllegalStateException) {
                // Обработка ошибок при ходе
                println(e.message)
            }
        }
    }

    private fun selectPiece(from: ChessPosition) {
        val piece = field.value?.get(from.row)?.get(from.col)
        if (piece != null && piece.colorChess == activeColor) {
            selectedPiece = piece
            selectedFrom = from
        } else {
            // Нет фигуры или фигура неверного цвета
        }
    }

    private fun notifyFieldChanged() {
        (field as MutableLiveData<ArrayList<ArrayList<ChessPiece?>>>).value = _field
    }
}