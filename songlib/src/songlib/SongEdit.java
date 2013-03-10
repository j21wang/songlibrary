package songlib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author Joyce
 *
 */
public class SongEdit extends JPanel{
	
	SongLib sL;
	Writer output;
	
	boolean addNew = false;
	
	JButton add = new JButton("Create New");
	JButton del = new JButton("Delete Song");
	JButton done = new JButton("Done");
	
	public SongEdit(SongLib sL) throws IOException{
		
		this.sL = sL;
		AddButtons();
		DeleteListener();
		AddListener();
		DoneListener();
		sL.error.setText(" ");
		if(sL.model.isEmpty()){
			del.setEnabled(false);
			done.setEnabled(false);
			sL.songDetail.songField.setEditable(false);
			sL.songDetail.artistField.setEditable(false);
			sL.songDetail.albumField.setEditable(false);
			sL.songDetail.yearField.setEditable(false);
			
		}
		
		if(sL.list.isSelectionEmpty()){
			
			sL.songDetail.songField.setEditable(false);
			sL.songDetail.artistField.setEditable(false);
			sL.songDetail.albumField.setEditable(false);
			sL.songDetail.yearField.setEditable(false);
			
		}
		
	}
	
	protected void AddButtons(){
		
		add(add);
		add(del);
		add(done);
		
	}
	
	protected void WriteToFile() throws IOException{	
		
		try {
			FileWriter writer = new FileWriter("songs.txt",true);
			
			for(int j=0; j<sL.artist.size(); j++){
				
				writer.write((String) sL.model.get(j));
				System.out.println(sL.model.get(j));
				writer.append("\t");
				writer.append(sL.artist.get(j));
				System.out.println(sL.artist.get(j));
				writer.append("\t");
				writer.append(sL.album.get(j));
				System.out.println(sL.album.get(j));
				writer.append("\t");
				writer.append(sL.year.get(j));
				System.out.println(sL.year.get(j));
				writer.append("\n");
								
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void DeleteListener() throws IOException{
		
			del.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				add.setEnabled(true);
				sL.error.setText(" ");
				sL.artist.remove(sL.i);
				sL.album.remove(sL.i);
				sL.year.remove(sL.i);
				sL.model.remove(sL.i);
				sL.songDetail.songField.setText("");
				sL.songDetail.artistField.setText("");
				sL.songDetail.albumField.setText("");
				sL.songDetail.yearField.setText("");
				sL.error.setText(" ");
				
				if(!sL.artist.isEmpty()) {
					sL.list.setSelectedIndex(sL.i+1);
				} else {
					del.setEnabled(false);
					done.setEnabled(false);
				}
				
				if(sL.list.isSelectionEmpty()){
					
					sL.songDetail.songField.setEditable(false);
					sL.songDetail.artistField.setEditable(false);
					sL.songDetail.albumField.setEditable(false);
					sL.songDetail.yearField.setEditable(false);
					
				}

				try {
					FileWriter writer = new FileWriter("songs.txt",false);
					for(int j=0; j<sL.artist.size(); j++){
						
						writer.write((String) sL.model.get(j));
						System.out.println(sL.model.get(j));
						writer.append("\t");
						writer.append(sL.artist.get(j));
						System.out.println(sL.artist.get(j));
						writer.append("\t");
						writer.append(sL.album.get(j));
						System.out.println(sL.album.get(j));
						writer.append("\t");
						writer.append(sL.year.get(j));
						System.out.println(sL.year.get(j));
						writer.append("\n");
										
					}

					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected void AddListener() throws IOException{
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
			
				sL.list.clearSelection();
				del.setEnabled(false);
				add.setEnabled(false);
				done.setEnabled(true);
				addNew = true;
				sL.error.setText(" ");
				
				sL.songDetail.songField.setText("");
				sL.songDetail.artistField.setText("");
				sL.songDetail.albumField.setText("");
				sL.songDetail.yearField.setText("");
				
				sL.songDetail.songField.setEditable(true);
				sL.songDetail.artistField.setEditable(true);
				sL.songDetail.albumField.setEditable(true);
				sL.songDetail.yearField.setEditable(true);
			
			}
		});
	}
	
	protected void DoneListener(){
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				String enteredSong = sL.songDetail.songField.getText();
				String enteredArtist = sL.songDetail.artistField.getText();
				String enteredAlbum = sL.songDetail.albumField.getText();
				String enteredYear = sL.songDetail.yearField.getText();
				String enteredSongAndArtist = enteredSong + "\t" + enteredArtist;
				
				if(enteredSong.isEmpty() || enteredArtist.isEmpty()){
					
					sL.error.setText("Song and/or artist information is missing");
					//revalidate();
					//repaint();
					
					sL.songDetail.songField.setEditable(true);
					sL.songDetail.artistField.setEditable(true);
					sL.songDetail.albumField.setEditable(true);
					sL.songDetail.yearField.setEditable(true);
				
					
				} else {
				
					File file = new File("songs.txt");
					Scanner scanner = null;
					try {
						scanner = new Scanner(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					while(scanner.hasNextLine()){
						String line = scanner.nextLine();
						
						System.out.println("line: " + line);
						System.out.println("songandartist: " + enteredSongAndArtist);
						if(line.toLowerCase().contains(enteredSongAndArtist.toLowerCase())){
							sL.error.setText("Cannot add the same song.");
							return;
						}
					}
					
					if(addNew){
						
						add.setEnabled(true);
						sL.error.setText(" ");
						
						addNew = false;
						if(enteredAlbum.isEmpty()){
							enteredAlbum = " ";
						}
					
						if(enteredYear.isEmpty()){
							enteredYear = " ";
						}
						sL.model.addElement(enteredSong);
						sL.artist.add(enteredArtist);
						sL.album.add(enteredAlbum);
						sL.year.add(enteredYear);
						sL.list.setSelectedIndex(sL.artist.size()-1);
						del.setEnabled(true);
						
						try {
							FileWriter writer = new FileWriter("songs.txt",true);

								writer.append((String) enteredSong);
								writer.append("\t");
								writer.append(enteredArtist);
								writer.append("\t");
								writer.append(enteredAlbum);
								writer.append("\t");
								writer.append(enteredYear);
								writer.append("\n");

							writer.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						sL.error.setText(" ");
						
					} else {
						
						add.setEnabled(true);
						sL.error.setText(" ");
						sL.model.setElementAt(enteredSong, sL.i);
						sL.artist.set(sL.i, enteredArtist);
						sL.album.set(sL.i,enteredAlbum);
						sL.year.set(sL.i,enteredYear);
						
						try {
							FileWriter writer = new FileWriter("songs.txt",false);
							for(int j=0; j<sL.artist.size(); j++){
								
								writer.write((String) sL.model.get(j));
								System.out.println(sL.model.get(j));
								writer.append("\t");
								writer.append(sL.artist.get(j));
								System.out.println(sL.artist.get(j));
								writer.append("\t");
								writer.append(sL.album.get(j));
								System.out.println(sL.album.get(j));
								writer.append("\t");
								writer.append(sL.year.get(j));
								System.out.println(sL.year.get(j));
								writer.append("\n");
												
							}

							writer.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		});
	}
}
