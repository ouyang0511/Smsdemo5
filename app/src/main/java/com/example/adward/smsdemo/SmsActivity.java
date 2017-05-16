package com.example.adward.smsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Adward on 2017/5/15.
 */

public class SmsActivity extends Activity {

    private ListView sms_lv;
    String object[] = {"正在吃饭，稍后联系","正在敲代码，稍后联系","正在学习，稍后联系"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        sms_lv = (ListView) findViewById(R.id.sms_lv);

        sms_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = object[position];
                Intent intent = new Intent();
                intent.putExtra("contact",s);
                setResult(2,intent);
                finish();
            }
        });
        //展示数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.sms_item,R.id.textView,object);
        sms_lv.setAdapter(adapter);
    }
}
