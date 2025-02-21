package com.firstProject.forUser.User;

import com.firstProject.forUser.User.insertedData.InsertData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapping mapping;
    private final UserRepository repository;
    public List<UserResponse> getAllUser() {
        InsertData insertData = new InsertData();
        insertData.create(repository);
        return repository
                .findAll()
                .stream()
                .map(mapping::toResponse)
                .collect(Collectors.toList());
    }
    public Boolean exist(String email) {
        return repository.existsByEmail(email);
    }

    public UserResponse getById(int id) {
       if(repository.existsById(id)){
           return mapping.toResponse(repository.findById(id).get());
       }else{
           throw new RuntimeException();/**TODO **/
       }
    }

    public String create(UserRequest request) {
        return repository
                .save(mapping
                        .fromRequest(request))
                .getEmail();
    }

    public Boolean update(UserRequest request) {
        if(exist(request.email())) {
            repository.save(mapping.fromRequest(request));
            return true;
        }
        return false;
    }

    public void delete(String email) {
        if(Boolean.TRUE.equals(exist(email))){
            repository.deleteAllByEmail(email);
        }
    }

    public UserResponse findByName(String email) {
        return mapping
                .toResponse(repository
                        .findByEmail(email));
    }
}
