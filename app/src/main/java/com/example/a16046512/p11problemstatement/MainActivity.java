package com.example.a16046512.p11problemstatement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String getcode = preferences.getString("code","");
        if (getcode.equals("738964")){
            String  list[] = new String[] { "Singapore Nation Day is on 9 Aug", "Singapore is 53 year old", "Theme is \"We are Singapore\""};
            ListView lv = (ListView)findViewById(R.id.lv);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
            lv.setAdapter(adapter);

        }else {
            getdialog();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String getcode = preferences.getString("code","");
        if (getcode.equals("738964")){
            String  list[] = new String[] { "Singapore Nation Day is on 9 Aug", "Singapore is 53 year old", "Theme is \"We are Singapore\""};
            ListView lv = (ListView)findViewById(R.id.lv);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
            lv.setAdapter(adapter);

        }else {
            getdialog();
        }
    }
    public void getdialog(){
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.passphrase, null);
        final EditText etPassphrase = (EditText) passPhrase
                .findViewById(R.id.editTextPassPhrase);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please login")
                .setView(passPhrase)
                .setCancelable(false)
                .setNegativeButton("NO ACCESS CODE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(MainActivity.this, "Please contact admin", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setTitle("Please login")
                .setView(passPhrase)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(etPassphrase.getText().toString().equals("738964")) {
                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            SharedPreferences.Editor prefEdit = prefs.edit();
                            prefEdit.putString("code",etPassphrase.getText().toString());
                            prefEdit.commit();
                            String  list[] = new String[] { "Singapore Nation Day is on 9 Aug", "Singapore is 53 year old", "Theme is \"We are Singapore\""};
                            ListView lv = (ListView)findViewById(R.id.lv);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                            lv.setAdapter(adapter);

                        }else{
                            Snackbar sn = Snackbar.make(passPhrase,"Invalid Code",Snackbar.LENGTH_LONG);
                            sn.show();



                        }
//                            Toast.makeText(MainActivity.this, "You had entered " +
//                                    etPassphrase.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action

        if (item.getItemId() == R.id.senttofriends) {


            String [] list = new String[] { "Email", "SMS"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                               //email
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_SUBJECT,"P11-KnowYourNationalDay");
                                email.putExtra(Intent.EXTRA_TEXT, "Join me at KnowYourNationalDay");
                                email.setType("message/rfc822");
                                startActivity(Intent.createChooser(email,"Choose an Email client:"));
                            } else if (which == 1) {
                                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.putExtra("sms_body","Join me at KnowYourNationalDay");
                                startActivity(smsIntent);

                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        } else if (item.getItemId() == R.id.quiz) {
            LayoutInflater inflater2 = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout passPhrase2 = (LinearLayout) inflater2.inflate(R.layout.quiz, null);




            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(passPhrase2)
                    .setCancelable(false)
                    .setNegativeButton("DON'T KNOW LAH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            builder.setView(passPhrase2)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            RadioGroup rg1 = (RadioGroup) passPhrase2.findViewById(R.id.rg1);
                            RadioGroup rg2 = (RadioGroup) passPhrase2.findViewById(R.id.rg2);
                            RadioGroup rg3 = (RadioGroup) passPhrase2.findViewById(R.id.rg3);
                            int selectedButtonId1 = rg1.getCheckedRadioButtonId();
                            int selectedButtonId2 = rg2.getCheckedRadioButtonId();
                            int selectedButtonId3 = rg3.getCheckedRadioButtonId();
                            RadioButton rb1 = (RadioButton) passPhrase2.findViewById(selectedButtonId1);
                            RadioButton rb2 = (RadioButton) passPhrase2.findViewById(selectedButtonId2);
                            RadioButton rb3 = (RadioButton) passPhrase2.findViewById(selectedButtonId3);


                            Log.i("MainActivity",rb1.getText()+":"+rb2.getText()+":"+rb3.getText());
                            int score = 0;
                            if (rb1.getText().equals("No")){
                                score=score+1;
                            }if (rb2.getText().equals("Yes")){
                                score=score+1;
                            }if(rb3.getText().equals("Yes")){
                                score=score+1;
                            }


                            Snackbar sn = Snackbar.make(getCurrentFocus(),"Score: "+score,Snackbar.LENGTH_LONG);
                            sn.show();

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        } else if (item.getItemId() == R.id.quit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("QUIT", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("NOT REALLY", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String getcode = preferences.getString("code","");
        if (getcode.equals("738964")) {
            getMenuInflater().inflate(R.menu.option, menu);
        }
        return true;
    }



}
