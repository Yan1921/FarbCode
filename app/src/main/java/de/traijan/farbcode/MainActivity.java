package de.traijan.farbcode;

import android.graphics.Color;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvOutput;
    TextView ringOne, ringTwo, ringThree, ringFour, ringFifth;
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

        ringOne = findViewById(R.id.ringOne);
        ringTwo = findViewById(R.id.ringTwo);
        ringThree = findViewById(R.id.ringThree);
        ringFour = findViewById(R.id.ringFour);
        ringFifth = findViewById(R.id.ringFifth);

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

        sp1.setOnItemSelectedListener(this);
        sp2.setOnItemSelectedListener(this);
        sp3.setOnItemSelectedListener(this);
        sp4.setOnItemSelectedListener(this);
        sp5.setOnItemSelectedListener(this);

        colorRings = getResources().getIntArray(R.array.spinner_1_values);
        multiplicator = getFloatList(R.array.spinner_multi_values);
        toleranz = getFloatList(R.array.spinner_toleranz_values);
    }

    /** Berechnet den Widerstand
     * @param v Die View
     */
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
        else if(toleranz.get(indexes[4]) == -1 && colorRings[indexes[2]] != -1) { // Wenn die Toleranz nicht gesetzt ist, aber der 3. Farbring
            Toast.makeText(this, "Geben Sie die Toleranz an", Toast.LENGTH_SHORT).show();
            return;
        }

        int number;

        // 4 Ringe
        if(colorRings[indexes[2]] == -1)
            number = Integer.parseInt("" + colorRings[indexes[0]] + colorRings[indexes[1]]);
        else // 5 Ringe
            number = Integer.parseInt("" + colorRings[indexes[0]] + colorRings[indexes[1]] + colorRings[indexes[2]]);

        // Wenn die Toleranz -1 ist (nicht gesetzt) dann setzen wir sie auf 0 (Nur 3 Ringe), ansonsten ist sie dann der eigentliche Wert
        float tol = toleranz.get(indexes[4]) == -1 ? 0 : toleranz.get(indexes[4]);

        number *= multiplicator.get(indexes[3]);
        tvOutput.setText("" + formatNumber(number) + " ± " + tol + "%");
    }

    /** Erstellt aus der angebenen ID eine Float List
     * @param id Der Name des Array Values
     * @return Die Float List
     */
    List<Float> getFloatList(int id) {
        String[] cacheString = getResources().getStringArray(id);
        List<Float> floatList = new ArrayList<Float>();

        for(String s : cacheString)
            floatList.add(Float.parseFloat(s));

        return floatList;
    }

    public void drawRect(TextView t, String color) {
        switch(color.toLowerCase()){
            case "schwarz":
            case "black":
                t.setBackgroundColor(Color.rgb(0, 0, 0));
                break;
            case "braun":
            case "brown":
                t.setBackgroundColor(Color.rgb(74, 72, 45));
                break;
            case "rot":
            case "red":
                t.setBackgroundColor(Color.rgb(255, 0, 0));
                break;
            case "orange":
                t.setBackgroundColor(Color.rgb(245, 178, 44));
                break;
            case "gelb":
            case "yellow":
                t.setBackgroundColor(Color.rgb(245, 225, 44));
                break;
            case "grün":
            case "green":
                t.setBackgroundColor(Color.rgb(0, 255, 0));
                break;
            case "blau":
            case "blue":
                t.setBackgroundColor(Color.rgb(0, 0, 255));
                break;
            case "violett":
                t.setBackgroundColor(Color.rgb(136, 0, 255));
                break;
            case "grau":
            case "gray":
                t.setBackgroundColor(Color.rgb(100, 100, 100));
                break;
            case "weiß":
            case "white":
                t.setBackgroundColor(Color.rgb(255, 255, 255));
                break;
            case "gold":
                t.setBackgroundColor(Color.rgb(212, 175, 55));
                break;
            case "silber":
            case "silver":
                t.setBackgroundColor(Color.rgb(192, 206, 206));
                break;
            default:
                t.setBackgroundColor(Color.argb(0, 0, 0, 0));
                break;
        }
    }

    // https://stackoverflow.com/questions/41859525/how-to-go-about-formatting-1200-to-1-2k-in-android-studio
    public static String formatNumber(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c", count / Math.pow(1000, exp),"kMGTPE".charAt(exp-1));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView == sp1)
            drawRect(ringOne, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == sp2)
            drawRect(ringTwo, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == sp3)
            drawRect(ringThree, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == sp4)
            drawRect(ringFour, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == sp5)
            drawRect(ringFifth, (String)adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
