package com.example.projeto_organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projeto_organizze.R;
import com.example.projeto_organizze.helper.DateCustom;
import com.example.projeto_organizze.model.Agendamento;

public class Agenda extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textProcedimentoSelecionado;
    private Button toolBtnInicio, toolBtnReservas, toolBtnAgendar, toolBtnMais;
    private Spinner spHorario;
    private String[] horariosDisponiveis = {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
    private String dataSelecionada, procedimento, horaSelecionada;

    public void setDataSelecionada(String dataSelecionada) { this.dataSelecionada = dataSelecionada; }

    public String getDataSelecionada() {
        return dataSelecionada;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getHoraSelecionada() {
        return horaSelecionada;
    }

    public void setHoraSelecionada(String horaSelecionada) { this.horaSelecionada = horaSelecionada; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        textProcedimentoSelecionado = findViewById(R.id.textProcedimentoSelecionado);
        calendarView = findViewById(R.id.calendarView);
        toolBtnInicio = findViewById(R.id.toolBtnInicio);
        toolBtnReservas = findViewById(R.id.toolBtnReservas);
        toolBtnAgendar = findViewById(R.id.toolBtnAgendar);
        toolBtnMais = findViewById(R.id.toolBtnMais);

        getSupportActionBar().setTitle("AGENDA");

        Intent intent = getIntent();
        Bundle procedimentoSelecionado = intent.getExtras();
        String procedimento = procedimentoSelecionado.getString("procedimento_selecionado");
        setProcedimento(procedimento);
        textProcedimentoSelecionado.setText(procedimento);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                setDataSelecionada(date);
            }
        });
        spHorario = findViewById(R.id.spHorario);

        //Criando o adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                android.R.id.text1,
                horariosDisponiveis
        ){

        };

        //Adicionando o adaptador á lista
        spHorario.setAdapter(adaptador);

        //Adicionando clique no item do spinner
        spHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String horarioSelecionado = spHorario.getItemAtPosition(position).toString(); //pego a informação e passo pra variavel horarioSelecionado
                setHoraSelecionada(horarioSelecionado);
                Toast.makeText(getApplicationContext(),horarioSelecionado, Toast.LENGTH_SHORT).show(); //Exibo num toast
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

    public void confirmarAgendamento(View view){

        //Instanciar classe do AlertDialog
        AlertDialog.Builder dialogConfirmar = new AlertDialog.Builder(this);

        //Configurar titulo e mensagem
        dialogConfirmar.setTitle("Confirmar agendamento");
        dialogConfirmar.setMessage("Deseja confirmar o agendamento de " + getProcedimento()+ " para o dia " + getDataSelecionada() + " às "+getHoraSelecionada()+" ?");

        //Configurar cancelamento
        dialogConfirmar.setCancelable(false); //não deixa o usuario sair sem clicar em uma das opções

        //Configurar icone
        dialogConfirmar.setIcon(android.R.drawable.ic_dialog_info);

        //Configurar ações para sim e não
        dialogConfirmar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Agendamento confirmado", Toast.LENGTH_LONG).show(); //Ação após confirmação

                Intent intent = new Intent(getApplicationContext(), Reservas.class);
                startActivity(intent);
                salvarAgendamento();
            }
        });
        dialogConfirmar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Agendamento cancelado", Toast.LENGTH_LONG).show();//Ação após cancelamento
            }
        });

        //Criar e exibir o AlertDialog
        dialogConfirmar.create();
        dialogConfirmar.show();

    }

    public void salvarAgendamento(){

        Agendamento agendamento = new Agendamento();
        agendamento.setData(dataSelecionada);
        agendamento.setHora(horaSelecionada);
        agendamento.setProcedimento(procedimento);
        agendamento.salvar();
    }
}