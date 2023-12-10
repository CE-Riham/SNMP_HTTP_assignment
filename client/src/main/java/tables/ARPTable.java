package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ARPTable {
    private final StringProperty index;
    private final StringProperty ipAddress;
    private final StringProperty macAddress;
    private final StringProperty type;

    public ARPTable(String index, String macAddress, String ipAddress, String type) {
        this.index = new SimpleStringProperty(index);
        this.macAddress = new SimpleStringProperty(macAddress);
        this.ipAddress = new SimpleStringProperty(ipAddress);
        this.type = new SimpleStringProperty(type);
    }

    public StringProperty indexProperty() {
        return index;
    }

    public StringProperty ipAddressProperty() {
        return ipAddress;
    }
    public StringProperty macAddressProperty() {
        return macAddress;
    }
    public StringProperty typeProperty() {
        return type;
    }
}
