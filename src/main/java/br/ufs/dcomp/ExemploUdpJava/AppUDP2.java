package br.ufs.dcomp.ExemploUdpJava; 

import java.net.*;
import java.util.Scanner;

public class AppUDP2 {

    public static void main(String[] args) throws SocketException {
        try{
            
            //System.out.print("[ Alocando porta UDP                  ..................  ");
    	    DatagramSocket socket = new DatagramSocket(20000);
            //System.out.println("[OK] ]");
            
            byte[] buf = new byte[20]; // Cria um vetor em bytes, capacidade 20 bytes
            DatagramPacket pack = new DatagramPacket(buf, buf.length); // Cria um pacote vazio
            
            while(true) {
                System.out.print("[ Aguardando recebimento de mensagem  ..................  \n");
                socket.receive(pack); // Recebe no pacote vazio (É bloqueante)
                //System.out.println("[OK] ]");
                
                byte[] received_data = pack.getData();
                String received_msg = new String(received_data); 
                InetAddress origin_address = pack.getAddress();
                int origin_port = pack.getPort();
                
                System.out.println("Chat 2: "+received_msg);
                //System.out.println("  Endereço de origem:   "+origin_address.getHostAddress());
                //System.out.println("  Porta de origem:      "+origin_port);
                
                // ENVIAR RESPOSTA
                
                //String msg = "Recebido!";
                
                System.out.print("Você: ");
                Scanner teclado = new Scanner(System.in);
                String msg = teclado.nextLine();
                
                byte[] msg_buf = msg.getBytes(); // Representação em bytes
                int msg_size = msg_buf.length; // Comprimento do payload
                InetAddress destination_address = origin_address; // Envelopar o endereço de destino em uma classe Java especial para isso
                                                                              // Endereço host local (getLocalHost)
                int destination_port = origin_port; 
    
                DatagramPacket sendpack = new DatagramPacket(msg_buf, msg_size, destination_address, destination_port);
                
                socket.send(sendpack);
            
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
        
        
        
        

    }
}