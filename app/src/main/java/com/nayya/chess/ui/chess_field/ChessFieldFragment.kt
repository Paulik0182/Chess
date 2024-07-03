package com.nayya.chess.ui.chess_field

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nayya.chess.R
import com.nayya.chess.databinding.FragmentChessFieldBinding
import com.nayya.chess.domain.ChessPiece
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.PieceType
import com.nayya.chess.utils.image.GlideImageLoader
import com.nayya.chess.utils.viewBinding

class ChessFieldFragment : Fragment(R.layout.fragment_chess_field) {

    private val binding by viewBinding<FragmentChessFieldBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initShapesBlack()

        initShapesWhite()

    }

    private fun initShapesBlack() {
        val bishopBlack = R.drawable.bishop_black_44
        val kingBlack = R.drawable.king_black_44
        val knightBlack = R.drawable.knight_black_44
        val pawnBlack = R.drawable.pawn_black_44
        val queenBlack = R.drawable.queen_black_44
        val rookBlack = R.drawable.rook_black_44


        bishopBlack.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8c)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8f)
        }
        kingBlack.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8e)
        }
        knightBlack.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8b)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8g)
        }
        pawnBlack.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7a)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7b)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7c)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7d)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7e)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7f)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7g)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare7h)
        }
        queenBlack.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8d)
        }
        rookBlack.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8a)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8h)
        }
    }

    private fun initShapesWhite() {
        val bishopWhite = R.drawable.bishop_white_44
        val kingWhite = R.drawable.king_white_44
        val knightWhite = R.drawable.knight_white_44
        val pawnWhite = R.drawable.pawn_white_44
        val queenWhite = R.drawable.queen_white_44
        val rookWhite = R.drawable.rook_white_44

        bishopWhite.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1c)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1f)
        }
        kingWhite.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1e)
        }
        knightWhite.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1b)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1g)
        }
        pawnWhite.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2a)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2b)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2c)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2d)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2e)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2f)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2g)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare2h)
        }
        queenWhite.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1d)
        }
        rookWhite.let {
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1a)
            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare1h)
        }
    }

    /**
     * Метод обрабатывает перемещение коня на доске.
     * Он определяет возможные ходы коня, выделяет их на доске,
     * а затем обрабатывает нажатие на клетку, куда пользователь хочет
     * переместить коня.
     */
    private fun handleKnightMove(knight: ChessPiece) {
        // Определяем возможные ходы коня
        val possibleMoves = getPossibleKnightMoves(knight.position)

        // Выделяем клетки, куда может сходить конь
        highlightChessSquares(possibleMoves)

        // Обрабатываем нажатие на клетку
        binding.chessFieldInclude.chessSquare1b.setOnClickListener { view ->
            val clickedSquare = getChessSquareFromView(view)
            if (clickedSquare in possibleMoves) {
                // Перемещаем коня на выбранную клетку
                moveKnight(knight, clickedSquare)
                // Снимаем выделение с клеток
                unhighlightChessSquares(possibleMoves)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChessFieldFragment().apply {
                arguments = Bundle().apply {


                }
            }
    }


    /**
     * функция определяет возможные ходы коня, исходя из его текущей позиции на доске. Она
     * возвращает множество объектов ChessPosition, представляющих допустимые клетки для перемещения.
     */
    private fun getPossibleKnightMoves(position: ChessPosition): Set<ChessPosition> {
        val (row, col) = position
        return setOf(
            ChessPosition(row - 2, col - 1),
            ChessPosition(row - 2, col + 1),
            ChessPosition(row - 1, col - 2),
            ChessPosition(row - 1, col + 2),
            ChessPosition(row + 1, col - 2),
            ChessPosition(row + 1, col + 2),
            ChessPosition(row + 2, col - 1),
            ChessPosition(row + 2, col + 1)
        ).filter { it.row in 0..7 && it.col in 0..7 }.toSet()
    }

    /**
     * метод принимает набор клеток, которые нужно выделить, и применяет к ним специальный фон,
     * чтобы визуально отметить доступные для перемещения клетки.
     */
    private fun highlightChessSquares(positions: Set<ChessPosition>) {
        for (position in positions) {
            val view = binding.chessFieldInclude.chessSquare1b.findViewWithTag<View>("${position.row},${position.col}")
            view?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.value_cell))
        }
    }

    /**метод возвращает клетки к их исходному виду, снимая с них выделение.*/
    private fun unhighlightChessSquares(positions: Set<ChessPosition>) {
        for (position in positions) {
            val view = binding.chessFieldInclude.chessSquare1b.findViewWithTag<View>("${position.row},${position.col}")
            view?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.value_cell))
        }
    }

    /**
     * метод обновляет позицию коня в объекте ChessPiece и вызывает setUpChessBoard(), чтобы
     * отобразить новое расположение фигур на доске.
     */
    private fun moveKnight(knight: ChessPiece, newPosition: ChessPosition) {
        knight.position = newPosition
        setUpChessBoard()
    }

    /**
     * метод определяет позицию клетки на доске, исходя из View объекта, представляющего эту клетку.
     * Предполагается, что клетки помечены тегами в формате "row,col", что позволяет извлечь координаты.
     */
    private fun getChessSquareFromView(view: View): ChessPosition {
        val tag = view.tag as String
        val (row, col) = tag.split(",").map { it.toInt() }
        return ChessPosition(row, col)
    }

    private fun setUpChessBoard() {
//
    }
}