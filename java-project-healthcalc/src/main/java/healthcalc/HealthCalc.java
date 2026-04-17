package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

/**
 * Calculator of some health parameters of persons.
 * 
 * @author ISA
 *
 */
public interface HealthCalc {
	
	/**
	 * Calculate the BMI classification of a person.
	 * The BMI (FULL Version) classification is based on the following table:
	 * - Severe thinness: BMI < 16.0
     * - Moderate thinness: 16.0 <= BMI < 17.0
     * - Mild thinness: 17.0 <= BMI < 18.5
     * - Normal weight: 18.5 <= BMI < 25.0
     * - Overweight: 25.0 <= BMI < 30.0
     * - Obese Class I (Moderate): 30.0 <= BMI < 35.0
     * - Obese Class II (Severe): 35.0 <= BMI < 40.0
     * - Obese Class III (Morbid): BMI >= 40.0
	 *
	 * @param bmi	Body Mass Index of the person (kg/m2).
	 * @return	  	The BMI classification of the person.
	 * @throws Exception
	 */
	public String bmiClassification(double bmi) throws InvalidHealthDataException;
	
	/**
	 * Calculate the Body Mass Index (BMI) of a person:
	 *
	 * @param weight	Weight of the person (kg).
	 * @param height 	Height of the person (m).
	 * @return	  		The Body Mass Index of the person (kg/m2).
	 * @throws Exception
	 */
	public double bmi(double weight, double height) throws InvalidHealthDataException;

	/**
	 * Calculate the Ideal Body Weight (IBW) of a person.
	 *
	 * @param height	Height of the person (cm).
	 * @param gender 	Gender of the person ("Hombre" or "Mujer").
	 * @return	  		The Ideal Body Weight of the person (kg).
	 * @throws InvalidHealthDataException
	 */
	public double ibw(int height, String gender) throws InvalidHealthDataException;
	public float calculateMAP(float pas, float pad) throws InvalidHealthDataException;
	public String mapClassification(float map) throws InvalidHealthDataException;

}
