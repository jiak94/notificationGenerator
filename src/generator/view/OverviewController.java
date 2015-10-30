package generator.view;

import java.io.File;

import generator.Main;
import generator.DateUtil.DateUtil;
import generator.PdfUtil.PdfGenerator;
import generator.model.Number;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OverviewController {
	@FXML
	private TableView<Number> numberTable;
	@FXML
	private TableColumn<Number, String> numberColumn;
	@FXML
	private Label numberLabel;
	@FXML
	private Label generateDateLabel;
	@FXML
	private Label noteLabel;
	@FXML
	private Label amountLabel;
	@FXML
	private Label nationalityLabel;
	@FXML
	private Label CIQLabel;
    @FXML
    private Label handlingObjLabel;
    @FXML
    private Label orderNumberLabel;
    @FXML
    private Label departPortLabel;
    @FXML
    private Label departDateLabel;
    @FXML
    private Label arrivalPortLabel;
    @FXML
    private Label arrivalDateLabel;
    @FXML
    private Label propertyLabel;
	
	
	private ObservableList<Number> numberData;
	
	
	private Main main;
	
	public OverviewController() {
		
	}
	
	@FXML
	private void initialize() {
		numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
		
		showNumberDetails(null);
		
		numberTable.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> showNumberDetails(newValue));
	}
	
	public void setMain(Main mainApp) {
		this.main = mainApp;
		
		this.numberData = main.getNumberData();
		numberTable.setItems(this.numberData);
	}
	
	public void showNumberDetails(Number number) {
		if (number != null) {
			this.numberLabel.setText(number.getNumber());
			this.generateDateLabel.setText(DateUtil.format(number.getGenerationDate()));
			this.noteLabel.setText(number.getNote());
			this.amountLabel.setText(number.getAmount());
			this.CIQLabel.setText(number.getCIQnumber());
			this.nationalityLabel.setText(number.getNationality());
            this.handlingObjLabel.setText(number.getHandlingObject());

            this.orderNumberLabel.setText(number.getOrderNUmber());
            this.departPortLabel.setText(number.getDepartPort());
            this.departDateLabel.setText(number.getDepartDate());
            this.arrivalDateLabel.setText(number.getArrivalDate());
            this.arrivalPortLabel.setText(number.getArrivalPort());

            this.propertyLabel.setText(number.getProperty());
		}
		else {
			this.numberLabel.setText("");
			this.generateDateLabel.setText("");
			this.noteLabel.setText("");
            this.amountLabel.setText("");
            this.CIQLabel.setText("");
            this.nationalityLabel.setText("");
            this.handlingObjLabel.setText("");

            this.orderNumberLabel.setText("");
            this.departDateLabel.setText("");
            this.departPortLabel.setText("");
            this.arrivalDateLabel.setText("");
            this.arrivalPortLabel.setText("");

            this.propertyLabel.setText("");
		}
	}
	
	@FXML
	public void handelDeleteNumber() {
		int selectedIndex = numberTable.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			numberTable.getItems().remove(selectedIndex);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("����");
			alert.setHeaderText("û��ѡ���κ�֪ͨ");
			alert.setContentText("�뷵��ѡ��Ҫɾ����֪ͨ");
			
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleEditNumber() {
		Number selectedNumber = numberTable.getSelectionModel().getSelectedItem();
		if (selectedNumber != null) {
			boolean okClicked = main.showNumberEditDialog(selectedNumber);
			
			if (okClicked) {
				showNumberDetails(selectedNumber);
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("����");
			alert.setHeaderText("û��ѡ���κ�֪ͨ");
			alert.setContentText("�뷵��ѡ��Ҫ�༭��֪ͨ");
			
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleNewNumber() {
		Number temp = new Number(main);
		
		boolean okClicked = main.showNewNumberDialog(temp);
		
		if (okClicked) {
			main.getNumberData().add(temp);
		}
	}
	
	@FXML
	public void handleGeneratePdf() {
		Number selectedNumber = numberTable.getSelectionModel().getSelectedItem();
		
		FileChooser fileChooser = new FileChooser();
		
		String filePath;
		
		FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("pdf file (*.pdf)", "*.pdf");
		fileChooser.getExtensionFilters().add(exFilter);
		
		File file = fileChooser.showSaveDialog(main.getPrimaryStage());
		
		if (file != null) {
			if (!file.getPath().endsWith(".pdf")) {
				filePath = file.getParent() + ".pdf";
			}
			else {
				filePath = file.getPath();
			}
			
			PdfGenerator.generatePdf(filePath, selectedNumber);
		}
	}
}
