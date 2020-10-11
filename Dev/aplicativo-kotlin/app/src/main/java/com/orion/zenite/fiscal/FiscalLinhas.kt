package com.orion.zenite.fiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orion.zenite.R
import kotlinx.android.synthetic.main.activity_main_fiscal.*

class FiscalLinhas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiscal_linhas)

        //        https://material.io/develop/android/components/app-bars-top
        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

}