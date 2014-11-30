package edu.dcu.ticTacToeApi2.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.dcu.ticTacToeApi2.ConnectionFactory;

public class ConnectionFactoryTest {
	@Mock
	private URL urlMock;
	@Mock
	private InputStream inputStreamMock;
	
	private ConnectionFactory connectionFactory;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(urlMock.openStream()).thenReturn(inputStreamMock);
		connectionFactory = new ConnectionFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateConnection() throws IOException {
		InputStream inputStream = connectionFactory.getInputStream(urlMock);
		assertNotNull(inputStream);
	}
}
