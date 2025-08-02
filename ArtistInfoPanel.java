import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
public class ArtistInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel[] names = new JLabel[10];
	private JLabel[] debuts = new JLabel[10];
	private JLabel[] sizes = new JLabel[10];
	private JLabel[] pops = new JLabel[10];
	private JLabel[] genders = new JLabel[10];
	private JLabel[] genres = new JLabel[10];
	private JLabel[] nations = new JLabel[10];
	
	public ArtistInfoPanel() {
		setForeground(new Color(192, 192, 192));
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		setSize( new Dimension(235, 1500));
		
		for( int i = 0; i < names.length; i++)
		{
			
			names[i] = new JLabel( "No name yet..." );
			names[i].setFont( new Font("Verdana", Font.BOLD, 15));
			names[i].setForeground(Color.white);
			names[i].setHorizontalAlignment(SwingConstants.LEFT);
			names[i].setBounds(50, 5 + 150*i, 185, 30 );
			if( i == 0 )
			{
				names[i].setVisible(true);
			}
			else
			{
				names[i].setVisible(false);
			}
			add(names[i]);
			
			debuts[i] = new JLabel( "Debut" );
			debuts[i].setFont( new Font("Verdana", Font.BOLD, 15));
			debuts[i].setHorizontalAlignment(SwingConstants.CENTER);
			debuts[i].setBounds( 10, 45 + 150*i, 65, 25);
			debuts[i].setForeground( new Color(255, 255, 255));
			debuts[i].setBackground( new Color(192, 192, 192));
			debuts[i].setOpaque( true );
			if( i == 0 )
			{
				debuts[i].setVisible(true);
			}
			else
			{
				debuts[i].setVisible(false);
			}
			add(debuts[i]);
			
			sizes[i] = new JLabel( "Size" );
			sizes[i].setFont( new Font("Verdana", Font.BOLD, 15));
			sizes[i].setHorizontalAlignment(SwingConstants.CENTER);
			sizes[i].setBounds( 85, 45 + 150*i, 65, 25);
			sizes[i].setForeground( new Color(255, 255, 255));
			sizes[i].setBackground( new Color(192, 192, 192));
			sizes[i].setOpaque( true );
			if( i == 0 )
			{
				sizes[i].setVisible(true);
			}
			else
			{
				sizes[i].setVisible(false);
			}
			add(sizes[i]);
			
			pops[i] = new JLabel( "Popular" );
			pops[i].setFont( new Font("Verdana", Font.BOLD, 15));
			pops[i].setHorizontalAlignment(SwingConstants.CENTER);
			pops[i].setBounds( 160, 45 + 150*i, 65, 25);
			pops[i].setForeground( new Color(255, 255, 255));
			pops[i].setBackground( new Color(192, 192, 192));
			pops[i].setOpaque( true );
			if( i == 0 )
			{
				pops[i].setVisible(true);
			}
			else
			{
				pops[i].setVisible(false);
			}
			add(pops[i]);
			
			genders[i] = new JLabel( "Gender" );
			genders[i].setFont( new Font("Verdana", Font.BOLD, 15));
			genders[i].setHorizontalAlignment(SwingConstants.CENTER);
			genders[i].setBounds( 10, 80 + 150*i, 65, 25);
			genders[i].setForeground( new Color(255, 255, 255));
			genders[i].setBackground( new Color(192, 192, 192));
			genders[i].setOpaque( true );
			if( i == 0 )
			{
				genders[i].setVisible(true);
			}
			else
			{
				genders[i].setVisible(false);
			}
			add(genders[i]);
			
			genres[i] = new JLabel( "Genre" );
			genres[i].setFont( new Font("Verdana", Font.BOLD, 15));
			genres[i].setHorizontalAlignment(SwingConstants.CENTER);
			genres[i].setBounds( 85, 80 + 150*i, 65, 25);
			genres[i].setForeground( new Color(255, 255, 255));
			genres[i].setBackground( new Color(192, 192, 192));
			genres[i].setOpaque( true );
			if( i == 0 )
			{
				genres[i].setVisible(true);
			}
			else
			{
				genres[i].setVisible(false);
			}
			add(genres[i]);
			
			nations[i] = new JLabel("Nation");
			nations[i].setFont( new Font("Verdana", Font.BOLD, 15));
			nations[i].setHorizontalAlignment(SwingConstants.CENTER);
			nations[i].setBounds( 160, 80 + 150*i, 65, 25);
			nations[i].setForeground( new Color(255, 255, 255));
			nations[i].setBackground( new Color(192, 192, 192));
			nations[i].setOpaque( true );
			if( i == 0 )
			{
				nations[i].setVisible(true);
			}
			else
			{
				nations[i].setVisible(false);
			}
			add(nations[i]);
		}
	}
	
		public void updateLabels( String name, int debut, int size, int popularity, String gender, String genre, String nationality )
		{

			names[SpotleGUI.getGuessNumber() - 1].setText(name);
			debuts[SpotleGUI.getGuessNumber() - 1].setText(String.valueOf(debut));
			sizes[SpotleGUI.getGuessNumber() - 1].setText(String.valueOf(size));
			pops[SpotleGUI.getGuessNumber() - 1].setText(String.valueOf(popularity));
			genders[SpotleGUI.getGuessNumber() - 1].setText(gender);
			genres[SpotleGUI.getGuessNumber() - 1].setText(genre);
			nations[SpotleGUI.getGuessNumber() - 1].setText(nationality);
			
			//creates the game aspect with colors
			//debuts
			if( Integer.parseInt(debuts[SpotleGUI.getGuessNumber() - 1].getText()) == Spotle.getRandomArtist().getDebut())
			{
				debuts[SpotleGUI.getGuessNumber() - 1].setBackground( new Color( 0, 128, 0));
			}
			if (Math.abs(Integer.parseInt(debuts[SpotleGUI.getGuessNumber() - 1].getText()) - Spotle.getRandomArtist().getDebut()) <= 5) 
			{
			    debuts[SpotleGUI.getGuessNumber() - 1].setBackground(new Color(139, 128, 0));
			}
			if( Integer.parseInt(debuts[SpotleGUI.getGuessNumber() - 1].getText()) < Spotle.getRandomArtist().getDebut())
			{
				debuts[SpotleGUI.getGuessNumber() - 1].setBorder(BorderFactory.createLineBorder( Color.blue, 3));
			}
			if( Integer.parseInt(debuts[SpotleGUI.getGuessNumber() - 1].getText()) > Spotle.getRandomArtist().getDebut())
			{
				debuts[SpotleGUI.getGuessNumber() - 1].setBorder(BorderFactory.createLineBorder(Color.orange, 3));
			}
			
			//size
			if( Integer.parseInt(sizes[SpotleGUI.getGuessNumber() - 1].getText()) == Spotle.getRandomArtist().getMembers() )
			{
				sizes[SpotleGUI.getGuessNumber() - 1].setBackground( new Color( 0, 128, 0));
			}
			
			//popularity
			if( Integer.parseInt(pops[SpotleGUI.getGuessNumber() - 1].getText()) == Spotle.getRandomArtist().getPopularity() )
			{
				pops[SpotleGUI.getGuessNumber() - 1].setBackground( new Color( 0, 128, 0));
			}
			
			if( (Integer.parseInt(pops[SpotleGUI.getGuessNumber() - 1].getText()) - Spotle.getRandomArtist().getPopularity()) <= 5 || (Integer.parseInt(pops[SpotleGUI.getGuessNumber() - 1].getText()) - Spotle.getRandomArtist().getPopularity()) >= 5 )
			{
				pops[SpotleGUI.getGuessNumber() - 1].setBackground(new Color(139, 128, 0));
			}
			if( Integer.parseInt(pops[SpotleGUI.getGuessNumber() - 1].getText()) > Spotle.getRandomArtist().getPopularity())
			{
				pops[SpotleGUI.getGuessNumber() - 1].setBorder(BorderFactory.createLineBorder( Color.blue, 3));
			}
			if( Integer.parseInt(pops[SpotleGUI.getGuessNumber() - 1].getText()) < Spotle.getRandomArtist().getPopularity())
			{
				pops[SpotleGUI.getGuessNumber() - 1].setBorder(BorderFactory.createLineBorder(Color.orange, 3));
			}
			//gender
			if( genders[SpotleGUI.getGuessNumber() - 1].getText().equals(Spotle.getRandomArtist().getGender()))
			{
				genders[SpotleGUI.getGuessNumber() - 1].setBackground( new Color( 0, 128, 0));
			}
			
			if( genres[SpotleGUI.getGuessNumber() - 1].getText().equals(Spotle.getRandomArtist().getGenre()))
			{
				genres[SpotleGUI.getGuessNumber() - 1].setBackground( new Color( 0, 128, 0));
			}
			
			if( nations[SpotleGUI.getGuessNumber() - 1].getText().equals(Spotle.getRandomArtist().getNationality()))
			{
				nations[SpotleGUI.getGuessNumber() - 1].setBackground( new Color( 0, 128, 0));
			}
			
			//font changes for bigger words
			if(nations[SpotleGUI.getGuessNumber() - 1].getText().equals("Colombia") || nations[SpotleGUI.getGuessNumber() - 1].getText().equals("Barbados"))
			{
				nations[SpotleGUI.getGuessNumber() - 1].setFont(new Font("Verdana", Font.BOLD, 12));
			}
			if( nations[SpotleGUI.getGuessNumber() - 1].getText().equals("Puerto Rico") )
			{
				nations[SpotleGUI.getGuessNumber() - 1].setFont(new Font("Verdana", Font.BOLD, 9));
			}
			if( genders[SpotleGUI.getGuessNumber() - 1].getText().equals("Nonbinary"))
			{
				genders[SpotleGUI.getGuessNumber() - 1].setFont(new Font("Verdana", Font.BOLD, 10));
			}
			if( genres[SpotleGUI.getGuessNumber() - 1].getText().equals("Electronic") || genres[SpotleGUI.getGuessNumber() - 1].getText().equals("Alternative") )
			{
				genres[SpotleGUI.getGuessNumber() - 1].setFont(new Font("Verdana", Font.BOLD, 12));
			}
			
			//shows user info once guess number is met
			if (!"No name yet...".equals(names[SpotleGUI.getGuessNumber() - 1].getText().trim()))
			{
			    names[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			    debuts[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			    sizes[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			    pops[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			    genders[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			    genres[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			    nations[SpotleGUI.getGuessNumber() - 1].setVisible(true);
			}
		}
		
		public void resetInfo() {
		    // Reset all labels and UI components to their default state
		    for (int i = 1; i < names.length; i++) {
		        names[i].setVisible(false);
		        debuts[i].setVisible(false);
		        sizes[i].setVisible(false);
		        pops[i].setVisible(false);
		        genders[i].setVisible(false);
		        genres[i].setVisible(false);
		        nations[i].setVisible(false);
		    }
		    names[0].setText("No name yet...");
		    debuts[0].setText("Debut");
		    debuts[0].setBackground( new Color( 195, 195, 195 ));
	        debuts[0].setBorder(null);
	        sizes[0].setText("Size");
	        sizes[0].setBackground( new Color( 195, 195, 195 ));
	        sizes[0].setBorder(null);
	        pops[0].setText("Popularity");
	        pops[0].setBackground( new Color( 195, 195, 195 ));
	        pops[0].setBorder(null);
	        genders[0].setText("Gender");
	        genders[0].setBackground( new Color( 195, 195, 195 ));
	        genres[0].setText("Genre");
	        genres[0].setBackground( new Color( 195, 195, 195 ));
	        nations[0].setText("Nationality");
	        nations[0].setBackground( new Color( 195, 195, 195 ));
		    revalidate();
		    repaint();
		}
	}