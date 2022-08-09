package guru.qa.enumvalues;

public enum Genders {
    MALE("male"),
    FEMALE("female");

    private String name;

    Genders(String nameOfGender) {
        name = nameOfGender;
    }

    public String getName() {
        return name;
    }
}
