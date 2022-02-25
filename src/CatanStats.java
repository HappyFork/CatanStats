import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;

public class CatanStats
{

	public static final Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args)
	{

		boolean end = false;
		
		while( end == false )
		{
			System.out.println( "What would you like to do?\n" );
			System.out.println( "(1) Record a game" );
			System.out.println( "(2) Check a player's stats" );
			System.out.println( "(3) Add a new player" );
			System.out.println( "(4) Edit a player's stats" );
			
			int choice = getIntFromKeyboard();
			while ( choice > 4 )
			{
				System.out.println( "I'm sorry, that's not an acceptable choice" );
				keyboard.nextLine();
				choice = getIntFromKeyboard();
			}
			
			if( choice == 1 )
			{
				recordGame();
			} else if ( choice == 2 )
			{
				checkStats();
			} else if ( choice == 3 )
			{
				addPlayer();
			} else if ( choice == 4 )
			{
				editPlayer();
			} else
			{
				System.out.println( "I'm sorry, that's not an acceptable choice" );
			}
			
			System.out.println( "Would you like to do something else? (True/False) " );
			
			
			end = !getBoolFromKeyboard();
		}
		
		keyboard.close();
		System.out.println( "Goodbye!" );
	}
	
	public static void recordGame()
	{
		
		System.out.println( "How many people played?" );
		int choice = getIntFromKeyboard();
		while ( choice < 2 )
		{
			System.out.println( "You can't play a game with less than two people. Try again." );
			keyboard.nextLine();
			choice = getIntFromKeyboard();
		}
		Player[] players = new Player[choice];
		String[] names = new String[choice];
		System.out.println( "Who played? Seperate names by spaces or new lines only please." );
		for( int i = 0; i < players.length; i++ ){
			String tempString = keyboard.next();
			tempString = tempString.toLowerCase();
			while ( Deserializer.deserializePlayer( tempString + ".ser" ) == null )
			{
				System.out.println( "That is not a saved player. Please try again.");
				tempString = keyboard.next();
			}
			players[i] = Deserializer.deserializePlayer( tempString + ".ser" );
			players[i].increaseGames(1);
			names[i] = players[i].getName().substring(0, 1).toUpperCase() + players[i].getName().substring(1);
		}
		System.out.println( "\nWho won?" );
		keyboard.nextLine();
		for( int i = 0; i < players.length; i++ )
		{
			System.out.println( "(" + ( i + 1 ) + ") " + names[i] + "?" );
		}
		choice = getIntFromKeyboard();
		while ( choice > players.length + 1  )
		{
			System.out.println( "I'm sorry, that's not an acceptable choice" );
			keyboard.nextLine();
			choice = getIntFromKeyboard();
		}
		players[choice-1].increaseWins(1);
		System.out.println( "Congratulations " + names[choice-1] + "! All hail the lord of Catan!" );
		int tempInt, portInt;
		String tempString;
		for( int i = 0; i < players.length; i++ )
		{
			System.out.println( "\nHow many cities did " + names[i] + " build?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseCities(tempInt);
			System.out.println( "How many settlements did " + names[i] + " build, including cities?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseSettlements(tempInt);
			System.out.println( "How many roads did " + names[i] + " build?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseRoads(tempInt);
			System.out.println( "How many development cards did " + names[i] + " play" );
			tempInt = getIntFromKeyboard();
			players[i].increaseDevs(tempInt);
			System.out.println( "How many sheep hexes did " + names[i] + " build on?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseSheep(tempInt);
			System.out.println( "How many brick hexes did " + names[i] + " build on?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseBrick(tempInt);
			System.out.println( "How many ore hexes did " + names[i] + " build on?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseOre(tempInt);
			System.out.println( "How many wood hexes did " + names[i] + " build on?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseWood(tempInt);
			System.out.println( "How many wheat hexes did " + names[i] + " build on?" );
			tempInt = getIntFromKeyboard();
			players[i].increaseWheat(tempInt);
			System.out.println( "How many ports did " + names[i] + " build on?" );
			tempInt = getIntFromKeyboard();
			if( tempInt != 0 )
			{
				System.out.println( "How many of those were 3:1 ports?" );
				portInt = getIntFromKeyboard();
				while ( portInt > tempInt )
				{
					System.out.println( "That's more ports than were built. Try again." );
					System.out.print( "Total ports: " );
					tempInt = getIntFromKeyboard();
					System.out.print( "3:1 ports: " );
					portInt = getIntFromKeyboard();
				}
				players[i].increaseRegPorts( portInt );
				if ( tempInt > portInt )
				{
					System.out.println( "What ports were the others built on? Seperate ports by spaces or new lines only please." );
					for (int l = 0; l < ( tempInt - portInt ); l++)
					{
						tempString = keyboard.next();
						switch ( tempString.toLowerCase() )
						{
						case "sheep":
							players[i].increaseSheepPorts( 1 );
							break;
						case "brick":
							players[i].increaseBrickPorts( 1 );
							break;
						case "ore":
							players[i].increaseOrePorts( 1 );
							break;
						case "wood":
							players[i].increaseWoodPorts( 1 );
							break;
						case "wheat":
							players[i].increaseWheatPorts( 1 );
							break;
						default:
							System.out.println( tempString + " is not an existing port. Please try again. " );
							l--;
							break;
						}
					}
				}
			}
			Serializer.serializePlayer(players[i]);
		}
		
	}
	
	public static int getIntFromKeyboard()
	{
		int x;
		while ( !keyboard.hasNextInt() )
		{
			System.out.println( "I'm sorry, that's not an acceptable choice." );
			keyboard.nextLine();
			keyboard.hasNextInt();	
		}
			
		x = keyboard.nextInt();
		if ( x < 0 )
		{
			System.out.println( "Negative numbers are not acceptable input." );
			x = getIntFromKeyboard();
		}
			
		return x;
	}
	
	public static int getNegIntFromKeyboard()
	{
		int x;
		while ( !keyboard.hasNextInt() )
		{
			System.out.println( "I'm sorry, that's not an acceptable choice." );
			keyboard.nextLine();
			keyboard.hasNextInt();	
		}
			
		x = keyboard.nextInt();
		/*if ( x < 0 )
		{
			System.out.println( "Negative numbers are not acceptable input." );
			x = getIntFromKeyboard();
		}*/
			
		return x;
	}
	
	public static boolean getBoolFromKeyboard()
	{
		while ( !keyboard.hasNextBoolean() )
		{
			//keyboard.nextLine();
			keyboard.hasNextBoolean();
			System.out.println( "Please give answer as \"true\" or \"false\"" );
			keyboard.nextLine();
		}
		boolean x = keyboard.nextBoolean();
		return x;
	}
	
	public static void checkStats()
	{
		boolean end = false;
		while( end == false )
		{
			
			System.out.println( "Which player's stats would you like to see?" );
			//File f = null;
			String name;
			//do
			//{
				keyboard.nextLine();
				name = keyboard.next();
				name = name.toLowerCase();
				//f = new File( name + ".ser" );
				while ( Deserializer.deserializePlayer( name + ".ser" ) == null )
				{
					System.out.println( "That is not a saved player. Please try again.");
					name = keyboard.next();
				}
				 
			//} while ( !f.exists() );
			Player x = Deserializer.deserializePlayer( name + ".ser" );
			name = x.getName().substring(0, 1).toUpperCase() + x.getName().substring(1);
			if ( x.getGames() == 0 )
			{
				System.out.println( "That player hasn't played any games!" );
			} else {
				System.out.println( "Player " + name + "\'s stats");
				System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
				System.out.println( "Games Played:				" + x.getGames() );
				System.out.println( "Wins:					" + x.getWins() );
				System.out.println( "Win percent:				" + percent( x.getWins() , x.getGames() ) + "%" );
				System.out.println( "Average Roads per Game:			" + ( x.getRoads() / x.getGames() ) );
				System.out.println( "Average Settlements per Game:		" + ( x.getSettlements() / x.getGames() ) );
				System.out.println( "Average Cities per Game: 		" + ( x.getCities() / x.getGames() ) );
				System.out.println( "Average Development Cards per Game:	" + ( x.getDevs() / x.getGames() ) );
				int ports = x.getRegPorts() + x.getSheepPorts() + x.getBrickPorts() + x.getOrePorts() + x.getWheatPorts() + x.getWoodPorts();
				System.out.println( "Average Ports per Game:			" + ( ports / x.getGames() ) );
				System.out.println( "Percent of resources built on..." );
				int resources = x.getSheep() + x.getBrick() + x.getOre() + x.getWheat() + x.getWood();
				System.out.println( "Sheep:					" + percent( x.getSheep() , resources ) + "%" );
				System.out.println( "Brick:					" + percent( x.getBrick() , resources ) + "%" );
				System.out.println( "Ore:					" + percent( x.getOre() , resources ) + "%" );
				System.out.println( "Wheat:					" + percent( x.getWheat() , resources ) + "%" );
				System.out.println( "Wood: 					" + percent( x.getWood() , resources ) + "%" );
				if ( ports == 0 )
				{
					System.out.println( "Port stats unavailable. Build on a port!" );
				} else
				{
					System.out.println( "Percent of ports built on..." );
					System.out.println( "3:1: 					" + percent( x.getRegPorts(), ports ) + "%" );
					System.out.println( "Sheep:					" + percent( x.getSheepPorts(), ports ) + "%" );
					System.out.println( "Brick:					" + percent( x.getBrickPorts(), ports ) + "%" );
					System.out.println( "Ore:					" + percent( x.getOrePorts(), ports ) + "%" );
					System.out.println( "Wheat:					" + percent( x.getWheatPorts(), ports ) + "%" );
					System.out.println( "Wood:					" + percent( x.getWoodPorts(), ports ) + "%" );
				}
			}
			
			//This is to clear the scanner stream and also allow players to pause
			//before the next message. I hope this isn't buggy...
			keyboard.nextLine();
			System.out.println( "Would you like to see another player's stats? (True/False)" );
			
			end = !getBoolFromKeyboard();
		}
	}
	
	public static void addPlayer()
	{
		
		System.out.println( "What is the new player's name?" );
		String name = keyboard.next();
		name = name.toLowerCase();
		File f = new File( name + ".ser" );
		Player x = new Player( name );
		if ( f.exists() && !f.isDirectory() )
		{
			System.out.println( "That file already exists! Would you like to overwrite it? (True/False)" );
			boolean a = getBoolFromKeyboard();
			if ( a == true )
			{
				Serializer.serializePlayer( x );
				System.out.print( "A Player by the name " );
				System.out.print( x.getName().substring(0, 1).toUpperCase() + x.getName().substring(1) );
				System.out.println( " has been created and saved in " + x.getName() + ".ser" );
			}
		} else
		{
			Serializer.serializePlayer( x );
			System.out.print( "A Player by the name " );
			System.out.print( x.getName().substring(0, 1).toUpperCase() + x.getName().substring(1) );
			System.out.println( " has been created and saved in " + x.getName() + ".ser" );
		}
		
		//Same deal as the previous method
		keyboard.nextLine();
	
	}
	
	public static void editPlayer()
	{
		System.out.println( "Which player would you like to edit?" );
		String name;
		keyboard.nextLine();
		name = keyboard.next();
		name = name.toLowerCase();
		int tempInt;
		
		while ( Deserializer.deserializePlayer( name + ".ser" ) == null )
		{
			System.out.println( "That is not a saved player. Please try again." );
			name = keyboard.next();
		}
		Player y = Deserializer.deserializePlayer( name + ".ser" );
		name = y.getName().substring(0, 1).toUpperCase() + y.getName().substring(1);
		
		System.out.println( name + "'s current games is " + y.getGames() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseGames( tempInt );
			System.out.println( "New Value = " + y.getGames() );
		}
		
		System.out.println( name + "'s current wins is " + y.getWins() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseWins( tempInt );
			System.out.println( "New Value = " + y.getWins() );
		}
		//y.increaseWins( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current settlements is " + y.getSettlements() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseSettlements( tempInt );
			System.out.println( "New Value = " + y.getSettlements() );
		}
		//y.increaseSettlements( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current roads is " + y.getRoads() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseRoads( tempInt );
			System.out.println( "New Value = " + y.getRoads() );
		}
		//y.increaseRoads( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current sheep is " + y.getSheep() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseSheep( tempInt );
			System.out.println( "New Value = " + y.getSheep() );
		}
		//y.increaseSheep( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current brick is " + y.getBrick() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseBrick( tempInt );
			System.out.println( "New Value = " + y.getBrick() );
		}
		//y.increaseBrick( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current ore is " + y.getOre() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseOre( tempInt );
			System.out.println( "New Value = " + y.getOre() );
		}
		//y.increaseOre( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current wood is " + y.getWood() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseWood( tempInt );
			System.out.println( "New Value = " + y.getWood() );
		}
		//y.increaseWood( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current wheat is " + y.getWheat() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseWheat( tempInt );
			System.out.println( "New Value = " + y.getWheat() );
		}
		//y.increaseWheat( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current cities is " + y.getCities() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseCities( tempInt );
			System.out.println( "New Value = " + y.getCities() );
		}
		//y.increaseCities( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current development cards is " + y.getDevs() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseDevs( tempInt );
			System.out.println( "New Value = " + y.getDevs() );
		}
		//y.increaseDevs( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current 3:1 ports is " + y.getRegPorts() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseRegPorts( tempInt );
			System.out.println( "New Value = " + y.getRegPorts() );
		}
		//y.increaseRegPorts( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current sheep ports is " + y.getSheepPorts() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseSheepPorts( tempInt );
			System.out.println( "New Value = " + y.getSheepPorts() );
		}
		//y.increaseSheepPorts( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current brick ports is " + y.getBrickPorts() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseBrickPorts( tempInt );
			System.out.println( "New Value = " + y.getBrickPorts() );
		}
		//y.increaseBrickPorts( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current ore ports is " + y.getOrePorts() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseOrePorts( tempInt );
			System.out.println( "New Value = " + y.getOrePorts() );
		}
		//y.increaseOrePorts( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current wood ports is " + y.getWoodPorts() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseWoodPorts( tempInt );
			System.out.println( "New Value = " + y.getWoodPorts() );
		}
		//y.increaseWoodPorts( getNegIntFromKeyboard() );
		
		System.out.println( name + "'s current wheat ports is " + y.getWheatPorts() );
		System.out.print( "Adjustment: " );
		tempInt = getNegIntFromKeyboard();
		if ( tempInt != 0 )
		{
			y.increaseWheatPorts( tempInt );
			System.out.println( "New Value = " + y.getWheatPorts() );
		}
		//y.increaseWheatPorts( getNegIntFromKeyboard() );
		
		Serializer.serializePlayer( y );
	}
	
	public static double percent( int a, int b )
	{
		double c;
		c = (double)a / (double)b;
		DecimalFormat df1 = new DecimalFormat("###.#");
		//c = Math.round( c * 100.0 );
		return Double.valueOf( df1.format(c * 100.0 ) );
	}

}
