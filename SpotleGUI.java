import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SpotleGUI extends JFrame implements ActionListener, KeyListener, DocumentListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel gamePanel;
	private JPanel artistPanel;
	private JPanel instructionsPanel;
	private static JTextField searchBar;
	private JPopupMenu suggestionBox;
	private static String guess;
	private static int guessNumber = 0;
	private static JLabel gnLabel;
	private List<String> matches;
	private JScrollPane scroll;
	private static String[] guessArray = new String[11];
	private static ArtistInfoPanel artistIP;
	
	public static void main(String[] args) {
		Spotle.chooseArtist();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotleGUI frame = new SpotleGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SpotleGUI() {
		setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 500, 500);
	    contentPane = new JPanel();
	    contentPane.setBackground(Color.black);
	    setContentPane(contentPane);
	    
	    cardLayout = new CardLayout();
	    contentPane.setLayout(cardLayout);
	    gamePanel = new JPanel();
	    contentPane.add( gamePanel, "game");
	    gamePanel.setBackground(Color.black);
	    gamePanel.setLayout(null);
	    
	    artistPanel = new JPanel();
	    contentPane.add( artistPanel, "artists");
	    artistPanel.setBackground(Color.black);
	    artistPanel.setLayout(null);
	    
	    instructionsPanel = new JPanel();
	    contentPane.add(instructionsPanel, "instructions");
	    instructionsPanel.setBackground(Color.black);
	    instructionsPanel.setLayout(null);
	    
	    JButton gpArtists = new JButton("Artists");
	    gpArtists.setFont(new Font("Verdana", Font.BOLD, 12));
	    gpArtists.setSize(100, 20);
	    gpArtists.setLocation(10, 10);
	    gpArtists.setBackground( new Color(255, 255, 255));
	    gamePanel.add(gpArtists);
	    
	    JButton gpInstructions = new JButton("Instructions");
	    gpInstructions.setFont(new Font("Verdana", Font.BOLD, 12));
	    gpInstructions.setSize(120, 20);
	    gpInstructions.setLocation(355, 10);
	    gpInstructions.setBackground( new Color(255, 255, 255));
	    gamePanel.add(gpInstructions);
	    
	    JLabel title = new JLabel("Spotle");
	    title.setFont(new Font("Verdana", Font.BOLD, 50));
	    title.setHorizontalAlignment(SwingConstants.CENTER);
	    title.setForeground(new Color(0, 128, 0));
	    title.setBounds(150, 10, 200, 80);
	    gamePanel.add(title);
	    
	    gnLabel = new JLabel("Guess " + guessNumber + " of 10");
	    gnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    gnLabel.setForeground(new Color(255, 255, 255));
	    gnLabel.setBounds(275, 77, 100, 13);
	    gamePanel.add(gnLabel);
	    
	    setSearchBar(new JTextField());
	    getSearchBar().setText("Type your guess...");
	    getSearchBar().setBounds(125, 90, 250, 30);
	    getSearchBar().getDocument().addDocumentListener(this);
	    getSearchBar().setColumns(10);
	    gamePanel.add(getSearchBar());
	    
	    suggestionBox = new JPopupMenu();
	    gamePanel.add(suggestionBox);
	   
	    scroll = new JScrollPane( );
	    scroll.setLocation( 125, 130);
	    scroll.setSize(250, 323);
	    scroll.getVerticalScrollBar().setPreferredSize( new Dimension( 10, 10));
	    scroll.getVerticalScrollBar().setUnitIncrement(15);
	    gamePanel.add(scroll);
	   
	    artistIP = new ArtistInfoPanel();
	    artistIP.setPreferredSize( new Dimension( 235 , 1500));
	    scroll.setViewportView(artistIP);
	    getSearchBar().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            guess = getSearchBar().getText();
	        }
	    });
	    getSearchBar().addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER && !getSearchBar().getText().equals("") && !getSearchBar().getText().equals("Type your guess...")) {
	                guess = getSearchBar().getText().trim(); // Ensure guess is updated and trimmed of extra spaces
	                if (ArtistDatabase.isInDatabase(guess)) {
	                    // Handle valid guess
	                    guessNumber++;
	                    Artist guessedArtist = ArtistDatabase.getArtistByName(guess);
	                    updateGuess(guessNumber, guess);
	                    guessArray[guessNumber] = guess;
	                    getSearchBar().setText("");
	                    gnLabel.setText("Guess " + guessNumber + " of 10");
	                    if (guess != null && ArtistDatabase.isInDatabase(guess)) {
	                        artistIP.updateLabels(
	                            guessedArtist.getName(),
	                            guessedArtist.getDebut(),
	                            guessedArtist.getMembers(),
	                            guessedArtist.getPopularity(),
	                            guessedArtist.getGender(),
	                            guessedArtist.getGenre(),
	                            guessedArtist.getNationality()
	                        );
	                    }
	                    // Losing conditions
	                    if (guessArray[10] != null && !guess.equalsIgnoreCase(Spotle.getRandomArtist().getName())) {
	                    	suggestionBox.setVisible(false);
	                        getSearchBar().setEditable(false);
	                        guessNumber = 0;
	                        ResultsFrame ResultsFrame = new ResultsFrame(artistIP);
	                        ResultsFrame.setVisible(true);
	                        ResultsFrame.getResultslbl().setText("You Lost!");
	                    }
	                    // Winning conditions
	                    else if (guess.equalsIgnoreCase(Spotle.getRandomArtist().getName())) {
	                        suggestionBox.setVisible(false);
	                        getSearchBar().setEditable(false);
	                        guessNumber = 0;
	                        ResultsFrame resultsFrame = new ResultsFrame(artistIP);
	                        resultsFrame.setVisible(true);
	                        resultsFrame.getResultslbl().setText("Congratulations");
	                    }
	                } else {
	                    // Handle invalid guess
	                    JOptionPane.showMessageDialog(null, "Sorry, " + guess + " isn't an artist in this database.");
	                    getSearchBar().setText("");
	                }
	                // Reset guess to avoid carrying over the state
	                guess = null;
	            }
	        }
	    });
	    
	    //artist card
	    JButton apGame = new JButton("Game");
	    apGame.setFont(new Font("Verdana", Font.BOLD, 12));
	    apGame.setSize(100, 20);
	    apGame.setLocation(10, 10);
	    apGame.setBackground( new Color(255, 255, 255));
	    artistPanel.add(apGame);
	    
	    JButton apInstructions = new JButton("Instructions");
	    apInstructions.setFont(new Font("Verdana", Font.BOLD, 12));
	    apInstructions.setSize(120, 20);
	    apInstructions.setLocation(355, 10);
	    apInstructions.setBackground( new Color(255, 255, 255));
	    artistPanel.add(apInstructions);
	    
	    JLabel artistLabel = new JLabel("<html>21 Savage<br>Adele<br>Arctic Monkeys<br>Ariana Grande<br>Bad Bunny<br>Benson Boone<br>Beyonce<br>Billie Eilish<br>Bruno Mars<br>Calvin Harris<br>Chris Brown<br>Coldplay<br>Daddy Yankee<br>David Guetta<br>Doja Cat<br>Drake<br>Dua Lipa<br>Ed Sheeran<br>Eminem<br>Future<br>Hozier<br>Imagine Dragons<br>J Balvin<br>Justin Bieber<br>Kanye West</html>");
	    artistLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    artistLabel.setSize(200, 400);
	    artistLabel.setForeground(Color.white);
	    artistLabel.setLocation(10, 50);
	    artistPanel.add(artistLabel);
	    
	    JLabel artistLabel2 = new JLabel("<html>Karol G<br>Katy Perry<br>Kendrick Lamar<br>Khalid<br>Lady Gaga<br>Lana Del Rey<br>Linkin Park<br>Maroon 5<br>Marshmello<br>Metro Boomin<br>Miley Cyrus<br>Olivia Rodrigo<br>OneRepublic<br>Playboi Carti<br>Post Malone<br>Queen<br>Rihanna<br>Sabrina Carpenter<br>Sam Smith<br>Shakira<br>Sia<br>SZA<br>Taylor Swift<br>Travis Scott<br>The Weeknd</html>");
	    artistLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	    artistLabel2.setSize( 200 , 400 );
	    artistLabel2.setLocation( 230 , 50 );
	    artistLabel2.setForeground(Color.white);
	    artistPanel.add(artistLabel2);
	    
	    //instructions
	    JButton ipGame = new JButton("Game");
	    ipGame.setFont(new Font("Verdana", Font.BOLD, 12));
	    ipGame.setSize(100, 20);
	    ipGame.setLocation(10, 10);
	    ipGame.setBackground( new Color(255, 255, 255));
	    instructionsPanel.add(ipGame);
	    
	    JLabel rules = new JLabel("<html>In this Wordle Spinoff, players have 10 attempts to guess the mystery artist.<br><br>1.) Type your guess in the search bar and press the ENTER key to submit it.<br>As you type, you can click on the suggestions to aid you.</html>");
	    rules.setHorizontalAlignment(SwingConstants.CENTER);
	    rules.setForeground(new Color(255, 255, 255));
	    rules.setFont(new Font("Verdana", Font.PLAIN, 11));
	    rules.setSize(466, 75);
	    rules.setLocation(10, 50);
	    
	    JLabel rules2 = new JLabel("<html>2.) From left to right, the 6 categories are debut year, group size,<br> popularity(#1 - #50), gender, genre, and nationality</html>");
	    rules2.setHorizontalAlignment(SwingConstants.CENTER);
	    rules2.setLocation(10, 122);
	    rules2.setFont(new Font("Verdana", Font.PLAIN, 11));
	    rules2.setForeground(new Color(255, 255, 255));
	    rules2.setSize(466, 50);
	    
	    JLabel rules3 = new JLabel("<html><span style='color:rgb(0,128,0); font-weight:bold;'>Green</span> means the categories match that of the mystery artist.<br><span style='color:rgb(139,128,0); font-weight:bold;'>Yellow</span> means it's close.<br><span style='color:rgb(192,192,192); font-weight:bold;'>Gray</span> means it's not equal/isn't close.</html>");
	    rules3.setHorizontalAlignment(SwingConstants.CENTER);
	    rules3.setLocation(10, 265);
	    rules3.setForeground(new Color(255, 255, 255));
	    rules3.setFont(new Font("Verdana", Font.PLAIN, 11));
	    rules3.setSize(466, 75);
	    
	    JLabel rules4 = new JLabel("<html>Blue outline for debut/popularity means they're lower than that of the<br>mystery artist(i.e 2010 < 2012)</html>");
	    rules4.setHorizontalAlignment(SwingConstants.CENTER);
	    rules4.setForeground(new Color(255, 255, 255));
	    rules4.setFont(new Font("Verdana", Font.PLAIN, 11));
	    rules4.setLocation(10, 336);
	    rules4.setSize(466, 50);
	    rules4.setBorder(BorderFactory.createLineBorder( Color.blue, 3));
	    
	    JLabel rules5 = new JLabel("<html>Orange outline for debut/popularity means they're higher than that of the<br>mystery artist(i.e 2012 > 2010)</html>");
	    rules5.setLocation(10, 402);
	    rules5.setForeground(new Color(255, 255, 255));
	    rules5.setFont(new Font("Verdana", Font.PLAIN, 11));
	    rules5.setHorizontalAlignment(SwingConstants.CENTER);
	    rules5.setSize(466, 50);
	    rules5.setBorder(BorderFactory.createLineBorder( Color.orange, 3));
	    
	    JLabel demoDebut = new JLabel("Debut");
	    demoDebut.setFont( new Font("Verdana", Font.BOLD, 15));
	    demoDebut.setHorizontalAlignment(SwingConstants.CENTER);
		demoDebut.setBounds( 128, 195, 65, 25);
		demoDebut.setForeground( Color.white);
		demoDebut.setBackground( new Color(192, 192, 192));
		demoDebut.setOpaque( true );
		
		instructionsPanel.add(rules);
		instructionsPanel.add(rules2);
		instructionsPanel.add(rules3);
		instructionsPanel.add(rules4);
		instructionsPanel.add(rules5);
		instructionsPanel.add(demoDebut);
		
		JLabel demoGender = new JLabel("Gender");
		demoGender.setOpaque(true);
		demoGender.setHorizontalAlignment(SwingConstants.CENTER);
		demoGender.setForeground(Color.WHITE);
		demoGender.setFont(new Font("Verdana", Font.BOLD, 15));
		demoGender.setBackground(Color.LIGHT_GRAY);
		demoGender.setBounds(128, 231, 65, 25);
		instructionsPanel.add(demoGender);
		
		JLabel demoSize = new JLabel("Size");
		demoSize.setOpaque(true);
		demoSize.setHorizontalAlignment(SwingConstants.CENTER);
		demoSize.setForeground(Color.WHITE);
		demoSize.setFont(new Font("Verdana", Font.BOLD, 15));
		demoSize.setBackground(Color.LIGHT_GRAY);
		demoSize.setBounds(200, 195, 65, 25);
		instructionsPanel.add(demoSize);
		
		JLabel demoGenre = new JLabel("Genre");
		demoGenre.setOpaque(true);
		demoGenre.setHorizontalAlignment(SwingConstants.CENTER);
		demoGenre.setForeground(Color.WHITE);
		demoGenre.setFont(new Font("Verdana", Font.BOLD, 15));
		demoGenre.setBackground(Color.LIGHT_GRAY);
		demoGenre.setBounds(200, 231, 65, 25);
		instructionsPanel.add(demoGenre);
		
		JLabel demoPop = new JLabel("Popular");
		demoPop.setOpaque(true);
		demoPop.setHorizontalAlignment(SwingConstants.CENTER);
		demoPop.setForeground(Color.WHITE);
		demoPop.setFont(new Font("Verdana", Font.BOLD, 13));
		demoPop.setBackground(Color.LIGHT_GRAY);
		demoPop.setBounds(272, 195, 65, 25);
		instructionsPanel.add(demoPop);
		
		JLabel demoNation = new JLabel("Nation");
		demoNation.setOpaque(true);
		demoNation.setHorizontalAlignment(SwingConstants.CENTER);
		demoNation.setForeground(Color.WHITE);
		demoNation.setFont(new Font("Verdana", Font.BOLD, 15));
		demoNation.setBackground(Color.LIGHT_GRAY);
		demoNation.setBounds(272, 231, 65, 25);
		instructionsPanel.add(demoNation);
		
		JButton ipArtists = new JButton("Artists");
		ipArtists.setFont(new Font("Verdana", Font.BOLD, 12));
		ipArtists.setBackground(Color.WHITE);
		ipArtists.setBounds(376, 10, 100, 20);
		instructionsPanel.add(ipArtists);
		
	    //button functionality
	    gpArtists.addActionListener( e ->
	    {
	    	cardLayout.show(contentPane, "artists");
	    	gpArtists.setBackground( new Color( 0 , 128 , 0 ));
	    });
	    gpInstructions.addActionListener( e ->
	    {
	    	cardLayout.show(contentPane, "instructions");
	    	gpInstructions.setBackground( new Color( 0 , 128 , 0 ));
	    });
	    apGame.addActionListener( e ->
	    {
	    	cardLayout.show(contentPane, "game");
	    	apGame.setBackground( new Color( 0 , 128 , 0 ));
	    });
	    apInstructions.addActionListener( e ->
	    {
	    	cardLayout.show(contentPane, "instructions");
	    	apInstructions.setBackground( new Color( 0 , 128 , 0 ));
	    });
	    ipGame.addActionListener( e ->
	    {
	    	cardLayout.show(contentPane, "game");
	    	ipGame.setBackground( new Color( 0 , 128 , 0 ));
	    });
	    ipArtists.addActionListener( e ->
	    {
	    	cardLayout.show(contentPane, "artists");
	    	ipArtists.setBackground( new Color( 0 , 128 , 0 ));
	    });
	}
	
	public void updateGuess( int index , String guess )
	{
		if( index >= 0 && index < guessArray.length )
		{
			guessArray[index] = guess;
			//ArtistInfoPanel.updateGuessLabel( guess );
		}
	}
	public void updateSuggestions()
	{
	    String input = getSearchBar().getText().toLowerCase().trim(); //trim() ensures extra spaces aren't accounted for
	   
	   
	    // Hide suggestion box if input is empty
	    if (input.isEmpty())
	    {
	        suggestionBox.setVisible(false);
	        return;
	    }
	    List<String> matches = new ArrayList<String>();
	    // Search for matches
	    for (int i = 0; i < ArtistDatabase.getArtists().size(); i++)
	    {
	        Artist artist = ArtistDatabase.getArtists().get(i);  //gets every artist in database
	        if (artist.getName().toLowerCase().startsWith(input))
	        {
	            matches.add(artist.getName());
	        }
	    }
	  
	    if (!matches.isEmpty())
	    {
	        showSuggestions(matches); //shows if there are matches
	    }
	    else
	    {
	        suggestionBox.setVisible(false);  // hides if no matches
	    }
	  
	    getSearchBar().requestFocusInWindow(); //keeps focus on the search bar so user can keep typing
	}
	public void showSuggestions(List<String> matches) {
	    suggestionBox.removeAll();  // Clear previous suggestions
	    suggestionBox.setVisible(false);
	    for (int i = 0; i < matches.size(); i++)
	    {
	        JMenuItem item = new JMenuItem(matches.get(i));
	        item.addActionListener(new ActionListener()
	        {//if item is clicked, do this
	            public void actionPerformed(ActionEvent e)
	            {
	                getSearchBar().setText(item.getText());  // set selected item as search text
	                suggestionBox.setVisible(false);   // hides suggestion box after selection
	            }
	        });
	        suggestionBox.add(item);
	    }
	    // display under search bar
	    suggestionBox.show(getSearchBar(), 0, getSearchBar().getHeight());
	}
	public static String getGuess() {
		return guess;
	}
	public static void setGuess(String guess) {
		guess = guess;
	}
	public static int getGuessNumber() {
		return guessNumber;
	}
	public static void setGuessNumber(int guessNumber) {
		guessNumber = guessNumber;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		updateSuggestions();
		
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		updateSuggestions();
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		updateSuggestions();
		
	}
	public static String[] getGuessArray() {
		return guessArray;
	}
	public void setGuessArray(String[] guessArray) {
		SpotleGUI.guessArray = guessArray;
	}
	public static JTextField getSearchBar() {
		return searchBar;
	}
	public void setSearchBar(JTextField searchBar) {
		this.searchBar = searchBar;
	}
	public static JLabel getGnLabel() {
		return gnLabel;
	}
	public static void setGnLabel(JLabel gnLabel) {
		SpotleGUI.gnLabel = gnLabel;
	}
}

