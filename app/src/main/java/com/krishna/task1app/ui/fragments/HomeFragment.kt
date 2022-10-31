package com.krishna.task1app.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.krishna.task1app.R
import com.krishna.task1app.adapters.CategoriesAdapter
import com.krishna.task1app.databinding.FragmentHomeBinding
import com.krishna.task1app.models.CategoriesModelItem
import com.krishna.task1app.models.MoviesModelItem
import com.krishna.task1app.util.CategoryApiState
import com.krishna.task1app.util.MovieApiState
import com.krishna.task1app.util.OnClickItem
import com.krishna.task1app.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : OnClickItem, Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter
    private var categoriesArrayList: ArrayList<CategoriesModelItem> = ArrayList()
    private var moviesArrayList: ArrayList<MoviesModelItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRv()
        mainViewModel.getCategory()
        mainViewModel.getMovies()
        lifecycleScope.launchWhenStarted {
            getMoviesData()
            getCategoriesData()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCategoriesData() {
        mainViewModel.categoryFlowObserver.observe(requireActivity()) {
            when (it) {
                is CategoryApiState.Loading -> {
                    _binding?.categoryRv?.isVisible = false
                    _binding?.progressBar?.isVisible = true
                }
                is CategoryApiState.Failure -> {
                    _binding?.categoryRv?.isVisible = false
                    _binding?.progressBar?.isVisible = false
                }
                is CategoryApiState.Success -> {
                    _binding?.categoryRv?.isVisible = true
                    _binding?.progressBar?.isVisible = false
                    categoriesArrayList.addAll(it.data)
                    categoriesAdapter.notifyDataSetChanged()
                }
                is CategoryApiState.Empty -> {

                }
                else -> {}
            }
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
                    moviesArrayList.addAll(it.data)
                    categoriesAdapter.notifyDataSetChanged()
                }
                is MovieApiState.Empty -> {

                }
                else -> {}
            }
        }

    }

    private fun initRv() {
        categoriesAdapter =
            CategoriesAdapter(categoriesArrayList, moviesArrayList, this, requireActivity())
        _binding?.categoryRv?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = categoriesAdapter
        }
    }

    override fun onClickOfMore(categoryId: String) {
        view?.let {
            Navigation.findNavController(it).navigate(
                R.id.action_homeFragment_to_allMovies, bundleOf(
                    "categoryId" to categoryId
                )
            )
        }
    }
}