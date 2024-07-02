package com.nayya.chess.ui.chess_field

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nayya.chess.R
import com.nayya.chess.databinding.FragmentChessFieldBinding
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

    companion object {
        @JvmStatic
        fun newInstance() =
            ChessFieldFragment().apply {
                arguments = Bundle().apply {


                }
            }
    }
}