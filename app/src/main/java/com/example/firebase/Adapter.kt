package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter(val mCtx: Context,val layoutId: Int, val heroList: List<Hero>): ArrayAdapter<Hero>(mCtx,layoutId,heroList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater  = LayoutInflater.from(mCtx)
        val view = layoutInflater.inflate(layoutId,null)
        val textshow = view.findViewById<TextView>(R.id.TextViewShow)
        val hero = heroList[position]
        textshow.text = hero.name
        return view
    }

}