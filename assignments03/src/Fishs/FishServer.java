package Fishs;

import java.io.*;
import java.net.*;
import java.util.*;

public class FishServer {
    private ArrayList<ObjectOutputStream> clients;
    private ArrayList<Fish> fishs;
    public static int clientID = -1;
    public FishServer() {
        clients = new ArrayList<>();
        fishs = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started on port 5000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                clientID++;

                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(clientID);
                clients.add(out);
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try { 
                            while (true) {
                                Fish fish = (Fish) in.readObject();
                                // ปลามีอยู่
                                if(fish!=null){
                                	//หากถูกลบ จะทำการส่งอ็อบเจ็กต์ "fishRemoved" ให้กับทุกตัวแปร ObjectOutputStream ที่อยู่ในลิสต์ "clients"
                                    if(fish.deleted){
                                        for(int i=0;i<clients.size();i++){
                                            ObjectOutputStream clientOut = clients.get(i);
                                            Fish fishRemoved = fishs.get(0);
                                            fishRemoved.deleted = true;
                                            clientOut.writeObject(fishRemoved);
                                            clientOut.flush();
                                            clientOut.reset();
                                        }
                                        
                                        fishs.remove(0);
                                    }// ปลาไม่ถูกลบ
                                    else{
                                        boolean found = false;
                                      //ตรวจสอบว่ามีอ็อบเจ็กต์ที่มี fishId เหมือนกับ "fish" ในลิสต์ "fishs" หรือไม่
                                        for(int i=0;i<fishs.size();i++){
                                        	//ถ้า fishId ของอ็อบเจ็กต์ที่ตรวจสอบตรงกับ fishId ของ "fish" ที่กำลังตรวจสอบในลูป, กำหนดค่า found เป็น true.
                                            if(fish.fishId.equals(fishs.get(i).fishId)){
                                                found = true;
                                            }
                                        }//ถ้าไม่พบอ็อบเจ็กต์ที่มี fishId เหมือนกับ "fish" ในลิสต์ "fishs"
                                        if(!found){
                                        	//เพิ่ม "fish" เข้าไปในลิสต์ fishs.
                                            fishs.add(fish);
                                        }
                                        //ตรวจสอบความเคลื่อนที่ของ "fish":
                                        if (fish.speedX > 0) { // Move right
                                            if (fish.clientId < clients.size() - 1) {
                                                // Add fish to next client
                                                fish.clientId++;
                                            } else {
                                                // Fish reached the last client, turn around
                                                fish.turnAround();
                                            }
                                        } else { // Move left
                                            if (fish.clientId > 0) {
                                                // Add fish to previous client
                                                fish.clientId--;
                                            } else {
                                                // Fish reached the first client, turn around
                                            	fish.turnAround();
                                            }
                                        }
                                        ObjectOutputStream clientOut = clients.get(fish.clientId);
                                        clientOut.writeObject(fish);
                                        clientOut.flush();
                                        clientOut.reset();
                                    }
                                }
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            clients.remove(out);
                        }
                    }
                }).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FishServer();
    }
}

