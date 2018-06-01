package com.yash.tcvm.dao;

import java.util.List;

import com.yash.tcvm.model.Container;

public interface ContainerDAO {

	int getContainerListSize();

	Container getContainerByName(String containerName);

	List<Container> listContainers();

	boolean updateContainer(Container updatedContainer);

}
