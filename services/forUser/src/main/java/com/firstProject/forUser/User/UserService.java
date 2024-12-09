package com.firstProject.forUser.User;

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
        return repository
                .findAll()
                .stream()
                .map(mapping::toResponse)
                .collect(Collectors.toList());
    }
    public Boolean exist(int id) {
        return repository.existsById(id);
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
        if(exist(request.id())){
            if(mapping.same(request, repository.findById(request.id()).get())){
                throw new withoutChangeException("This is same");
            }else{
                repository.save(mapping.fromRequest(request));
                return true;
            }
        }else{
            return false;
        }
    }

    public void delete(int id) {
        if(Boolean.TRUE.equals(exist(id))){
            repository.deleteById(id);
        }
    }

    public UserResponse findByName(String email) {
        return mapping
                .toResponse(repository
                        .findByEmail(email));
    }
}
