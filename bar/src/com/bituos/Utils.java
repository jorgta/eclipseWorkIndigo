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

public class Utils {



/**
 * @return Retorna fecha en formato string: yyyy/mm/dd
 */
	public static String DateToStrInv( Date d )
	  {
	  	/*
	  	String s = d.getYear() + "/" + d.getMonth() + "/" + d.getDate();

		return s;
		*/

		GregorianCalendar c = new GregorianCalendar();
        
		c.setTime( d );
		return c.get( Calendar.YEAR) + "/" + (c.get( Calendar.MONTH) + 1) + "/" + c.get( Calendar.DATE);
	  }

/**
 * @return Retorna fecha en formato string
 */
	public static String DateToStr( Date d )
	  {
	  	String s = d.toString();

		GregorianCalendar c = new GregorianCalendar();

		c.setTime( d );

		s = c.getTime().toString();

		//return d.getDate() + "/" + d.getMonth() + "/" + d.getYear();
		return c.get( Calendar.DATE ) + "/" + (c.get( Calendar.MONTH) + 1) + "/" + c.get( Calendar.YEAR);
	  }
	
	
	/**
	 * @return Retorna fecha en formato string
	 */
		public static String DateToStr1( Date d )
		  {
		  	/*
		  	String s = d.getYear() + "/" + d.getMonth() + "/" + d.getDate();

			return s;
			*/

			GregorianCalendar c = new GregorianCalendar();
	        
			c.setTime( d );
			return c.get( Calendar.YEAR) + "/" + (c.get( Calendar.MONTH) + 1) + "/" + c.get( Calendar.DATE);
		  }

/**
 * @return Retorna dia actual
 */
	public static Date Today()
	  {
		GregorianCalendar c = new GregorianCalendar();

	    c.set(  new Integer( c.get(GregorianCalendar.YEAR)).intValue(),
				new Integer( c.get(GregorianCalendar.MONTH)).intValue(),
				new Integer( c.get(GregorianCalendar.DATE)).intValue(), 0, 0, 0 );

		String sss = c.getTime().toString();

		return  c.getTime();
	  }

/**
 * @return Retorna true si es una fecha
 */
	public static boolean IsDate( String str)
	  {
		try
		  {
		  	/*
			long  l = Date.parse( str );
			Date d1 = new Date( l );

			DateFormat dformat = DateFormat.getDateInstance();
                              d1 = dformat.parse( str );
            
			int f = str.indexOf("/");

			if ( f < 0 )
			  {
				return false;
			  }

			int s = str.indexOf("/", f+1);

			String d  = str.substring(0, f);
			String m = str.substring(f+1, s);
			String y  = str.substring(s+1, str.length() );

			int df = new Integer( d ).intValue();
			int mf = new Integer( m ).intValue();
			int yf = new Integer( y ).intValue();

			//Date date = new Date(yf, mf, df);

			GregorianCalendar c = new GregorianCalendar(yf, mf, df);
			*/
			
			/*ESTA ES UNA FUNCION QUE ENCONTRE QUE VALIDA LA FECHA.
			*/
			
			String fdt = "dd/MM/yyyy"; //formato que utiliza
			SimpleDateFormat sdf = new SimpleDateFormat( fdt );
                              sdf.setLenient( false );
                              Date date = sdf.parse( str );
			
			
			//O tambien puede quedar asi:
			//Date date = fdt.parse(str); 
			
			return true;
		  }
		catch( Throwable  m )
		  {
			return false;
		  }
	  }

	public static boolean isInt(String str)
	  {
		try 
		  {
			int i = new Integer(str).intValue();
			return true;
		  } 
		catch ( Throwable  m )
		  {
			return false;
		  }
	  }
	
	public static boolean isDouble(String str)
	  {
		try 
		  {
			double i = new Double(str).doubleValue();
			return true;
		  } 
		catch ( Throwable  m )
		  {
			return false;
		  }
	  }


/**
 * @return Retorna una fecha
 */
	public static Date StrToDate( String str )
	  {
		int df;
		int mf;
		int yf;

		try
		  {
			long  l = Date.parse( str );
			Date d1 = new Date( l );

			int f = str.indexOf("/");

			if ( f < 0 )
			  {
			  	//throw new ConvertException();
				return new Date();
			  }

			int s = str.indexOf("/", f+1);

			String d = str.substring(0, f);
			String m = str.substring(f+1, s);
			String y = str.substring(s+1, str.length() );

			df = new Integer( d ).intValue();
			mf = new Integer( m ).intValue();
			yf = new Integer( y ).intValue();

			GregorianCalendar c = new GregorianCalendar( yf, mf-1, df);

			String sss = c.getTime().toString();

			return  c.getTime();
		  }
		catch( Throwable  m )
		  {
			return null;
		  }
	  }


/**
 * @return Retorna fecha posterior
 */
	static Date Date_Next( Date d )
	  {
		String test = Utils.DateToStrInv( d );
		Date f = d;

		//f.setDate( f.getDate()+1 );
		GregorianCalendar c = new GregorianCalendar();

		c.setTime( d );

		c.add( GregorianCalendar.DATE, 1);

		test = Utils.DateToStrInv( c.getTime() );

		//return f;
		return c.getTime();
	  }

