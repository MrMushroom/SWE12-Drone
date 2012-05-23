package at.tugraz.ist.droned.test;

import static org.junit.Assert.*;

import org.junit.Test;
import at.tugraz.ist.droned.dcf.security.*;
import at.tugraz.ist.droned.Drone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
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
	public void MoveTimeoutReactionTest() {
		
		Drone mockDrone = createMock(Drone.class);
		String[] methods = {"isHovering", "DroneSecurityLayer"};
		
		DroneSecurityLayer dsl = createPartialMock(DroneSecurityLayer.class,methods , mockDrone);
		MoveSecurityThread thread = new MoveSecurityThread(dsl);
		
		Whitebox.setInternalState(dsl, "drone", mockDrone);
		Whitebox.setInternalState(dsl, "hovering", true);
		Whitebox.setInternalState(dsl, "move", true);
		Whitebox.setInternalState(dsl, "moveTimeout", 5);
	    Whitebox.setInternalState(dsl, "reset",0);
	    
	    
	    expect(dsl.isHovering()).andReturn(true).times(7);
	    mockDrone.move(0, 0, 0, 0);
	    expectLastCall().once();
	    expect(dsl.isHovering()).andReturn(false).times(1);
	   
	    replayAll();	    
	    thread.run();

	    
	    verifyAll();
	   
	}

	@Test
	public void MoveTimeoutWithCommandsInTimeTest() {
		
//		DroneSecurityLayer dsl = createMock(DroneSecurityLayer.class);
		Drone mockDrone = createMock(Drone.class);
		//DroneSecurityLayer dsl = new DroneSecurityLayer(mockDrone);
		String[] methods = {"isHovering", "DroneSecurityLayer"};
		
		DroneSecurityLayer dsl = createPartialMock(DroneSecurityLayer.class,methods , mockDrone);
		MoveSecurityThread thread = new MoveSecurityThread(dsl);
		
		Whitebox.setInternalState(dsl, "drone", mockDrone);
		Whitebox.setInternalState(dsl, "hovering", true);
		Whitebox.setInternalState(dsl, "move", true);
		Whitebox.setInternalState(dsl, "moveTimeout", 5);
	    Whitebox.setInternalState(dsl, "reset",0);
	    
	    
	    expect(dsl.isHovering()).andReturn(true).times(3);
	    dsl.parseCommand("move,1,1,1,1");
	    expect(dsl.isHovering()).andReturn(false).times(2);
	    
	    replayAll();	    
	    thread.run();

	    
	    verifyAll();
	    
	   
	}

	
	


}
