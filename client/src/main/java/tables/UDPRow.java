package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UDPRow {
    private final StringProperty index;
    private final StringProperty ipAddress;
    private final StringProperty portNumber;

    public UDPRow(String index, String ipAddress, String portNumber) {
        this.index = new SimpleStringProperty(index);
        this.ipAddress = new SimpleStringProperty(ipAddress);
        this.portNumber = new SimpleStringProperty(portNumber);
    }

    public StringProperty indexProperty() {
        return index;
    }

    public StringProperty ipAddressProperty() {
        return ipAddress;
    }
    public StringProperty portNumberProperty() {
        return portNumber;
    }

}
