module printer {
    requires java.se;
    requires javafx.base;
    requires javafx.controls;
    requires org.kordamp.bootstrapfx.core;
    
    requires lombok;

    requires slf4j.api;
    
    exports com.hgf.printer;
}