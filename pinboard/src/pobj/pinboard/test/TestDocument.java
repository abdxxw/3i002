package pobj.pinboard.test;
import pobj.pinboard.document.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TestDocument extends javafx.application.Application {
	@Override
	public void start(javafx.stage.Stage stage) throws Exception {
		Canvas canvas = new Canvas(800, 600);
		VBox vbox = new VBox();
		vbox.getChildren().add(canvas);
		stage.setScene(new javafx.scene.Scene(vbox));
		Board board = new Board();
		board.addClip(new ClipRect(100, 100, 300, 200, Color.BLUE));
		board.addClip(new ClipEllipse(200, 150, 400, 250, Color.RED));
		board.draw(canvas.getGraphicsContext2D());
		stage.show();
	}
	public static void main(String[] args) { launch(args); }
}
