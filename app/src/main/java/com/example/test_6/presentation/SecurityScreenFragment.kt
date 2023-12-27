package com.example.test_6.presentation

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_6.BaseFragment
import com.example.test_6.common.Resource
import com.example.test_6.databinding.FragmentSecurityScreenBinding
import kotlinx.coroutines.launch


class SecurityScreenFragment :
    BaseFragment<FragmentSecurityScreenBinding>(FragmentSecurityScreenBinding::inflate) {
    private lateinit var numberAdapter: NumbersRecyclerAdapter
    private val viewModel: SecurityViewModel by viewModels()

    override fun setUp() {
        setAdapter()
    }

    override fun setListeners() {
        passwordInput()
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.passwordCheck.collect {
                    when (it) {
                        is Resource.Success -> showToast(it.successMessage)
                        is Resource.Fail -> showToast(it.errorMessage)
                        is Resource.Loading -> {
                            if (it.isLoading) {
                                binding.progressBar.visibility = View.VISIBLE
                            } else {
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    private var userInput: String = ""

    private fun setAdapter() {
        with(binding) {
            recycler.layoutManager = GridLayoutManager(requireContext(), 3)
            numberAdapter = NumbersRecyclerAdapter(viewModel.numberButtons)
            recycler.adapter = numberAdapter
        }
        numberAdapter.onItemClick = {
            userInput += it.numbers
        }
    }

    private fun passwordInput() {
        if (userInput.length == 4) {
            viewModel.checkPassword(userInput)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}
