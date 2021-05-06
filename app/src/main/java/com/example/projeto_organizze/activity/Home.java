package com.example.projeto_organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.projeto_organizze.R;

public class Home extends AppCompatActivity {

    Button btnAgendar, btnReservas, toolBtnInicio, toolBtnReservas, toolBtnAgendar, toolBtnMais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAgendar = findViewById(R.id.btnAgendar);
        btnReservas = findViewById(R.id.btnReservas);
        toolBtnInicio = findViewById(R.id.toolBtnInicio);
        toolBtnReservas = findViewById(R.id.toolBtnReservas);
        toolBtnAgendar = findViewById(R.id.toolBtnAgendar);
        toolBtnMais = findViewById(R.id.toolBtnMais);

        getSupportActionBar().setTitle("HOME");

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Procedimento.class);
                startActivity(intent);
            }
        });

        btnReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reservas.class);
                startActivity(intent);
            }
        });

        toolBtnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        toolBtnReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reservas.class);
                startActivity(intent);
            }
        });

        toolBtnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Procedimento.class);
                startActivity(intent);
            }
        });

        toolBtnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showPopup(v);
            }
        });

    }

//    public void showPopup(View v) {
//        PopupMenu popup = new PopupMenu(this, v);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.actions, popup.getMenu());
//        popup.show();
//    }
//
//    public void showMenu(View v) {
//        PopupMenu popup = new PopupMenu(this, v);
//        // This activity implements OnMenuItemClickListener
//        popup.setOnMenuItemClickListener(this);
//        popup.inflate(R.menu.actions);
//        popup.show();
//
//    }
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.archive:
//                archive(item);
//                return true;
//            case R.id.delete:
//                delete(item);
//                return true;
//            default:
//                return false;
//        }


}