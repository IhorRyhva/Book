package com.firstProject.forUser.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User/")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(service.getAllUser());
    }
    @GetMapping("exist/{user-email}")
    public ResponseEntity<Boolean> existByEmail(@PathVariable("user-email") String email){
        return ResponseEntity.ok(service.exist(email));
    }
    @GetMapping("{user-id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("user-id") int id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping("create")
    public ResponseEntity<String> create(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    @PutMapping("update")
    public ResponseEntity<Boolean> update(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.update(request));
    }
    @DeleteMapping("delete/{user-email}")
    public ResponseEntity<Void> delete(@PathVariable("user-email") String email){
        service.delete(email);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("findByName/{email}")
    public ResponseEntity<UserResponse> findByName(@PathVariable("email") String email){
        return ResponseEntity.ok(service.findByName(email));
    }
}
