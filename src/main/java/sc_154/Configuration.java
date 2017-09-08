/**
 *
 */
package sc_154;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Transformer;

/**
 */
@EnableBinding(Processor.class)
public class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    private final AtomicInteger msgCount = new AtomicInteger(0);

    @Bean
    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public org.springframework.integration.transformer.Transformer transformer() {
        return (msg) -> {
            simulateMessageHandling();
            return msg;
        };
    }

    private void simulateMessageHandling() {
        if (msgCount.get() < 5) {
            LOGGER.info("Simulating long-running logic...");
            try {
                Thread.sleep(30_000);
            } catch (InterruptedException ie) {
                // ignore
            }
        }
        LOGGER.info("Message handled, total count = {}", msgCount.incrementAndGet());
    }

}