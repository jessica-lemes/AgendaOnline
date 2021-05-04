package com.example.projeto_organizze.model;

import com.example.projeto_organizze.config.ConfiguracaoFirebase;
import com.example.projeto_organizze.helper.Base64custom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Agendamento {

    private String procedimento;
    private String data;
    private String hora;

    public Agendamento() {
    }

    public void salvar(){ //SALVANDO OS DADOS DE AGENDAMENTO NO FIREBASE

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("agendamento")
                .child(idUsuario)
                .push()
                .setValue(this);

    }


    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