	/**
	 * @return Retorna fecha mas un número de dias
	 */
		public static Date Date_plus( Date d, int days  )
			{
			String test = Utils.DateToStrInv( d );
			Date f = d;

			//f.setDate( f.getDate()+1 );
			GregorianCalendar c = new GregorianCalendar();

			c.setTime( d );

			c.add( GregorianCalendar.DATE, days);

			test = Utils.DateToStrInv( c.getTime() );

			//return f;
			return c.getTime();
			}

/**
 * @return Retorna fecha anterior
 */
	static Date Date_Before( Date d )
	  {
		String test = Utils.DateToStrInv( d );
		Date f = d;

		//f.setDate( f.getDate()-1 );

		GregorianCalendar c = new GregorianCalendar();

		c.setTime( d );

		c.add( GregorianCalendar.DATE, -1);

		test = Utils.DateToStrInv( c.getTime() );

		return c.getTime();
	  }

/**
 * @return Retorna true si la fecha es anterior
 */
	static boolean before( Date d1, Date d2)
	  {
		int v1 = d1.getYear() * 365 + d1.getMonth()*30 + d1.getDate();
		int v2 = d2.getYear() * 365 + d2.getMonth()*30 + d2.getDate();

		return  v1 < v2;
	  }

/**
 * @return Retorna true si la fecha es igual
 */
	static boolean equal( Date d1, Date d2)
	  {
		int v1 = d1.getYear() * 365 + d1.getMonth()*30 + d1.getDate();
		int v2 = d2.getYear() * 365 + d2.getMonth()*30 + d2.getDate();

		return  v1 == v2;
	  }

/**
 * @return Retorna fecha y hora actual
 */
	public String getCurrentMoment()
	  {
		GregorianCalendar c = new GregorianCalendar();

		String p =  new Integer( c.get(GregorianCalendar.YEAR)).toString() +
		            new Integer( c.get(GregorianCalendar.MONTH)).toString() +
		            new Integer( c.get(GregorianCalendar.DATE)).toString() +
					new Integer( c.get(GregorianCalendar.HOUR)).toString() +
					new Integer( c.get(GregorianCalendar.MINUTE)).toString() +
					new Integer( c.get(GregorianCalendar.SECOND)).toString() +
					new Integer( c.get(GregorianCalendar.MILLISECOND)).toString();

	  	return p;
	  }

	/**
	 * @return Retorna diferencia en dias entre dos fechas
	 */

		public static int dateDif( Date d1, Date d2 )
			{
			GregorianCalendar c = new GregorianCalendar();
        
			c.setTime( d1 );
			int count = 0;
			
			while ( c.getTime().before(d2) )
			  {
			    c.add( GregorianCalendar.DATE, 1);
			    count++;
			  }
			
			return count;
			}

	public static boolean isEmail(String str) {
		int posArroba= str.indexOf("@");

		if (posArroba ==-1)
			return false;
	    
		int posEnd = str.length()-1;
		String user,domain;

		domain=str.substring(posArroba+1,posEnd+1);
		user=str.substring(0,posArroba);

		int posPoint=str.indexOf(".",posArroba);

		if(posPoint==-1)
			return false;
			    
		if(posArroba>=posEnd||str.endsWith("-"))
			return false;
		
		
		if(!isValidEmailString(user)||!isValidEmailString(domain)||domain.startsWith("- ")||domain.indexOf("-")>-1)
			return false;
		else 
			return true;		

	}

	public static boolean isValidEmailString(String str){
		String stringValidChars="zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP0123456789_-. ";
		String sprb;
		int prb;
		
		if ( !str.startsWith(".") && !str.endsWith(".") && !str.startsWith("-") && str.length() > 0 )
		  {
			for(int i=0;i<str.length();i++)
			  {
				prb=stringValidChars.indexOf(stringValidChars.substring(i,i+1));
				sprb=str.substring(i,i+1);
				if(stringValidChars.indexOf(str.substring(i,i+1))==-1)
					return false;		
		      }
		 }
		else
		  return false;
	
		return true;	
	}


	
	public static int dayOfWeek1( Date date)
	  {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		
		int dow = calendar.get(Calendar.DAY_OF_WEEK);

		return dow;
	  }
	

	public static Date StrToDate1( String str )
	  {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		
		try 
		  {

			date = format.parse(str);

		  } 
		catch (ParseException ex) 
		  {
			ex.printStackTrace();
		  }

		return date;
	  }
	

	
	public static boolean before1( Date d1, Date d2 )
	  {
		GregorianCalendar c = new GregorianCalendar();
	
		c.setTime( d1 );
		
		String f1 = DateToStr( d1 );
		String f2 = DateToStr( d2 );
		
		if ( c.getTime().before(d2) )
		  return true;
		else
		  return false;
	  }	

	
	
}


