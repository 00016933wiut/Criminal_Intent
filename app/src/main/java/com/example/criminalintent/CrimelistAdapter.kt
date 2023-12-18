package com.example.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.databinding.ListItemCrimeBinding
import com.example.criminalintent.databinding.SeriousStuffListCrimeBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

private const val LAYOUT_ONE = 0
private const val LAYOUT_TWO = 1


class SimpleCrimeHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val binding: ListItemCrimeBinding = ListItemCrimeBinding.bind(itemView)

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.crimeIsSolved.text = crime.isSolved.toString().replaceFirstChar {  it.titlecase(Locale.getDefault()) }
        binding.crimePic.visibility = if(crime.isSolved) View.VISIBLE else View.GONE
        binding.root.setOnLongClickListener {
            Snackbar.make(
                binding.root,
                "${binding.crimeTitle.text}",
                2000
            ).show()
            return@setOnLongClickListener true
        }
    }
}

class SeriousCrimeHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val binding : SeriousStuffListCrimeBinding = SeriousStuffListCrimeBinding.bind(itemView)
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.callThePolice
        binding.root.setOnLongClickListener {
            Snackbar.make(
                binding.root,
                "${binding.crimeTitle.text}",
                2000
            ).show()
            return@setOnLongClickListener true
        }
    }
}

class CrimelistAdapter(private val crimes: List<Crime>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = if (position % 11 == 0) LAYOUT_TWO else LAYOUT_ONE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = when (viewType) {
            LAYOUT_ONE ->
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime,parent, false)
            LAYOUT_TWO ->
                LayoutInflater.from(parent.context).inflate(R.layout.serious_stuff_list_crime,parent, false)
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
        return if (viewType == LAYOUT_ONE) SimpleCrimeHolder(view) else SeriousCrimeHolder(view)
    }

    override fun getItemCount(): Int = crimes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder.itemViewType) {
            LAYOUT_ONE -> {
                val simpleHolder = holder as SimpleCrimeHolder
                simpleHolder.bind(crime)
            }
            LAYOUT_TWO -> {
                val seriousHolder = holder as SeriousCrimeHolder
                seriousHolder.bind(crime)
            }
        }
    }
}