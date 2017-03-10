import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class MainTest extends TestCase{

	Main c= new Main();
	private Object in;
	private Object list;
	@Test
    public void testLoad() throws Exception 
	{
        c.crawl();
        assertNotNull(c.list);
        c.index();
        assertNotNull(c.in.get("means"));
	}		
}
