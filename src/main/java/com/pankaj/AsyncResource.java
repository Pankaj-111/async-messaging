package com.pankaj;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;


@Path("/greet")
public class AsyncResource {

	@Inject
	private EventBus eventBus;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> hello(@QueryParam("name") String name) {
		return eventBus.<String>request("demo-topic", name)
				.onItem().
				transform(Message::body);

	}
}