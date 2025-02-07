package br.com.redragon.email_sender.consumer;

import br.com.redragon.email_sender.configuration.RabbitMQConfig;
import br.com.redragon.email_sender.dtos.*;
import br.com.redragon.email_sender.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_GARANTIA)
    public void processarGarantia(GarantiaDto dto) {
        emailService.enviarGarantia(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SOFTWARE)
    public void processarSoftware(SoftwareDto dto) {
        emailService.enviarSoftware(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_OUTROS)
    public void processarOutros(OutrosDto dto) {
        emailService.enviarOutros(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_REVENDA)
    public void processarRevenda(RevendaDto dto) {
        emailService.enviarRevenda(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PARCERIA)
    public void processarParceria(ParceriasDto dto) {
        emailService.enviarParcerias(dto);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DUVIDA)
    public void processarDuvida(DuvidasDto dto) {
        emailService.enviarDuvidas(dto);
    }
}
