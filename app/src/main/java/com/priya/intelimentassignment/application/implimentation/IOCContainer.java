package com.priya.intelimentassignment.application.implimentation;

import android.content.Context;

import com.priya.intelimentassignment.application.interfaces.IApplicationCommunicationService;
import com.priya.intelimentassignment.application.interfaces.IApplicationFacade;
import com.priya.intelimentassignment.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
* 
* This class creates application level objects and stores in the beanpool.
 * Objects can be retrieved by calling getBean(NAME)
*
* @author Priyanka P
*
* @createdOn 20 March, 2016
*
* @copyright
*/
public class IOCContainer
{
	private static final String TAG = "IOCContainer";

	private static IOCContainer uniqueInstance = null;

	private Context context;

	private Map<String, Object> beanPool;

	private IOCContainer()
	{
		beanPool = new HashMap<String, Object>();
	}

	public static synchronized IOCContainer getInstance()
	{
		
		if (uniqueInstance == null)
		{
			synchronized (IOCContainer.class)
			{
				if (uniqueInstance == null)
				{
					uniqueInstance = new IOCContainer();
				}
			}
		}
		return uniqueInstance;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}

	public Object getBean(String beanName)
	{
		Object bean = beanPool.get(beanName);
		if (bean == null)
		{
			bean = createBean(beanName);
			beanPool.put(beanName, bean);
		}
		return bean;
	}

	private Object createBean(String beanName)
	{
		
		if(Constants.Beans.APPLICATION_FACADE.equalsIgnoreCase(beanName))
		{
			return (IApplicationFacade)ApplicationFacade.getInstance((IApplicationCommunicationService) getBean(Constants.Beans.APPLICATION_COMMUNICATION_SERVICE));
		}
		else if(Constants.Beans.APPLICATION_COMMUNICATION_SERVICE.equalsIgnoreCase(beanName))
		{
			return (IApplicationCommunicationService) ApplicationCommunication.getInstance(context);
		}		
		return null;

	}

	public void resetIOCContainerData()
	{
		beanPool = new HashMap<String, Object>();
	}
}