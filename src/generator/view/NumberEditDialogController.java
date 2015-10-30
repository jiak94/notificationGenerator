package generator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import generator.Main;
import generator.DateUtil.DateUtil;
import generator.model.Number;

public class NumberEditDialogController {
	
	@FXML
	private TextField numberField;
	@FXML
	private TextField generationDateField;
	@FXML
	private TextArea noteArea;
	@FXML
	private TextField handlingObjField;
	@FXML
	private TextField amountField;
	@FXML
	private TextField nationalityField;
	@FXML
	private TextField CIQfield;
	@FXML
	private TextField orderNumberField;
	@FXML
	private TextField departPortField;
	@FXML
	private TextField departDateField;
	@FXML
	private TextField arrivalPortField;
	@FXML
	private TextField arrivalDateField;
	@FXML
	private ChoiceBox<String> propertyBox;
	
	private Stage dialogStage;
	private Number number;
	private boolean okClicked = false;
	private Main main;
	
	
	@FXML
	public void initialize() {
		ObservableList<String> properties = FXCollections.observableArrayList("进口", "出口");
		propertyBox.setItems(properties);
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setNumber(Number number) {
		this.number = number;
		
		this.numberField.setText(number.getNumber());
		this.generationDateField.setText(DateUtil.format(number.getGenerationDate()));
        this.noteArea.setText(number.getNote());

        this.handlingObjField.setText(number.getHandlingObject());
        this.amountField.setText(number.getAmount());
        this.nationalityField.setText(number.getNationality());
        this.CIQfield.setText(number.getCIQnumber());

        this.orderNumberField.setText(number.getOrderNUmber());
        this.departPortField.setText(number.getDepartPort());
        this.departDateField.setText(number.getDepartDate());
        this.arrivalPortField.setText(number.getArrivalPort());
        this.arrivalDateField.setText(number.getArrivalDate());

        this.propertyBox.setValue(number.getProperty());
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	public void handleOk() {
		if (isInputValid()) {
			number.setNumber(numberField.getText());
			number.setNote(noteArea.getText());
			number.setGenerationDate(DateUtil.convert(generationDateField.getText()));

            number.setAmount(amountField.getText());
            number.setCIQnumber(CIQfield.getText());
            number.setHandlingObject(handlingObjField.getText());
            number.setNationality(nationalityField.getText());

            number.setOrderNumber(orderNumberField.getText());
            number.setDepartDate(departDateField.getText());
            number.setDepartPort(departPortField.getText());
            number.setArrivalDate(arrivalDateField.getText());
            number.setArrivalPort(arrivalPortField.getText());

            number.setProperty(propertyBox.getValue());

			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	public void handleCancle() {
		dialogStage.close();
	}
	
	private boolean isInputValid() {
		String errorMessage = "";
		
		if (numberField.getText() == null || numberField.getText().length() == 0) {
			errorMessage += "֪ͨ�������\n";
		}
		if (generationDateField.getText() == null || generationDateField.getText().length() == 0) {
			errorMessage += "ǩ����������\n";
		}
		

		
		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			
			alert.setTitle("����");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			
			return false;
		}
	}
}
