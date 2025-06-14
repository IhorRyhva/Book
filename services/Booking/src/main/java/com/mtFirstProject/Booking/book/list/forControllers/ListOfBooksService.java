package com.mtFirstProject.Booking.book.list.forControllers;

import com.mtFirstProject.Booking.book.data.BookRequest;
import com.mtFirstProject.Booking.book.list.Data.ListOfBooksRepository;
import com.mtFirstProject.Booking.book.list.Data.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListOfBooksService {

    private final ListOfBooksRepository repository;
    private final ListOfBooksMapper mapper;

    public List<ListOfBooksResponse> getAll(String email) {
        if(!repository.existsByEmail(email)){
            UserInfo user = new UserInfo();
            user.setEmail(email);
            user.setBooks(
                    new ArrayList<>()
            );
            repository.save(user);
        }

        return repository.findByEmail(email).getBooks().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public void createNewList(BookRequest request) {
        if(repository.existsByEmail(request.userEmail())){
            UserInfo user = repository.findByEmail(request.userEmail());
            user.getBooks().add(mapper.toBookInfo(request, user));
            repository.save(user);
        }else{
            UserInfo user = new UserInfo();
            user.setEmail(request.userEmail());
            user.setBooks(mapper.getList(user, request));
            repository.save(user);
        }
    }
}
