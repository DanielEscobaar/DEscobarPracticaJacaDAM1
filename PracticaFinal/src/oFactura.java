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
		
		String dataFactura = facturaClient.getData().getDayOfMonth()+"/"+ facturaClient.getData().getMonthValue()+"/"+ facturaClient.getData().getYear();
		stmt.executeUpdate("INSERT INTO factures_client values("+facturaClient.getNumFactura()+",'" + facturaClient.getClient().getDni() + "', '"+ dataFactura +"')");
		entrarTotesLesLineas(facturaClient,lineaProductes,connexioPsql);
		restarStockProductes(facturaClient,lineaProductes,connexioPsql);
		
		System.out.println("|       La seva compra s'ha tramitat correctament.       |");
		System.out.println("|        (Torn-hi ha comprar quan voste vulgui.)         |");
	}
	private static void restarStockProductes(oFactura facturaClient, ArrayList<oProducte> lineaProductes,
			Connection connexioPsql) throws SQLException {
		for(int i = 0; i < lineaProductes.size(); i++) 
		{
			Statement stmt2=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			ResultSet resultatQuery = stmt2.executeQuery("SELECT * FROM productes WHERE codi = "+lineaProductes.get(i).getCodi()+";");
			resultatQuery.next();
			int nouStock = resultatQuery.getInt("stock") - lineaProductes.get(i).getQuantitat();
			resultatQuery.updateInt("stock", nouStock);
			resultatQuery.updateRow();
		}
		
	}

	private static void entrarTotesLesLineas(oFactura facturaClient, ArrayList<oProducte> lineaProductes, Connection connexioPsql) throws Exception {
		// TODO Auto-generated method stub
		for(int i = 0; i < lineaProductes.size(); i++) 
		{
			Statement stmt2=connexioPsql.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			int numFactura = facturaClient.getNumFactura();
			int numLinea = i;
			int codiProducte = lineaProductes.get(i).codi;
			int quantitatProducte = lineaProductes.get(i).quantitat;
			Double preuProducte = lineaProductes.get(i).preu;
			Double totalProducte = (lineaProductes.get(i).preu * lineaProductes.get(i).quantitat);
			Double iva = lineaProductes.get(i).iva;
			
			stmt2.executeUpdate("INSERT INTO lineafactura values("+ numFactura + "," + numLinea + "," + codiProducte + "," + quantitatProducte + 
			"," + preuProducte + "," + totalProducte + "," + iva+")");
		}
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
