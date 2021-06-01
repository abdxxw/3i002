package pobj.pinboard.editor;

import pobj.pinboard.document.Board;

public interface EditorInterface {
	public Board  getBoard();
	public Selection getSelection();
	public CommandStack getUndoStack();
}
