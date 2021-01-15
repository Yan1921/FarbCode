package de.traijan.farbcode;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    Spinner sp1, sp2, sp3, sp4, sp5;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = findViewById(R.id.tv_output);
        sp1 = findViewById(R.id.sp_1);
        sp2 = findViewById(R.id.sp_2);
        sp3 = findViewById(R.id.sp_3);
        sp4 = findViewById(R.id.sp_4);
        sp5 = findViewById(R.id.sp_5);
        button = findViewById(R.id.button);
    }

    public void calculate(View v) {

    }
}
