package com.example.mariu.blackjack;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editPassword;
    Button btnAddData;
    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editTextName);
        editSurname = findViewById(R.id.editTextSurname);
        editPassword = findViewById(R.id.editTextPassword);
        btnAddData = findViewById(R.id.buttonAdd);
        btnViewAll = findViewById(R.id.buttonAddAll);
        AddData();
        viewAll();
    }
    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editPassword.getText().toString());
                        if (isInserted){
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            //show message
                            showMessage("Error","No data found");
                            return;
                        }else{
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("ID :" + res.getString(0) + "\n");
                                buffer.append("NAME :" + res.getString(1) + "\n");
                                buffer.append("SURNAME :" + res.getString(2) + "\n");

                                buffer.append("PASSWORD :" + res.getString(3) + "\n\n");
                            }
                            showMessage("Data",buffer.toString());
                        }
                    }
                }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
