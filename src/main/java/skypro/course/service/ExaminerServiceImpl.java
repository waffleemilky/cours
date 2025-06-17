package skypro.course.service;

import org.springframework.stereotype.Service;
import skypro.course.exception.NotEnoughQuestionsException;
import skypro.course.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService questionService;

    public ExaminerServiceImpl(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();
        if (amount > allQuestions.size()) {
            throw new NotEnoughQuestionsException("Запрошено больше вопросов, чем есть в базе");
        }

        Set<Question> uniqueQuestions = new HashSet<>();
        while (uniqueQuestions.size() < amount) {
            uniqueQuestions.add(questionService.getRandomQuestion());
        }
        return uniqueQuestions;
    }
}
