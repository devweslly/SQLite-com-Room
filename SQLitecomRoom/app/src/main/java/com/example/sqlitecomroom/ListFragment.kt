package com.example.sqlitecomroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitecomroom.adapter.UserAdapter
import com.example.sqlitecomroom.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val adapter = UserAdapter()
        binding.recyclerUser.layoutManager = LinearLayoutManager(context)
        binding.recyclerUser.adapter = adapter
        binding.recyclerUser.setHasFixedSize(true)

        mainViewModel.selectUsers.observe(viewLifecycleOwner){
            response -> adapter.setList(response)
        }

        binding.floatingactionbutton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }
}