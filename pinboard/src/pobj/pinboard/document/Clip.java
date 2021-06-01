package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Clip {
	// Drawing
	public void draw(GraphicsContext ctx);
	
	// Geometry	
	public double getTop();
	public double getLeft();
	public double getBottom();
	public double getRight();
	public void setGeometry(double left, double top, double right, double bottom);
	public void move(double x, double y);
	public boolean isSelected(double x, double y);

	// Colors
	public void setColor(Color c);
	public Color getColor();

	// Cloning
	public Clip copy();

	double getWidth();
	double getHeight();
}
