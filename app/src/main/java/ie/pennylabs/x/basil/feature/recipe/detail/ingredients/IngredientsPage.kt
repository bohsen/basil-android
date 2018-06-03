package ie.pennylabs.x.basil.feature.recipe.detail.ingredients

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.pennylabs.x.basil.BasilApplication
import ie.pennylabs.x.basil.data.store.IngredientStore
import javax.inject.Inject

@SuppressLint("ViewConstructor")
class IngredientsPage(context: Context, recipeId: String) : RecyclerView(context) {
  @Inject
  lateinit var store: IngredientStore

  init {
    BasilApplication.component.inject(this)

    layoutManager = LinearLayoutManager(context)
    adapter = IngredientsRecyclerAdapter().apply {
      store.fetchForRecipe(recipeId).observe(context as AppCompatActivity, Observer {
        if (it != null) submitList(it)
      })
    }
  }
}