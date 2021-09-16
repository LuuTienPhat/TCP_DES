/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Phat
 */
public class Server {

    public static String decrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");

        byte[] byteEncrypted = text.getBytes();
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
        String result = new String(byteDecrypted);

        return result;
    }

    public static String encrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] byteEncrypted = cipher.doFinal(text.getBytes());
        String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);

        return encrypted;

    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        int port = 9999;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server is running at port " + port);

        while (true) {
            Socket client = server.accept();
            System.out.println(client.getInetAddress().getCanonicalHostName() + " connected to Server");
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            String text = dataInputStream.readUTF();
            String SECRET_KEY = dataInputStream.readUTF();

            String result = decrypt(text, SECRET_KEY);
            result = result.toUpperCase();
            dataOutputStream.writeUTF(result);

            client.close();
            System.out.println(client.getInetAddress().getHostName() + " disconnected");

        }

    }
}
