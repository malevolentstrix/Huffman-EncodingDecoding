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

	public HuffmanGUI(String[][] arr, String stringEncoded, String stringDecoded, double el, double sl, double r) {
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
				chooser.showOpenDialog(panel);
				File f = chooser.getSelectedFile();
				try (BufferedReader in = new BufferedReader(new FileReader(f))) {
					String line = in.readLine();
					while (line != null) {
						sb.append(line + "");
						line = in.readLine();

					}
					Desktop d = Desktop.getDesktop();
					Runtime.getRuntime().exec("dot -Tpng outputDescriptionFile.dot -o finalOutputHuffmanTree.png");
					String filePath = "finalOutputHuffmanTree.png";
					File file = new File(filePath);

					URI uri = file.toURI();
					try {
						d.browse(new URI(uri.toString()));
					} catch (URISyntaxException r) {
					}
				} catch (IOException e) {
					System.out.println(e);
				}
				String dotFile = "./outputDescriptionFile.dot";

				HuffManDisplay h = new HuffManDisplay(sb.toString(), dotFile);

				h.DisplayHuffman();

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HuffmanGUI frame = new HuffmanGUI(h.DataArray, h.encodedString, h.DecodedString,h.sizeAfterCoding, h.sizeForGivenString, h.reductionPercentage);
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

		JLabel lSize = new JLabel("SIZE");
		lSize.setForeground(Color.WHITE);
		lSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lSize.setBounds(33, 482, 64, 34);
		panel.add(lSize);

		JLabel lSizeOfOriginalString = new JLabel("Inputted String Size : ");
		lSizeOfOriginalString.setForeground(Color.WHITE);
		lSizeOfOriginalString.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lSizeOfOriginalString.setBounds(111, 514, 137, 28);
		panel.add(lSizeOfOriginalString);

		JLabel lSizeOfCodedString = new JLabel("Encoded String Size : ");
		lSizeOfCodedString.setForeground(Color.WHITE);
		lSizeOfCodedString.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lSizeOfCodedString.setBounds(111, 546, 140, 28);
		panel.add(lSizeOfCodedString);

		JLabel lReduction = new JLabel("Reduction %       :");
		lReduction.setForeground(Color.WHITE);
		lReduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lReduction.setBounds(111, 576, 1307, 28);
		panel.add(lReduction);

		JLabel lTreeGeneration = new JLabel("Tree Generation : ");
		lTreeGeneration.setForeground(Color.WHITE);
		lTreeGeneration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lTreeGeneration.setBounds(111, 607, 160, 28);
		panel.add(lTreeGeneration);

		JLabel lblNewLabel_1 = new JLabel("Huffman Encoding And Decoding");
		JLabel lblNewLabel_2 = new JLabel("A Project By Jithin John(AM.EN.U4AIE20135) and N Moneesh(AM.EN.U4AIE20150)");
		//lblNewLabel_1.setIcon(new ImageIcon("/home/Downloads/83648898.jpeg"));
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

		JLabel lReductionValue = new JLabel(Integer.toString((int) r) + " %");
		lReductionValue.setForeground(Color.WHITE);
		lReductionValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lReductionValue.setBounds(247, 576, 92, 28);
		panel.add(lReductionValue);

		JLabel lblDone = new JLabel("Done");
		lblDone.setForeground(Color.WHITE);
		lblDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDone.setBounds(247, 607, 92, 28);
		panel.add(lblDone);

		JLabel lEncodedSizeValue = new JLabel("Encoded String : " + stringEncoded);
		lEncodedSizeValue.setForeground(Color.WHITE);
		lEncodedSizeValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lEncodedSizeValue.setBounds(33, 415, 1370, 28);
		panel.add(lEncodedSizeValue);

		JLabel lOriginalSizeValue = new JLabel("Decoded String : " + stringDecoded);
		lOriginalSizeValue.setForeground(Color.WHITE);
		lOriginalSizeValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lOriginalSizeValue.setBounds(33, 436, 1370, 28);
		panel.add(lOriginalSizeValue);
		table.setVisible(true);
	}
}
