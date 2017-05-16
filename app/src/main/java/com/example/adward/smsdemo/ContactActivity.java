package com.example.adward.smsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adward on 2017/5/9.
 */

public class ContactActivity extends Activity {
    private ListView lv;
    private List<Person> lists;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        //1,找到控件
        lv = (ListView) findViewById(R.id.lv);
        //2准备显示的数据，这里自己定义数据

    lists = new ArrayList<Person>();
    for (int i = 0; i < 20; i++) {
        Person p = new Person();
        p.setName("ouyang" + i);
        p.setPhone("138001388" + i);
        lists.add(p);
    }
        //展示数据
        lv.setAdapter(new MyAdapter());
        //给Listview设置点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取点击条目的数据
                String phone = lists.get(position).getPhone();
                //把数据返回给调用者
                Intent intent = new Intent();
                intent.putExtra("Phone",phone);
                //把结果返回给调用者
                setResult(1,intent);
                //关闭当前页面
                finish();
            }
        });
}
    class MyAdapter extends BaseAdapter {


        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(ContactActivity.this, R.layout.contact_item, null);
            } else {
                view = convertView;
            }
            //找到item中的控件，用来显示数据
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
           //展示数据
            tv_name.setText(lists.get(position).getName());
            tv_phone.setText(lists.get(position).getPhone());
            return view;
        }
    }
}
