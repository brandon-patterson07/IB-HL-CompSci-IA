public class Artist {
	private String name;
	private int debut;
	private int members;
	private int popularity;
	private String gender;
	private String genre;
	private String nationality;
	
	public Artist(String name, int debut, int members, int popularity, String gender, String genre, String nationality)
	{
		super();
		this.name = name;
		this.debut = debut;
		this.members = members;
		this.popularity = popularity;
		this.gender = gender;
		this.genre = genre;
		this.nationality = nationality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDebut() {
		return debut;
	}
	public void setDebut(int debut) {
		this.debut = debut;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String toString()
	{
		return "Artist Name: " + name + "\nDebut: " + debut + "\nMembers: " + members + "\nPopularity: #" + popularity + "\nGender: " + gender + "\nGenre: " + genre + "\nNationality: " + nationality;
	}
}

