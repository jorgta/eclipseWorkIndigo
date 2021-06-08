/*
 * Created on May 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos;

/**
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.*;
import java.text.*;

import com.smsfreeworld.Beans.QuoteProduct;

public class StringList <Element> {

  ArrayList< Element > list;
  ArrayList< String > key;
  
  public StringList( )
    {
	  list = new ArrayList< Element >(0);
	  key = new ArrayList< String >(0);
    }
  
  public void add( String str, Element e)
    {
	  key.add(str);
	  list.add(e);
    }
  
  public int indexOf( String str)
    {
	  return key.indexOf( str );
    }
  
  public void remove( int index)
    {
	  key.remove( index );
	  list.remove( index );
    }

  
  
	
}


