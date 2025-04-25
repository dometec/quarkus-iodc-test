package testiodc;

import java.time.Duration;

import io.quarkus.oidc.AccessTokenCredential;
import io.quarkus.oidc.TenantIdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/check")
public class Resource {

	@Inject
	TenantIdentityProvider identityProvider;

	@GET
	public void init() {

		AccessTokenCredential tokenCredential = new AccessTokenCredential("fakeaccesstoken");
		SecurityIdentity securityIdentity = identityProvider.authenticate(tokenCredential).await()
				.atMost(Duration.ofSeconds(30));

		System.out.println(securityIdentity);

	}

}
