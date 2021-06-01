package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {

	private List<Clip> liste;
	private static Clipboard c;
	private List<ClipboardListener> observers;
	
	
	private Clipboard() {
		liste = new ArrayList<Clip>();
		observers = new ArrayList<ClipboardListener>();
	}
	
	
	public static Clipboard getInstance() {
		if (c == null)
			c = new Clipboard();
		return c;
	}
	
	
	public void copyToClipboard(List<Clip> clips){
		for(Clip c : clips){
			liste.add(c.copy());
		}
		notifyCB();
	}
	
	
	public List<Clip> copyFromClipboard(){
		List<Clip> out = new ArrayList<Clip>();
		for(Clip c : liste){
			out.add(c.copy());
		}
		return out;
	}
	
	
	public void clear(){
		liste.clear();
		notifyCB();
	}
	
	
	public boolean isEmpty() {
		return liste.isEmpty();
	}
	
	public void addListener(ClipboardListener listener){
		observers.add(listener);
	}
	
	
	public void removeListener(ClipboardListener listener){
		observers.remove(listener);
	}
	
	
	public void notifyCB() {
		for (ClipboardListener c: observers) {
			c.clipboardChanged();
		}
		
	}
}
