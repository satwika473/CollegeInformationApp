package com.example.nec_info
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nec_info.ApiService
import com.example.nec_info.R
import com.example.nec_info.RetrofitClient
import com.example.nec_info.Student
import com.example.nec_info.StudentsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentActivityActivity: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var studentsAdapter: StudentsAdapter
    private lateinit var searchView: SearchView
    private val studentsList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentsAdapter = StudentsAdapter(studentsList, this)
        recyclerView.adapter = studentsAdapter

        fetchStudentsFromApi()

        // Set up search functionality
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                studentsAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun fetchStudentsFromApi() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        apiService.getStudents().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        studentsList.clear()
                        studentsList.addAll(it)
                        studentsAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@StudentActivityActivity, "Failed to get students", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                Toast.makeText(this@StudentActivityActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
