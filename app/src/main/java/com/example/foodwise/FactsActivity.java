package com.example.foodwise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);



//create object of listview
        ListView listView=(ListView)findViewById(R.id.listview);

//create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<>();

//Add elements to arraylist
        arrayList.add('\u2023' + " Every 100 pounds of food waste in our landfills sends 8.3 pounds of methane into the atmosphere.");
        arrayList.add('\u2023' +" If global food waste were a country, it would rank third in greenhouse gas emissions after China and the U.S");
        arrayList.add('\u2023' +" The contribution of food waste emissions to global warming is almost equivalent (87%) to global road transport emissions");
        arrayList.add('\u2023' +" When the food is dumped in landfills, methane is released which heats up our planet twenty-five times more than carbon dioxide");
        arrayList.add('\u2023' +" SURPRISINGLY, 40% of the food waste in Canada comes from Households");
        arrayList.add('\u2023' +" Food waste accounts for 8% of the global greenhouse gases");
        arrayList.add('\u2023' +" Around 24% of the freshwater gets wasted as a result of food waste, while there are millions of people in the world who do not have access to clean drinking water.");



//Create Adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                new AlertDialog.Builder(view.getContext())
                        .setMessage("# "  + (i + 1) +   "\n\n" + arrayList.get(i).toString())
                        .setNegativeButton("Close", null).show();

                //Toast.makeText(FactsActivity.this,arrayList.get(i).toString(),Toast.LENGTH_LONG).show();
                //call a dialogue to display detail
            }
        });
    }


}

