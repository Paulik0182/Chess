package com.nayya.chess.di

import android.content.Context
import android.widget.ImageView
import com.nayya.chess.ui.chess_field.ChessFieldFragment
import com.nayya.chess.ui.chess_field.ChessFieldViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { ChessFieldFragment() }

    factory { (arrayButtons: ArrayList<ArrayList<ImageView>>) -> arrayButtons }
    factory { (context: Context) -> context }

    viewModel { (context: Context, arrayButtons: ArrayList<ArrayList<ImageView>>) ->
        ChessFieldViewModel(context, arrayButtons)
    }
}