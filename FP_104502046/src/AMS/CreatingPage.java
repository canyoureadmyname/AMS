package AMS;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class CreatingPage{
	@FXML TextField createdsubject;
	
	@FXML TextArea creatednotearea;
	
	@FXML Label labeltotalscore;
	
	@FXML GridPane weightedpane;
	@FXML GridPane goalpane;
	
	@FXML ScrollPane creatingroot;
	
	@FXML Button btnnewweighted;
	@FXML Button btnnewgoal;
	@FXML Button btncompletingcreate;
	@FXML Button weightedremove;
	@FXML Button goalremove;
	
	String storecreatedsubject;
	
	TextArea storecreatednotearea;
	int Wj=2;//row of weightedpane
	int Wk=0;//order of item of weightedscoretext
	int Gj=2;//row of goalpane
	int Gk=0;//order of item of goaltext
	int oldWL;//stored weightedscorelength before edit
	static boolean createmode=false;
	static boolean underSR=false;//whether click "編輯" under SubjectRow
	Main M=new Main();
	ArrayList<TextField> weightedscoretext=new ArrayList<TextField>();//store textfield of scorename & weightedscore(a set of two)
	ArrayList<TextField> goaltext=new ArrayList<TextField>();//store textfield of goalname & goalscore(a set of two)
	ArrayList<String> storegoaltext=new ArrayList<>();
	ArrayList<String> oldWn=new ArrayList<>();//stored weightedscorename before edit
	private Pattern numberonlytext=Pattern.compile("(\\d*)|(\\d+\\.\\d*)");//regex for textfield only can input Double number
	private ChangeListener<String> inputscorechanged=new ChangeListener<String>(){//for add all weightedscore and display in label
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			double total=0;
			for(int i=0; i*2+1<weightedscoretext.size(); i++){
				if(weightedscoretext.get(2*i+1).getText().equals("")){//when textfield is empty
					total=0;
				}
				else{	
					total=total+Double.valueOf(weightedscoretext.get(2*i+1).getText());
				}
			}
			labeltotalscore.setText("目前總分:"+total+"%");
		}
	};
	public CreatingPage(){
		createdsubject=new TextField();
		weightedpane=new GridPane();
		creatednotearea=new TextArea();
		goalpane=new GridPane();
		btnnewweighted=new Button();
		btnnewgoal=new Button();
		btncompletingcreate=new Button();
		labeltotalscore=new Label();
		createdsubject.textProperty().addListener((ov,oldvalue,newvalue)->{
			
		});
		/*weightedscoretext.add(new TextField());//create default weighted row ----top----
		weightedscoretext.add(new TextField());
		weightedscoretext.get(k).setFont(new Font(25));
		weightedscoretext.get(k+1).setFont(new Font(25));
		Label name=new Label("項目名稱: ");
		Label weighted=new Label("佔分(%):");
		name.setFont(new Font(35));
		weighted.setFont(new Font(35));
		weightedpane.add(name, 0, j);
		weightedpane.add(weighted, 2, j);
		weightedpane.add(weightedscoretext.get(k++), 1, j);
		weightedpane.add(weightedscoretext.get(k++), 3, j);
		GridPane.setHalignment(name,HPos.RIGHT);
		j++;//----bottom----*/
		//System.out.print(weightedpane.getChildren().get(1)+"!!");
	}
	@FXML
	private void Newgoal(ActionEvent e){//click "新增目標" create a new row
		goaltext.add(new TextField());
		goaltext.add(new TextField());
		goaltext.get(Gk).setFont(new Font(20));
		goaltext.get(Gk+1).setFont(new Font(20));
		goaltext.get(Gk).setMinHeight(TextField.USE_PREF_SIZE);
		goaltext.get(Gk+1).setMinHeight(TextField.USE_PREF_SIZE);
		goaltext.get(Gk).setPrefHeight(40);
		goaltext.get(Gk+1).setPrefHeight(40);
		goaltext.get(Gk+1).setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0,//for textfield only can input Double number
				change->{
					String newtext=change.getControlNewText();
					if(numberonlytext.matcher(newtext).matches()){
						return change;
					}
					else
						return null;
				}));
		Label name=new Label("目標名稱: ");
		Label goal=new Label("目標分數:");
		name.setFont(new Font(35));
		goal.setFont(new Font(35));
		goalpane.add(name, 0, Gj);
		goalpane.add(goal, 2, Gj);
		goalpane.add(goaltext.get(Gk++), 1, Gj);
		goalpane.add(goaltext.get(Gk++), 3, Gj);
		GridPane.setHalignment(name,HPos.RIGHT);
		GridPane.setHalignment(goal, HPos.LEFT);
		Gj++;
	}
	@FXML
	private void Newweighted(ActionEvent e){//click "新增項目" create a new row
		weightedscoretext.add(new TextField());
		weightedscoretext.add(new TextField());
		weightedscoretext.get(Wk).setFont(new Font(20));
		weightedscoretext.get(Wk+1).setFont(new Font(20));
		weightedscoretext.get(Wk).setMinHeight(TextField.USE_PREF_SIZE);
		weightedscoretext.get(Wk+1).setMinHeight(TextField.USE_PREF_SIZE);
		weightedscoretext.get(Wk).setPrefHeight(40);
		weightedscoretext.get(Wk+1).setPrefHeight(40);
		weightedscoretext.get(Wk+1).setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0,//for textfield only can input Double number
				change->{
					String newtext=change.getControlNewText();
					if(numberonlytext.matcher(newtext).matches()){
						return change;
					}
					else
						return null;
				}));
		weightedscoretext.get(Wk+1).textProperty().addListener(inputscorechanged);//when input score
		Label name=new Label("項目名稱: ");
		Label weighted=new Label("佔分(%):");
		name.setFont(new Font(35));
		weighted.setFont(new Font(35));
		weightedpane.add(name, 0, Wj);
		weightedpane.add(weighted, 2, Wj);
		weightedpane.add(weightedscoretext.get(Wk++), 1, Wj);
		weightedpane.add(weightedscoretext.get(Wk++), 3, Wj);
		GridPane.setHalignment(name,HPos.RIGHT);
		Wj++;
	}
	@FXML
	private void removeweighted(ActionEvent e){//remove bottom of row of weighted 
		if(weightedscoretext.size()!=0){
			weightedscoretext.remove(--Wk);
			weightedscoretext.remove(--Wk);
			weightedpane.getChildren().remove(weightedpane.getChildren().size()-4,weightedpane.getChildren().size());
			Wj--;
		}
	}
	@FXML
	private void removegoal(ActionEvent e){//remove bottom of row of goal 
		if(goaltext.size()!=0){
			goaltext.remove(--Gk);
			goaltext.remove(--Gk);
			goalpane.getChildren().remove(goalpane.getChildren().size()-4,goalpane.getChildren().size());
			Gj--;
		}
	}
	@FXML
	private void completing(ActionEvent e){//click "完成"
		if(createmode){
			try{
				String key=createdsubject.getText();
				boolean repeat=false;
				if(Main.createpageMap.keySet().contains(key)){//examine if there is repeat
					repeat=true;	
				}
				if(!repeat){
					FXMLLoader SR=new FXMLLoader(getClass().getResource("SubjectRow.fxml"));
					Main.subjectrowMap.put(key, SR);//new SubjectRow
					Main.PaneSubjectrowMap.put(key, Main.subjectrowMap.get(key).load());
					MainPage MPcontroller=Main.main_loader.getController();
					MPcontroller.SRspace.getChildren().add(Main.PaneSubjectrowMap.get(key));//add new SR in MainPage
					SubjectRow SRcontroller=SR.getController();
					SRcontroller.SRtitle.setText(key);//set SubjectRow title
					for(int i=0; 2*i<goaltext.size();i++){
						if(!goaltext.get(2*i).getText().equals("")){//display goal name in SubjectRow
							Label g=new Label(goaltext.get(2*i).getText());
							g.setPadding(new Insets(5,0,0,100));
							g.setFont(new Font(25));
							SRcontroller.SRgoalspace.getChildren().add(g);
						}
					}
					FXMLLoader buf=Main.createpageMap.remove("unknown");
					Parent Panebuf=Main.PaneCreatepageMap.remove("unknown");
					Main.createpageMap.put(key, buf);//change key name
					Main.PaneCreatepageMap.put(key, Panebuf);
					createsubjectpage();
					Main.CPname.set(Main.CPname.indexOf("unknown"), key);//use for save data
					storecreatedsubject=createdsubject.getText();//store subject name
					for(int i=0; i<goaltext.size(); i++){
						storegoaltext.add(goaltext.get(i).getText());//store goaltext's text
					}
					M.changepage(0,"");
				}
				else{//if there is repeat pop up an error window
					Stage rewindow=new Stage();
					rewindow.setTitle("error");
					Label ertext=new Label("Error:不可使用重複的科目名稱");
					ertext.setFont(Font.font("Yu Gothic",FontWeight.BOLD,35));
					ertext.setTextFill(Color.RED);
					rewindow.setScene(new Scene(ertext));
					rewindow.show();
				}
			}
			catch(Exception ex){
				if(ex.getMessage().equals("reWS")){
					System.out.println("repeat Weigthed score");
				}
				else{
					System.out.println(ex);
				}
			}
		}
		else{
			SubjectRow SRcontroller=Main.subjectrowMap.get(storecreatedsubject).getController();
			SubjectPage SPcontroller=Main.subjectpageMap.get(storecreatedsubject).getController();
			if(!storecreatedsubject.equals(createdsubject.getText())){//examine if subjectname changed
				String key=createdsubject.getText();
				boolean repeat=false;
				if(Main.createpageMap.keySet().contains(key)){//examine if there is repeat
					repeat=true;	
				}
				if(!repeat){
					try{
						FXMLLoader SRbuf=Main.subjectrowMap.remove(storecreatedsubject);
						VBox SRPanebuf=Main.PaneSubjectrowMap.remove(storecreatedsubject);
						Main.subjectrowMap.put(key, SRbuf);//change key name
						Main.PaneSubjectrowMap.put(key, SRPanebuf);
						SRcontroller.SRtitle.setText(key);//edit SubjectRow title
						FXMLLoader SPbuf=Main.subjectpageMap.remove(storecreatedsubject);
						Parent SPPanebuf=Main.PaneSubjectpageMap.remove(storecreatedsubject);
						Main.subjectpageMap.put(key, SPbuf);//change key name
						Main.PaneSubjectpageMap.put(key, SPPanebuf);
						SPcontroller.SPtitle.setText(key);//edit SubjectPage title
						FXMLLoader CPbuf=Main.createpageMap.remove(storecreatedsubject);
						Parent PaneCPbuf=Main.PaneCreatepageMap.remove(storecreatedsubject);
						Main.CPname.set(Main.CPname.indexOf(storecreatedsubject), key);
						Main.createpageMap.put(key, CPbuf);//change key name
						Main.PaneCreatepageMap.put(key, PaneCPbuf);
						
						storecreatedsubject=createdsubject.getText();//store subject name
					}
					catch(Exception ex){
						System.out.println(ex);
					}
				}
				else{//if there is repeat pop up an error window
					Stage rewindow=new Stage();
					rewindow.setTitle("error");
					Label ertext=new Label("Error:不可使用重複的科目名稱");
					ertext.setFont(Font.font("Yu Gothic",FontWeight.BOLD,35));
					ertext.setTextFill(Color.RED);
					rewindow.setScene(new Scene(ertext));
					rewindow.show();
				}
			}
			if(SRcontroller.SRgoalspace.getChildren().size()>goaltext.size()/2){//remove which be remove goal on SubjectRow
				SRcontroller.SRgoalspace.getChildren().remove(goaltext.size()/2, SRcontroller.SRgoalspace.getChildren().size());
			}
			for(int i=0; 2*i<goaltext.size();i++){
				if(!goaltext.get(2*i).getText().equals("")){//display goal name in SubjectRow
					Label g=new Label(goaltext.get(2*i).getText());
					g.setPadding(new Insets(5,0,0,100));
					g.setFont(new Font(25));
					try{
						SRcontroller.SRgoalspace.getChildren().set(i, g);
					}
					catch(Exception ex){//if add new goal(make it out of boundary
						SRcontroller.SRgoalspace.getChildren().add(g);
					}
				}
			}
			try{
				editSubjectPage(SPcontroller);
				if(underSR){
					M.changepage(0,"mp");
				}
				else{
					M.changepage(0,createdsubject.getText());
				}
			}
			catch(Exception ex){
				if(ex.getMessage().equals("reWS")){
					System.out.println("repeat Weigthed score");
				}
				else{
					System.out.println(ex);
				}
			}
		}
	}
	private void createsubjectpage()throws Exception{
		try{
			FXMLLoader SP=new FXMLLoader(getClass().getResource("SubjectPage.fxml"));
			Main.subjectpageMap.put(createdsubject.getText(), SP);//create new SubjectPage
			Main.PaneSubjectpageMap.put(createdsubject.getText(), SP.load());
			SubjectPage SPcontroller=SP.getController();
			SPcontroller.SPtitle.setText(createdsubject.getText());
			SPcontroller.SPnote.setText("備註: "+creatednotearea.getText());//note area display
			if(weightedscoretext.size()!=0){
				try{
					for(int i=0; 2*i<weightedscoretext.size(); i++){
						FXMLLoader SCR=new FXMLLoader(getClass().getResource("ScoreRow.fxml"));
						String key=weightedscoretext.get(2*i).getText();
						if(SPcontroller.scorerowMap.keySet().contains(key)){
							throw new Exception("reWS");
						}
						SPcontroller.scorerowMap.put(key, SCR);//create ScoreRow
						SPcontroller.PaneScorerowMap.put(key, SCR.load());
						ScoreRow SCRcontroller=SPcontroller.scorerowMap.get(key).getController();
						SCRcontroller.thisSPcontroller=SPcontroller;
						SCRcontroller.SCRtitle.setText(key);;//set title of ScoreRow
						SCRcontroller.SCRscore.setText("0.0%/0.0%/"+weightedscoretext.get(2*i+1).getText()+"%");//display how weighted
						SPcontroller.SPscorepane.getChildren().add(SPcontroller.PaneScorerowMap.get(key));
						oldWn.add(key);//store it use for editing condition
					}
					oldWL=weightedscoretext.size();
				}
				catch(Exception ex){
					if(ex.getMessage().equals("reWS")){
						SPcontroller.scorerowMap.clear();//remove all
						SPcontroller.PaneScorerowMap.clear();
						SPcontroller.SPscorepane.getChildren().clear();
						oldWn.clear();
						Stage rewindow=new Stage();
						rewindow.setTitle("error");
						Label ertext=new Label("Error:不可使用同樣的加權項目名稱");
						ertext.setFont(Font.font("Yu Gothic",FontWeight.BOLD,35));
						ertext.setTextFill(Color.RED);
						rewindow.setScene(new Scene(ertext));
						rewindow.show();
						throw ex;
					}
					else{
						System.out.println(ex);
					}
				}
			}
			if(goaltext.size()!=0){
				for(int i=0; 2*i<goaltext.size(); i++){//display goal on SubjectPage
					Label Gname=new Label(">"+goaltext.get(2*i).getText()+"<");
					Label Gscore=new Label("達成需求:"+goaltext.get(2*i+1).getText()+"%");
					Gname.setFont(new Font(40));
					Gname.setPadding(new Insets(0,0,0,10));
					Gscore.setFont(new Font(40));
					SPcontroller.SPgoalpane.add(Gname, 0, i);
					SPcontroller.SPgoalpane.add(Gscore, 1, i);
				}
			}
		}
		catch(Exception ex){
			System.out.println(ex);
			if(ex.getMessage().equals("reWS")){
				throw ex;
			}
		}
	}
	private void editSubjectPage(SubjectPage SPcontroller) throws Exception{
		SPcontroller.SPnote.setText("備註: "+creatednotearea.getText());
		if(goaltext.size()!=0){
			SPcontroller.SPgoalpane.getChildren().clear();//remove all and redisplay goal 
			for(int i=0; 2*i<goaltext.size(); i++){
				Label Gname=new Label(">"+goaltext.get(2*i).getText()+"<");
				Label Gscore=new Label("達成需求:"+goaltext.get(2*i+1).getText()+"%");
				Gname.setFont(new Font(40));
				Gname.setPadding(new Insets(0,0,0,10));
				Gscore.setFont(new Font(40));
				SPcontroller.SPgoalpane.add(Gname, 0, i);
				SPcontroller.SPgoalpane.add(Gscore, 1, i);
			}
		}
		if(weightedscoretext.size()!=0){
			try{
				int size=0;
				boolean expand=false;
				ArrayList<FXMLLoader> bufSCR=new ArrayList<>();
				ArrayList<VBox> bufSCRPane=new ArrayList<>();
				ArrayList<Integer> numOfchange=new ArrayList<>();
				if(oldWL<weightedscoretext.size()){
					size=oldWL;
					expand=true;
				}
				else{
					size=weightedscoretext.size();
					expand=false;
				}
				for(int i=0; 2*i<weightedscoretext.size(); i++){
					String name=weightedscoretext.get(2*i).getText();
					for(int k=i+1; 2*k<weightedscoretext.size(); k++){
						if(weightedscoretext.get(2*k).getText().equals(name)){//examine whether has repeat name
							System.out.println(k);
							throw new Exception("reWS");
						}
					}
				}
				for(int i=0; 2*i<size; i++){
					String key=weightedscoretext.get(2*i).getText();
					if(!oldWn.get(i).equals(key)){//change name?
						numOfchange.add(i);//record which No. change
						FXMLLoader Lbuf=SPcontroller.scorerowMap.remove(oldWn.get(i));
						VBox Vbuf=SPcontroller.PaneScorerowMap.remove(oldWn.get(i));
						bufSCR.add(Lbuf);
						bufSCRPane.add(Vbuf);
					}
				}
				if(!expand){//remove which be removed
					for(int i=oldWn.size()-1; i>=size/2; i--){
						SPcontroller.scorerowMap.remove(oldWn.get(i));
						SPcontroller.PaneScorerowMap.remove(oldWn.get(i));
						SPcontroller.SPscorepane.getChildren().remove(i);
					}
				}
				else{//add new items
					for(int i=size/2; 2*i<weightedscoretext.size(); i++){
						FXMLLoader SCR=new FXMLLoader(getClass().getResource("ScoreRow.fxml"));
						String key=weightedscoretext.get(2*i).getText();
						SPcontroller.scorerowMap.put(key, SCR);//create ScoreRow
						SPcontroller.PaneScorerowMap.put(key, SCR.load());
						ScoreRow SCRcontroller=SPcontroller.scorerowMap.get(key).getController();
						SCRcontroller.SCRtitle.setText(key);;//set title of ScoreRow
						ScorePage SCPcontroller=SCRcontroller.scorepagefxml.getController();
						double ensc=SCPcontroller.ensurescore;
						double tosc=SCPcontroller.totalscore;
						SCRcontroller.SCRscore.setText(ensc+"%/"+tosc+"%/"+weightedscoretext.get(2*i+1).getText()+"%");//display how weighted
						SPcontroller.SPscorepane.getChildren().add(SPcontroller.PaneScorerowMap.get(key));
					}
				}
				for(int i=0; i<numOfchange.size(); i++){//display changed item 
					String key=weightedscoretext.get(2*numOfchange.get(i)).getText();
					SPcontroller.scorerowMap.put(key, bufSCR.get(i));
					SPcontroller.PaneScorerowMap.put(key, bufSCRPane.get(i));
					ScoreRow SCRcontroller=SPcontroller.scorerowMap.get(key).getController();
					SCRcontroller.SCRtitle.setText(key);
					
				}
				for(int i=0; 2*i<size; i++){//changed weighted score?
					String key=weightedscoretext.get(2*i).getText();
					ScoreRow SCRcontroller=SPcontroller.scorerowMap.get(key).getController();
					ScorePage SCPcontroller=SCRcontroller.scorepagefxml.getController();
					double ensc=SCPcontroller.ensurescore;
					double tosc=SCPcontroller.totalscore;
					String score=ensc+"%/"+tosc+"%/"+weightedscoretext.get(2*i+1).getText()+"%";
					if(!SCRcontroller.SCRscore.getText().equals(score)){
						SCRcontroller.SCRscore.setText(score);
					}
				}
				oldWn=new ArrayList<>();
				for(int i=0; 2*i<weightedscoretext.size(); i++){
					oldWn.add(weightedscoretext.get(2*i).getText());
				}
				oldWL=weightedscoretext.size();
			}
			catch(Exception ex){
				if(ex.getMessage().equals("reWS")){
					Stage rewindow=new Stage();
					rewindow.setTitle("error");
					Label ertext=new Label("Error:不可使用同樣的加權項目名稱");
					ertext.setFont(Font.font("Yu Gothic",FontWeight.BOLD,35));
					ertext.setTextFill(Color.RED);
					rewindow.setScene(new Scene(ertext));
					rewindow.show();
					throw ex;
				}
				else{
					System.out.println(ex);
				}
			}
		}
		
	}
}
