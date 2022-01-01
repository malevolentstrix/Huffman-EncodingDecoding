import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
/*
 * this is the Graphical user interface class of huffman.
 */
public class HuffmanGUI extends JFrame {
	StringBuilder sb = new StringBuilder();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param arr 
	 * @param r 
	 * @param sl 
	 * @param el 
	 */
	public HuffmanGUI(String[][] arr, String EncodedStr, String DecodedStr, double el, double sl, double r, boolean ShowInConsole) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 689);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Write a Text Here");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(111, 74, 137, 34);
		panel.add(lblNewLabel);	
		JTextArea textArea = new JTextArea();
		textArea.setBounds(258, 92, 327, 95);

		panel.add(textArea);
	
		
		JLabel lblChooseInputFile = new JLabel("Choose  Input File");
		lblChooseInputFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseInputFile.setForeground(Color.WHITE);
		lblChooseInputFile.setBounds(134, 221, 137, 34);
		panel.add(lblChooseInputFile);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("search.png")));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JFileChooser chooser=new JFileChooser();
			        chooser.showOpenDialog(null);
			        File f=chooser.getSelectedFile();
			    	try (BufferedReader in = new BufferedReader(new FileReader(f))){
						String line = in.readLine();
						while (line != null){
							sb.append(line + "\n");
							line = in.readLine();
				
							
						}
					}
					catch (IOException e){
						System.out.println(e);
					}
					  String dotFile = "files/test.dot";
					  
					  System.out.print("* Loading the file...");
					  System.out.println("DONE");
					  
					  HuffManDisplay h = new HuffManDisplay(sb.toString(), dotFile);
				
					  	boolean isThisTestData = false;
						h.DisplayHuffman(ShowInConsole,isThisTestData);

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString,h.EncodedCost,h.OrgCost,h.Percent,ShowInConsole);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
			}
		});
		btnNewButton.setBounds(281, 238, 137, 34);
		panel.add(btnNewButton);
		
		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setForeground(Color.WHITE);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOutput.setBounds(33, 283, 64, 34);
		panel.add(lblOutput);
		
		String[] names = {"Letter", "Frequency", "Code"};


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 310, 347, 95);
		panel.add(scrollPane);
		JTable table = new JTable(arr,names);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setBackground(Color.ORANGE);
		 table.setFillsViewportHeight(true);
		 
		 JSeparator separator = new JSeparator();
		 separator.setBounds(10, 462, 771, 16);
		 panel.add(separator);
		 
		 JSeparator separator_1 = new JSeparator();
		 separator_1.setBounds(10, 283, 771, 16);
		 panel.add(separator_1);
		 
		 JLabel lblCost = new JLabel("COST");
		 lblCost.setForeground(Color.WHITE);
		 lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 lblCost.setBounds(33, 462, 64, 34);
		 panel.add(lblCost);
		 
		 JLabel lblOriginalStringCost = new JLabel("Original String Cost : ");
		 lblOriginalStringCost.setForeground(Color.WHITE);
		 lblOriginalStringCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblOriginalStringCost.setBounds(111, 494, 137, 28);
		 panel.add(lblOriginalStringCost);
		 
		 JLabel lblEncodeStringCost = new JLabel("Encode String Cost: ");
		 lblEncodeStringCost.setForeground(Color.WHITE);
		 lblEncodeStringCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblEncodeStringCost.setBounds(111, 526, 137, 28);
		 panel.add(lblEncodeStringCost);
		 
		 JLabel lblPercentage = new JLabel("reducation %:");
		 lblPercentage.setForeground(Color.WHITE);
		 lblPercentage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblPercentage.setBounds(111, 556, 1307, 28);
		 panel.add(lblPercentage);
		 
		 JLabel lblGeneratingGraphvizFile = new JLabel("Generating Graphviz file : ");
		 lblGeneratingGraphvizFile.setForeground(Color.WHITE);
		 lblGeneratingGraphvizFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblGeneratingGraphvizFile.setBounds(111, 587, 160, 28);
		 panel.add(lblGeneratingGraphvizFile);
		 
		 JLabel lblNewLabel_1 = new JLabel("HuffMan Ecoding ");
		 lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DELL\\Desktop\\EnodeHome.png"));
		 lblNewLabel_1.setForeground(Color.WHITE);
		 lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		 lblNewLabel_1.setBounds(323, 11, 224, 70);
		 panel.add(lblNewLabel_1);
		 
		 JLabel lblVariable = new JLabel(Integer.toString((int) sl));
		 lblVariable.setForeground(Color.WHITE);
		 lblVariable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblVariable.setBounds(247, 494, 137, 28);
		 panel.add(lblVariable);
		 
		 JLabel lblVariablee = new JLabel(Integer.toString((int) el));
		 lblVariablee.setForeground(Color.WHITE);
		 lblVariablee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblVariablee.setBounds(247, 526, 92, 28);
		 panel.add(lblVariablee);
		 
		 JLabel lblpercentage = new JLabel(Integer.toString((int)r) + " %");
		 lblpercentage.setForeground(Color.WHITE);
		 lblpercentage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblpercentage.setBounds(247, 556, 92, 28);
		 panel.add(lblpercentage);
		 
		 JLabel lblDone = new JLabel("Done");
		 lblDone.setForeground(Color.WHITE);
		 lblDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblDone.setBounds(258, 587, 92, 28);
		 panel.add(lblDone);
		 
		 JLabel lblEncodedString = new JLabel("Encoded String : " + EncodedStr);
		 lblEncodedString.setForeground(Color.WHITE);
		 lblEncodedString.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblEncodedString.setBounds(33, 415, 1370, 28);
		 panel.add(lblEncodedString);
		 
		 JLabel lblDecodedString = new JLabel("Decoded String : " + DecodedStr);
		 lblDecodedString.setForeground(Color.WHITE);
		 lblDecodedString.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 lblDecodedString.setBounds(33, 436, 1370, 28);
		 panel.add(lblDecodedString);
		 
		 JLabel lblOr = new JLabel("OR");
		 lblOr.setForeground(Color.WHITE);
		 lblOr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 lblOr.setBounds(365, 193, 64, 34);
		 panel.add(lblOr);
		 JButton btnShowResult = new JButton("Show Result");	
		 	
		 // Action of 'Show Result' Button	
		 btnShowResult.addActionListener(new ActionListener() {	
		 	public void actionPerformed(ActionEvent arg0) {	
		 		 String dotFile = "files/test.dot";
				  
				  System.out.print("* Loading the file...");
				  System.out.println("DONE");
				  
				  HuffManDisplay h = new HuffManDisplay(textArea.getText(), dotFile);
			boolean isThisTestData = false;
			h.DisplayHuffman(ShowInConsole,isThisTestData);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString,h.EncodedCost,h.OrgCost,h.Percent,ShowInConsole);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		}
		 		
		 });	
		 btnShowResult.setFont(new Font("Tahoma", Font.PLAIN, 17));	
		 btnShowResult.setBounds(616, 211, 147, 59);	
		 panel.add(btnShowResult);
		 table.setVisible(true);
	}
}
