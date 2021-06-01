package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {

	private List<Clip> liste =  new ArrayList<Clip>();
	
	public void select(Board board, double x, double y) {
		liste.clear();

		for(Clip c : board.getContents()) {
			if(c.isSelected(x, y)) {
				liste.add(c);
				break;
			}
		}
	}
	
	public void toogleSelect(Board board, double x, double y) {
		for(Clip c : board.getContents()) {
			if(c.isSelected(x, y)) {
				if(liste.contains(c))
					liste.remove(c);
				else
					liste.add(c);
				break;
			}
		}
	}
	
	public void clear() {
		liste.clear();
	}
	public List<Clip> getContents(){
		return liste;
	}
	public void drawFeedback(GraphicsContext gc) {

		if(liste.size()==0)
			return;
		List<Double> lefts = new ArrayList<Double>();
		List<Double> tops = new ArrayList<Double>();
		List<Double> rights = new ArrayList<Double>();
		List<Double> bottoms = new ArrayList<Double>();
		
		for(Clip c : liste) {
			lefts.add(c.getLeft());
			tops.add(c.getTop());
			rights.add(c.getRight());
			bottoms.add(c.getBottom());
		}
		double minLeft = Collections.min(lefts);
		double minTop = Collections.min(tops);
		gc.setStroke(Color.BLUE);
		gc.strokeRect(minLeft, minTop, Collections.max(rights) - minLeft, Collections.max(bottoms) - minTop);
	}
}
