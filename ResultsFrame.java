import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ResultsFrame extends JFrame {
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JLabel artistImage;
   private JLabel resultslbl;
   private static boolean restart = false;
   
   public static void main(String[] args) {
       EventQueue.invokeLater(new Runnable() {
           public void run() {
               try {
            	   ArtistInfoPanel artistInfoPanel = new ArtistInfoPanel();
                   ResultsFrame frame = new ResultsFrame(artistInfoPanel);
                   frame.setVisible(true);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });
   }
   public ResultsFrame(ArtistInfoPanel artistInfoPanel) {
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(100, 100, 500, 500);
       contentPane = new JPanel();
       contentPane.setBackground(new Color(0, 0, 0));
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null); // Use absolute layout
       
       resultslbl = new JLabel("Congratulations!");
       resultslbl.setHorizontalAlignment(SwingConstants.CENTER);
       resultslbl.setFont(new Font("Verdana", Font.BOLD, 30));
       resultslbl.setForeground(new Color(255, 255, 255));
       resultslbl.setBounds(100, 10, 300, 46); // Manually set bounds
       contentPane.add(resultslbl);
       // Panel to hold the image
       JPanel panel = new JPanel();
       panel.setBackground(new Color(0, 0, 0));
       panel.setBounds(150, 111, 200, 200); // Manually set bounds for the panel
       contentPane.add(panel);
       panel.setLayout(null); // No layout manager for the panel
       artistImage = new JLabel();
       artistImage.setBounds(0, 0, 250, 250); // Set initial bounds for the image
       panel.add(artistImage); // Add the image label to the panel
       // Call this method when initializing to show the first artist image
       updateImage();
       centerImage(panel);
      
       JLabel lblTheArtistWas = new JLabel("The artist was...");
       lblTheArtistWas.setHorizontalAlignment(SwingConstants.CENTER);
       lblTheArtistWas.setForeground(new Color(255, 255, 255));
       lblTheArtistWas.setFont(new Font("Tahoma", Font.PLAIN, 15));
       lblTheArtistWas.setBounds(190, 86, 120, 14);
       contentPane.add(lblTheArtistWas);
      
       JLabel nameLabel = new JLabel(Spotle.getRandomArtist().getName());
       nameLabel.setForeground(new Color(255, 255, 255));
       nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
       nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       nameLabel.setBounds(150, 322, 200, 25);
       contentPane.add(nameLabel);
      
       JButton replayButtton = new JButton("Play Again");
       replayButtton.setForeground(Color.WHITE);
      
       replayButtton.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e)
       	{
       		restart = true;
       		System.out.println(restart);
       		dispose();
       		setFocusable(false);
       		
       		//resets conditions of initial panel
       		SpotleGUI.getSearchBar().setEditable(true);
       		SpotleGUI.setGuessNumber(0);
       		SpotleGUI.getGnLabel().setText("Guess " + SpotleGUI.getGuessNumber() + " of 10");
       		Spotle.chooseArtist();
       		if( restart )
       		{
       			artistInfoPanel.resetInfo();
       		}
       	}
       });
       replayButtton.setBackground(new Color(0, 64, 0));
       replayButtton.setFont(new Font("Verdana", Font.BOLD, 13));
       replayButtton.setBounds(190, 357, 120, 35);
       contentPane.add(replayButtton);
   }
   public void updateImage() {
       // Load image and set it to the label
       Image image = getImage();
       if (image != null) {
           // Resize the image if necessary (adjust width and height if needed)
           ImageIcon icon = new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
           artistImage.setIcon(icon);
       } else {
           // Set a placeholder or error message
           artistImage.setText("Image not found");
       }
       contentPane.revalidate(); // Ensure the layout is updated
       contentPane.repaint(); // Repaint to show the updated image
   }
   public Image getImage() {
       try {
           // Get the name of the artist
           String artistName = Spotle.getRandomArtist().getName();
           if (artistName == null) {
               System.out.println("Error: Artist name is null.");
               return null;
           }
           // Build the image path dynamically using the artist's name
           String imagePath = "/images/" + artistName.toLowerCase() + ".jpg";
           // Load the image from the resources folder
           return ImageIO.read(getClass().getResource(imagePath));
       }
       catch (IOException e)
       {
           e.printStackTrace();
           return null; // Return null if there's an issue loading the image
       }
   }
   private void centerImage(JPanel panel) {
       // Get the panel's width and height
       int panelWidth = panel.getWidth();
       int panelHeight = panel.getHeight();
       // Get the image label's width and height
       int imageWidth = artistImage.getIcon().getIconWidth();
       int imageHeight = artistImage.getIcon().getIconHeight();
       // Calculate x and y position to center the image within the panel
       int x = (panelWidth - imageWidth) / 2;
       int y = (panelHeight - imageHeight) / 2;
       // Set the position of the image
       artistImage.setBounds(x, y, imageWidth, imageHeight);
   }
public static boolean isRestart() {
	return restart;
}
public void setRestart(boolean restart) {
	ResultsFrame.restart = restart;
}
public JLabel getArtistImage() {
	return artistImage;
}
public void setArtistImage(JLabel artistImage) {
	this.artistImage = artistImage;
}
public JLabel getResultslbl() {
	return resultslbl;
}
public void setResultslbl(JLabel resultslbl) {
	this.resultslbl = resultslbl;
}
}



