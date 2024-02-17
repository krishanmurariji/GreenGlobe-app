package com.SShayar.greenglobe.Post

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.SShayar.greenglobe.HomeActivity
import com.SShayar.greenglobe.Models.Reel
import com.SShayar.greenglobe.Models.User
import com.SShayar.greenglobe.R
import com.SShayar.greenglobe.databinding.ActivityReelsBinding
import com.SShayar.greenglobe.utils.REEL
import com.SShayar.greenglobe.utils.REEl_FOLDER
import com.SShayar.greenglobe.utils.USER_NODE
import com.SShayar.greenglobe.utils.uploadVedio
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ReelsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private lateinit var videoUrl: String
    lateinit var progressDialog: ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVedio(uri, REEl_FOLDER,progressDialog) { url ->
                if (url != null) {
                    videoUrl = url
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog= ProgressDialog(this)
        binding.selectReel.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user: User = it.toObject<User>()!!
                val reel: Reel = Reel(videoUrl!!, binding.caption.text.toString(),user.image!!)
                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).document().set(reel)
                        .addOnSuccessListener {
                            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                            finish()
                        }
                }
            }

        }
    }
}