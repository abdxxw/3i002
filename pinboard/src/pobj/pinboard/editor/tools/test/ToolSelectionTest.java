package pobj.pinboard.editor.tools.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolSelection;

public class ToolSelectionTest extends ToolTest {

	private EditorInterface editor = new MockEditor();
	private Clip rect = new ClipRect(100.,100, 200., 200., Color.RED);
	private Clip ellipse = new ClipEllipse(200.,100, 300., 200., Color.BLUE);
	private Tool tool = new ToolSelection();
	
	@Before
	public void beforeTest() {
		editor.getBoard().addClip(rect);
		editor.getBoard().addClip(ellipse);
	}
	
	
	// sans shift

	@Test
	public void testSelect() {
		assertTrue(editor.getSelection().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
	}
	
	@Test
	public void testReselect() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));

		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 250, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(ellipse));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 250, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(ellipse));
	}

	@Test
	public void testSelectEmpty() {
		assertTrue(editor.getSelection().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 50, 150, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 50, 150, false));
		assertTrue(editor.getSelection().getContents().isEmpty());
	}
	
	@Test
	public void testDeselect() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 50, 150, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 50, 150, false));
		assertTrue(editor.getSelection().getContents().isEmpty());
	}
	
	@Test
	public void testMove() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 160, 170, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 160, 170, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		assertEquals(110., rect.getLeft(), 0.);
		assertEquals(210., rect.getRight(), 0.);
		assertEquals(120., rect.getTop(), 0.);
		assertEquals(220., rect.getBottom(), 0.);
	}
	
	@Test
	public void testMoveComplex() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 250, 150, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(ellipse));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 230, 140, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(ellipse));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 400, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 250, 150, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 260, 170, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 260, 170, false));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(ellipse));
		assertEquals(210., ellipse.getLeft(), 0.);
		assertEquals(310., ellipse.getRight(), 0.);
		assertEquals(120., ellipse.getTop(), 0.);
		assertEquals(220., ellipse.getBottom(), 0.);
	}
	
	
	// avec shift

	@Test
	public void testSelectMulti() {
		assertTrue(editor.getSelection().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, true));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, true));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));

		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 250, 150, true));
		assertEquals(2, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		assertTrue(editor.getSelection().getContents().contains(ellipse));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 250, 150, true));
		assertEquals(2, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		assertTrue(editor.getSelection().getContents().contains(ellipse));
	}
	
	@Test
	public void testSelectEmptyMulti() {
		assertTrue(editor.getSelection().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 50, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 50, 150, true));
		assertTrue(editor.getSelection().getContents().isEmpty());
	}

	@Test
	public void testDeselectMulti() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, true));
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 250, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 250, 150, false));
		assertEquals(2, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		assertTrue(editor.getSelection().getContents().contains(ellipse));

		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 250, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 250, 150, true));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, true));
		assertTrue(editor.getSelection().getContents().isEmpty());	
	}

	@Test
	public void testSelectIdentityMulti() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 150, 150, true));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
		
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 50, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 50, 150, true));
		assertEquals(1, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(rect));
	}
	
	@Test
	public void testMoveMulti() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 250, 150, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 250, 150, true));
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 150, 150, true));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 160, 170, true));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 160, 170, true));
		assertEquals(2, editor.getSelection().getContents().size());
		assertTrue(editor.getSelection().getContents().contains(ellipse));
		assertTrue(editor.getSelection().getContents().contains(rect));
		assertEquals(110., rect.getLeft(), 0.);
		assertEquals(210., rect.getRight(), 0.);
		assertEquals(120., rect.getTop(), 0.);
		assertEquals(220., rect.getBottom(), 0.);
		assertEquals(210., ellipse.getLeft(), 0.);
		assertEquals(310., ellipse.getRight(), 0.);
		assertEquals(120., ellipse.getTop(), 0.);
		assertEquals(220., ellipse.getBottom(), 0.);
	}
	
}
