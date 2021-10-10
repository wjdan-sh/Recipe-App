package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var save: Button
    private lateinit var view: Button
    private lateinit var title: EditText
    private lateinit var author: EditText
    private lateinit var ingredients: EditText
    private lateinit var instructions: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save = findViewById(R.id.save)
        view = findViewById(R.id.view)
        title = findViewById(R.id.Title)
        author = findViewById(R.id.Author)
        ingredients = findViewById(R.id.Ingredients)
        instructions = findViewById(R.id.Instructions)


        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        save.setOnClickListener {

            val title= title.text.toString()
            val author= author.text.toString()
            val ingredients= ingredients.text.toString()
            val instructions= instructions.text.toString()
            //title , author , ingredients , instructions

            apiInterface!!.adduser(Data(title , author , ingredients , instructions)).enqueue(object : Callback<Data>{
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    Toast.makeText(this@MainActivity, "Recipe added ", Toast.LENGTH_SHORT).show()                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Recipe added ", Toast.LENGTH_SHORT).show()                }
            })

        }

        view.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent);
        }



    }
}

