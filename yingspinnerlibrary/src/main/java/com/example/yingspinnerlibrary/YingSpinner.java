package com.example.yingspinnerlibrary;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class YingSpinner extends LinearLayout {

    private boolean cutLine;
    private int recyclerViewBackGround;
    private int itemTextSize;

    private ArrayList<String> itemList =  new ArrayList<String>();
    private YingSpinnerAdapter myYingSpinnerAdapter;
    private TextView myTextView;
    private ImageView myImageView;
    private PopupWindow popupWindow = null;
    private View popupwindowView;
    private View myView;

    public YingSpinner(Context context) {
        this(context,null);
    }

    public YingSpinner(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public YingSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView(){
        String infServie = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater =  (LayoutInflater) getContext().getSystemService(infServie);
        myView = layoutInflater.inflate(R.layout.yingspinner_view, this,true);
        myTextView= (TextView)findViewById(R.id.yingspinner_TextView);
        myImageView = (ImageView)findViewById(R.id.yingspinner_ImageView);
        myView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow == null ){
                    showPopWindow();
                }
            }
        });
    }

    //打开下拉列表弹窗
    private void showPopWindow() {
        myImageView.setImageDrawable(getResources().getDrawable(R.drawable.dropdown_up));
        popupwindowView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow, null);
        RecyclerView recyclerView = (RecyclerView) popupwindowView.findViewById(R.id.popupwindow_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myYingSpinnerAdapter = new YingSpinnerAdapter(itemList);
        myYingSpinnerAdapter.setTextSize(itemTextSize);
        recyclerView.setAdapter(myYingSpinnerAdapter);
        recyclerView.setBackgroundColor(recyclerViewBackGround);
        if (cutLine) {
            recyclerView.addItemDecoration(new DividerItemDecoration(popupwindowView.getContext(), DividerItemDecoration.VERTICAL));
        }
        myYingSpinnerAdapter.setOnItemClickLitener(new YingSpinnerAdapter.ViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TextView textView, int position) {
                myTextView.setText(itemList.get(position));
                closePopWindow();
            }
        });

        popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popupwindowView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(this);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !popupWindow.isFocusable()) {
                    //如果点击了popupWindow外面且焦点不在popupWindow上,不再往下dispatch事件：
                    closePopWindow();
                    return true;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });

    }

    //设置下拉列表数据
    public void setItemsData(ArrayList<String> itemList){
        this.itemList = itemList;
        myTextView.setText(itemList.get(0));
    }

    //取得TextView内容
    public String getSelectItem(){
        return myTextView.getText().toString();
    }

    //关闭下拉列表弹窗
    private void closePopWindow(){
        myImageView.setImageDrawable(getResources().getDrawable(R.drawable.dropdown_down));
        popupWindow.dismiss();
        popupWindow = null;
    }

    //设置选项字体大小
    public void setItemTextSize(int itemTextSize){
        this.itemTextSize=itemTextSize;
    }

    public void setTextViewStyle(int textSize,int textColor,boolean cutLine,int recyclerViewBackGround){
        myTextView.setTextSize(textSize);
        myTextView.setTextColor(textColor);
        this.cutLine=cutLine;
        this.recyclerViewBackGround=recyclerViewBackGround;
    }


}

