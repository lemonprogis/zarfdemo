package com.lemonprogis.zarfdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemonprogis.zarfdemo.model.Joke;
import com.lemonprogis.zarfdemo.model.Jokes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class JokesService {
    private final Resource jokeData;

    public JokesService(@Value("classpath:jokes.json") Resource jokeData) {
        this.jokeData = jokeData;
    }

    public Joke getRandomJoke() throws IOException {
        List<Joke> jokes = getJokeData();
        Random rand = new Random();

        return jokes.get(rand.nextInt(jokes.size()));
    }

    private List<Joke> getJokeData() throws IOException {
        ObjectMapper om = new ObjectMapper();
        Jokes jokes = om.readValue(jokeData.getFile(), Jokes.class);
        return jokes.getJokes();
    }
}
