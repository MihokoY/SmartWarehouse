package s2.shipping;

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
import s2.shipping.ShippingGrpc.ShippingStub;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class GUIAppS2 {

	private static ShippingStub asyncStub;

	private ServiceInfo ShippingServiceInfo;
		
	private JFrame frame;
	private JTextField textNumber1;
	private JTextField textNumber2;
	private JTextField textNumber3;
	private JTextArea textResponse;
	private JTextArea textResponse2;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAppS2 window = new GUIAppS2();
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
	public GUIAppS2() {
		
		String shipping_service_type = "_ship._tcp.local.";
		discoverShippingService(shipping_service_type);
		
		String host = ShippingServiceInfo.getHostAddresses()[0];
		int port = ShippingServiceInfo.getPort();
		//String host = "localhost";
		//int port = 50052;
		
		ManagedChannel channel = ManagedChannelBuilder
								.forAddress(host, port)
								.usePlaintext()
								.build();

		//stub
		asyncStub = ShippingGrpc.newStub(channel);
		
		initialize();
	}

	/**
	 * Discover service
	 */
	private void discoverShippingService(String service_type) {
				
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Shipping Service resolved: " + event.getInfo());

					ShippingServiceInfo = event.getInfo();

					int port = ShippingServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + ShippingServiceInfo.getNiceTextString());
					System.out.println("\t host: " + ShippingServiceInfo.getHostAddresses()[0]);
									
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Shipping Service removed: " + event.getInfo());					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Shipping Service added: " + event.getInfo());				
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
		frame.setTitle("Client - Shipping Service Controller");
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		/* Method1 checkShippingQuantity */
		// Title
		JPanel name1 = new JPanel();
		frame.getContentPane().add(name1);
		name1.setLayout(new FlowLayout(FlowLayout.LEFT));
		name1.setPreferredSize(new Dimension(200, 10));
		JLabel methodName1 = new JLabel("1. Check Shipping Quantity");		
		name1.add(methodName1);
		
		// Request & Response
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setPreferredSize(new Dimension(550, 80));
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_1 = new JLabel("productNo");
		panel_1.add(lblNewLabel_1);
		
		textNumber1 = new JTextField();
		panel_1.add(textNumber1);
		textNumber1.setColumns(10);
		
		// Request2
		JLabel lblNewLabel_2 = new JLabel("shippingQty");
		panel_1.add(lblNewLabel_2);
		
		textNumber2 = new JTextField();
		panel_1.add(textNumber2);
		textNumber2.setColumns(10);
	
		// Response
		StreamObserver<ShippingQtyResponse> responseObserver = new StreamObserver<ShippingQtyResponse>() {

			@Override
			public void onNext(ShippingQtyResponse msg) {
				System.out.println("receiving message: " + msg.getMessage() );
				textResponse.append("receiving message: " + msg.getMessage());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed.");
				textResponse.append("\nstream is completed.");
			}

		};
		
		// Request
		StreamObserver<ShippingQtyRequest> requestObserver = asyncStub.checkShippingQuantity(responseObserver);
		
		// Send button to send the requests
		JButton btnCalculate = new JButton("Send");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String num1 = textNumber1.getText();
				int num2 = Integer.parseInt(textNumber2.getText());
								
				requestObserver.onNext(ShippingQtyRequest.newBuilder().setProductNo(num1).setShippingQty(num2).build());
				
				// reset the text field
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
		
		
		
		/* Method2 updateLocation */
		// Title
		JPanel name2 = new JPanel();
		frame.getContentPane().add(name2);
		name2.setLayout(new FlowLayout(FlowLayout.LEFT));
		name2.setPreferredSize(new Dimension(200, 10));
		JLabel methodName2 = new JLabel("2. Update Location");		
		name2.add(methodName2);
		
		// Request & Response
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(200, 80));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_3 = new JLabel("productIndivNo");
		panel_2.add(lblNewLabel_3);
		
		textNumber3 = new JTextField();
		panel_2.add(textNumber3);
		textNumber3.setColumns(10);
	
		// Response
		StreamObserver<UpdateLocResponse> responseObserver2 = new StreamObserver<UpdateLocResponse>() {

			int count =0 ;

			@Override
			public void onNext(UpdateLocResponse msg) {
				System.out.println("receiving removed location No: "+ msg.getLocationNo() );
				textResponse2.append("receiving removed location No: "+ msg.getLocationNo() + "\n");
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " location numbers");
				textResponse2.append("stream is completed.");
			}

		};
		
		// Request
		StreamObserver<UpdateLocRequest> requestObserver2 = asyncStub.updateLocation(responseObserver2);
		
		JButton btnCalculate2 = new JButton("Send");
		btnCalculate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String num3 = textNumber3.getText();
					
					requestObserver2.onNext(UpdateLocRequest.newBuilder().setProductIndivNo(num3).build());

				} catch (RuntimeException e1) {
					e1.printStackTrace();
				}

				// reset the text field
				textNumber3.setText("");
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
	}

}
