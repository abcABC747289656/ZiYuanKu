package com.example.mylibrary77.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary77.Activity.Util.HttpclientUtil;
import com.example.mylibrary77.R;


public class ChangePasswordActivity extends AppCompatActivity {

    EditText et_old_password,et_password,et_password_confirm;
    Button btn_change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        et_old_password=findViewById(R.id.et_old_password);
        et_password=findViewById(R.id.et_password);
        et_password_confirm=findViewById(R.id.et_password_confirm);
        btn_change_password=findViewById(R.id.btn_change_password);
        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old_password=et_old_password.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                String password_confirm=et_password_confirm.getText().toString().trim();
                if(old_password.isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this,"旧密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this,"新密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(password_confirm.isEmpty()){
                    Toast.makeText(ChangePasswordActivity.this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    HttpclientUtil.ChangePassword(old_password,password,password_confirm);
                    Intent intent=new Intent(ChangePasswordActivity.this,LoginByNameActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
