package sg.edu.rp.c346.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etGpa = findViewById(R.id.etGpa);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String strName = prefs.getString("strName", "");
        float fltGpa = prefs.getFloat("fltGpa", 0);

        etName.setText(strName);
        etGpa.setText(fltGpa+"");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Get user inputs //
        String stringName = etName.getText().toString();
        float floatGpa = Float.parseFloat(etGpa.getText().toString());

        // Save user inputs //
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEdit = prefs.edit();
        prefsEdit.putString("strName", stringName).putFloat("fltGpa",floatGpa);
        prefsEdit.commit();
    }


}
