package pobj.pinboard.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface,ClipboardListener{
	private Board board; 
	private Canvas canvas;
	private Selection selected;
	private Clipboard cb;
	private int w=1200,h=600;
	private final Tool toolRect = new ToolRect();
	private final Tool toolEli = new ToolEllipse();
	private final Tool toolSelect = new ToolSelection();
	private Tool tool = toolRect;
	private final Rectangle[] colorsRec = new Rectangle[10];
	private final Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.ORANGE, Color.PURPLE, Color.BLACK, Color.GRAY, Color.HOTPINK, Color.CYAN};
	private Color now = Color.BLACK;
	private MenuItem itemPaste;
	private CommandStack cs;
	public EditorWindow(final Stage stage) {
		
		//init
		board= new Board();
		selected = new Selection();
		cb = Clipboard.getInstance();
		cb.addListener(this);
		stage.setTitle("PinBoard");
		VBox vbox = new VBox();
		canvas = new Canvas(w,h);
		EditorInterface inter = this;
		cs = new CommandStack();
		
		final Menu menuFile = new Menu("File");
		final Menu menuEdit = new Menu("Edit");
		final Menu menuTools = new Menu("Tools");
		
		
		MenuItem itemNew = new MenuItem("New");
		MenuItem itemOpen = new MenuItem("Open");
		MenuItem itemSave = new MenuItem("Save");
		MenuItem itemClose = new MenuItem("Close");
		menuFile.getItems().addAll(itemNew, itemOpen, itemSave, itemClose);

		
		
		MenuItem itemCopy = new MenuItem("Copy");
		itemPaste = new MenuItem("Paste");
		itemPaste.setDisable(true);
		MenuItem itemDelete = new MenuItem("Delete");
		MenuItem itemGroup = new MenuItem("Group");
		MenuItem itemUngroup = new MenuItem("Ungroup");
		MenuItem itemUndo = new MenuItem("Undo");
		MenuItem itemRedo = new MenuItem("Redo");
		menuEdit.getItems().addAll(itemCopy,itemPaste,itemDelete,itemGroup,itemUngroup,itemUndo,itemRedo);
		
		
		MenuItem itemEllipse = new MenuItem("Ellipse");
		MenuItem itemBox = new MenuItem("Box");
		MenuItem itemImage = new MenuItem("Img...");
		MenuItem itemSelect = new MenuItem("Select");
		menuTools.getItems().addAll(itemBox, itemEllipse, itemImage, itemSelect);
		
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuFile, menuEdit, menuTools);
		
		
		Button buttonBox = new Button("Box");
		Button buttonEllipse = new Button("Ellipse");
		Button buttonImg = new Button("Img...");
		Button buttonSelect = new Button("Select");
		

		
		
		ToolBar shapeBar = new ToolBar(buttonBox , buttonEllipse , buttonImg, buttonSelect);

		for(int i=0;i<10;i++) {
			colorsRec[i] = new Rectangle(30,30);
			colorsRec[i].setFill(colors[i]);
			Rectangle temp = colorsRec[i];
			temp.setOnMousePressed(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					now = (Color) temp.getFill();
					tool.setColor(now);
				}
			});
		}
		
		ToolBar colorBar = new ToolBar(colorsRec);
		

		
		EventHandler<ActionEvent> boxHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) { tool = toolRect; tool.setColor(now); }
		};	
		
		EventHandler<ActionEvent> eliHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) { tool = toolEli; tool.setColor(now); }
		};	

		EventHandler<ActionEvent> imgHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) { 
		    	FileChooser file = new FileChooser();
				file.setTitle("Choose File");
				file.getExtensionFilters().addAll( new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File f = file.showOpenDialog(stage);
				if (f != null) tool = new ToolImage(f); }
		};
		
		EventHandler<ActionEvent> selectHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) { tool = toolSelect;}
		};

	
		
		itemNew.setOnAction( e -> { new EditorWindow(new Stage());  }); 			
		itemClose.setOnAction( e -> { stage.close(); cb.removeListener(this);});
		itemSave.setOnAction( e -> { 

			FileChooser file = new FileChooser();
			file.setTitle("Save File");
			file.getExtensionFilters().addAll( new ExtensionFilter("VG Files", "*.vg"));
			file.setInitialFileName("SavedWork");
			File f = file.showSaveDialog(stage);
			if (f != null) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
					out.writeObject(board);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		
		});

		itemOpen.setOnAction( e -> { 

			FileChooser file = new FileChooser();
			file.setTitle("Open File");
			file.getExtensionFilters().addAll( new ExtensionFilter("VG Files", "*.vg"));
			File f = file.showOpenDialog(stage);
			if (f != null) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					board = (Board) in.readObject();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			draw();
		
		});

		itemCopy.setOnAction( e -> { 
			cb.copyToClipboard(selected.getContents());
		  }); 
		itemPaste.setOnAction( e -> { 
			//board.addClip(cb.copyFromClipboard());
			CommandAdd ca = new CommandAdd(inter,cb.copyFromClipboard());
			ca.execute();
			inter.getUndoStack().addCommand(ca);
			draw();
		  }); 
		itemDelete.setOnAction( e -> { 
			board.removeClip(selected.getContents());
			cb.clear(); 
			draw();  }); 
		
		itemBox.setOnAction(boxHandler);
		itemEllipse.setOnAction(eliHandler);
		itemImage.setOnAction(imgHandler);
		itemSelect.setOnAction(selectHandler);
		
		buttonBox.setOnAction(boxHandler);
		buttonEllipse.setOnAction(eliHandler);
		buttonImg.setOnAction(imgHandler);
		buttonSelect.setOnAction(selectHandler);


		itemGroup.setOnAction( e -> { 
			CommandGroup cg = new CommandGroup(inter,selected.getContents());
			cg.execute();
			inter.getUndoStack().addCommand(cg);
			/*ClipGroup g = new ClipGroup();
			for(Clip c:selected.getContents()){
				g.addClip(c);
			}
			board.addClip(g);*/
		}); 
		
		itemUngroup.setOnAction( e -> { 	
			if(selected.getContents().size()==1 && (selected.getContents().get(0) instanceof ClipGroup)){

				CommandUngroup cu = new CommandUngroup(inter,(ClipGroup) selected.getContents().get(0));
				cu.execute();
				inter.getUndoStack().addCommand(cu);
			}
		}); 
		
		itemUndo.setOnAction( e -> {  inter.getUndoStack().undo(); draw();}); 	
		
		itemRedo.setOnAction( e -> { inter.getUndoStack().redo(); draw();}); 
		

		
		canvas.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){tool.press(inter, e);}
		});
		
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){tool.drag(inter, e); draw(); tool.drawFeedback(inter, canvas.getGraphicsContext2D()); }
		});
		
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){tool.release(inter, e); draw();}
		});

		

		vbox.getChildren().addAll(menuBar, shapeBar, colorBar, canvas);
		stage.setScene(new Scene(vbox));
		board.draw(canvas.getGraphicsContext2D());
		stage.show();
	}

	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return selected;
	}

	@Override
	public CommandStack getUndoStack() {
		return cs;
	}
	public void draw(){
		board.draw(canvas.getGraphicsContext2D());
		canvas.getGraphicsContext2D().setFill(Color.WHITE);
		for(Clip c : board.getContents()) {
			c.draw(canvas.getGraphicsContext2D());
		}
		
	}


	@Override
	public void clipboardChanged() {
		if(cb.isEmpty() ) {
			itemPaste.setDisable(true);
		} else {
			itemPaste.setDisable(false);
		}
	}
}
