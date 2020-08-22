package com.example.rstore.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rstore.Prevalent.Prevalent;
import com.example.rstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ResetPasswordActivity extends AppCompatActivity {

    private String check = "";
    private TextView pageTitle,questionsTitle;
    private EditText phoneNumber,question1,question2;
    private Button verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        check = getIntent().getStringExtra("check");
        pageTitle = findViewById(R.id.reset_page_title);
        questionsTitle = findViewById(R.id.title_questions);
        phoneNumber = findViewById(R.id.reset_phone_number);
        question1 = findViewById(R.id.question_1);
        question2 = findViewById(R.id.question_2);
        verifyBtn = findViewById(R.id.verify_btn);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        phoneNumber.setVisibility(View.GONE);

        if(check.equals("settings"))
        {
            pageTitle.setText("Set Questions");
            questionsTitle.setText("please set answer for the Following Security Questions..");
            verifyBtn.setText("Submit Answers");
            displayPreviousAnswers();

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    setAnswers();
                }
            });

        }
        else if(check.equals("login"))
        {
            phoneNumber.setVisibility(View.VISIBLE);

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    verifyAnswers();
                }
            });
        }
    }

    private void verifyAnswers()
    {
        String phone = phoneNumber.getText().toString();
        final String answerQuestion1 = question1.getText().toString().toLowerCase();
        final String answerQuestion2 = question2.getText().toString().toLowerCase();

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(ResetPasswordActivity.this, "please enter the phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(answerQuestion1) || TextUtils.isEmpty(answerQuestion2))
        {
            Toast.makeText(ResetPasswordActivity.this, "please answer both question", Toast.LENGTH_SHORT).show();
        }
        else
        {
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(phone);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if(!dataSnapshot.exists())
                    {
                        Toast.makeText(ResetPasswordActivity.this, "You are not Registered, please register first !!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, registerActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        String answer1 = dataSnapshot.child("Security Questions").child("answer1").getValue().toString();
                        String answer2 = dataSnapshot.child("Security Questions").child("answer2").getValue().toString();

                        if(!answerQuestion1.equals(answer1))
                        {
                            Toast.makeText(ResetPasswordActivity.this, "Your 1st answer is incorrect", Toast.LENGTH_SHORT).show();
                        }
                        else if(!answerQuestion2.equals(answer2))
                        {
                            Toast.makeText(ResetPasswordActivity.this, "Your 2nd answer is incorrect", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ResetPasswordActivity.this);
                            builder.setTitle("Please enter your new password");
                            final EditText newPassword = new EditText(ResetPasswordActivity.this);
                            newPassword.setHint("Write Password here...");
                            builder.setView(newPassword);
                            builder.setPositiveButton("Change", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    if(!newPassword.getText().toString().equals(""))
                                    {
                                        ref.child("password").setValue(newPassword.getText().toString())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                        {
                                                            Intent intent = new Intent(ResetPasswordActivity.this, loginActivity.class);
                                                            startActivity(intent);
                                                            Toast.makeText(ResetPasswordActivity.this, "Password change successfully", Toast.LENGTH_SHORT).show();
                                                        }

                                                    }
                                                });


                                    }
                                }
                            }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    finish();
                                }
                            });
                            builder.show();
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    private void displayPreviousAnswers()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(Prevalent.currentOnlineUser.getPhone())
                .child("Security Questions");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    question1.setText(dataSnapshot.child("answer1").getValue().toString());
                    question2.setText(dataSnapshot.child("answer2").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setAnswers()
    {
        String answerQuestion1 = question1.getText().toString().toLowerCase();
        String answerQuestion2 = question2.getText().toString().toLowerCase();

        if(TextUtils.isEmpty(answerQuestion1) || TextUtils.isEmpty(answerQuestion2))
        {
            Toast.makeText(ResetPasswordActivity.this, "please answer both question", Toast.LENGTH_SHORT).show();
        }
        else
        {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(Prevalent.currentOnlineUser.getPhone());

            HashMap<String,Object> userdataMap = new HashMap<>();
            userdataMap.put("answer1",answerQuestion1);
            userdataMap.put("answer2",answerQuestion2);

            ref.child("Security Questions").updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ResetPasswordActivity.this, "Security question set successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, homeActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
