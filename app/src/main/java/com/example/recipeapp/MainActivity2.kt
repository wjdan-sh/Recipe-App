package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    private lateinit var RV: RecyclerView
    private lateinit var Recipes: ArrayList<String>
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<RecipeList?>> = apiInterface!!.doGetListResources()
        RV = findViewById(R.id.rc)
        Recipes = ArrayList()
        btn = findViewById(R.id.add)

        RV.adapter = RecyclerViewAdapter(Recipes)
        RV.layoutManager = LinearLayoutManager(this)

        call?.enqueue(object : Callback<List<RecipeList?>> {
            override fun onResponse(
                call: Call<List<RecipeList?>>,
                response: Response<List<RecipeList?>>
            ) {

                val resource: List<RecipeList?>? = response.body()
                if (resource != null) {
                    for(user in resource) {

                        val title = user?.title
                        val author = user?.author
                        val ingredients = user?.ingredients
                        val instructions = user?.instructions
                        val Recipe = title.toString() +"\n"+ author.toString() +"\n"+ ingredients.toString() +"\n"+ instructions.toString()

                        Recipes.add(Recipe)

                        RV.adapter?.notifyDataSetChanged()
                        RV.scrollToPosition(Recipes.size-1)
                    }
                }

            }

            override fun onFailure(call: Call<List<RecipeList?>>, t: Throwable?) {
                call.cancel()
            }
        })

        btn.setOnClickListener {

            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent);
        }




    }
}