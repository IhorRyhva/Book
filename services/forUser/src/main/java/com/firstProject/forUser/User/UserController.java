package com.firstProject.forUser.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/User/")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    //ALL//
    @PreAuthorize("hasRole='client-admin'")
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(service.getAllUser());
    }
    //EXIST//
    //@PreAuthorize("hasRole='client-admin'")
    @GetMapping("exist/{user-email}")
    public ResponseEntity<Boolean> existByEmail(@PathVariable("user-email") String email){
        return ResponseEntity.ok(service.exist(email));
    }
    //FIND-BY-ID//
    //@PreAuthorize("hasRole='client-admin'")
    @GetMapping("{user-id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("user-id") int id){
        return ResponseEntity.ok(service.getById(id));
    }
    //CREATE//
    //@PreAuthorize("hasRole='client-admin'")
    @PostMapping("create")
    public ResponseEntity<String> create(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    //UPDATE//
   // @PreAuthorize("hasRole='client-admin'")
    @PutMapping("update")
    public ResponseEntity<Boolean> update(@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.update(request));
    }
    //DELETE//
    //@PreAuthorize("hasRole='client-admin'")
    @DeleteMapping("delete/{user-email}")
    public ResponseEntity<Void> delete(@PathVariable("user-email") String email){
        service.delete(email);
        return ResponseEntity.accepted().build();
    }
    //FIND-BY-NAME//
    //@PreAuthorize("hasRole='client-admin'")
    @GetMapping("findByName/{email}")
    public ResponseEntity<UserResponse> findByName(@PathVariable("email") String email){
        return ResponseEntity.ok(service.findByName(email));
    }
}
