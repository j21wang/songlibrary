package songlib;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * 
 * @author Joyce
 *
 */
public class SongDisplay extends JPanel{

	JScrollPane jsPane;
	JList list;
	SongLib sL;
	
	public SongDisplay(SongLib sL){
		
		this.sL = sL;
		ReadFile();
		makeScrollPane();
		Selection();
		
	}
	
	protected void ReadFile(){
		
		try{
			FileInputStream fstream = new FileInputStream("songs.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while((strLine = br.readLine()) != null){
				
				Scanner s = new Scanner(strLine).useDelimiter("\\t");
				sL.songs.add(s.next());
				sL.artist.add(s.next());
				sL.album.add(s.next());
				sL.year.add(s.next());
			}
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	protected void makeScrollPane(){
		
		list = new JList(sL.songs.toArray());
		list.setFixedCellWidth(100);
		list.setVisibleRowCount(3);
		jsPane = new JScrollPane(list);
		add(jsPane);
		
	}
	
	protected void Selection(){
		
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent evt){
				sL.i=list.getSelectedIndex();
				if(sL.i != -1 && !evt.getValueIsAdjusting()){
					
					System.out.println(sL.songs.get(sL.i));
					System.out.println(sL.artist.get(sL.i));
					System.out.println(sL.album.get(sL.i));
					System.out.println(sL.year.get(sL.i));
					
				}
			}
		});
		
	}
}
