package de.adesso.anki.battle.input;

import de.adesso.anki.battle.mqtt.MqttService;
import de.adesso.anki.battle.transfer.CommandDTO;
import de.adesso.anki.battle.world.World;
import de.adesso.anki.battle.world.bodies.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebsocketInput {

    @Autowired
    private World world;

    @Autowired
    private MqttService mqtt;

    @MessageMapping("/commands")
    public void handleCommand(CommandDTO dto) {
        log.debug("Received command: " + dto);

        if (dto.getCommand().equals("changeBehavior")) {
            log.debug("Changed username");
            changeBehavior(dto.getArg1());
        }
    }

    private void changeBehavior(String username) {
        world.getDynamicBodies().forEach(body -> {
            if (body instanceof Vehicle) {
                log.debug("Changed username: " + username);
                ((Vehicle) body).setName(username);

                try {
                    mqtt.subscribe(username + "_sub");
                } catch (MqttException e) {
                    log.error("Could not subscribe new username", e);
                }
            }
        });
    }
}
