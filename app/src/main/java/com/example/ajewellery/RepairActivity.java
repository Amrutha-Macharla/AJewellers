package com.example.ajewellery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

public class RepairActivity extends AppCompatActivity {
//    Spinner spinnerLanguages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Spinner spinnerLangauges;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio11:
                if (checked)
                    break;
            case R.id.radio22:
                if (checked)
                    break;
        }
    }

}