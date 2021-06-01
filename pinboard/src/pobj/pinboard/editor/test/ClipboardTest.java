package pobj.pinboard.editor.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.Clipboard;

public class ClipboardTest {

	private Clip[] clip = {
			new ClipRect(100.,100, 200., 200., Color.RED)
	};
	
	private Clip[] clips = {
			new ClipRect(100.,100, 200., 200., Color.RED), 
			new ClipEllipse(200.,100, 300., 200., Color.BLUE)
	};
	
	@Before
	public void beforeTest() {
		Clipboard.getInstance().clear();
	}
	
	@Test
	public void testSingleton() {
		Clipboard c = Clipboard.getInstance();
		assertTrue(c == Clipboard.getInstance());
	}

	@Test
	public void testNoPublicConstructor() {
		assertEquals(0, Clipboard.class.getConstructors().length);
	}
	
	@Test
	public void testCopy() {
		Clipboard c = Clipboard.getInstance();
		assertTrue(c.isEmpty());

		c.copyToClipboard(Arrays.asList(clips));
		assertFalse(c.isEmpty());
		assertEquals(2, c.copyFromClipboard().size());
	}

	@Test
	public void testCopy2() {
		Clipboard c = Clipboard.getInstance();
		c.copyToClipboard(Arrays.asList(clip));
		assertFalse(c.isEmpty());
		List<Clip> l = c.copyFromClipboard();
		assertEquals(1, l.size());
		assertTrue(l.get(0) instanceof ClipRect);
		ClipRect rect = (ClipRect)l.get(0);
		assertTrue(rect != clip[0]);		
		assertEquals(100., rect.getLeft(), 0.);
		assertEquals(200., rect.getRight(), 0.);
		assertEquals(100., rect.getTop(), 0.);
		assertEquals(200., rect.getBottom(), 0.);
	}

	@Test
	public void testCopy3() {
		Clipboard c = Clipboard.getInstance();
		c.copyToClipboard(Arrays.asList(clip));
		clip[0].move(10.,  20.);
		List<Clip> l = c.copyFromClipboard();
		ClipRect rect = (ClipRect)l.get(0);
		assertTrue(rect != clip[0]);		
		assertEquals(100., rect.getLeft(), 0.);
		assertEquals(200., rect.getRight(), 0.);
		assertEquals(100., rect.getTop(), 0.);
		assertEquals(200., rect.getBottom(), 0.);
	}
	
	@Test
	public void testCopy4() {
		Clipboard c = Clipboard.getInstance();
		c.copyToClipboard(Arrays.asList(clip));
		List<Clip> l0 = c.copyFromClipboard();
		ClipRect rect0 = (ClipRect)l0.get(0);
		rect0.move(10.,  20.);
		List<Clip> l = c.copyFromClipboard();
		ClipRect rect = (ClipRect)l.get(0);
		assertTrue(rect != rect0);		
		assertEquals(100., rect.getLeft(), 0.);
		assertEquals(200., rect.getRight(), 0.);
		assertEquals(100., rect.getTop(), 0.);
		assertEquals(200., rect.getBottom(), 0.);
	}
	
}
