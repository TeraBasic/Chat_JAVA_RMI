package demosJunit;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestPerson2 {
	
	@Test
	public void testRun() {
		Person p = new Person();
		Assert.assertEquals("run!", p.run());
		//p.run();
	}
	
	@Test
	public void testEat() {
		Person p = new Person();
		p.eat();
	}

}
