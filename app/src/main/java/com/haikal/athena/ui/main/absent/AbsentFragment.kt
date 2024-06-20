package com.haikal.athena.ui.main.absent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.R
import com.haikal.athena.adapter.SaveAdapter
import com.haikal.athena.databinding.FragmentAbsentBinding
import com.haikal.athena.ui.ViewModelFactory
import com.haikal.athena.ui.features.cam.ResultViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveModel: AbsentViewModel by viewModels { ViewModelFactory.getInstance(requireContext()) }

        val adapter = SaveAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvHistory.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)

        adapter.onClick = {
            saveModel.delete(it)
        }

        saveModel.getAllSave().observe(viewLifecycleOwner) { list ->
            if (list != null) {
                adapter.submitList(list)
                binding.rvHistory.adapter = adapter
            }

            if (list.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.save_no_data),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}