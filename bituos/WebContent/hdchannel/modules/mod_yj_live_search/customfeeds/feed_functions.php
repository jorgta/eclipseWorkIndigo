<?php
/*======================================================================*\
|| #################################################################### ||
|| # Package - YJ Live Search 2.0								        ||
|| # Copyright (C) 2010  Youjoomla.com. All Rights Reserved.            ||
|| # Authors - Dragan Todorovic and Constantin Boiangiu                 ||
|| # license - PHP files are licensed under  GNU/GPL V2                 ||
|| # license - CSS  - JS - IMAGE files  are Copyrighted material        ||
|| # bound by Proprietary License of Youjoomla.com                      ||
|| # for more information visit http://www.youjoomla.com/license.html   ||
|| # Redistribution and  modification of this software                  ||
|| # is bounded by its licenses                                         ||
|| # websites - http://www.youjoomla.com | http://www.yjsimplegrid.com  ||
|| #################################################################### ||
\*======================================================================*/
// no direct access
defined('_JEXEC') or die;
	if(!function_exists('yjls_k2_yjme_art_image'))
	{	
		function yjls_k2_yjme_art_image ($row)
		{	
	// awesome check! intro text has the priority. if image in introtext than that is what we will use
			if(preg_match_all("#\<img(.*)src\=\"(.*)\"#Ui", $row->descr, $images)):
				$kimg = $images[2][0];
				return $kimg;
			else:
				global $k2image_size;
				$isk_image = JFile::exists(JPATH_BASE.DIRECTORY_SEPARATOR.'media'.DIRECTORY_SEPARATOR.'k2'.DIRECTORY_SEPARATOR.'items'.DIRECTORY_SEPARATOR.'cache'.DIRECTORY_SEPARATOR.md5("Image".$row->id).'_'.$k2image_size .'.jpg');
				$kimg = JURI::root().'media/k2/items/cache/'.md5("Image".$row->id).'_'.$k2image_size .'.jpg';
				if( $isk_image ) return $kimg;
			endif;
		}
	}
	if(!function_exists('yjls_yjme_art_image'))
	{	 
		function yjls_yjme_art_image ($row)
		{
			
			$version = new JVersion;
			if($version->RELEASE > 1.7){	
				
				$img_from_params 	= json_decode($row->images);
				if(isset($img_from_params->image_intro)){
					$img_intro 			= $img_from_params->image_intro;
				}else{
					$img_intro='';
				}
				if(isset($img_from_params->image_fulltext)){
					$image_fulltext 	= $img_from_params->image_fulltext;
				}else{
					$image_fulltext='';
				}
				if(isset($img_from_params->image)){
					$image_fulltext 	= $img_from_params->image;
				}else{
					$image_fulltext='';
				}
				$img       			= yjls_yjme_search_image ( $row->descr );
				$img_full 			= yjls_yjme_search_image ( $row->descr_full );
				if( $img ) return $img;
				if( $img_full ) return $img_full;
				if( $img_intro ) return $img_intro;
				if( $image_fulltext ) return $image_fulltext;

			}else{
				
				$jimg = yjls_yjme_search_image ( $row->descr );
				if( $jimg ) return $jimg;
						
				$jimg = yjls_yjme_search_image ( $row->descr_full );
				return $jimg;			
				
			}
		}
	}
	if(!function_exists('yjme_search_image'))
	{	
		function yjls_yjme_search_image ( $text )
		{		
			preg_match_all("#\<img(.*)src\=\"(.*)\"#Ui", $text, $mathes);		
			return isset($mathes[2][0]) ? $mathes[2][0] : '';			
		}
	}
	
	class JSON
	{
		public $result;
		/**
		 * Class constructor
		 *
		 * @param array $array
		 */
		public function __construct( $array )
		{
			$this->json_encode($array);			
		}
		/**
		 * Json string builder. It calls itself if array value in key=>value pair is an array.
		 * It cand generate JSON strings from multidimensional array
		 *
		 * @param array $array
		 * @param string $separator
		 * @return void
		 */
		private function json_encode( $array = array(), $separator = '' )
		{
			$this->result .= '{';
			
			$pairs = array();
			foreach ($array as $key=>$value) 
			{
				if( is_array( $value ) )
				{
					$last_key = end( array_keys( $array ) );
					
					$this->result .= '"'.$key.'":';
					$this->json_encode( $value , ($key == $last_key ? '':',') );
				}
				else
					$pairs[] = '"'.$key.'":"'.$value.'"'; 
			}
			$this->result .= implode(',',$pairs).'}'.$separator;
		}
		
	}
?>