package com.example.athos.cg;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private WelcomeFragment welcomeFragment = new WelcomeFragment();
    private TangramFragment tangramFragment = new TangramFragment();
    private ListFragment listFragment = new ListFragment();
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private Activity activity = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.btn_novo:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, tangramFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.btn_lista:
                    listFragment = new ListFragment();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, listFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.btn_sair:
                    onBackPressed();
                    return true;
            }
            return false;
        }
    };

    public void trataClickItem(View view){
        Toast toast = Toast.makeText(this, ((TextView)view.findViewById(R.id.id)).getText().toString(), Toast.LENGTH_SHORT);
        toast.show();

        //view.find

//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("");
//        alert.setMessage(findViewById(R.id.id).toString());
//        alert.show();

        switch (findViewById(R.id.id).toString()){
            case "0":
                break;
            case "1":
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, welcomeFragment);
        fragmentTransaction.commit();
    }

}
