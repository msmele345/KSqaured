package com.mitchmele.ksquared.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitchmele.ksquared.R
import com.mitchmele.ksquared.algo_store.ResultData
import com.mitchmele.ksquared.algo_store.UIViewState
import com.mitchmele.ksquared.model.Algorithm
import kotlinx.android.synthetic.main.error_view.*
import kotlinx.android.synthetic.main.progress_spinner.*
import java.util.*

private const val TAG = "AlgorithmListFragment"

class AlgorithmListFragment : Fragment() {

    interface CallBacks {
        fun onAlgorithmSelected(algoId: UUID)
    }

    private lateinit var algoRecyclerView: RecyclerView
    private lateinit var retryButton: Button

    private val algorithmViewModel: AlgorithmViewModel
            by lazy { ViewModelProviders.of(this).get(AlgorithmViewModel::class.java) }

    private var algorithmAdapter: AlgorithmAdapter? = AlgorithmAdapter(emptyList())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_algo_list, container, false)
        retryButton = view.findViewById(R.id.error_retry_button)

        algoRecyclerView = view.findViewById(R.id.algo_recycler_view)

        algoRecyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

        )
        algoRecyclerView = view.findViewById(R.id.algo_recycler_view) as RecyclerView
        algoRecyclerView.layoutManager = LinearLayoutManager(context)
        algorithmAdapter = algorithmAdapter



        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewState(UIViewState.Loading)

        algorithmViewModel.algorithmLiveDataRetro.observe(
            this, Observer { algorithms ->
                algorithms?.let { data ->
                    when(data) {
                        is ResultData.Success -> {
                            Log.d(TAG, "Got ${data.value.size} Algorithms")
                            updateUI(data.value)
                        }
                        is ResultData.Failure -> {
                               setViewState(UIViewState.UIError)
                                   .also { Log.d(TAG, "ERROR: ${data.errorMessage}")}
                        }
                    }
                }
            }
        )
    }

    private fun updateUI(algorithms: List<Algorithm>) {
        setViewState(UIViewState.UISuccess)
        //create adapter
        //set adapter
        //set layoutManager
        algorithmAdapter = AlgorithmAdapter(algorithms)
        algoRecyclerView.adapter = algorithmAdapter
    }

    private inner class AlgorithmHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var algorithm: Algorithm

        private val algorithmName: TextView = itemView.findViewById(R.id.algorithm_name)
        private val algorithmCodeSnippet: TextView =
            itemView.findViewById(R.id.algorithm_code_snippet)
        private val algorithmDescription: TextView =
            itemView.findViewById(R.id.algorithm_description)
        private val isSolvedImage: ImageView = itemView.findViewById(R.id.algorithm_difficulty)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(algorithm: Algorithm) {
            this.algorithm = algorithm
            algorithmName.text = algorithm.name
            algorithmCodeSnippet.text = algorithm.codeSnippet
            algorithmDescription.text = algorithm.categoryDescription
            isSolvedImage.visibility = if (algorithm.solved) View.VISIBLE else View.GONE
        }

        override fun onClick(v: View?) {

        }
    }

    private inner class AlgorithmAdapter(var algorithms: List<Algorithm>) :
        RecyclerView.Adapter<AlgorithmHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlgorithmHolder {
            val view = layoutInflater.inflate(R.layout.list_item_algorithm, parent, false)
            return AlgorithmHolder(view)
        }

        override fun getItemCount(): Int = algorithms.size

        override fun onBindViewHolder(holder: AlgorithmHolder, position: Int) {
            val algorithm = algorithms[position]
            holder.bind(algorithm)
        }

    }

    private fun setViewState(uiViewState: UIViewState) {
        progress_spinner.visibility = View.GONE
        default_error_view.visibility = View.GONE
        //start with recycler view as gone
        return when (uiViewState) {
            is UIViewState.Loading -> {
                progress_spinner.visibility = View.VISIBLE
            }
            is UIViewState.Empty -> {
            } //empty view is visible
            is UIViewState.UISuccess -> {
                progress_spinner.visibility = View.GONE
                //SETUP to show recycler view here VISIBLE
            }
            is UIViewState.UIError -> {
//                algoRecyclerView.visibility = View.GONE
                default_error_view.visibility = View.VISIBLE
            } //show errorView
        }
    }

    companion object {
        fun newInstance(): AlgorithmListFragment {
            return AlgorithmListFragment()
        }
    }
}


//OLD WAY WITHOUT RESULTDATA
//        algorithmViewModel.algorithmLiveData.observe(
//            this, Observer { algorithms ->
//                algorithms?.let {algorithms ->
//                    Log.d(TAG, "Got Algorithms: ${algorithms.size}")
//                    updateUI(it)
//                    }
//                }
//            }
//        )
//
//        updateUI(algorithmViewModel.algorithms)