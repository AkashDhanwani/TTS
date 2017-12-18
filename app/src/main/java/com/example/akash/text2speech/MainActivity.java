package com.example.akash.text2speech;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvAns;
    Button btnSquare;
    EditText etNumber;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientaton = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientaton);

        tvAns = findViewById(R.id.tvAns);
        btnSquare = findViewById(R.id.btnSquare);
        etNumber = findViewById(R.id.etNumber);

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });

        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNumber.getText().toString().length() == 0 ) {
                    Toast.makeText(MainActivity.this, "Enter proper Numbers", Toast.LENGTH_LONG).show();
                    etNumber.requestFocus();
                    return;
                }
                int a = Integer.parseInt(etNumber.getText().toString());
                String result = "The Square of "+a+" is "+Integer.toString(a*a);
                tvAns.setText(result);
                tts.speak(result, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Kya aapko saschi me jana hai? :~(");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.setTitle("Exit");
        alertDialog.show();
    }//end of onBackPressed

}
