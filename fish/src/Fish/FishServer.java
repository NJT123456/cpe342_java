package Fish;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FishServer {
    private ArrayList<ObjectOutputStream> clients;
    private ArrayList<Fish> fishs;
    public static int clientID = -1;

    public FishServer() {
        clients = new ArrayList<>();
        fishs = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            System.out.println("Server started on port 9000...");

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
                                if(fish!=null){
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
                                    }else{
                                        boolean found = false;
                                        for(int i=0;i<fishs.size();i++){
                                            if(fish.fishId.equals(fishs.get(i).fishId)){
                                                found = true;
                                            }
                                        }
                                        if(!found){
                                            fishs.add(fish);
                                        }
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

