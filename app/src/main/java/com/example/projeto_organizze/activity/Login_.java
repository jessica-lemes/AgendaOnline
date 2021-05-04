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
import com.example.projeto_organizze.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Login_ extends AppCompatActivity {

    private TextInputEditText txtLogin, txtSenha;
    private Button btnEntrar, btnCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtLogin.getText().toString();
                String senha = txtSenha.getText().toString();

                if(!email.isEmpty()){
                    if(!senha.isEmpty()){
                        usuario = new Usuario();
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
                        validaLogin();
                    }else{
                        mensagem("Preencha a senha!");
                    }
                }else{
                    mensagem("Preencha o email!");
                }
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastraUsuario.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void validaLogin(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();
                }else{

                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "E-mail e senha não correspondem a um usuário cadastrado";
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não está cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace(); //printar no Log o erro
                    }

                    Toast toast = Toast.makeText(Login_.this, excecao, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    /*public boolean consultarUsuario(){
        String login = txtLogin.getText().toString();
        String senha = txtSenha.getText().toString();
        boolean validado;

        if (login.equals("u") && senha.equals("s")){
            validado = true;
        } else {
            mensagem("Usuário / Senha inválida");
            limparCampos();
            validado = false;
        }
        return validado;
    }*/

    public void limparCampos() {
        txtLogin.setText("");
        txtSenha.setText("");
    }

    public void mensagem(String msg) {
        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, msg, duracao);
        toast.show();
    }
}