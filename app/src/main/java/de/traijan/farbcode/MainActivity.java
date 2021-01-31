package de.traijan.farbcode;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvOutput;
    TextView ringOne, ringTwo, ringThree, ringFour, ringFifth;
    Spinner sp1, sp2, sp3, spMulti, spToleranz;
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
        spMulti = findViewById(R.id.sp_4);
        spToleranz = findViewById(R.id.sp_5);
        button = findViewById(R.id.button);

        ringOne = findViewById(R.id.ringOne);
        ringTwo = findViewById(R.id.ringTwo);
        ringThree = findViewById(R.id.ringThree);
        ringFour = findViewById(R.id.ringFour);
        ringFifth = findViewById(R.id.ringFifth);

        setSpinner(sp1, R.array.spinner_1_text);
        setSpinner(sp2, R.array.spinner_2_text);
        setSpinner(sp3, R.array.spinner_3_text);
        setSpinner(spMulti, R.array.spinner_multi_text);
        setSpinner(spToleranz, R.array.spinner_toleranz_text);

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
            spMulti.getSelectedItemPosition(),
            spToleranz.getSelectedItemPosition()
        };

        // -1 ist der Name des Rings selber, quasi wie nichts gesetzt.
        if(colorRings[indexes[0]] == -1) {
            Toast.makeText(this, getString(R.string.chooseFirstColorRing), Toast.LENGTH_SHORT).show();
            return;
        }
        else if(colorRings[indexes[1]] == -1) {
            Toast.makeText(this, getString(R.string.chooseSecondColorRing), Toast.LENGTH_SHORT).show();
            return;
        }
        else if(multiplicator.get(indexes[3]) == -1) {
            Toast.makeText(this, getString(R.string.chooseMultiplicator), Toast.LENGTH_SHORT).show();
            return;
        }
        else if(toleranz.get(indexes[4]) == -1 && colorRings[indexes[2]] != -1) { // Wenn die Toleranz nicht gesetzt ist, aber der 3. Farbring
            Toast.makeText(this, getString(R.string.chooseToleranz), Toast.LENGTH_SHORT).show();
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
        tvOutput.setText(String.format(getString(R.string.result), formatNumber(number), "" + tol) + "%");
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
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                break;
            case "braun":
            case "brown":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                break;
            case "rot":
            case "red":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                break;
            case "orange":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                break;
            case "gelb":
            case "yellow":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                break;
            case "grün":
            case "green":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                break;
            case "blau":
            case "blue":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            case "violett":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.violett));
                break;
            case "grau":
            case "gray":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                break;
            case "weiß":
            case "white":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                break;
            case "gold":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gold));
                break;
            case "silber":
            case "silver":
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.silver));
                break;
            default:
                t.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent)); //
                break;
        }
    }

    public static String formatNumber(long count) { // TPE
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c", count / Math.pow(1000, exp),"kMG".charAt(exp-1));
    }

    public void setSpinner(Spinner sp, int textArrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, textArrayId, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView == sp1)
            drawRect(ringOne, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == sp2)
            drawRect(ringTwo, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == sp3)
            drawRect(ringThree, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == spMulti)
            drawRect(ringFour, (String)adapterView.getItemAtPosition(i));
        else if(adapterView == spToleranz)
            drawRect(ringFifth, (String)adapterView.getItemAtPosition(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
