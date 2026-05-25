package healthcalc;

public class PersonImpl {
    private final float weight; // kg
    private final float height; // m
    private final Gender gender;
    private final int age;


    public PersonImpl(float weight, float height, Gender gender, int age) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
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

    
}