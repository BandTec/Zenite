package com.orion.zenite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView

class LogoutFragment : Fragment() {
    private var btncardSairConta: CardView? = null
    private var btncardSuporte: CardView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        btncardSairConta = view.findViewById(R.id.cardSairConta) as CardView
        btncardSairConta!!.setOnClickListener{
            suporte(it)
        }

        btncardSuporte = view.findViewById(R.id.cardSuporte) as CardView
        btncardSuporte!!.setOnClickListener{
            sairConta(it)
        }

        return view;
    }



    fun sairConta(component: View){
        Toast.makeText(activity, "Olá 1",
            Toast.LENGTH_LONG).show()
    }


    fun suporte(component: View){
        Toast.makeText(activity, "Olá 2",
            Toast.LENGTH_LONG).show()
    }
}