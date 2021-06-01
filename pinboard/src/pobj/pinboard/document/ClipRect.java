package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip{
	

	public ClipRect(double left, double top, double right, double bottom, Color color) {
		super(left,top,right,bottom,color);
	}
	

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(), getTop(), getWidth(), getHeight());
		
	}
	
	@Override
	public boolean isSelected(double x, double y) {
		return this.getTop()<=y && this.getBottom()>=y && this.getLeft()<=x && this.getRight()>=x;
	}

	@Override
	public Clip copy() {
		return new ClipRect(getLeft(), getTop(), getRight(),getBottom(),getColor());
	}

}
