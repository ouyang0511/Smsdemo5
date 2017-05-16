package com.example.adward.smsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button add_btn;
    private EditText et_number;
    private EditText et_contact;
    private Button send_btn;
    private Button sms_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = (EditText) findViewById(R.id.et_number);
        et_contact = (EditText) findViewById(R.id.et_contact);
        send_btn = (Button) findViewById(R.id.send_btn);
        add_btn = (Button) findViewById(R.id.add_btn);
        sms_btn = (Button) findViewById(R.id.sms_btn);

        //点击按钮跳转到contactActivity页面
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SmsActivity.class);
               // startActivity(intent);

                startActivityForResult(intent,2);
            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            //点击按钮发送短信
            public void onClick(View v) {
                //获取发送的号码和内容
                String number = et_number.getText().toString().trim();
                String contact = et_contact.getText().toString().trim();
                //获取到smsmanager的实例
                SmsManager smsManager = SmsManager.getDefault();
                //
                ArrayList<String> divideMessages = smsManager.divideMessage(contact);
                for (String div : divideMessages) {
                    //1 destinationAddress是发送给送，
                    // 2 scaddress 服务中心号码
                    // 3 text 发送的内容
                    // 4 广播接收者
                    // 5 广播
                    smsManager.sendTextMessage(number, null, div, null, null);

                }
                Toast.makeText(getApplicationContext(), "发送完毕", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==1){
            String phone = data.getStringExtra("Phone");
            et_number.setText(phone);
        } else if(resultCode==2){
            String contact = data.getStringExtra("contact");
            et_contact.setText(contact);
        }



        super.onActivityResult(requestCode, resultCode, data);

    }
}
