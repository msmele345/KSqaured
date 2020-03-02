package com.mitchmele.ksquared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mitchmele.ksquared.ui.AlgorithmDetailFragment
import com.mitchmele.ksquared.ui.AlgorithmListFragment
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), AlgorithmListFragment.CallBacks {

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
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onAlgorithmSelected(algoId: UUID) {
        Log.d(TAG, "MainActivity.onAlgoSelected: $algoId")

//        val fragment = AlgorithmDetailFragment.newInstance(algoId)
        //Add UUID and ID to AlgoDomainModel and write tests
        //reload algos with isSolved changes and new UUId
        //re-implement loadAlgo in repository


    }
}
