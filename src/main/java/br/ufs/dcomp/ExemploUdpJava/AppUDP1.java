package br.ufs.dcomp.ExemploUdpJava;

import java.net.*;

public class AppUDP1 {

    public static void main(String[] args) throws SocketException {
        try{
            System.out.print("[ Alocando porta UDP      ..................  ");
    	    DatagramSocket socket = new DatagramSocket(10000); //Socket, Bind
            System.out.println("[OK] ]");
            
            String msg = "Olá!!!";
            
            byte[] msg_buf = msg.getBytes(); // Representação em bytes
            int msg_size = msg_buf.length; // Comprimento do payload
            InetAddress destination_address = InetAddress.getLocalHost(); // Envelopar o endereço de destino em uma classe Java especial para isso
                                                                          // Endereço host local (getLocalHost)
            int destination_port = 20000; 

            System.out.print("[ Montando datagrama UDP  ..................  ");
            DatagramPacket pack = new DatagramPacket(msg_buf, msg_size, destination_address, destination_port);
            System.out.println("[OK] ]");
            
            System.out.print("[ Enviando datagrama UDP  ..................  ");
            socket.send(pack);
            System.out.println("[OK] ]");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
    }
}