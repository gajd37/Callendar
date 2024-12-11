module org.example.test1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    // Модули для Jackson
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    // Доступ для FXML к вашим контроллерам
    opens org.example.test1 to javafx.fxml;
    // Экспорт пакета, чтобы он был доступен другим модулям (если нужно)
    exports org.example.test1;
    uses org.example.test1.MainApp;
}




