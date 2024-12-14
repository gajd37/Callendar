package org.example.test1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    private NoteService noteService;
    private ObjectMapper objectMapperMock;
    private File fileMock;

    @BeforeEach
    void setUp() {
        objectMapperMock = mock(ObjectMapper.class); // Мокируем ObjectMapper
        fileMock = mock(File.class); // Мокируем File
        noteService = new NoteService(); // Можно передать мок-объекты через конструктор, если нужно
    }

    @Test
    void testOnAddNote() {
        noteService.addNote("2024-06-14", "Test note");
        List<String> notes = noteService.getNotes("2024-06-14");
        assertEquals(1, notes.size());
        assertEquals("Test note", notes.get(0));
    }

    @Test
    void testDeleteNote() {
        System.out.println("Before delete: " + noteService.getNotes("2024-06-14"));
        noteService.deleteNote("2024-06-14", 0);
        System.out.println("After delete: " + noteService.getNotes("2024-06-14"));
        assertTrue(noteService.getNotes("2024-06-14").isEmpty());
    }


    @Test
    void testClearDay() {
        noteService.addNote("2024-06-14", "Some note");
        noteService.clearDay("2024-06-14");
        assertTrue(noteService.getNotes("2024-06-14").isEmpty());
    }
}

