package com.orion.zenite.telas.autenticacao

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.orion.zenite.R
import com.orion.zenite.utils.AppPreferencias

class LogoutFragment : Fragment() {
    private var btncardSairConta: CardView? = null
    private var btncardSuporte: CardView? = null

    var preferencias: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_logout, container, false)

        btncardSairConta = view.findViewById(R.id.cardSairConta) as CardView
        btncardSairConta!!.setOnClickListener{
            sairConta(it)
        }

//        btncardSuporte = view.findViewById(R.id.cardSuporte) as CardView
//        btncardSuporte!!.setOnClickListener{
//            suporte(it)
//        }

        return view;
    }



    fun sairConta(component: View){
        AppPreferencias.clean()
        val login = Intent(activity, Login::class.java)
        startActivity(login)
    }


    fun suporte(component: View){
        // TODO: logica para mandar email
        Toast.makeText(activity, "Olá 2",
            Toast.LENGTH_LONG).show()
    }
}