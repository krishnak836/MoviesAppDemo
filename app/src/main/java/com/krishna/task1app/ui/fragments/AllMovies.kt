package com.krishna.task1app.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.krishna.task1app.adapters.MoviesAdapter
import com.krishna.task1app.databinding.FragmentAllMoviesBinding
import com.krishna.task1app.models.MoviesModelItem
import com.krishna.task1app.util.MovieApiState
import com.krishna.task1app.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMovies : Fragment() {
    private var _binding: FragmentAllMoviesBinding? = null
    private val binding get() = _binding!!
    private var categoryId: String? = null
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var moviesAdapter: MoviesAdapter
    private var moviesArrayList: ArrayList<MoviesModelItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryId = arguments?.getString("categoryId")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRv()
        return root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenStarted {
            getMoviesData()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getMoviesData() {
        mainViewModel.movieFlowObserver.observe(requireActivity()) {
            when (it) {
                is MovieApiState.Loading -> {
                    _binding?.progressBar?.isVisible = true
                }
                is MovieApiState.Failure -> {
                    _binding?.progressBar?.isVisible = false
                }
                is MovieApiState.Success -> {
                    _binding?.progressBar?.isVisible = false
                    for (i in 0 until it.data.size) {
                        if (categoryId == it.data[i].category_id) {
                            moviesArrayList.add(it.data[i])
                        }
                    }
                    moviesAdapter.notifyDataSetChanged()
                }
                is MovieApiState.Empty -> {

                }
                else -> {}
            }
        }

    }

    private fun initRv() {
        moviesAdapter =
            MoviesAdapter(moviesArrayList, requireActivity())
        _binding?.allMovieRv?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 3)
            adapter = moviesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}