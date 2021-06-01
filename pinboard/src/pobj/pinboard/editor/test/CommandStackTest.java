package pobj.pinboard.editor.test;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandMove;
import pobj.pinboard.editor.commands.test.CommandTest;

public class CommandStackTest extends CommandTest {

	private EditorInterface editor = new MockEditor();
	private ClipRect rect1 = new ClipRect(100., 200., 110., 220., Color.RED);
	private ClipRect rect2 = new ClipRect(120., 250., 130., 260., Color.RED);

	@Test
	public void test() {
		assertTrue(editor.getUndoStack().isUndoEmpty());
		assertTrue(editor.getUndoStack().isRedoEmpty());
		
		// add
		CommandAdd cmd1 = new CommandAdd(editor, rect1);
		cmd1.execute();
		editor.getUndoStack().addCommand(cmd1);
		assertEquals(1, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertTrue(editor.getUndoStack().isRedoEmpty());
		
		// add
		CommandAdd cmd2 = new CommandAdd(editor, rect2);
		cmd2.execute();
		editor.getUndoStack().addCommand(cmd2);
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));
		assertTrue(editor.getBoard().getContents().contains(rect2));
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertTrue(editor.getUndoStack().isRedoEmpty());
	
		// move
		CommandMove cmd3 = new CommandMove(editor, rect1, 1000., 2000.);
		cmd3.execute();
		editor.getUndoStack().addCommand(cmd3);
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));
		assertTrue(editor.getBoard().getContents().contains(rect2));
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertTrue(editor.getUndoStack().isRedoEmpty());
		assertEquals(1100., rect1.getLeft(), 0.);
		assertEquals(1110., rect1.getRight(), 0.);
		assertEquals(2200., rect1.getTop(), 0.);
		assertEquals(2220., rect1.getBottom(), 0.);
		
		// undo move
		editor.getUndoStack().undo();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));
		assertTrue(editor.getBoard().getContents().contains(rect2));
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertFalse(editor.getUndoStack().isRedoEmpty());
		assertEquals(100., rect1.getLeft(), 0.);
		assertEquals(110., rect1.getRight(), 0.);
		assertEquals(200., rect1.getTop(), 0.);
		assertEquals(220., rect1.getBottom(), 0.);

		// undo add
		editor.getUndoStack().undo();
		assertEquals(1, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertFalse(editor.getUndoStack().isRedoEmpty());

		// redo add
		editor.getUndoStack().redo();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));
		assertTrue(editor.getBoard().getContents().contains(rect2));
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertFalse(editor.getUndoStack().isRedoEmpty());

		// move
		editor.getUndoStack().addCommand(cmd3);
		assertFalse(editor.getUndoStack().isUndoEmpty());
		assertTrue(editor.getUndoStack().isRedoEmpty());
	}
	
}