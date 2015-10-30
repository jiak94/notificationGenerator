package generator.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import generator.Main;
import generator.DateUtil.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Number {
	private final StringProperty number;
	private final StringProperty note;
	private final ObjectProperty<LocalDate> generateDate;
	private final StringProperty CIQnumber;
	private final StringProperty handlingObject;
	private final StringProperty amount;
	private final StringProperty nationality;
    private final StringProperty orderNumber;
    private final StringProperty departPort;
    private final StringProperty arrivalPort;
    private final StringProperty departDate;
    private final StringProperty arrivalDate;
    private final StringProperty property;
	
	
	public Number() {
		this("");
	}
	
	public Number(Main main) {
		int newNum = 0;
		if (main.getNumberData().size() == 0) {
			newNum = 1;
		}
		else {
			String prevNumber =  main.getNumberData().get(main.getNumberData().size() - 1).getNumber();
			newNum = Integer.parseInt(prevNumber) + 1;
		}
		String newNumber = String.format("%05d", newNum);
		this.number = new SimpleStringProperty(newNumber);
		this.generateDate = new SimpleObjectProperty<LocalDate>(DateUtil.convert(getTodayDate()));
		this.note = new SimpleStringProperty("");

		this.CIQnumber = new SimpleStringProperty("");
		this.handlingObject = new SimpleStringProperty("");
		this.nationality = new SimpleStringProperty("");
		this.amount = new SimpleStringProperty("");

        this.orderNumber = new SimpleStringProperty("");
        this.departPort = new SimpleStringProperty("");
        this.departDate = new SimpleStringProperty("");
        this.arrivalPort = new SimpleStringProperty("");
        this.arrivalDate = new SimpleStringProperty("");
        this.property = new SimpleStringProperty("");
	}
	
	public Number(String number) {
		this.number = new SimpleStringProperty(number);
		this.generateDate = new SimpleObjectProperty<LocalDate>(DateUtil.convert(getTodayDate()));
		this.note = new SimpleStringProperty("");
		this.CIQnumber = new SimpleStringProperty("");
		this.handlingObject = new SimpleStringProperty("");
		this.nationality = new SimpleStringProperty("");
		this.amount = new SimpleStringProperty("");
        this.orderNumber = new SimpleStringProperty("");
        this.departPort = new SimpleStringProperty("");
        this.departDate = new SimpleStringProperty("");
        this.arrivalPort = new SimpleStringProperty("");
        this.arrivalDate = new SimpleStringProperty("");
        this.property = new SimpleStringProperty("");
	}
	
	private String getTodayDate() {
		Date date = new Date();
		
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getGenerationDate() {
		return generateDate.get();
	}
	
	public void setGenerationDate(LocalDate generationDate) {
		generateDate.set(generationDate);
	}
	
	public ObjectProperty<LocalDate> getGenerationDateProperty() {
		return generateDate;
	}
	
	public void setNumber(String number) {
		this.number.set(number);
	}
	
	public String getNumber() {
		return number.get();
	}
	
	public StringProperty getNumberProperty() {
		return number;
	}
	
	public void setNote(String note) {
		this.note.set(note);
	}
	
	public String getNote() {
		return note.get();
	}
	
	public StringProperty getNoteProperty() {
		return note;
	}
	
	public void setCIQnumber(String ciq) {
		this.CIQnumber.set(ciq);
	}
	
	public String getCIQnumber() {
		return CIQnumber.get();
	}

	public StringProperty getCIQProperty() {
		return this.CIQnumber;
	}

	public void setAmount(String amount) {
		this.amount.set(amount);
	}

	public String getAmount() {
		return this.amount.get();
	}

	public StringProperty getAmountProperty() {
		return this.amount;
	}

    public void setHandlingObject(String object) {
        this.handlingObject.set(object);
    }

    public String getHandlingObject() {
        return this.handlingObject.get();
    }

    public StringProperty getHandlingObjectProperty() {
        return this.handlingObject;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public String getNationality() {
        return this.nationality.get();
    }

    public StringProperty getNationalityProperty() {
        return this.nationality;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber.setValue(orderNumber);
    }

    public String getOrderNUmber() {
        return this.orderNumber.get();
    }

    public StringProperty getOrderNUmberProperty() {
        return this.orderNumber;
    }

    public void setDepartPort(String departPort) {
        this.departPort.set(departPort);
    }

    public String getDepartPort() {
        return this.departPort.get();
    }

    public StringProperty getDepartPortProperty() {
        return this.departPort;
    }

    public void setDepartDate(String departDate) {
        this.departDate.set(departDate);
    }

    public String getDepartDate() {
        return this.departDate.get();
    }

    public StringProperty getDepartDateProperty() {
        return this.departDate;
    }

    public void setArrivalPort(String arrivalPort) {
        this.arrivalPort.set(arrivalPort);
    }

    public String getArrivalPort() {
        return this.arrivalPort.get();
    }

    public StringProperty getArrivalPortProperty() {
        return this.arrivalPort;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public String getArrivalDate() {
        return this.arrivalDate.get();
    }

    public StringProperty getArrivalDateProperty() {
        return this.arrivalDate;
    }

    public void setProperty(String property) {
        this.property.set(property);
    }

    public String getProperty() {
        return this.property.get();
    }

    public StringProperty getPropertyProerty() {
        return property;
    }
}
