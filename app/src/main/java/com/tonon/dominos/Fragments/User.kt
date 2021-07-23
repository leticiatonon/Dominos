package com.tonon.dominos.Fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.tonon.dominos.Constants
import com.tonon.dominos.Orders
import com.tonon.dominos.R
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import kotlinx.android.synthetic.main.fragment_user.*


class User : Fragment() {

    companion object {
        fun newInstance(): User {
            val fragmentHome = User()
            val arg = Bundle()
            fragmentHome.arguments = arg
            return fragmentHome
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        logout.setOnClickListener {

            val pictureDialog = AlertDialog.Builder(context)
            pictureDialog.setTitle("Tem certeza que deseja sair?")
            val pictureDialogItems = arrayOf("Sim",
                "Voltar")
            pictureDialog.setItems(pictureDialogItems) { dialog, which ->
                when (which) {
                    0 -> FirebaseAuth.getInstance().signOut()
                    1 -> dialog.dismiss()
                }
            }
            pictureDialog.show()

        }

        pedido.setOnClickListener {
            val intent = Intent(context, Orders::class.java)
            startActivity(intent)
        }


        username.text = Constants.USER_NAME

    }

}



