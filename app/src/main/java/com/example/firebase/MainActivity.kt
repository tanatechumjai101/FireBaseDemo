package com.example.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var heroList: MutableList<Hero>
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("heros")


        buttonSave.apply {
            setOnClickListener {
                saveHero()
            }
        }
        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (i in p0.children) {
                        val heroj = i.getValue(Hero::class.java)
                        heroList.add(heroj!!)
                    }

                }
                val adapter = Adapter(applicationContext, R.layout.heros, heroList)
                listView1.adapter = adapter
            }


        })

    }

    private fun saveHero() {
        val name = editTextName.text.toString().trim()
        if (name.isEmpty()) {
            editTextName.error = "Please enter name"
            return
        }

//        val mUsersRef = ref.child("users")
//        val mMessagesRef = ref.child("messages")
//        mUsersRef.child("id-12345").setValue("Jirawatee")

        val heroID = ref.push().key

        val herok = Hero(heroID.toString(), name, ratingBar.numStars)

        ref.child(heroID.toString()!!).setValue(herok).addOnCompleteListener {
            Toast.makeText(applicationContext.applicationContext, "HERO SAVE SUCCESSFULLY", Toast.LENGTH_SHORT).show()
        }

    }

}
