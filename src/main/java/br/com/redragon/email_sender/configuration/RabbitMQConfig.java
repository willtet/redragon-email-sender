package br.com.redragon.email_sender.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_GARANTIA = "email_garantia_queue";
    public static final String QUEUE_SOFTWARE = "email_software_queue";
    public static final String QUEUE_OUTROS = "email_outros_queue";
    public static final String QUEUE_REVENDA = "email_revenda_queue";
    public static final String QUEUE_PARCERIA = "email_parceria_queue";
    public static final String QUEUE_DUVIDA = "email_duvida_queue";

    public static final String EXCHANGE = "email_exchange";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue garantiaQueue() {
        return new Queue(QUEUE_GARANTIA, true);
    }

    @Bean
    public Queue softwareQueue() {
        return new Queue(QUEUE_SOFTWARE, true);
    }

    @Bean
    public Queue outrosQueue() {
        return new Queue(QUEUE_OUTROS, true);
    }

    @Bean
    public Queue revendaQueue() {
        return new Queue(QUEUE_REVENDA, true);
    }

    @Bean
    public Queue parceriaQueue() {
        return new Queue(QUEUE_PARCERIA, true);
    }

    @Bean
    public Queue duvidaQueue() {
        return new Queue(QUEUE_DUVIDA, true);
    }

    @Bean
    public Binding bindingGarantia(Queue garantiaQueue, DirectExchange exchange) {
        return BindingBuilder.bind(garantiaQueue).to(exchange).with(QUEUE_GARANTIA);
    }

    @Bean
    public Binding bindingSoftware(Queue softwareQueue, DirectExchange exchange) {
        return BindingBuilder.bind(softwareQueue).to(exchange).with(QUEUE_SOFTWARE);
    }

    @Bean
    public Binding bindingOutros(Queue outrosQueue, DirectExchange exchange) {
        return BindingBuilder.bind(outrosQueue).to(exchange).with(QUEUE_OUTROS);
    }

    @Bean
    public Binding bindingRevenda(Queue revendaQueue, DirectExchange exchange) {
        return BindingBuilder.bind(revendaQueue).to(exchange).with(QUEUE_REVENDA);
    }

    @Bean
    public Binding bindingParceria(Queue parceriaQueue, DirectExchange exchange) {
        return BindingBuilder.bind(parceriaQueue).to(exchange).with(QUEUE_PARCERIA);
    }

    @Bean
    public Binding bindingDuvida(Queue duvidaQueue, DirectExchange exchange) {
        return BindingBuilder.bind(duvidaQueue).to(exchange).with(QUEUE_DUVIDA);
    }
}
