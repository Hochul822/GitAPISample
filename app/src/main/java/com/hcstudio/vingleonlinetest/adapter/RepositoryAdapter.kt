package com.hcstudio.vingleonlinetest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hcstudio.vingleonlinetest.R
import com.hcstudio.vingleonlinetest.data.Repo
import java.util.*

/**
 * Created by hochul on 2018-05-13.
 */
class RepositoryAdapter(private val context: Context, items: ArrayList<Repo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = ArrayList<Repo>();

    init {
        this.data = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val contactView = inflater.inflate(R.layout.repo_list_item, parent, false)

        return RepositoryAdapter(contactView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val v = holder as RepositoryAdapter
        v.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RepositoryAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var v: View? = null
        internal var tv: TextView? = null
        internal var iv: ImageView? = null

        internal fun bind(repo: Repo) {

            tv!!.setText("")

            iv!!.setOnClickListener { v ->

            }
        }
    }
}