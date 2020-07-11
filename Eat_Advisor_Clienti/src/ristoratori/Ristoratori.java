package ristoratori;

import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class Ristoratori {
	
	private JFrame frmRistorante;
	private JTextField tf_nome;
	private JComboBox cb_via;
	private JTextField tf_indirizzo;
	private JTextField tf_num_civ;
	private JTextField tf_citta;
	private JComboBox cb_sigla_prov;
	private JTextField tf_cap;
	private JTextField tf_cell;
	private JTextField tf_sito;
	private JComboBox cb_tipologia;
	static Frame message = new Frame();
	static File ristoranti_file;
	private JButton btnClean;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ristoratori window = new Ristoratori();
					window.frmRistorante.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		File findme = new File("Findme.txt");
		String data_file_path = findme.getAbsolutePath();
		String ristoranti_file_path = data_file_path.replaceAll("Findme.txt", "data\\\\EatAdvisor.data.txt");
		System.out.println(ristoranti_file_path);
		ristoranti_file = new File(ristoranti_file_path);
		//JOptionPane.showMessageDialog(message,ristoranti_file_path);
	}

	/**
	 * Create the application.
	 */
	public Ristoratori() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmRistorante = new JFrame();
		frmRistorante.setTitle("Ristorante");
		frmRistorante.getContentPane().setBackground(SystemColor.info);
		frmRistorante.setBounds(100, 100, 450, 329);
		frmRistorante.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRistorante.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 42, 46, 14);
		frmRistorante.getContentPane().add(lblNome);
		
		JLabel lblCell = new JLabel("Cell :");
		lblCell.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCell.setBounds(226, 42, 46, 14);
		frmRistorante.getContentPane().add(lblCell);
		
		JLabel lblSito = new JLabel("Sito :");
		lblSito.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSito.setBounds(10, 81, 46, 14);
		frmRistorante.getContentPane().add(lblSito);
		
		JLabel lblTipologia = new JLabel("Tipologia :");
		lblTipologia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipologia.setBounds(206, 81, 80, 14);
		frmRistorante.getContentPane().add(lblTipologia);
		
		cb_tipologia = new JComboBox();
		cb_tipologia.setModel(new DefaultComboBoxModel(new String[] {"", "Italiano", "Etnico", "Fusion"}));
		cb_tipologia.setSelectedIndex(0);
		cb_tipologia.setBounds(296, 77, 86, 22);
		frmRistorante.getContentPane().add(cb_tipologia);
		
		tf_cell = new JTextField();
		tf_cell.setBounds(296, 39, 86, 20);
		frmRistorante.getContentPane().add(tf_cell);
		tf_cell.setColumns(10);
		
		tf_sito = new JTextField();
		tf_sito.setBounds(66, 78, 86, 20);
		frmRistorante.getContentPane().add(tf_sito);
		tf_sito.setColumns(10);
		
		tf_nome = new JTextField();
		tf_nome.setBounds(66, 39, 86, 20);
		frmRistorante.getContentPane().add(tf_nome);
		tf_nome.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Indirizzo :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 132, 60, 14);
		frmRistorante.getContentPane().add(lblNewLabel_3);
		
		cb_via = new JComboBox();
		cb_via.setModel(new DefaultComboBoxModel(new String[] {"", "Via", "Piazza"}));
		cb_via.setSelectedIndex(0);
		cb_via.setBounds(80, 129, 68, 22);
		frmRistorante.getContentPane().add(cb_via);
		
		tf_indirizzo = new JTextField();
		tf_indirizzo.setBounds(147, 130, 86, 20);
		frmRistorante.getContentPane().add(tf_indirizzo);
		tf_indirizzo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("N\u00B0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(240, 132, 32, 14);
		frmRistorante.getContentPane().add(lblNewLabel);
		
		tf_num_civ = new JTextField();
		tf_num_civ.setBounds(261, 129, 46, 20);
		frmRistorante.getContentPane().add(tf_num_civ);
		tf_num_civ.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Citt\u00E0 :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 180, 46, 14);
		frmRistorante.getContentPane().add(lblNewLabel_1);
		
		tf_citta = new JTextField();
		tf_citta.setBounds(66, 177, 86, 20);
		frmRistorante.getContentPane().add(tf_citta);
		tf_citta.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CAP : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(317, 132, 39, 14);
		frmRistorante.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Sigla Provincia :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(206, 180, 111, 14);
		frmRistorante.getContentPane().add(lblNewLabel_4);
		
		cb_sigla_prov = new JComboBox();
		cb_sigla_prov.setModel(new DefaultComboBoxModel(new String[] {"", "AG", "AL", "AN", "AO", "AQ", "AR", "AP", "AT", "AV", "BA", "BT", "BL", "BN", "BG",
				"BI", "BO", "BZ", "BS", "BR", "CA", "CL", "CB", "CI", "CE", "CT", "CZ", "CH", "CO", "CS", "CR", "KR", "CN", "EN", "FM", "FE", "FI", "FG",
				"FC", "FR", "GE", "GO", "GR", "IM", "IS", "SP", "LT", "LE", "LC", "LI", "LO", "LU", "MC", "MN", "MS", "MT", "VS", "ME", "MI", "MO", "MB",
				"NA", "NO", "NU", "OG", "OT", "OR", "PD", "PA", "PR", "PV", "PG", "PU", "PE", "PC", "PI", "PT", "PN", "PZ", "PO", "RG", "RA", "RC", "RE", 
				"RI", "RN", "Roma", "RO", "SA", "SS", "SV", "SI", "SR", "SO", "TA", "TE", "TR", "TO", "TP", "TN", "TV", "TS", "UD", "VA", "VE", "VB", "VC",
				"VR", "VV", "VI", "VT"}));
		cb_sigla_prov.setBounds(317, 176, 60, 22);
		frmRistorante.getContentPane().add(cb_sigla_prov);
		
		tf_cap = new JTextField();
		tf_cap.setBounds(359, 129, 60, 20);
		frmRistorante.getContentPane().add(tf_cap);
		tf_cap.setColumns(10);
		
		JButton btnIscrivi = new JButton("Iscrivi");
		btnIscrivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				check_data();
				
				
			}
		});
		btnIscrivi.setBounds(159, 253, 89, 23);
		frmRistorante.getContentPane().add(btnIscrivi);
		
		btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tf_nome.setText("");
				tf_indirizzo.setText("");
				tf_num_civ.setText("");
				tf_citta.setText("");
				tf_cap.setText("");
				tf_cell.setText("");
				tf_sito.setText("");
				cb_via.setSelectedIndex(0);
				cb_sigla_prov.setSelectedIndex(0);
				cb_tipologia.setSelectedIndex(0);
				
			}
		});
		btnClean.setBounds(296, 253, 89, 23);
		frmRistorante.getContentPane().add(btnClean);
	}
	

	public void check_data() {
		Boolean check = true;
		String regex = "\\d+";
		
		String nome = (tf_nome.getText().isEmpty()) ? "!" : tf_nome.getText();
		
		String via =  (cb_via.getSelectedItem()=="") ? "!" : (String)cb_via.getSelectedItem();
		String tmp_indirizzo = (tf_indirizzo.getText().isEmpty()) ? "!" : tf_indirizzo.getText();
		String n_civ = ((tf_num_civ.getText().isEmpty())||(!tf_num_civ.getText().matches(regex))) ? "!" : tf_num_civ.getText();
		String citta = (tf_citta.getText().isEmpty()) ? "!" : tf_citta.getText();
		String sigla_prov =  (cb_sigla_prov.getSelectedItem()=="") ? "!" : (String)cb_sigla_prov.getSelectedItem();
		String cap = ((tf_cap.getText().isEmpty())||(!tf_num_civ.getText().matches(regex))||(tf_cap.getText().length()>5)) ? "!" : tf_cap.getText();
		String indirizzo = via+", "+tmp_indirizzo+", "+n_civ+", "+citta+" "+sigla_prov+", "+cap;
		if(indirizzo.contains("!")) check=false;
		System.out.print(indirizzo);
		
		String cell = ((tf_cell.getText().isEmpty())||(!tf_num_civ.getText().matches(regex))) ? "!" : tf_cell.getText();
		String sito = ((tf_sito.getText().isEmpty())||(!tf_sito.getText().contains(".")) ) ? "!" : tf_sito.getText();
		String tipologia = (cb_tipologia.getSelectedItem()=="") ? "!" : (String)cb_tipologia.getSelectedItem();
		
		String[] tmp_list = {nome,indirizzo,cell,sito, tipologia};	
		for(String s : tmp_list) {
				if(s.contains("!")) {check=false;break;}	
		}
		
		if(check==true) {
		
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(ristoranti_file, true));
				writer.newLine();
				writer.write(nome+" ll "+indirizzo+" ll "+cell+" ll "+sito+" ll "+tipologia+" ll "+"Recensione :"+" ll ");
				writer.flush();
				writer.close();
			} catch (IOException e) {e.printStackTrace();}
			
			JOptionPane.showMessageDialog(message,"Ristorante iscritto");
			
		}else JOptionPane.showMessageDialog(message,"Errore, dati non inseriti o errati");
		

	}

}