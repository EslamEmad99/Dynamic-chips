package com.example.androidchips

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private val bigList = arrayOf(
        ChipItem(
            id = 1,
            text = "10 K Egyptian pounds",
            icon = R.drawable.ic_10_k
        ),
        ChipItem(
            id = 2,
            text = "The hand have 5 fingers",
            icon = R.drawable.ic_hand
        ),
        ChipItem(
            id = 3,
            text = "Location",
            icon = R.drawable.ic_location
        ),
        ChipItem(
            id = 4,
            text = "I love rabbits",
            icon = R.drawable.ic_rabbit
        ),
        ChipItem(
            id = 5,
            text = "Smile",
            icon = R.drawable.ic_smile
        ),
        ChipItem(
            id = 6,
            text = "Location",
            icon = R.drawable.ic_location
        ),
        ChipItem(
            id = 7,
            text = "10 K Egyptian pounds 10 K Egyptian pounds 10 K Egyptian pounds 10 K Egyptian pounds",
            icon = R.drawable.ic_10_k
        )
    )

    private val smallList = arrayOf(
        ChipItem(
            id = 4,
            text = "I love rabbits",
            icon = R.drawable.ic_rabbit
        ),
        ChipItem(
            id = 5,
            text = "Smile",
            icon = R.drawable.ic_smile
        ),
        ChipItem(
            id = 6,
            text = "Location",
            icon = R.drawable.ic_location
        )
    )

    private lateinit var chipGroup: ChipGroup
    private lateinit var button: Button
    private lateinit var smallButton: Button
    private lateinit var bigButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chipGroup = findViewById(R.id.chip_group)
        button = findViewById(R.id.button)
        bigButton = findViewById(R.id.btn_big)
        smallButton = findViewById(R.id.btn_small)
        bigButton.setOnClickListener {
            loadBigList()
        }
        smallButton.setOnClickListener {
            loadSmallList()
        }
        button.setOnClickListener {
            val list = chipGroup.checkedChipIds
            Toast.makeText(this, "$list", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadBigList() {
        chipGroup.removeAllViews()
        bigList.forEach {
            addChipToGroup(it)
        }
    }

    private fun loadSmallList() {
        chipGroup.removeAllViews()
        smallList.forEach {
            addChipToGroup(it)
        }
    }

    private fun addChipToGroup(chipItem: ChipItem) {
        val chip = Chip(this)
        chip.id = chipItem.id
        chip.text = chipItem.text
        chip.setTextAppearance(R.style.ChipTextStyleAppearance)
        val drawable = ChipDrawable.createFromAttributes(
            this,
            null,
            0,
            R.style.CustomChipChoiceStyle
        )
        chip.setChipDrawable(drawable)
        createDrawableFromUrl("https://borghy.com/public/storage/images/carbrands/ferrari.png") {
            chip.chipIcon = it
            chipGroup.addView(chip)
        }
    }

    private fun createDrawableFromUrl(url: String, onDrawableReady: (Drawable) -> Unit) {
        Glide.with(this)
            .asDrawable()
            .load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    onDrawableReady(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
}