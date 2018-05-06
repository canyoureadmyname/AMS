package AMS;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SubjectPage {
	@FXML public Text SPtitle;
	@FXML public Label SPnote;
	@FXML public Label scentotal;
	@FXML public Label sctotal;
	@FXML public Label score;
	@FXML public Button SPbacktoMP;
	@FXML private Button SPedit;
	@FXML public VBox SPscorepane;
	@FXML public GridPane SPgoalpane;
	public double ensuretotal=0;
	public double totalscore=0;
	public Map<String,FXMLLoader> scorerowMap=new HashMap<>();
	public Map<String,VBox> PaneScorerowMap=new HashMap<>();
	Main M=new Main();
	public SubjectPage(){
		SPtitle=new Text();
		SPnote=new Label();
		SPbacktoMP=new Button();
		SPedit=new Button();
		SPscorepane=new VBox();
		SPgoalpane=new GridPane();
		scentotal=new Label();
		sctotal=new Label();
		score=new Label();
	}
	@FXML
	private void backtoMP(ActionEvent e){
		M.changepage(4, "");
	}
	@FXML
	private void SPgocreatepage(ActionEvent e){
		M.changepage(2, SPtitle.getText());
	}
	public void scorecalculate(){
		
	}
}
