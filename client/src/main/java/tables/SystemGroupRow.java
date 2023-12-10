package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SystemGroupRow {
    private final StringProperty description;
    private final StringProperty value;

    public SystemGroupRow(String description, String value) {
        this.description = new SimpleStringProperty(description);
        this.value = new SimpleStringProperty(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty valueProperty() {
        return value;
    }
}
