package com.orion.zenite.telas.fiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orion.zenite.telas.autenticacao.LogoutFragment
import com.orion.zenite.R
import com.orion.zenite.telas.fiscal.fragments.FiscalCamera
import com.orion.zenite.telas.fiscal.fragments.FiscalDashboard
import kotlinx.android.synthetic.main.activity_main_fiscal.*

// tutorial barra de navegação
// https://www.youtube.com/watch?v=v8MbOjBCu0o
// https://thesimplycoder.com/289/bottom-navigation-bar-android-using-kotlin/
//https://medium.com/over-engineering/setting-up-a-material-components-theme-for-android-fbf7774da739

class MainFiscal : AppCompatActivity() {
    private val dash = FiscalDashboard()
    private val camera = FiscalCamera()
    private val logout = LogoutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fiscal)
        replaceFragment(dash, "Dashboard")

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(dash, "Dashboard")
                R.id.camera -> replaceFragment(camera, "Scanner")
                R.id.logout -> replaceFragment(logout, "Zênite")
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, nomeTela: String){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
            val header = topAppBar;
            header.title = nomeTela
        }
    }

}