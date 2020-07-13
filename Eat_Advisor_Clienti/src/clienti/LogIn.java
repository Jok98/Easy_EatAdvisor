package clienti;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
/**
 * 
 * @author Moi Matteo 737574 Varese
 * La clasee permette di  ricercare(btnCerca) i ristoranti tramite nome o tipologia gli eventuali risultati vengono poi mostrati<br>
 * nella result_list nella quale poi l'utente potrà scegliere un singolo ristorante di cui visualizare le informazioni(btnInfo) e le recensioni<br>
 * se l'utente ha effettuato l'accesso al frame LogIn tramite credenziali avrà anche la possibilità di effettuare un recensione(btnInvia)
 */
public class LogIn {

	JFrame frmLogin;
	JButton btnInvia;
	JComboBox cb_star;
	JTextArea ta_commento;
	private JTextField tf_nome;
	private JComboBox cb_tipologia;
	private JList result_list;
	static Frame message = new Frame();
	static int pos;

	ArrayList<Ristorante_Struct> restaurant_data = new ArrayList<Ristorante_Struct>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(SystemColor.info);
		frmLogin.setTitle("LogIn");
		frmLogin.setBounds(100, 100, 450, 520);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblRicerca = new JLabel("Ricerca");
		lblRicerca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRicerca.setBounds(104, 11, 86, 14);
		frmLogin.getContentPane().add(lblRicerca);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNome.setBounds(34, 36, 46, 14);
		frmLogin.getContentPane().add(lblNome);
		
		JLabel lblTipologia = new JLabel("Tipologia :");
		lblTipologia.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblTipologia.setBounds(34, 73, 60, 14);
		frmLogin.getContentPane().add(lblTipologia);
		
		tf_nome = new JTextField();
		tf_nome.setBounds(104, 32, 86, 20);
		frmLogin.getContentPane().add(tf_nome);
		tf_nome.setColumns(10);
		
		cb_tipologia = new JComboBox();
		cb_tipologia.setModel(new DefaultComboBoxModel(new String[] {"", "Italiano", "Etnico", "Fusion"}));
		cb_tipologia.setBounds(104, 68, 86, 22);
		frmLogin.getContentPane().add(cb_tipologia);
		
		JLabel lblNewLabel = new JLabel("Lista ristoranti");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(284, 11, 140, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		result_list = new JList();
		clean_list();
		result_list.setSelectedIndex(1);
		result_list.setBounds(284, 35, 140, 103);
		frmLogin.getContentPane().add(result_list);
		JScrollPane scroll_list = new JScrollPane (result_list);
		scroll_list.setBounds(284, 35, 140, 103);
		frmLogin.getContentPane().add(scroll_list);
		
		JLabel lblNewLabel_1 = new JLabel("Info Ristorante");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 209, 140, 14);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JTextArea info_restaurant = new JTextArea();
		info_restaurant.setEditable(false);
		info_restaurant.setLineWrap(true);
		info_restaurant.setBounds(10, 234, 200, 236);
		frmLogin.getContentPane().add(info_restaurant);
		JScrollPane scroll_info = new JScrollPane (info_restaurant);
		scroll_info.setBounds(10, 234, 200, 236);
		frmLogin.getContentPane().add(scroll_info);
		
		JLabel lblNewLabel_2 = new JLabel("Recensione");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(257, 209, 86, 14);
		frmLogin.getContentPane().add(lblNewLabel_2);
		
		ta_commento = new JTextArea();
		ta_commento.setLineWrap(true);
		ta_commento.setBounds(257, 262, 167, 172);
		frmLogin.getContentPane().add(ta_commento);
		JScrollPane scroll_commento = new JScrollPane (ta_commento);
		scroll_commento.setBounds(257, 262, 167, 172);
		frmLogin.getContentPane().add(scroll_commento);
		
