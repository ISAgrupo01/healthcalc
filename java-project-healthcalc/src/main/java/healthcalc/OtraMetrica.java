package healthcalc;
import healthcalc.exceptions.InvalidHealthDataException;

public interface OtraMetrica {
    float m(Person person) throws InvalidHealthDataException;
    MAPCategory mapCategory(Person person) throws InvalidHealthDataException;
}
