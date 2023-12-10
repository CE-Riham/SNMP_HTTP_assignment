<?php

    $newSysContact = isset($_REQUEST['newSysContact']) ? $_REQUEST['newSysContact'] : "rihamkatout";        
    snmp2_set("localhost", "fff", ".1.3.6.1.2.1.1.4.0", 's', $newSysContact);

    $newSysName = isset($_REQUEST['newSysName']) ? $_REQUEST['newSysName'] : "Riham Katout";        
    snmp2_set("localhost", "fff", ".1.3.6.1.2.1.1.5.0", 's', $newSysName);

    $newSysLocation = isset($_REQUEST['newSysLocation']) ? $_REQUEST['newSysLocation'] : "Nablus";        
    snmp2_set("localhost", "fff", ".1.3.6.1.2.1.1.6.0", 's', $newSysLocation);

    header("Location: ../html/page1.html");
?>
