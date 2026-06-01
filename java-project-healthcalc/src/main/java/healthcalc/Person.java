package healthcalc;

public interface Person {
    float weight();
    float height();
    Gender gender();
    int age();
    float systolicPressure();    // mmHg (PAS)
    float diastolicPressure();   // mmHg (PAD)
}