package com.yash.tcvm.daoimpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.exception.ConfigurationFileNotFoundException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.messages.ExceptionMessage;
import com.yash.tcvm.model.Container;

public class ContainerDAOImpl implements ContainerDAO {
	
	private List<Container> containerList;
	
	private Gson gson;

	public ContainerDAOImpl(String fileName) {
		containerList = getContainers(fileName);
		gson = new Gson();
	}
	
	public ContainerDAOImpl() {
		
		gson = new Gson();
		containerList = getContainers();
		
	}

	
	private List<Container> getContainers() {
		List<Container> containerList = new ArrayList<Container>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("Containers.json"));
			containerList = gson.fromJson(br, new TypeToken<List<Container>>() {
			}.getType());
			
		} catch (IOException e) {
			throw new ConfigurationFileNotFoundException(ExceptionMessage.WHEN_FILE_NAME_GIVEN_IS_WRONG);
		}
		return containerList;
	}

	
	private List<Container> getContainers(String fileName) {
		
		List<Container> containerList = new ArrayList<Container>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			containerList = gson.fromJson(br, new TypeToken<List<Container>>() {
			}.getType());
			
		} catch (IOException e) {
			throw new ConfigurationFileNotFoundException(ExceptionMessage.WHEN_FILE_NAME_GIVEN_IS_WRONG);
		}
		return containerList;
	}

	@Override
	public int getContainerListSize() {
		return containerList.size();
	}

	@Override
	public Container getContainerByName(String materialName) {
		Container containerForMaterial = null;
		if(materialName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_CONTAINER_NAME_IS_NULL);
		}
		
		for (Container container : containerList) {
			if(container.getMaterialName().equals(materialName)) {
				containerForMaterial = container;
			}
		}
		return containerForMaterial;
	}

	@Override
	public List<Container> listContainers() {
		return containerList;
	}

	
	
	

}
