package com.example.selflearning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.selflearning.adapter.RoomAdapter
import com.example.selflearning.databinding.FragmentRoomBinding
import com.example.selflearning.repository.Repository
import com.example.selflearning.repository.utils.Resource
import com.example.selflearning.viewmodel.RoomViewModel
import com.example.selflearning.viewmodel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import java.util.prefs.Preferences

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentRoomBinding

//    private val viewModel: RoomViewModel by lazy {
//        ViewModelProvider(this)[RoomViewModel::class.java]
//    }

    private lateinit var viewModel: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        init()

        binding.roomList.layoutManager = GridLayoutManager(requireContext(),2)
        viewModel.roomdata.observe(viewLifecycleOwner,{event->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                       // hideProgressBar()
                        response.data?.let { loginResponse ->
                            binding.roomList.adapter = RoomAdapter(response.data)
                        }
                    }

                    is Resource.Error -> {
                        //hideProgressBar()
                        response.message?.let { message ->
                            //progress.errorSnack(message, Snackbar.LENGTH_LONG)
                        }
                    }

                    is Resource.Loading -> {
                        //showProgressBar()
                    }
                }
            }
        })

        viewModel.getRoomData()
        // viewModel.getPeopleData()
        return binding.root

        //binding.roomList.setLayoutManager(GridLayoutManager(this, 2))
    }

    private fun init() {
        activity?.let{it->
            val repository = Repository()
            val factory = ViewModelProviderFactory(it.application, repository)
            viewModel = ViewModelProvider(this, factory).get(RoomViewModel::class.java)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}