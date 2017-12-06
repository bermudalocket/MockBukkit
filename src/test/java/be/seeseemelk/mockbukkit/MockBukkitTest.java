package be.seeseemelk.mockbukkit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNotNull;

import org.bukkit.Bukkit;
import org.junit.Before;
import org.junit.Test;

public class MockBukkitTest
{
	@Before
	public void setUp()
	{
		MockBukkit.setServerInstanceToNull();
	}
	
	@Test
	public void setServerInstanceToNull()
	{
		ServerMock server = new ServerMock();
		Bukkit.setServer(server);
		assumeNotNull(Bukkit.getServer());
		MockBukkit.setServerInstanceToNull();
		assertNull(Bukkit.getServer());
	}
	
	@Test
	public void mock_ServerMocked()
	{
		ServerMock server = MockBukkit.mock();
		assertNotNull(server);
		assertEquals(server, MockBukkit.getMock());
		assertEquals(server, Bukkit.getServer());
	}
	
	@Test
	public void load_MyPlugin()
	{
		MockBukkit.mock();
		TestPlugin plugin = (TestPlugin) MockBukkit.load(TestPlugin.class);
		assertTrue("Plugin not enabled", plugin.isEnabled());
		assertTrue("Plugin's onEnable method not executed", plugin.onEnableExecuted);
	}
}
