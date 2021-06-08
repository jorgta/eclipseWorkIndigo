<?php
/**
 * @package		Youjoomla Extend Elements
 * @author		Youjoomla.com
 * @website     Youjoomla.com 
 * @copyright	Copyright (c) 2007 - 2011 Youjoomla.com.
 * @license   PHP files are GNU/GPL V2 Copyleft CSS / JS / IMAGES are Copyrighted Commercial
 */

// Check to ensure this file is within the rest of the framework
define( '_JEXEC', 1 );
header("Content-Type: application/json");
$jpath = dirname(dirname(dirname(dirname(__FILE__))));
define('JPATH_BASE',$jpath);

require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'defines.php' );
require_once ( JPATH_BASE .DIRECTORY_SEPARATOR.'includes'.DIRECTORY_SEPARATOR.'framework.php' );
jimport('joomla.filesystem.file');
jimport( 'joomla.filesystem.folder' );
jimport( 'joomla.methods' );
jimport( 'joomla.html.html.access' );
// get base vars
$mainframe 			=& JFactory::getApplication('administrator');
$lang 				=& JFactory::getLanguage();
$user 				=& JFactory::getUser();
$session		 	=& JFactory::getSession();
$default_lang 		= $lang->getDefault();
global $yj_mod_name;
$yj_mod_name 		= basename(dirname(dirname(__FILE__)));
$mainframe->initialise();


$lang->load(''.$yj_mod_name.'', JPATH_SITE);
$site_link			= str_replace('modules/'.basename(dirname(dirname(__FILE__))).'/elements/','',JURI::root());



