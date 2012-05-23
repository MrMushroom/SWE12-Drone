package at.tugraz.ist.droned.test;

import static org.junit.Assert.*;

import org.junit.Test;
import at.tugraz.ist.droned.dcf.security.*;
import at.tugraz.ist.droned.Drone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MoveSecurityThreadTest.class)
public class MoveSecurityThreadTest {

	@Test
	public void TimeoutTest() {
		
/*		DroneSecurityLayer dsl = createMock(DroneSecurityLayer.class);
		MoveSecurityThread thread = new MoveSecurityThread(dsl);
		
		Whitebox.setInternalState(dsl, "hovering", true);
		Whitebox.setInternalState(dsl, "move", true);
		Whitebox.setInternalState(dsl, "moveTimeout", 5);
	    Whitebox.setInternalState(dsl, "reset",0);
	*/    
	   
	}

}
