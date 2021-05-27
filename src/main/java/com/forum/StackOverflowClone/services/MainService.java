package com.forum.StackOverflowClone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.StackOverflowClone.models.Answer;
import com.forum.StackOverflowClone.models.Question;
import com.forum.StackOverflowClone.repositories.AnswerRepository;
import com.forum.StackOverflowClone.repositories.QuestionRepository;

@Service
public class MainService {
	
	@Autowired
	AnswerRepository answerRepo;
	QuestionRepository questionRepo;

	
    public MainService(
    		AnswerRepository answerRepo,
    		QuestionRepository questionRepo
    		) {
        this.answerRepo = answerRepo;
        this.questionRepo = questionRepo;
    } 
    
    // ANSWER METHODS
    public List<Answer> allAnswers(){
        return answerRepo.findAll();
    }
 
    public Answer createAnswer(Answer answer) {
        return answerRepo.save(answer);
    }
 
    public Answer getAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepo.findById(id);
        if (optionalAnswer.isPresent()) {
            return optionalAnswer.get();
        }
        return null;
    }
 
    public Answer updateAnswer(Answer answer) {
        return answerRepo.save(answer);
    }
 
    public void deleteAnswer(Long id) {
        answerRepo.deleteById(id);
    }
    
    // QUESTION METHODS
    public List<Question> allQuestions(){
        return questionRepo.findAll();
    }
 
    public Question createQuestion(Question question) {
        return questionRepo.save(question);
    }
 
    public Question getQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepo.findById(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        }
        return null;
    }
 
    public Question updateQuestion(Question question) {
        return questionRepo.save(question);
    }
 
    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }

}
