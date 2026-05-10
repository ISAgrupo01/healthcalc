package healthcalc;

import java.awt.EventQueue;
public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //modelo:instancia única (Singleton)
                    HealthCalc modelo = HealthCalcImpl.getInstance();
                    //vista
                    IHealthCalcView vista = new HealthCalcView();
                    //controlador: recibe modelo y vista como parámetros
                    new HealthCalcControlador(vista, modelo);
                    vista.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}