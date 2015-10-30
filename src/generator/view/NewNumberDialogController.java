package generator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import generator.Main;
import generator.DateUtil.DateUtil;
import generator.model.Number;

public class NewNumberDialogController {
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
	private boolean okClicked = false;
	private Number number;
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
	
	@FXML
	public void handleOk() {
		if (isInputValid()) {
			number.setNumber(numberField.getText());
			number.setGenerationDate(DateUtil.convert(generationDateField.getText()));
			number.setNote(noteArea.getText());

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
	public void handleCancel() {
		dialogStage.close();
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	private boolean isInputValid() {
		String errorMessage = "";
		if (numberField.getText() == null || numberField.getText().length() == 0) {
			errorMessage += "֪ͨ�������\n";
		}
		if (generationDateField.getText() == null || generationDateField.getText().length() == 0) {
			errorMessage += "ǩ����������\n";
		}
		
		for (int i = 0; i < main.getNumberData().size(); i++) {
			Number temp = main.getNumberData().get(i);
			if (temp.getNumber().equalsIgnoreCase(numberField.getText())) {
				errorMessage += "֪ͨ����Ѿ�����, ����������";
				break;
			}
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
	
	public void setNumber(Number number) {
		this.number = number;
		
		numberField.setText(number.getNumber());
		generationDateField.setText(DateUtil.format(number.getGenerationDate()));
		generationDateField.setPromptText("mm/dd/yyyy");
	}
}
