package healthcalc.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import static org.junit.jupiter.api.Assertions.assertTrue;
import healthcalc.HealthCalcImpl;
import healthcalc.IdealBodyWeight;
import healthcalc.PersonImpl;
import healthcalc.Gender;

public class IBWSteps {

    private HealthCalcImpl calculadora = HealthCalcImpl.getInstance();
    private IdealBodyWeight metrica = (IdealBodyWeight) calculadora;
    private String sexo;
    private int altura;
    private double resultadoIBW;
    private boolean errorLanzado;

    @Dado("la calculadora de IBW está iniciada")
    public void iniciarCalculadora() {
        calculadora = HealthCalcImpl.getInstance();
    }
    
    @Dado("una altura de {int} cm")
    public void la_altura_es(int altura) {
        this.altura = altura;
    }

    @Y("un sexo de {string}")
    public void el_sexo_es(String sexo) {
        this.sexo = sexo;
    }
    
    @Cuando("solicito calcular el IBW")
    public void ejecutarIBW() {
        try {
            if (!sexo.equals("hombre") && !sexo.equals("mujer")) {
                throw new healthcalc.exceptions.InvalidHealthDataException("Género no válido");
            }
            resultadoIBW = metrica.idealBodyWeight(new PersonImpl(0f, altura / 100f, sexo.equals("hombre") ? Gender.MALE : Gender.FEMALE, 0));
            errorLanzado = false;
        } catch (Exception e) {
            errorLanzado = true;
        }
    }

    @Entonces("el sistema muestra el valor {double} kg")
    public void verificarIBW(double ibwEsperado) {
        assertEquals(ibwEsperado, resultadoIBW, 0.01);
    }

    @Entonces("el sistema informa que los datos no son los adecuados")
    public void verificarError() {
        assertTrue(errorLanzado);
    }

}