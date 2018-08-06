package com.appcourse.slater.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Sets Variables to use throughout
    private Button CalculateButton;
    private SeekBar PercentageBar;
    private TextView EnteredBill;
    private TextView TipView;
    private int seekbarPercent;
    private double BillAmount;
    private String Billtxtview;



    @  Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creates variables based off of the ID
        PercentageBar = (SeekBar) findViewById(R.id.TipPercentageBar);
        EnteredBill = (TextView) findViewById(R.id.txtBillAmount);
        CalculateButton = (Button) findViewById(R.id.btnCalculate);
        TipView = (TextView) findViewById(R.id.txtTipPercent);





        //Sends to OnClick Method
        CalculateButton.setOnClickListener(this);

        //Seek Bar Method
        PercentageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //finds progress of Seek Bar and sets as string in TextView
                TipView.setText(String.valueOf(seekBar.getProgress()) + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               //Created integer variable to get seek bar progress when touch stops
                seekbarPercent = seekBar.getProgress();

            }
        });

    }


    @ Override
    public void onClick(View v){
       //If stmt to check if bill amount was entered
        if(!EnteredBill.getText().toString().equals("")){
            //Set TextView of Entered Bill to string to parse to double for calcs
            Billtxtview = EnteredBill.getText().toString();
            BillAmount = Double.parseDouble(Billtxtview);

            //Where we code to send to new activity to show bill
            Intent CalcIntent = new Intent(MainActivity.this, FinalCalculation.class);
            CalcIntent.putExtra("TipPerct", seekbarPercent);
            CalcIntent.putExtra("BillAmount", BillAmount);
            //starts intent
            startActivity(CalcIntent);
        }else{
            Toast.makeText(MainActivity.this, "Please enter a bill amount", Toast.LENGTH_LONG).show();
        }



    }
}
