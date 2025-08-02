import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ArtistDatabase {
	private static boolean inDatabase = true;
	private static ArrayList<Artist> artists  = new ArrayList<>();
	static
	{
		artists.add(new Artist("The Weeknd", 2011, 1, 1, "Male", "Pop", "Canada"));
		artists.add(new Artist("Bruno Mars", 2010, 1, 2, "Male", "Pop", "US"));
		artists.add(new Artist("Billie Eilish", 2016, 1, 3, "Female", "Pop", "US"));
		artists.add(new Artist("Lady Gaga", 2007, 1, 4, "Female", "Pop", "US"));
		artists.add(new Artist("Coldplay", 2000, 4, 5, "Male", "Alternative", "UK"));
		artists.add(new Artist("Taylor Swift", 2006, 1, 6, "Female", "Pop", "US"));
		artists.add(new Artist("Rihanna", 2005, 1, 7, "Female", "Pop", "Barbados"));
		artists.add(new Artist("Sabrina Carpenter", 2015, 1, 8, "Female", "Pop", "US"));
		artists.add(new Artist("Justin Bieber", 2009, 1, 9, "Male", "Pop", "Canada"));
		artists.add(new Artist("Ariana Grande", 2010, 1, 10, "Female", "Pop", "US"));
		artists.add(new Artist("Eminem", 1999, 1, 11, "Male", "Hip Hop", "US"));
		artists.add(new Artist("Post Malone", 2016, 1, 12, "Male", "Pop", "US"));
		artists.add( new Artist( "David Guetta", 2002, 1, 13, "Male", "Electronic", "France" ));
		artists.add( new Artist( "Ed Sheeran", 2011, 1, 14, "Male", "Pop", "UK" ));
		artists.add( new Artist( "Drake", 2009, 1, 15, "Male", "Hip Hop", "Canada" ));
		artists.add( new Artist( "Travis Scott", 2015, 1, 16, "Male", "Hip Hop", "US" ));
		artists.add( new Artist( "Kanye West", 2004, 1, 17, "Male", "Hip Hop", "US" ));
		artists.add( new Artist( "SZA", 2014, 1, 18, "Female", "RnB", "US" ));
		artists.add( new Artist( "Calvin Harris", 2007, 1, 19, "Male", "Electronics", "UK" ));
		artists.add( new Artist( "Dua Lipa", 2017, 1, 20, "Female", "Pop", "UK" ));
		artists.add( new Artist( "Shakira", 1995, 1, 21, "Female", "Pop", "Colombia" ));
		artists.add( new Artist( "Kendrick Lamar", 2011, 1, 22, "Male", "Hip Hop", "US" ));
		artists.add( new Artist( "Bad Bunny", 2018, 1, 23, "Male", "Latin", "Puerto Rico" ));
		artists.add( new Artist( "Maroon 5", 2002, 7, 24, "Male", "Pop", "US" ));
		artists.add( new Artist( "Imagine Dragons", 2012, 4, 25, "Male", "Alternative", "US" ));
		artists.add( new Artist( "Lana Del Rey", 2012, 1, 26, "Female", "Alternative", "US" ));
		artists.add( new Artist( "Adele", 2008, 1, 27, "Female", "Pop", "UK" ));
		artists.add( new Artist( "Katy Perry", 2008, 1, 28, "Female", "Pop", "US" ));
		artists.add( new Artist( "Karol G", 2017, 1, 29, "Female", "Latin", "Colombia" ));
		artists.add( new Artist( "Linkin Park", 2000, 6, 30, "Male", "Rock", "US" ));
		artists.add( new Artist( "Marshmello", 2016, 1, 31, "Male", "Electronic", "US" ));
		artists.add( new Artist( "Sia", 2003, 1, 32, "Female", "Pop", "Australia" ));
		artists.add( new Artist( "Miley Cyrus", 2007, 1, 33, "Female", "Pop", "US" ));
		artists.add( new Artist( "Future", 2012, 1, 34, "Male", "Hip Hop", "US" ));
		artists.add( new Artist( "Playboi Carti", 2017, 1, 35, "Male", "Hip Hop", "US" ));
		artists.add( new Artist( "J Balvin", 2012, 1, 36, "Male", "Latin", "Colombia" ));
		artists.add( new Artist( "Beyonce", 2002, 1, 37, "Female", "Pop", "US" ));
		artists.add( new Artist( "Arctic Monkeys", 2006, 4, 38, "Male", "Alternative", "UK" ));
		artists.add( new Artist( "OneRepublic", 2007, 6, 39, "Male", "Pop", "US" ));
		artists.add( new Artist( "Khalid", 2017, 1, 40, "Male", "RnB", "US" ));
		artists.add( new Artist( "Daddy Yankee", 2002, 1, 41, "Male", "Latin", "Puerto Rico" ));
		artists.add( new Artist( "Queen", 1970, 2, 42, "Male", "Rock", "UK" ));
		artists.add( new Artist( "Sam Smith", 2008, 1, 43, "Nonbinary", "Pop", "UK" ));
		artists.add( new Artist( "Hozier", 2008, 1, 44, "Male", "Folk", "Ireland" ));
		artists.add( new Artist( "Metro Boomin", 2009, 1, 45, "Male", "Hip Hop", "US" ));
		artists.add( new Artist( "Doja Cat", 2012, 1, 46, "Female", "Hip Hop", "US" ));
		artists.add( new Artist( "21 Savage", 2013, 1, 47, "Male", "Hip Hop", "UK" ));
		artists.add( new Artist( "Benson Boone", 2021, 1, 48, "Male", "Pop", "US" ));
		artists.add( new Artist( "Chris Brown", 2002, 1, 49, "Male", "RnB", "US" ));
		artists.add( new Artist( "Olivia Rodrigo", 2019, 1, 50, "Female", "Pop", "US" ));
	}
	public static ArrayList<Artist> getArtists() {
		return artists;
	}
	public static void setArtists(ArrayList<Artist> artists) {
		ArtistDatabase.artists = artists;
	}
	
	public static Artist getArtistByName( String name )
	{
		for( Artist artist : artists)
		{
			if( artist.getName().equalsIgnoreCase(name))
			{
				inDatabase = true;
				return artist;
			}
		}
		inDatabase = false;
		return null; //returns null if no artists are found
	}
	public static boolean isInDatabase(String artistName) {
       for (Artist artist : artists) {
           if (artist.getName().equalsIgnoreCase(artistName)) {
               return true;
           }
       }
       return false;
   }
	public void setInDatabase(boolean inDatabase) {
		ArtistDatabase.inDatabase = inDatabase;
	}
}