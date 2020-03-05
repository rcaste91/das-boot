package com.boot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

public class ShipwreckControllerTest {
	
	@InjectMocks
	private ShipwreckController sc;
	@Mock
	private ShipwreckRepository shipwreckRepository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShipwreckGet(){
		
		Shipwreck sw = new Shipwreck();
		sw.setId(1L);
		when(shipwreckRepository.findOne(1L)).thenReturn(sw);
		
		Shipwreck wreck = sc.get(1L);
		
		//Verify that call was only called once
		verify(shipwreckRepository).findOne(1L);
		
		//assertEquals(1L,wreck.getId().longValue());
		assertThat(wreck.getId(), is(1L));
	}
	
	@Test
	public void testShipwreckCreate(){
		
		Shipwreck sw= new Shipwreck();
		sw.setName("r");
		sw.setId(2L);
		sw.setDescription("ron");
		
		
		Shipwreck wreck=new Shipwreck();
		
		when(shipwreckRepository.saveAndFlush(wreck)).thenReturn(sw);
		
		wreck.setName("r");
		wreck.setId(2L);
		wreck.setDescription("ron");
		
		Shipwreck wreckInsert=sc.create(wreck);
		
		assertEquals(wreck.getName(),wreckInsert.getName());
		
		
	}
}
