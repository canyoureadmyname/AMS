package AMS;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.converter.DoubleStringConverter;

public class ScorePage {
	@FXML Label scorename;
	@FXML Button btnnew;
	@FXML GridPane SCPscorepane;
	@FXML Button btnremove;
	@FXML Label predictive;
	@FXML Button btncomplete;
	int Si=1;
	public Double weighted;
	public double totalscore=0;
	public double ensurescore=0;
	public double predictivescore=0;
	public double enscorebefore=0;
	public double totalscorebefore=0;
	public Parent SPbefore;
	public ScoreRow thisSCRcontroller;
	private Main M=new Main();
	ArrayList<TextField> SCPname=new ArrayList<>();
	ArrayList<TextField> SCPscore=new ArrayList<>();
	ArrayList<CheckBox> SCPprediction=new ArrayList<>();
	private Pattern numberonlytext=Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
	public ScorePage(){
		scorename=new Label();
		btnnew=new Button();
		SCPscorepane=new GridPane();
		btnremove=new Button();
		predictive=new Label();
		btncomplete=new Button();
	}
	@FXML
	private void newrow(ActionEvent e){//click "新增"/////缺預期分計算score的listener
		SCPname.add(new TextField());
		SCPname.get(Si-1).setFont(Font.font(20));
		SCPscore.add(new TextField());
		SCPscore.get(Si-1).setFont(Font.font(20));
		SCPscore.get(Si-1).setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), 0.0,//for textfield only can input Double number
				change->{
					String newtext=change.getControlNewText();
					if(numberonlytext.matcher(newtext).matches()){
						return change;
					}
					else
						return null;
				}));
		SCPscore.get(Si-1).textProperty().addListener((ov,oldbool,newbool)->{//count average predictive score
				double total=0;
				double add=0;
				int minus=0;
				for(int i=0; i<SCPscore.size(); i++){
					if(SCPscore.get(i).getText().equals("")){
						continue;
					}
					if(SCPscore.get(i).getText().charAt(0)=='-'){//negative not to average
						minus++;
					}
					if(SCPprediction.get(i).isSelected()){
						if(SCPscore.get(i).getText().charAt(0)=='-'){
							if(SCPscore.get(i).getText().substring(1).equals("")){
								continue;
							}
							total-=Double.valueOf(SCPscore.get(i).getText().substring(1));
						}
						else{
							add+=Double.valueOf(SCPscore.get(i).getText())/100;
						}
					}
				}
				if(SCPscore.size()-minus==0){
					total=0;
				}
				else{
					total+=add/(SCPscore.size()-minus)*weighted;
				}
				predictivescore=total;
				predictive.setText("預期分:"+total+"%");
		});
		SCPprediction.add(new CheckBox("預期分數"));
		SCPprediction.get(Si-1).setFont(Font.font(20));
		SCPprediction.get(Si-1).selectedProperty().addListener((ov,oldbool,newbool)->{//count average predictive score
				double total=0;
				double add=0;
				int minus=0;
				for(int i=0; i<SCPscore.size(); i++){
					if(SCPscore.get(i).getText().equals("")){
						continue;
					}
					if(SCPscore.get(i).getText().charAt(0)=='-'){//negative not to average
						minus++;
					}
					if(SCPprediction.get(i).isSelected()){
						if(SCPscore.get(i).getText().charAt(0)=='-'){
							if(SCPscore.get(i).getText().substring(1).equals("")){
								continue;
							}
							total-=Double.valueOf(SCPscore.get(i).getText().substring(1));
						}
						else{
							add+=Double.valueOf(SCPscore.get(i).getText())/100;
						}
					}
				}
				if(SCPscore.size()-minus==0){
					total=0;
				}
				else{
					total+=add/(SCPscore.size()-minus)*weighted;
				}
				predictivescore=total;
				predictive.setText("預期分:"+total+"%");
		});
		Label name=new Label(Si+".名稱:");
		name.setFont(Font.font(30));
		Label score=new Label("得分:");
		score.setFont(Font.font(30));
		GridPane.setMargin(name, new Insets(0,20,0,0));
		SCPscorepane.add(name, 0, Si);
		SCPscorepane.add(SCPname.get(Si-1), 1, Si);
		SCPscorepane.add(score, 2, Si);
		SCPscorepane.add(SCPscore.get(Si-1), 3, Si);
		GridPane.setMargin(SCPprediction.get(Si-1), new Insets(0,0,0,20));
		SCPscorepane.add(SCPprediction.get(Si-1), 4, Si);
		Si++;
	}
	@FXML
	private void remove(ActionEvent e){//click "刪除末項"
		if(SCPname.size()!=0){
			SCPname.remove(Si-2);
			SCPscore.remove(Si-2);
			if(SCPprediction.get(Si-2).isSelected()){
				double total=0;
				double add=0;
				int minus=0;
				for(int i=0; i<SCPscore.size(); i++){
					if(SCPscore.get(i).getText().equals("")){
						continue;
					}
					if(SCPscore.get(i).getText().charAt(0)=='-'){//negative not to average
						minus++;
					}
					if(SCPprediction.get(i).isSelected()){
						if(SCPscore.get(i).getText().charAt(0)=='-'){
							if(SCPscore.get(i).getText().substring(1).equals("")){
								continue;
							}
							total-=Double.valueOf(SCPscore.get(i).getText().substring(1));
						}
						else{
							add+=Double.valueOf(SCPscore.get(i).getText())/100;
						}
					}
				}
				if(SCPscore.size()-minus==0){
					total=0;
				}
				else{
					total+=add/(SCPscore.size()-minus)*weighted;
				}
				predictivescore=total;
				predictive.setText("預期分:"+total+"%");
			}
			SCPprediction.remove(Si-2);
			SCPscorepane.getChildren().remove(SCPscorepane.getChildren().size()-5, SCPscorepane.getChildren().size());
			Si--;
		}
	}
	@FXML
	private void complete(ActionEvent e){//click "完成"
		compute();
		M.changepage(6,this);
	}
	public void compute(){
		computepre();
		double total=0;//--top-- compute score
		double add=0;
		int minus=0;
		for(int i=0; i<SCPscore.size(); i++){
			if(SCPscore.get(i).getText().equals("")){
				continue;
			}
			if(SCPscore.get(i).getText().charAt(0)=='-'){//negative not to average
				minus++;
			}
			if(!SCPprediction.get(i).isSelected()){
				if(SCPscore.get(i).getText().charAt(0)=='-'){
					if(SCPscore.get(i).getText().substring(1).equals("")){
						continue;
					}
					total-=Double.valueOf(SCPscore.get(i).getText().substring(1));
				}
				else{
					add+=Double.valueOf(SCPscore.get(i).getText())/100;
				}
			}
		}
		if(SCPscore.size()-minus==0){
			total=0;
		}
		else{
			total+=add/(SCPscore.size()-minus)*weighted;
		}
		ensurescore=Math.rint(total*100)/100;//for %f.2
		totalscore=Math.rint((total+predictivescore)*100)/100;//--bottom--
		thisSCRcontroller.SCRscore.setText(ensurescore+"%/"+totalscore+"%/"+weighted+"%");
		thisSCRcontroller.SCRscorepane.getChildren().clear();
		for(int i=0; i<SCPname.size(); i++){//display item on ScoreRow
			Label name=new Label(SCPname.get(i).getText());
			name.setFont(Font.font(30));
			thisSCRcontroller.SCRscorepane.add(name, 0, i);
			Label label=new Label("得分:");
			label.setFont(Font.font(30));
			thisSCRcontroller.SCRscorepane.add(label, 1, i);
			Label score=new Label(SCPscore.get(i).getText());
			score.setFont(Font.font(30));
			if(SCPprediction.get(i).isSelected()){
				score.setText(SCPscore.get(i).getText()+" (預期)");
			}
			thisSCRcontroller.SCRscorepane.add(score, 2, i);
		}
	}
	private void computepre(){
		double total=0;
		double add=0;
		int minus=0;
		for(int i=0; i<SCPscore.size(); i++){
			if(SCPscore.get(i).getText().equals("")){
				continue;
			}
			if(SCPscore.get(i).getText().charAt(0)=='-'){//negative not to average
				minus++;
			}
			if(SCPprediction.get(i).isSelected()){
				if(SCPscore.get(i).getText().charAt(0)=='-'){
					if(SCPscore.get(i).getText().substring(1).equals("")){
						continue;
					}
					total-=Double.valueOf(SCPscore.get(i).getText().substring(1));
				}
				else{
					add+=Double.valueOf(SCPscore.get(i).getText())/100;
				}
			}
		}
		if(SCPscore.size()-minus==0){
			total=0;
		}
		else{
			total+=add/(SCPscore.size()-minus)*weighted;
		}
		predictivescore=total;
		predictive.setText("預期分:"+total+"%");
	}
}
