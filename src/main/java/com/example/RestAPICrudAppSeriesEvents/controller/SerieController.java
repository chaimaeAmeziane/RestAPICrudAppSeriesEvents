package com.example.RestAPICrudAppSeriesEvents.controller;

import com.example.RestAPICrudAppSeriesEvents.model.Serie;
import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.repository.SerieRepository;
import com.example.RestAPICrudAppSeriesEvents.repository.UserRepository;
import com.example.RestAPICrudAppSeriesEvents.service.SerieService;
import com.example.RestAPICrudAppSeriesEvents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8074")
@RestController
@RequestMapping("/api")
public class SerieController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SerieService serieService;

    @PostMapping("/users/{userId}/series")
    public ResponseEntity<Serie> createSerie(@PathVariable(value = "userId") Long userId,
                                                 @RequestBody Serie serieRequest) {
        Serie serie = userRepository.findById(userId).map(user -> {
            serieRequest.setUser(user);
            return serieService.saveSerie(serieRequest);
        }).orElseThrow(() -> new ExpressionException("Not found User with id = " + userId));

        return new ResponseEntity<>(serie, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/series")
    public ResponseEntity<List<Serie>> getAllSeriesByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ExpressionException("Not found User with id = " + userId);
        }

        List<Serie> series = serieService.findUserById(userId);
        return new ResponseEntity<>(series, HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/series/{serieId}")
    public String deleteSerieOfUser(@PathVariable(value = "userId") Long userId,@PathVariable(value="serieId") Long serieId) {
        if (!userRepository.existsById(userId)) {
            throw new ExpressionException("Not found User with id = " + userId);
        }
        User user = userRepository.findById(userId).orElseThrow();
        Serie serie  = serieService.findByUserIdAndSerieId(serieId,userId);
        serieService.deleteUserById(serie.getId());
        return "Serie " + serieId+ " of user "+user.getFirstname()+" "+ user.getLastname()+ "is successfully deleted ! ";
    }
    @PutMapping("/users/{userId}/series/{serieId}")
    public ResponseEntity<Serie> updateSerie(@PathVariable("userId") long userId,@PathVariable("serieId") long serieId,
                                             @RequestBody Serie serieRequest) {
        Serie serie = serieService.findByUserIdAndSerieId(serieId,userId);

        serie.setTitle(serieRequest.getTitle());
        serie.setDescription(serieRequest.getDescription());

        return new ResponseEntity<>(serieService.saveSerie(serie), HttpStatus.OK);
    }
}
