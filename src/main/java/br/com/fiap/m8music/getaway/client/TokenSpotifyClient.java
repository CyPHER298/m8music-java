package br.com.fiap.m8music.getaway.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "spotify-token", url = "https://accounts.spotify.com/api/token")
public interface TokenSpotifyClient {

}
