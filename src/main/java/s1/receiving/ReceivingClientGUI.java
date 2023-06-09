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
import io.grpc.stub.StreamObserver;
import s1.receiving.ReceivingGrpc.ReceivingBlockingStub;
import s1.receiving.ReceivingGrpc.ReceivingStub;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ReceivingClientGUI {

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

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceivingClientGUI window = new ReceivingClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application
	 */
	public ReceivingClientGUI() {
		
		// Discover service
		String receiving_service_type = "_receiving._tcp.local.";
		discoverReceivingService(receiving_service_type);
		
		// Get information of host and port
		String host = ReceivingServiceInfo.getHostAddresses()[0];
		int port = ReceivingServiceInfo.getPort();
		
		// Create channel
		ManagedChannel channel = ManagedChannelBuilder
								.forAddress(host, port)
								.usePlaintext()
								.build();

		//Stubs
		blockingStub = ReceivingGrpc.newBlockingStub(channel);
		asyncStub = ReceivingGrpc.newStub(channel);
		
		// Invoke initialize method to display the controller for the client
		initialize();
	}

	/**
	 * Discover service
	 */
	private void discoverReceivingService(String service_type) {
				
		try {
			// Create a jmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			// Add a service listener
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
			
			// Close jmDNS
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Create new JFrame with settings
		frame = new JFrame();
		frame.setTitle("Client - Receiving Service Controller");
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		/* Method1 checkReceivedQuantity */
		// Title
		JPanel name1 = new JPanel();
		frame.getContentPane().add(name1);
		name1.setLayout(new FlowLayout(FlowLayout.LEFT));
		name1.setPreferredSize(new Dimension(200, 10));
		JLabel methodName1 = new JLabel("1. Check Received Quantity");		
		name1.add(methodName1);
		
		// Request & Response
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setPreferredSize(new Dimension(550, 80));
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_1 = new JLabel("ProductNo");
		panel_1.add(lblNewLabel_1);
		
		textNumber1 = new JTextField();
		panel_1.add(textNumber1);
		textNumber1.setColumns(10);
		
		// Request2
		JLabel lblNewLabel_2 = new JLabel("ReceivedQty");
		panel_1.add(lblNewLabel_2);
		
		textNumber2 = new JTextField();
		panel_1.add(textNumber2);
		textNumber2.setColumns(10);
	
		// Handling the stream from server using onNext, onError, onCompleted
		StreamObserver<ReceivedQtyResponse> responseObserver = new StreamObserver<ReceivedQtyResponse>() {

			@Override
			public void onNext(ReceivedQtyResponse msg) {
				System.out.println("Receiving message: " + msg.getMessage() );
				// Display the message on the text area
				textResponse.append("Receiving message: " + msg.getMessage());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("Stream is completed.");
				// Display the message on the text area
				textResponse.append("\nStream is completed.");
			}

		};
		
		// Call the Remote checkReceivedQuantity method
		StreamObserver<ReceivedQtyRequest> requestObserver = asyncStub.checkReceivedQuantity(responseObserver);
		
		// Send button to send the requests
		JButton btnCalculate = new JButton("Send");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Get the input values
				String num1 = textNumber1.getText();
				int num2 = Integer.parseInt(textNumber2.getText());
				
				// Using onNext, sends a stream of messages
				requestObserver.onNext(ReceivedQtyRequest.newBuilder().setProductNo(num1).setReceivedQty(num2).build());
				
				// Reset the text field
				textNumber1.setText("");
				textNumber2.setText("");
			}
		});
		
		panel_1.add(btnCalculate);
		
		// Completed button to complete the requests
		JButton btnCalculate1_2 = new JButton("Completed");
		btnCalculate1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Mark the end of requests
				requestObserver.onCompleted();				
			}
		});
		panel_1.add(btnCalculate1_2);
		
		// Text area for display the response message
		textResponse = new JTextArea(3, 50);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		
		panel_1.add(scrollPane);
		
		
		
		/* Method2 setLocation */
		// Title
		JPanel name2 = new JPanel();
		frame.getContentPane().add(name2);
		name2.setLayout(new FlowLayout(FlowLayout.LEFT));
		name2.setPreferredSize(new Dimension(200, 10));
		JLabel methodName2 = new JLabel("2. Set Location");		
		name2.add(methodName2);
		
		// Request & Response
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(200, 80));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_3 = new JLabel("ProductNo");
		panel_2.add(lblNewLabel_3);
		
		textNumber3 = new JTextField();
		panel_2.add(textNumber3);
		textNumber3.setColumns(10);
		
		// Request2
		JLabel lblNewLabel_4 = new JLabel("ProductIndivNo");
		panel_2.add(lblNewLabel_4);
		
		textNumber4 = new JTextField();
		panel_2.add(textNumber4);
		textNumber4.setColumns(10);
	
		// Handling the stream from server using onNext, onError, onCompleted
		StreamObserver<SetLocResponse> responseObserver2 = new StreamObserver<SetLocResponse>() {

			int count =0 ;

			@Override
			public void onNext(SetLocResponse msg) {
				System.out.println("Receiving set LocationNo: "+ msg.getLocationNo() );
				// Display the message on the text area
				textResponse2.append("Receiving set LocationNo: "+ msg.getLocationNo() + "\n");
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("Stream is completed ... received "+ count+ " location numbers");
				// Display the message on the text area
				textResponse2.append("Stream is completed.");
			}

		};
		
		// Call the Remote setLocation method
		StreamObserver<SetLocRequest> requestObserver2 = asyncStub.setLocation(responseObserver2);
		
		// Send button to send the requests
		JButton btnCalculate2 = new JButton("Send");
		btnCalculate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Get the input values
					String num3 = textNumber3.getText();
					String num4 = textNumber4.getText();
					
					// Using onNext, sends a stream of messages
					requestObserver2.onNext(SetLocRequest.newBuilder().setProductNo(num3).setProductIndivNo(num4).build());

				} catch (RuntimeException e1) {
					e1.printStackTrace();
				}

				// Reset the text field
				textNumber3.setText("");
				textNumber4.setText("");
			}
		});
		panel_2.add(btnCalculate2);
		
		// Completed button to complete the requests
		JButton btnCalculate2_2 = new JButton("Completed");
		btnCalculate2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Mark the end of requests
				requestObserver2.onCompleted();			
			}
		});
		panel_2.add(btnCalculate2_2);
		
		// Text area for display the response message
		textResponse2 = new JTextArea(3, 50);
		textResponse2.setLineWrap(true);
		textResponse2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textResponse2);
		
		panel_2.add(scrollPane2);
		

		
		/* Method3 checkLocationAvailability */
		// Title
		JPanel name3 = new JPanel();
		frame.getContentPane().add(name3);
		name3.setLayout(new FlowLayout(FlowLayout.LEFT));
		name3.setPreferredSize(new Dimension(200, 10));
		JLabel methodName3 = new JLabel("3. Check Location Availability");		
		name3.add(methodName3);
		
		// Request & Response
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setPreferredSize(new Dimension(200, 80));
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request
		JLabel lblNewLabel_5 = new JLabel("LocationNo");
		panel_3.add(lblNewLabel_5);
		
		textNumber5 = new JTextField();
		panel_3.add(textNumber5);
		textNumber5.setColumns(10);
	
		// Check button to send the request and get the response
		JButton btnCalculate3 = new JButton("Check");
		btnCalculate3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Get the input value
				String num5 = textNumber5.getText();
				
				// Create a request message
				LocationAvailRequest req = LocationAvailRequest.newBuilder().setLocationNo(num5).build();

				// Calling a remote RPC method and get the response
				LocationAvailResponse response = blockingStub.checkLocationAvailability(req);
				
				System.out.println("Location No: " + req.getLocationNo() + ", Availability: " + response.getAvailNum());

				// Display the message on the text area
				textResponse3.append("Request LocationNo: "+ req.getLocationNo() + ", Availability: "+ response.getAvailNum() + "\n");
			}
		});
		panel_3.add(btnCalculate3);
		
		// Text area for display the response message
		textResponse3 = new JTextArea(3, 50);
		textResponse3.setLineWrap(true);
		textResponse3.setWrapStyleWord(true);
		
		JScrollPane scrollPane3 = new JScrollPane(textResponse3);
		
		panel_3.add(scrollPane3);
	}

}
