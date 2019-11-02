package br.ufs.dcomp.ExemploUdpJava;

import java.net.*;
import java.util.Scanner;

public class AppUDP1 {

    public static void main(String[] args) throws SocketException {
        try{
            //System.out.print("[ Alocando porta UDP      ..................  ");
    	    DatagramSocket socket = new DatagramSocket(10000); //Socket, Bind
            //System.out.println("[OK] ]");
            
            while(true) {
                System.out.print("Você: ");
                Scanner teclado = new Scanner(System.in);
                String msg = teclado.nextLine();
                
                byte[] msg_buf = msg.getBytes(); // Representação em bytes
                int msg_size = msg_buf.length; // Comprimento do payload
                InetAddress destination_address = InetAddress.getLocalHost(); // Envelopar o endereço de destino em uma classe Java especial para isso
                                                                              // Endereço host local (getLocalHost)
                int destination_port = 20000; 
    
                //System.out.print("[ Montando datagrama UDP  ..................  ");
                DatagramPacket pack = new DatagramPacket(msg_buf, msg_size, destination_address, destination_port);
                //System.out.println("[OK] ]");
                
                //System.out.print("[ Enviando datagrama UDP  ..................  ");
                socket.send(pack);
                //System.out.println("[OK] ]");
            
            
                // RECEBER RESPOSTA
                
                System.out.print("[ Aguardando recebimento de mensagem  ..................  \n");
                byte[] buf = new byte[20]; // Cria um vetor em bytes, capacidade 20 bytes
                DatagramPacket rcvpack = new DatagramPacket(buf, buf.length); // Cria um pacote vazio
                
                socket.receive(rcvpack); // Recebe no pacote vazio (É bloqueante)
                
                byte[] received_data = rcvpack.getData();
                String received_msg = new String(received_data); 
                
                System.out.println("Chat 1: "+received_msg);
            
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
    }
}