package gr.hua.android.dbapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent = getIntent();

        //TEXTVIEWS
        TextView text2 = findViewById(R.id.textView2);
        text2.setText("Write a name and choose a timestamp and then press SEARCH to search for your record.");

        //SPINNER - DROP DOWN LIST FOR TIMESTAMPS SAVED TO DATABASE
        Spinner spinner= findViewById(R.id.spinner);
        ArrayList<String> cursorData = (ArrayList<String>) intent.getSerializableExtra("spinner");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, cursorData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //BUTTON SEARCH - GOES TO THIRD ACTIVITY
        Button button3activity = findViewById(R.id.button3activity);
        button3activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserId = ((EditText)findViewById(R.id.editTextUserid2)).getText().toString();
                String Spinner = spinner.getSelectedItem().toString();
                if(UserId.isEmpty() || Spinner.isEmpty()){
                    Toast.makeText(SecondActivity.this,"Please fill name and timestamp!",Toast.LENGTH_LONG).show();
                } else{
                    Intent intent1 = new Intent(SecondActivity.this, ThirdActivity.class);
                    ContractRecord input = new ContractRecord(UserId,Spinner);
                    intent1.putExtra("search", (Serializable) input);
                    startActivityForResult(intent1,0);
                }
            }
        });
    }
}
