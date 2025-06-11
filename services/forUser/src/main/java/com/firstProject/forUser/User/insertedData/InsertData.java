package com.firstProject.forUser.User.insertedData;

import com.firstProject.forUser.User.User;
import com.firstProject.forUser.User.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


public class InsertData {
    public void create(UserRepository repository){
        for (int i = 0; i < 20; i++) {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s%s%@gmail.com", firstName, lastName, i);
            User user = User.builder()
                    .firstname(firstName)
                    .lastname(lastName)
                    .email(email)
                    .build();
            repository.save(user);
        }

    }
}
