package com.ongraph.CrudExample.controller

import com.ongraph.CrudExample.exception.ResourceNotFoundException
import com.ongraph.CrudExample.model.Question
import com.ongraph.CrudExample.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
class QuestionContoller {
    @Autowired
    QuestionRepository questionRepository


    @GetMapping('/questions')
    List<Question> getQuestions() {
        return questionRepository.findAll()
    }

    @PostMapping(value = '/questions')
    Question createQuestion(@Valid @RequestBody Question question) {
        println(question.properties)
        return questionRepository.save(question)
    }

    @PutMapping('/questions/{questionId}')
    Map updateQuestion(@PathVariable Long questionId,
                       @Valid @RequestBody Question questionRequest) {
        Optional<Question> checkNull = Optional.ofNullable(questionRepository.findById(questionId)) as Optional<Question>
        if (checkNull.isPresent()) {
            Question q = questionRepository.findById(questionId).get()
            q.setTitle(questionRequest.getTitle())
            q.setDescription(questionRequest.getDescription())
            questionRepository.save(q)
            Map m = [:]
            m.put('id', questionId)
            m.put('title', questionRequest.getTitle())
            m.put('description', questionRequest.getDescription())
            return m;
        } else {
            return "Question Id is not valid"
        }


    }


    @DeleteMapping('/questions/{questionId}')
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {

        Optional<Question> checkNull =
                Optional.ofNullable(questionRepository.findById(questionId)) as Optional<Question>
        if (checkNull.isPresent()) {
            Question q = questionRepository.findById(questionId).get()
            questionRepository.delete(q)
        } else {
            println "Question doesnot exist"
        }



        return ResponseEntity.ok().build()
    }

}
