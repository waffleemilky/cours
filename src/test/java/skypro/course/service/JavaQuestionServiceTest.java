package skypro.course.service;

import org.junit.jupiter.api.Test;
import skypro.course.model.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

public class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    void testAddAndGetAll() {
        Question q1 = service.add("Q1", "A1");

        Question q2 = service.add("Q2", "A2");

        Collection<Question> all = service.getAll();

        assertEquals(2, all.size());

        assertTrue(all.contains(q1));

        assertTrue(all.contains(q2));
    }

    @Test
    void testAddDuplicate() {
        JavaQuestionService service = new JavaQuestionService();
        service.add("Q", "A");

        service.add("Q", "A");

        assertEquals(1, service.getAll().size());
    }

    @Test
    void testRemove() {
        Question q = service.add("Q", "A");

        Question removed = service.remove(q);

        assertEquals(q, removed);

        assertTrue(service.getAll().isEmpty());
    }

    @Test
    void testGetRandomQuestion() {
        JavaQuestionService service = new JavaQuestionService();

        service.add("Q1", "A1");

        service.add("Q2", "A2");

        Question random = service.getRandomQuestion();

        assertNotNull(random);

        assertTrue(service.getAll().contains(random));
    }
}
