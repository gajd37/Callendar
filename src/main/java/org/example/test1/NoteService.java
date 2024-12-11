package org.example.test1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NoteService {
    private final Map<String, List<String>> notes = new HashMap<>();
    private final File file = new File("notes.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NoteService() {
        loadNotesFromFile(); // Загрузка заметок при старте программы
    }

    // Добавление заметки
    public void addNote(String date, String content) {
        notes.computeIfAbsent(date, k -> new ArrayList<>()).add(content);
        saveNotesToFile(); // Сохранение изменений
    }

    // Получение заметок на определённую дату
    public List<String> getNotes(String date) {
        return notes.getOrDefault(date, new ArrayList<>());
    }

    // Удаление заметки по индексу
    public void deleteNote(String date, int index) {
        List<String> dayNotes = notes.get(date);
        if (dayNotes != null && index >= 0 && index < dayNotes.size()) {
            dayNotes.remove(index);
            if (dayNotes.isEmpty()) {
                notes.remove(date);
            }
            saveNotesToFile(); // Сохранение изменений
        }
    }

    // Очистка всех заметок на день
    public void clearDay(String date) {
        notes.remove(date);
        saveNotesToFile(); // Сохранение изменений
    }

    // Сохранение заметок в файл JSON
    private void saveNotesToFile() {
        try {
            objectMapper.writeValue(file, notes);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении заметок: " + e.getMessage());
        }
    }

    // Загрузка заметок из файла JSON
    private void loadNotesFromFile() {
        if (file.exists()) {
            try {
                Map<String, List<String>> loadedNotes = objectMapper.readValue(file, new TypeReference<>() {});
                notes.putAll(loadedNotes);
            } catch (IOException e) {
                System.err.println("Ошибка при загрузке заметок: " + e.getMessage());
            }
        }
    }
}








