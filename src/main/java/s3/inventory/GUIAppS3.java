package s3.inventory;

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
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import s3.inventory.InventoryGrpc.InventoryBlockingStub;
import s3.inventory.InventoryGrpc.InventoryStub;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class GUIAppS3 {

	private static InventoryBlockingStub blockingStub;
	private static InventoryStub asyncStub;

	private ServiceInfo InventoryServiceInfo;
		
	private JFrame frame;
	private JTextField textNumber1;
	private JTextField textNumber2;
	private JTextField textNumber3;
	private JTextField textNumber4;
	private JTextField textNumber5;
	private JTextField textNumber6;
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
					GUIAppS3 window = new GUIAppS3();
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
	public GUIAppS3() {
		
		String inventory_service_type = "_inventory._tcp.local.";
		discoverInventoryService(inventory_service_type);
		
		String host = InventoryServiceInfo.getHostAddresses()[0];
		int port = InventoryServiceInfo.getPort();
		//String host = "localhost";
		//int port = 50051;
		
		ManagedChannel channel = ManagedChannelBuilder
								.forAddress(host, port)
								.usePlaintext()
								.build();

		//stubs
		blockingStub = InventoryGrpc.newBlockingStub(channel);
		asyncStub = InventoryGrpc.newStub(channel);
		
		initialize();
	}

	/**
	 * Discover service
	 */
	private void discoverInventoryService(String service_type) {
				
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Inventory Service resolved: " + event.getInfo());

					InventoryServiceInfo = event.getInfo();

					int port = InventoryServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + InventoryServiceInfo.getNiceTextString());
					System.out.println("\t host: " + InventoryServiceInfo.getHostAddresses()[0]);
									
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Inventory Service removed: " + event.getInfo());					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Inventory Service added: " + event.getInfo());				
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
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Inventory Service Controller");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		/* Method1 checkInventoryQuantity */
		// Title
		JPanel name1 = new JPanel();
		frame.getContentPane().add(name1);
		name1.setLayout(new FlowLayout(FlowLayout.LEFT));
		name1.setPreferredSize(new Dimension(200, 10));
		JLabel methodName1 = new JLabel("1. Check Inventory Quantity");		
		name1.add(methodName1);
		
		// Request & Response
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setPreferredSize(new Dimension(550, 80));
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_1 = new JLabel("totalQty");
		panel_1.add(lblNewLabel_1);
		
		textNumber1 = new JTextField();
		panel_1.add(textNumber1);
		textNumber1.setColumns(10);
		
		// Send button to send the requests
		JButton btnCalculate = new JButton("Send");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num1 = Integer.parseInt(textNumber1.getText());
				
				// Request
				InventoryQtyRequest request = InventoryQtyRequest.newBuilder().setTotalQty(num1).build();

				try {
					// Response
					Iterator<InventoryQtyResponse> responces = blockingStub.checkInventoryQuantity(request);

					// Client keeps a check on the next message in stream.
					while(responces.hasNext()) {
						InventoryQtyResponse temp = responces.next();
						System.out.println("receiving product No: " + temp.getProductNo());
						textResponse.append("receiving product No: " + temp.getProductNo() + "\n");
					}
					System.out.println("stream is completed.");
					textResponse.append("stream is completed.");

				} catch (StatusRuntimeException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		panel_1.add(btnCalculate);
		
		// Text area for display the response message
		textResponse = new JTextArea(5, 60);
		textResponse .setLineWrap(true);
		textResponse.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textResponse);
		
		panel_1.add(scrollPane);
		
		
		
		/* Method2 order */
		// Title
		JPanel name2 = new JPanel();
		frame.getContentPane().add(name2);
		name2.setLayout(new FlowLayout(FlowLayout.LEFT));
		name2.setPreferredSize(new Dimension(200, 10));
		JLabel methodName2 = new JLabel("2. Order");		
		name2.add(methodName2);
		
		// Request & Response
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(200, 80));
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_2 = new JLabel("productNo");
		panel_2.add(lblNewLabel_2);
		
		textNumber2 = new JTextField();
		panel_2.add(textNumber2);
		textNumber2.setColumns(10);
		
		// Request2
		JLabel lblNewLabel_3 = new JLabel("orderQty");
		panel_2.add(lblNewLabel_3);
		
		textNumber3 = new JTextField();
		panel_2.add(textNumber3);
		textNumber3.setColumns(10);
	
		// Response
		StreamObserver<OrderResponse> responseObserver2 = new StreamObserver<OrderResponse>() {

			int count =0 ;

			@Override
			public void onNext(OrderResponse msg) {
				System.out.println("receiving product No: " + msg.getProductNo() + ", orderQty: "+ msg.getOrderQty()+ ", afterQty: "+ msg.getAfterQty() );
				textResponse2.append("receiving product No: " + msg.getProductNo() + ", orderQty: "+ msg.getOrderQty()+ ", afterQty: "+ msg.getAfterQty()  + "\n");
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " product Nos");
				textResponse2.append("stream is completed.");
			}

		};
		
		// Request
		StreamObserver<OrderRequest> requestObserver2 = asyncStub.order(responseObserver2);
		
		JButton btnCalculate2 = new JButton("Send");
		btnCalculate2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String num2 = textNumber2.getText();
					int num3 = Integer.parseInt(textNumber3.getText());
					
					requestObserver2.onNext(OrderRequest.newBuilder().setProductNo(num2).setOrderQty(num3).build());

				} catch (RuntimeException e1) {
					e1.printStackTrace();
				}

				// reset the text field
				textNumber2.setText("");
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
		textResponse2 = new JTextArea(5, 60);
		textResponse2.setLineWrap(true);
		textResponse2.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textResponse2);
		
		panel_2.add(scrollPane2);
		

		
		/* Method3 orderHistory */
		// Title
		JPanel name3 = new JPanel();
		frame.getContentPane().add(name3);
		name3.setLayout(new FlowLayout(FlowLayout.LEFT));
		name3.setPreferredSize(new Dimension(200, 10));
		JLabel methodName3 = new JLabel("3. Order History");		
		name3.add(methodName3);
		
		// Request & Response
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setPreferredSize(new Dimension(200, 80));
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Request1
		JLabel lblNewLabel_4 = new JLabel("startDate");
		panel_3.add(lblNewLabel_4);
		
		textNumber4 = new JTextField();
		panel_3.add(textNumber4);
		textNumber4.setColumns(10);
		
		// Request2
		JLabel lblNewLabel_5 = new JLabel("endDate");
		panel_3.add(lblNewLabel_5);
		
		textNumber5 = new JTextField();
		panel_3.add(textNumber5);
		textNumber5.setColumns(10);
		
		// Request3
		JLabel lblNewLabel_6 = new JLabel("productNo");
		panel_3.add(lblNewLabel_6);
		
		textNumber6 = new JTextField();
		panel_3.add(textNumber6);
		textNumber6.setColumns(10);
	
		// Check button to send the request and get the response
		JButton btnCalculate3 = new JButton("Check");
		btnCalculate3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String num4 = textNumber4.getText();
				String num5 = textNumber5.getText();
				String num6 = textNumber6.getText();
				
				// Request
				OrderHisRequest req = OrderHisRequest.newBuilder().setStartDate(num4).setEndDate(num5).setProductNo(num6).build();

				// Response
				OrderHisResponse response = blockingStub.orderHistory(req);

				textResponse3.append("Request productNo: " + req.getProductNo() + ", duration: " + req.getStartDate() + " - "  
									+ req.getEndDate() + ", Response totalQty: " + response.getTotalQty() + "\n");
				
				System.out.println("productNo: " + req.getProductNo() + ", Duration: " + req.getStartDate() + " - "
						 			+ req.getEndDate() + ", totalQty: " + response.getTotalQty());

			}
		});
		panel_3.add(btnCalculate3);
		
		// Text area for display the response message
		textResponse3 = new JTextArea(3, 60);
		textResponse3.setLineWrap(true);
		textResponse3.setWrapStyleWord(true);
		
		JScrollPane scrollPane3 = new JScrollPane(textResponse3);
		
		panel_3.add(scrollPane3);
	}

}
