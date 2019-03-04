package com.ongraph.CrudExample.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*

@Entity
@Table(name='answers')

class Answer extends AuditModel {
    @Id
    @GeneratedValue(generator ='answer_generator')
    @SequenceGenerator(name='answer_generator',sequenceName ='answer_sequence',initialValue =1000)

     Long id

    @Column(columnDefinition='text')
     String text

    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore

    Question question

    void setId(Long id){
        this.id=id
    }
    Long getId(){
        return id
    }
    void setText(String text){
        this.text=text
    }
    String getText(){
        return  text
    }
    void setQuestion(Question question){
        this.question=question
    }
    Question getQuestion(){
        return question
    }


}