		JLabel lblNewLabel_3 = new JLabel("Stelle :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_3.setBounds(257, 238, 46, 14);
		frmLogin.getContentPane().add(lblNewLabel_3);
		
		cb_star = new JComboBox();
		cb_star.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		cb_star.setBounds(313, 234, 60, 22);
		frmLogin.getContentPane().add(cb_star);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(0, 179, 434, 14);
		frmLogin.getContentPane().add(separator);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				String nome = tf_nome.getText();
				String tipologia = (String) cb_tipologia.getSelectedItem();
				try {
				if((!nome.equals(""))&&(!tipologia.equals(""))) {
					JOptionPane.showMessageDialog(message,"Ricerca valida solo su un campo !");
					
				}else if((!nome.isEmpty())&&(tipologia.equals(""))) {
					JOptionPane.showMessageDialog(message,"Ricerca per nome : " + nome);
					restaurant_data = Clienti.search_func("nome", nome);
					System.out.println(restaurant_data.get(0).nome);
					
				//&&(!tipologia.equals(""))
				}else if((nome.equals(""))&&(!tipologia.equals(""))){
					JOptionPane.showMessageDialog(message,"Ricerca per tipologia : "+ tipologia);
					restaurant_data = Clienti.search_func("tipologia", tipologia);
					
				}
				
				String [] values = new String[restaurant_data.size()];
				int position;
				for (int i = 0; i < restaurant_data.size(); i++) {
					
					values[i] = restaurant_data.get(i).nome;
				}

				result_list.setModel(new AbstractListModel() {
					public int getSize() {return values.length;}
					public Object getElementAt(int index) {return values[index];}
					});
				info_restaurant.setText("");
				}catch(IndexOutOfBoundsException e) {JOptionPane.showMessageDialog(message,"Nessun ristorante trovato");}
				if(restaurant_data.isEmpty())JOptionPane.showMessageDialog(message,"Nessun ristorante trovato");
			
				
			}
		});
		btnCerca.setBounds(104, 130, 89, 23);
		frmLogin.getContentPane().add(btnCerca);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pos = result_list.getSelectedIndex();
				String tmp_comm = restaurant_data.get(pos).commento;
				tmp_comm = tmp_comm.replaceAll("----", "\n");
				info_restaurant.setText("Nome : "+restaurant_data.get(pos).nome+"\n"+"Indirizzo : "+restaurant_data.get(pos).indirizzo
						+"\n"+"Tell : "+restaurant_data.get(pos).tell+"\n"+"Sito : "+restaurant_data.get(pos).sito
						+"\n"+"Tipologia : "+restaurant_data.get(pos).tipologia+"\n"+tmp_comm);
			}
		});
		btnInfo.setBounds(313, 149, 89, 23);
		frmLogin.getContentPane().add(btnInfo);
		
		btnInvia = new JButton("Invia");
		btnInvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp_comm = ta_commento.getText();
				if(tmp_comm.length()<257) {
					try {
						String comment = "ID : "+ Clienti.ID+"----Stelle : "+cb_star.getSelectedItem()+"----Commento : "+tmp_comm+"----";
						Clienti.insert_comment(restaurant_data.get(pos).nome, comment);
						ta_commento.setText("");
					} catch (IOException e) {
					
						e.printStackTrace();
					}
				}else JOptionPane.showMessageDialog(message,"Superata dimensione di 256 caratteri");

			}
		});
		btnInvia.setBounds(300, 445, 89, 23);
		frmLogin.getContentPane().add(btnInvia);
		
		JButton btnlogout = new JButton("Logout");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clienti.frmClienti.setVisible(true);
				clean_list();
				tf_nome.setText("");
				cb_tipologia.setSelectedIndex(0);
				ta_commento.setText("");
				cb_star.setSelectedIndex(0);
				cb_tipologia.setSelectedIndex(0);
				info_restaurant.setText("");
				frmLogin.dispose();
			}
		});
		btnlogout.setBounds(5, 130, 89, 23);
		frmLogin.getContentPane().add(btnlogout);
		
		JList list = new JList();
		list.setBounds(400, 36, 24, 103);
		frmLogin.getContentPane().add(list);
	}
	
	@SuppressWarnings("unchecked")
	public void clean_list() {
		result_list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Lista ristoranti trovati"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
}
