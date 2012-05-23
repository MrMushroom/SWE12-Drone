package at.tugraz.ist.droned.test;

/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import at.tugraz.ist.droned.Drone;
import at.tugraz.ist.droned.IDrone;
import at.tugraz.ist.droned.dcf.ThreadNavData;
import at.tugraz.ist.droned.dcf.WiFiConnection;
import at.tugraz.ist.droned.dcf.navdata.NavData;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ThreadNavData.class)
public class WatchdogTest {

	@Test
	public void ConnectNavTest() throws Exception {
		// mockStatic(SimpleConfig.class);

		// SimpleConfig sc = createMock(SimpleConfig.class);

		// Hier wird nur definiert was erwartet wird
		// Es wird noch nicht ausgeführt
		DatagramSocket socketNAV = createMock(DatagramSocket.class);
		byte[] buffer = new byte[31];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = ' '; // is egal
		}
		// das ist für den header
		buffer[3] = (byte) 0x55;
		buffer[2] = (byte) 0x66;
		buffer[1] = (byte) 0x77;
		buffer[0] = (byte) 0x88;
		// -------------------------
		DatagramPacket dg = new DatagramPacket(buffer, buffer.length);// createMock(DatagramPacket.class);
		// WiFiConnection wifi = createMock(WiFiConnection.class);
		// expect(mock_drone.setConfiguration("", true)).andReturn(true);

		// expect(dg.getLength()).andReturn(24);
		// expect(dg.getData()).andReturn(buffer);

		socketNAV.receive((DatagramPacket) anyObject());
		expectLastCall().anyTimes();

		// expectPrivate(DatagramSocket.class, "receive", dg);

		// Jetzt wird geprüft, ob das oben definierte Zeug auch wirklich
		// das zurück gibt was es soll
		// IDrone d = Drone.getInstance();
		final byte[] trigger_bytes = { 0x01, 0x00, 0x00, 0x00 };
		// NavData nav = new NavData();
		NavData nav = createMock(NavData.class);
		nav.loadFromData(buffer);
		expectLastCall().anyTimes();
		Whitebox.setInternalState(nav, "COM_WATCHDOG_Bit", true);
		WiFiConnection wifi = createMock(WiFiConnection.class);
		wifi.setSocketNAV(socketNAV);
		expectLastCall().anyTimes();

		expect(wifi.isRunning()).andReturn(true);
		expect(wifi.isRunning()).andReturn(false);
		Whitebox.setInternalState(wifi, "trigger_bytes", trigger_bytes);
		Whitebox.setInternalState(wifi, "INTERVAL", 30);
		wifi.connectNAV();
		expectLastCall().anyTimes();
		wifi.sendAtCommand("AT*COMWDG=1");
		expectLastCall().times(1);
		wifi.resetSeqNumber();
		expectLastCall();
		replayAll();
		ThreadNavData tnd = new ThreadNavData(wifi, nav);
		// tnd.setDatagramPacket(dg);
		tnd.setBuffer(buffer);
		tnd.start();
		tnd.join();
		// tnd.setFirsttestcase(1);
		assertEquals(1, tnd.getFirsttestcase());
		// assertEquals(true, );

		verifyAll();

	}
}
