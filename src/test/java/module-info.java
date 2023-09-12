module test {
    requires javafx.controls;
    requires java.desktop;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.slf4j;
    requires org.junit.jupiter.api;
    requires jakarta.jms.api;
    requires activemq.client;
    requires java.naming;
    requires java.transaction.xa;

    requires slf4j.api;
    requires org.apache.logging.log4j.jul;

    exports com.hgf.printer;
}