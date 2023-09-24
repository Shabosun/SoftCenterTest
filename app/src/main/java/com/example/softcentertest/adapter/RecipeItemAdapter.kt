package com.example.softcentertest.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.softcentertest.R
import com.example.softcentertest.databinding.CardRecipeBinding
import com.example.softcentertest.retrofit.Recipe
import com.squareup.picasso.Picasso


class RecipeItemAdapter(val clickListener: (recipe : Recipe) ->Unit)
    : ListAdapter<Recipe, RecipeItemAdapter.ViewHolder>(Comporator()) {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = CardRecipeBinding.bind(view)

        fun bind(recipe: Recipe, clickListener: (recipe: Recipe) -> Unit)
        {
            binding.headline.text = recipe.name
            binding.description.text = recipe.headline
            Picasso.get().load(recipe.thumb).into(binding.thumbImg)
            binding.root.setOnClickListener{clickListener(recipe)}
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_recipe, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class Comporator : DiffUtil.ItemCallback<Recipe>(){

        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

}