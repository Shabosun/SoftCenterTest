package com.example.softcentertest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.softcentertest.R
import com.example.softcentertest.databinding.FragmentDetailRecipeBinding
import com.squareup.picasso.Picasso


class DetailRecipeFragment : Fragment() {

    lateinit var binding : FragmentDetailRecipeBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val recipe = DetailRecipeFragmentArgs.fromBundle(requireArguments()).recipe



        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            name.text = recipe.name
            headline.text = recipe.headline
            Picasso.get().load(recipe.image).into(img)
            descr.text = recipe.description
            calories.text = String.format(getString(R.string.calories), recipe.calories)
            proteins.text = String.format(getString(R.string.proteins), recipe.proteins)
            carbos.text = String.format(getString(R.string.carbos), recipe.carbos)
            fats.text = String.format(getString(R.string.fats), recipe.fats)
            time.text = String.format(getString(R.string.time), getDigitsFromString(recipe.time))
            difficult.rating = (recipe.difficulty).toFloat()


        }




        return view
    }

    fun getDigitsFromString(str : String) : String{
        return str.replace(Regex("[^\\d]"), "")
    }

}