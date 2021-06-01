package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolRect implements Tool{

	private Clip rect;
	private Color color=Color.BLACK;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		rect = new ClipRect(e.getX(), e.getY(), e.getX(), e.getY(), color);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		rect.setGeometry(rect.getLeft(), rect.getTop(), e.getX(), e.getY());
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		if(rect.getLeft()>rect.getRight()) 
			rect.setGeometry(rect.getRight(), rect.getTop(), rect.getLeft(), rect.getBottom());
		
		if(rect.getTop()>rect.getBottom()) 
			rect.setGeometry(rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop());
			
		//i.getBoard().addClip(rect);

		CommandAdd ca = new CommandAdd(i,rect);

		ca.execute();
		i.getUndoStack().addCommand(ca);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(rect.getLeft(), rect.getTop(), ((ClipRect)rect).getWidth(), ((ClipRect)rect).getHeight());
		gc.setFill(color);
		gc.strokeRect(rect.getLeft(), rect.getTop(), ((ClipRect)rect).getWidth(), ((ClipRect)rect).getHeight());
		
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Rectangle";
	}
	
	@Override
	public void setColor(Color c) {
		color = c;
	}

}
