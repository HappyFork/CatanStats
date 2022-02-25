import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
//import java.util.Scanner;

public class Deserializer
{
	public static Player deserializePlayer( String fileName )
	{
		Player currentPlayer = null;
		
		File f = null;
		
		//while ( currentPlayer == null )
		//{
			f = new File ( "Players/" + fileName );
			
			if ( f.exists() && !f.isDirectory() )
			{
				try
				{
					FileInputStream fileRead = new FileInputStream( "Players/" + fileName );
					ObjectInputStream objectRead = new ObjectInputStream( fileRead );
					currentPlayer = (Player)objectRead.readObject();
					objectRead.close();
					fileRead.close();
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException i)
				{
					i.printStackTrace();
				}
			} else
			{
			//	System.out.println( "That is not a saved player. Please try again" );
				currentPlayer = null;
			//	Scanner keyboard2 = new Scanner( System.in );
			//	fileName = keyboard2.next() + ".ser";
			//	keyboard2.nextLine();
			//	keyboard2.close();
			}
		//}
		
		return currentPlayer;
		
	}
}
