package clienti;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Reader_Writer {
	
	
	
	Scanner scan;
	static File clienti_file;
	static File ristoranti_file;
	
	static Frame message = new Frame();
	
	public Reader_Writer( File clienti_file, File ristoranti_file) {
		this.clienti_file = clienti_file;
		this.ristoranti_file = ristoranti_file;
		
	}
	
	public ArrayList<String> read_file(File file, ArrayList<String> data_list ) throws FileNotFoundException {
		
		scan = new Scanner(file).useDelimiter(" ll ");
		while(scan.hasNextLine()) {
			try {
				data_list.add(scan.next());
			
			}catch(NoSuchElementException e ) {
				break;
			}
		}
		
		System.out.println(data_list);
		//System.out.println(data_list.get(7));
		return data_list;
		
	}
	
	public static void registra_utente(File file, String[] data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		//System.out.println(clienti_file.getAbsolutePath());
		//vedere per eliminare righe vuote
		writer.newLine();
		for (int i = 0 ; i< data.length; i++) {
			/*
			if(i!= data.length-1) {writer.write(data[i]+" ll ");
			}else writer.write(data[i]);*/
			writer.write(data[i]+" ll ");
		}
		//writer.write(data[7]);
		writer.flush();
		writer.close();
		JOptionPane.showMessageDialog(message,"Utente registrato!");
		
	}
	
	

}
