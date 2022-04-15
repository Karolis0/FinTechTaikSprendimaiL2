package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView tvInput;
    TextView tvResult;
    Button btnCalculate;
    Spinner spnSelection;
    Counter counter;

    TextView tvAnalysisResults;

    Button btnCalculatePaymentResult;
    TextView tvDollars;
    TextView tvDiscount;
    TextView tvPeriods;
    TextView tvCalculationResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = (TextView) findViewById(R.id.txtInput);
        tvResult = (TextView) findViewById(R.id.txtResults);
        btnCalculate = (Button) findViewById(R.id.btnCount);
        spnSelection = (Spinner) findViewById(R.id.spnSelection);

        tvDollars = (TextView) findViewById((R.id.txtDollarAmountPerAnnuity));
        tvDiscount = (TextView) findViewById((R.id.txtDiscountRate));
        tvPeriods = (TextView) findViewById((R.id.txtPeriods));
        tvCalculationResults = (TextView) findViewById((R.id.txtCalculationResult));
        btnCalculatePaymentResult = (Button) findViewById((R.id.btnCalculate));

        counter = new Counter();

        //Spinner init (3)
        String[] arraySpinner = new String[] {
                getString(R.string.Words),
                getString(R.string.Characters),
                getString(R.string.CharactersWSpace),
                getString(R.string.Letters),
                getString(R.string.Numbers)
        };
        Spinner s = (Spinner) findViewById(R.id.spnSelection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        // "context" must be an Activity, Service or Application object from your app.
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!Objects.equals(tvInput.getText().toString(), "")) {
                    int CountResult = counter.Count(tvInput.getText().toString(), spnSelection.getSelectedItem().toString());
                    if (CountResult > 0) {
                        tvResult.setText(Integer.toString(CountResult));
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.FalseInput), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.FalseInput), Toast.LENGTH_SHORT).show();
                }

                //  Geras how-to:
                //  https://www.youtube.com/watch?v=dFtxLCSu3wQ
                tvAnalysisResults = (TextView) findViewById(R.id.txtAnalysis);

                Python python = Python.getInstance();
                PyObject pyObj = python.getModule("L4Python");
                PyObject obj = pyObj.callAttr("CalculateStatistics", tvInput.getText().toString());
                tvAnalysisResults.setText(obj.toString());
            }
        });

        btnCalculatePaymentResult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!Objects.equals(tvDollars.getText().toString(), "") && !Objects.equals(tvPeriods.getText().toString(), "") && !Objects.equals(tvDiscount.getText().toString(), "")) {

                    //  Geras how-to:
                    //  https://www.youtube.com/watch?v=dFtxLCSu3wQ
                    tvAnalysisResults = (TextView) findViewById(R.id.txtAnalysis);

                    Python python = Python.getInstance();
                    PyObject pyObj = python.getModule("L4Python");
                    PyObject obj = pyObj.callAttr("CalculatePresentValueOfAnnuity", tvDollars.getText().toString(), tvDiscount.getText().toString(), tvPeriods.getText().toString());
                    tvCalculationResults.setText(obj.toString());
                }

            }
        });
    }
}