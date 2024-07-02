package com.nayya.chess.ui.root

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nayya.chess.databinding.ActivityMainBinding
import com.nayya.chess.ui.chess_field.ChessFieldFragment
import com.nayya.chess.utils.ViewBindingActivity

private const val TAG_ROOT_CONTAINER_FRAGMENT = "TAG_ROOT_CONTAINER_FRAGMENT"

class MainActivity : ViewBindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            swapFragmentBackStack(ChessFieldFragment.newInstance())
        }
    }

    private fun swapFragmentBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, fragment, TAG_ROOT_CONTAINER_FRAGMENT)
            .addToBackStack(null)
            .commit()
    }
}