package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public interface BasalMetabolicIndex {
    float basalMetabolicIndex(Person person) throws InvalidHealthDataException;
    BMICategory category(Person person) throws InvalidHealthDataException;
}