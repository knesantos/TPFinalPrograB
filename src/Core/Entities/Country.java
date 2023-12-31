package Core.Entities;

public class Country {
    private String name;
    private String abbreviatedName;

    public Country(String name, String abbreviatedName) {
        this.name = name;
        this.abbreviatedName = abbreviatedName;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }
}


