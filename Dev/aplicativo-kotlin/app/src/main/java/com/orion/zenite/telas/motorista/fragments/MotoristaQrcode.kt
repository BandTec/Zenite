package com.orion.zenite.telas.motorista.fragments


import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import kotlinx.android.synthetic.main.fragment_linhas.*
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.*
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.list_error
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.loading_view
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.swipeRefreshLayout
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MotoristaQrcode : Fragment() {

    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_motorista_qrcode, container, false)


        load()

        swipe = view.findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            load()
        }
        return view;
    }

    fun load() {
        consumir()
        loadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    imageee?.visibility = View.GONE
                }else {
                    imageee?.visibility = View.VISIBLE
                }
            }
        })
    }


    // https://stackoverflow.com/questions/25462523/retrofit-api-to-retrieve-a-png-image#25463200
    fun consumir() {
        loading.value = true;

        // TODO REMOVER TOKEN ESTATICO
        val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        val listaRemoto: Call<ResponseBody> = service.getQrcode(19, token)

        listaRemoto.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("deu ruim = ${t.message}")
                loadError.value = true;
                loading.value = false;
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        val bmp = BitmapFactory . decodeStream (response.body()!!.byteStream());
                        imageee.setImageBitmap(bmp);
                        loadError.value = false;
                        loading.value = false;
                    } else {
                        loadError.value = true;
                        loading.value = false;
                    }
                } else {
                    loadError.value = true;
                    loading.value = false;

                }
            }
        })

    }

}