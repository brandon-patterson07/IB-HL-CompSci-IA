public class Spotle {
	private static Artist randomArtist;
	
	public static void chooseArtist()
	{
		if( ArtistDatabase.getArtists().size() > 0 )
			{
				int random = (int)(Math.random() * ArtistDatabase.getArtists().size());
				randomArtist = ArtistDatabase.getArtists().get( random );
			}
			else
			{
				System.out.println( "Sorry, no artists available at this time." );
			}
		}

	public static Artist getRandomArtist() {
		return randomArtist;
	}
	public void setRandomArtist(Artist randomArtist) {
		Spotle.randomArtist = randomArtist;
	}
	public static void main(String[] args) {
		Spotle game = new Spotle();
		Spotle.chooseArtist();
	}
}