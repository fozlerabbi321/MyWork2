package com.rabbi.mywork.view.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;
import com.rabbi.mywork.R;
import com.rabbi.mywork.view.fragments.VarififyOTP;

public class PhoneAhActivity extends AppCompatActivity {
    private EditText inputNumber;
    private Button btnSendOTP;

    private String cCodePlusNumber;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_ah);

        init();
    }
    private void init() {
        inputNumber = findViewById(R.id.input_number);
        btnSendOTP = findViewById(R.id.sendOTP);

        ccp = (CountryCodePicker) findViewById(R.id.ccp_sign_up);
        ccp.registerCarrierNumberEditText(inputNumber);

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uNumber = inputNumber.getText().toString();
                cCodePlusNumber = ccp.getFullNumberWithPlus();

                if(uNumber.length() == 0){
                    inputNumber.setError(getString(R.string.phone));
                }else {
                    Intent i=new Intent(PhoneAhActivity.this,VarifiedActivity.class);
                    i.putExtra("num",cCodePlusNumber);
                    startActivity(i);
                }
            }
        });
    }
}