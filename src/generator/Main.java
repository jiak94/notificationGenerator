package generator;
	
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import generator.model.Number;
import generator.model.NumberListWrapper;
import generator.view.NewNumberDialogController;
import generator.view.NumberEditDialogController;
import generator.view.OverviewController;
import generator.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private BorderPane rootLayout;
	private Stage primaryStage;
	
	private static ObservableList<Number> numberData = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("通知生成器");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				File personalFile = getNumberFilePath();
				if (personalFile != null) {
					saveNumberDataToFile(personalFile);
				}
				else {
					//handleSaveAs();
					FileChooser fileChooser = new FileChooser();
					
					FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("XML file (*.xml)", "*.xml");
					fileChooser.getExtensionFilters().add(exFilter);
					
					File file = fileChooser.showSaveDialog(getPrimaryStage());
					
					if (file != null) {
						if (!file.getPath().endsWith(".xml")) {
							file = new File(file.getPath() + ".xml");
						}
						saveNumberDataToFile(file);
					}
				}
			}
			
		});
		
		this.primaryStage.getIcons().add(new Image("file:resources/NotificationNumberGenerator.png"));
		
		initRootLayout();
		showNumberOverview();
	}
	
	public void showNumberOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Overview.fxml"));
			
			AnchorPane numberOverview = (AnchorPane)loader.load();
			
			rootLayout.setCenter(numberOverview);
			
			OverviewController controller = loader.getController();
			controller.setMain(this);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Main(){}
	
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			
			rootLayout = (BorderPane)loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = getNumberFilePath();
		if (file != null) {
			loadNumberDataFromFile(file);
		}
	}
	
	public void loadNumberDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(NumberListWrapper.class);
			
			Unmarshaller um = context.createUnmarshaller();
			
			NumberListWrapper wrapper = (NumberListWrapper)um.unmarshal(file);
			
			numberData.clear();
			numberData.addAll(wrapper.getNumbers());
			
			setNumberFilePath(file);
		} catch (Exception e) {
			if (file.exists()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("资料库空");
				alert.setContentText("当前资料库为空" + file.getPath());
				
				alert.showAndWait();
			}else {
				getNumberData().clear();
				setNumberFilePath(null);
			}
		}
	}

	public void setNumberFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		
		if (file != null) {
			prefs.put("filePath", file.getPath());
			
			primaryStage.setTitle("通知库 - " + file.getName());
		} else {
			prefs.remove("filePath");
			
			primaryStage.setTitle("通知生成器");
		}
		
	}

	public File getNumberFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		
		if (filePath != null) {
			return new File(filePath);
		}
		else {
			return null;
		}
	}
	
	public Stage getPrimaryStage() {
		// TODO Auto-generated method stub
		return primaryStage;
	}

	public ObservableList<Number> getNumberData() {
		// TODO Auto-generated method stub
		return numberData;
	}
	
	public boolean showNewNumberDialog(Number number) {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("view/NewNumberDialog.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			
			dialogStage.setTitle("新建通知");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			NewNumberDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMain(this);
			controller.setNumber(number);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showNumberEditDialog(Number selectedNumber) {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("view/NumberEditDialog.fxml"));
			
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("编辑通知");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			NumberEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMain(this);
			controller.setNumber(selectedNumber);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	public void saveNumberDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(NumberListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			NumberListWrapper wrapper = new NumberListWrapper();
			wrapper.setNumbers(numberData);
			m.marshal(wrapper, file);
			
			setNumberFilePath(file);
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("错误");
			alert.setContentText("无法在" + file.getPath() + "中储存数据");
			
			alert.showAndWait();
		}
	}

	
}
