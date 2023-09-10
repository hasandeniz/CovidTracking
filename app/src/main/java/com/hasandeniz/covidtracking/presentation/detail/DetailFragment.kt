package com.hasandeniz.covidtracking.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hasandeniz.covidtracking.R
import com.hasandeniz.covidtracking.data.remote.dto.history.toHistory
import com.hasandeniz.covidtracking.data.remote.dto.statistics.toCountryDetails
import com.hasandeniz.covidtracking.databinding.BottomSheetHistoryBinding
import com.hasandeniz.covidtracking.databinding.FragmentDetailBinding
import com.hasandeniz.covidtracking.domain.model.toCountryDetails
import com.hasandeniz.covidtracking.presentation.detail.adapter.HistoryListAdapter
import com.hasandeniz.covidtracking.util.Resource
import com.hasandeniz.covidtracking.util.Utils
import com.hasandeniz.covidtracking.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var bottomSheetBinding: BottomSheetHistoryBinding
    private lateinit var historyListAdapter: HistoryListAdapter
    private lateinit var shareMessage: String
    private lateinit var historyBottomSheet: BottomSheetDialog
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isLoading = true
        setupToolbarMenu()
        viewModel.getCountryDetails(args.name)
        viewModel.getHistory(args.name)
        getCountryDetails()
        getHistory()
        setupBottomSheet()

        binding.button.setOnClickListener {
            historyBottomSheet.show()
        }

        binding.btnRetry.setOnClickListener {
            binding.tvError.visibility = View.GONE
            binding.btnRetry.visibility = View.GONE
            binding.isLoading = true
            viewModel.getCountryDetails(args.name)
            viewModel.getHistory(args.name)
        }
    }

    private fun setupBottomSheet() {
        historyListAdapter = HistoryListAdapter()
        historyBottomSheet = BottomSheetDialog(requireContext())
        bottomSheetBinding = BottomSheetHistoryBinding.inflate(layoutInflater)

        bottomSheetBinding.adapter = historyListAdapter
        historyBottomSheet.setContentView(bottomSheetBinding.root)

        historyListAdapter.isItemClicked = { history ->
            binding.details = history.toCountryDetails()
            shareMessage = Utils().generateShareMessageFromHistory(history, requireContext())
            historyBottomSheet.dismiss()
        }

        bottomSheetBinding.ibCloseBottomSheet.setOnClickListener {
            historyBottomSheet.dismiss()
        }
    }

    private fun setupToolbarMenu() {
        val menuHost = requireActivity() as MenuHost
        val menuProvider: MenuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.share_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.share -> {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        val message = shareMessage
                        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(shareIntent, "Share via"))
                    }
                }
                return true
            }
        }
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun getCountryDetails() {
        viewModel.countryDetailsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.tvError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                    response.data?.let {
                        val countryDetailsList = response.data.toCountryDetails()
                        val countryDetails = countryDetailsList[0]
                        binding.details = countryDetails
                        shareMessage = Utils().generateShareMessageFromCountryDetails(
                            countryDetails,
                            requireContext()
                        )
                    }
                    binding.isSuccess = true
                    binding.isLoading = false
                }

                is Resource.Error -> {
                    requireContext().showToast("${response.message}")
                    binding.isSuccess = false
                    binding.tvError.visibility = View.VISIBLE
                    binding.btnRetry.visibility = View.VISIBLE
                    shareMessage = ""
                    binding.isLoading = false
                }

                is Resource.Loading -> {
                    binding.isLoading = true
                    binding.isSuccess = false
                    binding.tvError.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                }
            }
        }
    }

    private fun getHistory() {
        viewModel.historyResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        val historyList = response.data.toHistory().map { history ->
                            history.copy(time = Utils().formatDate(history.time))
                        }
                        historyListAdapter.submitList(historyList)

                    }
                }

                else -> {}
            }

        }
    }


}