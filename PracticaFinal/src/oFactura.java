import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class oFactura {
	
	public int numFactura;
	public oClient client;
	public LocalDate data; 
	
	
	public oFactura() {
		
	}
	
	public void oFactura(int numFactura, oClient client, LocalDate data) {
		this.numFactura = numFactura;
		this.client = client;
		this.data = data;
	}
	
	public static void tramitarFactura(oFactura facturaClient, ArrayList<oProducte> lineaProductes,Connection connexioPsql) throws Exception {
		// estat
		Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate("INSERT INTO factures_client values('" + facturaClient.getClient().getDni() + "', '"+ facturaClient.getData() +"')");
		for(int i = 0; i < lineaProductes.size(); i++) 
		{
			stmt.executeUpdate("INSERT INTO lineafactura values(" + facturaClient.getNumFactura() + "," + (i+1) + "," + lineaProductes.get(i).codi + "," + lineaProductes.get(i).quantitat + "," + lineaProductes.get(i).preu + "," + (lineaProductes.get(i).preu * lineaProductes.get(i).quantitat ) + "," + lineaProductes.get(i).iva);
		}
		System.out.println("|--------------------------------------------------------|");
		System.out.println("|       La seva compra s'ha tramitat correctament.       |");
		System.out.println("|        (Torn-hi ha comprar quan voste vulgui.)         |");
		System.out.println("|--------------------------------------------------------|");
	}
	public static boolean comprovaNumFactura(int numFactura,Connection connexioPsql) throws Exception {
		boolean existeix = false;
		// estat
		Statement stmt=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		// resultat
		ResultSet resultat = stmt.executeQuery("SELECT * FROM factures_client WHERE numfactura = "+numFactura+";");
		if(resultat.next()) return existeix = true;
		else return existeix = false;
	}
	public int getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public oClient getClient() {
		return client;
	}

	public void setClient(oClient client) {
		this.client = client;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	
	
}
