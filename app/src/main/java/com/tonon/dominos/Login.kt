package com.tonon.dominos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.tonon.dominos.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        verifyLogin()

        button_login.setOnClickListener {

            val email = txt_email.text.toString()
            val password = txt_password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                userAuthentication()
            }
        }

    }

    private fun userAuthentication(){

        val email = txt_email.text.toString()
        val password = txt_password.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Login efetuado com sucesso",Toast.LENGTH_SHORT).show()
                orderScreen()
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> Toast.makeText(this,"Email ou senha incorretos", Toast.LENGTH_SHORT).show()
                erro is FirebaseNetworkException -> Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this,"Erro ao logar usuário", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun verifyLogin(){
        val userLogin = FirebaseAuth.getInstance().currentUser

        if(userLogin != null){
            orderScreen()
        }
    }

    private fun orderScreen(){
        val intent = Intent(this, Orders::class.java)
        startActivity(intent)
    }
}