package pobj.pinboard.document.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;


public class BoardTest {

	@Test
	public void test() {
		Clip c1 = new ClipRect(100., 200. ,300., 400., Color.RED);
		Clip c2 = new ClipRect(101., 201. ,301., 401., Color.BLUE);
		Clip c3 = new ClipRect(102., 202. ,302., 402., Color.GREEN);
		Board b = new Board();
		assertTrue(b.getContents().isEmpty());
		b.addClip(c1);
		assertTrue(b.getContents().contains(c1));
		assertFalse(b.getContents().contains(c2));
		b.addClip(c2);
		assertTrue(b.getContents().contains(c1));
		assertTrue(b.getContents().contains(c2));
		b.removeClip(c1);
		assertFalse(b.getContents().contains(c1));
		assertTrue(b.getContents().contains(c2));
		b.addClip(Arrays.asList(c1,c3));
		assertTrue(b.getContents().contains(c1));
		assertTrue(b.getContents().contains(c2));
		assertTrue(b.getContents().contains(c3));
		b.removeClip(Arrays.asList(c1,c2));
		assertFalse(b.getContents().contains(c1));
		assertFalse(b.getContents().contains(c2));
		assertTrue(b.getContents().contains(c3));
	}
}
