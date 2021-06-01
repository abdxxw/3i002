package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{

	private EditorInterface editor;
	private ClipGroup groupe;

	public CommandGroup(EditorInterface	 editor,ClipGroup groupe){
		this.editor=editor;
		this.groupe=groupe;
	}
	
	
	public CommandGroup(EditorInterface editor, List<Clip> clips) {
		this.editor=editor;
		this.groupe=new ClipGroup();
		for(Clip c : clips)
			this.groupe.addClip(c);
	}

	@Override
	public void execute() {
		editor.getBoard().removeClip(groupe.getClips());
		editor.getBoard().addClip(groupe);
		
	}

	@Override
	public void undo() {			
		editor.getBoard().removeClip(groupe);
		editor.getBoard().addClip(groupe.getClips());
	}


}
