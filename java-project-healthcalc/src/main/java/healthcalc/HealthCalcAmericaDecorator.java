package healthcalc;

public class HealthCalcAmericaDecorator extends HealthCalcDecorator {

    private static final double PIES_A_METROS   = 0.3048;
    private static final double LIBRAS_A_GRAMOS = 453.592;

    public HealthCalcAmericaDecorator(HealthHospital decorado) {
        super(decorado);
    }

    @Override
    public Tuple<Float, String> indiceMasaCorporal(float alturaEnPies, int pesoEnLibras) {
        
        float  alturaMetros = (float) (alturaEnPies * PIES_A_METROS);
        int    pesoGramos   = (int)   (pesoEnLibras * LIBRAS_A_GRAMOS);

        Tuple<Float, String> resultado = decorado.indiceMasaCorporal(alturaMetros, pesoGramos);

        float imc    = resultado.getX();
        double pesoKg = pesoGramos / 1000.0;

        System.out.printf("[ES] La persona con altura %.2f metros y %.1f Kg tiene un IMC de %.2f.%n",
                alturaMetros, pesoKg, imc);

        System.out.printf("[EN] The person with height %.2f feet and %.1f lbs has a BMI of %.2f.%n",
                alturaEnPies, (double) pesoEnLibras, imc);

        return resultado;
    }

    @Override
    public int pesoCorporalIdeal(char genero, float alturaEnPies) {
        
        float alturaMetros = (float) (alturaEnPies * PIES_A_METROS);
        return decorado.pesoCorporalIdeal(genero, alturaMetros);
    }
}
