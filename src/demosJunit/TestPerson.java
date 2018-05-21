package demosJunit;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TestPerson {
	
	private Person p = new Person();
	
	// create le resource for test
	@Before
	public void before() {
		System.out.println("before");
		//p = new Person();
	}
	@Test
	public void testRun() {
		//Person p = new Person();
		p.run();
	}
	
	@Test
	public void testEat() {
		//Person p = new Person();
		p.eat();
	}
	
	//release resource for test
	@After
	public void after() {
		System.out.println("after");
	}

}
