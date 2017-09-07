/**
 *
 */
package sc_154;

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

    @Bean
    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public org.springframework.integration.transformer.Transformer decodingPayloadTransformer() {
        return (msg) -> {
            simulateLongRunningOperation();
            return msg;
        };
    }

    private void simulateLongRunningOperation() {
        LOGGER.info("Simulating long-running operation");
        try {
            Thread.sleep(30_000);
        } catch (InterruptedException ie) {
            // ignore
        }
    }

}