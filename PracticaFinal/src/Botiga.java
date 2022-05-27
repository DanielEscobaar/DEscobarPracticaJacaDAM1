import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Botiga {
	
	static Scanner lector = new Scanner(System.in);
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		try 
		{
			Connection connexioPsql = DriverManager.getConnection("jdbc:postgresql://localhost:5432/botigaVirtual","postgres","1234");
			
			int opcioMenu = 0;
			boolean menu = false;
			do
			{
				oProducte.llistarProductes(connexioPsql);

				boolean comprovacioInt = false;
				do {
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|                     Menu Principal:                    |");
					System.out.println("|--------------------------------------------------------|");
					System.out.println("|                  1 - Comprar productes                 |");
					System.out.println("|                    2 - Gestio Botiga                   |");
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
							
							ProgramaClient.programa_Client(connexioPsql);
						break;
					case 2:	
							ProgramaEmpresa.programa_Empresa(connexioPsql);
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
			}while(!menu);
		}
		catch(Exception e) 
		{
			System.out.println("Error: " + e);
		}
	}

}
