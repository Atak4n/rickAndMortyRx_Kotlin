package com.atak4n.rickandmortyRx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atak4n.rickandmortyRx.adapter.RecyclerViewAdapter
import com.atak4n.rickandmortyRx.model.CharacterModel
import com.atak4n.rickandmortyRx.service.CharacterAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://rickandmortyapi.com/api/"
    private var characterModels: ArrayList<CharacterModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    private var compositeDisposable: CompositeDisposable? = null //disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        compositeDisposable = CompositeDisposable()
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }


    private fun loadData() {


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //where the data will be pulled in gson format
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CharacterAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io()) // the array to save the records is listening for data in the background
            .observeOn(AndroidSchedulers.mainThread()) //in which thred we will observe
            .subscribe(this::handleResponse)) //where do we deal with the result

    }

    private fun handleResponse(characterList : List<CharacterModel>) {

            characterModels = ArrayList(characterList)
            characterModels?.let {
                recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
                recyclerView.adapter = recyclerViewAdapter

            }
    }

    override fun onItemClick(characterModels: CharacterModel) {
        Toast.makeText(this,"tıklandı : ${characterModels.name}", Toast.LENGTH_LONG).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}