package healthcalc;
import healthcalc.exceptions.InvalidHealthDataException;

public class HealthCalcImpl implements HealthCalc {
    private static HealthCalcImpl instance;
    private HealthCalcImpl() {
    }


    public static HealthCalcImpl getInstance() {
        if (instance == null) {
            instance = new HealthCalcImpl();
        }
        return instance;
    }


    @Override
    public String bmiClassification(double bmi) throws InvalidHealthDataException {
        if (bmi < 0) {
            throw new InvalidHealthDataException("El IMC no puede ser negativo.");
        }
        if (bmi > 150) {
            throw new InvalidHealthDataException("El IMC debe estar en un rango biológico posible [0-150].");
        }
        String result;
        if (bmi < 16.0)       result = "Severe thinness";
        else if (bmi < 17.0)  result = "Moderate thinness";
        else if (bmi < 18.5)  result = "Mild thinness";
        else if (bmi < 25.0)  result = "Normal weight";
        else if (bmi < 30.0)  result = "Overweight";
        else if (bmi < 35.0)  result = "Obese Class I (Moderate)";
        else if (bmi < 40.0)  result = "Obese Class II (Severe)";
        else                  result = "Obese Class III (Morbid)";
        return result;
}

    @Override
    public double bmi(double weight, double height) throws InvalidHealthDataException {
        if (weight <= 0) {
            throw new InvalidHealthDataException("El peso debe ser positivo.");
        }
        if (height <= 0) {
            throw new InvalidHealthDataException("La altura debe ser positiva.");
        }
        if (weight < 1 || weight > 700) {
            throw new InvalidHealthDataException("El peso debe estar en un rango biológico posible [1-700] kg.");
        }
        if (height < 0.30 || height > 3.00) {
            throw new InvalidHealthDataException("La altura debe estar en un rango biológico posible [0.30-3.00] m.");
        }
        return weight / (height * height);
    }
    
    @Override
    public double ibw(int height, String gender) throws InvalidHealthDataException {
        if (height <= 0) {
            throw new InvalidHealthDataException("La altura debe ser positiva.");
        }
        if (height < 150 || height > 300) {
            throw new InvalidHealthDataException("La altura debe estar entre 150cm y 300cm.");
        }
        if (!"hombre".equals(gender) && !"mujer".equals(gender)) {
            throw new InvalidHealthDataException("El género debe ser 'hombre' o 'mujer'.");
        }
        double weight = 0.0;
        if ("hombre".equals(gender)) {
            weight = (height - 100) - ((height - 150) / 4.0);
        } else {
            weight = (height - 100) - ((height - 150) / 2.5);
        }
        return weight;
    }

    @Override
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
            throw new InvalidHealthDataException("El MAP no puede ser cero o negativo.");
        }
        String result = "High";
        if (map < 70)        result = "Low";
        else if (map <= 100) result = "Normal";
        return result;
    }
}