package com.example.simpletodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;
    EditText todoText;
    Button add;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoText = findViewById(R.id.todoText);
        add = findViewById(R.id.add);

        //Une fois le boutton clique nous voulons ajouter la valeur de la zone
        // de texte dans l'arrayList pour etre afficher dans
        // le Listview par l'intermediaire de l'arrayAdapter

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Le boutton add est clque
                if(!todoText.getText().toString().trim().isEmpty()){
                    items.add(todoText.getText().toString());
                    //Reinitialiser ma zone de texte
                    todoText.setText("");
                    itemsAdapter.notifyDataSetChanged();
                }
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView = findViewById(R.id.listview);
        listView.setAdapter(itemsAdapter);
        //Ce qui va s'passer quand on fait un
        // long press sur l'un des elements du listview

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( final AdapterView<?> adapterView, View view,final int i, long l) {
                alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setCancelable(true);
                alertDialog.setTitle("Take action");
                alertDialog.setMessage("Choose Action");
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        itemsAdapter.remove(adapterView.getItemAtPosition(i).toString());
                    }
                });
                alertDialog.setNegativeButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                return false;
            }
        });
    }
}
