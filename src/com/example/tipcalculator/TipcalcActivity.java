package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class TipcalcActivity extends Activity {

	TextView tips;
	EditText amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipcalc);
		// tipAdapter = new SimpleAdapter(this);
		tips = (TextView) findViewById(R.id.tips);
		amount = (EditText) findViewById(R.id.totalAmount);
		setupEditTextListener();
	}
	
	public void setupEditTextListener(){
		amount.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				amount.setText("");
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipcalc, menu);
		return true;
	}

	public void calcTip(View v) {
		EditText totalAmount = (EditText) findViewById(R.id.totalAmount);
		Double tipsAmount = validate(totalAmount.getText().toString());
		if (tipsAmount == null) {
			//amount.setText("Invalidate Input");
			amount.setError("Invalidate Input");
		} else {
			if (v.getId() == R.id.tip10)
				tipsAmount *= 0.1;
			else if (v.getId() == R.id.tip15)
				tipsAmount *= 0.15;
			if (v.getId() == R.id.tip20)
				tipsAmount *= 0.2;

			DecimalFormat myFormatter = new DecimalFormat("$###,###.##");

			tips.setText("Tips:" + myFormatter.format(tipsAmount));
		}
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
