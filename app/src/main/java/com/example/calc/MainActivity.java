package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res_tv,sol_tv;
    MaterialButton clear,all_clear,b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,ba,bs,bd,bm,be,b_dot,b_open,b_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res_tv=findViewById(R.id.txt_result);
        sol_tv=findViewById(R.id.txt_enter);

        assignId(clear,R.id.clear);
        assignId(all_clear,R.id.all_clear);
        assignId(b1,R.id.bt_1);
        assignId(b2,R.id.bt_2);
        assignId(b3,R.id.bt_3);
        assignId(b4,R.id.bt_4);
        assignId(b5,R.id.bt_5);
        assignId(b6,R.id.bt_6);
        assignId(b7,R.id.bt_7);
        assignId(b8,R.id.bt_8);
        assignId(b9,R.id.bt_9);
        assignId(b0,R.id.BT_0);
        assignId(b_dot,R.id.bt_dot);
        assignId(be,R.id.bt_equals);
        assignId(b_open,R.id.open_brac);
        assignId(b_close,R.id.close_brac);
        assignId(bd,R.id.divide);
        assignId(ba,R.id.add);
        assignId(bs,R.id.subsctract);
        assignId(bm,R.id.multiply);







    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataTocal=sol_tv.getText().toString();

        if(buttonText.equals("AC")){
            sol_tv.setText("");
            res_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            sol_tv.setText(res_tv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataTocal=dataTocal.substring(0,dataTocal.length()-1);
        }else{
            dataTocal=dataTocal+buttonText;
        }
        sol_tv.setText(dataTocal);
        String finalResult=getResult(dataTocal);
        if(!finalResult.equals("err")){
            res_tv.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }

            return finalResult;
        }catch (Exception e){
            return "err";
        }


    }
}