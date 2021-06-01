package pobj.pinboard.editor.commands;

import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command{

	private EditorInterface editor;
	private ClipGroup groupe;
	

	public CommandUngroup(EditorInterface editor,ClipGroup groupe){
		this.editor=editor;
		this.groupe=groupe;
	}
	
	
	@Override
	public void execute() {

		editor.getBoard().removeClip(groupe);
		editor.getBoard().addClip(groupe.getClips());
		
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(groupe.getClips());
		editor.getBoard().addClip(groupe);
		
	}


}
