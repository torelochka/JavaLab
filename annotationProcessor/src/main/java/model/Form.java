package model;

import java.util.List;
import java.util.Objects;

public class Form {
    private String action;
    private String method;
    private List<Input> inputs;

    public Form() {

    }

    public Form(String action, String method, List<Input> inputs) {
        this.action = action;
        this.method = method;
        this.inputs = inputs;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Form)) return false;
        Form form = (Form) o;
        return Objects.equals(action, form.action) &&
                Objects.equals(method, form.method) &&
                Objects.equals(inputs, form.inputs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, method, inputs);
    }
}
