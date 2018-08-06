package com.appcourse.slater.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FinalCalculation extends AppCompatActivity implements View.OnClickListener {
    //Setup bundle to pass data from Activities
    private Bundle calc;
    //Set up variables to calculate tip and total
    private double BillAmount;
    private double TotalAmount;
    private double TipRounded;
    private int TotalRounded;
    private double TipCalculated;
    //Variables for the text fields and buttons
    private TextView TipShow;
    private TextView BillShow;
    private TextView TotalShow;
    private Button RoundCalc;

    //Decimal Format
    private static DecimalFormat d2 = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_calculation);

        //Create variables by r.ID
        TipShow = (TextView) findViewById(R.id.txtTipAmount);
        BillShow = (TextView) findViewById(R.id.txtBillAmount);
        TotalShow = (TextView) findViewById(R.id.txtFinalAmount);
        RoundCalc = (Button) findViewById(R.id.btnRound);


        calc = getIntent().getExtras();

        if(calc != null){
            int TipPerct = calc.getInt("TipPerct");
            double BillAmount = calc.getDouble("BillAmount");

            Calculations(TipPerct, BillAmount);
        }

        RoundCalc.setOnClickListener(this);

    }

    public void Calculations(int TipPerct, Double BillAmount){
        //Calculations
        TipCalculated = TipPerct * BillAmount / 100;
        TotalAmount = TipCalculated + BillAmount;


        //Set Calculated Values to Text Views
        TipShow.setText(String.valueOf(d2.format(TipCalculated)));
        BillShow.setText(String.valueOf(d2.format(BillAmount)));
        TotalShow.setText(String.valueOf(d2.format(TotalAmount)));

    }

    @ Override
    public void onClick(View v){
        RoundedCalcs();

    }

    public void RoundedCalcs(){

        TotalRounded = (int) Math.ceil(TotalAmount);
        TipRounded = TipCalculated + (TotalRounded - TotalAmount);


        //Set Calculated Values to Text Views
        TipShow.setText(String.valueOf(d2.format(TipRounded)));
        TotalShow.setText(String.valueOf(d2.format(TotalRounded)));
    }
}
