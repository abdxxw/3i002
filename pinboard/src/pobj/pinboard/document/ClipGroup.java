package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite {

	private List<Clip> clips;
	
	public ClipGroup() {
		super(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 0, Color.BLACK);
		clips= new ArrayList<Clip>();
	}

	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip c : clips) {
			c.draw(ctx);
		}
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		for(Clip c : clips){
			if(c.isSelected(x, y))
				return true;
		}
		return false;
	}

	@Override
	public Clip copy() {
		ClipGroup out = new ClipGroup();
		for(Clip c : clips){
			out.addClip(c.copy());
		}
		return out;
	}

	@Override
	public List<Clip> getClips() {
		return clips;
	}


	private void resizeSelected(Clip c){
	
		double left = this.getLeft();
		double top = this.getTop();
		double right = this.getRight();
		double bottom = this.getBottom();
		
		if(c.getLeft()<left)
			left = c.getLeft();
		
		if(c.getRight()>right)
			right = c.getRight();
		
		if(c.getTop()<top)
			top = c.getTop();
		
		if(c.getBottom()>bottom)
			bottom = c.getBottom();
		
		this.setGeometry(left, top, right, bottom);
	}
	@Override
	public void addClip(Clip toAdd) {
		resizeSelected(toAdd);
		clips.add(toAdd);
		
	}

	@Override
	public void removeClip(Clip toRemove) {

		clips.remove(toRemove);
		if(clips.size()==0)
			return;
		List<Double> lefts = new ArrayList<Double>();
		List<Double> tops = new ArrayList<Double>();
		List<Double> rights = new ArrayList<Double>();
		List<Double> bottoms = new ArrayList<Double>();
		
		for(Clip c : clips) {
			lefts.add(c.getLeft());
			tops.add(c.getTop());
			rights.add(c.getRight());
			bottoms.add(c.getBottom());
		}
		double minLeft = Collections.min(lefts);
		double minTop = Collections.min(tops);
		this.setGeometry(minLeft, minTop, Collections.max(rights), Collections.max(bottoms));
		
	}
	
	
	@Override
	public void move(double x, double y) {
		for(Clip c : clips) {
			c.move(x, y);
		}
		
		super.move(x, y);
	}
}
