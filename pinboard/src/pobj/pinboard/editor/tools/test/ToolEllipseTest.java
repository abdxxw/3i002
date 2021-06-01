package pobj.pinboard.editor.tools.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;

public class ToolEllipseTest extends ToolTest {
	
	private EditorInterface editor = new MockEditor();
	private Tool tool = new ToolEllipse();

	@Test
	public void testCreate() {
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipEllipse);
		ClipEllipse r = (ClipEllipse) list.get(0);
		assertEquals(100., r.getLeft(), 0.);
		assertEquals(200., r.getTop(), 0.);
		assertEquals(300., r.getRight(), 0.);
		assertEquals(400., r.getBottom(), 0.);
	}
	
	// mouvement plus complexe de la souris
	@Test
	public void testCreate2() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 400, 500, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 50, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 200, 50, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 600, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipEllipse);
		ClipEllipse r = (ClipEllipse) list.get(0);
		assertEquals(100., r.getLeft(), 0.);
		assertEquals(200., r.getTop(), 0.);
		assertEquals(300., r.getRight(), 0.);
		assertEquals(400., r.getBottom(), 0.);
	}
	
	// coins inversés
	@Test
	public void testCreate3() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 300, 400, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 100, 200, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 100, 200, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipEllipse);
		ClipEllipse r = (ClipEllipse) list.get(0);
		assertEquals(100., r.getLeft(), 0.);
		assertEquals(200., r.getTop(), 0.);
		assertEquals(300., r.getRight(), 0.);
		assertEquals(400., r.getBottom(), 0.);
	}	
}
