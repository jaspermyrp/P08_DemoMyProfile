package sg.edu.rp.c346.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGpa;
    RadioGroup rgGender;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etGpa = findViewById(R.id.etGpa);
        rgGender = findViewById(R.id.rgGenders);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Data saved!", Toast.LENGTH_SHORT).show();
                savePreference();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String strName = prefs.getString("strName", "");
        float fltGpa = prefs.getFloat("fltGpa", 0);
        String strGender = prefs.getString("strGender", "Male");

        if(strGender.equals("Male")){
            rgGender.check(R.id.rbMale);
        }else{
            rgGender.check(R.id.rbFemale);
        }

        etName.setText(strName);
        etGpa.setText(fltGpa+"");
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreference();

    }

    private void savePreference(){
        String stringName = etName.getText().toString();
        float floatGpa = Float.parseFloat(etGpa.getText().toString());

        String gender = "";
        if(rgGender.getCheckedRadioButtonId() == R.id.rbMale){
            gender = "Male";
        }else{
            gender = "Female";
        }

        // Save user inputs //
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEdit = prefs.edit();
        prefsEdit.putString("strName", stringName).putFloat("fltGpa",floatGpa).putString("strGender",gender);
        prefsEdit.commit();
    }


}
