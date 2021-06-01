package pobj.pinboard.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Serializable {
	private List<Clip> liste;
	public Board(){
		liste = new ArrayList<Clip>();
	}
	public List<Clip> getContents(){
		return liste;
	}
	public void addClip(Clip clip) {
		liste.add(clip);
	}
	
	public void addClip(List<Clip> clip){
		liste.addAll(clip);
	}
	public void removeClip(Clip clip) {
		liste.remove(clip);
	}
	
	public void removeClip(List<Clip> clip) {
		liste.removeAll(clip);
	}
	
	public void draw(GraphicsContext gc) {
		new ClipRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight(),Color.WHITE).draw(gc);
		for (Clip c : liste)
			c.draw(gc);
	}
	
   

}
