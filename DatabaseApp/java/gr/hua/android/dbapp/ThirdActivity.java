package gr.hua.android.dbapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
   
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        DBHelper dbhelper = new DBHelper(ThirdActivity.this);
        Intent intent3 = getIntent();

        //TEXTVIEWS
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText("These are the results from your search.");
        TextView resultUserid = findViewById(R.id.resultUserid);
        TextView resultLongitude = findViewById(R.id.resultLongitude);
        TextView resultLatitude = findViewById(R.id.resultLatitude);
        TextView resultTimestamp = findViewById(R.id.resultTimestamp);

        //SEARCHING FOR THE GIVEN NAME & TIMESTAMP AND SHOWING THE RESULTS
        ContractRecord search = (ContractRecord) intent3.getSerializableExtra("search");
        Cursor cursor = dbhelper.searchContact(search);
        if (cursor.getCount() <= 0) {
            Toast.makeText(ThirdActivity.this, "There are no results for your search", Toast.LENGTH_LONG);
            textView3.setText("There are no results for your search");
        } else {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    resultUserid.setText(cursor.getString(cursor.getColumnIndex("userid")));
                    resultLongitude.setText(cursor.getString(cursor.getColumnIndex("longitude")));
                    resultLatitude.setText(cursor.getString(cursor.getColumnIndex("latitude")));
                    resultTimestamp.setText(cursor.getString(cursor.getColumnIndex("timestamp")));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
    }
}
