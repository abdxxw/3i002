package pobj.pinboard.editor.commands;

public interface Command {
	public void execute();
	public void undo();
}
