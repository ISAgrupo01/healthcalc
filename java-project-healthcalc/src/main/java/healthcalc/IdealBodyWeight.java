package healthcalc;
import healthcalc.exceptions.InvalidHealthDataException;

public interface IdealBodyWeight {
    float idealBodyWeight(Person person) throws InvalidHealthDataException;
}
