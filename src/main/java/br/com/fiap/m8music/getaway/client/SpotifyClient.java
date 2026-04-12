package br.com.fiap.m8music.getaway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "spotify", url = "https://api.spotify.com/v1")
public interface SpotifyClient {

    @GetMapping
    String middlewareCall();
}
