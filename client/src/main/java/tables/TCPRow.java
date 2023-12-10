package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TCPRow {
    private final StringProperty index;
    private final StringProperty state;
    private final StringProperty srcIPAddress;
    private final StringProperty srcPort;
    private final StringProperty desIPAddress;
    private final StringProperty desPort;

    public TCPRow(String index, String state, String srcIPAddress, String srcPort, String desIPAddress, String desPort) {
        this.index = new SimpleStringProperty(index);
        this.state = new SimpleStringProperty(state);
        this.srcIPAddress = new SimpleStringProperty(srcIPAddress);
        this.srcPort = new SimpleStringProperty(srcPort);
        this.desIPAddress = new SimpleStringProperty(desIPAddress);
        this.desPort = new SimpleStringProperty(desPort);
    }

    public StringProperty getIndex() {
        return index;
    }

    public StringProperty getSrcIPAddress() {
        return srcIPAddress;
    }
    public StringProperty getSrcPort() {
        return srcPort;
    }
    public StringProperty getState() {
        return state;
    }

    public StringProperty getDesIPAddress() {
        return desIPAddress;
    }
    public StringProperty getDesPort() {
        return desPort;
    }
}
