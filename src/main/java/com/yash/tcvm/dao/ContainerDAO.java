package com.yash.tcvm.dao;

import java.util.List;

import com.yash.tcvm.model.Container;

public interface ContainerDAO {

	public int getContainerListSize();

	public Container getContainerByName(String containerName);

	public List<Container> listContainers();

	public boolean updateContainer(Container updatedContainer);

}
