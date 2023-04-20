package s1.receiving;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import s1.receiving.ReceivingGrpc.ReceivingBlockingStub;
import s1.receiving.ReceivingGrpc.ReceivingStub;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class GUIAppS1 {

	private static ReceivingBlockingStub blockingStub;
	private static ReceivingStub asyncStub;

	private ServiceInfo ReceivingServiceInfo;
		
	private JFrame frame;
	private JTextField textNumber1;
	private JTextField textNumber2;
	private JTextField textNumber3;
	private JTextField textNumber4;
	private JTextField textNumber5;
	private JTextArea textResponse;
	private JTextArea textResponse2;
	private JTextArea textResponse3;

	//Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAppS1 window = new GUIAppS1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	//Create the application
	public GUIAppS1() {
		
		String receiving_service_type = "_receiving._tcp.local.";
		discoverReceivingService(receiving_service_type);
		
		String host = ReceivingServiceInfo.getHostAddresses()[0];
		int port = ReceivingServiceInfo.getPort();
		//String host = "localhost";
		//int port = 50051;
		
		ManagedChannel channel = ManagedChannelBuilder
								.forAddress(host, port)
								.usePlaintext()
								.build();

		//stubs
		blockingStub = ReceivingGrpc.newBlockingStub(channel);
		asyncStub = ReceivingGrpc.newStub(channel);
		
		initialize();
	}

	
	
	private void discoverReceivingService(String service_type) {
				
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Receiving Service resolved: " + event.getInfo());

					ReceivingServiceInfo = event.getInfo();

					int port = ReceivingServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + ReceivingServiceInfo.getNiceTextString());
					System.out.println("\t host: " + ReceivingServiceInfo.getHostAddresses()[0]);
									
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Receiving Service removed: " + event.getInfo());					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Receiving Service added: " + event.getInfo());				
				}
			});
			
			// Wait a bit
			Thread.sleep(2000);
			
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Receiving Service Controller");
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		/* Method 1 */
		// title
		JPanel name1 = new JPanel();
		frame.getContentPane().add(name1);
		name1.setLayout(new FlowLayout(FlowLayout.LEFT));
		name1.setPreferredSize(new Dimension(200, 10));
		JLabel methodName1 = new JLabel("1. Check Received Quantity");		
		name1.add(methodName1);
		
		// request & response
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setPreferredSize(new Dimension(200, 80));
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("productNo");
		panel_1.add(lblNewLabel_1);
		
		textNumber1 = new JTextField();
		panel_1.add(textNumber1);
		textNumber1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("receivedQty");
		panel_1.add(lblNewLabel_2);
		
		textNumber2 = new JTextField();
		panel_1.add(textNumber2);
		textNumber2.setColumns(10);
	
		
		JButton btnCalculate = new JButton("Send");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String num1 = textNumber1.getText();
				int num2 = Integer.parseInt(textNumber2.getText());
				
				ReceivedQtyRequest req = ReceivedQtyRequest.newBuilder().setProductNo(num1).setReceivedQty(num2).build();

				//ReceivedQtyResponse response = blockingStub.checkReceivedQuantity(req);
				
				
				//textResponse.append("Request Location No: "+ req.getLocationNo() + "\nAvailability: "+ response.getAvailNum() + "\n");
				
				//System.out.println("res: " + response.getResult() + " mes: " + response.getMessage());

			}
		});
		panel_1.add(btnCalculate);
		
		JButton btnCalculate1_2 = new JButton("Completed");
		btnCalculate1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String num1 = textNumber1.getText();
				int num2 = Integer.parseInt(textNumber2.getText());
				
				ReceivedQtyRequest req = ReceivedQtyRequest.newBuilder().setProductNo(num1).setReceivedQty(num2).build();

				//ReceivedQtyResponse response = blockingStub.checkReceivedQuantity(req);
				
				
				//textResponse.append("Request Location No: "+ req.getLocationNo() + "\nAvailability: "+ response.getAvailNum() + "\n");
				
				//System.out.println("res: " + response.getResult() + " mes: " + response.getMessage());

			}
		});
		panel_1.add(btnCalculate1_2);
		
		textResponse = new JTextArea(3, 50);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_1.add(scrollPane);
		
		
		
		/* Method 2 */
		// title
		JPanel name2 = new JPanel();
		frame.getContentPane().add(name2);
		name2.setLayout(new FlowLayout(FlowLayout.LEFT));
		name2.setPreferredSize(new Dimension(200, 10));
		JLabel methodName2 = new JLabel("2. Set Location");		
		name2.add(methodName2);
		
		// request & response
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(200, 80));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("locationNo");
		panel_2.add(lblNewLabel_3);
		
		textNumber1 = new JTextField();
		panel_2.add(textNumber1);
		textNumber1.setColumns(10);
		
		//JLabel lblNewLabel_2 = new JLabel("Number 2");
		//panel_service_1.add(lblNewLabel_2);
		
		//textNumber2 = new JTextField();
		//panel_service_1.add(textNumber2);
		//textNumber2.setColumns(10);
	
		
		JButton btnCalculate2 = new JButton("Send");
		btnCalculate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String num1 = textNumber1.getText();
				//int num2 = Integer.parseInt(textNumber2.getText());

				//int index = comboOperation.getSelectedIndex();
				//Operation operation = Operation.forNumber(index);
				
				LocationAvailRequest req = LocationAvailRequest.newBuilder().setLocationNo(num1).build();

				LocationAvailResponse response = blockingStub.checkLocationAvailability(req);

				textResponse.append("Request Location No: "+ req.getLocationNo() + "\nAvailability: "+ response.getAvailNum() + "\n");
				
				//System.out.println("res: " + response.getResult() + " mes: " + response.getMessage());

			}
		});
		panel_2.add(btnCalculate2);
		
		textResponse2 = new JTextArea(3, 50);
		textResponse2.setLineWrap(true);
		textResponse2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textResponse2);
		
		panel_2.add(scrollPane2);
		

		
		/* Method 3 */
		// title
		JPanel name3 = new JPanel();
		frame.getContentPane().add(name3);
		name3.setLayout(new FlowLayout(FlowLayout.LEFT));
		name3.setPreferredSize(new Dimension(200, 10));
		JLabel methodName3 = new JLabel("3. Check Location Availability");		
		name3.add(methodName3);
		
		// request & response
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setPreferredSize(new Dimension(200, 80));
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_5 = new JLabel("locationNo");
		panel_3.add(lblNewLabel_5);
		
		textNumber5 = new JTextField();
		panel_3.add(textNumber5);
		textNumber5.setColumns(10);
	
		
		JButton btnCalculate3 = new JButton("Check");
		btnCalculate3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String num5 = textNumber5.getText();
				
				LocationAvailRequest req = LocationAvailRequest.newBuilder().setLocationNo(num5).build();

				LocationAvailResponse response = blockingStub.checkLocationAvailability(req);

				textResponse3.append("Request Location No: "+ req.getLocationNo() + "\nAvailability: "+ response.getAvailNum() + "\n");
				
				System.out.println("Location No: " + req.getLocationNo() + ", Availability: " + response.getAvailNum());

			}
		});
		panel_3.add(btnCalculate3);
		
		textResponse3 = new JTextArea(3, 50);
		textResponse3.setLineWrap(true);
		textResponse3.setWrapStyleWord(true);
		
		JScrollPane scrollPane3 = new JScrollPane(textResponse3);
		
		panel_3.add(scrollPane3);
	}

}
