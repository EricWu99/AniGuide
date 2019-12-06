package com.example.aniguide.ui.send

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aniguide.R

class SendFragment : Fragment() {

    private lateinit var sendViewModel: SendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendViewModel =
            ViewModelProviders.of(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        sendViewModel.text.observe(this, Observer {
            textView.text = it
        })

        //https://stackoverflow.com/questions/3312438/how-to-open-email-program-via-intents-but-only-an-email-program
        var emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        emailIntent.setType("vnd.android.cursor.item/email")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ericwu0199@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "\nAniGuide");
        startActivity(Intent.createChooser(emailIntent, "Send mail using..."));

        fragmentManager!!.popBackStack()

        return root
    }
}