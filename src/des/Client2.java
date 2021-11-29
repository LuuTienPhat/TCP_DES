/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package des;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Phat
 */
public class Client2 {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 7777;
        Socket client = new Socket(host, port);
        DataInputStream din = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        
        dos.writeUTF("hello");
        
        client.close();
    }
}
