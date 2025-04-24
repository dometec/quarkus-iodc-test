package testiodc;

import java.time.Duration;

import io.quarkus.oidc.AccessTokenCredential;
import io.quarkus.oidc.TenantIdentityProvider;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApplicationLifecycleListener {

	@Inject
	TenantIdentityProvider identityProvider;

	public void init(@Observes StartupEvent ev) {

		AccessTokenCredential tokenCredential = new AccessTokenCredential("fakeaccesstoken");
		SecurityIdentity securityIdentity = identityProvider.authenticate(tokenCredential).await()
				.atMost(Duration.ofSeconds(30));

		System.out.println(securityIdentity);

	}

}
