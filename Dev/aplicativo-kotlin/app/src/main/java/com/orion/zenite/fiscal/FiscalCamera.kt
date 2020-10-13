package com.orion.zenite.fiscal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import kotlinx.android.synthetic.main.fragment_fiscal_camera.*

class FiscalCamera : Fragment() {

    private var btn: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fiscal_camera, container, false)

        btn = view.findViewById(R.id.btn_escanear) as Button
        btn!!.setOnClickListener{
            goToScanner(it)
        }

        return view;
    }

    fun goToScanner(view: View){
        val intent = Intent(activity, QrcodeScanner::class.java)
        startActivity(intent)
    }

}