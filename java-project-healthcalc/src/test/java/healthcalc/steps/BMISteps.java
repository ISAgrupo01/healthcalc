package healthcalc.steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class BMISteps {
    private HealthCalcImpl calc = HealthCalcImpl.getInstance();
    private double peso;
    private double altura;
    private double resultadoBMI;
    private String clasificacion;
    private boolean errorLanzado;

    // para bmi_calculo.feature
    @Dado("un peso de {double} kg")
    public void setPeso(Double valor) {
        this.peso = valor;
    }

    @Y("una altura de {double} m")
    public void setAltura(Double valor) {
        this.altura = valor;
    }

    @Cuando("solicito calcular el BMI")
    public void calcularBMI() {
        try {
            resultadoBMI = calc.bmi(peso, altura);
            errorLanzado = false;
        } catch (InvalidHealthDataException e) {
            errorLanzado = true;
            resultadoBMI = 0.0;
        }
    }

    @Entonces("el sistema muestra el resultado de BMI {string}")
    public void verificarValorBMI(String esperado) {
        assertEquals(Double.parseDouble(esperado), resultadoBMI, 0.01);
    }

    @Entonces("el sistema informa de que los datos de BMI no son válidos")
    public void verificarErrorBMI() {
        assertTrue(this.errorLanzado);
    }

    // para bmi_clasificacion.feature
    @Dado("un valor de BMI de {double}")
    public void valorBMI(Double valor) {
        this.resultadoBMI = valor;
    }

    @Cuando("solicito clasificar el BMI")
    public void clasificarBMI() {
        try {
            this.clasificacion = calc.bmiClassification(resultadoBMI);
            this.errorLanzado = false;
        } catch (InvalidHealthDataException e) {
            this.errorLanzado = true;
        }
    }

    @Entonces("el sistema muestra la clasificación BMI {string}")
    public void verificarClasificacionBMI(String esperada) {
        assertEquals(esperada, this.clasificacion);
    }
}