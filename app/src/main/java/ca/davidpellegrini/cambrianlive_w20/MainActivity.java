package ca.davidpellegrini.cambrianlive_w20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tipAmountTextView, totalAmountTextView, tipPercentTextView;
    private EditText billAmountEditText;
    private Button percentUpButton, percentDownButton;
    private CheckBox rememberTipCheckbox;
    private RadioGroup splitBillRadioGroup;
    private RadioButton onePerson, twoPeople, threePeople, fourPeople;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipAmountTextView = findViewById(R.id.TipAmountTextView);
        totalAmountTextView = findViewById(R.id.totalTextView);
        tipPercentTextView = findViewById(R.id.TipPercentTextView);
        billAmountEditText = findViewById(R.id.BillAmountEditText);

        percentDownButton = findViewById(R.id.decreaseButton);
        percentUpButton = findViewById(R.id.increaseButton);

        rememberTipCheckbox = findViewById(R.id.rememberTipCheckBox);

        splitBillRadioGroup = findViewById(R.id.splitRadioGroup);
        onePerson = findViewById(R.id.radioButton_1);
        twoPeople = findViewById(R.id.radioButton_2);
        threePeople = findViewById(R.id.radioButton_3);
        fourPeople = findViewById(R.id.radioButton_4);
    }
}
