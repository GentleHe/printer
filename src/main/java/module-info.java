module printer {
    requires transitive java.se;
    requires javafx.base;
    requires javafx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.jms.api;
    requires activemq.client;
    requires lombok;
//    requires transitive java.desktop;

    requires slf4j.api;

    exports com.hgf.printer;
}