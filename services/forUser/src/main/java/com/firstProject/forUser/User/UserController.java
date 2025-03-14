package com.firstProject.forUser.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User/")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    //ALL//
    @GetMapping("all")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(service.getAllUser());
    }
    //EXIST//
    @GetMapping("exist/{user-email}")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<Boolean> existByEmail(@PathVariable("user-email") String email){
        return ResponseEntity.ok(service.exist(email));
    }
    //FIND-BY-ID//
    @GetMapping("{user-id}")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<UserResponse> findById(@PathVariable("user-id") int id){
        return ResponseEntity.ok(service.getById(id));
    }
    //CREATE//
    @PostMapping("create")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<String> create(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    //UPDATE//
    @PutMapping("update")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<Boolean> update(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.update(request));
    }
    //DELETE//
    @DeleteMapping("delete/{user-email}")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<Void> delete(@PathVariable("user-email") String email){
        service.delete(email);
        return ResponseEntity.accepted().build();
    }
    //FIND-BY-NAME//
    @GetMapping("findByName/{email}")
    @PreAuthorize("hasRole('client-admin')")
    public ResponseEntity<UserResponse> findByName(@PathVariable("email") String email){
        return ResponseEntity.ok(service.findByName(email));
    }
}
