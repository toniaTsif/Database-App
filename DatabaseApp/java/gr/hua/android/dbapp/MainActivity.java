package gr.hua.android.dbapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(MainActivity.this);
        SQLiteDatabase db =helper.getWritableDatabase();
        //TEXTVIEWS
        TextView text = findViewById(R.id.textView);
        text.setText("Welcome!\n\nPlease write a name, longitude, latitude and timestamp and press SAVE TO DATABASE to save your record in our database.\n\nThen, press NEXT to continue.");

        //BUTTON SAVE TO DATABASE
        Button buttonDb = findViewById(R.id.buttonDb);
        buttonDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String UserId = ((EditText)findViewById(R.id.editTextUserId)).getText().toString();
                    float Longitude = Float.valueOf(((EditText)findViewById(R.id.editTextLongitude)).getText().toString());
                    float Latitude = Float.valueOf(((EditText)findViewById(R.id.editTextLatitude)).getText().toString());
                    String Timestamp = ((EditText)findViewById(R.id.editTextTimestamp)).getText().toString();
                    if(UserId.isEmpty() || Timestamp.isEmpty()){
                        Toast.makeText(MainActivity.this,"Please fill name and timestamp!",Toast.LENGTH_LONG).show();
                    } else {
                        ContractRecord contract = new ContractRecord(UserId,Longitude,Latitude,Timestamp);
                        long id = helper.insertContact(contract);
                        Toast.makeText(MainActivity.this, id+"", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                        Toast.makeText(MainActivity.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                }
            }
        });

        //BUTTON NEXT - GOES TO SECOND ACTIVITY
        Button button2activity = findViewById(R.id.button2activity);
        button2activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                ContractRecord input = new ContractRecord();
                Cursor cursor =helper.searchTimestamp(input);
                ArrayList<String> cursorData = new ArrayList<String>();
                if (cursor.moveToFirst()){
                    while(!cursor.isAfterLast()){
                        String data = cursor.getString(cursor.getColumnIndex("timestamp"));
                        cursorData.add(data);
                        cursor.moveToNext();
                    }
                }
                cursor.close();
                i.putExtra("spinner",cursorData);
                startActivityForResult(i,0);
            }
        });
    }
}