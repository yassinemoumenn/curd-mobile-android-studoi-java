package com.example.secondbrief;




import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editLastname,editEmail,editTextId ;
    Button CreateCandidat;
    Button Viewall;
    Button UpdateCandidat;
    Button DeleteCandidat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        /*SQLiteDatabase sqLiteDatabase = myDb.getWritableDatabase();
        myDb.onCreate(sqLiteDatabase);*/
    editName = (EditText)findViewById(R.id.editTextTName2);
    editLastname = (EditText)findViewById(R.id.editTextLastname);
    editEmail = (EditText)findViewById(R.id.editTextEmail);
     editTextId = (EditText)findViewById(R.id.editText_id);
    CreateCandidat = (Button)findViewById(R.id.buttonCreateCandidat);
        Viewall = (Button)findViewById(R.id.buttonViewCandidat);
        UpdateCandidat = (Button)findViewById(R.id.buttonUpdateCandidat);
        DeleteCandidat = (Button)findViewById(R.id.buttonDeleteCandidat);

        UpdateData();
        AddData();
        viewAll();
        DeleteData();
}

    public  void AddData()  {
        CreateCandidat.setOnClickListener(
                v -> {
                    boolean isInserted = myDb.insertData(editName.getText().toString(),
                            editLastname.getText().toString(),
                            editEmail.getText().toString() );
                    if(isInserted == true)
                        Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                }
        );
                    }
    public void UpdateData() {
        UpdateCandidat.setOnClickListener(
                v -> {
                    boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                            editName.getText().toString(),
                            editLastname.getText().toString(),editEmail.getText().toString());
                    if(isUpdate == true)
                        Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                }
        );
    }
 public void DeleteData() {
        DeleteCandidat.setOnClickListener(
                v -> {
                    Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                    if(deletedRows > 0)
                        Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                }
        );
    }
    public void viewAll() {
        Viewall.setOnClickListener(
                v -> {
                    Cursor res = myDb.getAllData();
                    if(res.getCount() == 0) {
                        // show message
                        showMessage("Error","Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id :"+ res.getString(0)+"\n");
                        buffer.append("Name :"+ res.getString(1)+"\n");
                        buffer.append("lastname:"+ res.getString(2)+"\n");
                        buffer.append("Email :"+ res.getString(3)+"\n\n");
                    }

                    // Show all data
                    showMessage("Data",buffer.toString());
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}



