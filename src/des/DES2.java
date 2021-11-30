/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package des;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
public class DES2 {
    //DES encryptiom but using available class methods

//    public static String encrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
//        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//        byte[] cipherText = cipher.doFinal(text.getBytes());
//        String encodedText = Base64.getEncoder().encodeToString(cipherText);
//        return encodedText;
//    }
//
//    public static String decrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        byte[] decodeText = Base64.getDecoder().decode(text);
//        for (int i = 0; i < text.length(); i++) {
//            String hex = textToHex(text.substring(i, i + 1));
//            decodeText[i] = Byte.decode(hex);
//        }
//        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
//        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
//
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//        byte[] byteDecrypted = cipher.doFinal(decodeText);
//        String decrypted = new String(byteDecrypted);
//
//        return decrypted;
//    }
    public static String bytesToHex(byte[] bytes) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] encrypt(String text, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] cipherText = cipher.doFinal(text.getBytes());
        return cipherText;
    }

    public static String decrypt(byte[] decodeText, String SECRET_KEY) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] byteDecrypted = cipher.doFinal(decodeText);
        String decrypted = new String(byteDecrypted);

        return decrypted;
    }

    public static void main(String[] args) {
        try {
            String text = "hello th";
            String key = "12345678";

            byte[] cipherText = encrypt(text, key);
            System.out.println("Ban ma: " + bytesToHex(cipherText));
            String plainText = decrypt(cipherText, key);
            System.out.println("Ban ro: " + plainText);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DES2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
