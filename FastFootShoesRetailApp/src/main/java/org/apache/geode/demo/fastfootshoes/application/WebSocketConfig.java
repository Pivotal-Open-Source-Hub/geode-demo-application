/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author lshannon
 *
 */
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/notifications").withSockJS();
    }

    @Override
    	public void configureClientInboundChannel(ChannelRegistration registration) {
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.taskExecutor().corePoolSize(4).maxPoolSize(10);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue/", "/topic/");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
