package co.com.sofka.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Handler {
    public Mono<ServerResponse> listenGETUseCaseGetCookie(ServerRequest serverRequest) {
        String cookie = serverRequest.queryParam("id").orElse("Id no encontrado");
        return ServerResponse.ok().body(getAnCookie(cookie), Flux.class);
    }

    public Flux<Cookie> getAnCookie(String id) {

        Flux<Cookie> cookieTypes = Flux.fromIterable(Arrays.asList(
                new Cookie("1", "Mantequilla"), new Cookie("2", "Chocolate"),
                new Cookie("3", "Vainilla"), new Cookie("4", "Avena"),
                new Cookie("5", "Coco"), new Cookie("6", "Jengibre"),
                new Cookie("7", "Chispas"), new Cookie("8", "Macadamia"),
                new Cookie("9", "Avellana"), new Cookie("10", "Especial")
        ));

        return cookieTypes
                .filter(type -> type.getId().equals(id))
                .switchIfEmpty(Mono.error(new Exception("No existe una galleta con este nombre")))
                .onErrorResume(error -> Mono.just(new Cookie(id, "Tipo de galleta no encontrado")));
    }
}
