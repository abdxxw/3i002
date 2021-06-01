package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command{
	
	private EditorInterface editor;
	private List<Clip> toMove;
	private double x;
	private double y;
	
	public CommandMove(EditorInterface editor,List<Clip> toMove, double x, double y){
		this.toMove=toMove;
		this.editor=editor;
		this.x=x;
		this.y=y;
	}	
	
	public CommandMove(EditorInterface editor,Clip toMove, double x, double y){
		List<Clip> tmp = new ArrayList<Clip>();
		tmp.add(toMove);
		this.toMove=tmp;
		this.editor=editor;
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void execute() {
		for(Clip c : toMove)
			c.move(x,y);
	}

	@Override
	public void undo() {
		for(Clip c : toMove)
			c.move(-x,-y);
		
	}

}
