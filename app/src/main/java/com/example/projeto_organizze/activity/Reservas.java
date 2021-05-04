package com.example.projeto_organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projeto_organizze.R;

public class Reservas extends AppCompatActivity {

    private ListView listaReservas;
    Button toolBtnInicio, toolBtnReservas, toolBtnAgendar, toolBtnMais;

    private String[] itens = {
            "Botox - Dia 14/05/2021 às 16:00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        listaReservas = findViewById(R.id.listaReservas);
        toolBtnInicio = findViewById(R.id.toolBtnInicio);
        toolBtnReservas = findViewById(R.id.toolBtnReservas);
        toolBtnAgendar = findViewById(R.id.toolBtnAgendar);
        toolBtnMais = findViewById(R.id.toolBtnMais);

        getSupportActionBar().setTitle("RESERVAS");

        // Criando o Adaptador da lista de String
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(), // 1º parametro é o context
                android.R.layout.simple_list_item_1, //2º parametro é um layout padrao do Android
                android.R.id.text1, //3º parametro é o id do componente aonde serao inseridos os dados do array, neste caso é o text1 que é um TextView
                itens //4º parametro é a listagem
        );

        //Adicionando o adaptador á lista
        listaReservas.setAdapter(adaptador);

        //Adicionando clique na lista
        listaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valorSelecionado = listaReservas.getItemAtPosition(position).toString(); //Aqui é aonde eu recupero o item

                Toast.makeText(getApplicationContext(),valorSelecionado, Toast.LENGTH_LONG).show(); //Aqui é onde eu passo a informação pra tela
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
            //vai para a pagina de configurações
        }
    });
    }
}