package com.example.projeto_organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projeto_organizze.R;
import com.example.projeto_organizze.config.ConfiguracaoFirebase;
import com.example.projeto_organizze.helper.Base64custom;
import com.example.projeto_organizze.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.ArrayList;
import java.util.Base64;

public class CadastraUsuario extends AppCompatActivity {

    private Button toolBtnAgendar, toolBtnReservas, toolBtnInicio, toolBtnMais, btnConfirmaCad;
    private TextInputEditText txtEditNome, txtEditTelefone, txtEditEmail, txtEditSenha, txtEditConfirmaSenha;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_usuario);

        toolBtnAgendar = findViewById(R.id.toolBtnAgendar);
        toolBtnReservas = findViewById(R.id.toolBtnReservas);
        toolBtnInicio = findViewById(R.id.toolBtnInicio);
        toolBtnMais = findViewById(R.id.toolBtnMais);
        btnConfirmaCad = findViewById(R.id.btnConfirmaCad);

        txtEditNome = findViewById(R.id.txtEditNome);
        txtEditTelefone = findViewById(R.id.txtEditTelefone);
        txtEditEmail = findViewById(R.id.txtEditEmail);
        txtEditSenha = findViewById(R.id.txtEditSenha);
        txtEditConfirmaSenha = findViewById(R.id.txtEditConfirmaSenha);

        getSupportActionBar().setTitle("CADASTRO");

        btnConfirmaCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtEditNome.getText().toString();
                String telefone = txtEditTelefone.getText().toString();
                String email = txtEditEmail.getText().toString();
                String senha = txtEditSenha.getText().toString();
                String confirmaSenha = txtEditConfirmaSenha.getText().toString();

                validaCampos(nome, telefone, email, senha, confirmaSenha);
            }
        });
    }

    public boolean validaCampos(String nome, String telefone,String email, String senha,String confirmaSenha ) {

        boolean validado = true;
        //Valida se campos foram preenchidos e se senha confirmada é a mesma que a primeira
        if(!nome.isEmpty()){
            if(!telefone.isEmpty()){
                if(!email.isEmpty()){
                    if(!senha.isEmpty()){
                        if(!confirmaSenha.isEmpty()){
                            if(confirmaSenha.equals(senha)){
                                usuario = new Usuario();
                                usuario.setNome(nome);
                                usuario.setTelefone(telefone);
                                usuario.setEmail(email);
                                usuario.setSenha(senha);
                                salvaDadosNoBanco();
                                }else{
                                    mensagem("Senhas não conferem!");
                                }
                            }else{
                                mensagem("Confirme a senha!");
                            }
                        }else{
                            mensagem("Preencha a senha!");
                    }
                }else{
                    mensagem("Preencha o email!");
                }
            }else{
                mensagem("Preencha o telefone!");
            }
        }else{
            mensagem("Preencha o nome!");
        }
        return validado;
    }


    public void salvaDadosNoBanco( ){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao(); //cria nó para cadastrar usuario
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String idUsuario = Base64custom.codificarBase64(usuario.getEmail());
                    usuario.setIdUsuario(idUsuario);
                    usuario.salvar();

                    mensagem("Sucesso ao cadastrar usuário");
                    Intent intent = new Intent(getApplicationContext(), Login_.class);
                    startActivity(intent);

                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, digite um e-mail válido";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já foi cadastrada";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace(); //printar no Log o erro
                    }
                    Toast.makeText(CadastraUsuario.this, excecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void limparCampos() {
        txtEditNome.setText("");
        txtEditTelefone.setText("");
        txtEditEmail.setText("");
        txtEditSenha.setText("");
        txtEditConfirmaSenha.setText("");
    }

    public void mensagem (String msg){
        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, msg, duracao);
        toast.show();
    }
}