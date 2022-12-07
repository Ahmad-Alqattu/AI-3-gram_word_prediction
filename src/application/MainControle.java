package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainControle implements Initializable {


	@FXML
	private TextField idTF;

    @FXML
    private ListView<String> tt;



	HashMap<String, uni> hash1 = new HashMap<String, uni>();
	HashMap<String, bi> hash2 = new HashMap<String, bi>();
	HashMap<String, tri> hash3 = new HashMap<String, tri>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tt.setCellFactory(cell -> {
			return new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item);

						// decide to add a new styleClass
						// getStyleClass().add("costume style");
						// decide the new font size
						setFont(Font.font(17));
						setStyle("-fx-background-color:  linear-gradient(from 20px 300px to 150px 30px, #15bdff, #ebebeb),      \r\n"
								+ "  radial-gradient(center 1% 110% , radius 200%,  #F57D6C,  #15bdff);-fx-text-fill: white");

					}
				}
			};
		});
		try {
			// Opens a file in read mode
			FileInputStream fis = new FileInputStream(new File("ai.txt"));

			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			String s = "";
			StringBuffer str = new StringBuffer();
			while ((s = reader.readLine()) != null) {
				str.append(s);

			}
			String str1 = str.toString();

			String words[] = str1.split("[\\n]|[.]|[,]|[ ]|[\\t]");

			for (int i = 0; i < words.length; i++) {
				if (hash1.get(words[i]) != null) {
					hash1.get(words[i]).setCunt(hash1.get(words[i]).getCunt() + 1);
				} else if (hash1.get(words[i]) == null) {
					hash1.put(words[i], new uni(1, words[i]));
				}
			}
			for (int i = 0; i < words.length - 1; i++) {
				if (hash2.get(words[i]+" "+words[i + 1]) != null) {
					hash2.get(words[i]+" "+words[i + 1]).setCunt(hash2.get(words[i]+" "+words[i + 1]).getCunt() + 1);
				} else if (hash2.get(words[i]+" "+words[i + 1] ) == null) {
					hash2.put(words[i]+" "+words[i + 1] , new bi(1, words[i],words[i +1]));

				}

			}
			for (int i = 0; i < words.length - 2; i++) {
				if (hash3.get(words[i] +" "+ words[i + 1]+" "+words[i + 2] ) != null) {
					hash3.get(words[i] +" "+ words[i + 1]+" "+words[i + 2]).setCunt(hash3.get(words[i] +" "+ words[i + 1]+" "+words[i + 2]).getCunt() + 1);

				} else if (hash3.get(words[i] +" "+ words[i + 1]+" "+words[i + 2]) == null) {
					hash3.put((words[i] +" "+ words[i + 1] +" "+words[i + 2]),new tri(1, words[i] +" "+ words[i + 1],(words[i + 2])));
				}
			}
//			


			for (int i = 0; i < words.length - 2; i++) {

				hash3.get(words[i] +" "+ words[i + 1]+" "+ words[i + 2]).setProp(hash3.get(words[i] +" "+ words[i + 1]+" "+ words[i + 2]).getCunt() / 1.0/ hash2.get(words[i] +" "+ words[i + 1]).getCunt() / 1.0);
			}
			for (int i = 0; i < words.length - 1; i++) {
				hash2.get(words[i] +" "+ words[i + 1]).setProp(((double) hash2.get(words[i] +" "+ words[i + 1] ).getCunt() / 1.0	/ hash1.get(words[i]).getCunt() / 1.0));
			//	System.out.println(
					//	hash2.get(words[i] +" "+ words[i + 1]).getCunt() / 1.0 / hash1.get(words[i]).getCunt() / 1.0);
		//		System.out.println(
				//		"jjj" +" "+ hash2.get(words[i] +" "+ words[i + 1]).getCunt() +" "+ "  " +" "+ hash1.get(words[i]).getCunt());

			}
			try (Writer writer = new FileWriter("input.csv")) {
				  for (Entry<String, tri> entry : hash3.entrySet()) {
				    writer.append(entry.getKey())
				          .append(',')
				          .append(String.valueOf(entry.getValue().getProp()))
				          .append(System.getProperty("line.separator"));
				  }
				} catch (IOException ex) {
				  ex.printStackTrace(System.err);
				}
			
			
			
			idTF.setOnMouseReleased(e -> {
				try {
				if (idTF.getSelectedText()!="") {
				int c = 0;
				String w[] = idTF.getText().split(" ");

				for (int i = 0; i < w.length; i++) {
					if (idTF.getSelectedText().trim().equalsIgnoreCase(w[i])) {
						c = i;
						break;
					}
				}
				tt.getItems().clear();

				tt.setCellFactory(cell ->

				{
					return new ListCell<String>() {
						@Override
						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							if (item != null) {
								setText(item);

								// decide to add a new styleClass
								// getStyleClass().add("costume style");
								// decide the new font size
								setStyle("-fx-background-color:  linear-gradient(from 20px 300px to 150px 30px, #15bdff, #ebebeb),      \r\n"
										+ "  radial-gradient(center 1% 110% , radius 200%,  #F57D6C,  #15bdff);-fx-text-fill: white");
								setFont(Font.font(17));
							}
						}
					};
				});			
				System.out.println(c);
				ArrayList<list> a = new ArrayList();
		
			
			if(c>1) {
				tri[] Tarry = new tri[hash3.values().size()];
			hash3.values().toArray(Tarry);
			Arrays.sort(Tarry);
			if(c==2)
				for (int i = 0; i < Tarry.length; i++) {
					if (Tarry[i].getStr12().equalsIgnoreCase((w[0] +" "+ w[1]))){
						a.add(new list(Tarry[i].getProp()*hash2.get(w[0] +" "+ w[1]).getProp(),Tarry[i].getStr3()));

					//	tt.appendText(Tarry[i].getStr3() +" "+Tarry[i].getProp()*hash2.get(w[0] +" "+ w[1]).getProp()+ "\n");
//					a.add(new list(Tarry[i].getProp(), Tarry[i].getStr3()));
					 }
				}
			else if(c==3)
				for (int i = 0; i < Tarry.length; i++) {
					if (Tarry[i].getStr12().equalsIgnoreCase((w[1]+" "+ w[2]))){
						
						//tt.appendText(Tarry[i].getStr3() +" "+Tarry[i].getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp()+ "\n");
				a.add(new list(Tarry[i].getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp(), Tarry[i].getStr3()));
					 }
				}
			else if(c==4)
				for (int i = 0; i < Tarry.length; i++) {
					if (Tarry[i].getStr12().equalsIgnoreCase((w[2]+" "+ w[3]))){
					//	tt.appendText(Tarry[i].getStr3() +" "+Tarry[i].getProp()*hash3.get(w[1]+" "+w[2]+" "+ w[3]).getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp()+ "\n");
					a.add(new list(Tarry[i].getProp()*hash3.get(w[1]+" "+w[2]+" "+ w[3]).getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp(), Tarry[i].getStr3()));
					 }
				}
			
			else if(c==5)
				for (int i = 0; i < Tarry.length; i++) {
					if (Tarry[i].getStr12().equalsIgnoreCase((w[3]+" "+ w[4]))){
					//	tt.appendText(Tarry[i].getStr3() +" "++ "\n");
					a.add(new list(Tarry[i].getProp()*hash3.get(w[2]+" "+w[3]+" "+ w[4]).getProp()*hash3.get(w[1]+" "+w[2]+" "+ w[3]).getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp(), Tarry[i].getStr3()));
					 }
				}
			else if(c==6)
				for (int i = 0; i < Tarry.length; i++) {
					if (Tarry[i].getStr12().equalsIgnoreCase((w[4]+" "+ w[5]))){
					//	tt.appendText(Tarry[i].getStr3() +" "++ "\n");
					a.add(new list(Tarry[i].getProp()*hash3.get(w[3]+" "+w[4]+" "+ w[5]).getProp()*hash3.get(w[2]+" "+w[3]+" "+ w[4]).getProp()*hash3.get(w[1]+" "+w[2]+" "+ w[3]).getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp(), Tarry[i].getStr3()));
					 }
				}
			else if(c>6) {
				double res =1;
				for (int i = 0; i < Tarry.length; i++) {
					if (Tarry[i].getStr12().equalsIgnoreCase((w[c-2]+" "+ w[c-1]))){
						double dd=Tarry[i].getProp();
						res=dd;
						for(int j = c; j > 2; j--) {
							if(hash3.get(w[j-3]+" "+w[j-2]+" "+ w[j-1])!=null)
							res*=hash3.get(w[j-3]+" "+w[j-2]+" "+ w[j-1]).getProp();
					//	tt.appendText(Tarry[i].getStr3() +" "+Tarry[i].getProp()*hash3.get(w[1]+" "+w[2]+" "+ w[3]).getProp()*hash3.get(w[0]+" "+w[1]+" "+ w[2]).getProp()*hash2.get(w[0] +" "+ w[1]).getProp()+ "\n");
//					a.add(new list(Tarry[i].getProp(), Tarry[i].getStr3()));
						
					 }
						res*=hash2.get(w[c-7] +" "+ w[c-6]).getProp();
						a.add(new list(res,Tarry[i].getStr3()));
					}
				}
			}

				}else if(c==1){
					bi[] big = new bi[hash2.values().size()];
					hash2.values().toArray(big);
				//	Arrays.sort(big);
					System.out.println(w[0]);


					for (int i = 0; i < big.length; i++) {
						if (big[i].getStr1().equalsIgnoreCase(w[0])) {
							a.add(new list(big[i].getProp(),big[i].getStr2()));
				}
}
//				for (int i = 0; i < 10; i++) {
//					tt.appendText(a.poll().toString() +" "+ "**\n");
//		}	
				}else if(c==0){
					uni[] uni = new uni[hash1.values().size()];
					hash1.values().toArray(uni);
				//	Arrays.sort(big);
					System.out.println(w[0]);


					for (int i = 0; i < hash1.size(); i++) {
						if (uni[i].getStr().equalsIgnoreCase(w[0])) {
							a.add(new list(uni[i].getCunt()/1.0/w.length,uni[i].getStr()));
				}
}
				}
			
			Collections.sort(a, Collections.reverseOrder());   
			for (int i = 0; i < 10&&(i<a.size()); i++) {
				tt.getItems().add(a.get(i).toString());
			}
		
			
				}
				
				}catch (Exception e1) {
					e1.printStackTrace();

				}
		});

		} catch (Exception e) {
			Alert a = new Alert(AlertType.WARNING);
			a.setAlertType(AlertType.WARNING);
			a.setContentText("Word not found");
			a.setTitle("WARNING");
			a.show();			
			e.printStackTrace();
		}

	
	}

	private String[] reverseArray(String[] array1) {
		int index = array1.length;
		String[] array2 = new String[index];
		for (String i : array1) {
			array2[index - 1] = i;
			index--;
		}
		return array2;
	}
}