package clienti;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Registrazione {

	static JFrame frmRegistrazione;
	private static JPasswordField passwordField;
	private static JTextField tf_nome;
	private static JTextField tf_cognome;
	private static JTextField tf_comune;
	private static JTextField tf_email;
	private static JTextField tf_nikname;
	private static JComboBox cb_sigla_prov;
	static Frame message = new Frame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrazione window = new Registrazione();
					window.frmRegistrazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registrazione() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmRegistrazione = new JFrame();
		frmRegistrazione.setResizable(false);
		frmRegistrazione.setTitle("Registrazione");
		frmRegistrazione.setBounds(100, 100, 514, 420);
		frmRegistrazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistrazione.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome : ");
		lblNome.setBounds(10, 11, 46, 14);
		frmRegistrazione.getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome :");
		lblCognome.setBounds(10, 49, 63, 14);
		frmRegistrazione.getContentPane().add(lblCognome);
		
		JLabel lblComune = new JLabel("Comune di residenza :");
		lblComune.setBounds(10, 96, 113, 14);
		frmRegistrazione.getContentPane().add(lblComune);
		
		JLabel lblSigla_res = new JLabel("Sigla della provincia :");
		lblSigla_res.setBounds(10, 142, 113, 14);
		frmRegistrazione.getContentPane().add(lblSigla_res);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(10, 184, 46, 14);
		frmRegistrazione.getContentPane().add(lblEmail);
		
		JLabel lblNickname = new JLabel("Nickname :");
		lblNickname.setBounds(10, 231, 63, 14);
		frmRegistrazione.getContentPane().add(lblNickname);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 278, 63, 14);
		frmRegistrazione.getContentPane().add(lblPassword);
		
		
		
		tf_nome = new JTextField();
		tf_nome.setBounds(151, 8, 86, 20);
		frmRegistrazione.getContentPane().add(tf_nome);
		tf_nome.setColumns(10);
		
		tf_cognome = new JTextField();
		tf_cognome.setBounds(151, 46, 86, 20);
		frmRegistrazione.getContentPane().add(tf_cognome);
		tf_cognome.setColumns(10);
		
		tf_comune = new JTextField();
		tf_comune.setBounds(151, 93, 86, 20);
		frmRegistrazione.getContentPane().add(tf_comune);
		tf_comune.setColumns(10);
		
		JComboBox cb_sigla_prov = new JComboBox();
		cb_sigla_prov.setModel(new DefaultComboBoxModel(new String[] {"", "AG", "AL", "AN", "AO", "AQ", "AR", "AP", "AT", "AV", "BA", "BT",
				"BL", "BN", "BG", "BI", "BO", "BZ", "BS", "BR", "CA", "CL", "CB", "CI", "CE", "CT", "CZ", "CH", "CO", "CS", "CR", "KR",
				"CN", "EN", "FM", "FE", "FI", "FG", "FC", "FR", "GE", "GO", "GR", "IM", "IS", "SP", "LT", "LE", "LC", "LI", "LO", "LU", 
				"MC", "MN", "MS", "MT", "VS", "ME", "MI", "MO", "MB", "NA", "NO", "NU", "OG", "OT", "OR", "PD", "PA", "PR", "PV", "PG", 
				"PU", "PE", "PC", "PI", "PT", "PN", "PZ", "PO", "RG", "RA", "RC", "RE", "RI", "RN", "Roma", "RO", "SA", "SS", "SV", "SI", 
				"SR", "SO", "TA", "TE", "TR", "TO", "TP", "TN", "TV", "TS", "UD", "VA", "VE", "VB", "VC", "VR", "VV", "VI", "VT"}));
		cb_sigla_prov.setSelectedIndex(0);
		cb_sigla_prov.setBounds(151, 138, 86, 22);
		frmRegistrazione.getContentPane().add(cb_sigla_prov);
		
		tf_email = new JTextField();
		tf_email.setBounds(151, 181, 86, 20);
		frmRegistrazione.getContentPane().add(tf_email);
		tf_email.setColumns(10);
		
		tf_nikname = new JTextField();
		tf_nikname.setBounds(151, 228, 86, 20);
		frmRegistrazione.getContentPane().add(tf_nikname);
		tf_nikname.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 275, 86, 20);
		frmRegistrazione.getContentPane().add(passwordField);
		
		JButton btnIscrizione = new JButton("Iscriviti");
		btnIscrizione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//fare controllo dati inseriti
				//automatizzare sigla provincia
				String nome = tf_nome.getText();
				String cognome = tf_cognome.getText();
				String comune = tf_comune.getText();
				String sigla_prov = (String) cb_sigla_prov.getSelectedItem();
				String email = tf_email.getText();
				String nickname = tf_nikname.getText();
				String password = passwordField.getText();
				if((nome.isEmpty())||(cognome.isEmpty())||(comune.isEmpty())||(sigla_prov.equals(""))||(email.isEmpty())||(nickname.isEmpty())||(password.isEmpty())) {
					JOptionPane.showMessageDialog(message,"Dati mancanti !");
				}else if((!email.contains("@"))||(!email.contains("."))) {JOptionPane.showMessageDialog(message,"Campo email errato !");
				
				}else {
					try {
						Cliente_Struct user = new Cliente_Struct(nome,cognome,comune,
								sigla_prov,email,nickname,password);
						String[] user_data = user.get_user_data();
						//System.out.println(user_data[0]+" || "+ user.password);
				
						Reader_Writer.registra_utente(Clienti.clienti_file, user_data);
				
						Clienti.read_all_files();
					} catch (IOException e) {e.printStackTrace();}
				}
			}
		});
		btnIscrizione.setBounds(151, 347, 89, 23);
		frmRegistrazione.getContentPane().add(btnIscrizione);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear_field();
				frmRegistrazione.dispose();
				Clienti.frmClienti.setVisible(true);
			}
		});
		btnBack.setBounds(10, 347, 89, 23);
		frmRegistrazione.getContentPane().add(btnBack);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear_field();
			}
		});
		btnClear.setBounds(275, 347, 89, 23);
		frmRegistrazione.getContentPane().add(btnClear);
		
		
	}
	
	public static void clear_field() {
		tf_nome.setText("");
		tf_cognome.setText("");
		tf_comune.setText("");
		tf_email.setText("");
		tf_nikname.setText("");
		passwordField.setText("");
		
	}
}
