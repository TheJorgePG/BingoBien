package net.azarquiel.bingobien.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import kotlinx.android.synthetic.main.activity_game.*
import net.azarquiel.bingobien.R
import net.azarquiel.bingoshare.model.Carton
import net.azarquiel.bingoshare.model.CartonesSelected

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var cartonesGame: List<Carton>
    private lateinit var cartonesSelected: CartonesSelected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        getData()
        showData()
    }

    private fun getData() {
        //Sacar los objetos. También para sacar un objeto se necesita serializarlos
        cartonesSelected = intent.getSerializableExtra("cartones") as CartonesSelected
        cartonesGame = cartonesSelected.cartones //Sacas los cartones del arrayList
    }

    private fun showData() {
        val linearPadre = lvGame as LinearLayout
        var contador = 0
        val idbghueco = R.drawable.bghueco
        val idbgnumero = R.drawable.bgnumero
        var contsizeCartones = 0



        cartonesGame.forEach{
            var contador = 0
            val carton: List<String> =it.numeros.split(",")
            val linearv = linearPadre.getChildAt(contsizeCartones) as LinearLayout
            for (i in 0 until linearv.size) {
                val linearh = linearv.getChildAt(i) as LinearLayout
                for (j in 0 until linearh.size) {
                    val tv = linearh.getChildAt(j) as TextView
                    tv.setBackgroundResource(if (carton[contador].equals("0")) idbghueco else idbgnumero)
                    tv.text = (if (carton[contador].equals("0")) " " else carton[contador])
                    tv.setOnClickListener(this)
                    contador++
                }

            }
            contsizeCartones++
        }
    }

    override fun onClick(v: View?) {
        val idbgcheck = R.drawable.bgnumerocheck
        val idbgnumero = R.drawable.bgnumero

        val tvpulsado = v as TextView //Una variable para coger el textview seleccionado
        //var marcado = tvpulsado.tag as Boolean //Una variable para saber si esta marcado o no está marcado

        //if (!marcado){
        if (tvpulsado.equals(" ")){
            tvpulsado.setBackgroundResource(idbgnumero)
        }else{
            tvpulsado.setBackgroundResource(idbgcheck)
        }

        //}else{
            //tvpulsado.setBackgroundColor(idbgnumero)
        //}

        //v.tag=!marcado
    }
}
