package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public class HealthHospitalAdapter implements HealthHospital {
    private HealthCalcImpl calculadora;

    public HealthHospitalAdapter() {
        // Usamos la instancia Singleton 
        this.calculadora = HealthCalcImpl.getInstance();
    }

    @Override
    public Tuple<Float, String> indiceMasaCorporal(float altura, int peso) {
        try {
            // Convertimos gramos en Kg
            double pesoKg = peso / 1000.0;
            
            double bmi = calculadora.bmi(pesoKg, altura);
            String clasificacion = calculadora.bmiClassification(bmi);
            
            return new Tuple<>((float) bmi, clasificacion);

        } catch (InvalidHealthDataException e) {
            System.err.println("Error: " + e.getMessage());
            return new Tuple<>(0.0f, "Error en los datos");
        }
    }

    @Override
    public int pesoCorporalIdeal(char genero, float altura) {
        try {
            // Convertimos metros en cm
            int alturaCm = Math.round(altura * 100);

            String sexo;
            if (genero == 'H' || genero == 'h') {
                sexo = "hombre";
            } else {
                sexo = "mujer";
            }

            double ibw = calculadora.ibw(alturaCm, sexo);

            return (int) Math.round(ibw);

        } catch (InvalidHealthDataException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
}

