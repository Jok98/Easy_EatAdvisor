package clienti;


public class Cliente_Struct {
	String nome;
	String cognome;
	String comune;
	String sigla_provincia;
	String email;
	String nikname;
	String password;
	
	public Cliente_Struct(String nome,String cognome,String comune,String sigla_provincia,String email,String nikname,String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.comune = comune;
		this.sigla_provincia = sigla_provincia;
		this.email = email;
		this.nikname = nikname;
		this.password = password;
	}
	
	public String[] get_user_data() {
		String[] user_data = {nome,cognome,comune,sigla_provincia,email,nikname,password};
		return user_data;
		}

}
