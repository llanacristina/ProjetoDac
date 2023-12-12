package br.com.develop.model.utils;

import java.io.Serializable;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CriptografarSenha implements Serializable{

	private static final long serialVersionUID = 1L;

	private CriptografarSenha() {

    }

    public static String encripta(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            String encoded = Base64.getEncoder().encodeToString(digest.digest());
            return encoded;
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return senha;
        }
    }
}
