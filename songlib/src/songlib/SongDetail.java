package songlib;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
/**
 * 
 * @author Joyce
 *
 */
public class SongDetail extends JPanel{
	
	SongLib sL;
	JLabel[] labels = new JLabel[4];
	GridBagLayout gridbag;
	JLabel songFieldLabel;
	JLabel artistFieldLabel;
	JLabel albumFieldLabel;
	JLabel yearFieldLabel;
	JTextField songField;
	JTextField artistField;
	JTextField albumField;
	JTextField yearField;
	
	public SongDetail(SongLib sL){
		
		this.sL = sL;
		this.initial();
		
	}
	
	protected static final String songLabel = "Song";
	protected static final String artistLabel = "Artist";
	protected static final String albumLabel = "Album";
	protected static final String yearLabel = "Year";
	
	protected void initial(){
		
		songFieldLabel = new JLabel(songLabel + ": ");
		labels[0] = songFieldLabel;
		songField = new JTextField();
		if(sL.model.isEmpty()) {songField.setText("");}
		else {songField.setText((String) sL.model.get(0));}
		songField.setActionCommand(songLabel);
		songFieldLabel.setLabelFor(songField);
			
		artistFieldLabel = new JLabel(artistLabel + ": ");
		labels[1] = artistFieldLabel;
		artistField = new JTextField();
		if(sL.model.isEmpty()) artistField.setText("");
		else artistField.setText((String) sL.artist.get(0));
		artistField.setActionCommand(artistLabel);
		artistFieldLabel.setLabelFor(artistField);
			
		albumFieldLabel = new JLabel(albumLabel + ": ");
		labels[2] = albumFieldLabel;
		albumField = new JTextField();
		if(sL.model.isEmpty()) albumField.setText("");
		else albumField.setText((String) sL.album.get(0));
		albumField.setActionCommand(albumLabel);
		albumFieldLabel.setLabelFor(albumField);
		
		yearFieldLabel = new JLabel(yearLabel + ": ");
		labels[3] = yearFieldLabel;
		yearField = new JTextField();
		if(sL.model.isEmpty()) yearField.setText("");
		else yearField.setText((String) sL.year.get(0));
		yearField.setActionCommand(yearLabel);	
		yearFieldLabel.setLabelFor(yearField);
		
		gridbag = new GridBagLayout();
		setLayout(gridbag);
		
		JTextField[] textFields = {songField, artistField, albumField, yearField};
		GridBagConstraints c = new GridBagConstraints();

		int numLabels = labels.length;
		
		for(int j=0; j<numLabels; j++){
			
			c.gridwidth = GridBagConstraints.RELATIVE;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.0;
			add(labels[j],c);
			
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;
			add(textFields[j],c);
		
	    }
		//this.repaint();
		//this.sL.repaint();
	}
	
	protected void Repaint(Object song, String artist, String album, String year){
		
		songField.setText((String) song);
		artistField.setText(artist);
		albumField.setText(album);
		yearField.setText(year);
		sL.error.setText(" ");
				
		//this.repaint();
		//this.invalidate();
		//this.sL.getContentPane().repaint();
		//this.sL.invalidate();
	}
}
