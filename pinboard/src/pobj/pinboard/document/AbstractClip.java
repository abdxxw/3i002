package pobj.pinboard.document;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip,Serializable{
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}	
	
	
	@Override
	public double getTop() {
		return top;
	}

	@Override
	public double getLeft() {
		return left;
	}

	@Override
	public double getBottom() {
		return bottom;
	}

	@Override
	public double getRight() {
		return right;
	}
	
	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	@Override
	public void move(double x, double y) {
		this.left = left+x;
		this.top = top+y;
		this.right = right+x;
		this.bottom = bottom+y;
		
	}


	@Override
	public void setColor(Color c) {
		color =c;
	}


	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public double getWidth() {
		return right-left;
	}
	
	@Override
	public double getHeight() {
		return bottom-top;
	}
	
	 private void writeObject(ObjectOutputStream s) throws IOException {
	        s.writeDouble(left);
	        s.writeDouble(top);
	        s.writeDouble(right);
	        s.writeDouble(bottom);
	        s.writeDouble(color.getRed());
	        s.writeDouble(color.getGreen());
	        s.writeDouble(color.getBlue());
	        s.writeDouble(color.getOpacity());
	    }
	    
	 private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
	        left = s.readDouble();
	        top = s.readDouble();
	        right = s.readDouble();
	        bottom = s.readDouble();
	        double red = s.readDouble() ;
	        double green = s.readDouble() ;
	        double blue = s.readDouble() ;
	        double opacity = s.readDouble() ;
	        color = Color.color(red, green, blue, opacity) ;
	    }
}
