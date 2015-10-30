package generator.view;

import java.io.File;

import generator.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {
	
	private Main main;
	
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	@FXML
	public void handleNew() {
		main.getNumberData().clear();
		main.setNumberFilePath(null);
	}
	
	@FXML
	public void handleSave() {
		File personFile = main.getNumberFilePath();
		if (personFile != null) {
			main.saveNumberDataToFile(personFile);
		}
		else {
			handleSaveAs();
		}
	}
	
	@FXML
	public void handleOpen() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("XML file (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(exFilter);
		
		File file = fileChooser.showOpenDialog(main.getPrimaryStage());
		
		if (file != null) {
			main.loadNumberDataFromFile(file);
		}
	}

	@FXML
	public void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("XML file (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(exFilter);
		
		File file = fileChooser.showSaveDialog(main.getPrimaryStage());
		
		if (file != null) {
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			main.saveNumberDataToFile(file);
		}
	}
	
	@FXML
	public void handleExit() {
		handleSave();
		System.exit(0);
	}
	
	@FXML
	public void handleAbount() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("����");
		alert.setHeaderText("֪ͨ������");
		alert.setContentText("���������ο���\n�汾 v1.0");
		
		alert.showAndWait();
	}
}
