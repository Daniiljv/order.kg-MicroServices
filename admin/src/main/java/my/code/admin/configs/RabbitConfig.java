package my.code.admin.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queues.requestEstablishment.name}")
    private String requestEstablishmentQueueName;

    @Value("${spring.rabbitmq.queues.responseEstablishment.name}")
    private String responseEstablishmentQueueName;

    @Value("${spring.rabbitmq.queues.requestEstablishment.routing-key}")
    private String requestEstablishmentRoutingKey;

    @Value("${spring.rabbitmq.queues.responseEstablishment.routing-key}")
    private String responseEstablishmentRoutingKey;


    @Value("${spring.rabbitmq.ownerExchange.name}")
    private String ownerExchangeName;

    @Value("${spring.rabbitmq.ownerQueues.requestOwner.name}")
    private String requestOwnerQueueName;

    @Value("${spring.rabbitmq.ownerQueues.responseOwner.name}")
    private String responseOwnerQueueName;

    @Value("${spring.rabbitmq.ownerQueues.requestOwner.routing-key}")
    private String requestOwnerRoutingKey;

    @Value("${spring.rabbitmq.ownerQueues.responseOwner.routing-key}")
    private String responseOwnerRoutingKey;



    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public DirectExchange ownerExchange() {
        return new DirectExchange(ownerExchangeName);
    }



    @Bean
    public Queue requestEstablishmentQueue() {
        return new Queue(requestEstablishmentQueueName);
    }

    @Bean
    public Queue responseEstablishmentQueue() {
        return new Queue(responseEstablishmentQueueName);
    }

    @Bean
    public Queue requestOwnerQueue(){
        return new Queue(requestOwnerQueueName);
    }

    @Bean
    public Queue responseOwnerQueue(){
        return new Queue(responseOwnerQueueName);
    }



    @Bean
    public Binding requestEstablishmentBinding() {
        return BindingBuilder
                .bind(requestEstablishmentQueue())
                .to(directExchange())
                .with(requestEstablishmentRoutingKey);
    }

    @Bean
    public Binding responseEstablishmentBinding(){
        return BindingBuilder
                .bind(responseEstablishmentQueue())
                .to(directExchange())
                .with(responseEstablishmentRoutingKey);
    }

    @Bean
    public Binding requestOwnerBinding() {
        return BindingBuilder
                .bind(requestOwnerQueue())
                .to(ownerExchange())
                .with(requestOwnerRoutingKey);
    }

    @Bean
    public Binding responseOwnerBinding(){
        return BindingBuilder
                .bind(responseOwnerQueue())
                .to(ownerExchange())
                .with(responseOwnerRoutingKey);
    }




    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
