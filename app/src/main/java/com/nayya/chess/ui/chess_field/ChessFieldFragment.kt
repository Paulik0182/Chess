package com.nayya.chess.ui.chess_field

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nayya.chess.R
import com.nayya.chess.databinding.FragmentChessFieldBinding
import com.nayya.chess.domain.ChessPosition
import com.nayya.chess.domain.ColorChess
import com.nayya.chess.domain.PieceType
import com.nayya.chess.utils.image.GlideImageLoader
import com.nayya.chess.utils.viewBinding

class ChessFieldFragment : Fragment(R.layout.fragment_chess_field) {

    private val bishopBlack = R.drawable.bishop_black_44
    private val kingBlack = R.drawable.king_black_44
    private val knightBlack = R.drawable.knight_black_44
    private val pawnBlack = R.drawable.pawn_black_44
    private val queenBlack = R.drawable.queen_black_44
    private val rookBlack = R.drawable.rook_black_44

    private val bishopWhite = R.drawable.bishop_white_44
    private val kingWhite = R.drawable.king_white_44
    private val knightWhite = R.drawable.knight_white_44
    private val pawnWhite = R.drawable.pawn_white_44
    private val queenWhite = R.drawable.queen_white_44
    private val rookWhite = R.drawable.rook_white_44

    private val binding by viewBinding<FragmentChessFieldBinding>()
    private val viewModel: ChessFieldViewModel by lazy {
        ViewModelProvider(
            this,
        )[ChessFieldViewModel::class.java]
    }

    private val arrayButtons: ArrayList<ArrayList<ImageView>> by lazy {
        arrayListOf(
            arrayListOf(
                binding.chessFieldInclude.chessSquare8a,
                binding.chessFieldInclude.chessSquare8b,
                binding.chessFieldInclude.chessSquare8c,
                binding.chessFieldInclude.chessSquare8d,
                binding.chessFieldInclude.chessSquare8e,
                binding.chessFieldInclude.chessSquare8f,
                binding.chessFieldInclude.chessSquare8g,
                binding.chessFieldInclude.chessSquare8h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare7a,
                binding.chessFieldInclude.chessSquare7b,
                binding.chessFieldInclude.chessSquare7c,
                binding.chessFieldInclude.chessSquare7d,
                binding.chessFieldInclude.chessSquare7e,
                binding.chessFieldInclude.chessSquare7f,
                binding.chessFieldInclude.chessSquare7g,
                binding.chessFieldInclude.chessSquare7h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare6a,
                binding.chessFieldInclude.chessSquare6b,
                binding.chessFieldInclude.chessSquare6c,
                binding.chessFieldInclude.chessSquare6d,
                binding.chessFieldInclude.chessSquare6e,
                binding.chessFieldInclude.chessSquare6f,
                binding.chessFieldInclude.chessSquare6g,
                binding.chessFieldInclude.chessSquare6h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare5a,
                binding.chessFieldInclude.chessSquare5b,
                binding.chessFieldInclude.chessSquare5c,
                binding.chessFieldInclude.chessSquare5d,
                binding.chessFieldInclude.chessSquare5e,
                binding.chessFieldInclude.chessSquare5f,
                binding.chessFieldInclude.chessSquare5g,
                binding.chessFieldInclude.chessSquare5h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare4a,
                binding.chessFieldInclude.chessSquare4b,
                binding.chessFieldInclude.chessSquare4c,
                binding.chessFieldInclude.chessSquare4d,
                binding.chessFieldInclude.chessSquare4e,
                binding.chessFieldInclude.chessSquare4f,
                binding.chessFieldInclude.chessSquare4g,
                binding.chessFieldInclude.chessSquare4h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare3a,
                binding.chessFieldInclude.chessSquare3b,
                binding.chessFieldInclude.chessSquare3c,
                binding.chessFieldInclude.chessSquare3d,
                binding.chessFieldInclude.chessSquare3e,
                binding.chessFieldInclude.chessSquare3f,
                binding.chessFieldInclude.chessSquare3g,
                binding.chessFieldInclude.chessSquare3h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare2a,
                binding.chessFieldInclude.chessSquare2b,
                binding.chessFieldInclude.chessSquare2c,
                binding.chessFieldInclude.chessSquare2d,
                binding.chessFieldInclude.chessSquare2e,
                binding.chessFieldInclude.chessSquare2f,
                binding.chessFieldInclude.chessSquare2g,
                binding.chessFieldInclude.chessSquare2h,
            ),
            arrayListOf(
                binding.chessFieldInclude.chessSquare1a,
                binding.chessFieldInclude.chessSquare1b,
                binding.chessFieldInclude.chessSquare1c,
                binding.chessFieldInclude.chessSquare1d,
                binding.chessFieldInclude.chessSquare1e,
                binding.chessFieldInclude.chessSquare1f,
                binding.chessFieldInclude.chessSquare1g,
                binding.chessFieldInclude.chessSquare1h,
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.field.observe(viewLifecycleOwner) { field ->
            field.forEachIndexed { row, chessPieces ->

                chessPieces.forEachIndexed { col, chessPiece ->
                    if (chessPieces == null) {
                        arrayButtons[row][col].setImageDrawable(null)
                    } else {
                        GlideImageLoader().loadInto(
                            when (chessPiece?.type) {
                                PieceType.BISHOP ->
                                    if (chessPiece.colorChess == ColorChess.WHITE) bishopWhite else bishopBlack

                                PieceType.KING ->
                                    if (chessPiece.colorChess == ColorChess.WHITE) kingWhite else kingBlack

                                PieceType.PAWN ->
                                    if (chessPiece.colorChess == ColorChess.WHITE) pawnWhite else pawnBlack

                                PieceType.QUEEN ->
                                    if (chessPiece.colorChess == ColorChess.WHITE) queenWhite else queenBlack

                                PieceType.ROOK ->
                                    if (chessPiece.colorChess == ColorChess.WHITE) rookWhite else rookBlack

                                PieceType.KNIGHT ->
                                    if (chessPiece.colorChess == ColorChess.WHITE) knightWhite else knightBlack

                                null -> 1
                            },
                            arrayButtons[row][col]
                        )
                    }
                }
            }
        }


        arrayButtons.forEachIndexed { row, row_buttons ->
            row_buttons.forEachIndexed { col, button ->
                button.setOnClickListener {
                    viewModel.onPositionSelected(ChessPosition(row, col))
                }
            }
        }
    }

// Пример:
//        bishopBlack.let {
//            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8c)
//            GlideImageLoader().loadInto(it, binding.chessFieldInclude.chessSquare8f)
//        }


    companion object {
        @JvmStatic
        fun newInstance() =
            ChessFieldFragment().apply {
                arguments = Bundle().apply {


                }
            }
    }
}