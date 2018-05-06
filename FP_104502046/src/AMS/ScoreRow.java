package AMS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScoreRow {
	@FXML public Label SCRtitle;
	@FXML public Label SCRscore;
	@FXML public Button btneditscore;
	@FXML public GridPane SCRscorepane;
	public FXMLLoader scorepagefxml;
	public Parent PaneScorePage;
	public SubjectPage thisSPcontroller;
	private Main M=new Main();
	
	public ScoreRow(){
		SCRtitle=new Label();
		SCRscore=new Label();
		btneditscore=new Button();
		SCRscorepane=new GridPane();
		try{
			scorepagefxml=new FXMLLoader(getClass().getResource("ScorePage.fxml"));
			PaneScorePage=scorepagefxml.load();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	@FXML
	public void initialize(){
		ScorePage SCPcontroller=scorepagefxml.getController();
		SCRtitle.textProperty().addListener(new ChangeListener<String>(){//make scorename of ScorePage change when SCRtitle change
			@Override
			public void changed(ObservableValue<? extends String> ov,String oldvalue,String newvalue){
			SCPcontroller.scorename.setText(newvalue);
			}
		});
		SCRscore.textProperty().addListener((ov,oldvalue,newvalue)->{//gain weighted score for SCP
			int num=0;
			for(int i=newvalue.length()-1; i>=0; i--){
				if(newvalue.charAt(i)=='/'){
					num=i+1;
					break;
				}
			}
			SCPcontroller.weighted=Double.valueOf(newvalue.substring(num, newvalue.length()-1));
			SCPcontroller.compute();
			M.goalachieve(SCPcontroller);
			thisSPcontroller.totalscore=thisSPcontroller.totalscore-SCPcontroller.totalscorebefore+SCPcontroller.totalscore;
			SCPcontroller.totalscorebefore=SCPcontroller.totalscore;//score print on SP
			double ensc=Math.rint(thisSPcontroller.ensuretotal*100)/100;
			thisSPcontroller.scentotal.setText("固有總得分:"+ensc+"%");
			double tosc=Math.rint(thisSPcontroller.totalscore*100)/100;
			thisSPcontroller.sctotal.setText("總得分(固有+預期):"+tosc+"%");
			CreatingPage CPcon=Main.createpageMap.get(thisSPcontroller.SPtitle.getText()).getController();
			double total=0;
			for(int i=0; i*2+1<CPcon.weightedscoretext.size(); i++){
				if(CPcon.weightedscoretext.get(2*i+1).getText().equals("")){//when textfield is empty
					total=0;
				}
				else{	
					total=total+Double.valueOf(CPcon.weightedscoretext.get(2*i+1).getText());
				}
			}
			thisSPcontroller.score.setText("總分:"+total+"%");
			SubjectRow SRcon=Main.subjectrowMap.get(thisSPcontroller.SPtitle.getText()).getController();
			SRcon.SRensc.setText(thisSPcontroller.scentotal.getText());
			SRcon.SRtosc.setText(thisSPcontroller.sctotal.getText());
			SRcon.SRsc.setText(thisSPcontroller.score.getText());
		});
		ScorePage SCPcon=scorepagefxml.getController();
		SCPcon.thisSCRcontroller=this;
	}
	@FXML
	private void goScorePage(ActionEvent e){
		M.changepage(5,this);
	}
}
