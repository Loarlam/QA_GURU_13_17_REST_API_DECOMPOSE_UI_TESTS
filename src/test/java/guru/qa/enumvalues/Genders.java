package guru.qa.enumvalues;

public enum Genders {
    MALE("M"),
    FEMALE("F");

    private String name;

    Genders(String nameOfGender) {
        name = nameOfGender;
    }

    public String getName() {
        return name;
    }
}
