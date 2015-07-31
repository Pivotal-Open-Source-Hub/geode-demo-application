package org.apache.geode.demo.fastfootshoes.clusterside.test;

import javax.annotation.Resource;

import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gemstone.gemfire.cache.Region;

@ContextConfiguration("/cache-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWriters {
	
	@Resource(name="Alert")
	private Region<String,Alert> alertRegion;
	
	@Test
	public void testWrite() {
		Alert alert = new Alert();
		alert.setId("TestAlert");
		alert.setMessage("Why won't our writer work?!?!?!");
		
		alertRegion.put(alert.getId(), alert);
	}
}
