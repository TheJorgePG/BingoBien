package net.azarquiel.bingobien.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.bingobien.R
import net.azarquiel.bingobien.util.Util
import net.azarquiel.bingoshare.model.Carton
import net.azarquiel.bingoshare.model.CartonesSelected
import net.azarquiel.recyclerviewpajaros.adapter.CustomAdapter

class MainActivity : AppCompatActivity() {

    private var cartonesCount: Int = 0
    private lateinit var cartonesclick: java.util.ArrayList<Carton>
    private lateinit var cartonesSelected: CartonesSelected
    private lateinit var adapter: CustomAdapter
    private lateinit var cartonesAL: java.util.ArrayList<Carton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Inyectar el xml del bingo en el movil
        Util.inyecta(this, "bingo.xml")
        btnjugar.setOnClickListener{btnjugarOnclick()}
        cartonesclick = ArrayList()
        cartonesSelected = CartonesSelected()
        initRV()
        getData()
        showData()
    }

    private fun btnjugarOnclick() {//Pasar de una pantalla a otra
        intent = Intent(this,GameActivity::class.java)
        //Paso info. Para pasar objetos al intent tienen que estar serializados (En la calse está serializado)
        //intent.putExtra("carton1",cartonesAL.get(0))   ESTOS SERÍA UNO A UNO


        cartonesSelected.cartones = cartonesclick
        intent.putExtra("cartones", cartonesSelected)

        startActivity(intent)
    }

    private fun initRV() {
        adapter= CustomAdapter(this,R.layout.row)
        rvcartones.adapter = adapter
        rvcartones.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        //Obtener el sharepreferences
        val cartonesShare = getSharedPreferences("bingo",Context.MODE_PRIVATE)
        val cartonesMap = cartonesShare.all
        cartonesAL = ArrayList()
        for(entry in cartonesMap.entries){
            //Añadir los cartones al arrayList
            cartonesAL.add(Carton(entry.value.toString(), false))
        }
    }

    private fun showData() {
        adapter.setCartones(cartonesAL)
    }

    fun clickCarton(v: View){
        //Saber cual es el cartón seleccionado
        val cartonMarcado = v.tag as Carton

        if(cartonMarcado.selected){
            cartonesclick.remove(cartonMarcado)
            cartonesCount--
        }else{
            cartonesclick.add(cartonMarcado)
            cartonesCount++
        }
        cartonMarcado.selected = !cartonMarcado.selected
        adapter.notifyDataSetChanged()

    }
}

