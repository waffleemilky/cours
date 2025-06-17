package skypro.course.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.course.exception.NotEnoughQuestionsException;
import skypro.course.model.Question;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestions() {
        Set<Question> questions = Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        );

        when(javaQuestionService.getAll()).thenReturn(questions);
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(new Question("Q1", "A1"))
                .thenReturn(new Question("Q2", "A2"));

        Collection<Question> result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(questions));
    }

    @Test
    void testNotEnoughQuestions() {
        when(javaQuestionService.getAll()).thenReturn(Set.of(new Question("Q", "A")));
        assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(2));
    }
}
