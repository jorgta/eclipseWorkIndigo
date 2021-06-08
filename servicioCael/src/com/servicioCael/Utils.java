/*
 * Created on May 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.servicioCael;


/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.*;
import java.text.*;
import com.bituos.Beans.BeanStates;
import javax.servlet.http.*;


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

	public static double roundNum(double num) throws Exception
	{
		double valor = 0;

		valor = num;

		valor = valor*100;
		valor = java.lang.Math.round(valor);
		valor = valor/100;

		return valor;
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

	public static boolean IsTime( String str)
	  {
		try
		  {
				String fdt = "HH:mm:ss"; //formato que utiliza
				SimpleDateFormat stf = new SimpleDateFormat(fdt);

				stf.setLenient( false );
				Date date = stf.parse( str );
				return true;
		  }
		catch( Throwable  m )
		  {
			return false;
		  }
	  }


	public static boolean IsDouble(String str)
	{
		try
		{
				double dbl = Double.valueOf(str.trim()).doubleValue();
				return true;
		}
		catch( Throwable  m )
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


		//	long  l = Date.parse( str );
		String strTmp = str;
		Date d1 = new SimpleDateFormat("MM/dd/yy").parse(strTmp);
	//	String strOutDt = new SimpleDateFormat("yyyy-MM-dd").format(d1);




			//Date d1 = new Date( l );

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
			m.printStackTrace();
			return null;
		  }
	  }


	/**
	 * @return Retorna un valor tipo Date
	 * Requiere un String que contenga los datos como siguen:
	 * yyyy-MM-dd HH:mm:ss
	 */
		public static Date toDate(String str)
		  {
			try
			{
				Date d = new Date();

				int year=Integer.parseInt(str.substring(0,4));
				int month=Integer.parseInt(str.substring(5,7));
				int day=Integer.parseInt(str.substring(8,10));

				int hours=Integer.parseInt(str.substring(11,13));
				int minutes=Integer.parseInt(str.substring(14,16));
				int seconds=Integer.parseInt(str.substring(17,19));

				d.setYear(year - 1900);
				d.setMonth(month - 1);
				d.setDate(day);
				d.setHours(hours);
				d.setMinutes(minutes);
				d.setSeconds(seconds);

				return  d;
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

		String p = new Integer( c.get(GregorianCalendar.YEAR)).toString() +
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

		public static int Datedif( Date d1, Date d2 )
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


final static String MILLON       = "MILLON "      ;
final static String MILLONES     = "MILLONES "	   ;
final static String MIL          = " MIL "	   ;
final static String MILES        = " MILES "	   ;
final static String cien         = "cien "	   ;
final static String Ciento       = "ciento "	   ;
final static String Cientos      = "cientos "	   ;
final static String centavos     = "centavos "	   ;
final static String un           = "un"	   ;
final static String uno          = "uno"	   ;
final static String dos          = "dos"	   ;
final static String tres         = "tres"	   ;
final static String cuatro       = "cuatro"	   ;
final static String cinco        = "cinco"	   ;
final static String seis         = "seis"	   ;
final static String siete        = "siete"	   ;
final static String sete         = "sete"	   ;
final static String ocho         = "ocho"	   ;
final static String nueve        = "nueve"	   ;
final static String nove         = "nove"	   ;

final static String diez         = "diez "	   ;
final static String once         = "once "	   ;
final static String doce         = "doce "	   ;
final static String trece        = "trece "	   ;
final static String catorce      = "catorce "	   ;
final static String quince       = "quince "	   ;
final static String dieciseis    = "dieciseis "   ;
final static String diecisiete   = "diecisiete "  ;
final static String dieciocho    = "dieciocho "   ;
final static String diecinueve   = "diecinueve "  ;

final static String veinte       = "veinte "	   ;
final static String treinta      = "treinta "	   ;
final static String cuarenta     = "cuarenta "	   ;
final static String cincuenta    = "cincuenta "   ;
final static String sesenta      = "sesenta "	   ;
final static String setenta      = "setenta "	   ;
final static String ochenta      = "ochenta "	   ;
final static String noventa      = "noventa "	   ;




	private static String num2Letras( int num )
	  {
		String S="";

		if ( num / 1000000 > 0 )  {
		  switch ( num / 1000000 ) {
			case 1: S = (un) + MILLON;
					break;
			case 2: S = dos + MILLONES;
					break;
			case 3: S = tres + MILLONES;
					break;
			case 4: S = cuatro + MILLONES;
					break;
			case 5: S = cinco + MILLONES;
					break;
			case 6: S = seis + MILLONES;
					break;
			case 7: S = siete + MILLONES;
					break;
			case 8: S = ocho + MILLONES;
					break;
			case 9: S = nueve + MILLONES;
		  }

		  num = num % 1000000;

		  if ( num == 0 )  {
			return S;
		  }

		}


		if ( num / 100000 > 0 )  {
		  if ( S == "" )
			S = num2Letras( num / 1000) + MIL + ",";
		  else
			S = S + "," + num2Letras(num / 1000) + MIL + ",";

			//String Aux( IntToStr(num) );
		  String Aux = (new Integer( num )).toString();

		  num = new Integer( Aux.substring( Aux.length()-3, Aux.length() )  ).intValue();
		  if ( num == 0 )  {
			return S;
		  }
		}


		if ( num / 1000 > 0 )
		  {
			if ( num / 1000 == 1 )
			  if ( S == "" )
				S = "MIL";
			  else
				S = S + ", MIL";
			else
			  if ( S == "" )
				S = num2Letras(num / 1000) + MIL + ",";
			  else
				S = S + "," + num2Letras(num / 1000) + MIL + ",";

			String Aux = new Integer(num).toString();
			num = new Integer( Aux.substring( Aux.length()-3, Aux.length() )  ).intValue();

			if ( num == 0 )
			  return S;
		  }

		if ( num / 100 > 0 )  {
		  switch ( num / 100) {
			case 1:
					if ( (num % 100) > 0 )
					  S = S + Ciento;
					else
					  S = S + cien;
					break;
			case 2: S = S + dos + Cientos;
					break;
			case 3: S = S + tres + Cientos;
					break;
			case 4: S = S + cuatro + Cientos;
					break;
			case 5: S = S + "quinientos ";
					break;
			case 6: S = S + seis + Cientos;
					break;
			case 7: S = S + sete + Cientos;
					break;
			case 8: S = S + ocho + Cientos;
					break;
			case 9: S = S + nove + Cientos;
		  }
		  num = num % 100;

		  if ( num == 0 )  {
			return S;
		  }

		}


		switch (num) {
		  case 1: S = S + un;
				  break;
		  case 2: S = S + dos;
				  break;
		  case 3: S = S + tres;
				  break;
		  case 4: S = S + cuatro;
				  break;
		  case 5: S = S + cinco;
				  break;
		  case 6: S = S + seis;
				  break;
		  case 7: S = S + siete;
				  break;
		  case 8: S = S + ocho;
				  break;
		  case 9: S = S + nueve;
				  break;
		  case 10: S = S + diez;
				   break;
		  case 11: S = S + once;
				   break;
		  case 12: S = S + doce;
				   break;
		  case 13: S = S + trece;
				   break;
		  case 14: S = S + catorce;
				   break;
		  case 15: S = S + quince;
				   break;
		  case 16: S = S + dieciseis;
				   break;
		  case 17: S = S + diecisiete;
				   break;
		  case 18: S = S + dieciocho;
				   break;
		  case 19: S = S + diecinueve;
				   break;
		  case 20: S = S + veinte;
				   break;
		  case 21: S = S + veinte + " y " + uno;
				   break;
		  case 22: S = S + veinte + " y " + dos;
				   break;
		  case 23: S = S + veinte + " y " + tres;
				   break;
		  case 24: S = S + veinte + " y " + cuatro;
				   break;
		  case 25: S = S + veinte + " y " + cinco;
				   break;
		  case 26: S = S + veinte + " y " + seis;
				   break;
		  case 27: S = S + veinte + " y " + siete;
				   break;
		  case 28: S = S + veinte + " y " + ocho;
				   break;
		  case 29: S = S + veinte + " y " + nueve;
				   break;
		  case 30: S = S + treinta;
				   break;
		  case 31: S = S + treinta + " y " + uno;
				   break;
		  case 32: S = S + treinta + " y " + dos;
				   break;
		  case 33: S = S + treinta + " y " + tres;
				   break;
		  case 34: S = S + treinta + " y " + cuatro;
				   break;
		  case 35: S = S + treinta + " y " + cinco;
				   break;
		  case 36: S = S + treinta + " y " + seis;
				   break;
		  case 37: S = S + treinta + " y " + siete;
				   break;
		  case 38: S = S + treinta + " y " + ocho;
				   break;
		  case 39: S = S + treinta + " y " + nueve;
				   break;
		  case 40: S = S + cuarenta;
				   break;
		  case 41: S = S + cuarenta + " y " + uno;
				   break;
		  case 42: S = S + cuarenta + " y " + dos;
				   break;
		  case 43: S = S + cuarenta + " y " + tres;
				   break;
		  case 44: S = S + cuarenta + " y " + cuatro;
				   break;
		  case 45: S = S + cuarenta + " y " + cinco;
				   break;
		  case 46: S = S + cuarenta + " y " + seis;
				   break;
		  case 47: S = S + cuarenta + " y " + siete;
				   break;
		  case 48: S = S + cuarenta + " y " + ocho;
				   break;
		  case 49: S = S + cuarenta + " y " + nueve;
				   break;
		  case 50: S = S + cincuenta;
				   break;
		  case 51: S = S + cincuenta + " y " + uno;
				   break;
		  case 52: S = S + cincuenta + " y " + dos;
				   break;
		  case 53: S = S + cincuenta + " y " + tres;
				   break;
		  case 54: S = S + cincuenta + " y " + cuatro;
				   break;
		  case 55: S = S + cincuenta + " y " + cinco;
				   break;
		  case 56: S = S + cincuenta + " y " + seis;
				   break;
		  case 57: S = S + cincuenta + " y " + siete;
				   break;
		  case 58: S = S + cincuenta + " y " + ocho;
				   break;
		  case 59: S = S + cincuenta + " y " + nueve;
				   break;
		  case 60: S = S + sesenta;
				   break;
		  case 61: S = S + sesenta + " y " + uno;
				   break;
		  case 62: S = S + sesenta + " y " + dos;
				   break;
		  case 63: S = S + sesenta + " y " + tres;
				   break;
		  case 64: S = S + sesenta + " y " + cuatro;
				   break;
		  case 65: S = S + sesenta + " y " + cinco;
				   break;
		  case 66: S = S + sesenta + " y " + seis;
				   break;
		  case 67: S = S + sesenta + " y " + siete;
				   break;
		  case 68: S = S + sesenta + " y " + ocho;
				   break;
		  case 69: S = S + sesenta + " y " + nueve;
				   break;
		  case 70: S = S + setenta;
				   break;
		  case 71: S = S + setenta + " y " + uno;
				   break;
		  case 72: S = S + setenta + " y " + dos;
				   break;
		  case 73: S = S + setenta + " y " + tres;
				   break;
		  case 74: S = S + setenta + " y " + cuatro;
				   break;
		  case 75: S = S + setenta + " y " + cinco;
				   break;
		  case 76: S = S + setenta + " y " + seis;
				   break;
		  case 77: S = S + setenta + " y " + siete;
				   break;
		  case 78: S = S + setenta + " y " + ocho;
				   break;
		  case 79: S = S + setenta + " y " + nueve;
				   break;
		  case 80: S = S + ochenta;
				   break;
		  case 81: S = S + ochenta + " y " + uno;
				   break;
		  case 82: S = S + ochenta + " y " + dos;
				   break;
		  case 83: S = S + ochenta + " y " + tres;
				   break;
		  case 84: S = S + ochenta + " y " + cuatro;
				   break;
		  case 85: S = S + ochenta + " y " + cinco;
				   break;
		  case 86: S = S + ochenta + " y " + seis;
				   break;
		  case 87: S = S + ochenta + " y " + siete;
				   break;
		  case 88: S = S + ochenta + " y " + ocho;
				   break;
		  case 89: S = S + ochenta + " y " + nueve;
				   break;
		  case 90: S = S + noventa;
				   break;
		  case 91: S = S + noventa + " y " + uno;
				   break;
		  case 92: S = S + noventa + " y " + dos;
				   break;
		  case 93: S = S + noventa + " y " + tres;
				   break;
		  case 94: S = S + noventa + " y " + cuatro;
				   break;
		  case 95: S = S + noventa + " y " + cinco;
				   break;
		  case 96: S = S + noventa + " y " + seis;
				   break;
		  case 97: S = S + noventa + " y " + siete;
				   break;
		  case 98: S = S + noventa + " y " + ocho;
				   break;
		  case 99: S = S + noventa + " y " + nueve;
		}

		return S;
	  }


	public static String toPesosYCentavos( double total, String moneda )
	  {
		String CentavosStr;
		//unsigned Centavos = ceil(100 * (total -  (unsigned)(total) ));
		//unsigned Centavos = floor(100 * (total -  (unsigned)(total) ));

		double d = Math.ceil(10000 * (total -  (int)(total) ));
		Double d1 = new Double( d );

		int Centavos = d1.intValue()/100;

		if ( Centavos == 0 )
		  CentavosStr = "00/100";
		else
		  CentavosStr = new Integer ( Centavos ).toString() + "/100";

        Double d2 = new Double( total );
        double d3 = Math.floor( d2.doubleValue() );
        int i = new Double( d3 ).intValue();

        String s = num2Letras( i ) + " " + moneda + " " + CentavosStr;
		return s.toUpperCase();
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

	public static void deleteAllParameters( HttpServletRequest req  )
	  {
		HttpSession ses = req.getSession();
		int maxParameter = 100;
		int i = 0;

        ses.removeAttribute("parameterCount");

        while ( i<maxParameter )
          {
			ses.removeAttribute("parameter" + i);
			ses.removeAttribute("parameterName" + i);
			ses.removeAttribute("parameterType" + i);
          }

	  }

	public static boolean isMayorCero(String str)
	{
		try
		{
			double i = new Double(str).doubleValue();
			if(i > 0)
			 return true;
			else
			 return false;
		}
		catch( Throwable  m )
		{
			return false;
		}
	}


	static boolean isValidEmailString(String str)
	  {
		String stringValidChars="zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP0123456789_-. ";
		String sprb;
		int prb;		
		if(!str.startsWith(".")&&!str.endsWith(".")&&!str.startsWith("-")&& str.length()>0)
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


	public static boolean isEmail(String str) {
		int posArroba= str.indexOf("@");
	//	int posPoint=str.indexOf(".",posArroba);
		int posEnd = str.length()-1;
		String user,domain;
		domain=str.substring(posArroba+1,posEnd+1);
		///////user=str.substring(0,posArroba);
		if (posArroba ==-1)
			return false;
		else
			user=str.substring(0,posArroba)	;
		
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



}
