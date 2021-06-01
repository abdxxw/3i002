package pobj.pinboard.document.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.ClipRect;

public class ClipGroupTest {

	private ClipRect rect1 = new ClipRect(100., 200., 110., 220., Color.RED);
	private ClipRect rect2 = new ClipRect(120., 250., 130., 260., Color.RED);
	private ClipGroup group = new ClipGroup();

	@Test
	public void testAdd() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);
		assertEquals(1, group.getClips().size());
		assertTrue(group.getClips().contains(rect1));
		
		group.addClip(rect2);
		assertEquals(2, group.getClips().size());
		assertTrue(group.getClips().contains(rect1));
		assertTrue(group.getClips().contains(rect2));
	}
	
	@Test
	public void testRemove() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);
		group.addClip(rect2);
		assertEquals(2, group.getClips().size());
		assertTrue(group.getClips().contains(rect1));
		assertTrue(group.getClips().contains(rect2));
		
		group.removeClip(rect1);
		assertEquals(1, group.getClips().size());
		assertTrue(group.getClips().contains(rect2));
		
		group.removeClip(rect2);
		assertTrue(group.getClips().isEmpty());
	}
	
	@Test
	public void testAddSize() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);
		assertEquals(100., group.getLeft(), 0.);
		assertEquals(110., group.getRight(), 0.);
		assertEquals(200., group.getTop(), 0.);
		assertEquals(220., group.getBottom(), 0.);
		
		group.addClip(rect2);		
		assertEquals(100., group.getLeft(), 0.);
		assertEquals(130., group.getRight(), 0.);
		assertEquals(200., group.getTop(), 0.);
		assertEquals(260., group.getBottom(), 0.);
	}
	
	@Test
	public void testRemoveSize() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);
		group.addClip(rect2);		
		assertEquals(100., group.getLeft(), 0.);
		assertEquals(130., group.getRight(), 0.);
		assertEquals(200., group.getTop(), 0.);
		assertEquals(260., group.getBottom(), 0.);
		
		group.removeClip(rect1);
		assertEquals(120., group.getLeft(), 0.);
		assertEquals(130., group.getRight(), 0.);
		assertEquals(250., group.getTop(), 0.);
		assertEquals(260., group.getBottom(), 0.);
	}

	@Test
	public void testMove() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);
		group.addClip(rect2);
		assertEquals(100., group.getLeft(), 0.);
		assertEquals(130., group.getRight(), 0.);
		assertEquals(200., group.getTop(), 0.);
		assertEquals(260., group.getBottom(), 0.);

		group.move(100., 200.);
		
		assertEquals(200., group.getLeft(), 0.);
		assertEquals(230., group.getRight(), 0.);
		assertEquals(400., group.getTop(), 0.);
		assertEquals(460., group.getBottom(), 0.);

		assertEquals(200., rect1.getLeft(), 0.);
		assertEquals(210., rect1.getRight(), 0.);
		assertEquals(400., rect1.getTop(), 0.);
		assertEquals(420., rect1.getBottom(), 0.);

		assertEquals(220., rect2.getLeft(), 0.);
		assertEquals(230., rect2.getRight(), 0.);
		assertEquals(450., rect2.getTop(), 0.);
		assertEquals(460., rect2.getBottom(), 0.);
	}
	
	@Test
	public void testCopy() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);
		group.addClip(rect2);

		Clip clip = group.copy();
		assertTrue(clip instanceof ClipGroup);
		ClipGroup group2 = (ClipGroup)clip;
		assertEquals(2, group.getClips().size());
		assertEquals(2, group2.getClips().size());
		
		group.removeClip(rect1);
		assertEquals(1, group.getClips().size());
		assertEquals(2, group2.getClips().size());
	}
	
	@Test
	public void testCopyDeep() {
		assertTrue(group.getClips().isEmpty());
		group.addClip(rect1);

		Clip clip = group.copy();
		assertTrue(clip instanceof ClipGroup);
		ClipGroup group2 = (ClipGroup)clip;
		List<Clip> list = group2.getClips();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipRect);
		ClipRect r = (ClipRect)list.get(0);
		assertNotEquals(rect1,  r);
		assertEquals(100., r.getLeft(), 0.);
		assertEquals(110., r.getRight(), 0.);
		assertEquals(200., r.getTop(), 0.);
		assertEquals(220., r.getBottom(), 0.);

		r.move(100.,  100.);
		assertEquals(200., r.getLeft(), 0.);
		assertEquals(210., r.getRight(), 0.);
		assertEquals(300., r.getTop(), 0.);
		assertEquals(320., r.getBottom(), 0.);
		assertEquals(100., rect1.getLeft(), 0.);
		assertEquals(110., rect1.getRight(), 0.);
		assertEquals(200., rect1.getTop(), 0.);
		assertEquals(220., rect1.getBottom(), 0.);
	}

}
