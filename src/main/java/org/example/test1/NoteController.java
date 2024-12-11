package org.example.test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NoteController {
    private final NoteService noteService = new NoteService();
    private final ObservableList<String> noteList = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField noteInput;

    @FXML
    private ListView<String> notesListView;

    @FXML
    public void initialize() {
        notesListView.setItems(noteList);
    }

    @FXML
    public void onAddNote() {
        String date = getDate();
        String noteContent = noteInput.getText().trim();
        if (!date.isEmpty() && !noteContent.isEmpty()) {
            noteService.addNote(date, noteContent);
            updateNotes(date);
            noteInput.clear();
        }
    }

    @FXML
    public void onDeleteNote() {
        String date = getDate();
        int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
        if (!date.isEmpty() && selectedIndex >= 0) {
            noteService.deleteNote(date, selectedIndex);
            updateNotes(date);
        }
    }

    @FXML
    public void onClearDay() {
        String date = getDate();
        if (!date.isEmpty()) {
            noteService.clearDay(date);
            updateNotes(date);
        }
    }

    @FXML
    public void onDateChange() {
        String date = getDate();
        updateNotes(date);
    }

    private String getDate() {
        return datePicker.getValue() != null ? datePicker.getValue().toString() : "";
    }

    private void updateNotes(String date) {
        noteList.clear();
        noteList.addAll(noteService.getNotes(date));
    }
}



