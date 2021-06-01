package pobj.pinboard.document;

import java.util.List;

public interface Composite extends Clip {
	public List<Clip> getClips();
	public void addClip(Clip toAdd);
	public void removeClip(Clip toRemove);
}
