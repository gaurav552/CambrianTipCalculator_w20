package ca.davidpellegrini.cambrianlive_w20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Button;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
implements OnEditorActionListener, OnClickListener {

    private TextView tipAmountTextView, totalAmountTextView, tipPercentTextView;
    private EditText billAmountEditText;
    private Button percentUpButton, percentDownButton;

    private String billAmountString = "";
    private float tipPercent = 15f;

    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipAmountTextView = (TextView) findViewById(R.id.TipAmountTextView);
        totalAmountTextView = (TextView) findViewById(R.id.totalTextView);
        tipPercentTextView = findViewById(R.id.TipPercentTextView);
        billAmountEditText = (EditText) findViewById(R.id.BillAmountEditText);
        billAmountEditText.setOnEditorActionListener(this);

        percentDownButton = (Button) findViewById(R.id.decreaseButton);
        percentUpButton = (Button) findViewById(R.id.increaseButton);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }

    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("billAmountString", billAmountString);
        editor.putFloat("tipPercent", tipPercent);
        editor.commit();

        super.onPause();
    }

    public void onResume() {
        super.onResume();

        billAmountString = savedValues.getString("billAmountString", "");
        tipPercent = savedValues.getFloat("tipPercent", 0.15f);
        billAmountEditText.setText(billAmountString);
        calculateAndDisplay();
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        if(actionId == EditorInfo.IME_ACTION_DONE ){
            calculateAndDisplay();
        }

        return false;
    }

    public void calculateAndDisplay() {
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountString.equals("")){
            billAmount = 0;
        }
        else{
            billAmount = Float.parseFloat(billAmountString);
        }
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipAmountTextView.setText(currency.format(tipAmount));
        totalAmountTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        tipPercentTextView.setText(percent.format(tipPercent));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.decreaseButton:
                tipPercent = tipPercent - 0.01f;
                break;
            case R.id.increaseButton:
                tipPercent = tipPercent + 0.01f;
                break;
        }
        calculateAndDisplay();
    }
}
