import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	/* TODO: Add your own tests */
	@Test
	public void removeRootTest() {
		DefaultMap<String, String> test = new BST<>();
		test.set("f", "first");
		test.set("a", "second");
		test.set("g", "fourth");
		test.remove("f");
		assertEquals(2, test.size());
	}
	@Test
	public void getTest() {
		DefaultMap<String, String> test = new BST<>();
		test.set("fast", "1");
		test.set("jump", "2");
		test.set("karate", "3");
		test.set("iodine", "4");
		assertEquals(null, test.get("me"));
		assertEquals("3", test.get("karate"));
	}
}
