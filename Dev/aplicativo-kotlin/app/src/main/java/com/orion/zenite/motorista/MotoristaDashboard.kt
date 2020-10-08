package com.orion.zenite.motorista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orion.zenite.R
import kotlinx.android.synthetic.main.fragment_motorista_dashboard.*


class MotoristaDashboard : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motorista_dashboard, container, false)
    }

}