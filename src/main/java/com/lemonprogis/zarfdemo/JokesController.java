package com.lemonprogis.zarfdemo;

import com.lemonprogis.zarfdemo.model.Joke;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class JokesController {
    private final JokesService jokesService;

    public JokesController(JokesService jokesService) {
        this.jokesService = jokesService;
    }

    @GetMapping("/random")
    public Joke getRandomJoke() throws IOException {
        return jokesService.getRandomJoke();
    }
}
