package DES;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Phat
 */
public class Server {

    public static String decrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        byte[] decodeText = Base64.getDecoder().decode(text);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(decodeText);
        String decrypted = new String(byteDecrypted);

        return decrypted;
    }

    public static String encrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] cipherText = cipher.doFinal(text.getBytes());

        String encrypted = Base64.getEncoder().encodeToString(cipherText);
        return encrypted;
    }

    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); // DES/ECB/PKCS5Padding for SunJCE

        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }

    public static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }

    public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
        encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
    }

    public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
        encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
    }

//    public static void main(String[] args) throws FileNotFoundException, Throwable {
//
//        FileInputStream seckey = new FileInputStream("D:\\Phat\\Documents\\NetBeansProjects\\TCP_DES\\key.txt");
//        byte[] b = new byte[8];
//        seckey.read(b);
//        //String key = "squirrel123"; // needs to be at least 8 characters for DES
//        String key = seckey.toString();
//        
//        FileInputStream fis = new FileInputStream("D:\\Phat\\Documents\\NetBeansProjects\\TCP_DES\\original.txt");
//        FileOutputStream fos = new FileOutputStream("D:\\Phat\\Documents\\NetBeansProjects\\TCP_DES\\encrypted.txt");
//        encrypt(key, fis, fos);
//
//        FileInputStream fis2 = new FileInputStream("D:\\Phat\\Documents\\NetBeansProjects\\TCP_DES\\encrypted.txt");
//        FileOutputStream fos2 = new FileOutputStream("D:\\Phat\\Documents\\NetBeansProjects\\TCP_DES\\decrypted.txt");
//        decrypt(key, fis2, fos2);
//    }
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        int port = 9999;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server is running at port " + port);

//        String text = "luu tien phat";
//        String key = "12345678";
//
//        String encryted = encrypt(text, key);
//        System.out.println(decrypt(encryted, key));
        while (true) {
            Socket client = server.accept();
            System.out.println(client.getInetAddress().getCanonicalHostName() + " connected to Server");
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            int option = dataInputStream.readInt();

            switch (option) {
                case 1:
                    {
                        System.out.println("You choose option 1");
                        String text = dataInputStream.readUTF();
                        String SECRET_KEY = dataInputStream.readUTF();
                        System.out.println("text: " + text);
                        System.out.println("SECRE_KEY: " + SECRET_KEY);
                        
//                String result = decrypt(text, SECRET_KEY);
//                result = result.toUpperCase();
//                dataOutputStream.writeUTF(result);
                        break;
                    }
                case 2:
                    {
                        System.out.println("You choose option 2");
                        String text = dataInputStream.readUTF();
                        String SECRET_KEY = dataInputStream.readUTF();
                        System.out.println("text: " + text);
                        System.out.println("SECRER_KEY: " + SECRET_KEY);
                        String result = encrypt(text, SECRET_KEY);
                        dataOutputStream.writeUTF(result);
                        break;
                    }
                case 3:
                    {
                        System.out.println("You choose option 3");
                        String text = dataInputStream.readUTF();
                        String SECRET_KEY = dataInputStream.readUTF();
                        System.out.println("text: " + text);
                        System.out.println("SECRET_KEY: " + SECRET_KEY);
                        String result = decrypt(text, SECRET_KEY);
                        result = result.toUpperCase();
                        dataOutputStream.writeUTF(result);
                        break;
                    }
                default:
                    break;
            }

            client.close();
            System.out.println(client.getInetAddress().getHostName() + " disconnected");

        }

    }
}
