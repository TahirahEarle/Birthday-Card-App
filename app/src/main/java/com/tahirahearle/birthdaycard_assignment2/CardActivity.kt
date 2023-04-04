package com.tahirahearle.birthdaycard_assignment2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class CardActivity : AppCompatActivity() {

    private lateinit var textName : TextView
    private lateinit var textAge : TextView
    private lateinit var textZodiac : TextView
    private lateinit var textBirthstone : TextView
    private lateinit var imageZodiac : ImageView
    private lateinit var imageBirthstone:ImageView

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        textName=findViewById(R.id.tv_Name)
        textAge=findViewById(R.id.tv_Age)
        textBirthstone=findViewById(R.id.tv_Birthstone)
        textZodiac=findViewById(R.id.tv_Chinesezodiac)
        imageZodiac= findViewById(R.id.chinese)
        imageBirthstone=findViewById(R.id.bStone)


        // data that was passed from the user input in the main activity
        val name = intent.getStringExtra("Name")
        val dob =  intent.getStringExtra("DOB")

        // transforming the string data into time and date
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val date = dateFormatter.parse(dob.toString())
        val calendar = Calendar.getInstance()
        calendar.time = date
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        // call the functions
        val birthstone = getBirthstone(month)
        val zodiac = getChineseZodiac(year)
        val age1 = calculateAge(date)

        // display calculation output
        textBirthstone.text="Birthstone : $birthstone"
        textZodiac.text="Chinese Zodiac Sign: $zodiac"
        textName.text= "Name : $name"
        textAge.text= "Age : $age1 Years Old"

        // Display chinese zodiac in image form
        val imageCZodiac = when(zodiac){
             "Monkey" -> R.drawable.monkey
             "Rooster" -> R.drawable.rooster
             "Dog" -> R.drawable.dog
             "Pig" -> R.drawable.pig
             "Rat" -> R.drawable.rat
             "Ox" -> R.drawable.ox
             "Tiger" -> R.drawable.tiger
             "Rabbit" -> R.drawable.rabbit
             "Dragon" -> R.drawable.dragon
             "Snake" -> R.drawable.snake
             "Horse" -> R.drawable.horse
            else -> R.drawable.goat
        }

        // Display birthstone in image form
        val imagebstone = when(birthstone){
            "Garnet" -> R.drawable.garnet
            "Amethyst" -> R.drawable.amethyst
            "Aquamarine" -> R.drawable.aquamarine
            "Diamond" -> R.drawable.diamond
            "Emerald" -> R.drawable.emerald
            "Pearl" -> R.drawable.pearl
            "Ruby" -> R.drawable.ruby
            "Peridot" -> R.drawable.peridot
            "Sapphire" -> R.drawable.sapphire
            "Opal" -> R.drawable.opal
            "Topaz"-> R.drawable.topaz
            else -> R.drawable.turquoise
        }

        // display the images in the card activity
        imageZodiac.setImageResource(imageCZodiac)
        imageBirthstone.setImageResource(imagebstone)
    }

    // calculate age
    private fun calculateAge(date: Date): Int {
        val currentDate = Date()
        val age = currentDate.time - date.time
        return (age / (1000 * 60 * 60 * 24 * 365.25)).roundToInt() // to change age from time into years
    }


    // calculate birthstone
    private fun getBirthstone(month: Int): String {
        return when (month) {
            1 -> "Garnet"
            2 -> "Amethyst"
            3 -> "Aquamarine"
            4 -> "Diamond"
            5 -> "Emerald"
            6 -> "Pearl"
            7 -> "Ruby"
            8 -> "Peridot"
            9 -> "Sapphire"
            10 -> "Opal"
            11 -> "Topaz"
            else -> "Turquoise"
        }
    }


    // calculate chinese zodiac
    private fun getChineseZodiac(year: Int): String {
        return when (year % 12) {
            0 -> "Monkey"
            1 -> "Rooster"
            2 -> "Dog"
            3 -> "Pig"
            4 -> "Rat"
            5 -> "Ox"
            6 -> "Tiger"
            7 -> "Rabbit"
            8 -> "Dragon"
            9 -> "Snake"
            10 -> "Horse"
            else -> "Goat or Ram"
        }
    }
}