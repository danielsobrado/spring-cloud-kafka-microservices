package com.jds.jvmcc.reviewservice.reactive.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Supplier;

import com.jds.jvmcc.reviewservice.reactive.entity.Review;
import com.jds.jvmcc.reviewservice.reactive.repository.ReviewRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@Slf4j
@Component
class ReviewDataLoader implements ApplicationRunner {

    @Autowired
    private final ReviewRepository reviewRepository;

    @Override
    public void run(final ApplicationArguments args) {
        if (reviewRepository.count().block() == 0L) {
            var idSupplier = getIdSequenceSupplier();
            var bufferedReader = new BufferedReader(
                    new InputStreamReader(getClass()
                            .getClassLoader()
                            .getResourceAsStream("pg2000.txt"))
            );
            Flux.fromStream(
                    bufferedReader.lines()
                            .filter(l -> !l.trim().isEmpty())
                            .map(l -> reviewRepository.save(
                                    new Review(idSupplier.get(),
                                            "El Quijote", l))
                            )
            ).subscribe(m -> log.info("New review loaded: {}", m.block()));
            log.info("Repository contains now {} entries.",
                reviewRepository.count().block());
        }
    }

    private Supplier<String> getIdSequenceSupplier() {
        return new Supplier<>() {
            Long l = 0L;

            @Override
            public String get() {
                return String.format("%05d", l++);
            }
        };
    }
}
