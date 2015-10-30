package generator.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="numbers")
public class NumberListWrapper {
	private List<Number> numbers;
	
	@XmlElement(name="number")
	public List<Number> getNumbers() {
		return numbers;
	}
	
	
	public void setNumbers(List<Number> numbers) {
		this.numbers = numbers;
	}
}
