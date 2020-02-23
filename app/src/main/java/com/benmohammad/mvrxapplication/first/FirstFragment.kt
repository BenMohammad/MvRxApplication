package com.benmohammad.mvrxapplication.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.benmohammad.mvrxapplication.FormViewModel
import com.benmohammad.mvrxapplication.R
import com.benmohammad.mvrxapplication.core.BaseFragment
import com.benmohammad.mvrxapplication.data.networking.RequestsService
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment : BaseFragment() {

    private val viewModel : FormViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        incrementButton.setOnClickListener {
            viewModel.incrementCount()
        }

        RequestsService.getRepos()

        firstButton.setOnClickListener {
            it.findNavController().navigate(R.id.first_to_second)
        }
    }
    override fun invalidate() {
        withState(viewModel) {
            currentState -> textView.text = currentState.toString()
        }
    }
}