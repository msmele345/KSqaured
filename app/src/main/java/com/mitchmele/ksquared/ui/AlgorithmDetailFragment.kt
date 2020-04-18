package com.mitchmele.ksquared.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mitchmele.ksquared.R
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.algo_store.UIViewState
import com.mitchmele.ksquared.model.Algorithm
import com.mitchmele.ksquared.utils.AlgorithmResponseUtils
import com.mitchmele.ksquared.utils.KSquaredConstants.ARG_ALGORITHM_ID
import com.mitchmele.ksquared.utils.KSquaredConstants.DETAIL_TAG
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.fragment_algorithm.*
import kotlinx.android.synthetic.main.progress_spinner.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlgorithmDetailFragment : Fragment() {

    private lateinit var algorithm: Algorithm

    private var algorithmNameId: String? = null

    private val algorithmDetailViewModel: AlgorithmDetailViewModel
            by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        algorithm = Algorithm()

        algorithmNameId = arguments?.getString(ARG_ALGORITHM_ID)
        Log.d(DETAIL_TAG, "Loaded Algo: $algorithmNameId")
    }

    //happens before onStart()
    //View is restored after this is called
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_algorithm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        algorithmNameId?.let {
            algorithmDetailViewModel.getAlgorithmByName2(it).observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer { response ->
                    when (response) {
                        is ResultData.Success -> {
                            this.algorithm = response.value
                            updateUI(algorithm)
                        }
                        is ResultData.Failure -> { setViewState(UIViewState.UIError) }
                        is ResultData.Loading -> { setViewState(UIViewState.Loading) }
                    }
                }
            )
        }
    }

    private fun updateUI(algorithm: Algorithm) {
        name_value.text = algorithm.name
        details_value.text = algorithm.categoryDescription
        difficulty_value.text = algorithm.difficultyLevel.toString()
        algorithm_code_snippet.text = algorithm.codeSnippet
        categories_value.text = AlgorithmResponseUtils.formatCategoryTags(algorithm.categoryTags)
        algorithm_solved.isChecked = algorithm.solved
    }

    private fun setViewState(uiViewState: UIViewState) {
        progress_spinner.visibility = View.GONE
        default_error_view.visibility = View.GONE
        algo_detail_view.visibility = View.GONE
        return when (uiViewState) {
            is UIViewState.Loading -> { progress_spinner.visibility = View.VISIBLE }
            is UIViewState.UISuccess -> {
                progress_spinner.visibility = View.GONE
                algo_detail_view.visibility = View.VISIBLE
            }
            is UIViewState.UIError -> { default_error_view.visibility = View.VISIBLE }
        }
    }

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