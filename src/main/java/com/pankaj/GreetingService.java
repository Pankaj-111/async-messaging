package com.pankaj;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class GreetingService {

	@ConsumeEvent(value = "demo-topic")
	public Uni<String> onMessage(String name) throws InterruptedException {
		Thread.sleep(1000);
		return Uni
				.createFrom()
				.item(() -> "Hello, " + name.toUpperCase());
	}
}
