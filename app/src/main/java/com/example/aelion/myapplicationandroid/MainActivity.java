package com.example.aelion.myapplicationandroid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    Button btcancel, btvalidate, next, okButton;
    RadioButton rbJaime, rbJaimePas;
    String selectedButton;
    EditText editText;
    RadioGroup radioGroup;
    ImageView imageView;

    Calendar calendar = Calendar.getInstance();

    private static final int alertDialog = 1;
    private static final int timePicker = 2;
    private static final int datePicker = 3;
    private static final int ASYNCTASK = 4;
    private static final int WEBVIEWS = 5;
    private static final int MAPVIEW = 6;

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, alertDialog, 0, "AlertDialog");
        menu.add(0, timePicker, 0, "TimePicker");
        menu.add(0, datePicker, 0, "DatePicker");
        menu.add(0, ASYNCTASK, 0, "AsyncTask");
        menu.add(0, WEBVIEWS, 0, "WebActivity");
        menu.add(0, MAPVIEW, 0, "MapsActivity");
        menu.add(0, 25, 0, "New Game").setIcon(R.mipmap.ic_launcher).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case alertDialog:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Afficher mon Toast");
                alertDialogBuilder.setTitle("Mon titre");
                alertDialogBuilder.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "C'est un toast",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                alertDialogBuilder.show();
                break;

            case timePicker:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 14, 33, true);


                timePickerDialog.show();


                break;
            case datePicker:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case ASYNCTASK:

                MonAT monAT = new MonAT();
                monAT.execute();
                break;
            case WEBVIEWS:
                Intent intent = new Intent(this, WebActivity.class);
                startActivity(intent);
                finish();
                break;
            case MAPVIEW:
                Intent intent1 = new Intent(this, MapsActivity.class);
                startActivity(intent1);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btcancel = findViewById(R.id.btAnnuler);
        btvalidate = findViewById(R.id.btValider);
        next = findViewById(R.id.next);
        btcancel.setOnClickListener(this);
        btvalidate.setOnClickListener(this);
        next.setOnClickListener(this);

        rbJaime = findViewById(R.id.rb1);
        rbJaimePas = findViewById(R.id.rb2);

        editText = findViewById(R.id.editText);

        radioGroup = findViewById(R.id.radioGroup);

        imageView = findViewById(R.id.iv);


    }


    @Override
    public void onClick(View v) {
        if (v == btcancel) {
            editText.setText("");
            radioGroup.clearCheck();
            imageView.setImageResource(R.drawable.ic_delete_forever_black_48dp);
            imageView.setColorFilter(Color.RED);
            Toast.makeText(this, "Opération annulée", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.btValider) {
            if (rbJaime.isChecked()) {
                selectedButton = rbJaime.getText().toString();
                editText.setText(selectedButton);
            } else if (rbJaimePas.isChecked()) {
                selectedButton = rbJaimePas.getText().toString();
                editText.setText(selectedButton);
            }
            imageView.setImageResource(R.drawable.ic_done_black_48dp);
            imageView.setColorFilter(Color.GREEN);
            Toast.makeText(this, "Choix enregistré", Toast.LENGTH_SHORT).show();
        } else if (v == next) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(this, "L'heure choisie est " + hourOfDay + "h" + minute, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, "La date est : " + dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
    }

    public class MonAT extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            //WSUtils.sendGetOkHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Toast.makeText(MainActivity.this, "AsyncTask effectué", Toast.LENGTH_SHORT).show();
        }
    }

}