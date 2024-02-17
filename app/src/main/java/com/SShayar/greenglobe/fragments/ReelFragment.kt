package com.SShayar.greenglobe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.SShayar.greenglobe.Models.Reel
import com.SShayar.greenglobe.R
import com.SShayar.greenglobe.adapter.ReelAdapter
import com.SShayar.greenglobe.databinding.FragmentReelBinding
import com.SShayar.greenglobe.utils.REEL
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ReelFragment : Fragment() {
    private lateinit var  binding: FragmentReelBinding
    lateinit var  adapter: ReelAdapter
    var reelList= ArrayList<Reel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentReelBinding.inflate(inflater, container, false)
        adapter= ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            reelList.clear()
            var temList=ArrayList<Reel>()
            for (i in it.documents){
                var reel = i.toObject<Reel>()!!
                temList.add(reel)
            }
            reelList.addAll(temList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}