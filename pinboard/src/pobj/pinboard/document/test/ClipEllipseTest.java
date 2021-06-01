package pobj.pinboard.document.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;

public class ClipEllipseTest {

	@Test
	public void testNew() {
		Clip c = new ClipEllipse(100., 200. ,300., 400., Color.RED);
		assertEquals(100., c.getLeft(), 0.);
		assertEquals(200., c.getTop(), 0.);
		assertEquals(300., c.getRight(), 0.);
		assertEquals(400., c.getBottom(), 0.);
		assertEquals(Color.RED.toString(), c.getColor().toString());
	}

	@Test
	public void testSetGeometry() {
		Clip c = new ClipEllipse(100., 200. ,300., 400., Color.RED);
		c.setGeometry(101., 201., 301., 401.);
		assertEquals(101., c.getLeft(), 0.);
		assertEquals(201., c.getTop(), 0.);
		assertEquals(301., c.getRight(), 0.);
		assertEquals(401., c.getBottom(), 0.);
	}

	@Test
	public void testMove() {
		Clip c = new ClipEllipse(100., 200. ,300., 400., Color.RED);
		c.move(1., 2.);
		assertEquals(101., c.getLeft(), 0.);
		assertEquals(202., c.getTop(), 0.);
		assertEquals(301., c.getRight(), 0.);
		assertEquals(402., c.getBottom(), 0.);
	}

	@Test
	public void testSetColor() {
		Clip c = new ClipEllipse(100., 200. ,300., 400., Color.RED);
		c.setColor(Color.BLUE);
		assertEquals(Color.BLUE.toString(), c.getColor().toString());
	}

	@Test
	public void testIsSelected() {
		Clip c = new ClipEllipse(100., 200. ,300., 400., Color.RED);
		assertTrue(c.isSelected(200., 300.));
		assertFalse(c.isSelected(100.,200.));
		assertFalse(c.isSelected(100.,400.));
		assertFalse(c.isSelected(300., 200.));
		assertFalse(c.isSelected(300., 400.));
	}

	@Test
	public void testCopy() {
		Clip c = new ClipEllipse(100., 200. ,300., 400., Color.RED);
		Clip d = c.copy();
		assertTrue(d instanceof ClipEllipse);
		c.move(1., 2.);
		c.setColor(Color.BLUE);
		assertEquals(101., c.getLeft(), 0.);
		assertEquals(202., c.getTop(), 0.);
		assertEquals(301., c.getRight(), 0.);
		assertEquals(402., c.getBottom(), 0.);
		assertEquals(Color.BLUE.toString(), c.getColor().toString());
		assertEquals(100., d.getLeft(), 0.);
		assertEquals(200., d.getTop(), 0.);
		assertEquals(300., d.getRight(), 0.);
		assertEquals(400., d.getBottom(), 0.);
		assertEquals(Color.RED.toString(), d.getColor().toString());
	}
	
}

