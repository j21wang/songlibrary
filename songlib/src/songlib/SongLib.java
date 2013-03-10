package songlib;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;
/**
 * 
 * @author Joyce
 *
 */
public class SongLib extends JFrame{

	SongDetail songDetail;
	SongEdit songEdit;
	
	ArrayList<String> songs = new ArrayList<String>();
	ArrayList<String> artist = new ArrayList<String>();
	ArrayList<String> album = new ArrayList<String>();
	ArrayList<String> year = new ArrayList<String>();
	int i;
	final DefaultListModel model = new DefaultListModel();
	
	JLabel error = new JLabel(" ");
	JList list;
	JScrollPane jsPane;
	
	protected GridBagLayout gridbag = new GridBagLayout();
	protected GridBagConstraints c = new GridBagConstraints();
	
	public  SongLib(String title) throws IOException{
		super(title);
		
		ReadFile();
		makeScrollPane();
		Selection();
		
		songDetail = new SongDetail(this);
		songEdit = new SongEdit(this);
		layOut();
		
	}
	
protected void ReadFile(){
		
		try{
			FileInputStream fstream = new FileInputStream("songs.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = null;

			int cout = 0;
			
			while((strLine = br.readLine()) != null){
				
				Scanner s = new Scanner(strLine).useDelimiter("\\t");
				model.add(cout,s.next());
				artist.add(s.next());
				album.add(s.next());
				year.add(s.next());
				cout++;
			}
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	protected void makeScrollPane(){
		
		list = new JList(model);
		list.setFixedCellWidth(150);
		list.setVisibleRowCount(5);
		list.setSelectedIndex(0);
		jsPane = new JScrollPane(list);
		
	}
	
	protected void Selection(){
		
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent evt){
				i=list.getSelectedIndex();
				if(i != -1 && !evt.getValueIsAdjusting()){
					
					songEdit.add.setEnabled(true);
					songEdit.del.setEnabled(true);
					songEdit.done.setEnabled(true);
					System.out.println(model.get(i));
					System.out.println(artist.get(i));
					System.out.println(album.get(i));
					System.out.println(year.get(i));
					songDetail.Repaint(model.get(i),artist.get(i),album.get(i),year.get(i));
				}
			}
		});
		
	}

	
	protected void layOut(){
		
		setLayout(gridbag);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		
		gridbag.setConstraints(jsPane,c);
		add(jsPane);		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight=1;
		c.weightx = 1;
		c.weighty = 1;
		
		gridbag.setConstraints(songDetail,c);
		add(songDetail);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.PAGE_END;
		
		gridbag.setConstraints(songEdit,c);
		add(songEdit);	
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty= 1; //ADDING AN ERROR FOR FIRST TIME
		
		gridbag.setConstraints(error,c);
		add(error);
		
	}
	
	public static void main(String[] args) throws IOException{
		
		JFrame sL = new SongLib("My Song Library");
		sL.pack();
		sL.setVisible(true);
		sL.setLocationRelativeTo(null);
		sL.setResizable(true);
		sL.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}