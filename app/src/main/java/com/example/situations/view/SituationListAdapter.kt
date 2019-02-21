package com.example.situations.view

import android.content.Context
 import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.situations.R
import com.example.situations.model.Situation

class SituationListAdapter(private val context:Context): RecyclerView.Adapter<SituationListAdapter.SituationViewHolder>() {
   private lateinit var mSituation:List<Situation>
    fun getSiuation()=mSituation
    fun setSituation(situation: List<Situation>){

        mSituation=situation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SituationViewHolder {

        return SituationViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
      return mSituation.size
    }

    override fun onBindViewHolder(holder: SituationViewHolder, position: Int) {
        var currentSituation = mSituation[position]

        holder.situationTextView.text=currentSituation.name
    }

    class SituationViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    var situationTextView :TextView = itemView.findViewById(R.id.textView)

    }
}