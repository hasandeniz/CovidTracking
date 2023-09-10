package com.hasandeniz.covidtracking.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hasandeniz.covidtracking.data.remote.dto.statistics.toCountry
import com.hasandeniz.covidtracking.databinding.FragmentHomeBinding
import com.hasandeniz.covidtracking.presentation.home.adapter.CountryListAdapter
import com.hasandeniz.covidtracking.util.Resource
import com.hasandeniz.covidtracking.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CountryListAdapter
    private val viewModel: HomeViewModel by viewModels()
    private var searchQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CountryListAdapter()
        getCountries()

        binding.adapter = adapter
        setupSearchView()

        adapter.isItemClicked = { country ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(country.name)
            findNavController().navigate(action)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchCountries(searchQuery)

        }

        binding.btnRetry.setOnClickListener {
            viewModel.fetchCountries(searchQuery)
        }
    }

    private fun getCountries() {
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.tvError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                    binding.swipeRefresh.visibility = View.VISIBLE
                    response.data?.let {
                        val countries = it.toCountry().sortedBy { country -> country.name }
                        adapter.submitList(countries)
                    }
                    binding.swipeRefresh.isRefreshing = false
                }

                is Resource.Error -> {
                    requireContext().showToast("${response.message}")
                    binding.swipeRefresh.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                    binding.btnRetry.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = false
                }

                is Resource.Loading -> {
                    binding.tvError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                    binding.swipeRefresh.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchQuery = query
                    viewModel.fetchCountries(query.trim())
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "") {
                    searchQuery = ""
                    viewModel.fetchCountries(newText)
                }
                return false
            }

        })

    }

}