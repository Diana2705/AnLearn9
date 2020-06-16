package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class MailActivity extends AppCompatActivity {
    @BindView(R.id.title)

    TextView title;

    @BindView(R.id.text)

    TextView text;

    Mail mail;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mail);

        mail=(Mail) getIntent().getExtras().get("mail");

        ButterKnife.bind(target this);

        title.setText(mail.getTitle());

        text.setText(mail.getMessage());

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();

        firebaseDatabase.getReference(path: "mail".child(Utils.md5Custom(mail.getRecipient()))

                .child(String.valueOf(Long.MAX_VALUE-mail.getDateInMS())).child("checked").setValue(true);
    }
}
