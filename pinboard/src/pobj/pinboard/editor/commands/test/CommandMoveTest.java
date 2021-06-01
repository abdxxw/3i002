package pobj.pinboard.editor.commands.test;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandMove;;

public class CommandMoveTest extends CommandTest {

	private EditorInterface editor = new MockEditor();
	private ClipRect rect = new ClipRect(100., 200., 110., 220., Color.RED);

	@Test
	public void testExecute() {
		editor.getBoard().getContents().add(rect);
		CommandMove cmd1 = new CommandMove(editor, rect, 1000., 2000.);
		CommandMove cmd2 = new CommandMove(editor, rect, 10000., 20000.);
		assertEquals(100., rect.getLeft(), 0.);
		assertEquals(110., rect.getRight(), 0.);
		assertEquals(200., rect.getTop(), 0.);
		assertEquals(220., rect.getBottom(), 0.);
		cmd1.execute();
		assertEquals(1100., rect.getLeft(), 0.);
		assertEquals(1110., rect.getRight(), 0.);
		assertEquals(2200., rect.getTop(), 0.);
		assertEquals(2220., rect.getBottom(), 0.);
		cmd2.execute();
		assertEquals(11100., rect.getLeft(), 0.);
		assertEquals(11110., rect.getRight(), 0.);
		assertEquals(22200., rect.getTop(), 0.);
		assertEquals(22220., rect.getBottom(), 0.);
	}
	
	@Test
	public void testUndo() {
		editor.getBoard().getContents().add(rect);
		CommandMove cmd1 = new CommandMove(editor, rect, 1000., 2000.);
		CommandMove cmd2 = new CommandMove(editor, rect, 10000., 20000.);
		cmd1.execute();
		cmd2.execute();
		assertEquals(11100., rect.getLeft(), 0.);
		assertEquals(11110., rect.getRight(), 0.);
		assertEquals(22200., rect.getTop(), 0.);
		assertEquals(22220., rect.getBottom(), 0.);

		cmd2.undo();
		assertEquals(1100., rect.getLeft(), 0.);
		assertEquals(1110., rect.getRight(), 0.);
		assertEquals(2200., rect.getTop(), 0.);
		assertEquals(2220., rect.getBottom(), 0.);

		cmd1.undo();
		assertEquals(100., rect.getLeft(), 0.);
		assertEquals(110., rect.getRight(), 0.);
		assertEquals(200., rect.getTop(), 0.);
		assertEquals(220., rect.getBottom(), 0.);
	}
	
	@Test
	public void testRedo() {
		editor.getBoard().getContents().add(rect);
		CommandMove cmd1 = new CommandMove(editor, rect, 1000., 2000.);
		CommandMove cmd2 = new CommandMove(editor, rect, 10000., 20000.);
		cmd1.execute();
		cmd2.execute();
		cmd2.undo();
		cmd1.undo();
		assertEquals(100., rect.getLeft(), 0.);
		assertEquals(110., rect.getRight(), 0.);
		assertEquals(200., rect.getTop(), 0.);
		assertEquals(220., rect.getBottom(), 0.);
		
		cmd1.execute();
		assertEquals(1100., rect.getLeft(), 0.);
		assertEquals(1110., rect.getRight(), 0.);
		assertEquals(2200., rect.getTop(), 0.);
		assertEquals(2220., rect.getBottom(), 0.);

		cmd2.execute();
		assertEquals(11100., rect.getLeft(), 0.);
		assertEquals(11110., rect.getRight(), 0.);
		assertEquals(22200., rect.getTop(), 0.);
		assertEquals(22220., rect.getBottom(), 0.);
	}
	
}
