package com.orion.zenite.telas.motorista.fragments


import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.motorista.MotoristaApi
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.*
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.list_error
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.loading_view
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.swipeRefreshLayout
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MotoristaQrcode : Fragment() {

    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null

    var id :Int? = null
    var token : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_motorista_qrcode, container, false)


        id = AppPreferencias.id
        token = AppPreferencias.token

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

        val service: MotoristaApi = HttpHelper().getApiClient()!!.create(MotoristaApi::class.java)
        if(id != null) {
            val listaRemoto: Call<ResponseBody> = service.getQrcode(id!!, token)

            listaRemoto.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println("deu ruim = ${t.message}")
                    loadError.value = true;
                    loading.value = false;
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            val bmp = BitmapFactory.decodeStream(response.body()!!.byteStream());
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
        } else {
            loadError.value = true;
            loading.value = false;
        }

    }

}