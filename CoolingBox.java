import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of cooling boxes.
 * 
* @author Jérôme D'hulst, Marie Levrau, Art Willems
*/

public class CoolingBox extends Device {
	
	/**********************************************************
	 * Constructors
	 **********************************************************/
	
	/**
	 * Initialize a new cooling box with given laboratory, that owns this cooling box, and given temperature setting.
	 * 
	 * @param  laboratory
	 *         The laboratory that owns this new cooling box.
	 * @param  temperatureSetting
	 *         The temperature setting of the new cooling box.
	 * @effect The new cooling box is a device with given laboratory and temperature setting.
	 *         | super(laboratory)
	 * 
	 */
	
	/**NOG AANPASSEN VOOR TEMPERATUUR*/
	@Raw
	public CoolingBox(Laboratory laboratory, int temperatureSetting) {
		super(laboratory);
	}
	
	
}
