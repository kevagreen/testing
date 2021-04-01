package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;
    String email = "jamila@gmail.com";

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExist() {
        //given
        Student student = new Student("Jamila", "jamila@gmail.com", Gender.FEMALE);
        underTest.save(student);

        //when
       boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }
    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {
        //given
        Student student = new Student("Jamila", "jamila@gmail.com", Gender.FEMALE);

        //when
        boolean expected = underTest.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();
    }
}