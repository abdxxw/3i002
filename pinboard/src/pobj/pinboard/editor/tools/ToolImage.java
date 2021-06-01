package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolImage implements Tool{

	private File filename;
	private Clip image;

	public ToolImage(File filename) {

		this.filename=filename;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		image = new ClipImage(e.getX(), e.getY(), filename);
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		image.setGeometry(e.getX(), e.getY(), image.getRight(), image.getBottom());
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {

		Clip temp = null;
		for (Clip o : i.getBoard().getContents()) {
			if(o.equals(image)) {
				temp = o;
				break;
			}
		}
		if (temp != null)
			i.getBoard().getContents().remove(temp);
		
		//i.getBoard().addClip(image);
		CommandAdd ca = new CommandAdd(i,image);

		ca.execute();
		i.getUndoStack().addCommand(ca);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.strokeRect(image.getLeft(), image.getTop(), ((ClipImage)image).getWidth(), ((ClipImage)image).getHeight());
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Image";
	}

	@Override
	public void setColor(Color c) {
	}

}
