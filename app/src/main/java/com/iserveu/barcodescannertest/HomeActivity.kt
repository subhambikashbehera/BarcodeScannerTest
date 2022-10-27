package com.iserveu.barcodescannertest

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.cameralib.MainActivity
import com.example.cameralib.ScanBarCode
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        capture.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intentLauncher.launch(intent)
        }

        gallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickerLauncher.launch(intent)
        }



    }



    private val intentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val data = it.data?.extras
            val value = data?.getString("data")
            value?.let {outPut->
            result.text = outPut
            }
        }
    }

    private val pickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            ScanBarCode.scanBarCodeFromImage(it.data?.data!!,this){it2->
                result.text = it2
            }
        }
    }


}