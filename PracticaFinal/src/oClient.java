import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class oClient {
	
	String dni;
	String nom;
	String correo;
	int telefon;
	String adresa;
	
	public oClient() 
	{
		
	}
	public void oClient(String dni,String nom,String correo,int telefon,String adresa)
	{
		this.dni = dni;
		this.nom = nom;
		this.correo = correo;
		this.telefon = telefon;
		this.adresa = adresa;
	}
	public String getDni() {
		return this.dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCorreu() {
		return this.correo;
	}
	public void setCorreu(String correu) {
		this.correo = correu;
	}
	public int getTelefon() {
		return this.telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	public String getAdresa() {
		return this.adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public static boolean registrarClient(oClient client,Connection connexioPsql) throws Exception {
		boolean registrat = false;
		Statement escriure = connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		escriure.executeUpdate("INSERT INTO clients values('" + client.getDni() + "','" + client.getNom() + "','" + client.getCorreu() + "'," + client.getTelefon() + ",'" + client.getAdresa() + "')");
		registrat = true;	
		return registrat;
	}
	public static boolean existeixDni(String dni,Connection connexioPsql) throws Exception {
		boolean returnBool;
		// estat
		Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		// resultat
		ResultSet resultat = stmt.executeQuery("SELECT dni FROM clients WHERE dni = '"+dni+"';");
		if(resultat.next()) return returnBool = true;
		else return returnBool = false;
	}
	public static boolean validacioDni(String dni) {
		
		boolean correcte = false;
		
		String letra= "TRWAGMYFPDXBNJZSQVHLCKE";
		Boolean res= false;
		if(dni.length()==9)
		{
			res=true;
			for(int i=0;i<dni.length()-1;i++){
				res= res&&Character.isDigit(dni.charAt(i));
			}
			Integer valor= new Integer(dni.substring(0, 8));
			int aux= valor%23;
			Character letraReal = dni.charAt(8);
			Character letraCalculada= letra.charAt(aux);
			if(letraReal==letraCalculada) res= true;
		}
		if(res) correcte = true;
		else correcte = false;
		return correcte;
	}
	public static boolean validacioCorreo(String correo2) {
		 // Establecer el patron
	    Pattern p = Pattern.compile("[-\\w\\.]+@[\\.\\w]+\\.\\w+");
	    // Asociar el string al patron
	    Matcher m = p.matcher(correo2);
	   // Comprobar si encaja
	   return m.matches();
	}

}

