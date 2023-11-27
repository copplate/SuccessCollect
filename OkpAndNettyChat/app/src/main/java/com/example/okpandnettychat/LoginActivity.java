package com.example.okpandnettychat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText etUserName;
    private Button btnLogin,btnUserOne,btnUserTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = findViewById(R.id.et_user_name);
        btnLogin = findViewById(R.id.btn_login);
        btnUserOne = findViewById(R.id.btn_user_one);
        btnUserTwo = findViewById(R.id.btn_user_two);
        btnLogin.setOnClickListener(v -> {
            String s = etUserName.getText().toString();
            if (!"张无忌".equals(s) && !"谢逊".equals(s)) {
                return;
            }
            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
            intent.putExtra("username", etUserName.getText().toString());
            startActivity(intent);
        });
        btnUserOne.setOnClickListener(v -> {
            etUserName.setText("张无忌");
        });
        btnUserTwo.setOnClickListener(v -> {
            etUserName.setText("谢逊");
        });



    }
}