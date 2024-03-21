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
import com.example.gethub.domain.model.UserModel
import com.example.gethub.presentation.adapter.SectionPagerAdapter
import com.example.gethub.presentation.adapter.UserAdapter
import com.example.gethub.presentation.vm.FollowViewModel
import com.example.gethub.presentation.vm.ViewModelFactory
import com.example.gethub.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding
    private lateinit var userAdapter: UserAdapter
    private val temp: FollowViewModel by viewModels(){
        ViewModelFactory.getInstance()
    }

    private val username by lazy {
        arguments?.getString(SectionPagerAdapter.EXTRA_USERNAME)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            temp.setUsername(username.toString())
        }

        setUpType()
        setUpRecyclerView()
        setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        userAdapter = UserAdapter(
            onItemClicked = { _ -> }
        )
        binding?.rvUser?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    private fun setUpView() {
        binding?.contentError?.btnRetry?.setOnClickListener {
            temp.setUsername(username.toString())
            setUpType()
        }
    }

    private fun setUpType() {
        when (arguments?.getInt(SectionPagerAdapter.EXTRA_POSITION, 1)) {
            1 -> temp.getFollowers.observe(viewLifecycleOwner, userObserver)
            2 -> temp.getFollowing.observe(viewLifecycleOwner, userObserver)
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
            contentError.root.isVisible = isError
            contentEmpty.root.isVisible = isEmpty
            shimmerFrameUser.root.isVisible = isLoading
            rvUser.isVisible = !isError and !isLoading
        }
    }
}