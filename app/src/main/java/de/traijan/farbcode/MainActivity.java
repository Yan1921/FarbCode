package de.traijan.farbcode;

import android.content.res.TypedArray;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    Spinner sp1, sp2, sp3, sp4, sp5;
    Button button;

    int[] colorRings;
    List<Float> multiplicator;
    List<Float> toleranz;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_1_text, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.spinner_2_text, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.spinner_3_text, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
                this, R.array.spinner_multi_text, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(
                this, R.array.spinner_toleranz_text, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp5.setAdapter(adapter5);

        colorRings = getResources().getIntArray(R.array.spinner_1_values);
        multiplicator = getFloatArray(R.array.spinner_multi_values);
        toleranz = getFloatArray(R.array.spinner_toleranz_values);
    }

    public void calculate(View v) {
        int[] indexes = {
            sp1.getSelectedItemPosition(),
            sp2.getSelectedItemPosition(),
            sp3.getSelectedItemPosition(),
            sp4.getSelectedItemPosition(),
            sp5.getSelectedItemPosition()
        };

        // -1 ist der Name des Rings selber, quasi wie nichts gesetzt.
        if(colorRings[indexes[0]] == -1) {
            Toast.makeText(this, "Geben Sie den 1. Farbring an", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(colorRings[indexes[1]] == -1) {
            Toast.makeText(this, "Geben Sie den 2. Farbring an", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(multiplicator.get(indexes[3]) == -1) {
            Toast.makeText(this, "Geben Sie den Multiplikator an", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(toleranz.get(indexes[4]) == -1) {
            Toast.makeText(this, "Geben Sie die Toleranz an", Toast.LENGTH_SHORT).show();
            return;
        }

        int number;

        // 4 Ringe
        if(colorRings[indexes[2]] == -1)
            number = Integer.parseInt("" + colorRings[indexes[0]] + colorRings[indexes[1]]);
        else // 5 Ringe
            number = Integer.parseInt("" + colorRings[indexes[0]] + colorRings[indexes[1]] + colorRings[indexes[2]]);

        number *= multiplicator.get(indexes[3]);
        tvOutput.setText("" + number + " ± " + toleranz.get(indexes[4]) + "%");
    }

    List<Float> getFloatArray(int id) {
        String[] cacheString = getResources().getStringArray(id);
        List<Float> floatList = new ArrayList<Float>();

        for(String s : cacheString)
            floatList.add(Float.parseFloat(s));

        return floatList;
    }
}