$userGroups = $user->getAuthorisedGroups();
if ( in_array(8,$userGroups) ||  in_array(7,$userGroups) ||  in_array(6,$userGroups)){
	$isadmin = true;
}else{
	$isadmin = false;
}
// joomla is on :)
if($isadmin && isset($_GET['makethumb']))	{
// nothing goes pass this
  $strip_imgrefresh 	= preg_replace('/[?](.*)/','',$_GET['makethumb']); 
  $file_path 			= JPATH_ROOT.DIRECTORY_SEPARATOR.$strip_imgrefresh;
  $allow = array('jpg','jpeg','gif','png','JPG','JPEG','GIF','PNG');
  foreach ($allow as $key => $value ){
	  
	  
		  if( !strstr($strip_imgrefresh ,$value) && !JFile::exists($file_path)){
			 //you may pass echo 'ok';
			 
			  echo 'Restricted acsess';
			  die;
			  break;
		  }
	  
  }
	

	
	
	
	$image_path 		= $site_link.$strip_imgrefresh;
	$thumb_width 		= $_GET['get_width'];
	$thumb_height 		= $_GET['get_height'];
	
	
	$check_folder_path 	= JPATH_ROOT.DIRECTORY_SEPARATOR."images".DIRECTORY_SEPARATOR."upload_slides".DIRECTORY_SEPARATOR."thumbnails".DIRECTORY_SEPARATOR;

	function add_photos($file_path,$width_2,$height_2){
		
		require('yjsgjson.php');
		
		
		
		if (!extension_loaded('gd') && !function_exists('gd_info')) {
		  $response = array('error'=> 'GD library not setup or not installed');
		  $json = new JSON($response);
		  echo $json->result;
		  exit();
		}
		
		
		
		if($file_path != ""){
			//check the photo extension, only jpg,gif,png
			$image_info 		= pathinfo($file_path);
			$file_name 			= $image_info['basename'];
			$thumb_path 		= JPATH_ROOT.DIRECTORY_SEPARATOR."images".DIRECTORY_SEPARATOR."upload_slides".DIRECTORY_SEPARATOR."thumbnails".DIRECTORY_SEPARATOR;
			$image_extension 	= $image_info['extension'];
			
			
			//verify image type
			$allow_type_image = array('jpg','jpeg','gif','png','JPG','JPEG','GIF','PNG');
			
			
			if(!in_array(strtolower($image_info['extension']),$allow_type_image)){
				
				$response = array('error'=> 'Only jpg,jpeg,gif,png,JPG,JPEG,GIF,PNG files allowed');
				$json = new JSON($response);
				echo $json->result;
				exit();				
				
				
			}else{
				$img_size = @getimagesize($file_path);
				$width_old = $img_size[0];
				$height_old = $img_size[1];	

				$original_aspect = $width_old / $height_old;
				$thumb_aspect = $width_2 / $height_2;

				if ( $original_aspect >= $thumb_aspect )
				{
				   $new_height = $height_2;
				   $new_width = $width_old / ($height_old / $height_2);
				}
				else
				{
				   $new_width = $width_2;
				   $new_height = $height_old / ($width_old / $width_2);
				}				
					
				if((!isset($gdimg) || !$gdimg) && $image_extension =='jpg'){
					$gdimg = imagecreatefromjpeg($file_path);
					$ext = "jpg";
				}
				if((!isset($gdimg) || !$gdimg) && $image_extension =='gif'){
					$gdimg = imagecreatefromgif($file_path);
					$ext = "gif";
				}
				if((!isset($gdimg) || !$gdimg) && $image_extension =='png'){
					$gdimg = imagecreatefrompng($file_path);
					$ext = "png";
				}
				if(!isset($gdimg) || !$gdimg){
					  $response = array('error'=> 'Image create functions are not present.');
					  $json = new JSON($response);
					  echo $json->result;
					  exit();	
				}else{
					//create image regenerated
					if($ext!='gif'){
						$image_p = imagecreatetruecolor($width_2,$height_2);
						$trans = imagecolorallocate($image_p,0,0,0);
						imagecolortransparent($image_p,$trans);			
						imagecopyresampled($image_p, 
											$gdimg, 
											0 - ($new_width - $width_2) / 2,
											0 - ($new_height - $height_2) / 2,
											0, 
											0, 
											$new_width, 
											$new_height, 
											$width_old, 
											$height_old);
					}else{
						$image_p = imagecreate($width_2,$height_2);
						$trans = imagecolorallocate($image_p,0,0,0);
						imagecolortransparent($image_p,$trans);					
						imagecopyresized($image_p, 
											$gdimg, 
											0 - ($new_width - $width_2) / 2,
											0 - ($new_height - $height_2) / 2,
											0, 
											0, 
											$new_width, 
											$new_height, 
											$width_old, 
											$height_old);				
					}
					
					//put image on the folder with th_ in front of image name
					 $name = "thumb_".$file_name;
					 
					 //check if we already have a image with this name
					 if(file_exists($thumb_path.$name)){
						  $response = array('error_exists'=> $name);
						  $json = new JSON($response);
						  echo $json->result;
						  exit();
					 }else{
						 if($ext == "jpg") $upload_th = imagejpeg($image_p, $thumb_path.$name, 70);
						 if($ext == "gif") $upload_th = imagegif($image_p, $thumb_path.$name, 70);
						 if($ext == "png") $upload_th = imagepng($image_p, $thumb_path.$name, 7);
						 @chmod($thumb_path.$name, 0755);

						if($upload_th){
							  $response = array('message'=> $name);
							  $json = new JSON($response);
							  echo $json->result;
							  exit();							
						}else{
							  $response = array('error'=> 'Thumbnail '.$name.' canot be created');
							  $json = new JSON($response);
							  echo $json->result;
							  exit();			
						}
						
				
					}
					
				}
			}
		}
		
	}
			
	add_photos($file_path,$thumb_width,$thumb_height);

}elseif($isadmin && isset($_GET['check_thumb']))	{

  $check_refresh 		= preg_replace('/[?](.*)/','',$_GET['check_thumb']); 
  $file_path 			= JPATH_ROOT.DIRECTORY_SEPARATOR.$check_refresh;
  $check_folder_path 	= JPATH_ROOT.DIRECTORY_SEPARATOR."images".DIRECTORY_SEPARATOR."upload_slides".DIRECTORY_SEPARATOR."thumbnails".DIRECTORY_SEPARATOR;
  $allow = array('jpg','jpeg','gif','png','JPG','JPEG','GIF','PNG');
  foreach ($allow as $key => $value ){
	  
	  
		  if( !strstr($check_refresh,$value) && !JFile::exists($file_path)){
			 //you may not pass 
			 
			  echo 'Restricted acsess';
			  die;
			  break;
		  }
	  
  }
  
  
		require('yjsgjson.php');
		
		$check_file = $site_link.$check_refresh;
		$check_file = pathinfo($check_file);
		$check_thumb_file = $check_folder_path."thumb_".$check_file['basename'];
		if (JFile::exists($check_thumb_file)) {
		  $thumb_link = "thumb_".$check_file['basename'];	
		  $response = array('message'=> $thumb_link);
		  $json = new JSON($response);
		  echo $json->result;
		  exit();
		}else{
		  $response = array('error'=> 'no thumb');
		  $json = new JSON($response);
		  echo $json->result;
		  exit();
		}


}elseif($isadmin && isset($_GET['delete_thumb']))	{
	
	$thumb_info 	= pathinfo(JPATH_ROOT.DIRECTORY_SEPARATOR.$_GET['delete_thumb']);
	$strip_refresh 	= preg_replace('/[?](.*)/','',$thumb_info['basename']); 
	$thumb_loc 		= $thumb_info['dirname'].DIRECTORY_SEPARATOR.$strip_refresh;
	
  $allow = array('jpg','jpeg','gif','png','JPG','JPEG','GIF','PNG');
  foreach ($allow as $key => $value ){
	  
	  
		  if( !strstr($_GET['delete_thumb'],$value) && !JFile::exists($thumb_loc)){
			 //you may not pass 
			 
			  echo 'Restricted acsess';
			  die;
			  break;
		  }
	  
  }
	
	
	require('yjsgjson.php');
	
	if(JFile::exists($thumb_loc)){
		  
		  JFile::delete($thumb_loc);
		  $response = array('message'=> $strip_refresh.' has been deleted');
		  $json = new JSON($response);
		  echo $json->result;
		  exit();
	}else{
		  $response = array('error'=> 'Not able to delete '.$strip_refresh);
		  $json = new JSON($response);
		  echo $json->result;
		  exit();
	}
	
}else{
	echo 'Restricted acsess';
}