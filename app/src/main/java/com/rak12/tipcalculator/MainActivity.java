package com.rak12.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;
import android.text.Editable;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.text.NumberFormat;

import static android.widget.SeekBar.*;

public class MainActivity extends AppCompatActivity {
private static final NumberFormat currencyFormat=NumberFormat.getCurrencyInstance();
private static final NumberFormat percentageFormat=NumberFormat.getPercentInstance();
private double billAmount=0.0;
private double percent=0.15;
private TextView a;
private TextView p;
private TextView t1;
private TextView t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (TextView) findViewById(R.id.a);
        p = (TextView) findViewById(R.id.p);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t1.setText(currencyFormat.format(0));
        t2.setText(percentageFormat.format(0));
        EditText amounteditText = (EditText) findViewById(R.id.amounteditText);
        amounteditText.addTextChangedListener(amounteditTextWatcher);
        SeekBar psb = (SeekBar) findViewById(R.id.percentSeekBar);
        psb.setOnSeekBarChangeListener(seekBarListener);
    }
        private void calculate()
        {
            p.setText(percentageFormat.format(percent));
            double tip=billAmount*percent;
            double total=billAmount+tip;
            t1.setText(currencyFormat.format(tip));
            t2.setText(currencyFormat.format(total));

        }
         private final OnSeekBarChangeListener seekBarListener=new OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 percent=progress/100.0;
                 calculate();
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         };
         private final TextWatcher amounteditTextWatcher=new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                 try {
                     billAmount=Double.parseDouble(s.toString());
                     a.setText(currencyFormat.format(billAmount));

                 }
                 catch (NumberFormatException e)
                 {
                     a.setText("");
                     billAmount=0.0;


                 }
             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         };
}
