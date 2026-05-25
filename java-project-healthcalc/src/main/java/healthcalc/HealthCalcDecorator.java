package healthcalc;

public abstract class HealthCalcDecorator implements HealthHospital {

    protected HealthHospital decorado;

    public HealthCalcDecorator(HealthHospital decorado) {
        this.decorado = decorado;
    }

    @Override
    public Tuple<Float, String> indiceMasaCorporal(float altura, int peso) {
        return decorado.indiceMasaCorporal(altura, peso);
    }

    @Override
    public int pesoCorporalIdeal(char genero, float altura) {
        return decorado.pesoCorporalIdeal(genero, altura);
    }
}
