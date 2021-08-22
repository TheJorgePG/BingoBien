package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*
import net.azarquiel.bingobien.R
import net.azarquiel.bingoshare.model.Carton


/**
 * Created by pacopulido on 9/10/18.
 */
class CustomAdapter(val context: Context,
                    val layout: Int
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Carton> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setCartones(cartones: List<Carton>) {
        this.dataList = cartones
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Carton){
            //itemview es el item de diseño
            //al que hay que poner los datos del objeto dataItem
            val carton: List<String> = dataItem.numeros.split(",")
            val linearv = itemView.linearrow
            var contador = 0
            val idbghueco = R.drawable.bghueco
            val idbgnumero = R.drawable.bgnumero
            //val idbghueco = context.resources.getIdentifier("bghueco", "drawable", context.packageName)
            //val idbgnumero = context.resources.getIdentifier("bgnumero", "drawable", context.packageName)
            if(dataItem.selected)
                itemView.setBackgroundResource(android.R.color.black)
            else
                itemView.setBackgroundResource(android.R.color.transparent)
            for(i in 0 until linearv.size){
                //Con esta variable se rellena el cartón junto con el bucle de debajo
                val linearh = linearv.getChildAt(i) as LinearLayout
                for(j in 0 until linearh.size){
                    val tv = linearh.getChildAt(j) as TextView
                    tv.setBackgroundResource(if(carton[contador].equals("0")) idbghueco else idbgnumero)
                    tv.text = (if(carton[contador].equals("0")) " "  else carton[contador])
                    contador++
                }
            }
            itemView.tag = dataItem
        }
    }
}