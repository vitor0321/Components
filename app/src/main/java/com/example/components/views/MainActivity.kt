package com.example.components.views

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.components.mook.Mook
import com.example.components.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        buttonToastMe.setOnClickListener { toastme() }
        buttonSnackMe.setOnClickListener { snackme() }
        buttonGetSpinner.setOnClickListener { getSpinner() }
        buttonSetSpinner.setOnClickListener { setSpinner() }
        buttonProgress.setOnClickListener { progress() }
        buttonGetSeek.setOnClickListener { getSeek() }
        buttonSetSeek.setOnClickListener { setSeek() }
        buttonDate.setOnClickListener {
                startActivityForResult(
                    DateActivity.newIntent(MainActivity@ this),
                    DateActivity.REQ_CODE)
            }

        loadSpiner()

        spinerDinamic.onItemSelectedListener = this
        seekBar.setOnSeekBarChangeListener(this)
    }

    private fun toastme() {
        val toast = Toast.makeText(this, "Toast Notification", Toast.LENGTH_LONG)

        //MUDAR A COR DO TEXTO DA TOAST NOTIFICATION
        //toast.view.findViewById<TextView>(android.R.id.message).setTextColor(Color.GREEN)

        //CRIAR UM ESTILO PARA A TOAST
        val toastLayout = layoutInflater.inflate(R.layout.toast_custom, null)
        toast.view = toastLayout

        val textView = toast.view.findViewById<TextView>(R.id.testMensage)
        textView.setTextColor(Color.BLACK)
        textView.text = "Toast Notification"

        toast.show()
    }


    private fun snackme() {
        val snack =
            Snackbar.make(constraintLayout, "Snack Bar Notification!", Snackbar.LENGTH_SHORT)

        //Cor da backgraound
        //snack.view.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark))
        snack.view.setBackgroundColor(Color.RED)

        // pegar a cor do color
        ContextCompat.getColor(this,
            R.color.colorPrimaryDark
        )

        //mostra a ipm da action
        snack.setAction("Desfazer!") {
            Snackbar.make(constraintLayout, "Ação desfeita!", Snackbar.LENGTH_LONG).show()
        }
        //cor do texto da action
        snack.setActionTextColor(Color.BLUE)

        snack.show()
    }

    private fun getSpinner() {
        //val value = spinerDinamic.selectedItem.toString()
        val value = spinerDinamic.selectedItemPosition.toString()
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }

    private fun setSpinner() {
        spinerDinamic.setSelection(3)

    }

    private fun progress() {
        val progress: ProgressDialog = ProgressDialog(this)
        progress.setTitle("Título")
        progress.setMessage("Mensagem")
        progress.show()

        // fechar o ProgressDialog via codigo
        // progress.hide()
        // progress.dismiss()
    }

    private fun getSeek() {
        val value = seekBar.progress.toString()
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }

    private fun setSeek() {
        seekBar.progress = 10
    }


    private fun loadSpiner() {
        val list = Mook.getList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        spinerDinamic.adapter = adapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val id = view.id
        if (id == R.id.spinerDinamic) {
            val value: String = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        val id = seekBar.id
        if (id == R.id.seekBar) {
            textSeekBar.text = progress.toString()
        }
    }

    //coloquei o dedo e comecei a arrastar
    override fun onStartTrackingTouch(seekBar: SeekBar) { }

    //parei de arrastar, tirei o dedo
    override fun onStopTrackingTouch(seekBar: SeekBar) { }
}
