import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer
{
	
	public static void serializePlayer( Player name )
	{
		
		try
		{
			FileOutputStream fileWrite = new FileOutputStream( "Players/" + name.getName() + ".ser" );
			ObjectOutputStream objectWrite = new ObjectOutputStream( fileWrite );
			objectWrite.writeObject( name );
			objectWrite.close();
			System.out.println( "Player " + name.getName() + " info serialized!" );
		} catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		
	}
}
