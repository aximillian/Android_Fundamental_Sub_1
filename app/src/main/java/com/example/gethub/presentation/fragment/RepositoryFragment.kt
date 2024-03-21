package com.example.gethub.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gethub.data.remote.response.ResponseApi
import com.example.gethub.domain.model.RepositoryModel
import com.example.gethub.presentation.adapter.RepositoryAdapter
import com.example.gethub.presentation.adapter.SectionPagerAdapter
import com.example.gethub.presentation.vm.RepositoryViewModel
import com.example.gethub.presentation.vm.ViewModelFactory
import com.example.gethub.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding
    private lateinit var repositoryAdapter: RepositoryAdapter

    private val viewModel: RepositoryViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    private val username by lazy {
        arguments?.getString(SectionPagerAdapter.EXTRA_USERNAME)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState === null) {
            viewModel.setUsername(username.toString())
        }

        setUpRecyclerView()
        setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        repositoryAdapter = RepositoryAdapter(
            onItemClicked = {}
        )
        binding?.rvRepo?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = repositoryAdapter
        }
    }

    private fun setUpView() {
        viewModel.getRepository.observe(viewLifecycleOwner, repositoryObserver)
        binding?.contentError?.btnRetry?.setOnClickListener {
            viewModel.setUsername(username.toString())
        }
    }

    private val repositoryObserver = Observer<ResponseApi<List<RepositoryModel>>> { result ->
        when (result) {
            is ResponseApi.Loading -> {
                setState(
                    isError = false,
                    isLoading = true
                )
            }
            is ResponseApi.Success -> {
                val repos = result.data
                setState(
                    isError = false,
                    isLoading = false,
                    isEmpty = repos.isNullOrEmpty()
                )
                repositoryAdapter.submitList(repos)
            }
            is ResponseApi.Error -> {
                setState(
                    isError = true,
                    isLoading = false
                )
            }
        }
    }

    private fun setState(isError: Boolean, isLoading: Boolean, isEmpty: Boolean = false) {
        binding?.apply {
            contentError.root.isVisible = isError
            contentEmpty.root.isVisible = isEmpty
            placeholderFrameRepo.root.isVisible = isLoading
            rvRepo.isVisible = !isError and !isLoading
        }
    }


}