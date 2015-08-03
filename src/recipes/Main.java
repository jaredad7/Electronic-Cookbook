package recipes;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//Handles the user interface for the cookbook
public class Main 
{
	public static void main(String args[])
	{
		//Create the frame and components
		JFrame frame = new JFrame("The Dembrun Family Electronic Cookbook System");
		final JTextField titleField = new JTextField(30);
		JButton search = new JButton("Search");
		JButton save =  new JButton("Save");
		JButton delete =  new JButton("Delete");
		
		//Ingredients Field
		final JTextArea ingredientsField = new JTextArea(10, 80);
		JScrollPane scrollPane1 = new JScrollPane(ingredientsField); 
		ingredientsField.setEditable(true);
		
		//InstructionsField
		final JTextArea instructionsField = new JTextArea(15, 80);
		JScrollPane scrollPane2 = new JScrollPane(instructionsField); 
		instructionsField.setEditable(true);
		
		//Add the components to the frame
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		frame.add(titleField);
		frame.add(search);
		frame.add(save);
		frame.add(delete);
		frame.add(scrollPane1);
		frame.add(scrollPane2);
		
		//Final frame setup
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 100);
		frame.setSize(900, 475);
		frame.setVisible(true);
		
		//Create CookBook from file or new one and display sample recipe
		File book = new File("cookbook.sav");
		Cookbook input = null;
		//If the file exists
		if(book.exists())
		{
			try 
			{
				FileInputStream fis = new FileInputStream(book);
				ObjectInputStream ois = new ObjectInputStream(fis);
				input = (Cookbook) ois.readObject();
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}

		}
		else
		{
			input = new Cookbook();
		}
		//The cookbook needs to be final in order to be accessed inside of the
		//ActionListener functions
		final Cookbook cookbook = input;
		Recipe rec = cookbook.getRecipe("Recipe Title Here");
		
		titleField.setText(rec.getTitle());
		ingredientsField.setText(rec.getIngredients());
		instructionsField.setText(rec.getInstructions());
		
		//Add ActionListeners for buttons
		//Search functionality
		search.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String title = titleField.getText();
				if(title.equals(""))
				{
					ArrayList<String> titles = cookbook.getRecipeTitles();
					String titleDisplay = "Available titles: ";
					for(String s : titles)
					{
						titleDisplay += (s + ", ");
					}
					ingredientsField.setText(titleDisplay);
					instructionsField.setText("");
				}
				else
				{
					Recipe r = cookbook.getRecipe(title);
					titleField.setText(r.getTitle());
					ingredientsField.setText(r.getIngredients());
					instructionsField.setText(r.getInstructions());
				}
			}
			
		});
		
		//Save functionality
		save.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Save Recipe to cookbook
				cookbook.addRecipe(titleField.getText(), ingredientsField.getText(),
						instructionsField.getText());
				
				//Write cookbook to disk as .sav file
				try 
				{
					FileOutputStream fos = new FileOutputStream("cookbook.sav");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(cookbook);
					fos.close();
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
			
		});
		//Delete functionality
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cookbook.delRecipe(titleField.getText());
				
				//Write cookbook to disk as .sav file
				try 
				{
					FileOutputStream fos = new FileOutputStream("cookbook.sav");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(cookbook);
					fos.close();
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}				
			}
			
		});
	}
}
