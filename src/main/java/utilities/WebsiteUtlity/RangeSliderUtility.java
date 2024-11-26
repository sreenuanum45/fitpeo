package utilities.WebsiteUtlity;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.List;

public class RangeSliderUtility {

	public HashMap<String, String> getElementState(WebElement element) {
		    HashMap<String, String> elementState = new HashMap<String, String>();

		    if (element.isDisplayed()) {
				elementState.put("height", String.valueOf(element.getSize().getHeight()));
		        elementState.put("width", String.valueOf(element.getSize().getWidth()));
		        elementState.put("x", String.valueOf(element.getLocation().getX()));
		        elementState.put("y", String.valueOf(element.getLocation().getY()));
		        elementState.put("class", element.getAttribute("class"));
		        elementState.put("id", element.getAttribute("id"));
		        elementState.put("border", element.getCssValue("border"));
		        elementState.put("font", element.getCssValue("font"));
		        elementState.put("padding", element.getCssValue("padding"));
				elementState.put("value", element.getAttribute("value"));
				elementState.put("min", element.getAttribute("min"));
				elementState.put("max", element.getAttribute("max"));

		        if (element.getTagName().equals("a")) {
		            elementState.put("href", element.getAttribute("href"));
		        }

		        if (element.getTagName().equals("ul") || element.getTagName().equals("ol")) {
		            elementState.put("itemcount", element.findElements(By.tagName("li")).size()+"");
		        }

		        if (element.getTagName().equals("table")) {
		            List<WebElement> tableRows = element.findElements(By.tagName("tr"));
		            elementState.put("rowcount", (tableRows.size()+""));

		        }
		    } else {
		        elementState.put("displayed", "false");
		    }

		    return elementState;

		}
    public void setRangeSliderValue(WebElement rangeSlider, int value,RemoteWebDriver driver) {

		String sliderValue = String.valueOf(value);

		// Update the slider's value using JavaScript
		driver.executeScript("arguments[0].value = arguments[1];", rangeSlider, sliderValue);

		// Dispatch a 'change' event to ensure the UI and any associated scripts are updated
		driver.executeScript("var event = new Event('change', { bubbles: true }); arguments[0].dispatchEvent(event);", rangeSlider);

		// Optional: Log the action for debugging
		System.out.println("Slider value set to: " + sliderValue);
	}
	public  void setSliderValue(RemoteWebDriver driver, WebElement slider) {
		try {
			// Get slider properties
			int minValue = Integer.parseInt(slider.getAttribute("min")); // Minimum value
			int maxValue = Integer.parseInt(slider.getAttribute("max")); // Maximum value
			int currentValue = Integer.parseInt(slider.getAttribute("value")); // Current slider value
			int targetValue = 1200; // Example target value

			if (targetValue < minValue || targetValue > maxValue) {
				throw new IllegalArgumentException("Target value is out of bounds.");
			}

			// Slider width
			int sliderWidth = slider.getSize().getWidth(); // Total width of the slider in pixels

			// Calculate the offset
			double proportion = (double) (targetValue - minValue) / (maxValue - minValue); // Proportion of the range
			double currentProportion = (double) (currentValue - minValue) / (maxValue - minValue);
			double xOffsetDouble = (proportion - currentProportion) * sliderWidth; // Offset proportional to width
			int xOffset = (int) Math.round(xOffsetDouble); // Round to nearest pixel

			// Debugging: Log all values
			System.out.println("Slider Min Value: " + minValue);
			System.out.println("Slider Max Value: " + maxValue);
			System.out.println("Slider Current Value: " + currentValue);
			System.out.println("Slider Width: " + sliderWidth);
			System.out.println("Target Proportion: " + proportion);
			System.out.println("Current Proportion: " + currentProportion);
			System.out.println("Calculated Offset: " + xOffset);

			// Move slider using Actions class
			Actions actions = new Actions(driver);
			actions.clickAndHold(slider)
					.moveByOffset(xOffset, 0)
					.release()
					.perform();

			System.out.println("Slider successfully moved to target value: " + targetValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void moveSliderUsingJS(RemoteWebDriver driver, WebElement slider, int targetValue) {
		try {

			driver.executeScript(
					"arguments[0].value = arguments[1]; " +
							"arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
							"arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
					slider, targetValue
			);

			Thread.sleep(500); // Wait for the UI to update
			System.out.println("Slider moved to target value using JavaScript: " + targetValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void setSliderValueUsingJS(RemoteWebDriver driver, WebElement slider, int targetValue) {
			driver.executeScript("arguments[0].value = arguments[1]", slider, String.valueOf(targetValue));
	    driver.executeScript("arguments[0].dispatchEvent(new Event('change'))", slider);
	}
}



