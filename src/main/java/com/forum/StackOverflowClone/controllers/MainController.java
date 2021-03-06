package com.forum.StackOverflowClone.controllers;

import java.util.List;

import javax.validation.Valid;

import com.forum.StackOverflowClone.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forum.StackOverflowClone.models.Answer;
import com.forum.StackOverflowClone.models.Question;

@Controller
public class MainController {
 
	@Autowired
    MainService service;
    
	public MainController(MainService service) {
        this.service = service;
    }
 
    @GetMapping("/questions")
    public String dashboard(Model model){
        List<Question> questions = service.allQuestions();
        model.addAttribute("questions", questions);
        return "dashboard.jsp";
    }
 
    @GetMapping("/questions/new")
    public String newQuestion(@ModelAttribute("newQuestion") Question question){
        return "newQuestion.jsp";
    }
 
    @PostMapping("/questions/create")
    public String createQuestion(
        @Valid @ModelAttribute("newQuestion") Question q,
        BindingResult result,
        Model model
    		){
        if (result.hasErrors()){
            return "newQuestion.jsp";
        }
        service.createQuestion(q);

        return "redirect:/questions";
    }
    
    
    @GetMapping("/questions/{id}")
    public String detailQuestion(
    		@PathVariable("id") Long id,
    		@ModelAttribute("newAnswer") Answer newAnswer,
    		Model model
    		) {
    	Question q = this.service.getQuestion(id);
    	model.addAttribute("question", q);
    	return "detailQuestion.jsp";
    }
    
    
    @PostMapping("/questions/{id}/answer")
    public String createAnswer(
    		@PathVariable("id") Long id,
    		@Valid @ModelAttribute("newAnswer") Answer newAnswer,
    		BindingResult result,
    		Model model
    		) {
    	Question q = this.service.getQuestion(id);
    	if (result.hasErrors()) {
    		model.addAttribute("question", q);
    		return "detailQuestion.jsp";
    	}
    	Answer a = new Answer();
    	a.setAnswer(newAnswer.getAnswer());
    	a.setQuestion(q);
    	this.service.createAnswer(a);
    	return "redirect:/questions/" + q.getId();
    }
 
}
