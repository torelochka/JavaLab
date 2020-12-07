package model;


import java.io.Serializable;
import java.util.Objects;

public class Input implements Serializable {
    private String type;
    private String name;
    private String placeholder;

    public Input() { }

    public Input(String type, String name, String placeholder) {
        this.type = type;
        this.name = name;
        this.placeholder = placeholder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Input)) return false;
        Input input = (Input) o;
        return Objects.equals(type, input.type) &&
                Objects.equals(name, input.name) &&
                Objects.equals(placeholder, input.placeholder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, placeholder);
    }
}
