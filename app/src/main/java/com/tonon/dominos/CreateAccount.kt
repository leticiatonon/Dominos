package com.tonon.dominos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.tonon.dominos.Fragments.Food
import com.tonon.dominos.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.txt_email
import kotlinx.android.synthetic.main.activity_login.txt_password

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)


        button_create.setOnClickListener {

            val email = txt_email_create.text.toString()
            val password = txt_password_create.text.toString()
            val username = txt_name_create.text.toString()


            if (email.isEmpty() || username.isEmpty()  || password.isEmpty()){
                Toast.makeText(this,"Preencha todos os campo", Toast.LENGTH_LONG).show()
            }else{
                createAccount()
            }
        }
    }

    private fun createAccount(){

        val email = txt_email_create.text.toString()
        val password = txt_password_create.text.toString()
        val username = txt_name_create.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                txt_email_create.setText("")
                txt_password_create.setText("")
                menuScreen()

                val intent = Intent(this, Food::class.java)
                intent.putExtra(Constants.USER_NAME, txt_name_create.text.toString())
                startActivity(intent)
            }
        }.addOnFailureListener {

            var fail = it

            when{
                fail is FirebaseAuthWeakPasswordException -> Toast.makeText(this,"Digite uma senha com no mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
                fail is FirebaseAuthUserCollisionException -> Toast.makeText(this,"Esta conta já foi cadastrada", Toast.LENGTH_SHORT).show()
                fail is FirebaseNetworkException -> Toast.makeText(this,"Sem conexão com a internet", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this,"Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun menuScreen(){
        val intent = Intent(this, Food::class.java)
        startActivity(intent)
    }
}