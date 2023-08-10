package com.example.buttomnav

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
private const val ARG1 = "Name"
private const val ARG2= "Age"
class PlayFragment : Fragment() {
    private var ARG1: String? = "Name"
    private var ARG2: String? = "Age"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as MainActivity).callFromFragment()

        if(arguments != null){
            val name : String? = arguments?.getString(ARG1)
            val age : Int? = arguments?.getInt(ARG2)
            Log.d("PlayFragment","Name:$name Age:$age")
        }


        return inflater.inflate(R.layout.fragment_play, container, false)
    }
    companion object{

                 fun getInstance(param1: String, param2: Int) : PlayFragment{
                    var playFragment = PlayFragment()
                    val  bundle = Bundle()
                    bundle.putString(ARG1,param1)
                    bundle.putInt(ARG2,param2)
                    playFragment.arguments = bundle
                    return playFragment
                }
    }

}