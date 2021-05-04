package com.example.projeto_organizze.helper;

import android.util.Base64;

public class Base64custom {

    public static String codificarBase64(String texto){

        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
        //o replaceAll tira os caracteres invalidos, neste caso os espaços antes e depois
    }

    public static String decodificarBase64(String textoCodificado){
            return new String(Base64.decode(textoCodificado, Base64.DEFAULT)) ;
    }

}

