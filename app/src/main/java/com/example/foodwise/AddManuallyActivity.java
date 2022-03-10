package com.example.foodwise;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddManuallyActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText nameEdit;
    private TextView expDate;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref = db.collection("Items");

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manually);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add with Text");

        nameEdit = findViewById(R.id.man_nameET);
        expDate = findViewById(R.id.man_expdateTV);

        findViewById(R.id.man_saveBtn).setOnClickListener(this);
        findViewById(R.id.man_chooseDateBtn).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_refresh:
                startActivity(new Intent(this, AddManuallyActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(final View view) {

        switch (view.getId()) {
            case R.id.man_chooseDateBtn:
                showDatePicker();
                break;
            case R.id.man_saveBtn:
                saveItem();
                break;
        }
    }

    private void showDatePicker() {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void saveItem() {
        final String foodName = nameEdit.getText().toString();
        final String expDate = this.expDate.getText().toString();

        if (foodName.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a food name", Toast.LENGTH_LONG).show();
            return;
        }

        if (expDate.equals("Expiration Date:")) {
            Toast.makeText(getApplicationContext(), "Please enter an expiration date", Toast.LENGTH_LONG).show();
            return;
        }

        Timestamp timestampDate = new Timestamp(new Date());
        try {

            final DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            final Date date = formatter.parse(expDate);
            timestampDate = new Timestamp(date);

        } catch (final ParseException e) {
            System.out.println("Exception :" + e);
        }

        ref.add(new Item(foodName, expDate, auth.getUid(), timestampDate, Timestamp.now()));

        Toast.makeText(getApplicationContext(), "Food Item Saved", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddManuallyActivity.this, MainActivity.class));
    }

    @Override
    public void onDateSet(final DatePicker view, final int year, int month, final int dayOfMonth) {
        expDate.setText(++month + "/" + dayOfMonth + "/" + year);

    }

}


//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class AddManuallyActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_manually);
//    }
//}