import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaClient {
    static Scanner lector = new Scanner(System.in);
    static public oClient client = new oClient();
	public static void programa_Client(Connection connexioPsql) throws Exception{
		// TODO Auto-generated method stub
		boolean login = false;
		
        boolean menu = false;
        do {
                boolean comprovacioInt = false;
                int opcioMenu = 0;
				do {
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|                    Comprar productes:                  |");
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|                  1 - Comprar productes                 |");
					System.out.println("|                  2 - Entrar a la Compte                |");
					System.out.println("|                    3 - Sortir Botiga                   |");
					System.out.println("|--------------------------------------------------------|");
					if(lector.hasNextInt()){
						opcioMenu = lector.nextInt();
						lector.nextLine();
						comprovacioInt = true;
					}
					else
					{
						System.out.println("|    Aquesta opcio no es correcte, torna a provar.       |");
						lector.nextLine();
					}
				} while (!comprovacioInt);
                switch (opcioMenu) {
                    case 1:
                            comprarProductes(login,connexioPsql);
                        break;
                    case 2:
                            client = ferLogIn(connexioPsql);
                            login = true;
                        break;
                    case 3:
                            System.out.println("|    Gracies per visitar la pagina, Pasi un bon dia.     |");
                            System.out.println("|--------------------------------------------------------|");
                            menu = true;
                        break;
                    default:
                            System.out.println("|    Aquesta opcio no es correcte, torna a provar.       |");
                        break;
                }
        } while (!menu);
        
    }
    public static void comprarProductes(boolean login, Connection connexioPsql) throws Exception{
		if(login){
			menuClient(connexioPsql);
		}
		else menuNoClient(connexioPsql);
	}
	public static void menuNoClient(Connection connexioPsql) throws Exception 
	{
        oFactura facturaClient = new oFactura();
		LocalDate data = LocalDate.now();
		int randomNum = (int) (Math.random() * ( 9999 - 1001 ));
		facturaClient.oFactura(randomNum,client,data);

		boolean menuNoClient = false;
		int codiProducte = 0;
        ArrayList<oProducte> lineaProductes = new ArrayList<oProducte>();
	
		do {
			boolean comprovacioInt = false;
		
			do {
				System.out.println("|--------------------------------------------------------|");
				System.out.println("|                    Compra Productes:                   |");
				System.out.println("|--------------------------------------------------------|");
				System.out.println("|          Entra el codi del producte que desitges       |");
				System.out.println("|             (Si desitges sortir entra un 0)            |");
				System.out.println("|--------------------------------------------------------|");
				if(lector.hasNextInt()){
					codiProducte = lector.nextInt();
					lector.nextLine();
					comprovacioInt = true;
				}
				else {
					System.out.println("|    Aquesta opcio no es correcte, torna a provar.       |");
					lector.nextLine();
				}
			} while (!comprovacioInt);
			if(oProducte.comprovaExistenciaProdu(codiProducte, connexioPsql) == 0){
				
				boolean comprovacioInt2 = false;
                int quantitatProducte = 0;
				do {
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|            Entra la quantitat que desitges:            |");
					System.out.println("|--------------------------------------------------------|");
					if(lector.hasNextInt()){
						quantitatProducte= lector.nextInt();
						lector.nextLine();
						comprovacioInt2 = true;
					}
					else {
						System.out.println("|    Aquesta opcio no es correcte, torna a provar.       |");
						lector.nextLine();
					}
				} while (!comprovacioInt2);
                lineaProductes.add(oProducte.tornaProducte(codiProducte, quantitatProducte, connexioPsql));

			}
			else if(oProducte.comprovaExistenciaProdu(codiProducte, connexioPsql) == 1){
				System.out.println("|      Aquesta opcio no existeix, torna a provar.        |");
			}
			else{
                boolean comprovaOpcioSi = false;
                boolean comprovaOpcioNo = false;
                do{
                    System.out.println("|--------------------------------------------------------|");
                    System.out.println("|        Necesites registrarte per fer la compra         |");
                    System.out.println("|   ('SI' em vull registrar | 'NO' em vull registrar)    |");
                    System.out.println("|--------------------------------------------------------|");
                    String opcio = lector.nextLine();
                    if(opcio.equalsIgnoreCase("SI"))
                    {
                    	String auxClient =  ferRegister(connexioPsql);
                    	String [] taula = auxClient.split("-");
                    	String dni = taula[0];
                    	String nom = taula[1];
                    	String correo = taula[2];
                    	int telefon = Integer.parseInt(taula[3]);
                    	String adresa = taula[4];
                        client.oClient(dni, nom, correo, telefon, adresa);
                        comprovaOpcioSi = true;
                    }
                    else if(opcio.equalsIgnoreCase("NO"))
                    {
                        System.out.println("|--------------------------------------------------------|");
                        System.out.println("|               La compra no s'executara                 |");
                        System.out.println("|             un plaer treballar amb voste.              |");
                        System.out.println("|--------------------------------------------------------|");
                        comprovaOpcioNo = true;
                    }
                    else System.out.println("|    Aquesta opcio no existeix, torna a provar.       |");
                }while(!comprovaOpcioSi && !comprovaOpcioNo);
                if(comprovaOpcioSi)
                {
                	System.out.println("|-------------------------aaaaaaaaaaa"+facturaClient.getClient().getDni()+"-------------------------------|");
                    
                    oFactura.tramitarFactura(facturaClient,lineaProductes,connexioPsql);
                    System.out.println("|   Gracies per comprar amb nosaltres, Pasi un bon dia.  |");
                    System.out.println("|--------------------------------------------------------|");
                    menuNoClient = true;
                }
                else
                {
                    System.out.println("|    Gracies per visitar la pagina, Pasi un bon dia.     |");
                    menuNoClient = true;
                }
			}
				
		} while (!menuNoClient);	
	}
	public static void menuClient(Connection connexioPsql)
	{

	}	
	public static String ferRegister(Connection connexioPsql) throws Exception 
	{
		String stringClient = null;
        oClient client = new oClient();
        String dni = "";
        String nom = "";
        String correo = "";
        int telefon = 0;
        String adresa = "";
        System.out.println("|--------------------------------------------------------|");
		System.out.println("|                         Register                       |");
		System.out.println("|--------------------------------------------------------|");
		boolean validacioDni = false;
        do {
            System.out.println("|                     Entra el teu dni:                  |");
            dni = lector.nextLine();
            if(oClient.validacioDni(dni))
            {
                if(!oClient.existeixDni(dni, connexioPsql))
                {
                    validacioDni = true;
                }
                else System.out.println("|      Aquest dni es incorrecte, torna a provar.         |");
            } 
            else System.out.println("|      Aquest dni es incorrecte, torna a provar.         |");
        } while (!validacioDni);

        System.out.println("|                     Entra el teu nom:                  |");
        nom = lector.nextLine();

        boolean validacioCorreo = false;
        do {
            System.out.println("|                    Entra el teu correo:                |");
            correo = lector.nextLine();
            if(oClient.validacioCorreo(correo)) validacioCorreo = true;
        } while (!validacioCorreo);

        boolean validacioTelefon = false;
        do {
            System.out.println("|                    Entra el teu telefon:               |");
            if(lector.hasNextInt())
            {
            	int auxTelInt = lector.nextInt();
            	lector.nextLine();
                String auxTelefon = Integer.toString(auxTelInt);
                if(auxTelefon.length() == 9)
                {
                    telefon = auxTelInt;
                    validacioTelefon = true;
                }
                else System.out.println("|    Aquest telefon es incorrecte, torna a provar.       |");
            }
            else System.out.println("|    Aquest telefon es incorrecte, torna a provar.       |");
        } while (!validacioTelefon);
        
        System.out.println("|                   Entra la teva adreça:                |");
        adresa = lector.nextLine();
        if(dni != null && nom != null && correo != null && telefon != 0 && adresa != null) 
        {
        	//client.oClient(dni, nom, correo, telefon, adresa);
        	client.setDni(dni);
        	client.setNom(nom);
        	client.setCorreu(correo);
        	client.setTelefon(telefon);
        	client.setAdresa(adresa);
        	stringClient = client.getDni() + "-" + client.getNom() + "-" + client.getCorreu() + "-" + client.getTelefon() + "-" + client.getAdresa();
            oClient.registrarClient(client, connexioPsql);
        }
        else System.out.println("|    Registre les dades novament, ha agut un error.      |");
        	 System.out.println("|--------------------------------------------------------|");
        
        
		return stringClient;
	}
	public static oClient ferLogIn(Connection connexioPsql) 
	{
		return null;
	}
}