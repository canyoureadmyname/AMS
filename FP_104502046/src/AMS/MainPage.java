package AMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class MainPage {
	@FXML Button btncreating; 
	@FXML Button openold;
	@FXML Button save;
	@FXML Button btnin;
	@FXML public VBox SRspace;
	Main M=new Main();
	public MainPage(){
		btncreating=new Button();
		openold=new Button();
		SRspace=new VBox();
		
	}
	@FXML
	private void gocreatingpage(ActionEvent e){
		M.changepage(1,"");
	}
	@FXML
	private void openold(ActionEvent e){
		FileChooser fc=new FileChooser();
		FileChooser.ExtensionFilter fef=new FileChooser.ExtensionFilter("AMS file(*.AMS)","*.AMS");
		fc.getExtensionFilters().add(fef);
		File file=fc.showOpenDialog(Main.stage);
		if(file!=null){
			try{
				Main.createpageMap.clear();//remove all of it's data --top-- 
				Main.PaneCreatepageMap.clear();
				Main.subjectrowMap.clear();
				Main.CPname.clear();
				Main.PaneSubjectrowMap.clear();
				Main.subjectpageMap.clear();
				Main.PaneSubjectpageMap.clear();//--bottom--
				SRspace.getChildren().clear();
				FileInputStream fis=new FileInputStream(file);
				Properties pt=new Properties();
				pt.loadFromXML(fis);
				int times=Integer.valueOf(pt.getProperty("CPMapsize"));
				for(int i=0; i<times; i++){//obtain CP data
					btncreating.fire();
					CreatingPage CPcon=Main.createpageMap.get("unknown").getController();
					CPcon.createdsubject.setText(pt.getProperty("CP"+i+".createdsubject"));
					CPcon.creatednotearea.setText(pt.getProperty("CP"+i+".creatednotearea"));
					int times2=Integer.valueOf(pt.getProperty("CP"+i+".weightedsize"));
					for(int k=0; k<times2; k++){
						CPcon.btnnewweighted.fire();
						CPcon.weightedscoretext.get(2*k).setText(pt.getProperty("CP"+i+".weighted"+k+".name"));
						CPcon.weightedscoretext.get(2*k+1).setText(pt.getProperty("CP"+i+".weighted"+k+".score"));
					}
					int times3=Integer.valueOf(pt.getProperty("CP"+i+".goalsize"));
					for(int k=0; k<times3 ;k++){
						CPcon.btnnewgoal.fire();
						CPcon.goaltext.get(2*k).setText(pt.getProperty("CP"+i+".goal"+k+".name"));
						CPcon.goaltext.get(2*k+1).setText(pt.getProperty("CP"+i+".goal"+k+".score"));
					}
					CPcon.btncompletingcreate.fire();
					SubjectPage SPcon=Main.subjectpageMap.get(CPcon.createdsubject.getText()).getController();
					for(int k=0; k<times2; k++){//obtain SCP data
						ScoreRow SCRcon=SPcon.scorerowMap.get(CPcon.weightedscoretext.get(2*k).getText()).getController();
						ScorePage SCPcon=SCRcon.scorepagefxml.getController();
						SCRcon.btneditscore.fire();//because SCP need SCRcontroller and this will give it
						int times4=Integer.valueOf(pt.getProperty("SP"+i+".SCR"+k+".SCP.namesize"));
						for(int l=0; l<times4; l++){
							SCPcon.btnnew.fire();
							SCPcon.SCPname.get(l).setText(pt.getProperty("SP"+i+".SCR"+k+".SCP.name"+l));
							SCPcon.SCPscore.get(l).setText(pt.getProperty("SP"+i+".SCR"+k+".SCP.score"+l));
							if(pt.getProperty("SP"+i+".SCR"+k+".SCP.pre"+l).equals("true")){
								SCPcon.SCPprediction.get(l).setSelected(true);
							}
							else{
								SCPcon.SCPprediction.get(l).setSelected(false);
							}
						}
						SCPcon.btncomplete.fire();
					}
					SPcon.SPbacktoMP.fire();//back to MainPage
				}
			}
			catch(Exception ex){
				Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	@FXML
	private void in(ActionEvent e){
		FileChooser fc=new FileChooser();
		FileChooser.ExtensionFilter fef=new FileChooser.ExtensionFilter("AMS file(*.AMS)","*.AMS");
		fc.getExtensionFilters().add(fef);
		File file=fc.showOpenDialog(Main.stage);
		if(file!=null){
			try{
				FileInputStream fis=new FileInputStream(file);
				Properties pt=new Properties();
				pt.loadFromXML(fis);
				int times=Integer.valueOf(pt.getProperty("CPMapsize"));
				for(int i=0; i<times; i++){//obtain CP data
					btncreating.fire();
					CreatingPage CPcon=Main.createpageMap.get("unknown").getController();
					CPcon.createdsubject.setText(pt.getProperty("CP"+i+".createdsubject"));
					CPcon.creatednotearea.setText(pt.getProperty("CP"+i+".creatednotearea"));
					int times2=Integer.valueOf(pt.getProperty("CP"+i+".weightedsize"));
					for(int k=0; k<times2; k++){
						CPcon.btnnewweighted.fire();
						CPcon.weightedscoretext.get(2*k).setText(pt.getProperty("CP"+i+".weighted"+k+".name"));
						CPcon.weightedscoretext.get(2*k+1).setText(pt.getProperty("CP"+i+".weighted"+k+".score"));
					}
					int times3=Integer.valueOf(pt.getProperty("CP"+i+".goalsize"));
					for(int k=0; k<times3 ;k++){
						CPcon.btnnewgoal.fire();
						CPcon.goaltext.get(2*k).setText(pt.getProperty("CP"+i+".goal"+k+".name"));
						CPcon.goaltext.get(2*k+1).setText(pt.getProperty("CP"+i+".goal"+k+".score"));
					}
					CPcon.btncompletingcreate.fire();
					SubjectPage SPcon=Main.subjectpageMap.get(CPcon.createdsubject.getText()).getController();
					for(int k=0; k<times2; k++){//obtain SCP data
						ScoreRow SCRcon=SPcon.scorerowMap.get(CPcon.weightedscoretext.get(2*k).getText()).getController();
						ScorePage SCPcon=SCRcon.scorepagefxml.getController();
						SCRcon.btneditscore.fire();//because SCP need SCRcontroller and this will give it
						int times4=Integer.valueOf(pt.getProperty("SP"+i+".SCR"+k+".SCP.namesize"));
						for(int l=0; l<times4; l++){
							SCPcon.btnnew.fire();
							SCPcon.SCPname.get(l).setText(pt.getProperty("SP"+i+".SCR"+k+".SCP.name"+l));
							SCPcon.SCPscore.get(l).setText(pt.getProperty("SP"+i+".SCR"+k+".SCP.score"+l));
							if(pt.getProperty("SP"+i+".SCR"+k+".SCP.pre"+l).equals("true")){
								SCPcon.SCPprediction.get(l).setSelected(true);
							}
							else{
								SCPcon.SCPprediction.get(l).setSelected(false);
							}
						}
						SCPcon.btncomplete.fire();
					}
					SPcon.SPbacktoMP.fire();//back to MainPage
				}
			}
			catch(Exception ex){
				Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	@FXML
	private void save(ActionEvent e){
		FileChooser fc=new FileChooser();
		FileChooser.ExtensionFilter fef=new FileChooser.ExtensionFilter("AMS file(*.AMS)","*.AMS");
		fc.getExtensionFilters().add(fef);
		File file=fc.showSaveDialog(Main.stage);
		if(file!=null){
			try{
				Properties pt=new Properties();
				pt.setProperty("CPMapsize",String.valueOf(Main.createpageMap.size()));
				for(int i=0; i<Main.createpageMap.size(); i++){//store CP data
					CreatingPage CPcon=Main.createpageMap.get(Main.CPname.get(i)).getController();
					pt.setProperty("CP"+i+".createdsubject", CPcon.createdsubject.getText());
					pt.setProperty("CP"+i+".creatednotearea", CPcon.creatednotearea.getText());
					pt.setProperty("CP"+i+".weightedsize", String.valueOf(CPcon.weightedscoretext.size()/2));
					for(int k=0; 2*k<CPcon.weightedscoretext.size(); k++){
						pt.setProperty("CP"+i+".weighted"+k+".name", CPcon.weightedscoretext.get(2*k).getText());
						pt.setProperty("CP"+i+".weighted"+k+".score", CPcon.weightedscoretext.get(2*k+1).getText());
						SubjectPage SPcon=Main.subjectpageMap.get(CPcon.createdsubject.getText()).getController();
						ScoreRow SCRcon=SPcon.scorerowMap.get(CPcon.weightedscoretext.get(2*k).getText()).getController();
						ScorePage SCPcon=SCRcon.scorepagefxml.getController();
						pt.setProperty("SP"+i+".SCR"+k+".SCP.namesize", String.valueOf(SCPcon.SCPname.size()));
						for(int l=0; l<SCPcon.SCPname.size(); l++){//store SCP data
							pt.setProperty("SP"+i+".SCR"+k+".SCP.name"+l, SCPcon.SCPname.get(l).getText());
							pt.setProperty("SP"+i+".SCR"+k+".SCP.score"+l, SCPcon.SCPscore.get(l).getText());
							if(SCPcon.SCPprediction.get(l).isSelected()){
								pt.setProperty("SP"+i+".SCR"+k+".SCP.pre"+l, "true");
							}
							else{
								pt.setProperty("SP"+i+".SCR"+k+".SCP.pre"+l, "false");
							}
						}
					}
					pt.setProperty("CP"+i+".goalsize", String.valueOf(CPcon.goaltext.size()/2));
					for(int k=0; 2*k<CPcon.goaltext.size(); k++){
						pt.setProperty("CP"+i+".goal"+k+".name", CPcon.goaltext.get(2*k).getText());
						pt.setProperty("CP"+i+".goal"+k+".score", CPcon.goaltext.get(2*k+1).getText());
					}
				}
				FileOutputStream fs=new FileOutputStream(file);
				pt.storeToXML(fs, "data", "UTF-8");
				fs.close();
			}
			catch(Exception ex){
				Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
