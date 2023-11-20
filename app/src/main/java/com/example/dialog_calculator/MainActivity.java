package com.example.dialog_calculator;

import static androidx.appcompat.R.layout.support_simple_spinner_dropdown_item;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    LinearLayout myDialog;
    RadioButton rb2;
    RadioButton rb1;
    RadioGroup rd;
    TextView texxt2;
    TextView texxt;
    EditText ad_A1;
    EditText ad_QD;



    Button returner;
    TextView A1;
    TextView AN;
    TextView qd;
    TextView n;
    TextView Sn;
    ListView lv;
    Double[] B = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    double a1 = 0;
    double qd12=0;
    boolean ans= true;

    AlertDialog.Builder adb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        A1 = findViewById(R.id.txt3);
        qd = findViewById(R.id.txt4);
        n = findViewById(R.id.txt5);
        Sn = findViewById(R.id.txt6);
        lv = findViewById(R.id.An);
        AN = findViewById(R.id.txt);
        ArrayAdapter<Double> adp = new ArrayAdapter<Double>(this, support_simple_spinner_dropdown_item,B);
        lv.setAdapter(adp);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        menu.add("Credits");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent g = new Intent(this,Credits1.class);
        startActivity(g);
        return super.onOptionsItemSelected(item);
    }

    public void WARNIG(){
        Toast.makeText(this,"Your 'q/d' or 'a1' is not a number",Toast.LENGTH_LONG).show();
    }

    public void array_updater(){
        B = list_veiw_filler();
        ArrayAdapter<Double> adp = new ArrayAdapter<Double>(this, support_simple_spinner_dropdown_item,B);
        lv.setAdapter(adp);
    }

    public Double[] list_veiw_filler()
    {
        Double[] C = new Double[20];

        for(int i=2;i<22;i++)
        {
            C[i-2] = ANQN(i,a1,qd12,ans);
        }
        return C;
    }


    public void text_setter(Double q,Double a1)
    {
        String st = " a1 = "+Double.toString(a1);
        String st2 = " q/d = "+Double.toString(q);
        A1.setText(st);
        qd.setText(st2);
        if(ans && qd12 == 0)
        {
            String st3 = " S(n) = "+Double.toString(a1);
            Sn.setText(st3);
        }
    }
    public double ANQN(int position,double a1,double qd,boolean answear)
    {
        if(answear)
        {
            return Qn_calculator(position,a1,qd);
        }
        else
        {
            return An_calculator(position,a1,qd);
        }
    }


    public double Qn_calculator(int position,double a1,double q){
        return (a1*(Math.pow(q,((double)position)-1.0)));
    }

    public double An_calculator(int position,double a1,double d){
        return (a1+((((double) position) - 1.0)*d));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String st = " n = "+Integer.toString(position+2);
        String st2 = " A(n) = "+Double.toString(B[position]);
        n.setText(st);
        AN.setText(st2);
        Sn_writer(position);
    }

    public void Sn_writer(int position)
    {
        String st = " S(n) = "+Double.toString(Sn_calculator_NORMAL(position+2,B[position]));
        String st2 = " S(n) = "+Double.toString(Sn_calculator_Engineer(position+2));
        if(ans && qd12 == 1)
        {
            Sn.setText(st);
        }
        else if(ans){
            Sn.setText(st2);
        }
        else
        {
            Sn.setText(st);
        }
    }


    public Double Sn_calculator_Engineer(int position)
    {
        return((a1*(Math.pow(qd12,position) -1.0))/(qd12-1));
    }

    public Double Sn_calculator_NORMAL(int position, Double an)
    {
        return((position*(an+a1))/2);
    }


    public void Load_data(View view) {
        myDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog,null);
        rb2 = (RadioButton) myDialog.findViewById(R.id.rd2);
        rb1 = (RadioButton) myDialog.findViewById(R.id.rd1);
        rd = (RadioGroup) myDialog.findViewById(R.id.rd);
        ad_A1 = (EditText) myDialog.findViewById(R.id.el2);
        ad_QD = (EditText) myDialog.findViewById(R.id.el);
        texxt = (TextView) myDialog.findViewById(R.id.textView);
        texxt2 = (TextView) myDialog.findViewById(R.id.textView2);

        adb = new AlertDialog.Builder(this);
        adb.setView(myDialog);
        adb.setTitle("parameters input");
        adb.setPositiveButton("Calculate",my_click);
        adb.setNegativeButton("Cancel",my_click);
        adb.setNeutralButton("Clear",my_click);
        adb.setCancelable(false);
        adb.show();
    }

    DialogInterface.OnClickListener my_click = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE)
            {
                String QD_str = String.valueOf(ad_QD.getText());
                String A1_str = String.valueOf(ad_A1.getText());
                if( check_valid_value(QD_str) && check_valid_value(A1_str)){
                    A1.setText("A1 = "+A1_str);
                    qd.setText("q/d = "+QD_str);
                    qd12 = strToDouble(QD_str);
                    a1 = strToDouble(A1_str);
                    ans = Engineering_or_Not(rb2);
                    array_updater();
                }
                else {
                    WARNIG();
                }
                dialog.dismiss();
            }
            else if (which == DialogInterface.BUTTON_NEGATIVE)
            {
                dialog.dismiss();
            }
            else if(which == DialogInterface.BUTTON_NEUTRAL){
                qd12 = 0;
                a1 = 0;
                ans = false;
                array_updater();
                dialog.dismiss();
            }
        }
    };

    public static boolean check_valid_value(String num){
        /* function gets a string and checks if it could be turned into a double.*/
        return !num.isEmpty();
    }
    public static boolean Engineering_or_Not(RadioButton rb1)
    {
        return (rb1.isChecked());
    }
    public static double strToDouble(String str) {
        return Double.parseDouble(str);
    }
}