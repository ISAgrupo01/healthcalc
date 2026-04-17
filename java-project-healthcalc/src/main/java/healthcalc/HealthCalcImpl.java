package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public class HealthCalcImpl implements HealthCalc {

    @Override
    public String bmiClassification(double bmi) throws InvalidHealthDataException {
        if (bmi < 0) {
            throw new InvalidHealthDataException("BMI cannot be negative.");
        }
        if (bmi > 150) {
            throw new InvalidHealthDataException("BMI must be within a possible biological range [0-150].");
        }
        String result = "Obesity";
        if (bmi < 16.0) {
            result = "Severe thinness";
        } else if (bmi < 17.0) {
            result = "Moderate thinness";
        } else if (bmi < 18.5) {
            result = "Mild thinness";
        } else if (bmi < 25.0) {
            result = "Normal weight";
        } else if (bmi < 30.0) {
            result = "Overweight";
        } else if (bmi < 35.0) {
            result = "Obese Class I (Moderate)";
        } else if (bmi < 40.0) {
            result = "Obese Class II (Severe)";
        } else {
            result = "Obese Class III (Morbid)";
        }
        return result;
    }

    @Override
    public double bmi(double weight, double height) throws InvalidHealthDataException {
        if (weight <= 0) {
            throw new InvalidHealthDataException("Weight must be positive.");
        }
        if (height <= 0) {
            throw new InvalidHealthDataException("Height must be positive.");
        }
        if (weight < 1 || weight > 700) {
            throw new InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.");
        }
        if (height < 0.30 || height > 3.00) {
            throw new InvalidHealthDataException("Height must be within a possible biological range [0.30-3.00] m.");
        }
        return weight / Math.pow(height, 2);
    }
    
    @Override
    public double ibw(int height, String gender) throws InvalidHealthDataException {
        if (height <= 0) {
            throw new InvalidHealthDataException("Height must be positive.");
        }
        if (height < 150 || height > 300) {
            throw new InvalidHealthDataException("Height must be within a range between 150cm and 300cm.");
        }
        if (!"hombre".equals(gender) && !"mujer".equals(gender)) {
            throw new InvalidHealthDataException("Gender must be 'Hombre' or 'Mujer'.");
        }
        double weight = 0.0;
        if ("hombre".equals(gender)) {
	        weight = (height - 100) - ((height - 150) / 4.0);
        } else {
	        weight = (height - 100) - ((height - 150) / 2.5);
        }
        return weight;
    }
    
    public float calculateMAP(float pas, float pad) throws InvalidHealthDataException {

        if (pas <= 0 || pad <= 0) {
            throw new InvalidHealthDataException("Las presiones no pueden ser cero o negativas.");
        }
        if (pad >= pas) {
            throw new InvalidHealthDataException("La presión diastólica no puede ser mayor o igual a la sistólica.");
        }
        if (pas > 300 || pad > 200) {
            throw new InvalidHealthDataException("Valores fuera del rango biológico humano.");
        }

        return (pas + 2 * pad) / 3;
    }

    @Override
    public String mapClassification(float map) throws InvalidHealthDataException {
        if (map <= 0) {
            throw new InvalidHealthDataException("El MAP no puede ser cero o negativo."); //Comprobar que no se pueden clasificar valores de MAP nulos o negativos
        }

        String result = "High";

        if (map < 70) {
            result = "Low";
        } else if (map <= 100) {
            result = "Normal";
        }

        return result;
    }
}
