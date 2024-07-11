package com.nayya.chess.di

import android.widget.ImageView
import com.nayya.chess.ui.chess_field.ChessFieldFragment
import com.nayya.chess.ui.chess_field.ChessFieldViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { ChessFieldFragment() }

    factory { (binding: ChessFieldFragment) -> binding }
    factory { (arrayButtons: ArrayList<ArrayList<ImageView>>) -> arrayButtons }

    viewModel { (binding: ChessFieldFragment, arrayButtons: ArrayList<ArrayList<ImageView>>) ->
        ChessFieldViewModel(binding, arrayButtons)
    }
}