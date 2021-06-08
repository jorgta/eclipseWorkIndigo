package com.bituos.bituosMonitor;

import java.util.ArrayList;
import java.util.List;

import com.bituos.bituosMonitor.Beans.*;
import com.struts2.Beans.*;


public class ServiceConfiguration {
	private int timeToReport;

	public Counter counterList[];
	public int counterCount = 0; 

	public BeanCommand commandList[];
	public int commandCount = 0; 

	private int error;
	private String errorStr;

	
	public ServiceConfiguration()
	  {
		counterCount = 0;	
		counterList = null;

	  }

	public ServiceConfiguration(int newCounterCount, int newCommandCount)
	  {
		counterCount = newCounterCount;
		counterList = new Counter[counterCount];

		commandCount = newCommandCount;
		commandList = new BeanCommand[commandCount];
	  }
	
	public void redimCounter(int count)
	  {
		Counter newArray[] = new Counter[ count ];
		int moveCount = counterCount;
		
		if ( count==0 || count < moveCount )
		  moveCount = count;
		
		for(int i=0; i<moveCount; i++)
		  {
			newArray[ i ] = counterList[ i ];
		  }
		
		counterList = newArray;
		counterCount = count;
	  }

	public void redimCommand(int count)
	  {
		BeanCommand newArray[] = new BeanCommand[ count ];
		int moveCount = commandCount;
		
		if ( count==0 || count < moveCount )
		  moveCount = count;
		
		for(int i=0; i<moveCount; i++)
		  {
			newArray[ i ] = commandList[ i ];
		  }
		
		commandList = newArray;
		commandCount = count;
	  }

	
	public Counter counterAt(int index )
		{
		  return counterList[ index ];
		}
	
	public void putCounterAt(int index, Counter counter )
	{
      if (index > counterCount || counterCount==0 )
    	redimCounter( index + 10);
      
      Counter newCounter = new Counter();
      
      newCounter.setId_counter( counter.getId_counter() );
      newCounter.setClassName( counter.getClassName() );
      newCounter.setPropertyName( counter.getPropertyName() );
      newCounter.setTimeToRepot( counter.getTimeToRepot() );
      
	  counterList[ index ] = newCounter; 
	}

	
	public void putCommandAt(int index, BeanCommand beanCommand )
	{
      if (index > commandCount || commandCount==0 )
    	redimCounter( index + 10);
	  commandList[ index ] = beanCommand; 
	}

	//getters and setters

	public String getErrorStr() {
		return errorStr;
	}


	public int getTimeToReport() {
		return timeToReport;
	}


	public int getError() {
		return error;
	}

	public int getCounterCount() {
		return counterCount;
	}

	public void setErrorStr(String errorStr) {
		this.errorStr = errorStr;
	}

	public void setError(int error) {
		this.error = error;
	}

	public void setTimeToReport(int timeToReport) {
		this.timeToReport = timeToReport;
	}

	public int getCommandCount() {
		return commandCount;
	}



	


}
