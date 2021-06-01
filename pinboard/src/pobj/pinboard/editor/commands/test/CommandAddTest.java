package pobj.pinboard.editor.commands.test;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class CommandAddTest extends CommandTest {

	private EditorInterface editor = new MockEditor();
	private ClipRect rect1 = new ClipRect(100., 200., 110., 220., Color.RED);
	private ClipRect rect2 = new ClipRect(120., 250., 130., 260., Color.RED);

	@Test
	public void testExecute() {
		CommandAdd cmd1 = new CommandAdd(editor, rect1);
		CommandAdd cmd2 = new CommandAdd(editor, rect2);
		assertTrue(editor.getBoard().getContents().isEmpty());
		cmd1.execute();
		assertEquals(1, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
	}
	
	@Test
	public void testUndo() {
		CommandAdd cmd1 = new CommandAdd(editor, rect1);
		CommandAdd cmd2 = new CommandAdd(editor, rect2);
		cmd1.execute();
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
		cmd2.undo();
		assertEquals(1, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		cmd1.undo();
		assertTrue(editor.getBoard().getContents().isEmpty());
	}

	@Test
	public void testRedo() {
		CommandAdd cmd1 = new CommandAdd(editor, rect1);
		CommandAdd cmd2 = new CommandAdd(editor, rect2);
		cmd1.execute();
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
		cmd2.undo();
		cmd1.undo();
		assertTrue(editor.getBoard().getContents().isEmpty());
		cmd1.execute();
		assertEquals(1, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
	}
	
}
