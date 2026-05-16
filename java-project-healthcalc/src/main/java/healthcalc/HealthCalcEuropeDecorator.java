package healthcalc;

public class HealthCalcEuropeDecorator extends HealthCalcDecorator {

    public HealthCalcEuropeDecorator(HealthHospital decorado) {
        super(decorado);
    }

    @Override
    public Tuple<Float, String> indiceMasaCorporal(float altura, int peso) {

        Tuple<Float, String> resultado = decorado.indiceMasaCorporal(altura, peso);

        double pesoKg = peso / 1000.0; //Me parece extraño que ponga en gramos en el enunciado, pero bueno, lo transformo a kilogramos.
        float imc = resultado.getX();

        System.out.printf("[ES] La persona con altura %.2f metros y %.1f Kg tiene un IMC de %.2f.%n",
                altura, pesoKg, imc);

        System.out.printf("[EN] The person with height %.2f meters and %.1f Kg has a BMI of %.2f.%n",
                altura, pesoKg, imc);

        return resultado;
    }
}
