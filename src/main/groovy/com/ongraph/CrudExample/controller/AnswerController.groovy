package com.ongraph.CrudExample.controller

import com.ongraph.CrudExample.exception.ResourceNotFoundException
import com.ongraph.CrudExample.model.Answer
import com.ongraph.CrudExample.model.Question
import com.ongraph.CrudExample.repository.AnswerRepository
import com.ongraph.CrudExample.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
class AnswerController {

    @Autowired
    AnswerRepository answerRepository

    @Autowired
    QuestionRepository questionRepository

    @GetMapping('/questions/{questionId}/answers')
    List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerRepository.findByQuestionId(questionId)
    }


    @PostMapping('/questions/{questionId}/answers')
    Answer addAnswer(@PathVariable Long questionId,
                     @Valid @RequestBody Answer answer) {
        println answer.properties
        Optional<Question> checkNull = Optional.ofNullable(questionRepository.findById(questionId)) as Optional<Question>
        if (checkNull.isPresent()) {
            Question q = questionRepository.findById(questionId).get()
            answer.setQuestion(q)
            return answerRepository.save(answer)
        } else {

            Map m = ["msg": "Question doesn't exist"]

            return m

        }
    }

    @PutMapping('/questions/{questionId}/answers/{answerId}')
    public Answer updateAnswer(@PathVariable Long questionId,
                               @PathVariable Long answerId,
                               @Valid @RequestBody Answer answerRequest) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        Optional<Answer> answer = Optional.ofNullable(answerRepository.findById(answerId))
        if (answer.isPresent()) {
            Answer ans = answerRepository.findById(answerId).get()
            ans.setText(answerRequest.getText())
            return answerRepository.save(ans)
        } else {
            Map m = ["msg": "Request can be succeed"]
            return m
        }

    }

    @DeleteMapping('/questions/{questionId}/answers/{answerId}')
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
                                          @PathVariable Long answerId) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        Optional<Answer> checkNull = Optional.ofNullable(answerRepository.findById(answerId)) as Optional<Answer>
        if (checkNull.isPresent()) {
            Answer answer = answerRepository.findById(answerId).get()
            answerRepository.delete(answer)
            return ResponseEntity.ok().build()
        } else {
            Map m = ["msg": "Answer or Question does not exist"]
            return m
        }

    }
}
