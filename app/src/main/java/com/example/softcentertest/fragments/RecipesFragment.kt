package com.example.softcentertest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.softcentertest.adapter.RecipeItemAdapter
import com.example.softcentertest.database.RecipeDatabase
import com.example.softcentertest.databinding.FragmentListRecipesBinding


class RecipesFragment : Fragment() {

    private var _binding : FragmentListRecipesBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter : RecipeItemAdapter






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = RecipeDatabase.getInstance(application).recipeDao
        val viewModelFactory = RecipesViewModelFactory(dao, view.context)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RecipesViewModel::class.java)


        //инициализируем адаптер для получения списка
        adapter = RecipeItemAdapter{recipe -> viewModel.onRecipeClicked(recipe)}
        binding.recipesList.adapter = adapter
        binding.recipesList.layoutManager = LinearLayoutManager(context)

        //получаем список
        viewModel.getRecipes()



        viewModel.recipes.observe(viewLifecycleOwner ,Observer{
            recipes -> adapter.submitList(recipes)
        })

        viewModel.navigateToRecipe.observe(viewLifecycleOwner, Observer{ recipe ->
            recipe?.let {
                val action = RecipesFragmentDirections
                    .actionRecipesFragmentToDetailRecipeFragment(recipe)
                this.findNavController().navigate(action)
                viewModel.onRecipeNavigated()
            }
        }
        )

//        binding.addRecipe.setOnClickListener {
//            //val action = RecipesFragmentDirections.actionRecipesFragmentToAddRecipeFragment()
//            //this.findNavController().navigate(action)
//        }


        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}