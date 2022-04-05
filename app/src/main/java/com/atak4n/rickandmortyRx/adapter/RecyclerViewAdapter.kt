package com.atak4n.rickandmortyRx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atak4n.rickandmortyRx.R
import com.atak4n.rickandmortyRx.model.CharacterModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val characterList : ArrayList<CharacterModel>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(characterModels: CharacterModel)
    }
    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(characterModels: CharacterModel, position: Int, listener: Listener) {

            itemView.setOnClickListener {
                listener.onItemClick(characterModels)
            }
            itemView.name_Text.text = characterModels.name
            itemView.status_Text.text = characterModels.status
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(characterList[position],position,listener)
    }


}



