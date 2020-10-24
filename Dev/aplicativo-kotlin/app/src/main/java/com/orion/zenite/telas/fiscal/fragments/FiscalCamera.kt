package com.orion.zenite.telas.fiscal.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.orion.zenite.R
import com.orion.zenite.telas.fiscal.QrcodeScanner

class FiscalCamera : Fragment() {

    private var btn: Button? = null
    var id :Int? = null
    var token : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fiscal_camera, container, false)
        id = activity?.intent?.extras?.getInt("id")
        token = activity?.intent?.extras?.getString("token").toString()

        btn = view.findViewById(R.id.btn_escanear) as Button
        btn!!.setOnClickListener{
            goToScanner(it)
        }

        return view;
    }

    fun goToScanner(view: View){
        val intent = Intent(activity, QrcodeScanner::class.java)
        intent.putExtra("token", token)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}