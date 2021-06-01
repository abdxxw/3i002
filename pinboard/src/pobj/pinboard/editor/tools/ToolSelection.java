package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool{


	private double x, y;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x=e.getX();
		y=e.getY();
		if(!e.isShiftDown())
			i.getSelection().select(i.getBoard(), x, y);
		else
			i.getSelection().toogleSelect(i.getBoard(), x, y);
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		
		for(Clip c: i.getSelection().getContents()){
			//c.move(e.getX()-x, e.getY()-y);
			CommandMove cm = new CommandMove(i, c, e.getX()-x, e.getY()-y);
			cm.execute();
			i.getUndoStack().addCommand(cm);
		}
		x=e.getX();
		y=e.getY();
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {

		return "Selection";
	}

	@Override
	public void setColor(Color c) {
	}

}
