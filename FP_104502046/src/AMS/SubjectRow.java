package AMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SubjectRow {
	@FXML public Text SRtitle;
	@FXML private Button btnSRcreate;
	@FXML private Button btnSRremove;
	@FXML public VBox SRgoalspace;
	@FXML private VBox SRpane;
	@FXML public Label SRensc;
	@FXML public Label SRtosc;
	@FXML public Label SRsc;
	private Main M=new Main();
	public SubjectRow(){
		SRtitle=new Text();
		SRgoalspace=new VBox();
		SRpane=new VBox();
		btnSRcreate=new Button();
		btnSRremove=new Button();
		SRensc=new Label();
		SRtosc=new Label();
		SRsc=new Label();
	}
	@FXML
	private void goCreatingPage(ActionEvent e){
		CreatingPage.underSR=true;
		M.changepage(2, SRtitle.getText());
	}
	@FXML
	private void removeSR(ActionEvent e){
		String key=SRtitle.getText();
		MainPage MPcontroller=Main.main_loader.getController();
		MPcontroller.SRspace.getChildren().remove(Main.PaneSubjectrowMap.get(key));//remove SubjectRow on MainPage
		Main.createpageMap.remove(key);//remove all of it's data --top-- 
		Main.PaneCreatepageMap.remove(key);
		Main.subjectrowMap.remove(key);
		Main.CPname.remove(key);
		Main.subjectpageMap.remove(key);
		Main.PaneSubjectpageMap.remove(key);
		Main.PaneSubjectrowMap.remove(key);//--bottom--
	}
	@FXML
	private void goSubjectPage(MouseEvent e){
		M.changepage(3, SRtitle.getText());
	}
}
