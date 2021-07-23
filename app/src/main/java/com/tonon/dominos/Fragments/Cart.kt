package com.tonon.dominos.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.tonon.dominos.CreateAccount
import com.tonon.dominos.Login
import com.tonon.dominos.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_cart.*


class Cart : Fragment() {

    companion object {
        fun newInstance(): Cart {
            val fragmentHome = Cart()
            val arg = Bundle()
            fragmentHome.arguments = arg
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        button_entrar.setOnClickListener {

            startActivity(Intent(context, Login::class.java))

        }

        button_criar_conta.setOnClickListener {
            val intent = Intent(context, CreateAccount::class.java)
            startActivity(intent)
        }
    }


}