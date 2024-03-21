package com.example.gethub.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gethub.data.remote.response.ResponseApi
import com.example.gethub.databinding.FragmentHomeBinding
import com.example.gethub.domain.model.UserModel
import com.example.gethub.presentation.adapter.UserAdapter
import com.example.gethub.presentation.vm.HomeViewModel
import com.example.gethub.presentation.vm.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var userAdapter: UserAdapter
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        binding?.apply {
            userAdapter = UserAdapter(
                onItemClicked = { username ->
                    val toDetailUser = HomeFragmentDirections.actionHomeFragmentToDetailActivity(username)
                    findNavController().navigate(toDetailUser)
                }
            )
            with(rvUser) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = userAdapter
            }
        }
    }

    private fun setUpView() {
        viewModel.getUsers.observe(viewLifecycleOwner, userObserver)
        binding?.searchView?.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        viewModel.fetchSearchUsers(query)
                    } else{
                        viewModel.fetchSearchUsers("")
                    }
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(query: String?) = false
            })
        }
        binding?.contentError?.btnRetry?.setOnClickListener {
            val query = binding?.searchView?.query
            if (!query.isNullOrBlank()) viewModel.fetchSearchUsers("$query")
        }
    }

    private val userObserver = Observer<ResponseApi<List<UserModel>>> { result ->
        when (result) {
            is ResponseApi.Loading -> {
                setState(
                    isError = false,
                    isLoading = true
                )
            }

            is ResponseApi.Success -> {
                val users = result.data
                setState(
                    isError = false,
                    isLoading = false,
                    isEmpty = users.isNullOrEmpty()
                )
                userAdapter.submitList(users)
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
            searchAnimation.isVisible = false
            contentError.root.isVisible = isError
            contentEmpty.root.isVisible = isEmpty
            placeholderFrameUser.root.isVisible = isLoading
            rvUser.isVisible = !isError and !isLoading
        }
    }
}