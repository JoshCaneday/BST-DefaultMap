import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class FileSystemTest {
	@Test
	public void Outputtest() {
		FileSystem test = new FileSystem();
		test.add("Josh", "/home", "2021/02/01");
		test.add("Josh", "/home", "2020/02/01");
		test.add("Josh", "/root", "2023/02/01");

		System.out.println(test.outputNameTree());
		
		System.out.println();
		System.out.println(test.outputDateTree());
		System.out.println(test.dateTree.size());
	}
	@Test
	public void filterTest() {
		FileSystem test = new FileSystem();
		FileSystem expected = new FileSystem();
		test.add("Josh", "/home", "2021/02/01");
		test.add("Thomas", "/home", "2020/02/01");
		test.add("Joshua", "/root", "2023/02/01");
		test.add("Scott", "/root", "2024/02/01");
		
		expected.add("Josh", "/home", "2021/02/01");
		expected.add("Joshua", "/root", "2023/02/01");
		assertEquals(expected.outputNameTree(), test.filter("osh").outputNameTree());
	}
	@Test
	public void allDifferentDuplicateTest() {
		FileSystem test = new FileSystem();
		test.add("Josh", "/home", "2021/02/01");
		test.add("Josh", "/home", "2021/02/01");
		test.add("Josh", "/root", "2021/02/01");
		test.add("Josh", "/root", "2023/02/01");
		test.add("Josh", "/Users", "2019/02/01");
		
		assertEquals(1, test.dateTree.size());
		assertEquals("2023/02/01", test.dateTree.keys().get(0));
	}
	

}