package ch.simas.jtoggl.util;


import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

/**
 * A ClientFilter implementation that delays for a fixed period of time before allowing the next
 * filter to be called. This can be used to throttle every request sent to an API endpoint with rate
 * limitations, or to perhaps as a simple simulation of network issues.
 * 
 * @author cewing
 *
 */
public class DelayFilter implements ClientRequestFilter {

	private long throttlePeriod;

	public DelayFilter(long throttlePeriod) {
		if (throttlePeriod <= 0L) {
			throw new IllegalArgumentException("Must be positive throttlePeriod");
		}
		this.throttlePeriod = throttlePeriod;
	}


	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		try {
			Thread.sleep(throttlePeriod);
		} catch (InterruptedException e) {
			// ignore, except to propagate
			Thread.currentThread().interrupt();
		}
	}
}