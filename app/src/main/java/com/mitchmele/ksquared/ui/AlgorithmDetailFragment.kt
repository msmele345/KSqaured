package com.mitchmele.ksquared.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mitchmele.ksquared.R
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.model.Algorithm
import java.util.*

private const val ARG_ALGORITHM_ID = "algorithm_id"
private const val TAG = "AlgorithmDetailFragment"

class AlgorithmDetailFragment : Fragment() {

    private lateinit var algorithm: Algorithm
    private lateinit var titleField: TextView
    private lateinit var difficultyField: TextView
    private lateinit var codeSnippetField: TextView
    private lateinit var categoriesField: TextView
    private lateinit var solvedCheckBox: CheckBox
    var algorithmId: String? = null

    private val algorithmDetailViewModel: AlgorithmDetailViewModel by lazy {
        ViewModelProviders.of(this).get(AlgorithmDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        algorithm = Algorithm()

        algorithmId = arguments?.getString(ARG_ALGORITHM_ID)
        Log.d(TAG, "Loaded Algo: $algorithmId")

        //BIND view properties
        //setup algorithm_detail_viewModel to call findAlgoByName endpoint
        //add detail_view_model to onViewCreated
    }


    //happens before onStart()
    //View is restored after this is called
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_algorithm, container, false)

        titleField = view.findViewById(R.id.algorithm_title) as TextView
        difficultyField = view.findViewById(R.id.algorithm_difficulty) as TextView
        codeSnippetField = view.findViewById(R.id.algorithm_code_snippet) as TextView
        categoriesField = view.findViewById(R.id.algorithm_categories) as TextView
        solvedCheckBox = view.findViewById(R.id.algorithm_solved) as CheckBox


        return view
    }

    //need to redesign to handle nulls and errors
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        algorithmId?.let {
            algorithmDetailViewModel.getAlgorithmByName(it).observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer { liveData ->
                    when (liveData) {
                        is ResultData.Success -> {
                            algorithm = liveData.value
                        }
                        is ResultData.Failure -> {
                            //setViewState
                        }
                        is ResultData.Loading -> {
                            //setViewState
                        }
                    }
                }
            )
        }
    }

    //pass list of all cached algorithms? OR manage in liveData?
    companion object {
        fun newInstance(algorithmNameId: String): AlgorithmDetailFragment {
            val bundle = Bundle().apply {
                putString(ARG_ALGORITHM_ID, algorithmNameId)
            }

            return AlgorithmDetailFragment().apply {
                arguments = bundle
            }
        }
    }
}