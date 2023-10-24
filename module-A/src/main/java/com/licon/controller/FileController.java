package com.licon.controller;

import com.licon.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author Licon
 * @description: TODO
 * @date 2023/10/24 18:40
 */
@RestController
public class FileController {


    @Autowired
    WebClient webClient;

    @PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFile(@RequestPart("metadata") FileDto fileDto, @RequestPart("files") Flux<FilePart> files) throws IOException {

        Assert.notNull(fileDto,"fileDto not null");
        Assert.notNull(files,"files not null");

        long start = System.currentTimeMillis();
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        Mono<String> mono = files.flatMap(file -> {
                    formData.add("files", file);
                    return Mono.empty();
                })
                .thenMany(
                        webClient.post()
                                .uri("http://localhost:8082/file")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .body(BodyInserters.fromMultipartData(formData))
                                .retrieve()
                                .bodyToMono(String.class)
                )
                .last()
                .defaultIfEmpty("Files forwarded successfully.");
        System.out.println(System.currentTimeMillis()-start);
        return mono;
    }
}
