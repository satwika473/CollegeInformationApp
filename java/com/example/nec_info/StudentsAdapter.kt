package com.example.nec_info
import android.content.Context

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.util.*

class StudentsAdapter(
    private var studentsList: List<Student>,
    private val context: Context
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>(), android.widget.Filterable {

    private var filteredStudentsList: List<Student> = studentsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = filteredStudentsList[position]
        holder.nameTextView.text = student.name
        holder.studentnoTextView.text = "Parent No: ${student.studentno}"
        holder.parentnoTextView.text = "Student No: ${student.parentno}"

        // Click listeners to make calls
        holder.studentnoTextView.setOnClickListener {
            makeCall(student.studentno)
        }
        holder.parentnoTextView.setOnClickListener {
            makeCall(student.parentno)
        }
    }

    override fun getItemCount(): Int {
        return filteredStudentsList.size
    }

    // Implementing filter logic
    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.ROOT) ?: ""

                val filteredList = if (query.isEmpty()) {
                    studentsList
                } else {
                    studentsList.filter {
                        it.name.lowercase(Locale.ROOT).contains(query)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredStudentsList = results?.values as List<Student>
                notifyDataSetChanged()
            }
        }
    }

    private fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        context.startActivity(intent)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val studentnoTextView: TextView = itemView.findViewById(R.id.studentnoTextView)
        val parentnoTextView: TextView = itemView.findViewById(R.id.parentnoTextView)
    }
}
