import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class HuffmanGUI extends JFrame {
	StringBuilder sb = new StringBuilder();
	private JPanel contentPane;

	public HuffmanGUI(String[][] arr, String EncodedStr, String DecodedStr, double el, double sl, double r,
			boolean ShowInConsole) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 689);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblChooseInputFile = new JLabel("Select the Input File(.txt)");
		lblChooseInputFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseInputFile.setForeground(Color.WHITE);
		lblChooseInputFile.setBounds(134, 150, 200, 34);
		panel.add(lblChooseInputFile);

		JButton btnNewButton = new JButton("Open");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				try (BufferedReader in = new BufferedReader(new FileReader(f))) {
					String line = in.readLine();
					while (line != null) {
						sb.append(line + "\n");
						line = in.readLine();

					}
					Desktop d = Desktop.getDesktop();
					Runtime.getRuntime().exec("dot -Tpng ../files/test.dot -o ../files/k.png");
					String filePath = "../files/k.png";
					File file = new File(filePath);

					URI uri = file.toURI();
					System.out.println(uri.toString());
					try {
						d.browse(new URI(uri.toString()));
					} catch (URISyntaxException r) {
					}
				} catch (IOException e) {
					System.out.println(e);
				}
				String dotFile = "../files/test.dot";

				System.out.print("* Loading the file...");
				System.out.println("DONE");

				HuffManDisplay h = new HuffManDisplay(sb.toString(), dotFile);

				boolean isThisTestData = false;
				h.DisplayHuffman(ShowInConsole, isThisTestData);

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString,
									h.EncodedCost, h.OrgCost, h.Percent, ShowInConsole);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(281, 200, 137, 34);
		panel.add(btnNewButton);

		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setForeground(Color.WHITE);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOutput.setBounds(33, 260, 64, 34);
		panel.add(lblOutput);

		JLabel lblInput = new JLabel("INPUT");
		lblInput.setForeground(Color.WHITE);
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInput.setBounds(33, 110, 64, 34);
		panel.add(lblInput);

		String[] names = { "Letter", "Frequency", "Code" };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 310, 347, 95);
		panel.add(scrollPane);
		JTable table = new JTable(arr, names);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setBackground(Color.ORANGE);
		table.setFillsViewportHeight(true);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 470, 1500, 16);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 250, 1500, 16);
		panel.add(separator_1);

		JLabel lblCost = new JLabel("COST");
		lblCost.setForeground(Color.WHITE);
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(33, 482, 64, 34);
		panel.add(lblCost);

		JLabel lblOriginalStringCost = new JLabel("Original String Cost : ");
		lblOriginalStringCost.setForeground(Color.WHITE);
		lblOriginalStringCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOriginalStringCost.setBounds(111, 514, 137, 28);
		panel.add(lblOriginalStringCost);

		JLabel lblEncodeStringCost = new JLabel("Encoded String Cost : ");
		lblEncodeStringCost.setForeground(Color.WHITE);
		lblEncodeStringCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEncodeStringCost.setBounds(111, 546, 140, 28);
		panel.add(lblEncodeStringCost);

		JLabel lblPercentage = new JLabel("Reduction %       :");
		lblPercentage.setForeground(Color.WHITE);
		lblPercentage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPercentage.setBounds(111, 576, 1307, 28);
		panel.add(lblPercentage);

		JLabel lblGeneratingGraphvizFile = new JLabel("Tree Generation : ");
		lblGeneratingGraphvizFile.setForeground(Color.WHITE);
		lblGeneratingGraphvizFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGeneratingGraphvizFile.setBounds(111, 607, 160, 28);
		panel.add(lblGeneratingGraphvizFile);

		JLabel lblNewLabel_1 = new JLabel("Huffman Encoding And Decoding");
		JLabel lblNewLabel_2 = new JLabel("A Project By Jithin John(AM.EN.U4AIE20135) and N Moneesh(AM.EN.U4AIE20150)");
		lblNewLabel_1.setIcon(new ImageIcon("/home/Downloads/83648898.jpeg"));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(430, 11, 1000, 70);
		lblNewLabel_2.setIcon(new ImageIcon("../"));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(250, 650, 1000, 70);
		panel.add(lblNewLabel_1);
		panel.add(lblNewLabel_2);

		JLabel lblVariable = new JLabel(Integer.toString((int) sl));
		lblVariable.setForeground(Color.WHITE);
		lblVariable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVariable.setBounds(247, 514, 137, 28);
		panel.add(lblVariable);

		JLabel lblVariablee = new JLabel(Integer.toString((int) el));
		lblVariablee.setForeground(Color.WHITE);
		lblVariablee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVariablee.setBounds(247, 546, 92, 28);
		panel.add(lblVariablee);

		JLabel lblpercentage = new JLabel(Integer.toString((int) r) + " %");
		lblpercentage.setForeground(Color.WHITE);
		lblpercentage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblpercentage.setBounds(247, 576, 92, 28);
		panel.add(lblpercentage);

		JLabel lblDone = new JLabel("Done");
		lblDone.setForeground(Color.WHITE);
		lblDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDone.setBounds(247, 607, 92, 28);
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
		table.setVisible(true);
	}
}
