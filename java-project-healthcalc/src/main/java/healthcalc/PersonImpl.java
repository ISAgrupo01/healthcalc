package healthcalc;

public class PersonImpl implements Person {
    private final float weight; // kg
    private final float height; // m
    private final Gender gender;
    private final int age;
    private float systolicPressure;
    private float diastolicPressure;


    public PersonImpl (float weight, float height, Gender gender, int age) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
    }

    public PersonImpl(float weight, float height, Gender gender, int age,
                      float systolicPressure, float diastolicPressure) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
    }

    public float weight() {
        return weight;
    }
    public float height() {
        return height;
    }
    public Gender gender() {
        return gender;
    }
    public int age() {
        return age;
    }

    public float systolicPressure() {
        return systolicPressure;
    }

    public float diastolicPressure() {
        return diastolicPressure;
    }
}