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
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;

/**
 * 
 * @author Moi Matteo 737574 Varese<br>
 * La classe ottiene in modo automatico il path dei fileClienti & EatAdvisor contenuti nella directory data<br>
 * I file vengono letti e salvati in degli arraylist tramite metodo read_all_files() per permetterne un utilizzo più pratico a tutte le classi<br>
 * Tramite GUI è possibile aprire il frame per la registrazione(Registrazione) o accedere a quello di ricerca (LogIn) senza credenziali(ma con la funzionalità di recensione bloccata)<br>
 * oppure con credenziali (ciò permetterà il completo utilizzo del frame LogIn)<br>
 * 
 */
public class Clienti {

	static JFrame frmClienti;
	Registrazione Registrazione = new Registrazione();
	LogIn login = new LogIn();

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
					File findme = new File("Findme.txt");
					String data_file_path = findme.getAbsolutePath();
					String clienti_file_path = data_file_path.replaceAll("Findme.txt", "data\\\\Utenti.data.txt");
					String ristoranti_file_path = data_file_path.replaceAll("Findme.txt", "data\\\\EatAdvisor.data.txt");
					System.out.println(clienti_file_path +" "+ ristoranti_file_path);
					clienti_file = new File(clienti_file_path);
					ristoranti_file = new File(ristoranti_file_path);
					//JOptionPane.showMessageDialog(message,clienti_file_path);
					read_all_files();
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
	public Clienti()  {
		initialize();
		
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClienti = new JFrame();
		frmClienti.getContentPane().setBackground(SystemColor.info);
		frmClienti.setResizable(false);
		frmClienti.setTitle("Clienti");
		frmClienti.setBounds(100, 100, 412, 213);
		frmClienti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClienti.getContentPane().setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname : ");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNickname.setBounds(113, 53, 86, 14);
		frmClienti.getContentPane().add(lblNickname);
		
		tf_nickname = new JTextField();
		tf_nickname.setBounds(219, 50, 86, 20);
		frmClienti.getContentPane().add(tf_nickname);
		tf_nickname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(113, 97, 86, 14);
		frmClienti.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 94, 86, 20);
		frmClienti.getContentPane().add(passwordField);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean error = false;
				ID = tf_nickname.getText();
				String password = passwordField.getText();
				for(int i = 5; i< clienti_list.size();) {
					int pos = i;
					//System.out.println(clienti_list.get(i));
					if((clienti_list.get(i).equals(ID))&&(clienti_list.get(pos+1).equals(password))) {
						JOptionPane.showMessageDialog(message,"Accesso effettuato!");
						login.frmLogin.setVisible(true);
						login.btnInvia.setEnabled(true);
						login.cb_star.setEnabled(true);
						login.ta_commento.setEnabled(true);
						tf_nickname.setText("");
						passwordField.setText("");
						frmClienti.setVisible(false);
						error = false;
						break;
					}
					i+=7;
					error = true;
				}
				if(error==true) {JOptionPane.showMessageDialog(message,"Dati inseriti errati!");}
			}
		});
		btnAccedi.setBounds(130, 147, 89, 23);
		frmClienti.getContentPane().add(btnAccedi);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmClienti.setVisible(false);
				Registrazione.frmRegistrazione.setVisible(true);
			}
		});
		btnRegistrati.setBounds(10, 147, 89, 23);
		frmClienti.getContentPane().add(btnRegistrati);
		
		JButton btnNoLogIn = new JButton("Accesso libero");
		btnNoLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.btnInvia.setEnabled(false);
				login.cb_star.setEnabled(false);
				login.ta_commento.setEnabled(false);
				login.frmLogin.setVisible(true);
				frmClienti.setVisible(false);
			}
		});
		btnNoLogIn.setBounds(261, 147, 127, 23);
		frmClienti.getContentPane().add(btnNoLogIn);
		
		
	}
	/**
	 * 
	 * @param file<br>
	 * @param data_list<br>
	 * @return data_list<br>
	 * @throws FileNotFoundException<br>
	 * inserisce all'interno di un'arraylistper ogni index un singolo dato<br>
	 * 
	 */
	public static ArrayList<String> read_file(File file, ArrayList<String> data_list ) throws FileNotFoundException {
		
		Scanner scan = new Scanner(file).useDelimiter(" ll ");
		while(scan.hasNextLine()) {
			try {
				data_list.add(scan.next());
			
			}catch(NoSuchElementException e ) {
				break;
			}
		}
		
		System.out.println(data_list);
		return data_list;
		
	}
	/**
	 * Salva nel  file Clienti.data.txt i dati inseriti dall'utente nel frame Registrazione<br>
	 */
	public static void registra_utente(File file, String[] data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		//System.out.println(clienti_file.getAbsolutePath());
		writer.newLine();
		for (int i = 0 ; i< data.length; i++) {
			writer.write(data[i]+" ll ");
		}
		writer.flush();
		writer.close();
		JOptionPane.showMessageDialog(message,"Utente registrato!");
		
	}
	/**
	 * 
	 * @throws FileNotFoundException
	 * Salva all'interno di una stringa di array il contenuto del file indicato<br>
	 */
	public static void read_all_files() throws FileNotFoundException {
		clienti_list.clear();
		ristoranti_list.clear();
		clienti_list = read_file(clienti_file,clienti_list );
		ristoranti_list = read_file(ristoranti_file,ristoranti_list);

		
	}
	/**
	 * 
	 * @param reconizer permette di riconoscere se effettuare la ricerca del ristorante tramite nome o tipologia<br>
	 * @param find indica il dato che viene utilizzato per ricercare i ristoranti<br>
	 * @return result_list arraylist contenente tutti i dati dei ristoranti che hanno avuto riscontro con la ricerca<br>
	 */
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
	/**
	 * 
	 * @param nome inviato dalla classe LogIn<br>
	 * @param commento  inviato dalla classe LogIn<br>
	 * @throws IOException
	 * Il metodo modifica la lista ristoranti_list contenente tutti i dati del file EatAdvisor e fa una sovrascrittura della recensione <br>
	 * (aggiungendo il nuovo commento)del ristorante indicato dal parametro nome <br>
	 * 
	 */
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
		writer.flush();
		writer.close();
		JOptionPane.showMessageDialog(message,"Commento inserito !");
		
		
		
	}
	
}
