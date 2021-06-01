package pobj.pinboard.document;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip implements Clip{
	
	private Image image;
	private File filename;
	
	public ClipImage(double left, double top, File filename) {
		super(left, top, 0.0, 0.0, Color.BLACK);
		image = new Image("file:///" + filename.getAbsolutePath()); // for windows i used file:/// and not file://
		this.filename=filename;
		setGeometry(left, top, left+image.getWidth(), top+image.getHeight());
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(image, getLeft(), getTop());
	}


	@Override
	public boolean isSelected(double x, double y) {
		return this.getTop()<=y && this.getBottom()>=y && this.getLeft()<=x && this.getRight()>=x;
	}


	@Override
	public Clip copy() {
		return new ClipImage(getLeft(), getTop(), filename);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if (o == this) return true;
		if(!(o instanceof ClipImage)) return false;
		ClipImage temp = (ClipImage) o;
		return temp.filename.equals(this.filename); 		
	}

	 private void writeObject(ObjectOutputStream s) throws IOException {
		 	s.writeObject(filename);
	    }
	    
	 private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
	        filename = (File) s.readObject();
			image = new Image("file:///" + filename.getAbsolutePath());
	        
	    }
	 
}
