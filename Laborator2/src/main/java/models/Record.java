package models;

public class Record {
    private Category category;
    private String key;
    private String value;

    public Record(Category category, String key, String value) {
        this.category = category;
        this.key = key;
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
