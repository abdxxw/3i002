package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {

	private List<Command> redo;
	private List<Command> undo;
	
	public CommandStack() {
		redo=new ArrayList<Command>();
		undo=new ArrayList<Command>();
	}
	
	public void addCommand(Command c){
		redo.clear();
		undo.add(c);	
	}
	
	public void undo(){
		if(isUndoEmpty()) return;
		Command c = undo.remove(undo.size()-1);
		c.undo();
		redo.add(c);
	}
	
	public void redo(){
		if(isRedoEmpty()) return;
		Command c=redo.remove(redo.size()-1);
		c.execute();
		undo.add(c);
	}
	
	public boolean isUndoEmpty(){
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty(){
		return redo.isEmpty();
	}
}
