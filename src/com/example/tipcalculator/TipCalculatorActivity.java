package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

	TextView tvTips, tvTipRate;
	EditText amount;
	SeekBar sbTipRate;
	private double tipRate;

	private final DecimalFormat myFormatter = new DecimalFormat("$###,###.##");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);

		tvTips = (TextView) findViewById(R.id.tvTips);
		tvTipRate = (TextView) findViewById(R.id.tvTipRate);
		amount = (EditText) findViewById(R.id.etTotalAmount);
		sbTipRate = (SeekBar) findViewById(R.id.sbTip);
		
		setupEditTextListener();
		setupTextChangedWatcher();
		setupSeekBarListener();
	}
	
	private void setupSeekBarListener() {
		sbTipRate.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tipRate = progress * 0.01;
				tvTipRate.setText(myFormatter.format(tipRate));
				String amountStr = amount.getText().toString();
				Double amountNum = validate(amountStr);
				if(amountNum == null)
					return;
				else
					updateTip(amountNum);
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				

				
			}});
		
	}

	private void setupTextChangedWatcher() {
		// TODO Auto-generated method stub
		amount.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				String amountStr = amount.getText().toString();
				if(amountStr.trim().isEmpty())
					return;
				Double amountNum = validate(amountStr);
				if(amountNum == null){
					amount.setError("Invalidate Input");
					tvTips.setText("");
				}
				else
					updateTip(amountNum);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}});
	}

	public void setupEditTextListener(){
		amount.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				amount.setText("");
				tvTips.setText("");
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipcalc, menu);
		return true;
	}

	/*public void calcTip(View v) {
		EditText totalAmount = (EditText) findViewById(R.id.etTotalAmount);
		Double tipsAmount = validate(totalAmount.getText().toString());
		if (tipsAmount == null) {
			//amount.setText("Invalidate Input");
			amount.setError("Invalidate Input");
		} else {
			if (v.getId() == R.id.btTip10)
				tipRate = 0.1;
			else if (v.getId() == R.id.btTip15)
				tipRate = 0.15;
			if (v.getId() == R.id.btTip20)
				tipRate = 0.2;

			updateTip(tipsAmount);
		}
	}*/

	private void updateTip(double amount){
		
		tvTips.setText("Tips:" + myFormatter.format(amount*tipRate));
	}
	private Double validate(String amountStr) {
		Double num;
		try {
			num = Double.parseDouble(amountStr);
		} catch (NumberFormatException e) {
			return null;
		}
		if (num <= 0)
			return null;
		return num;
	}
}
