package com.mitchmele.ksquared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mitchmele.ksquared.ui.AlgorithmListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        when (currentFragment) {
            null -> {
                val fragment = AlgorithmListFragment.newInstance()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()

            }
        }

    }
}
