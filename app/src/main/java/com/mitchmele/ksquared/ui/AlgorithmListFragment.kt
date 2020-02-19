package com.mitchmele.ksquared.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitchmele.ksquared.R
import com.mitchmele.ksquared.algo_store.UIViewState
import com.mitchmele.ksquared.model.Algorithm

private const val TAG = "AlgorithmListFragment"

class AlgorithmListFragment : Fragment() {

    private lateinit var algoRecyclerView: RecyclerView

    val algorithmViewModel: AlgorithmViewModel
            by lazy { ViewModelProviders.of(this).get(AlgorithmViewModel::class.java) }

    private var algorithmAdapter: AlgorithmAdapter? = AlgorithmAdapter(emptyList())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_algo_list, container, false)

        algoRecyclerView = view.findViewById(R.id.algo_recycler_view) as RecyclerView
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        algorithmViewModel.algorithmLiveData.observe(
//            this, Observer { algorithms ->
//                algorithms?.let {
//                    Log.d(TAG, "Got Algorithms: ${algorithms.size}")
//                    updateUI(it)
//                }
//            }
//        )

        val algos = algorithmViewModel.algorithms
        updateUI(algos)
    }


    private fun updateUI(algorithms: List<Algorithm>) {
        //create adapter
        //set adapter
        //set layoutManager
        algorithmAdapter = AlgorithmAdapter(algorithms)
        algoRecyclerView.layoutManager = object: LinearLayoutManager(context) {}
        algoRecyclerView.adapter = algorithmAdapter

    }

    private inner class AlgorithmHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var algorithm: Algorithm

        private val algorithmName: TextView = itemView.findViewById(R.id.algorithm_name)
        private val algorithmCodeSnippet: TextView = itemView.findViewById(R.id.algorithm_code_snippet)
        private val algorithmDescription: TextView = itemView.findViewById(R.id.algorithm_description)
        private val difficultyLevel: TextView = itemView.findViewById(R.id.algorithm_difficulty)


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(algorithm: Algorithm) {
            this.algorithm = algorithm
            algorithmName.text = algorithm.name
            algorithmCodeSnippet.text = algorithm.codeSnippet
            algorithmDescription.text = algorithm.categoryDescription
            difficultyLevel.text = algorithm.difficultyLevel.toString()
        }


        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


    private inner class AlgorithmAdapter(var algorithms: List<Algorithm>): RecyclerView.Adapter<AlgorithmHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlgorithmHolder {
            val view = layoutInflater.inflate(R.layout.list_item_algorithm, parent, false)
            return AlgorithmHolder(view)
        }

        override fun getItemCount(): Int {
            return algorithms.size
        }

        override fun onBindViewHolder(holder: AlgorithmHolder, position: Int) {
            val algorithm = algorithms[position]
            holder.bind(algorithm)
        }

    }


    fun setViewState(UIViewState: UIViewState) {
        return when (UIViewState) {
            is UIViewState.Loading -> {
            } //progress spinner
            is UIViewState.Empty -> {
            } //empty view is visible
            is UIViewState.UISuccess<*> -> {
            }
            is UIViewState.UIError -> {
            } //show
        }
    }


    companion object {
        fun newInstance(): AlgorithmListFragment {
            return AlgorithmListFragment()
        }
    }
}