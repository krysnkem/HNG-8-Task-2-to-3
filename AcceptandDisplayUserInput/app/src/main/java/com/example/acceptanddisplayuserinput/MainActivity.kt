package com.example.acceptanddisplayuserinput

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.acceptanddisplayuserinput.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUp(binding)

    }

    private fun setUp(binding: ActivityMainBinding?) {

        //set up alert dialog using AlertDialog.Builder
        val builder = AlertDialog.Builder(this)

        // inflate the dialog custom layout to "view" value
        val view = layoutInflater.inflate(R.layout.dialog_layout, null)

        // set the view for the alert dialog to the custom layout
        builder.setView(view)

        // create the alert dialog and save and instance of it in alertDialog value
        val alertDialog = builder.create()

        //get the user input Edit text
        val userInput = view.findViewById<EditText>(R.id.input_EditText)
        val okBtn = view.findViewById<Button>(R.id.okay_btn)
        val cancelBtn = view.findViewById<Button>(R.id.cancel_btn)

        // set the varible user input to an empty string
        var inputFromUser = " "

        //check button clicked
        var clickedBefore = false
        binding?.apply {
            // when enterInput button is clicked
            enterInputBtn.setOnClickListener {
                /*if the display text is and empty string and the button has not
                 be clicked before show the alert dialog and change the text on the button
                if the display text is visible, make it invisible and set the value to an empty string*/

                if(!clickedBefore && displayTextView.text.isNullOrBlank()){
                    clickedBefore = true
                    alertDialog.show()
                    enterInputBtn.text = "click to clear Input"

                }else{
                    clickedBefore = false
                    displayTextView.visibility = View.GONE
                    displayTextView.text = " "
                    enterInputBtn.text = "click to enter Input"

                }



            }
            //when the ok button is clicked
            okBtn.setOnClickListener {
                /*if the user input field is empty, toast a message "No input found"
                else, extract the input from the input field", dismiss the alert dialog and remove the text
                in the display text view*/
                if (userInput.text.isNullOrBlank() || userInput.text.isEmpty()) {
                    Toast.makeText(this@MainActivity, "No input found", Toast.LENGTH_SHORT).show()
                } else {
                    inputFromUser = userInput.text.toString()
                    alertDialog.dismiss()
                    displayTextView.visibility = View.VISIBLE
                    displayTextView.text = inputFromUser
                    userInput.text.clear()
                }

            }
            cancelBtn.setOnClickListener {
                alertDialog.dismiss()
                enterInputBtn.text = "click to enter Input"

            }
        }
        alertDialog.setOnDismissListener {
            clickedBefore = false
        }


    }


    // when activity is destroyed, set binding to null
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}