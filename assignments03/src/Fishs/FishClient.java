package Fishs;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class FishClient extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Fish> fishs;
	private JPanel fishPanel;
	public Timer timer;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int id;
	private Fish fishRemoved = null;
	private int randpic;

	public FishClient(String serverAddress, int serverPort) {

		setSize(500, 400);
		setVisible(true);

		fishs = new ArrayList<>();
		fishPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ArrayList<Fish> fishsCopy = new ArrayList<>(fishs);
				for (Fish fish : fishsCopy) {
					fish.draw(g);
				}
			}
		};
		add(fishPanel);

		try {
			socket = new Socket(serverAddress, serverPort);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			try {
				id = (Integer) in.readObject();
				setTitle("Bouncing fish (client id: " + id + ")");
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							Fish fish = (Fish) in.readObject();
							if (fish != null) {
								if (fish.deleted) {
									fishRemoved = fish;
								}
								// ปลาไม่ถูกลบ
								
								else {
									boolean found = false;
									//หาก fish.deleted เป็น false จะตรวจสอบว่าอ็อบเจ็กต์ "fish" นี้มี fishId ที่ซ้ำกับปลาในลิสต์ "fishs" 
									for (int i = 0; i < fishs.size(); i++) {
										////ถ้า fishId ของอ็อบเจ็กต์ที่ตรวจสอบตรงกับ fishId ของ "fish" ที่กำลังตรวจสอบในลูป, กำหนดค่า found เป็น true.
										if (fish.fishId.equals(fishs.get(i).fishId)) {
											found = true;
										}
									}
									//ถ้าไม่พบอ็อบเจ็กต์ที่มี fishId เหมือนกับ "fish" ในลิสต์ "fishs"
									if (!found) {
										Fish fishAdded;
										if (fish.x + fish.size > fishPanel.getWidth() && fish.speedX > 0 ) {
											fishAdded = new Fish(fish.fishId, fish.clientId, fish.randpic, 0, fish.y);
										} else if (fish.x < 0 && fish.speedX < 0) {
											fishAdded = new Fish(fish.fishId, fish.clientId, fish.randpic, fishPanel.getWidth(),
													fish.y);
										}
										else {
											fishAdded = new Fish(fish.fishId, fish.clientId, fish.randpic, fish.x, fish.y);
										}
										
										fishAdded.speedX = fish.speedX;
										fishAdded.speedY = fish.speedY;
										fishs.add(fishAdded);
									}
								}
							}
						}
					} catch (IOException | ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
			}).start();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		fishPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
//					Fish fish = new Fish(id, e.getX(), e.getY());
//					fishs.add(fish);
					
					randpic = (int) (Math.random() * 3);
			        Fish randomFish = new Fish(id, randpic, e.getX(), e.getY());
			        fishs.add(randomFish);
				} else if (e.getButton() == MouseEvent.BUTTON3 && !fishs.isEmpty()) {
					try {
						Fish fish = new Fish(id, randpic, e.getX(), e.getY());
						fish.deleted = true;
						out.writeObject(fish);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}

		});
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Fish fishMoved = null;
				for (Fish fish : fishs) {
					fish.update(fishPanel);
					 if (fish.x + fish.size > fishPanel.getWidth() && fish.speedX > 0 || fish.x < 0 && fish.speedX < 0) {
						try {
							out.writeObject(fish);
							fishMoved = fish;
							out.flush();
							out.reset();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					 
				}//ลบปลาที่เคลื่อนที่ไปจากลิสต์ "fishs
				//ปลายังเคลื่อนที่อยู่
				if (fishMoved != null) {
					fishs.remove(fishMoved);
				}//ลบปลาที่ถูกลบออกจากลิสต์ "fishs"
				if (fishRemoved != null) {
					int index = -1;
					//ใช้ลูป for เพื่อหาปลาที่ถูกลบในลิสต์ fishs โดยเปรียบเทียบ fishId
					for (int i = 0; i < fishs.size(); i++) {
						//หากเจอปลาที่ถูกลบ กำหนดค่า fishRemoved เป็นปลานั้นและกำหนดค่า index เป็นตำแหน่งของปลานั้น.
						if (fishRemoved.fishId.equals(fishs.get(i).fishId)) {
							fishRemoved = fishs.get(i);
							index = i;
						}
					}
//					ถ้า index ไม่เป็นค่า -1:
					if (index != -1) {
						//ลบปลาที่ตำแหน่ง index ออกจากลิสต์ fishs
						fishs.remove(index);
					}
					//กำหนดค่า fishRemoved เป็นค่า null เพื่อรีเซ็ตค่า
					fishRemoved = null;
				}
				fishPanel.repaint();
			}
		});
		timer.start();

	}

	public static void main(String[] args) {
		FishClient client = new FishClient("localhost", 5000);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
