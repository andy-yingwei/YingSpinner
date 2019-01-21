package com.example.andy.yingspinner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yingspinnerlibrary.YingSpinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    YingSpinner myYingSpinner1;
    YingSpinner myYingSpinner2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myYingSpinner1 = (YingSpinner)findViewById(R.id.main_yingSpinner1);
        myYingSpinner2 = (YingSpinner)findViewById(R.id.main_yingSpinner2);
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0;i< 6;i++){
            list.add("下拉列表项"+(i+1));
        }

        myYingSpinner1.setItemData(list);
        myYingSpinner1.setChoose(2);
        myYingSpinner1.setTextViewStyle(20, Color.BLACK,false, Color.WHITE);

        myYingSpinner2.setItemData(list);
        myYingSpinner2.setTextViewStyle(20, Color.BLACK,true, Color.WHITE);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"选择的是："+myYingSpinner1.getSelectItem(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
