package pobj.pinboard.editor.commands.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;

public class CommandUngroupTest extends CommandTest {

	private EditorInterface editor = new MockEditor();
	private ClipRect rect1 = new ClipRect(100., 200., 110., 220., Color.RED);
	private ClipRect rect2 = new ClipRect(120., 250., 130., 260., Color.RED);
	private List<Clip> rects = Arrays.asList((Clip)rect1,(Clip)rect2);

	@Test
	public void testExecute() {
		CommandGroup cmd1 = new CommandGroup(editor, rects);
		editor.getBoard().getContents().addAll(rects);
		cmd1.execute();
		assertEquals(1, editor.getBoard().getContents().size());
		ClipGroup group = (ClipGroup) editor.getBoard().getContents().get(0);
		assertEquals(2, group.getClips().size());
		assertTrue(group.getClips().contains(rect1));		
		assertTrue(group.getClips().contains(rect2));		

		CommandUngroup cmd2 = new CommandUngroup(editor, group);
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
	}
	
	@Test
	public void testUndo() {
		CommandGroup cmd1 = new CommandGroup(editor, rects);
		editor.getBoard().getContents().addAll(rects);
		cmd1.execute();
		assertEquals(1, editor.getBoard().getContents().size());
		ClipGroup group = (ClipGroup) editor.getBoard().getContents().get(0);

		CommandUngroup cmd2 = new CommandUngroup(editor, group);
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		
		cmd2.undo();
		assertEquals(1, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().get(0) instanceof ClipGroup);
		ClipGroup group2 = (ClipGroup) editor.getBoard().getContents().get(0);
		assertEquals(2, group2.getClips().size());
		assertTrue(group2.getClips().contains(rect1));		
		assertTrue(group2.getClips().contains(rect2));		
	}

	@Test
	public void testRedo() {
		CommandGroup cmd1 = new CommandGroup(editor, rects);
		editor.getBoard().getContents().addAll(rects);
		cmd1.execute();
		assertEquals(1, editor.getBoard().getContents().size());
		ClipGroup group = (ClipGroup) editor.getBoard().getContents().get(0);
		assertEquals(2, group.getClips().size());
		assertTrue(group.getClips().contains(rect1));		
		assertTrue(group.getClips().contains(rect2));		

		CommandUngroup cmd2 = new CommandUngroup(editor, group);
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
		
		cmd2.undo();
		assertEquals(1, editor.getBoard().getContents().size());
		
		cmd2.execute();
		assertEquals(2, editor.getBoard().getContents().size());
		assertTrue(editor.getBoard().getContents().contains(rect1));		
		assertTrue(editor.getBoard().getContents().contains(rect2));		
	}
	
}
