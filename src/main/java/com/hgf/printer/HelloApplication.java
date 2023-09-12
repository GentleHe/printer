package com.hgf.printer;

import com.hgf.printer.component.ComponentUtil;
import com.hgf.printer.event.DefaultEventBus;
import com.hgf.printer.event.ReceiveMessageEvent;
import com.hgf.printer.jms.JmsConstant;
import com.hgf.printer.jms.JmsHelper;
import com.hgf.printer.util.PrinterUtil;
import com.hgf.printer.util.ToastUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import javax.jms.*;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, JMSException {


        Panel panel = new Panel("标题");
        panel.getStyleClass().add("panel-primary");
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(20));
        Button button = new Button("Hello BootstrapFX");
        button.getStyleClass().setAll("btn", "btn-danger");
        content.setCenter(button);

        // 头部状态栏
        FlowPane flowPane = new FlowPane(10, 10);
        // 消息队列连接状态
        flowPane.getChildren().add(ComponentUtil.getLabel("队列监听状态:", "未连接"));
        // 连接和取消连接按钮
        Button connectButton = new Button("连接");
        connectButton.getStyleClass().setAll("btn", "btn-success");
        Button disConnectButton = new Button("取消连接");
        disConnectButton.getStyleClass().setAll("btn", "btn-warning");
        flowPane.getChildren().add(connectButton);
        flowPane.getChildren().add(disConnectButton);

        // 取一个按钮
        Button fetctAMeesageButton = new Button("取一个任务");
        fetctAMeesageButton.getStyleClass().setAll("btn", "btn-primary");
        flowPane.getChildren().add(fetctAMeesageButton);

        content.setTop(flowPane);

        // 打印机列表
        VBox printerVBox = new VBox();
        printerVBox.getChildren().add(new Label("打印机列表"));
        ListView<String> printerListView = new ListView<>();
        printerListView.setItems(FXCollections.observableList(PrinterUtil.listPrinterNames()));
        printerListView.setPrefSize(200, 200);
        printerVBox.getChildren().add(printerListView);
        content.setRight(printerVBox);

        // 打印机列表监听
        printerListView.setOnMouseClicked(x -> {
            System.out.println("点击了" + x.getClickCount() + "次");
        });
        printerListView.getSelectionModel().selectedIndexProperty().addListener(x -> {
            System.out.println("当前选择的是: " + printerListView.getSelectionModel().getSelectedIndex());
        });
        printerListView.getSelectionModel().selectedItemProperty().addListener(x ->
                {
                    ReadOnlyObjectProperty<String> stringReadOnlyObjectProperty = printerListView.getSelectionModel().selectedItemProperty();
                    System.out.println("当前选择的是: " + stringReadOnlyObjectProperty.getValue());
                }
        );

        // 接收到的消息记录
        VBox messageListVBox = new VBox();
        messageListVBox.getChildren().add(new Label("打印消息："));
        ListView<String> messageListView = new ListView<>();
        messageListView.setItems(FXCollections.observableList(new ArrayList<>()));
        messageListVBox.getChildren().add(messageListView);
        content.setBottom(messageListVBox);


        // 父面板设置内容
        panel.setBody(content);

        // 场景
        Scene scene = new Scene(panel);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());


        // 消息队列监听
        Session session = JmsHelper.openSession();
        MessageConsumer consumer = session.createConsumer(session.createQueue(JmsConstant.queueName));
//        consumer.setMessageListener(x -> {
//            TextMessage textMessage = (TextMessage) x;
//            try {
//                String text = textMessage.getText();
//                messageListView.getItems().add(text);
//            } catch (JMSException e) {
//                throw new RuntimeException(e);
//            }
//        });

        // 点击连接
        fetctAMeesageButton.setOnMouseClicked(x -> {
            System.out.println("连接");
            try {
                JmsHelper.openSession();

            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });


        // 点击取消连接
        disConnectButton.setOnMouseClicked(x -> {
            System.out.println("取消连接");
            try {
                JmsHelper.closeSession(JmsHelper.defaultSession);
                consumer.close();
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });


        DefaultEventBus.getInstance().subscribe(ReceiveMessageEvent.class, x->{
            // 加到消息列表
            messageListView.getItems().add(x.getMessage());
            // 气泡提示
            ToastUtil.toast("取到了消息: " + x.getMessage());
        });

        fetctAMeesageButton.setOnMouseClicked(x -> {
            System.out.println("取一个消息");
            try {
                TextMessage receive = (TextMessage) consumer.receive(100L);
                String receiveText = receive.getText();
                System.out.println("取到了消息: " + receiveText);

                ReceiveMessageEvent.fire(receiveText);

            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });


        stage.setTitle("打印机程序");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("init");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
//        Platform.exit();
        System.exit(0);
    }
}