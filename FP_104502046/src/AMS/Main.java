package AMS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Main extends Application{
	
	static Stage stage;
	//public Parent create_fxml;
	public static Parent main_fxml;
	public static FXMLLoader main_loader;
	static Map<String,FXMLLoader> createpageMap=new HashMap<>();
	static Map<String,FXMLLoader> subjectrowMap=new HashMap<>();
	static Map<String,FXMLLoader> subjectpageMap=new HashMap<>();
	
	static Map<String,Parent> PaneCreatepageMap=new HashMap<>();
	static Map<String,VBox> PaneSubjectrowMap=new HashMap<>();
	static Map<String,Parent> PaneSubjectpageMap=new HashMap<>();
	
	static ArrayList<String> CPname=new ArrayList<>();
	@Override
	public void start(Stage Pstage){
		try{
			//main_fxml=FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			main_loader=new FXMLLoader(getClass().getResource("MainPage.fxml"));
			//create_fxml=FXMLLoader.load(getClass().getResource("CreatingPage.fxml"));//load fxml
			stage=Pstage;
			main_fxml=(Parent)main_loader.load();
			stage.setScene(new Scene(main_fxml));
			stage.setTitle("AMS");
			stage.centerOnScreen();
			stage.show();
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	public void changepage(int page,String key){
		if(page==0){//click "完成" in creatingpage
			if(CreatingPage.createmode){
				stage.getScene().setRoot(main_fxml);
				CreatingPage.createmode=false;
			}
			else if(key.equals("mp")){//after click "編輯" under SubjectRow 
				stage.getScene().setRoot(main_fxml);
				CreatingPage.underSR=false;
			}
			else{
				stage.getScene().setRoot(PaneSubjectpageMap.get(key));
			}
		}
		if(page==1){//click "建立新科目"
			CreatingPage.createmode=true;
			try{
				FXMLLoader CP=new FXMLLoader(getClass().getResource("CreatingPage.fxml"));
				createpageMap.put("unknown", CP);//new CreatingPage
				CPname.add("unknown");
				PaneCreatepageMap.put("unknown", createpageMap.get("unknown").load());
				stage.getScene().setRoot(PaneCreatepageMap.get("unknown"));
			}
			catch(Exception ex){
				System.out.println(ex);
			}
		}
		if(page==2){//click "編輯"
			try{
				stage.getScene().setRoot(PaneCreatepageMap.get(key));
			}
			catch(Exception ex){
				System.out.println(ex);
			}
		}
		if(page==3){//click pane of SubjectRow
			stage.getScene().setRoot(PaneSubjectpageMap.get(key));
		}
		if(page==4){//back to MP
			stage.getScene().setRoot(main_fxml);
		}
	}
	public void changepage(int page,ScoreRow SCRcontroller){
		ScorePage SCPcontroller=SCRcontroller.scorepagefxml.getController();
		if(page==5){//click "編輯得分" on SCR
			SCPcontroller.SPbefore=stage.getScene().getRoot();
			//SCPcontroller.thisSCRcontroller=SCRcontroller;
			stage.getScene().setRoot(SCRcontroller.PaneScorePage);
		}
	}
	public void changepage(int page,ScorePage SCPcontroller){
		if(page==6){
			goalachieve(SCPcontroller);
			stage.getScene().setRoot(SCPcontroller.SPbefore);
		}
	}
	public void goalachieve(ScorePage SCPcontroller){
		SubjectPage SPcon=SCPcontroller.thisSCRcontroller.thisSPcontroller;
		SPcon.ensuretotal=SPcon.ensuretotal-SCPcontroller.enscorebefore+SCPcontroller.ensurescore;
		SCPcontroller.enscorebefore=SCPcontroller.ensurescore;
		System.out.println(SPcon.SPtitle.getText());
		System.out.println(createpageMap.get(SPcon.SPtitle.getText()));
		CreatingPage CPcon=createpageMap.get(SPcon.SPtitle.getText()).getController();
		SubjectRow SRcon=subjectrowMap.get(SPcon.SPtitle.getText()).getController();
		for(int i=0; 2*i<CPcon.goaltext.size(); i++){//check if the goal is achieved
			double goal=Double.valueOf(CPcon.goaltext.get(2*i+1).getText());
			if(SPcon.ensuretotal>=goal){
				Label Gn=(Label)SPcon.SPgoalpane.getChildren().get(2*i);
				Gn.setTextFill(Color.BLUE);
				Label Gs=(Label)SPcon.SPgoalpane.getChildren().get(2*i+1);
				Gs.setTextFill(Color.BLUE);
				Label SRGn=(Label)SRcon.SRgoalspace.getChildren().get(i);
				SRGn.setTextFill(Color.BLUE);
			}
			else{
				if(SPcon.SPgoalpane.getChildren().size()!=0){
					Label Gn=(Label)SPcon.SPgoalpane.getChildren().get(2*i);
					Gn.setTextFill(Color.BLACK);
					Label Gs=(Label)SPcon.SPgoalpane.getChildren().get(2*i+1);
					Gs.setTextFill(Color.BLACK);
					Label SRGn=(Label)SRcon.SRgoalspace.getChildren().get(i);
					SRGn.setTextFill(Color.BLACK);
				}
			}
		}
	}
}
