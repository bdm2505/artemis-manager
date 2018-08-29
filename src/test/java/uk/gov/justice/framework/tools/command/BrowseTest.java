package uk.gov.justice.framework.tools.command;

import static org.mockito.Mockito.verify;

import uk.gov.justice.artemis.manager.connector.ArtemisConnector;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BrowseTest {

    @Mock
    ArtemisConnector artemisConnector;

    @InjectMocks
    Browse browseCommand;

    @Test
    public void shouldInvokeConnector() throws Exception {
        browseCommand.jmxURLs = Arrays.asList("service:jmx:rmi:///jndi/rmi://localhost:3000/jmxrmi");
        browseCommand.jmsURL = "tcp://localhost:61616";
        browseCommand.brokerName = "brokerabc";

        browseCommand.run(null);
        verify(artemisConnector).messagesOf("DLQ");
    }
}