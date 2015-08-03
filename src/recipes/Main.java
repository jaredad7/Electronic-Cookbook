package recipes;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
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
	/********************Main Variables**********************/
	
	private static final JFrame frame = new JFrame("Electronic Cookbook");
	
	//Create the frame components
	private static final JTextField titleField = new JTextField(30);
	private static final JTextField category = new JTextField(30);
	private static final JButton searchByCategory = new JButton("Serach by Category");
	private static final JButton searchByTitle = new JButton("Search by Title");
	private static final JButton save =  new JButton("Save");
	private static final JButton delete =  new JButton("Delete");
	private static final JTextArea ingredientsField = new JTextArea(10, 80);
	private static final JTextArea instructionsField = new JTextArea(15, 80);
	private static final String FILENAME = "cookbook.sav";
	
	//Cookbook info
	private static Cookbook cookbook = new Cookbook();
	
	/*******************************Main Method*************************/
	
	public static void main(String args[])
	{		
		
		frameSetup();
		
		cookbook = loadBook();
		Recipe rec = cookbook.getRecipe("Recipe Title Here");
		
		titleField.setText(rec.getTitle());
		ingredientsField.setText(rec.getIngredients());
		instructionsField.setText(rec.getInstructions());
		category.setText(rec.getCategory());
		
		//Add ActionListeners for buttons
		addSearch();
		addSave();
		addDelete();
	}

	/*******************HELPER METHODS**************************************/
	
	private static void frameSetup()
	{
		//Setup ScrollPanes
		JScrollPane scrollPane1 = new JScrollPane(ingredientsField); 
		ingredientsField.setEditable(true);
		JScrollPane scrollPane2 = new JScrollPane(instructionsField); 
		instructionsField.setEditable(true);
		
		//Add the components to the frame
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		frame.add(titleField);
		frame.add(searchByTitle);
		frame.add(save);
		frame.add(delete);
		frame.add(category);
		frame.add(searchByCategory);
		frame.add(scrollPane1);
		frame.add(scrollPane2);
		
		//Final frame setup
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 100);
		frame.setSize(900, 475);
		frame.setVisible(true);
	}

	//Search functionality
	private static void addSearch()
	{
		searchByTitle.addActionListener(new ActionListener()
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
					category.setText("");
				}
				else
				{
					Recipe r = cookbook.getRecipe(title);
					titleField.setText(r.getTitle());
					ingredientsField.setText(r.getIngredients());
					instructionsField.setText(r.getInstructions());
					category.setText(r.getCategory());
				}
			}
			
		});
	}
	
	//Save functionality
	private static void addSave()
	{
		save.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Save Recipe to cookbook
				cookbook.addRecipe(titleField.getText(), ingredientsField.getText(),
						instructionsField.getText(), category.getText());
				
				//Write cookbook to disk as .sav file
				try 
				{
					FileOutputStream fos = new FileOutputStream(FILENAME);
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
	
	//Delete functionality
	private static void addDelete()
	{
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cookbook.delRecipe(titleField.getText());
				
				//Write cookbook to disk as .sav file
				try 
				{
					FileOutputStream fos = new FileOutputStream(FILENAME);
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
	
	private static Cookbook loadBook()
	{
		//Create CookBook from file or new one and display sample recipe
		File book = new File(FILENAME);
		Cookbook cookbook = null;
		
		//If the file exists
		if(book.exists())
		{
			try 
			{
				FileInputStream fis = new FileInputStream(book);
				@SuppressWarnings("resource")
				ObjectInputStream ois = new ObjectInputStream(fis);
				cookbook = (Cookbook) ois.readObject();
			} catch (Exception e1) 
			{
				e1.printStackTrace();
			}

		}
		else
		{
			cookbook = new Cookbook();
		}
		
		return cookbook;
	}
}
