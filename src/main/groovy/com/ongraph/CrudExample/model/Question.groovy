package com.ongraph.CrudExample.model

import ch.qos.logback.classic.db.names.TableName

import javax.persistence.Entity
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name="questions")
class Question  extends AuditModel{
    @Id
    @GeneratedValue(generator ='question_generator')
    @SequenceGenerator(
            name='question_generator',sequenceName = 'question_sequence',initialValue =1000
    )

     Long id

    @NotBlank
    @Size(min=3,max=100)

     String title
    @Column(columnDefinition='text')
     String description

    void setId(Long id){
        this.id=id
    }
    Long getId(){
        return id
    }
    void setTitle(String title){
        this.title=title
    }
    String getTitle(){
        return title
    }
    void setDescription(String description){
        this.description=description
    }
    String getDescription(){
        return description
    }

}
