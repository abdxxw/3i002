package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool{

	private Clip eli;
	private Color color=Color.BLACK;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		eli = new ClipEllipse(e.getX(), e.getY(), e.getX(), e.getY(), color);
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		eli.setGeometry(eli.getLeft(), eli.getTop(), e.getX(), e.getY());
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {

		if(eli.getLeft()>eli.getRight()) 
			eli.setGeometry(eli.getRight(), eli.getTop(), eli.getLeft(), eli.getBottom());
		
		if(eli.getTop()>eli.getBottom()) 
			eli.setGeometry(eli.getLeft(), eli.getBottom(), eli.getRight(), eli.getTop());
		
		//i.getBoard().addClip(eli);
		CommandAdd ca = new CommandAdd(i,eli);

		ca.execute();
		i.getUndoStack().addCommand(ca);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(eli.getLeft(), eli.getTop(), ((ClipEllipse)eli).getWidth(), ((ClipEllipse)eli).getHeight());
		gc.setFill(color);
		gc.strokeOval(eli.getLeft(), eli.getTop(), ((ClipEllipse)eli).getWidth(), ((ClipEllipse)eli).getHeight());
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Ellipse";
	}
	
	@Override
	public void setColor(Color c) {
		color = c;
	}

}
