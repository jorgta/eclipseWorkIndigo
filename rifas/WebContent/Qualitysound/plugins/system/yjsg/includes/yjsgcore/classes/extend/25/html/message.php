<?php
/**
 * @package     Joomla.Platform
 * @subpackage  Document
 *
 * @copyright   Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.
 * @license     GNU General Public License version 2 or later; see LICENSE
 */

defined('JPATH_PLATFORM') or die;

/**
 * JDocument system message renderer
 *
 * @package     Joomla.Platform
 * @subpackage  Document
 * @since       11.1
 */
class JDocumentRendererMessage extends JDocumentRenderer
{
	/**
	 * Renders the error stack and returns the results as a string
	 *
	 * @param   string  $name     Not used.
	 * @param   array   $params   Associative array of values
	 * @param   string  $content  Not used.
	 *
	 * @return  string  The output of the script
	 *
	 * @since   11.1
	 */
	public function render($name, $params = array (), $content = null)
	{

		// Initialise variables.
		$buffer = null;
		$lists = null;

		// Get the message queue
		$messages = JFactory::getApplication()->getMessageQueue();

		// Build the sorted message list
		if (is_array($messages) && !empty($messages))
		{
			foreach ($messages as $msg)
			{
				if (isset($msg['type']) && isset($msg['message']))
				{
					$lists[$msg['type']][] = $msg['message'];
				}
			}
		}

		// Build the return string
		$alert = array('error' => 'yjtb_red', 'warning' => 'yjtb_red', 'notice' => 'yjtb_yellow', 'message' => 'yjtb_green');
		$icon  = array('error' => 'fa fa-ban', 'warning' => 'fa fa-warning', 'notice' => 'fa fa-warning', 'message' => 'fa fa-thumbs-up');
		
		// If messages exist render them
		if (is_array($lists))
		{

			foreach ($lists as $type => $msgs)
			{
				$buffer .= '<div class="yjtbox ' . $alert[$type]. ' lineup">';
				$buffer .= '<span class="yjtboxicon ' . $icon[$type]. '"></span>';
				$buffer .= '<span class="yjtb_close"></span>';
				$buffer .= "\n<h4 class=\"yjtboxtitle\">" . JText::_($type) . "</h4>";
				if (count($msgs))
				{
					foreach ($msgs as $msg)
					{
						$buffer .= "\n\t\t" . $msg ;
					}
				}
				$buffer .= "\n</div>";
			}



		}

		return $buffer;
	}
}