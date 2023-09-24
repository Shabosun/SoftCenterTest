package com.example.softcentertest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.softcentertest.R
import com.example.softcentertest.database.RecipeDatabase
import com.example.softcentertest.databinding.FragmentAddRecipeBinding
import kotlin.random.Random


class AddRecipeFragment : Fragment() {

    private var _binding : FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddRecipeBinding.inflate(inflater, container, false)

        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = RecipeDatabase.getInstance(application).recipeDao


        val viewModelFactory = AddRecipeViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddRecipeViewModel::class.java)

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_addRecipeFragment_to_recipesFragment)
                viewModel.onNavigatedToList()
            }
        })

        binding.addToDb.setOnClickListener{
            binding.apply {
                viewModel.addRecipe(caloriesEt.text.toString() + " kcal",
                    (carbosEt.text.toString() + " g"),
                    descrEt.text.toString(),
                    difficultyEt.text.toString().toInt(),
                    fatsEt.text.toString() + " g",
                    headlineEt.text.toString(),
                    Random.nextInt(100).toString(),
                    "",
                    nameEt.text.toString(),
                    proteinsEt.text.toString() + " g",
                    "",
                    "PT" + cookingTimesEt.text.toString() + "M")
            }
        }






        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }
}