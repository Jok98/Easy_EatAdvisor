package clienti;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import java.awt.Font;

public class Clienti {

	static JFrame frmClienti;
	Registrazione Registrazione = new Registrazione();
	LogIn login = new LogIn();
	static Reader_Writer R_W ;
	
	
	static ArrayList<String> clienti_list = new ArrayList<String>();
	static ArrayList<String> ristoranti_list = new ArrayList<String>();
	static File clienti_file;
	static File ristoranti_file;
	
	private JTextField tf_nickname;
	private JTextField textField;
	private JPasswordField passwordField;
	static Frame message = new Frame();
	
	static String ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clienti window = new Clienti();
					window.frmClienti.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public Clienti() throws FileNotFoundException {
		initialize();
		
		File findme = new File("Findme.txt");
		String data_file_path = findme.getAbsolutePath();
		String clienti_file_path = data_file_path.replaceAll("Findme.txt", "data\\\\Utenti.data.txt");
		String ristoranti_file_path = data_file_path.replaceAll("Findme.txt", "data\\\\EatAdvisor.data.txt");
		System.out.println(clienti_file_path +" "+ ristoranti_file_path);
		clienti_file = new File(clienti_file_path);
		ristoranti_file = new File(ristoranti_file_path);
		read_all_files();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClienti = new JFrame();
		frmClienti.setResizable(false);
		frmClienti.setTitle("Clienti");
		frmClienti.setBounds(100, 100, 450, 300);
		frmClienti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClienti.getContentPane().setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname : ");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNickname.setBounds(110, 72, 86, 14);
		frmClienti.getContentPane().add(lblNickname);
		
		tf_nickname = new JTextField();
		tf_nickname.setBounds(216, 69, 86, 20);
		frmClienti.getContentPane().add(tf_nickname);
		tf_nickname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(110, 116, 86, 14);
		frmClienti.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 113, 86, 20);
		frmClienti.getContentPane().add(passwordField);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ID = tf_nickname.getText();
				String password = passwordField.getText();
				for(int i = 5; i< clienti_list.size();) {
					int pos = i;
					//System.out.println(clienti_list.get(i));
					if((clienti_list.get(i).equals(ID))&&(clienti_list.get(pos+1).equals(password))) {
						JOptionPane.showMessageDialog(message,"Accesso effettuato!");
						login.frmLogin.setVisible(true);
						tf_nickname.setText("");
						passwordField.setText("");
						frmClienti.setVisible(false);
						break;
					}
					i+=7;
				}
			}
		});
		btnAccedi.setBounds(177, 237, 89, 23);
		frmClienti.getContentPane().add(btnAccedi);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmClienti.setVisible(false);
				Registrazione.frmRegistrazione.setVisible(true);
			}
		});
		btnRegistrati.setBounds(13, 237, 89, 23);
		frmClienti.getContentPane().add(btnRegistrati);
		
		JButton btnNoLogIn = new JButton("Accesso libero");
		btnNoLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.btnInvia.setEnabled(false);;
				login.cb_star.setEnabled(false);;
				login.ta_commento.setEnabled(false);;
				login.frmLogin.setVisible(true);
				frmClienti.setVisible(false);
			}
		});
		btnNoLogIn.setBounds(319, 237, 115, 23);
		frmClienti.getContentPane().add(btnNoLogIn);
		
		
	}
	
	public static void read_all_files() throws FileNotFoundException {
		clienti_list.clear();
		ristoranti_list.clear();
		R_W = new Reader_Writer(clienti_file,ristoranti_file);
		clienti_list = R_W.read_file(clienti_file,clienti_list );
		ristoranti_list = R_W.read_file(ristoranti_file,ristoranti_list);

		
	}
	
	public static ArrayList<Ristorante_Struct> search_func(String reconizer, String find) {
		ArrayList<Ristorante_Struct> result_list = new ArrayList<Ristorante_Struct>();
		if(reconizer.equals("nome")) {
			int pos;
			for(int i=0;i<ristoranti_list.size();) {
				pos = i;
				if(ristoranti_list.get(i).contains(find)) {
					result_list.add(new Ristorante_Struct(ristoranti_list.get(pos),ristoranti_list.get(pos+1),ristoranti_list.get(pos+2),
							ristoranti_list.get(pos+3),ristoranti_list.get(pos+4),ristoranti_list.get(pos+5)));

				}
				//numero elementi Classe Ristorante_Struct
				i+=6;
			}	
		}else if (reconizer.equals("tipologia")) {
			int pos;
			for(int i=4;i<ristoranti_list.size();) {
				pos = i;
				if(ristoranti_list.get(i).equals(find)) {
					result_list.add(new Ristorante_Struct(ristoranti_list.get(pos-4),ristoranti_list.get(pos-3),ristoranti_list.get(pos-2),
							ristoranti_list.get(pos-1),ristoranti_list.get(pos),ristoranti_list.get(pos+1)));
				}
				//numero elementi Classe Ristorante_Struct
				i+=6;
			}
		}else return null; 
		
		return result_list;
		
	}
	
	public static void insert_comment(String nome, String commento) throws IOException {
	
		for(int i=0;i<ristoranti_list.size();) {
			if(ristoranti_list.get(i).contains(nome)) {
				String tmp = ristoranti_list.get(i+5);
				ristoranti_list.remove(i+5);
				ristoranti_list.add(i+5, tmp+"----"+commento);

			}
			i+=6;
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(ristoranti_file));

		for (int i = 0 ; i< ristoranti_list.size(); i++) {
		
			writer.write(ristoranti_list.get(i)+" ll ");
		}
		//writer.write(data[7]);
		writer.flush();
		writer.close();
		JOptionPane.showMessageDialog(message,"Commento inserito !");
		
		
		
	}
	
}
