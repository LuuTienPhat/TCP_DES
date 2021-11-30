/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package des;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
public class DES2 {

    // Global Charset Encoding
    public static Charset encodingType = StandardCharsets.UTF_8;

    // Text To Hex
    public static String textToHex(String text) {
        byte[] buf = null;
        buf = text.getBytes(encodingType);
        char[] HEX_CHARS = "0123456789abcdef".toCharArray();
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
        //return String.format("%040x", new BigInteger(1, text.getBytes(encodingType)));
    }

// Hex To Text
    public static String hexToText(String hex) {
        int l = hex.length();
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        String st = new String(data, encodingType);
        return st;
    }
    
    private static String encrypt1(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] cipherText = cipher.doFinal(text.getBytes());
        
        String encrypted2 = "";
        try {
            for (int i = 0; i < cipherText.length; i++) {
                int k = Byte.toUnsignedInt(cipherText[i]);
                encrypted2 += String.format("%02x", k);
            }
        } catch (Exception ex) {

        }
        System.out.println(encrypted2);
        return encrypted2;
    }

    private static byte[] encrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] cipherText = cipher.doFinal(text.getBytes());
        System.out.println(cipherText.toString());
        
        return cipherText;
    }

    public static String decrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        //byte[] decodeText = Base64.getDecoder().decode(text);
        //byte[] decodeText = text.getBytes();

        byte[] decodeText = null;
        for (int i = 0; i < text.length(); i++) {
            String hex = textToHex(text.substring(i, i + 1));
            decodeText[i] = Byte.decode(hex);
        }
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(decodeText);
        String decrypted = new String(byteDecrypted);

        return decrypted;
    }
    
    public static String decrypt(byte[] decodeText, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        //byte[] decodeText = Base64.getDecoder().decode(text);
        //byte[] decodeText = text.getBytes();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(decodeText);
        String decrypted = new String(byteDecrypted);

        return decrypted;
    }

    public static void main(String[] args) {
        try {
            String text = "xin chao cac ban";
            String key = "12345678";

            String cipherText = encrypt1(text, key);
            System.out.println("Ban ma: " + cipherText);
//            String result = decrypt(cipherText, key);
//            System.out.println("Ban ro: " + result);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DES2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
