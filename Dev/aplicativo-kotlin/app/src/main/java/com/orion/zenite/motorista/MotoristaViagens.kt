package com.orion.zenite.motorista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.orion.zenite.R
import com.orion.zenite.motorista.viagens.ViagensDiarias
import com.orion.zenite.motorista.viagens.ViagensSemanais
import com.orion.zenite.utils.TabAdapter


class MotoristaViagens : Fragment() {
    // https://inducesmile.com/kotlin-source-code/how-to-add-tablayout-inside-fragment-in-kotlin/
    // https://stackoverflow.com/questions/51014462/material-component-tablayout-inside-fragment-in-kotlin
    // https://medium.com/@droidbyme/android-material-design-tabs-tab-layout-with-swipe-884085ae80ff
    // https://developer.android.com/guide/navigation/navigation-swipe-view

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_motorista_viagens, container, false)

        tabLayout = view.findViewById(R.id.tabViagem) as TabLayout
        viewPager = view.findViewById(R.id.viewpager_main) as ViewPager

        val adapter = TabAdapter(getChildFragmentManager())
        adapter.addFragment(ViagensDiarias(), "Diário")
        adapter.addFragment(ViagensSemanais(), "Semanal")
        viewPager!!.adapter = adapter

        tabLayout!!.post(Runnable { tabLayout!!.setupWithViewPager(viewPager) })

        return view
    }
}