-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 06, 2014 at 01:00 PM
-- Server version: 5.1.36-community-log
-- PHP Version: 5.4.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hdchannelplugin`
--

-- --------------------------------------------------------

--
-- Table structure for table `#__assets`
--

DROP TABLE IF EXISTS `#__assets`;
CREATE TABLE IF NOT EXISTS `#__assets` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT 'Nested set parent.',
  `lft` int(11) NOT NULL DEFAULT '0' COMMENT 'Nested set lft.',
  `rgt` int(11) NOT NULL DEFAULT '0' COMMENT 'Nested set rgt.',
  `level` int(10) unsigned NOT NULL COMMENT 'The cached level in the nested tree.',
  `name` varchar(50) NOT NULL COMMENT 'The unique name for the asset.\n',
  `title` varchar(100) NOT NULL COMMENT 'The descriptive title for the asset.',
  `rules` varchar(5120) NOT NULL COMMENT 'JSON encoded access control.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_asset_name` (`name`),
  KEY `idx_lft_rgt` (`lft`,`rgt`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=48 ;

--
-- Dumping data for table `#__assets`
--

INSERT INTO `#__assets` (`id`, `parent_id`, `lft`, `rgt`, `level`, `name`, `title`, `rules`) VALUES
(1, 0, 1, 424, 0, 'root.1', 'Root Asset', '{"core.login.site":{"6":1,"2":1},"core.login.admin":{"6":1},"core.login.offline":{"6":1},"core.admin":{"8":1},"core.manage":{"7":1},"core.create":{"6":1,"3":1},"core.delete":{"6":1},"core.edit":{"6":1,"4":1},"core.edit.state":{"6":1,"5":1},"core.edit.own":{"6":1,"3":1}}'),
(2, 1, 1, 2, 1, 'com_admin', 'com_admin', '{}'),
(3, 1, 3, 6, 1, 'com_banners', 'com_banners', '{"core.admin":{"7":1},"core.manage":{"6":1},"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(4, 1, 7, 8, 1, 'com_cache', 'com_cache', '{"core.admin":{"7":1},"core.manage":{"7":1}}'),
(5, 1, 9, 10, 1, 'com_checkin', 'com_checkin', '{"core.admin":{"7":1},"core.manage":{"7":1}}'),
(6, 1, 11, 12, 1, 'com_config', 'com_config', '{}'),
(7, 1, 13, 16, 1, 'com_contact', 'com_contact', '{"core.admin":{"7":1},"core.manage":{"6":1},"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[],"core.edit.own":[]}'),
(8, 1, 17, 26, 1, 'com_content', 'com_content', '{"core.admin":{"7":1},"core.manage":{"6":1},"core.create":{"3":1},"core.delete":[],"core.edit":{"4":1},"core.edit.state":{"5":1},"core.edit.own":[]}'),
(9, 1, 27, 28, 1, 'com_cpanel', 'com_cpanel', '{}'),
(10, 1, 29, 30, 1, 'com_installer', 'com_installer', '{"core.admin":{"7":1},"core.manage":{"7":1},"core.delete":[],"core.edit.state":[]}'),
(11, 1, 31, 32, 1, 'com_languages', 'com_languages', '{"core.admin":{"7":1},"core.manage":[],"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(12, 1, 33, 34, 1, 'com_login', 'com_login', '{}'),
(13, 1, 35, 36, 1, 'com_mailto', 'com_mailto', '{}'),
(14, 1, 37, 38, 1, 'com_massmail', 'com_massmail', '{}'),
(15, 1, 39, 40, 1, 'com_media', 'com_media', '{"core.admin":{"7":1},"core.manage":{"6":1},"core.create":{"3":1},"core.delete":{"5":1}}'),
(16, 1, 41, 42, 1, 'com_menus', 'com_menus', '{"core.admin":{"7":1},"core.manage":[],"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(17, 1, 43, 44, 1, 'com_messages', 'com_messages', '{"core.admin":{"7":1},"core.manage":{"7":1}}'),
(18, 1, 45, 46, 1, 'com_modules', 'com_modules', '{"core.admin":{"7":1},"core.manage":[],"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(19, 1, 47, 50, 1, 'com_newsfeeds', 'com_newsfeeds', '{"core.admin":{"7":1},"core.manage":{"6":1},"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[],"core.edit.own":[]}'),
(20, 1, 51, 52, 1, 'com_plugins', 'com_plugins', '{"core.admin":{"7":1},"core.manage":[],"core.edit":[],"core.edit.state":[]}'),
(21, 1, 53, 54, 1, 'com_redirect', 'com_redirect', '{"core.admin":{"7":1},"core.manage":[]}'),
(22, 1, 55, 56, 1, 'com_search', 'com_search', '{"core.admin":{"7":1},"core.manage":{"6":1}}'),
(23, 1, 57, 58, 1, 'com_templates', 'com_templates', '{"core.admin":{"7":1},"core.manage":[],"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(24, 1, 59, 60, 1, 'com_users', 'com_users', '{"core.admin":{"7":1},"core.manage":[],"core.create":[],"core.delete":[],"core.edit":[],"core.edit.own":{"6":1},"core.edit.state":[]}'),
(25, 1, 61, 64, 1, 'com_weblinks', 'com_weblinks', '{"core.admin":{"7":1},"core.manage":{"6":1},"core.create":{"3":1},"core.delete":[],"core.edit":{"4":1},"core.edit.state":{"5":1},"core.edit.own":[]}'),
(26, 1, 65, 66, 1, 'com_wrapper', 'com_wrapper', '{}'),
(27, 8, 18, 21, 2, 'com_content.category.2', 'Latest', '{"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[],"core.edit.own":[]}'),
(28, 3, 4, 5, 2, 'com_banners.category.3', 'Uncategorised', '{"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(29, 7, 14, 15, 2, 'com_contact.category.4', 'Uncategorised', '{"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[],"core.edit.own":[]}'),
(30, 19, 48, 49, 2, 'com_newsfeeds.category.5', 'Uncategorised', '{"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[],"core.edit.own":[]}'),
(31, 25, 62, 63, 2, 'com_weblinks.category.6', 'Uncategorised', '{"core.create":[],"core.delete":[],"core.edit":[],"core.edit.state":[],"core.edit.own":[]}'),
(32, 27, 19, 20, 3, 'com_content.article.1', 'News Item1', '{"core.delete":[],"core.edit":[],"core.edit.state":[]}'),
(33, 1, 420, 421, 1, 'com_k2', 'com_k2', '{"core.admin":{"8":1},"core.manage":{"7":1},"core.create":{"6":1,"3":1},"core.delete":{"6":1},"core.edit":{"6":1,"4":1},"core.edit.state":{"6":1,"5":1},"core.edit.own":{"6":1,"3":1}}'),
(34, 1, 422, 423, 1, 'com_jce', 'jce', '{}'),
(35, 8, 22, 25, 2, 'com_content.category.7', 'Template Settings', '{"core.create":{"6":1,"3":1},"core.delete":{"6":1},"core.edit":{"6":1,"4":1},"core.edit.state":{"6":1,"5":1},"core.edit.own":{"6":1,"3":1}}'),
(39, 35, 23, 24, 3, 'com_content.article.12', 'Typography', '{"core.delete":{"6":1},"core.edit":{"6":1,"4":1},"core.edit.state":{"6":1,"5":1}}');

-- --------------------------------------------------------

--
-- Table structure for table `#__associations`
--

DROP TABLE IF EXISTS `#__associations`;
CREATE TABLE IF NOT EXISTS `#__associations` (
  `id` varchar(50) NOT NULL COMMENT 'A reference to the associated item.',
  `context` varchar(50) NOT NULL COMMENT 'The context of the associated item.',
  `key` char(32) NOT NULL COMMENT 'The key for the association computed from an md5 on associated ids.',
  PRIMARY KEY (`context`,`id`),
  KEY `idx_key` (`key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `#__banners`
--

DROP TABLE IF EXISTS `#__banners`;
CREATE TABLE IF NOT EXISTS `#__banners` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `imptotal` int(11) NOT NULL DEFAULT '0',
  `impmade` int(11) NOT NULL DEFAULT '0',
  `clicks` int(11) NOT NULL DEFAULT '0',
  `clickurl` varchar(200) NOT NULL DEFAULT '',
  `state` tinyint(3) NOT NULL DEFAULT '0',
  `catid` int(10) unsigned NOT NULL DEFAULT '0',
  `description` text NOT NULL,
  `custombannercode` varchar(2048) NOT NULL,
  `sticky` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `metakey` text NOT NULL,
  `params` text NOT NULL,
  `own_prefix` tinyint(1) NOT NULL DEFAULT '0',
  `metakey_prefix` varchar(255) NOT NULL DEFAULT '',
  `purchase_type` tinyint(4) NOT NULL DEFAULT '-1',
  `track_clicks` tinyint(4) NOT NULL DEFAULT '-1',
  `track_impressions` tinyint(4) NOT NULL DEFAULT '-1',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_up` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_down` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `reset` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `language` char(7) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_state` (`state`),
  KEY `idx_own_prefix` (`own_prefix`),
  KEY `idx_metakey_prefix` (`metakey_prefix`),
  KEY `idx_banner_catid` (`catid`),
  KEY `idx_language` (`language`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__banner_clients`
--

DROP TABLE IF EXISTS `#__banner_clients`;
CREATE TABLE IF NOT EXISTS `#__banner_clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `contact` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `extrainfo` text NOT NULL,
  `state` tinyint(3) NOT NULL DEFAULT '0',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `metakey` text NOT NULL,
  `own_prefix` tinyint(4) NOT NULL DEFAULT '0',
  `metakey_prefix` varchar(255) NOT NULL DEFAULT '',
  `purchase_type` tinyint(4) NOT NULL DEFAULT '-1',
  `track_clicks` tinyint(4) NOT NULL DEFAULT '-1',
  `track_impressions` tinyint(4) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`id`),
  KEY `idx_own_prefix` (`own_prefix`),
  KEY `idx_metakey_prefix` (`metakey_prefix`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__banner_tracks`
--

DROP TABLE IF EXISTS `#__banner_tracks`;
CREATE TABLE IF NOT EXISTS `#__banner_tracks` (
  `track_date` datetime NOT NULL,
  `track_type` int(10) unsigned NOT NULL,
  `banner_id` int(10) unsigned NOT NULL,
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`track_date`,`track_type`,`banner_id`),
  KEY `idx_track_date` (`track_date`),
  KEY `idx_track_type` (`track_type`),
  KEY `idx_banner_id` (`banner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `#__categories`
--

DROP TABLE IF EXISTS `#__categories`;
CREATE TABLE IF NOT EXISTS `#__categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'FK to the #__assets table.',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0',
  `lft` int(11) NOT NULL DEFAULT '0',
  `rgt` int(11) NOT NULL DEFAULT '0',
  `level` int(10) unsigned NOT NULL DEFAULT '0',
  `path` varchar(255) NOT NULL DEFAULT '',
  `extension` varchar(50) NOT NULL DEFAULT '',
  `title` varchar(255) NOT NULL,
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `note` varchar(255) NOT NULL DEFAULT '',
  `description` mediumtext,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `checked_out` int(11) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `access` int(10) unsigned DEFAULT NULL,
  `params` text NOT NULL,
  `metadesc` varchar(1024) NOT NULL COMMENT 'The meta description for the page.',
  `metakey` varchar(1024) NOT NULL COMMENT 'The meta keywords for the page.',
  `metadata` varchar(2048) NOT NULL COMMENT 'JSON encoded metadata properties.',
  `created_user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `modified_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `hits` int(10) unsigned NOT NULL DEFAULT '0',
  `language` char(7) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cat_idx` (`extension`,`published`,`access`),
  KEY `idx_access` (`access`),
  KEY `idx_checkout` (`checked_out`),
  KEY `idx_path` (`path`),
  KEY `idx_left_right` (`lft`,`rgt`),
  KEY `idx_alias` (`alias`),
  KEY `idx_language` (`language`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=89 ;

--
-- Dumping data for table `#__categories`
--

INSERT INTO `#__categories` (`id`, `asset_id`, `parent_id`, `lft`, `rgt`, `level`, `path`, `extension`, `title`, `alias`, `note`, `description`, `published`, `checked_out`, `checked_out_time`, `access`, `params`, `metadesc`, `metakey`, `metadata`, `created_user_id`, `created_time`, `modified_user_id`, `modified_time`, `hits`, `language`) VALUES
(1, 0, 0, 0, 13, 0, '', 'system', 'ROOT', 'root', '', '', 1, 0, '0000-00-00 00:00:00', 1, '{}', '', '', '', 0, '2009-10-18 16:07:09', 0, '0000-00-00 00:00:00', 0, '*'),
(2, 27, 1, 1, 2, 1, 'latest', 'com_content', 'Latest', 'latest', '', '<p>The latest news from the YouJoomla Team</p>', 1, 0, '0000-00-00 00:00:00', 1, '{"category_layout":"","image":"images\\/stories\\/taking_notes.jpg"}', '', '', '{"author":"","robots":""}', 42, '2010-06-28 13:26:37', 42, '2013-03-23 20:31:39', 0, '*'),
(3, 28, 1, 3, 4, 1, 'uncategorised', 'com_banners', 'Uncategorised', 'uncategorised', '', '', 1, 0, '0000-00-00 00:00:00', 1, '{"target":"","image":"","foobar":""}', '', '', '{"page_title":"","author":"","robots":""}', 42, '2010-06-28 13:27:35', 0, '0000-00-00 00:00:00', 0, '*'),
(5, 30, 1, 5, 6, 1, 'joomla-news-feeds', 'com_newsfeeds', 'Joomla News Feeds', 'joomla-news-feeds', '', '', 1, 0, '0000-00-00 00:00:00', 1, '{"category_layout":"","image":""}', '', '', '{"author":"","robots":""}', 42, '2010-06-28 13:28:15', 42, '2011-01-29 15:24:39', 0, '*'),
(6, 31, 1, 7, 8, 1, 'recomended-weblinks', 'com_weblinks', 'Recomended Weblinks', 'recomended-weblinks', '', '', 1, 0, '0000-00-00 00:00:00', 1, '{"category_layout":"","image":""}', '', '', '{"author":"","robots":""}', 42, '2010-06-28 13:28:33', 42, '2011-01-29 14:01:48', 0, '*'),
(7, 35, 1, 9, 10, 1, 'template-settings', 'com_content', 'Template Settings', 'template-settings', '', '', 1, 0, '0000-00-00 00:00:00', 1, '{"category_layout":"","image":""}', '', '', '{"author":"","robots":""}', 42, '2011-01-27 15:11:41', 42, '2013-03-23 20:31:44', 0, '*'),
(8, 43, 1, 11, 12, 1, 'site-contact', 'com_contact', 'Site contact', 'site-contact', '', '', 1, 0, '0000-00-00 00:00:00', 1, '{"category_layout":"","image":""}', '', '', '{"author":"","robots":""}', 42, '2011-01-29 14:30:28', 0, '0000-00-00 00:00:00', 0, '*');

-- --------------------------------------------------------

--
-- Table structure for table `#__contact_details`
--

DROP TABLE IF EXISTS `#__contact_details`;
CREATE TABLE IF NOT EXISTS `#__contact_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `con_position` varchar(255) DEFAULT NULL,
  `address` text,
  `suburb` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `postcode` varchar(100) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `misc` mediumtext,
  `image` varchar(255) DEFAULT NULL,
  `imagepos` varchar(20) DEFAULT NULL,
  `email_to` varchar(255) DEFAULT NULL,
  `default_con` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `params` text NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `catid` int(11) NOT NULL DEFAULT '0',
  `access` int(10) unsigned DEFAULT NULL,
  `mobile` varchar(255) NOT NULL DEFAULT '',
  `webpage` varchar(255) NOT NULL DEFAULT '',
  `sortname1` varchar(255) NOT NULL,
  `sortname2` varchar(255) NOT NULL,
  `sortname3` varchar(255) NOT NULL,
  `language` char(7) NOT NULL,
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` int(10) unsigned NOT NULL DEFAULT '0',
  `created_by_alias` varchar(255) NOT NULL DEFAULT '',
  `modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by` int(10) unsigned NOT NULL DEFAULT '0',
  `metakey` text NOT NULL,
  `metadesc` text NOT NULL,
  `metadata` text NOT NULL,
  `featured` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Set if article is featured.',
  `xreference` varchar(50) NOT NULL COMMENT 'A reference to enable linkages to external data sets.',
  `publish_up` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_down` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `idx_access` (`access`),
  KEY `idx_checkout` (`checked_out`),
  KEY `idx_state` (`published`),
  KEY `idx_catid` (`catid`),
  KEY `idx_createdby` (`created_by`),
  KEY `idx_featured_catid` (`featured`,`catid`),
  KEY `idx_language` (`language`),
  KEY `idx_xreference` (`xreference`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `#__contact_details`
--

INSERT INTO `#__contact_details` (`id`, `name`, `alias`, `con_position`, `address`, `suburb`, `state`, `country`, `postcode`, `telephone`, `fax`, `misc`, `image`, `imagepos`, `email_to`, `default_con`, `published`, `checked_out`, `checked_out_time`, `ordering`, `params`, `user_id`, `catid`, `access`, `mobile`, `webpage`, `sortname1`, `sortname2`, `sortname3`, `language`, `created`, `created_by`, `created_by_alias`, `modified`, `modified_by`, `metakey`, `metadesc`, `metadata`, `featured`, `xreference`, `publish_up`, `publish_down`) VALUES
(4, 'Publisher', 'publisher', 'Publisher', '', 'Miami', 'Florida', 'USA', '', '555555', '', '', 'images/powered_by.png', NULL, '', 0, 1, 0, '0000-00-00 00:00:00', 2, '{"show_contact_category":"","show_contact_list":"","presentation_style":"","show_name":"","show_position":"","show_email":"","show_street_address":"","show_suburb":"","show_state":"","show_postcode":"","show_country":"","show_telephone":"","show_mobile":"","show_fax":"","show_webpage":"","show_misc":"","show_image":"","allow_vcard":"","show_articles":"","show_profile":"","show_links":"","linka_name":"","linka":"","linkb_name":"","linkb":"","linkc_name":"","linkc":"","linkd_name":"","linkd":"","linke_name":"","linke":"","contact_layout":"","show_email_form":"","show_email_copy":"","banned_email":"","banned_subject":"","banned_text":"","validate_session":"","custom_reply":"","redirect":""}', 46, 8, 1, '', 'http://www.youjoomla.com', '', '', '', '*', '2011-01-29 14:31:28', 42, '', '2011-01-29 15:23:59', 42, '', '', '{"robots":"","rights":""}', 0, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3, 'Admin', 'admin', 'Administrator', '', 'Miami', 'Florida', 'USA', '', '555555', '', '', '', NULL, '', 0, 1, 0, '0000-00-00 00:00:00', 1, '{"show_contact_category":"hide","show_contact_list":"","presentation_style":"plain","show_name":"","show_position":"","show_email":"","show_street_address":"","show_suburb":"","show_state":"","show_postcode":"","show_country":"","show_telephone":"","show_mobile":"","show_fax":"","show_webpage":"","show_misc":"","show_image":"","allow_vcard":"","show_articles":"","show_profile":"","show_links":"","linka_name":"","linka":"","linkb_name":"","linkb":"","linkc_name":"","linkc":"","linkd_name":"","linkd":"","linke_name":"","linke":"","contact_layout":"","show_email_form":"","show_email_copy":"","banned_email":"","banned_subject":"","banned_text":"","validate_session":"","custom_reply":"","redirect":""}', 42, 8, 1, '', 'http://www.youjoomla.com', '', '', '', '*', '2011-01-29 14:30:52', 42, '', '2011-01-29 15:21:26', 42, '', '', '{"robots":"","rights":""}', 0, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `#__content`
--

DROP TABLE IF EXISTS `#__content`;
CREATE TABLE IF NOT EXISTS `#__content` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `asset_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'FK to the #__assets table.',
  `title` varchar(255) NOT NULL DEFAULT '',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `title_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Deprecated in Joomla! 3.0',
  `introtext` mediumtext NOT NULL,
  `fulltext` mediumtext NOT NULL,
  `state` tinyint(3) NOT NULL DEFAULT '0',
  `sectionid` int(10) unsigned NOT NULL DEFAULT '0',
  `mask` int(10) unsigned NOT NULL DEFAULT '0',
  `catid` int(10) unsigned NOT NULL DEFAULT '0',
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` int(10) unsigned NOT NULL DEFAULT '0',
  `created_by_alias` varchar(255) NOT NULL DEFAULT '',
  `modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_up` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_down` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `images` text NOT NULL,
  `urls` text NOT NULL,
  `attribs` varchar(5120) NOT NULL,
  `version` int(10) unsigned NOT NULL DEFAULT '1',
  `parentid` int(10) unsigned NOT NULL DEFAULT '0',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `metakey` text NOT NULL,
  `metadesc` text NOT NULL,
  `access` int(10) unsigned NOT NULL DEFAULT '0',
  `hits` int(10) unsigned NOT NULL DEFAULT '0',
  `metadata` text NOT NULL,
  `featured` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Set if article is featured.',
  `language` char(7) NOT NULL COMMENT 'The language code for the article.',
  `xreference` varchar(50) NOT NULL COMMENT 'A reference to enable linkages to external data sets.',
  PRIMARY KEY (`id`),
  KEY `idx_access` (`access`),
  KEY `idx_checkout` (`checked_out`),
  KEY `idx_state` (`state`),
  KEY `idx_catid` (`catid`),
  KEY `idx_createdby` (`created_by`),
  KEY `idx_featured_catid` (`featured`,`catid`),
  KEY `idx_language` (`language`),
  KEY `idx_xreference` (`xreference`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=109 ;

--
-- Dumping data for table `#__content`
--

INSERT INTO `#__content` (`id`, `asset_id`, `title`, `alias`, `title_alias`, `introtext`, `fulltext`, `state`, `sectionid`, `mask`, `catid`, `created`, `created_by`, `created_by_alias`, `modified`, `modified_by`, `checked_out`, `checked_out_time`, `publish_up`, `publish_down`, `images`, `urls`, `attribs`, `version`, `parentid`, `ordering`, `metakey`, `metadesc`, `access`, `hits`, `metadata`, `featured`, `language`, `xreference`) VALUES
(1, 32, 'News Item1', 'news-item1', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<p> </p>', 1, 0, 0, 2, '2011-01-27 14:53:20', 42, '', '2012-01-26 19:33:26', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:53:20', '0000-00-00 00:00:00', '{"image_intro":"","float_intro":"","image_intro_alt":"","image_intro_caption":"","image_fulltext":"","float_fulltext":"","image_fulltext_alt":"","image_fulltext_caption":""}', '{"urla":null,"urlatext":"","targeta":"","urlb":null,"urlbtext":"","targetb":"","urlc":null,"urlctext":"","targetc":""}', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","urls_position":"","alternative_readmore":"","article_layout":"","show_publishing_options":"","show_article_options":"","show_urls_images_backend":"","show_urls_images_frontend":""}', 6, 0, 1, '', '', 1, 142, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(2, 33, 'News Item2', 'news-item2', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 1, 0, 0, 2, '2011-01-27 14:53:39', 42, '', '2011-10-30 11:37:30', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:53:39', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 3, '', '', 1, 87, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(3, 34, 'News Item3', 'news-item3', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 1, 0, 0, 2, '2011-01-27 14:53:48', 42, '', '2011-10-30 11:37:30', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:53:48', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 4, '', '', 1, 72, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(4, 35, 'News Item4', 'news-item4', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 1, 0, 0, 2, '2011-01-27 14:53:57', 42, '', '2011-10-30 11:37:30', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:53:57', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 5, '', '', 1, 17, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(5, 36, 'News Item5', 'news-item5', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 1, 0, 0, 2, '2011-01-27 14:54:05', 42, '', '2011-10-30 11:37:30', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:54:05', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 6, '', '', 1, 4, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(6, 37, 'News Item6', 'news-item6', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 1, 0, 0, 2, '2011-01-27 14:54:14', 42, '', '2011-10-30 11:37:30', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:54:14', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 7, '', '', 1, 5, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(7, 38, 'News Item7', 'news-item7', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 1, 0, 0, 2, '2011-01-27 14:54:22', 42, '', '2011-10-30 11:37:30', 42, 0, '0000-00-00 00:00:00', '2011-01-27 14:54:22', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 8, '', '', 1, 8, '{"robots":"","author":"","rights":"","xreference":""}', 1, '*', ''),
(8, 40, 'Sample News item', 'sample-news-item', '', 'Praesent posuere tempus nibh bibendum aliquam. Integer ullamcorper vestibulum nisl vitae ullamcorper. Donec fermentum ligula at tortor tincidunt rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque blandit tellus vitae justo rutrum viverra. Proin semper sapien faucibus dolor ultrices a tempus leo porta. Integer sed lorem nisl, ac tincidunt diam. Sed ante felis, pellentesque in consectetur at, rutrum non turpis. Aenean id pellentesque lacus. Quisque ligula est, aliquam vitae lobortis ornare, posuere sit amet risus. Sed massa lacus, iaculis et venenatis nec, tempor mattis est. Sed placerat placerat volutpat. Integer ut orci metus. Etiam egestas, ante ac fermentum dapibus, arcu est semper odio, nec mattis metus elit eget nunc. Praesent posuere tempus nibh bibendum aliquam. Integer ullamcorper vestibulum nisl vitae ullamcorper. Donec fermentum ligula at tortor tincidunt rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque blandit tellus vitae justo rutrum viverra. Proin semper sapien faucibus dolor ultrices a tempus leo porta. Integer sed lorem nisl, ac tincidunt diam. Sed ante felis, pellentesque in consectetur at, rutrum non turpis. Aenean id pellentesque lacus. Quisque ligula est, aliquam vitae lobortis ornare, posuere sit amet risus. Sed massa lacus, iaculis et venenatis nec, tempor mattis est. Sed placerat placerat volutpat. Integer ut orci metus. Etiam egestas, ante ac fermentum.<br/><br/>', '', 1, 0, 0, 7, '2011-01-27 15:12:11', 42, '', '2013-02-26 20:04:13', 42, 0, '0000-00-00 00:00:00', '2011-01-27 15:12:11', '0000-00-00 00:00:00', '{"image_intro":"","float_intro":"","image_intro_alt":"","image_intro_caption":"","image_fulltext":"","float_fulltext":"","image_fulltext_alt":"","image_fulltext_caption":""}', '{"urla":null,"urlatext":"","targeta":"","urlb":null,"urlbtext":"","targetb":"","urlc":null,"urlctext":"","targetc":""}', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"0","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","urls_position":"","alternative_readmore":"","article_layout":"","show_publishing_options":"","show_article_options":"","show_urls_images_backend":"","show_urls_images_frontend":""}', 11, 0, 5, '', '', 1, 624, '{"robots":"","author":"","rights":"","xreference":""}', 0, '*', ''),
(11, 44, 'Archived Article', 'archived-article', '', '<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n', '\r\n<p>Vivamus porta eros et neque vulputate pretium. Viva mus vel velit metus, scelerisque molestie augue. Aliquam semper hendrerit velit, at convallis augue pel lentesque vel.Donec sed accumsan sem. Nunc feugiat elit eu nisi laci nia consequat. Mauris sed erat sit amet nibh aliquet Duis nisi sapien, feugiat tem</p>\r\n<br />', 2, 0, 0, 2, '2011-01-29 15:42:27', 42, '', '0000-00-00 00:00:00', 0, 0, '0000-00-00 00:00:00', '2011-01-29 15:42:27', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 1, 0, 2, '', '', 1, 2, '{"robots":"","author":"","rights":"","xreference":""}', 0, '*', ''),
(12, 39, 'Typography', 'typography', '', '[yjsgparse url="https://raw.githubusercontent.com/YJSGframework/demo-docs/master/add-ons/Typography.html" days="" hours=""]\r\n<h3>Custom button</h3>\r\n<a class="template_readmore">Your text</a>\r\n<h3 class="yjsg-sub-heading">markup:</h3>\r\n[yjsgpre pretty="1" scroll="0"]\r\n<a class="template_readmore">Your text</a>\r\n[/yjsgpre]\r\n\r\n<h3>Custom button 2</h3>\r\n<a class="template_readmore2">Your text</a>\r\n<h3 class="yjsg-sub-heading">markup:</h3>\r\n[yjsgpre pretty="1" scroll="0"]\r\n<a class="template_readmore2">Your text</a>\r\n[/yjsgpre]', '', 1, 0, 0, 7, '2011-01-29 15:50:35', 42, '', '2014-08-06 09:49:11', 42, 0, '0000-00-00 00:00:00', '2011-01-29 15:50:35', '0000-00-00 00:00:00', '{"image_intro":"","float_intro":"","image_intro_alt":"","image_intro_caption":"","image_fulltext":"","float_fulltext":"","image_fulltext_alt":"","image_fulltext_caption":""}', '{"urla":false,"urlatext":"","targeta":"","urlb":false,"urlbtext":"","targetb":"","urlc":false,"urlctext":"","targetc":""}', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","urls_position":"","alternative_readmore":"","article_layout":"","show_publishing_options":"","show_article_options":"","show_urls_images_backend":"","show_urls_images_frontend":"","yjsg_microdata_enabeled":"0","yjsg_microdata_position":"0","yjsg_microdata_ratingtype":"none","yjsg_article_microdata":"Article","yjsg_md_video_link":"","yjsg_md_video_thumb":"","yjsg_md_video_duration":"","yjsg_md_event_start":"","yjsg_md_event_end":"","yjsg_md_event_streetadress":"","yjsg_md_event_adresslocality":"","yjsg_md_event_adressregion":"","yjsg_md_event_postalcode":"","yjsg_md_event_price":"","yjsg_md_event_tickets":"","yjsg_md_product_price":"","yjsg_md_product_availability":"na","yjsg_md_recipe_preptime":"","yjsg_md_recipe_cooktime":"","yjsg_md_recipe_totaltime":"","yjsg_md_recipe_ingredients":"","yjsg_md_movie_directors":"","yjsg_md_movie_writters":"","yjsg_md_movie_stars":"","yjsg_md_book_price":"","yjsg_md_book_availability":"na","yjsg_md_book_pages":"","yjsg_md_book_author":"","yjsg_md_book_publisher":"","yjsg_md_book_language":"","yjsg_module_positons":"0"}', 13, 0, 2, '', '', 1, 234, '{"robots":"","author":"","rights":"","xreference":""}', 0, '*', ''),
(13, 46, 'YJSG Module Positions', 'yjsg-module-positions', '', '<p>YJSimpleGrid Framework comes with 51 build in module positions . <em><strong><a title="Adding new modules grid to YJSG Framework" href="http://www.joomla1.5.youjoomla.info/yougrids/yjsg-framework/adding-new-grid.html" target="_blank">Adding new module</a></strong></em> grids is a very simple process and within few seconds you can adjust the layout to your own liking. Each&nbsp; module positon is completely collapsible either by disabling module in module manager or by setting module size to 0 in template manager. Mainbody grid is completely flexible and can swithc sides by simply adjusting default mainbody layout in template manager. Logo width and height can also be adjusted in template manager or completely disabled.</p>\r\n\r\n<p><img style="display: block; margin-left: auto; margin-right: auto;" alt="yjsg_modulepoz" src="images/stories/yjsg_modulepoz.gif" height="1010" width="800" /></p>\r\n', '', 1, 0, 0, 7, '2011-01-29 16:12:44', 42, '', '2011-02-04 15:01:48', 42, 0, '0000-00-00 00:00:00', '2011-01-29 16:12:44', '0000-00-00 00:00:00', '', '', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","alternative_readmore":"","article_layout":""}', 3, 0, 1, '', '', 1, 19, '{"robots":"","author":"","rights":"","xreference":""}', 0, '*', ''),
(15, 49, 'Module Styles', 'module-styles', '', '<div>\r\n<div>\r\n<div>\r\n<div>\r\n<div>\r\n<p>Following modules styles can be used In this<strong> Joomla! Template</strong></p>\r\n<ul>\r\n<li>Default module style . Leave Module Class  Suffix input box empty  (<strong>Sample Default</strong>module )</li>\r\n<li><strong>Add " yj1"</strong> in Module setting  Module Class Suffix input box  (<strong>Sample yj1</strong> module )</li>\r\n<li><strong>Add " yj2"</strong> in Module setting  Module Class Suffix input box  (<strong>Sample yj2</strong> module )</li>\r\n</ul>\r\n</div>\r\n</div>\r\n</div>\r\n</div>\r\n</div>', '', 1, 0, 0, 7, '2011-10-29 22:32:05', 42, '', '2013-03-12 17:40:13', 42, 0, '0000-00-00 00:00:00', '2011-10-29 22:32:05', '0000-00-00 00:00:00', '{"image_intro":"","float_intro":"","image_intro_alt":"","image_intro_caption":"","image_fulltext":"","float_fulltext":"","image_fulltext_alt":"","image_fulltext_caption":""}', '{"urla":null,"urlatext":"","targeta":"","urlb":null,"urlbtext":"","targetb":"","urlc":null,"urlctext":"","targetc":""}', '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_vote":"","show_hits":"","show_noauth":"","urls_position":"","alternative_readmore":"","article_layout":"","show_publishing_options":"","show_article_options":"","show_urls_images_backend":"","show_urls_images_frontend":""}', 2, 0, 0, '', '', 1, 85, '{"robots":"","author":"","rights":"","xreference":""}', 0, '*', '');

-- --------------------------------------------------------

--
-- Table structure for table `#__content_frontpage`
--

DROP TABLE IF EXISTS `#__content_frontpage`;
CREATE TABLE IF NOT EXISTS `#__content_frontpage` (
  `content_id` int(11) NOT NULL DEFAULT '0',
  `ordering` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`content_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `#__content_frontpage`
--

INSERT INTO `#__content_frontpage` (`content_id`, `ordering`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7);

-- --------------------------------------------------------

--
-- Table structure for table `#__content_rating`
--

DROP TABLE IF EXISTS `#__content_rating`;
CREATE TABLE IF NOT EXISTS `#__content_rating` (
  `content_id` int(11) NOT NULL DEFAULT '0',
  `rating_sum` int(10) unsigned NOT NULL DEFAULT '0',
  `rating_count` int(10) unsigned NOT NULL DEFAULT '0',
  `lastip` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`content_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `#__core_log_searches`
--

DROP TABLE IF EXISTS `#__core_log_searches`;
CREATE TABLE IF NOT EXISTS `#__core_log_searches` (
  `search_term` varchar(128) NOT NULL DEFAULT '',
  `hits` int(10) unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `#__extensions`
--

DROP TABLE IF EXISTS `#__extensions`;
CREATE TABLE IF NOT EXISTS `#__extensions` (
  `extension_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `element` varchar(100) NOT NULL,
  `folder` varchar(100) NOT NULL,
  `client_id` tinyint(3) NOT NULL,
  `enabled` tinyint(3) NOT NULL DEFAULT '1',
  `access` int(10) unsigned DEFAULT NULL,
  `protected` tinyint(3) NOT NULL DEFAULT '0',
  `manifest_cache` text NOT NULL,
  `params` text NOT NULL,
  `custom_data` text NOT NULL,
  `system_data` text NOT NULL,
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ordering` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`extension_id`),
  KEY `element_clientid` (`element`,`client_id`),
  KEY `element_folder_clientid` (`element`,`folder`,`client_id`),
  KEY `extension` (`type`,`element`,`folder`,`client_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10041 ;

--
-- Dumping data for table `#__extensions`
--

INSERT INTO `#__extensions` (`extension_id`, `name`, `type`, `element`, `folder`, `client_id`, `enabled`, `access`, `protected`, `manifest_cache`, `params`, `custom_data`, `system_data`, `checked_out`, `checked_out_time`, `ordering`, `state`) VALUES
(1, 'com_mailto', 'component', 'com_mailto', '', 0, 1, 1, 1, '{"legacy":false,"name":"com_mailto","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_MAILTO_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(2, 'com_wrapper', 'component', 'com_wrapper', '', 0, 1, 1, 1, '{"legacy":false,"name":"com_wrapper","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_WRAPPER_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(3, 'com_admin', 'component', 'com_admin', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_admin","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_ADMIN_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(4, 'com_banners', 'component', 'com_banners', '', 1, 1, 1, 0, '{"legacy":false,"name":"com_banners","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_BANNERS_XML_DESCRIPTION","group":""}', '{"purchase_type":"3","track_impressions":"0","track_clicks":"0","metakey_prefix":""}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(5, 'com_cache', 'component', 'com_cache', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_cache","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CACHE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(6, 'com_categories', 'component', 'com_categories', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_categories","type":"component","creationDate":"December 2007","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CATEGORIES_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(7, 'com_checkin', 'component', 'com_checkin', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_checkin","type":"component","creationDate":"Unknown","author":"Joomla! Project","copyright":"(C) 2005 - 2008 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CHECKIN_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(8, 'com_contact', 'component', 'com_contact', '', 1, 1, 1, 0, '{"legacy":false,"name":"com_contact","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CONTACT_XML_DESCRIPTION","group":""}', '{"show_contact_category":"hide","show_contact_list":"0","presentation_style":"sliders","show_name":"1","show_position":"1","show_email":"0","show_street_address":"1","show_suburb":"1","show_state":"1","show_postcode":"1","show_country":"1","show_telephone":"1","show_mobile":"1","show_fax":"1","show_webpage":"1","show_misc":"1","show_image":"1","image":"","allow_vcard":"0","show_articles":"0","show_profile":"0","show_links":"0","linka_name":"","linkb_name":"","linkc_name":"","linkd_name":"","linke_name":"","contact_icons":"0","icon_address":"","icon_email":"","icon_telephone":"","icon_mobile":"","icon_fax":"","icon_misc":"","show_headings":"1","show_position_headings":"1","show_email_headings":"0","show_telephone_headings":"1","show_mobile_headings":"0","show_fax_headings":"0","allow_vcard_headings":"0","show_suburb_headings":"1","show_state_headings":"1","show_country_headings":"1","show_email_form":"1","show_email_copy":"1","banned_email":"","banned_subject":"","banned_text":"","validate_session":"1","custom_reply":"0","redirect":"","show_category_crumb":"0","metakey":"","metadesc":"","robots":"","author":"","rights":"","xreference":""}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(9, 'com_cpanel', 'component', 'com_cpanel', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_cpanel","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CPANEL_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10, 'com_installer', 'component', 'com_installer', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_installer","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_INSTALLER_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(11, 'com_languages', 'component', 'com_languages', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_languages","type":"component","creationDate":"2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_LANGUAGES_XML_DESCRIPTION","group":""}', '{"administrator":"en-GB","site":"en-GB"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(12, 'com_login', 'component', 'com_login', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_login","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_LOGIN_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(13, 'com_media', 'component', 'com_media', '', 1, 1, 0, 1, '{"legacy":false,"name":"com_media","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_MEDIA_XML_DESCRIPTION","group":""}', '{"upload_extensions":"bmp,csv,doc,gif,ico,jpg,jpeg,odg,odp,ods,odt,pdf,png,ppt,swf,txt,xcf,xls,BMP,CSV,DOC,GIF,ICO,JPG,JPEG,ODG,ODP,ODS,ODT,PDF,PNG,PPT,SWF,TXT,XCF,XLS","upload_maxsize":"10","file_path":"images","image_path":"images","restrict_uploads":"1","allowed_media_usergroup":"3","check_mime":"1","image_extensions":"bmp,gif,jpg,png","ignore_extensions":"","upload_mime":"image\\/jpeg,image\\/gif,image\\/png,image\\/bmp,application\\/x-shockwave-flash,application\\/msword,application\\/excel,application\\/pdf,application\\/powerpoint,text\\/plain,application\\/x-zip","upload_mime_illegal":"text\\/html","enable_flash":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(14, 'com_menus', 'component', 'com_menus', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_menus","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_MENUS_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(15, 'com_messages', 'component', 'com_messages', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_messages","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_MESSAGES_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(16, 'com_modules', 'component', 'com_modules', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_modules","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_MODULES_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(17, 'com_newsfeeds', 'component', 'com_newsfeeds', '', 1, 1, 1, 0, '{"legacy":false,"name":"com_newsfeeds","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_NEWSFEEDS_XML_DESCRIPTION","group":""}', '{"show_feed_image":"1","show_feed_description":"1","show_item_description":"1","feed_word_count":"0","show_headings":"1","show_name":"1","show_articles":"0","show_link":"1","show_description":"1","show_description_image":"1","display_num":"","show_pagination_limit":"1","show_pagination":"1","show_pagination_results":"1","show_cat_items":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(18, 'com_plugins', 'component', 'com_plugins', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_plugins","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_PLUGINS_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(19, 'com_search', 'component', 'com_search', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_search","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_SEARCH_XML_DESCRIPTION","group":""}', '{"enabled":"0","show_date":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(20, 'com_templates', 'component', 'com_templates', '', 1, 1, 1, 1, '{"legacy":false,"name":"com_templates","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_TEMPLATES_XML_DESCRIPTION","group":""}', '{"template_positions_display":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(21, 'com_weblinks', 'component', 'com_weblinks', '', 1, 1, 1, 0, '{"legacy":false,"name":"com_weblinks","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\n\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_WEBLINKS_XML_DESCRIPTION","group":""}', '{"show_comp_description":"1","comp_description":"","show_link_hits":"1","show_link_description":"1","show_other_cats":"0","show_headings":"0","show_numbers":"0","show_report":"1","count_clicks":"1","target":"0","link_icons":""}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(22, 'com_content', 'component', 'com_content', '', 1, 1, 0, 1, '{"legacy":false,"name":"com_content","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CONTENT_XML_DESCRIPTION","group":""}', '{"article_layout":"_:default","show_title":"1","link_titles":"1","show_intro":"1","show_category":"1","link_category":"1","show_parent_category":"0","link_parent_category":"0","show_author":"1","link_author":"0","show_create_date":"0","show_modify_date":"0","show_publish_date":"1","show_item_navigation":"1","show_vote":"0","show_readmore":"1","show_readmore_title":"1","readmore_limit":"100","show_icons":"1","show_print_icon":"1","show_email_icon":"1","show_hits":"1","show_noauth":"0","urls_position":"0","show_publishing_options":"1","show_article_options":"1","show_urls_images_frontend":"1","show_urls_images_backend":"1","targeta":0,"targetb":0,"targetc":0,"float_intro":"right","float_fulltext":"right","category_layout":"_:blog","show_category_title":"0","show_description":"0","show_description_image":"0","maxLevel":"1","show_empty_categories":"0","show_no_articles":"1","show_subcat_desc":"1","show_cat_num_articles":"0","show_base_description":"1","maxLevelcat":"-1","show_empty_categories_cat":"0","show_subcat_desc_cat":"1","show_cat_num_articles_cat":"1","num_leading_articles":"1","num_intro_articles":"4","num_columns":"2","num_links":"4","multi_column_order":"0","show_subcategory_content":"0","show_pagination_limit":"1","filter_field":"hide","show_headings":"1","list_show_date":"0","date_format":"","list_show_hits":"1","list_show_author":"1","orderby_pri":"order","orderby_sec":"rdate","order_date":"published","show_pagination":"2","show_pagination_results":"1","show_feed_link":"1","feed_summary":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(23, 'com_config', 'component', 'com_config', '', 1, 1, 0, 1, '{"legacy":false,"name":"com_config","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_CONFIG_XML_DESCRIPTION","group":""}', '{"filters":{"1":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"6":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"7":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"2":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"3":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"4":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"5":{"filter_type":"BL","filter_tags":"","filter_attributes":""},"8":{"filter_type":"NONE","filter_tags":"","filter_attributes":""}}}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(24, 'com_redirect', 'component', 'com_redirect', '', 1, 1, 0, 1, '{"legacy":false,"name":"com_redirect","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_REDIRECT_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(25, 'com_users', 'component', 'com_users', '', 1, 1, 0, 1, '{"legacy":false,"name":"com_users","type":"component","creationDate":"April 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.\\t","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"COM_USERS_XML_DESCRIPTION","group":""}', '{"allowUserRegistration":"1","new_usertype":"2","useractivation":"1","frontend_userparams":"1","mailSubjectPrefix":"","mailBodySuffix":""}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(100, 'PHPMailer', 'library', 'phpmailer', '', 0, 1, 1, 1, '{"legacy":false,"name":"PHPMailer","type":"library","creationDate":"2001","author":"PHPMailer","copyright":"(c) 2001-2003, Brent R. Matzelle, (c) 2004-2009, Andy Prevost. All Rights Reserved., (c) 2010-2011, Jim Jagielski. All Rights Reserved.","authorEmail":"jimjag@gmail.com","authorUrl":"https:\\/\\/code.google.com\\/a\\/apache-extras.org\\/p\\/phpmailer\\/","version":"5.2","description":"LIB_PHPMAILER_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(101, 'SimplePie', 'library', 'simplepie', '', 0, 1, 1, 1, '{"legacy":false,"name":"SimplePie","type":"library","creationDate":"2004","author":"SimplePie","copyright":"Copyright (c) 2004-2009, Ryan Parman and Geoffrey Sneddon","authorEmail":"","authorUrl":"http:\\/\\/simplepie.org\\/","version":"1.2","description":"LIB_SIMPLEPIE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(102, 'phputf8', 'library', 'phputf8', '', 0, 1, 1, 1, '{"legacy":false,"name":"phputf8","type":"library","creationDate":"2006","author":"Harry Fuecks","copyright":"Copyright various authors","authorEmail":"hfuecks@gmail.com","authorUrl":"http:\\/\\/sourceforge.net\\/projects\\/phputf8","version":"0.5","description":"LIB_PHPUTF8_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(103, 'Joomla! Platform', 'library', 'joomla', '', 0, 1, 1, 0, '{"legacy":false,"name":"Joomla! Platform","type":"library","creationDate":"2008","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"http:\\/\\/www.joomla.org","version":"11.4","description":"LIB_JOOMLA_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(200, 'mod_articles_archive', 'module', 'mod_articles_archive', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_articles_archive","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters.\\n\\t\\tAll rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_ARTICLES_ARCHIVE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(201, 'mod_articles_latest', 'module', 'mod_articles_latest', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_articles_latest","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_LATEST_NEWS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(202, 'mod_articles_popular', 'module', 'mod_articles_popular', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_articles_popular","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_POPULAR_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(203, 'mod_banners', 'module', 'mod_banners', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_banners","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_BANNERS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(204, 'mod_breadcrumbs', 'module', 'mod_breadcrumbs', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_breadcrumbs","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_BREADCRUMBS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(205, 'mod_custom', 'module', 'mod_custom', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_custom","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_CUSTOM_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(206, 'mod_feed', 'module', 'mod_feed', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_feed","type":"module","creationDate":"July 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_FEED_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(207, 'mod_footer', 'module', 'mod_footer', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_footer","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_FOOTER_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(208, 'mod_login', 'module', 'mod_login', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_login","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_LOGIN_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(209, 'mod_menu', 'module', 'mod_menu', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_menu","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_MENU_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(210, 'mod_articles_news', 'module', 'mod_articles_news', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_articles_news","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_ARTICLES_NEWS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(211, 'mod_random_image', 'module', 'mod_random_image', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_random_image","type":"module","creationDate":"July 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_RANDOM_IMAGE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(212, 'mod_related_items', 'module', 'mod_related_items', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_related_items","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_RELATED_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(213, 'mod_search', 'module', 'mod_search', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_search","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_SEARCH_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(214, 'mod_stats', 'module', 'mod_stats', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_stats","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_STATS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(215, 'mod_syndicate', 'module', 'mod_syndicate', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_syndicate","type":"module","creationDate":"May 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_SYNDICATE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(216, 'mod_users_latest', 'module', 'mod_users_latest', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_users_latest","type":"module","creationDate":"December 2009","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_USERS_LATEST_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(217, 'mod_weblinks', 'module', 'mod_weblinks', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_weblinks","type":"module","creationDate":"July 2009","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_WEBLINKS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(218, 'mod_whosonline', 'module', 'mod_whosonline', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_whosonline","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_WHOSONLINE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(219, 'mod_wrapper', 'module', 'mod_wrapper', '', 0, 1, 1, 0, '{"legacy":false,"name":"mod_wrapper","type":"module","creationDate":"October 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_WRAPPER_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(220, 'mod_articles_category', 'module', 'mod_articles_category', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_articles_category","type":"module","creationDate":"February 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_ARTICLES_CATEGORY_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(221, 'mod_articles_categories', 'module', 'mod_articles_categories', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_articles_categories","type":"module","creationDate":"February 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_ARTICLES_CATEGORIES_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(222, 'mod_languages', 'module', 'mod_languages', '', 0, 1, 1, 1, '{"legacy":false,"name":"mod_languages","type":"module","creationDate":"February 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_LANGUAGES_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(300, 'mod_custom', 'module', 'mod_custom', '', 1, 1, 1, 1, '{"legacy":false,"name":"mod_custom","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_CUSTOM_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(301, 'mod_feed', 'module', 'mod_feed', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_feed","type":"module","creationDate":"July 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_FEED_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(302, 'mod_latest', 'module', 'mod_latest', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_latest","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_LATEST_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(303, 'mod_logged', 'module', 'mod_logged', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_logged","type":"module","creationDate":"January 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_LOGGED_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(304, 'mod_login', 'module', 'mod_login', '', 1, 1, 1, 1, '{"legacy":false,"name":"mod_login","type":"module","creationDate":"March 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_LOGIN_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(305, 'mod_menu', 'module', 'mod_menu', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_menu","type":"module","creationDate":"March 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_MENU_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(306, 'mod_online', 'module', 'mod_online', '', 1, 1, 1, 0, '', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(307, 'mod_popular', 'module', 'mod_popular', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_popular","type":"module","creationDate":"July 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_POPULAR_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(308, 'mod_quickicon', 'module', 'mod_quickicon', '', 1, 1, 1, 1, '{"legacy":false,"name":"mod_quickicon","type":"module","creationDate":"Nov 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_QUICKICON_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(309, 'mod_status', 'module', 'mod_status', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_status","type":"module","creationDate":"Feb 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_STATUS_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(310, 'mod_submenu', 'module', 'mod_submenu', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_submenu","type":"module","creationDate":"Feb 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_SUBMENU_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(311, 'mod_title', 'module', 'mod_title', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_title","type":"module","creationDate":"Nov 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_TITLE_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(312, 'mod_toolbar', 'module', 'mod_toolbar', '', 1, 1, 1, 1, '{"legacy":false,"name":"mod_toolbar","type":"module","creationDate":"Nov 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_TOOLBAR_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(313, 'mod_multilangstatus', 'module', 'mod_multilangstatus', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_multilangstatus","type":"module","creationDate":"September 2011","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_MULTILANGSTATUS_XML_DESCRIPTION","group":""}', '{"cache":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(400, 'plg_authentication_gmail', 'plugin', 'gmail', 'authentication', 0, 0, 1, 0, '{"legacy":false,"name":"plg_authentication_gmail","type":"plugin","creationDate":"February 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_GMAIL_XML_DESCRIPTION","group":""}', '{"applysuffix":"0","suffix":"","verifypeer":"1","user_blacklist":""}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(401, 'plg_authentication_joomla', 'plugin', 'joomla', 'authentication', 0, 1, 1, 1, '{"legacy":false,"name":"plg_authentication_joomla","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_AUTH_JOOMLA_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(402, 'plg_authentication_ldap', 'plugin', 'ldap', 'authentication', 0, 0, 1, 0, '{"legacy":false,"name":"plg_authentication_ldap","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_LDAP_XML_DESCRIPTION","group":""}', '{"host":"","port":"389","use_ldapV3":"0","negotiate_tls":"0","no_referrals":"0","auth_method":"bind","base_dn":"","search_string":"","users_dn":"","username":"admin","password":"bobby7","ldap_fullname":"fullName","ldap_email":"mail","ldap_uid":"uid"}', '', '', 0, '0000-00-00 00:00:00', 3, 0),
(404, 'plg_content_emailcloak', 'plugin', 'emailcloak', 'content', 0, 1, 1, 0, '{"legacy":false,"name":"plg_content_emailcloak","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_CONTENT_EMAILCLOAK_XML_DESCRIPTION","group":""}', '{"mode":"1"}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(405, 'plg_content_geshi', 'plugin', 'geshi', 'content', 0, 0, 1, 0, '{"legacy":false,"name":"plg_content_geshi","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"","authorUrl":"qbnz.com\\/highlighter","version":"2.5.0","description":"PLG_CONTENT_GESHI_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 2, 0),
(406, 'plg_content_loadmodule', 'plugin', 'loadmodule', 'content', 0, 1, 1, 0, '{"legacy":false,"name":"plg_content_loadmodule","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_LOADMODULE_XML_DESCRIPTION","group":""}', '{"style":"none"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(407, 'plg_content_pagebreak', 'plugin', 'pagebreak', 'content', 0, 1, 1, 1, '{"legacy":false,"name":"plg_content_pagebreak","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_CONTENT_PAGEBREAK_XML_DESCRIPTION","group":""}', '{"title":"1","multipage_toc":"1","showall":"1"}', '', '', 0, '0000-00-00 00:00:00', 4, 0),
(408, 'plg_content_pagenavigation', 'plugin', 'pagenavigation', 'content', 0, 1, 1, 1, '{"legacy":false,"name":"plg_content_pagenavigation","type":"plugin","creationDate":"January 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_PAGENAVIGATION_XML_DESCRIPTION","group":""}', '{"position":"1"}', '', '', 0, '0000-00-00 00:00:00', 5, 0),
(409, 'plg_content_vote', 'plugin', 'vote', 'content', 0, 1, 1, 1, '{"legacy":false,"name":"plg_content_vote","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_VOTE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 6, 0),
(410, 'plg_editors_codemirror', 'plugin', 'codemirror', 'editors', 0, 1, 1, 1, '{"legacy":false,"name":"plg_editors_codemirror","type":"plugin","creationDate":"28 March 2011","author":"Marijn Haverbeke","copyright":"","authorEmail":"N\\/A","authorUrl":"","version":"1.0","description":"PLG_CODEMIRROR_XML_DESCRIPTION","group":""}', '{"linenumbers":"0","tabmode":"indent"}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(411, 'plg_editors_none', 'plugin', 'none', 'editors', 0, 1, 1, 1, '{"legacy":false,"name":"plg_editors_none","type":"plugin","creationDate":"August 2004","author":"Unknown","copyright":"","authorEmail":"N\\/A","authorUrl":"","version":"2.5.0","description":"PLG_NONE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 2, 0),
(412, 'plg_editors_tinymce', 'plugin', 'tinymce', 'editors', 0, 1, 1, 1, '{"legacy":false,"name":"plg_editors_tinymce","type":"plugin","creationDate":"2005-2013","author":"Moxiecode Systems AB","copyright":"Moxiecode Systems AB","authorEmail":"N\\/A","authorUrl":"tinymce.moxiecode.com\\/","version":"3.5.4.1","description":"PLG_TINY_XML_DESCRIPTION","group":""}', '{"mode":"1","skin":"0","compressed":"0","cleanup_startup":"0","cleanup_save":"2","entity_encoding":"raw","lang_mode":"0","lang_code":"en","text_direction":"ltr","content_css":"1","content_css_custom":"","relative_urls":"1","newlines":"0","invalid_elements":"script,applet,iframe","extended_elements":"","toolbar":"top","toolbar_align":"left","html_height":"550","html_width":"750","element_path":"1","fonts":"1","paste":"1","searchreplace":"1","insertdate":"1","format_date":"%Y-%m-%d","inserttime":"1","format_time":"%H:%M:%S","colors":"1","table":"1","smilies":"1","media":"1","hr":"1","directionality":"1","fullscreen":"1","style":"1","layer":"1","xhtmlxtras":"1","visualchars":"1","nonbreaking":"1","template":"1","blockquote":"1","wordcount":"1","advimage":"1","advlink":"1","autosave":"1","contextmenu":"1","inlinepopups":"1","safari":"0","custom_plugin":"","custom_button":""}', '', '', 0, '0000-00-00 00:00:00', 3, 0),
(413, 'plg_editors-xtd_article', 'plugin', 'article', 'editors-xtd', 0, 1, 1, 1, '{"legacy":false,"name":"plg_editors-xtd_article","type":"plugin","creationDate":"October 2009","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_ARTICLE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(414, 'plg_editors-xtd_image', 'plugin', 'image', 'editors-xtd', 0, 1, 1, 0, '{"legacy":false,"name":"plg_editors-xtd_image","type":"plugin","creationDate":"August 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_IMAGE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 2, 0),
(415, 'plg_editors-xtd_pagebreak', 'plugin', 'pagebreak', 'editors-xtd', 0, 1, 1, 0, '{"legacy":false,"name":"plg_editors-xtd_pagebreak","type":"plugin","creationDate":"August 2004","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_EDITORSXTD_PAGEBREAK_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 3, 0),
(416, 'plg_editors-xtd_readmore', 'plugin', 'readmore', 'editors-xtd', 0, 1, 1, 0, '{"legacy":false,"name":"plg_editors-xtd_readmore","type":"plugin","creationDate":"March 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_READMORE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 4, 0),
(417, 'plg_search_categories', 'plugin', 'categories', 'search', 0, 1, 1, 0, '{"legacy":false,"name":"plg_search_categories","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SEARCH_CATEGORIES_XML_DESCRIPTION","group":""}', '{"search_limit":"50","search_content":"1","search_archived":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(418, 'plg_search_contacts', 'plugin', 'contacts', 'search', 0, 1, 1, 0, '{"legacy":false,"name":"plg_search_contacts","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SEARCH_CONTACTS_XML_DESCRIPTION","group":""}', '{"search_limit":"50","search_content":"1","search_archived":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(419, 'plg_search_content', 'plugin', 'content', 'search', 0, 1, 1, 0, '{"legacy":false,"name":"plg_search_content","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SEARCH_CONTENT_XML_DESCRIPTION","group":""}', '{"search_limit":"50","search_content":"1","search_archived":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(420, 'plg_search_newsfeeds', 'plugin', 'newsfeeds', 'search', 0, 1, 1, 0, '{"legacy":false,"name":"plg_search_newsfeeds","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SEARCH_NEWSFEEDS_XML_DESCRIPTION","group":""}', '{"search_limit":"50","search_content":"1","search_archived":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(421, 'plg_search_weblinks', 'plugin', 'weblinks', 'search', 0, 1, 1, 0, '{"legacy":false,"name":"plg_search_weblinks","type":"plugin","creationDate":"November 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SEARCH_WEBLINKS_XML_DESCRIPTION","group":""}', '{"search_limit":"50","search_content":"1","search_archived":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(422, 'plg_system_languagefilter', 'plugin', 'languagefilter', 'system', 0, 0, 1, 1, '{"legacy":false,"name":"plg_system_languagefilter","type":"plugin","creationDate":"July 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SYSTEM_LANGUAGEFILTER_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(423, 'plg_system_p3p', 'plugin', 'p3p', 'system', 0, 1, 1, 1, '{"legacy":false,"name":"plg_system_p3p","type":"plugin","creationDate":"September 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_P3P_XML_DESCRIPTION","group":""}', '{"headers":"NOI ADM DEV PSAi COM NAV OUR OTRo STP IND DEM"}', '', '', 0, '0000-00-00 00:00:00', 2, 0),
(424, 'plg_system_cache', 'plugin', 'cache', 'system', 0, 0, 1, 1, '{"legacy":false,"name":"plg_system_cache","type":"plugin","creationDate":"February 2007","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_CACHE_XML_DESCRIPTION","group":""}', '{"browsercache":"0","cachetime":"15"}', '', '', 0, '0000-00-00 00:00:00', 3, 0),
(425, 'plg_system_debug', 'plugin', 'debug', 'system', 0, 1, 1, 0, '{"legacy":false,"name":"plg_system_debug","type":"plugin","creationDate":"December 2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_DEBUG_XML_DESCRIPTION","group":""}', '{"profile":"1","queries":"1","memory":"1","language_files":"1","language_strings":"1","strip-first":"1","strip-prefix":"","strip-suffix":""}', '', '', 0, '0000-00-00 00:00:00', 4, 0),
(426, 'plg_system_log', 'plugin', 'log', 'system', 0, 1, 1, 1, '{"legacy":false,"name":"plg_system_log","type":"plugin","creationDate":"April 2007","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_LOG_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 5, 0),
(427, 'plg_system_redirect', 'plugin', 'redirect', 'system', 0, 1, 1, 1, '{"legacy":false,"name":"plg_system_redirect","type":"plugin","creationDate":"April 2009","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_REDIRECT_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 6, 0),
(428, 'plg_system_remember', 'plugin', 'remember', 'system', 0, 0, 1, 1, '{"legacy":false,"name":"plg_system_remember","type":"plugin","creationDate":"April 2007","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_REMEMBER_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 7, 0),
(429, 'plg_system_sef', 'plugin', 'sef', 'system', 0, 1, 1, 0, '{"legacy":false,"name":"plg_system_sef","type":"plugin","creationDate":"December 2007","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SEF_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 8, 0),
(430, 'plg_system_logout', 'plugin', 'logout', 'system', 0, 1, 1, 1, '{"legacy":false,"name":"plg_system_logout","type":"plugin","creationDate":"April 2009","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SYSTEM_LOGOUT_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 9, 0),
(431, 'plg_user_contactcreator', 'plugin', 'contactcreator', 'user', 0, 0, 1, 1, '{"legacy":false,"name":"plg_user_contactcreator","type":"plugin","creationDate":"August 2009","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_CONTACTCREATOR_XML_DESCRIPTION","group":""}', '{"autowebpage":"","category":"34","autopublish":"0"}', '', '', 0, '0000-00-00 00:00:00', 1, 0);
INSERT INTO `#__extensions` (`extension_id`, `name`, `type`, `element`, `folder`, `client_id`, `enabled`, `access`, `protected`, `manifest_cache`, `params`, `custom_data`, `system_data`, `checked_out`, `checked_out_time`, `ordering`, `state`) VALUES
(432, 'plg_user_joomla', 'plugin', 'joomla', 'user', 0, 1, 1, 0, '{"legacy":false,"name":"plg_user_joomla","type":"plugin","creationDate":"December 2006","author":"Joomla! Project","copyright":"(C) 2005 - 2009 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_USER_JOOMLA_XML_DESCRIPTION","group":""}', '{"autoregister":"1"}', '', '', 0, '0000-00-00 00:00:00', 2, 0),
(433, 'plg_user_profile', 'plugin', 'profile', 'user', 0, 0, 1, 1, '{"legacy":false,"name":"plg_user_profile","type":"plugin","creationDate":"January 2008","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_USER_PROFILE_XML_DESCRIPTION","group":""}', '{"register-require_address1":"1","register-require_address2":"1","register-require_city":"1","register-require_region":"1","register-require_country":"1","register-require_postal_code":"1","register-require_phone":"1","register-require_website":"1","register-require_favoritebook":"1","register-require_aboutme":"1","register-require_tos":"1","register-require_dob":"1","profile-require_address1":"1","profile-require_address2":"1","profile-require_city":"1","profile-require_region":"1","profile-require_country":"1","profile-require_postal_code":"1","profile-require_phone":"1","profile-require_website":"1","profile-require_favoritebook":"1","profile-require_aboutme":"1","profile-require_tos":"1","profile-require_dob":"1"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(434, 'plg_extension_joomla', 'plugin', 'joomla', 'extension', 0, 1, 1, 1, '{"legacy":false,"name":"plg_extension_joomla","type":"plugin","creationDate":"May 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_EXTENSION_JOOMLA_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(435, 'plg_content_joomla', 'plugin', 'joomla', 'content', 0, 1, 1, 0, '{"legacy":false,"name":"plg_content_joomla","type":"plugin","creationDate":"November 2010","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_CONTENT_JOOMLA_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(500, 'atomic', 'template', 'atomic', '', 0, 1, 1, 0, '{"legacy":false,"name":"atomic","type":"template","creationDate":"10\\/10\\/09","author":"Ron Severdia","copyright":"Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.","authorEmail":"contact@kontentdesign.com","authorUrl":"http:\\/\\/www.kontentdesign.com","version":"2.5.0","description":"TPL_ATOMIC_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(502, 'bluestork', 'template', 'bluestork', '', 1, 1, 1, 0, '{"legacy":false,"name":"bluestork","type":"template","creationDate":"07\\/02\\/09","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"TPL_BLUESTORK_XML_DESCRIPTION","group":""}', '{"useRoundedCorners":"1","showSiteName":"0","textBig":"0","highContrast":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(503, 'beez_20', 'template', 'beez_20', '', 0, 1, 1, 0, '{"legacy":false,"name":"beez_20","type":"template","creationDate":"25 November 2009","author":"Angie Radtke","copyright":"Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.","authorEmail":"a.radtke@derauftritt.de","authorUrl":"http:\\/\\/www.der-auftritt.de","version":"2.5.0","description":"TPL_BEEZ2_XML_DESCRIPTION","group":""}', '{"wrapperSmall":"53","wrapperLarge":"72","sitetitle":"","sitedescription":"","navposition":"center","templatecolor":"nature"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(504, 'hathor', 'template', 'hathor', '', 1, 1, 1, 0, '{"legacy":false,"name":"hathor","type":"template","creationDate":"May 2010","author":"Andrea Tarr","copyright":"Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.","authorEmail":"hathor@tarrconsulting.com","authorUrl":"http:\\/\\/www.tarrconsulting.com","version":"2.5.0","description":"TPL_HATHOR_XML_DESCRIPTION","group":""}', '{"showSiteName":"0","colourChoice":"0","boldText":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(505, 'beez5', 'template', 'beez5', '', 0, 1, 1, 0, '{"legacy":false,"name":"beez5","type":"template","creationDate":"21 May 2010","author":"Angie Radtke","copyright":"Copyright (C) 2005 - 2014 Open Source Matters, Inc. All rights reserved.","authorEmail":"a.radtke@derauftritt.de","authorUrl":"http:\\/\\/www.der-auftritt.de","version":"2.5.0","description":"TPL_BEEZ5_XML_DESCRIPTION","group":""}', '{"wrapperSmall":"53","wrapperLarge":"72","sitetitle":"","sitedescription":"","navposition":"center","html5":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(600, 'English (United Kingdom)', 'language', 'en-GB', '', 0, 1, 1, 1, '{"legacy":false,"name":"English (United Kingdom)","type":"language","creationDate":"2008-03-15","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.19","description":"en-GB site language","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(601, 'English (United Kingdom)', 'language', 'en-GB', '', 1, 1, 1, 1, '{"legacy":false,"name":"English (United Kingdom)","type":"language","creationDate":"2008-03-15","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.19","description":"en-GB administrator language","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(700, 'files_joomla', 'file', 'joomla', '', 0, 1, 1, 1, '{"legacy":false,"name":"files_joomla","type":"file","creationDate":"July 2014","author":"Joomla! Project","copyright":"(C) 2005 - 2014 Open Source Matters. All rights reserved","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.24","description":"FILES_JOOMLA_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10002, 'PKG_JOOMLA', 'package', 'pkg_joomla', '', 0, 1, 1, 1, '{"legacy":false,"name":"PKG_JOOMLA","type":"package","creationDate":"2006","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"http:\\/\\/www.joomla.org","version":"2.5.0","description":"PKG_JOOMLA_XML_DESCRIPTION","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10003, 'K2 Comments', 'module', 'mod_k2_comments', '', 0, 1, 0, 0, '{"legacy":false,"name":"K2 Comments","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"MOD_K2_COMMENTS_DESCRIPTION","group":""}', '{"moduleclass_sfx":"","module_usage":"","":"K2_TOP_COMMENTERS","catfilter":"0","category_id":"","comments_limit":"5","comments_word_limit":"10","commenterName":"1","commentAvatar":"1","commentAvatarWidthSelect":"custom","commentAvatarWidth":"50","commentDate":"1","commentDateFormat":"absolute","commentLink":"1","itemTitle":"1","itemCategory":"1","feed":"1","commenters_limit":"5","commenterNameOrUsername":"1","commenterAvatar":"1","commenterAvatarWidthSelect":"custom","commenterAvatarWidth":"50","commenterLink":"1","commenterCommentsCounter":"1","commenterLatestComment":"1","cache":"1","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10004, 'K2 Content', 'module', 'mod_k2_content', '', 0, 1, 0, 0, '{"legacy":false,"name":"K2 Content","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_MOD_K2_CONTENT_DESCRIPTION","group":""}', '{"moduleclass_sfx":"","getTemplate":"Default","source":"filter","":"K2_OTHER_OPTIONS","catfilter":"0","category_id":"","getChildren":"0","itemCount":"5","itemsOrdering":"","FeaturedItems":"1","popularityRange":"","videosOnly":"0","item":"","items":"","itemTitle":"1","itemAuthor":"1","itemAuthorAvatar":"1","itemAuthorAvatarWidthSelect":"custom","itemAuthorAvatarWidth":"50","userDescription":"1","itemIntroText":"1","itemIntroTextWordLimit":"","itemImage":"1","itemImgSize":"Small","itemVideo":"1","itemVideoCaption":"1","itemVideoCredits":"1","itemAttachments":"1","itemTags":"1","itemCategory":"1","itemDateCreated":"1","itemHits":"1","itemReadMore":"1","itemExtraFields":"0","itemCommentsCounter":"1","feed":"1","itemPreText":"","itemCustomLink":"0","itemCustomLinkTitle":"","itemCustomLinkURL":"http:\\/\\/","itemCustomLinkMenuItem":"","K2Plugins":"1","JPlugins":"1","cache":"1","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10005, 'K2 Login', 'module', 'mod_k2_login', '', 0, 1, 0, 0, '{"legacy":true,"name":"K2 Login","type":"module","creationDate":"October 18th, 2011","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2011 JoomlaWorks Ltd. All rights reserved.","authorEmail":"contact@joomlaworks.gr","authorUrl":"www.joomlaworks.gr","version":"2.5.0","description":"K2_LOGIN_DESCRIPTION","group":""}', '{"moduleclass_sfx":"","pretext":"","":"K2_LOGIN_LOGOUT_REDIRECTION","name":"1","userAvatar":"1","userAvatarWidthSelect":"custom","userAvatarWidth":"50","menu":"","login":"","logout":"","usesecure":"0","cache":"0","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10006, 'K2 Tools', 'module', 'mod_k2_tools', '', 0, 1, 0, 0, '{"legacy":false,"name":"K2 Tools","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_TOOLS","group":""}', '{"moduleclass_sfx":"","module_usage":"0","":"K2_CUSTOM_CODE_SETTINGS","archiveItemsCounter":"1","archiveCategory":"","authors_module_category":"","authorItemsCounter":"1","authorAvatar":"1","authorAvatarWidthSelect":"custom","authorAvatarWidth":"50","authorLatestItem":"1","calendarCategory":"","home":"","seperator":"","root_id":"","end_level":"","categoriesListOrdering":"","categoriesListItemsCounter":"1","root_id2":"","catfilter":"0","category_id":"","getChildren":"0","liveSearch":"","width":"20","text":"","button":"","imagebutton":"","button_text":"","min_size":"75","max_size":"300","cloud_limit":"30","cloud_category":"0","cloud_category_recursive":"0","customCode":"","parsePhp":"0","K2Plugins":"0","JPlugins":"0","cache":"1","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10007, 'K2 Users', 'module', 'mod_k2_users', '', 0, 1, 0, 0, '{"legacy":false,"name":"K2 Users","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_MOD_K2_USERS_DESCRTIPTION","group":""}', '{"moduleclass_sfx":"","getTemplate":"Default","source":"0","":"K2_DISPLAY_OPTIONS","filter":"1","K2UserGroup":"","ordering":"1","limit":"4","userIDs":"","userName":"1","userAvatar":"1","userAvatarWidthSelect":"custom","userAvatarWidth":"50","userDescription":"1","userDescriptionWordLimit":"","userURL":"1","userEmail":"0","userFeed":"1","userItemCount":"1","cache":"1","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10008, 'K2 User', 'module', 'mod_k2_user', '', 0, 1, 0, 0, '{"legacy":false,"name":"K2 User","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_MOD_K2_USER_DESCRIPTION","group":""}', '{"moduleclass_sfx":"","pretext":"","":"K2_LOGIN_LOGOUT_REDIRECTION","name":"1","userAvatar":"1","userAvatarWidthSelect":"custom","userAvatarWidth":"50","menu":"","login":"","logout":"","usesecure":"0","cache":"0","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10009, 'K2 Quick Icons (admin)', 'module', 'mod_k2_quickicons', '', 1, 1, 2, 0, '{"legacy":false,"name":"K2 Quick Icons (admin)","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_QUICKICONS_FOR_USE_IN_THE_JOOMLA_CONTROL_PANEL_DASHBOARD_PAGE","group":""}', '{"modCSSStyling":"1","modLogo":"1","cache":"0","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10010, 'K2 Stats (admin)', 'module', 'mod_k2_stats', '', 1, 1, 2, 0, '{"legacy":false,"name":"K2 Stats (admin)","type":"module","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_STATS_FOR_USE_IN_THE_K2_DASHBOARD_PAGE","group":""}', '{"latestItems":"1","popularItems":"1","mostCommentedItems":"1","latestComments":"1","statistics":"1","cache":"0","cache_time":"900"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10011, 'Search - K2', 'plugin', 'k2', 'search', 0, 1, 1, 0, '{"legacy":false,"name":"Search - K2","type":"plugin","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_THIS_PLUGIN_EXTENDS_THE_DEFAULT_JOOMLA_SEARCH_FUNCTIONALITY_TO_K2_CONTENT","group":""}', '{"search_limit":"50","search_tags":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10012, 'System - K2', 'plugin', 'k2', 'system', 0, 1, 1, 0, '{"legacy":false,"name":"System - K2","type":"plugin","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_THE_K2_SYSTEM_PLUGIN_IS_USED_TO_ASSIST_THE_PROPER_FUNCTIONALITY_OF_THE_K2_COMPONENT_SITE_WIDE_MAKE_SURE_ITS_ALWAYS_PUBLISHED_WHEN_THE_K2_COMPONENT_IS_INSTALLED","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10013, 'User - K2', 'plugin', 'k2', 'user', 0, 1, 1, 0, '{"legacy":false,"name":"User - K2","type":"plugin","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"K2_A_USER_SYNCHRONIZATION_PLUGIN_FOR_K2","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10014, 'com_k2', 'component', 'com_k2', '', 1, 1, 0, 0, '{"legacy":false,"name":"COM_K2","type":"component","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"Thank you for installing K2 by JoomlaWorks, the powerful content extension for Joomla!","group":""}', '{"enable_css":"1","jQueryHandling":"1.6remote","backendJQueryHandling":"remote","userName":"1","userImage":"1","userDescription":"1","userURL":"0","userEmail":"0","userFeedLink":"0","userFeedIcon":"0","userItemCount":"4","userItemTitle":"1","userItemTitleLinked":"1","userItemDateCreated":"1","userItemImage":"1","userItemIntroText":"1","userItemCategory":"0","userItemTags":"0","userItemCommentsAnchor":"0","userItemReadMore":"1","userItemK2Plugins":"1","tagItemCount":"4","tagItemTitle":"1","tagItemTitleLinked":"1","tagItemDateCreated":"1","tagItemImage":"1","tagItemIntroText":"1","tagItemCategory":"1","tagItemReadMore":"1","tagItemExtraFields":"1","tagOrdering":"order","tagFeedLink":"1","tagFeedIcon":"1","genericItemCount":"5","genericItemTitle":"1","genericItemTitleLinked":"1","genericItemDateCreated":"1","genericItemImage":"1","genericItemIntroText":"1","genericItemCategory":"0","genericItemReadMore":"1","genericItemExtraFields":"1","genericFeedLink":"1","genericFeedIcon":"1","feedLimit":"10","feedItemImage":"1","feedImgSize":"S","feedItemIntroText":"1","feedTextWordLimit":"","feedItemFullText":"1","feedItemTags":"0","feedItemVideo":"0","feedItemGallery":"0","feedItemAttachments":"0","feedBogusEmail":"","introTextCleanup":"0","introTextCleanupExcludeTags":"","introTextCleanupTagAttr":"","fullTextCleanup":"0","fullTextCleanupExcludeTags":"","fullTextCleanupTagAttr":"","xssFiltering":"0","linkPopupWidth":"900","linkPopupHeight":"600","imagesQuality":"100","itemImageXS":"95","itemImageS":"160","itemImageM":"180","itemImageL":"600","itemImageXL":"900","itemImageGeneric":"180","catImageWidth":"290","catImageDefault":"1","userImageWidth":"100","userImageDefault":"1","commenterImgWidth":"60","onlineImageEditor":"splashup","imageTimestamp":"0","imageMemoryLimit":"","socialButtonCode":"","twitterUsername":"youjoomla","facebookImage":"XSmall","comments":"1","commentsOrdering":"DESC","commentsLimit":"10","commentsFormPosition":"below","commentsPublishing":"0","commentsReporting":"1","commentsReportRecipient":"","inlineCommentsModeration":"0","gravatar":"0","recaptcha":"0","commentsFormNotes":"1","commentsFormNotesText":"","frontendEditing":"1","showImageTab":"1","showImageGalleryTab":"1","showVideoTab":"1","showExtraFieldsTab":"1","showAttachmentsTab":"1","showK2Plugins":"1","sideBarDisplayFrontend":"0","mergeEditors":"1","sideBarDisplay":"1","attachmentsFolder":"","hideImportButton":"0","googleSearch":"0","googleSearchContainer":"k2Container","K2UserProfile":"0","K2UserGroup":"1","redirect":"156","adminSearch":"simple","cookieDomain":"","taggingSystem":"1","lockTags":"0","showTagFilter":"0","k2TagNorm":"0","k2TagNormCase":"lower","k2TagNormAdditionalReplacements":"","recaptcha_public_key":"","recaptcha_private_key":"","recaptcha_theme":"clean","recaptchaOnRegistration":"0","stopForumSpam":"0","stopForumSpamApiKey":"","showItemsCounterAdmin":"1","showChildCatItems":"1","disableCompactOrdering":"0","metaDescLimit":"150","enforceSEFReplacements":"0","SEFReplacements":"\\u00c5\\u00a0|S, \\u00c5\\u2019|O, \\u00c5\\u00bd|Z, \\u00c5\\u00a1|s, \\u00c5\\u201c|oe, \\u00c5\\u00be|z, \\u00c5\\u00b8|Y, \\u00c2\\u00a5|Y, \\u00c2\\u00b5|u, \\u00c3\\u20ac|A, \\u00c3\\ufffd|A, \\u00c3\\u201a|A, \\u00c3\\u0192|A, \\u00c3\\u201e|A, \\u00c3\\u2026|A, \\u00c3\\u2020|A, \\u00c3\\u2021|C, \\u00c3\\u02c6|E, \\u00c3\\u2030|E, \\u00c3\\u0160|E, \\u00c3\\u2039|E, \\u00c3\\u0152|I, \\u00c3\\ufffd|I, \\u00c3\\u017d|I, \\u00c3\\ufffd|I, \\u00c3\\ufffd|D, \\u00c3\\u2018|N, \\u00c3\\u2019|O, \\u00c3\\u201c|O, \\u00c3\\u201d|O, \\u00c3\\u2022|O, \\u00c3\\u2013|O, \\u00c3\\u02dc|O, \\u00c3\\u2122|U, \\u00c3\\u0161|U, \\u00c3\\u203a|U, \\u00c3\\u0153|U, \\u00c3\\ufffd|Y, \\u00c3\\u0178|s, \\u00c3\\u00a0|a, \\u00c3\\u00a1|a, \\u00c3\\u00a2|a, \\u00c3\\u00a3|a, \\u00c3\\u00a4|a, \\u00c3\\u00a5|a, \\u00c3\\u00a6|a, \\u00c3\\u00a7|c, \\u00c3\\u00a8|e, \\u00c3\\u00a9|e, \\u00c3\\u00aa|e, \\u00c3\\u00ab|e, \\u00c3\\u00ac|i, \\u00c3\\u00ad|i, \\u00c3\\u00ae|i, \\u00c3\\u00af|i, \\u00c3\\u00b0|o, \\u00c3\\u00b1|n, \\u00c3\\u00b2|o, \\u00c3\\u00b3|o, \\u00c3\\u00b4|o, \\u00c3\\u00b5|o, \\u00c3\\u00b6|o, \\u00c3\\u00b8|o, \\u00c3\\u00b9|u, \\u00c3\\u00ba|u, \\u00c3\\u00bb|u, \\u00c3\\u00bc|u, \\u00c3\\u00bd|y, \\u00c3\\u00bf|y, \\u00c3\\u0178|ss","k2Sef":"0","k2SefLabelCat":"content","k2SefLabelTag":"tag","k2SefLabelUser":"author","k2SefLabelSearch":"search","k2SefLabelDate":"date","k2SefLabelItem":"0","k2SefLabelItemCustomPrefix":"","k2SefInsertItemId":"1","k2SefItemIdTitleAliasSep":"dash","k2SefUseItemTitleAlias":"1","k2SefInsertCatId":"1","k2SefCatIdTitleAliasSep":"dash","k2SefUseCatTitleAlias":"1","sh404SefLabelCat":"","sh404SefLabelUser":"blog","sh404SefLabelItem":"2","sh404SefTitleAlias":"alias","sh404SefModK2ContentFeedAlias":"feed","sh404SefInsertItemId":"0","sh404SefInsertUniqueItemId":"0","cbIntegration":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10015, 'English language pack for K2', 'file', '_install', '', 0, 1, 0, 0, '{"legacy":false,"name":"English language pack for K2","type":"file","creationDate":"October 29, 2011","author":"getk2.org","copyright":"Copyright (c) 2006 - 2011 JoomlaWorks Ltd. All rights reserved.","authorEmail":"nospam@getk2.org","authorUrl":"getk2.org","version":"2.5.0","description":"English language pack for K2","group":""}', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(314, 'mod_version', 'module', 'mod_version', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_version","type":"module","creationDate":"January 2012","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2012 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"MOD_VERSION_XML_DESCRIPTION","group":""}', '{"format":"short","product":"1","cache":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(28, 'com_joomlaupdate', 'component', 'com_joomlaupdate', '', 1, 1, 0, 1, '{"legacy":false,"name":"com_joomlaupdate","type":"component","creationDate":"February 2012","author":"Joomla! Project","copyright":"(C) 2005 - 2012 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.2","description":"COM_JOOMLAUPDATE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10020, 'plg_finder_k2', 'plugin', 'k2', 'finder', 0, 1, 1, 0, '{"legacy":false,"name":"plg_finder_k2","type":"plugin","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"PLG_FINDER_K2_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10021, 'Josetta - K2 Categories', 'plugin', 'k2category', 'josetta_ext', 0, 1, 1, 0, '{"legacy":false,"name":"Josetta - K2 Categories","type":"plugin","creationDate":"February 28th, 2014","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10022, 'Josetta - K2 Items', 'plugin', 'k2item', 'josetta_ext', 0, 1, 1, 0, '{"legacy":false,"name":"Josetta - K2 Items","type":"plugin","creationDate":"June 7th, 2012","author":"JoomlaWorks","copyright":"Copyright (c) 2006 - 2014 JoomlaWorks Ltd. All rights reserved.","authorEmail":"please-use-the-contact-form@joomlaworks.net","authorUrl":"www.joomlaworks.net","version":"2.6.8","description":"","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10023, 'YJ Pop Login', 'module', 'mod_yj_pop_login', '', 0, 1, 0, 0, '{"legacy":false,"name":"YJ Pop Login","type":"module","creationDate":"7-9-2014","author":"Youjoomla","copyright":"Youjoomla.com.","authorEmail":"youjoomla@gmail.com","authorUrl":"www.youjoomla.com","version":"1.0.5","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">#holdthem{font-family: Arial Narrow,sans-serif;text-align:left;}#wrap1 {padding:0px 0px 4px 0px;}h1 {clear:both;font-family: Arial Narrow,sans-serif;font-size:18px;margin:0px 0px 12px 0px;padding:0px 0px 1px 10px;color:#C64934;}.wrap2 {background:#F7F7F7;display:block;overflow:hidden;padding:15px;}<\\/style><div id=\\"holdthem\\"><h1>YJ Pop Login Module for Joomla 1.6x and UP<\\/h1><br \\/><div class=\\"wrap2\\"><a title=\\"Visit the official website!\\" href=\\"http:\\/\\/www.youjoomla.com\\"> <img style=\\"float:left;border:1px solid #CFCFCF;margin:0px 15px 4px 22px;\\" src=\\"..\\/modules\\/mod_yj_pop_login\\/images\\/yj_extensions.jpg\\" border=\\"0\\" alt=\\"Logo\\" \\/><\\/a><\\/div><\\/div>\\n\\t","group":""}', '{"cache":"1","@spacer":"","moduleclass_sfx":"","pretext":"Welcome,Guest <br \\/> <br \\/><span> Please login or register<\\/span>","login":"","logout":"","greeting":"1","name":"0","usesecure":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10024, 'YJ Live Search', 'module', 'mod_yj_live_search', '', 0, 1, 0, 0, '{"legacy":false,"name":"YJ Live Search","type":"module","creationDate":"7-14-2014","author":"youjoomla","copyright":"Youjoomla","authorEmail":"youjoomla@gmail.com","authorUrl":"http:\\/\\/www.youjoomla.com","version":"2.0.4","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">#holdthem{font-family: Arial Narrow,sans-serif;text-align:left;}#wrap1 {padding:0px 0px 4px 0px;}h1 {clear:both;font-family: Arial Narrow,sans-serif;font-size:18px;margin:0px 0px 12px 0px;padding:0px 0px 1px 10px;color:#C64934;}.wrap2 {background:#F7F7F7;overflow:hidden;padding:10px;}<\\/style><div id=\\"holdthem\\"><h1>YJ Live Search Module for Joomla 1.7 and UP <\\/h1><div class=\\"wrap2\\"><a title=\\"Visit the official website!\\" href=\\"http:\\/\\/www.youjoomla.com\\" target=\\"_blank\\" ><img style=\\"float:right;border:1px solid #CFCFCF;margin:0px 15px 4px 22px;\\" src=\\"..\\/modules\\/mod_yj_live_search\\/images\\/live_logo.jpg\\" border=\\"0\\" alt=\\"Logo\\" \\/><\\/a><h3>Mootools and CSS powered Live Search Module for Joomla 1.7 and UP Fully CSS customizable , cross browser compatible, fast loading , CSS and XHTML Valid, search parameters are fully adjustable to fit your Joomla installation. Simply publish the module in any Joomla module position, set the input box width and  hit save. Happy searching!<\\/h3><ul><li>Easy to install and use<\\/li><li>Extremely ffast and lightweight<\\/li><li>Fully customizable via  CSS<\\/li><\\/ul><\\/h4><br \\/><br \\/><a title=\\"Visit the official website for more info\\" href=\\"http:\\/\\/www.youjoomla.com\\">This is a Commercial Joomla Extension provided to you by Youjoomla.com<\\/a><\\/div><\\/div>\\n\\t","group":""}', '{"live_status":"1","is_copy":"defaultfeed","feedtype":"feeds","index_items":"1","mysql_operator":"1","input_width":"210","search_limit":"5","intro_limit":"70","topDistance":"35","duration":"500","minLenght":"3","maxLenght":"10","minCharTxt1":"Minimum","minCharTxt2":"characters required","default_input":"Search","no_results":"Sorry, no results for","search_button":"1","imagesettings":"<strong>Image Settings<\\/strong>","show_image":"1","k2image_size":"XS","image_width":"75","Search_Content":"1","Search_Weblinks":"1","Search_Contacts":"1","Search_Categories":"1","Search_Newsfeeds":"1","Search_K2":"1","Search_K2_cat":"1","Search_K2_tags":"1","moduleclass_sfx":""}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10025, 'YJ Image Slider V4', 'module', 'mod_yjis4', '', 0, 1, 0, 0, '{"legacy":false,"name":"YJ Image Slider V4","type":"module","creationDate":"7-15-2014","author":"Youjoomla.com","copyright":"","authorEmail":"youjoomla@gmail.com","authorUrl":"www.youjoomla.com","version":"4.0.9","description":"\\n\\t\\t<div class=''wrap2'' style=''display:block;overflow:hidden;clear:both;''><h1>YJ Image Slider v4<\\/h1><br \\/><a title=''Visit the official website!'' href=''http:\\/\\/www.youjoomla.com'' target=''_blank''> <img style=''float:left;border:0px;margin:0px 15px 4px 22px;''src=''..\\/modules\\/mod_yjis4\\/images\\/yj_extensions.png'' border=''0'' alt=''Logo'' \\/><\\/a><br \\/> <h3> Images can be in any ROOT\\/images folder but to keep them all in one place<br \\/>we created ROOT\\/images\\/upload_slides folder for you.<br \\/>Once you click on ''select'' button you will be taken to images\\/upload_slides folder.<\\/h3><br \\/> YJIS support can be found <a href=''http:\\/\\/www.youjoomla.com\\/joomla_support\\/yj-image-slider-4-0\\/'' target=''_blank''>here<\\/a><br \\/><br \\/><a href=''http:\\/\\/extensions.youjoomla.info\\/yj-image-slider.html'' target=''_blank''>Different Options examples are here<\\/a><br \\/><br \\/>  List of available MediaboxAdvanced video types  can be found <a href=''http:\\/\\/iaian7.com\\/webcode\\/mediaboxAdvanced#twitter'' target=''_blank''>here<\\/a><br \\/> <br \\/> For all info on MediaboxAdvanced please visit <a href=''http:\\/\\/iaian7.com\\/webcode\\/mediaboxAdvanced'' target=''_blank''>official website<\\/a><\\/div>\\n\\t","group":""}', '{"moduleclass_sfx":"_yjis","module_template":"Default","swidth":"600","sheight":"250","sorient":"0","type_slider":"0","start_slide":"0","stime":"6000","sduration":"600","smenu":"1","tooltips":"0","order":"0","hide_all_intros":"0","hide_all_descriptions":"0","slide_title_1":"","slide_description_1":"","slide_intro_1":"","mboxlink_1":"","mboxtype_1":"0","video_width_1":"640","video_height_1":"360","image_group_1":"0","image_group_name_1":"yjgallery","slide_link_1":"","slide_open_1":"0","slide_title_2":"","slide_description_2":"","slide_intro_2":"","mboxlink_2":"","mboxtype_2":"0","video_width_2":"640","video_height_2":"360","image_group_2":"0","image_group_name_2":"yjgallery","slide_link_2":"","slide_open_2":"0","slide_title_3":"","slide_description_3":"","slide_intro_3":"","mboxlink_3":"","mboxtype_3":"0","video_width_3":"640","video_height_3":"360","image_group_3":"0","image_group_name_3":"yjgallery","slide_link_3":"","slide_open_3":"0","slide_title_4":"","slide_description_4":"","slide_intro_4":"","mboxlink_4":"","mboxtype_4":"0","video_width_4":"640","video_height_4":"360","image_group_4":"0","image_group_name_4":"yjgallery","slide_link_4":"","slide_open_4":"0","slide_title_5":"","slide_description_5":"","slide_intro_5":"","mboxlink_5":"","mboxtype_5":"0","video_width_5":"640","video_height_5":"360","image_group_5":"0","image_group_name_5":"yjgallery","slide_link_5":"","slide_open_5":"0","slide_title_6":"","slide_description_6":"","slide_intro_6":"","mboxlink_6":"","mboxtype_6":"0","video_width_6":"640","video_height_6":"360","image_group_6":"0","image_group_name_6":"yjgallery","slide_link_6":"","slide_open_6":"0","slide_title_7":"","slide_description_7":"","slide_intro_7":"","mboxlink_7":"","mboxtype_7":"0","video_width_7":"640","video_height_7":"360","image_group_7":"0","image_group_name_7":"yjgallery","slide_link_7":"","slide_open_7":"0","slide_title_8":"","slide_description_8":"","slide_intro_8":"","mboxlink_8":"","mboxtype_8":"0","video_width_8":"640","video_height_8":"360","image_group_8":"0","image_group_name_8":"yjgallery","slide_link_8":"","slide_open_8":"0","slide_title_9":"","slide_description_9":"","slide_intro_9":"","mboxlink_9":"","mboxtype_9":"0","video_width_9":"640","video_height_9":"360","image_group_9":"0","image_group_name_9":"yjgallery","slide_link_9":"","slide_open_9":"0","slide_title_10":"","slide_description_10":"","slide_intro_10":"","mboxlink_10":"","mboxtype_10":"0","video_width_10":"640","video_height_10":"360","image_group_10":"0","image_group_name_10":"yjgallery","slide_link_10":"","slide_open_10":"0","slide_title_11":"","slide_description_11":"","slide_intro_11":"","mboxlink_11":"","mboxtype_11":"0","video_width_11":"640","video_height_11":"360","image_group_11":"0","image_group_name_11":"yjgallery","slide_link_11":"","slide_open_11":"0","slide_title_12":"","slide_description_12":"","slide_intro_12":"","mboxlink_12":"","mboxtype_12":"0","video_width_12":"640","video_height_12":"360","image_group_12":"0","image_group_name_12":"yjgallery","slide_link_12":"","slide_open_12":"0","slide_title_13":"","slide_description_13":"","slide_intro_13":"","mboxlink_13":"","mboxtype_13":"0","video_width_13":"640","video_height_13":"360","image_group_13":"0","image_group_name_13":"yjgallery","slide_link_13":"","slide_open_13":"0","slide_title_14":"","slide_description_14":"","slide_intro_14":"","mboxlink_14":"","mboxtype_14":"0","video_width_14":"640","video_height_14":"360","image_group_14":"0","image_group_name_14":"yjgallery","slide_link_14":"","slide_open_14":"0","slide_title_15":"","slide_description_15":"","slide_intro_15":"","mboxlink_15":"","mboxtype_15":"0","video_width_15":"640","video_height_15":"360","image_group_15":"0","image_group_name_15":"yjgallery","slide_link_15":"","slide_open_15":"0","slide_title_16":"","slide_description_16":"","slide_intro_16":"","mboxlink_16":"","mboxtype_16":"0","video_width_16":"640","video_height_16":"360","image_group_16":"0","image_group_name_16":"yjgallery","slide_link_16":"","slide_open_16":"0","slide_title_17":"","slide_description_17":"","slide_intro_17":"","mboxlink_17":"","mboxtype_17":"0","video_width_17":"640","video_height_17":"360","image_group_17":"0","image_group_name_17":"yjgallery","slide_link_17":"","slide_open_17":"0","slide_title_18":"","slide_description_18":"","slide_intro_18":"","mboxlink_18":"","mboxtype_18":"0","video_width_18":"640","video_height_18":"360","image_group_18":"0","image_group_name_18":"yjgallery","slide_link_18":"","slide_open_18":"0","slide_title_19":"","slide_description_19":"","slide_intro_19":"","mboxlink_19":"","mboxtype_19":"0","video_width_19":"640","video_height_19":"360","image_group_19":"0","image_group_name_19":"yjgallery","slide_link_19":"","slide_open_19":"0","slide_title_20":"","slide_description_20":"","slide_intro_20":"","mboxlink_20":"","mboxtype_20":"0","video_width_20":"640","video_height_20":"360","image_group_20":"0","image_group_name_20":"yjgallery","slide_link_20":"","slide_open_20":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10038, 'fields_common', 'plugin', 'fields_common', 'josetta_ext', 0, 1, 1, 0, 'false', '', '', '', 0, '0000-00-00 00:00:00', 0, -1),
(10039, 'definitions', 'plugin', 'definitions', 'josetta_ext', 0, 1, 1, 0, 'false', '', '', '', 0, '0000-00-00 00:00:00', 0, -1),
(10040, 'fields_common', 'plugin', 'fields_common', 'josetta_ext', 0, 1, 1, 0, 'false', '', '', '', 0, '0000-00-00 00:00:00', 0, -1),
(10018, 'mod_unread', 'module', 'mod_unread', '', 1, 1, 1, 0, '{"legacy":false,"name":"mod_unread","type":"module","creationDate":"Nov 2005","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2011 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"1.6.0","description":"MOD_UNREAD_XML_DESCRIPTION","group":""}', '{"cache":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(436, 'plg_system_languagecode', 'plugin', 'languagecode', 'system', 0, 0, 1, 0, '{"legacy":false,"name":"plg_system_languagecode","type":"plugin","creationDate":"November 2011","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_SYSTEM_LANGUAGECODE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 10, 0),
(437, 'plg_quickicon_joomlaupdate', 'plugin', 'joomlaupdate', 'quickicon', 0, 1, 1, 1, '{"legacy":false,"name":"plg_quickicon_joomlaupdate","type":"plugin","creationDate":"August 2011","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_QUICKICON_JOOMLAUPDATE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(438, 'plg_quickicon_extensionupdate', 'plugin', 'extensionupdate', 'quickicon', 0, 1, 1, 1, '{"legacy":false,"name":"plg_quickicon_extensionupdate","type":"plugin","creationDate":"August 2011","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_QUICKICON_EXTENSIONUPDATE_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(27, 'com_finder', 'component', 'com_finder', '', 1, 1, 0, 0, '', '{"show_description":"1","description_length":255,"allow_empty_query":"0","show_url":"1","show_advanced":"1","expand_advanced":"0","show_date_filters":"0","highlight_terms":"1","opensearch_name":"","opensearch_description":"","batch_size":"50","memory_table_limit":30000,"title_multiplier":"1.7","text_multiplier":"0.7","meta_multiplier":"1.2","path_multiplier":"2.0","misc_multiplier":"0.3","stemmer":"porter_en"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(439, 'plg_captcha_recaptcha', 'plugin', 'recaptcha', 'captcha', 0, 1, 1, 0, '{"legacy":false,"name":"plg_captcha_recaptcha","type":"plugin","creationDate":"December 2011","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"2.5.0","description":"PLG_CAPTCHA_RECAPTCHA_XML_DESCRIPTION","group":""}', '{"public_key":"","private_key":"","theme":"clean"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(440, 'plg_system_highlight', 'plugin', 'highlight', 'system', 0, 1, 1, 0, '', '{}', '', '', 0, '0000-00-00 00:00:00', 7, 0),
(441, 'plg_content_finder', 'plugin', 'finder', 'content', 0, 0, 1, 0, '{"legacy":false,"name":"plg_content_finder","type":"plugin","creationDate":"December 2011","author":"Joomla! Project","copyright":"Copyright (C) 2005 - 2012 Open Source Matters. All rights reserved.","authorEmail":"admin@joomla.org","authorUrl":"www.joomla.org","version":"1.7.0","description":"PLG_CONTENT_FINDER_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(442, 'plg_finder_categories', 'plugin', 'categories', 'finder', 0, 1, 1, 0, '', '{}', '', '', 0, '0000-00-00 00:00:00', 1, 0),
(443, 'plg_finder_contacts', 'plugin', 'contacts', 'finder', 0, 1, 1, 0, '', '{}', '', '', 0, '0000-00-00 00:00:00', 2, 0),
(444, 'plg_finder_content', 'plugin', 'content', 'finder', 0, 1, 1, 0, '', '{}', '', '', 0, '0000-00-00 00:00:00', 3, 0),
(445, 'plg_finder_newsfeeds', 'plugin', 'newsfeeds', 'finder', 0, 1, 1, 0, '', '{}', '', '', 0, '0000-00-00 00:00:00', 4, 0),
(446, 'plg_finder_weblinks', 'plugin', 'weblinks', 'finder', 0, 1, 1, 0, '', '{}', '', '', 0, '0000-00-00 00:00:00', 5, 0),
(223, 'mod_finder', 'module', 'mod_finder', '', 0, 1, 0, 0, '', '', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10019, 'HDChannel', 'template', 'hdchannel', '', 0, 1, 1, 0, '{"legacy":false,"name":"HDChannel","type":"template","creationDate":"06-29-2014","author":"Youjoomla.com","copyright":"Copyright (C),FT Web Studio INC. All Rights Reserved.","authorEmail":"templates@youjoomla.com","authorUrl":"www.youjoomla.com","version":"1.0.5","description":"<style type=\\"text\\/css\\" media=\\"all\\">#wrap1 {padding:10px 0;margin:20px auto;float:left;display:block;overflow:hidden;clear:both;max-width:500px;line-height:24px;height:auto!important;text-align:center!important;background:#F6F6F6;border:1px solid #DEDEDE;-webkit-box-sizing: border-box; -moz-box-sizing: border-box;box-sizing: border-box;}.admin_t_name {font-size:18px;display:block;clear:both;text-align:center!important;-webkit-box-sizing: border-box; -moz-box-sizing: border-box;box-sizing: border-box;margin:15px!important;}#wrap1 img {display:block;margin:0 auto;text-align:center!important;float:none!important;}.exinfo{font-size:14px;padding:10px;display:block;overflow:hidden;text-align:center!important;-webkit-box-sizing: border-box; -moz-box-sizing: border-box;box-sizing: border-box;}<\\/style><div id=\\"wrap1\\"><h2 class=\\"admin_t_name\\">Hdchannel Joomla! Template<\\/h2><img src=\\"..\\/templates\\/hdchannel\\/template_thumbnail.png\\" \\/><h2 class=\\"admin_t_name\\">is proudly powered by<\\/h2><a href=\\"http:\\/\\/www.yjsimplegrid.com\\" target=\\"_blank\\"><span title=\\"YJSimpleGrid Joomla! Template Framework by Youjoomla.com\\"><img src=\\"..\\/templates\\/hdchannel\\/images\\/system\\/yjsg.png\\" border=\\"0\\" title=\\"Yjsglogo\\" alt=\\"yjsglogo\\"\\/><\\/span><\\/a><h2 class=\\"admin_t_name\\">YJSG Template Framework<\\/h2><div class=\\"exinfo\\">Thank you for downloading Youjoomla.com template. <br \\/>For fast info visit following links: <br \\/><a href=\\"http:\\/\\/yjsimplegrid.com\\/documentation\\/\\" target=\\"_blank\\">Documentation<\\/a> | <a href=\\"http:\\/\\/www.joomlatemplates.youjoomla.info\\/hdchannel\\/\\" target=\\"_blank\\">Demo<\\/a><br \\/>If you need support please post new thread in our <a href=\\"http:\\/\\/www.youjoomla.com\\/joomla_support\\/index.php\\" target=\\"_blank\\">Forum<\\/a>. <br \\/>Have fun playing with your new template :) <br \\/><a href=\\"http:\\/\\/www.youjoomla.com\\">Youjoomla.com<\\/a><\\/div><\\/div>","group":""}', '{"":"TOP_MENU_OFF_YJ_LABEL","STYLE_SETTINGS_TAB":"STYLE_SETTINGS_TAB","custom_css":"2","STTEXT_LABEL":"STTEXT_LABEL","STYLE_SUB":"STYLE_SUB","default_color":"gray","default_font":"3","default_font_family":"12","selectors_override":"2","selectors_override_type":"1","css_font_family":"12","google_font_family":"2","cufon_font_family":"1","affected_selectors":"div.title h1,div.title h2,div.componentheading, h1,h2,h3,h4,h5,h6,.yjround h4,.yjsquare h4","LOGO_SUB":"LOGO_SUB","LGTEXT_LABEL":"LGTEXT_LABEL","LOGO_YJ_TITLE":"LOGO_YJ_TITLE","logo_height":"125px","logo_width":"300px","turn_logo_off":"2","turn_header_block_off":"2","TOP_MENU_SUB":"TOP_MENU_SUB","TMTEXT_LABEL":"TMTEXT_LABEL","TOP_MENU_YJ_LABEL":"TOP_MENU_YJ_LABEL","menuName":"mainmenu","default_menu_style":"2","sub_width":"280px","yjsg_menu_offset":"95","turn_topmenu_off":"0","DEF_GRID_SUB":"DEF_GRID_SUB","DGTEXT_LABEL":"DGTEXT_LABEL","MAINB_YJ_LABEL":"MAINB_YJ_LABEL","css_widthdefined":"px","css_width":"1000","site_layout":"2","MBC_W_LABEL":"MBC_W_LABEL","widthdefined":"%","maincolumn":"40","insetcolumn":"0","leftcolumn":"30","rightcolumn":"30","SPII_LABEL":"SPII_LABEL","widthdefined_itmid":"%","maincolumn_itmid":"55","insetcolumn_itmid":"0","leftcolumn_itmid":"22.5","rightcolumn_itmid":"22.5","define_itemid":"0","MG_SUB":"MG_SUB","MGTEXT_LABEL":"MGTEXT_LABEL","yjsg_1_width":"20|20|20|20|20","yjsg_header_width":"33|33|33","yjsg_2_width":"20|20|20|20|20","yjsg_3_width":"20|20|20|20|20","yjsg_4_width":"20|20|20|20|20","yjsg_bodytop_width":"33|33|33","yjsg_yjsgbodytbottom_width":"33|33|33","yjsg_5_width":"20|20|20|20|20","yjsg_6_width":"20|20|20|20|20","yjsg_7_width":"20|20|20|20|20","MOBILE_SUB":"MOBILE_SUB","MOBILE_TXT_LABEL":"MOBILE_TXT_LABEL","iphone_default":"1","android_default":"1","handheld_default":"2","mobile_reg":"1","ADD_F_SUB":"ADD_F_SUB","ADTEXT_LABEL":"ADTEXT_LABEL","GA_YJ_LABEL":"GA_YJ_LABEL","GATEXT_LABEL":"GATEXT_LABEL","ga_switch":"0","GAcode":"UA-1234567-8","NOT_YJ_LABEL":"NOT_YJ_LABEL","compress":"0","ie6notice":"0","nonscript":"0","ST_YJ_LABEL":"ST_YJ_LABEL","show_tools":"1","show_fres":"1","show_rtlc":"1","TDIR_YJ_LABEL":"TDIR_YJ_LABEL","text_direction":"2","SEO_YJ_LABEL":"SEO_YJ_LABEL","turn_seo_off":"2","seo":"Your description goes here","tags":"Your keywords go here","COPY_YJ_LABEL":"COPY_YJ_LABEL","branding_off":"2","joomlacredit_off":"2","ADV_SUB":"ADV_SUB","ADVTEXT_LABEL":"ADVTEXT_LABEL","FPC_YJ_LABEL":"FPC_YJ_LABEL","fp_controll_switch":"2","fp_chars_limit":"3000","fp_after_text":"","SCRIPT_YJ_LABEL":"SCRIPT_YJ_LABEL","JQ_LABEL":"JQ_LABEL","jq_switch":"2","SMS_YJ_LABEL":"SMS_YJ_LABEL","MSTEXT_LABEL":"MSTEXT_LABEL","YJsg1_module_style":"YJsgxhtml","YJsgh_module_style":"YJsgxhtml","YJsg2_module_style":"YJsgxhtml","YJsg3_module_style":"YJsgxhtml","YJsg4_module_style":"YJsgxhtml","YJsgmt_module_style":"YJsgxhtml","YJsgl_module_style":"YJsgxhtml","YJsgr_module_style":"YJsgxhtml","YJsgi_module_style":"YJsgxhtml","YJsgit_module_style":"YJsgxhtml","YJsgib_module_style":"YJsgxhtml","YJsgmb_module_style":"YJsgxhtml","YJsg5_module_style":"YJsgxhtml","YJsg6_module_style":"YJsgxhtml","YJsg7_module_style":"YJsgxhtml","CP_LABEL":"CP_LABEL","component_switch":"0","admin_css_time":"0"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10026, 'Youyork Module Slider', 'module', 'mod_youyork_slider', '', 0, 1, 0, 0, '{"legacy":false,"name":"Youyork Module Slider","type":"module","creationDate":"8-1-2014","author":"YouJoomla.com","copyright":"","authorEmail":"youjoomla@gmail.com","authorUrl":"www.youjoomla.com","version":"2.0.3","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">#holdthem{font-family: Arial Narrow,sans-serif;text-align:left;}#wrap1 {padding:0px 0px 4px 0px;}h1 {clear:both;font-family: Arial Narrow,sans-serif;font-size:18px;margin:0px 0px 12px 0px;padding:0px 0px 1px 10px;color:#C64934;}.wrap2 {background:#F7F7F7;}<\\/style><div id=\\"holdthem\\"><h1>Youyork Module Slider for Joomla <\\/h1><div class=\\"wrap2\\"><a title=\\"Visit the official website!\\" href=\\"http:\\/\\/www.youjoomla.com\\"> <img style=\\"float:left;border:1px solid #CFCFCF;margin:0px 15px 4px 22px;\\" src=\\"..\\/modules\\/mod_youyork_slider\\/images\\/yj_extensions.jpg\\" border=\\"0\\" alt=\\"Logo\\" \\/><\\/a><\\/div><\\/div>\\n\\t","group":""}', '{"is_copy":"","module_template":"Default","slider_width":"980px","slider_height":"250px","module_pozi":"Slide1,Slide2","module_title":"Title1,Title2","showtitle":"1","visible_modules":"2","effectDuration":"500","autoslide":"5000","show_bottom_nav":"1","moduleclass_sfx":"_youyork_slider"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10027, 'Youjoomla Multitabs Modules', 'module', 'mod_yj_multitab', '', 0, 1, 0, 0, '{"legacy":false,"name":"Youjoomla Multitabs Modules","type":"module","creationDate":"7-10-2014","author":"Youjoomla.com","copyright":"Youjoomla.com","authorEmail":"youjoomla@gmail.com","authorUrl":"www.youjoomla.com.com","version":"2.0.4","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">#holdthem{font-family: Arial Narrow,sans-serif;text-align:left;}#wrap1 {padding:0px 0px 4px 0px;}h1 {clear:both;font-family: Arial Narrow,sans-serif;font-size:18px;margin:0px 0px 12px 0px;padding:0px 0px 1px 10px;color:#C64934;}.wrap2 {background:#F7F7F7;}<\\/style><div id=\\"holdthem\\"><h1>Youjoomla Multitabs Modules for Joomla 1.7 and UP<\\/h1><div class=\\"wrap2\\"><a title=\\"Visit the official website!\\" href=\\"http:\\/\\/joomlatemplates.youjoomla.info\\/youscene\\"> <img style=\\"float:left;border:1px solid #CFCFCF;margin:0px 15px 4px 22px;\\" src=\\"..\\/modules\\/mod_yj_multitab\\/images\\/yj_extensions.jpg\\" border=\\"0\\" alt=\\"Logo\\" \\/><\\/a><\\/div><\\/div>\\n\\t","group":""}', '{"is_copy":"","module_template":"Default","module_pozi":"user1,user2","module_title":"Title1,Title2","auto_width":"1","ulis_width2":"100px","transtype":"1","tabs_height":"","moduleclass_sfx":"_yjmutlitab","handler":""}', '', '', 0, '0000-00-00 00:00:00', 0, 0);
INSERT INTO `#__extensions` (`extension_id`, `name`, `type`, `element`, `folder`, `client_id`, `enabled`, `access`, `protected`, `manifest_cache`, `params`, `custom_data`, `system_data`, `checked_out`, `checked_out_time`, `ordering`, `state`) VALUES
(10028, 'YJ Module Engine', 'module', 'mod_yjme', '', 0, 1, 0, 0, '{"legacy":false,"name":"YJ Module Engine","type":"module","creationDate":"7-9-2014","author":"YouJoomla.com","copyright":"","authorEmail":"youjoomla@gmail.com","authorUrl":"www.youjoomla.com","version":"1.0.11","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">body {background:#fff;}#wrap1 {padding:10px 0;margin:0 auto;display:block;overflow:hidden;width:100%;height:580px!important;background:#F6F6F6;border:1px solid #DEDEDE;}span.extitle {display:block;clear:both;font-weight:bold;font-family: Arial Narrow, sans-serif;font-size:18px;margin:10px 20px;color:#1e2325!important;text-align:center;padding:5px;background:#DEDEDE;border:5px solid #fff;text-shadow:1px 1px #fff;}.eximg {float:left;clear:right;border:5px solid #fff;margin:0 0 0 18px;}.exinfo{display:block;overflow:hidden;text-align:left;padding:5px 15px 0 0;float:right;font-size:12px;font-weight:bold;width:50%;}<\\/style>\\n\\t\\t<div id=\\"wrap1\\">\\n\\t\\t\\t<span class=\\"extitle\\">Youjoomla Module Engine for Joomla 1.6x and UP<\\/span>\\n\\t\\t\\t<a title=\\"Visit the official website!\\" href=\\"http:\\/\\/www.youjoomla.com\\" target=\\"_blank\\">\\n\\t\\t\\t\\t<img class=\\"eximg\\" src=\\"..\\/modules\\/mod_yjme\\/images\\/yj_extensions.jpg\\" border=\\"0\\" alt=\\"Logo\\" \\/>\\n\\t\\t\\t<\\/a>\\n\\t\\t\\t<div class=\\"exinfo\\">Thank you for downloading Youjoomla.com extension.\\n\\t\\t\\t\\t<br \\/>\\n\\t\\t\\t\\tTo setup your module please hover over the module parameters\\n\\t\\t\\t\\t<br \\/>\\n\\t\\t\\t\\tand follow the instructions.\\n\\t\\t\\t\\t<br \\/>\\n\\t\\t\\t\\tIf you need support please post new thread in our\\n\\t\\t\\t\\t<a href=\\"http:\\/\\/www.youjoomla.com\\/joomla_support\\/index.php\\" target=\\"_blank\\">Forum<\\/a>\\n\\t\\t\\t\\t.\\n\\t\\t\\t\\t<br \\/>\\n\\t\\t\\t\\tHave fun playing with your new extension :).\\n\\t\\t\\t\\t<br \\/>\\n\\t\\t\\t\\t<a href=\\"www.youjoomla.com\\">Youjoomla.com<\\/a>\\n\\t\\t\\t<\\/div>\\n\\t\\t<\\/div>\\n\\t","group":""}', '{"module_template":"Default","module_css":"stylesheet.css","select_source_title":"<strong>PLEASE SELECT NEWS ITEM SOURCE<\\/strong>","item_source":"1","j_news_source":"<strong>JOOMLA NEWS SOURCE SETTINGS<\\/strong>","yjcatfilter":"0","get_items":"","item":"","getspecific":"","ordering":"1","show_frontpage":"0","k2_news_source":"<strong>K2 NEWS SOURCE SETTINGS<\\/strong>","k2catfilter":"0","category_id":"","k2item":"","k2items":"","k2image_size":"M","k2ordering":"1","access_redirect":"1","nitems":"4","chars":"40","allow_tags":"","show_title":"1","show_author":"1","author_name":"1","show_img":"1","img_align":"1","img_width":"90px","img_height":"50px","show_cat_title":"1","show_date":"1","show_intro":"1","show_read":"1","moduleclass_sfx":" yjme"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10029, 'YJ Latest Tweets', 'module', 'mod_yj_twitter', '', 0, 1, 0, 0, '{"legacy":false,"name":"YJ Latest Tweets","type":"module","creationDate":"7-17-2014","author":"youjoomla","copyright":"Youjoomla","authorEmail":"youjoomla@gmail.com","authorUrl":"http:\\/\\/www.youjoomla.com","version":"2.0.7","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">#wrap1 {padding:0px 0px 4px 0px;}h1 {clear:both;font-family: Arial Narrow,sans-serif;font-size:18px;margin:0px 0px 12px 0px;padding:0px 0px 1px 10px;color:#C64934;}.wrap2 {background:#F7F7F7;}<\\/style><div id=\\"holdthem\\"><h1>YJ Latest Tweets Module v2 for Joomla<\\/h1><div class=\\"wrap2\\"><a title=\\"Visit the official website!\\" href=\\"http:\\/\\/joomlatemplates.youjoomla.info\\/youmedia\\"> <img style=\\"float:left;border:1px solid #CFCFCF;margin:0px 15px 4px 22px;\\" src=\\"..\\/modules\\/mod_yj_twitter\\/images\\/yj_extensions.jpg\\" border=\\"0\\" alt=\\"Logo\\" \\/><\\/a><\\/div><\\/div>\\n\\t","group":""}', '{"twitter_user":"","nr_article":"20","tweet_limit":"","tweet_link":"1","tweet_date":"1","tweet_follow":"1","tweet_image":"1","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"itemid"}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10036, 'System - Yjsg Framework', 'plugin', 'yjsg', 'system', 0, 1, 1, 0, '{"legacy":false,"name":"System - Yjsg Framework","type":"plugin","creationDate":"6-5-2014","author":"Youjoomla.com","copyright":"Copyright (c) since 2007 Youjoomla.com. All rights reserved.","authorEmail":"youjoomla@gmail.com","authorUrl":"www.youjoomla.com","version":"2.0.0","description":"\\n\\t\\t<style type=\\"text\\/css\\" media=\\"all\\">#wrap1 {padding:10px 0;margin:20px auto;float:left;display:block;overflow:hidden;clear:both;max-width:500px;line-height:24px;height:auto!important;text-align:center!important;background:#F6F6F6;border:1px solid #DEDEDE;-webkit-box-sizing: border-box; -moz-box-sizing: border-box;box-sizing: border-box;}.admin_t_name {display:block;clear:both;text-align:center!important;font-size:18px;-webkit-box-sizing: border-box; -moz-box-sizing: border-box;box-sizing: border-box;margin:15px!important;}#wrap1 img {display:block;margin:0 auto;text-align:center!important;float:none!important;}.exinfo{font-size:14px;padding:10px;display:block;overflow:hidden;text-align:center!important;-webkit-box-sizing: border-box; -moz-box-sizing: border-box;box-sizing: border-box;}<\\/style><div id=\\"wrap1\\"><h2 class=\\"admin_t_name\\">YJSG v2.0.0 Framework system plugin for Joomla!<\\/h2><br \\/><a href=\\"http:\\/\\/www.yjsimplegrid.com\\" target=\\"_blank\\"><span title=\\"YJSimpleGrid Joomla! Template Framework by Youjoomla.com\\"><img src=\\"..\\/plugins\\/system\\/yjsg\\/assets\\/images\\/yjsg.png\\" border=\\"0\\" title=\\"Yjsglogo\\" alt=\\"yjsglogo\\"\\/><\\/span><\\/a><br \\/><div class=\\"exinfo\\">Thank you for downloading Youjoomla.com extension. <br \\/>For fast info visit following links: <br \\/><a href=\\"http:\\/\\/yjsimplegrid.com\\/\\" target=\\"_blank\\">Homepage<\\/a> | <a href=\\"http:\\/\\/yjsimplegrid.com\\/documentation\\/\\" target=\\"_blank\\">Documentation<\\/a> | <a href=\\"http:\\/\\/www.joomlatemplates.youjoomla.info\\/eximium\\/\\" target=\\"_blank\\">Demo<\\/a><br \\/>If you need support please post new thread in our <a href=\\"http:\\/\\/www.youjoomla.com\\/joomla_support\\/index.php\\" target=\\"_blank\\">Forum<\\/a>. <br \\/>Have fun playing with your new extension :) <br \\/><a href=\\"http:\\/\\/www.youjoomla.com\\">Youjoomla.com<\\/a><\\/div><\\/div>\\n\\t","group":""}', '[]', '', '', 0, '0000-00-00 00:00:00', -1000, 0),
(10032, 'plg_editors_jce', 'plugin', 'jce', 'editors', 0, 1, 1, 0, '{"legacy":false,"name":"plg_editors_jce","type":"plugin","creationDate":"28 July 2014","author":"Ryan Demmer","copyright":"2006-2010 Ryan Demmer","authorEmail":"info@joomlacontenteditor.net","authorUrl":"http:\\/\\/www.joomlacontenteditor.net","version":"2.4.2","description":"WF_EDITOR_PLUGIN_DESC","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10033, 'plg_quickicon_jcefilebrowser', 'plugin', 'jcefilebrowser', 'quickicon', 0, 1, 1, 0, '{"legacy":false,"name":"plg_quickicon_jcefilebrowser","type":"plugin","creationDate":"28 July 2014","author":"Ryan Demmer","copyright":"Copyright (C) 2006 - 2014 Ryan Demmer. All rights reserved","authorEmail":"@@email@@","authorUrl":"www.joomalcontenteditor.net","version":"2.4.2","description":"PLG_QUICKICON_JCEFILEBROWSER_XML_DESCRIPTION","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10034, 'jce', 'component', 'com_jce', '', 1, 1, 0, 0, '{"legacy":false,"name":"JCE","type":"component","creationDate":"28 July 2014","author":"Ryan Demmer","copyright":"Copyright (C) 2006 - 2014 Ryan Demmer. All rights reserved","authorEmail":"info@joomlacontenteditor.net","authorUrl":"www.joomlacontenteditor.net","version":"2.4.2","description":"WF_ADMIN_DESC","group":""}', '{}', '', '', 0, '0000-00-00 00:00:00', 0, 0),
(10037, 'definitions', 'plugin', 'definitions', 'josetta_ext', 0, 1, 1, 0, 'false', '', '', '', 0, '0000-00-00 00:00:00', 0, -1);

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_attachments`
--

DROP TABLE IF EXISTS `#__k2_attachments`;
CREATE TABLE IF NOT EXISTS `#__k2_attachments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemID` int(11) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `titleAttribute` text NOT NULL,
  `hits` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `itemID` (`itemID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_categories`
--

DROP TABLE IF EXISTS `#__k2_categories`;
CREATE TABLE IF NOT EXISTS `#__k2_categories` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `alias` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `parent` int(11) DEFAULT '0',
  `extraFieldsGroup` int(11) NOT NULL,
  `published` smallint(6) NOT NULL DEFAULT '0',
  `access` int(11) NOT NULL DEFAULT '0',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `image` varchar(255) NOT NULL,
  `params` text NOT NULL,
  `trash` smallint(6) NOT NULL DEFAULT '0',
  `plugins` text NOT NULL,
  `language` char(7) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category` (`published`,`access`,`trash`),
  KEY `parent` (`parent`),
  KEY `ordering` (`ordering`),
  KEY `published` (`published`),
  KEY `access` (`access`),
  KEY `trash` (`trash`),
  KEY `language` (`language`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `#__k2_categories`
--

INSERT INTO `#__k2_categories` (`id`, `name`, `alias`, `description`, `parent`, `extraFieldsGroup`, `published`, `access`, `ordering`, `image`, `params`, `trash`, `plugins`, `language`) VALUES
(1, 'Categories', 'categories', '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', 0, 0, 1, 1, 1, '1.jpg', '{"inheritFrom":"0","theme":"","num_leading_items":"0","num_leading_columns":"0","leadingImgSize":"Large","num_primary_items":"4","num_primary_columns":"2","primaryImgSize":"Medium","num_secondary_items":"0","num_secondary_columns":"0","secondaryImgSize":"Small","num_links":"0","num_links_columns":"0","linksImgSize":"XSmall","catCatalogMode":"1","catFeaturedItems":"1","catOrdering":"","catPagination":"2","catPaginationResults":"1","catTitle":"0","catTitleItemCounter":"0","catDescription":"0","catImage":"0","catFeedLink":"0","catFeedIcon":"1","subCategories":"1","subCatColumns":"3","subCatOrdering":"","subCatTitle":"1","subCatTitleItemCounter":"0","subCatDescription":"1","subCatImage":"1","itemImageXS":"","itemImageS":"","itemImageM":"","itemImageL":"","itemImageXL":"","catItemTitle":"1","catItemTitleLinked":"1","catItemFeaturedNotice":"0","catItemAuthor":"1","catItemDateCreated":"1","catItemRating":"0","catItemImage":"1","catItemIntroText":"1","catItemIntroTextWordLimit":"","catItemExtraFields":"0","catItemHits":"0","catItemCategory":"1","catItemTags":"1","catItemAttachments":"0","catItemAttachmentsCounter":"0","catItemVideo":"0","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"0","catItemImageGallery":"0","catItemDateModified":"0","catItemReadMore":"1","catItemCommentsAnchor":"1","catItemK2Plugins":"1","itemDateCreated":"1","itemTitle":"1","itemFeaturedNotice":"1","itemAuthor":"1","itemFontResizer":"1","itemPrintButton":"1","itemEmailButton":"1","itemSocialButton":"1","itemVideoAnchor":"1","itemImageGalleryAnchor":"1","itemCommentsAnchor":"1","itemRating":"1","itemImage":"1","itemImgSize":"Large","itemImageMainCaption":"1","itemImageMainCredits":"1","itemIntroText":"1","itemFullText":"1","itemExtraFields":"1","itemDateModified":"1","itemHits":"1","itemCategory":"1","itemTags":"1","itemAttachments":"1","itemAttachmentsCounter":"1","itemVideo":"1","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"0","itemVideoCaption":"1","itemVideoCredits":"1","itemImageGallery":"1","itemNavigation":"1","itemComments":"1","itemTwitterButton":"1","itemFacebookButton":"1","itemGooglePlusOneButton":"1","itemAuthorBlock":"1","itemAuthorImage":"1","itemAuthorDescription":"1","itemAuthorURL":"1","itemAuthorEmail":"0","itemAuthorLatest":"1","itemAuthorLatestLimit":"5","itemRelated":"1","itemRelatedLimit":"5","itemRelatedTitle":"1","itemRelatedCategory":"0","itemRelatedImageSize":"0","itemRelatedIntrotext":"0","itemRelatedFulltext":"0","itemRelatedAuthor":"0","itemRelatedMedia":"0","itemRelatedImageGallery":"0","itemK2Plugins":"1","catMetaDesc":"","catMetaKey":"","catMetaRobots":"","catMetaAuthor":""}', 0, '', '*'),
(2, 'In Theaters', 'in-theaters', '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', 1, 0, 1, 1, 1, '2.jpg', '{"inheritFrom":"0","theme":"","num_leading_items":"4","num_leading_columns":"1","leadingImgSize":"Medium","num_primary_items":"0","num_primary_columns":"0","primaryImgSize":"Medium","num_secondary_items":"0","num_secondary_columns":"0","secondaryImgSize":"Small","num_links":"0","num_links_columns":"0","linksImgSize":"XSmall","catCatalogMode":"0","catFeaturedItems":"1","catOrdering":"","catPagination":"2","catPaginationResults":"0","catTitle":"0","catTitleItemCounter":"0","catDescription":"0","catImage":"0","catFeedLink":"0","catFeedIcon":"0","subCategories":"1","subCatColumns":"2","subCatOrdering":"","subCatTitle":"1","subCatTitleItemCounter":"1","subCatDescription":"1","subCatImage":"1","itemImageXS":"","itemImageS":"","itemImageM":"","itemImageL":"","itemImageXL":"","catItemTitle":"1","catItemTitleLinked":"1","catItemFeaturedNotice":"1","catItemAuthor":"0","catItemDateCreated":"1","catItemRating":"0","catItemImage":"1","catItemIntroText":"1","catItemIntroTextWordLimit":"","catItemExtraFields":"1","catItemHits":"0","catItemCategory":"0","catItemTags":"0","catItemAttachments":"0","catItemAttachmentsCounter":"0","catItemVideo":"0","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"0","catItemImageGallery":"0","catItemDateModified":"0","catItemReadMore":"1","catItemCommentsAnchor":"0","catItemK2Plugins":"1","itemDateCreated":"1","itemTitle":"1","itemFeaturedNotice":"1","itemAuthor":"1","itemFontResizer":"1","itemPrintButton":"1","itemEmailButton":"1","itemSocialButton":"1","itemVideoAnchor":"1","itemImageGalleryAnchor":"1","itemCommentsAnchor":"1","itemRating":"1","itemImage":"1","itemImgSize":"Large","itemImageMainCaption":"1","itemImageMainCredits":"1","itemIntroText":"1","itemFullText":"1","itemExtraFields":"1","itemDateModified":"1","itemHits":"1","itemCategory":"1","itemTags":"1","itemAttachments":"1","itemAttachmentsCounter":"1","itemVideo":"1","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"0","itemVideoCaption":"1","itemVideoCredits":"1","itemImageGallery":"1","itemNavigation":"0","itemComments":"1","itemTwitterButton":"0","itemFacebookButton":"0","itemGooglePlusOneButton":"0","itemAuthorBlock":"0","itemAuthorImage":"0","itemAuthorDescription":"0","itemAuthorURL":"0","itemAuthorEmail":"0","itemAuthorLatest":"0","itemAuthorLatestLimit":"5","itemRelated":"0","itemRelatedLimit":"5","itemRelatedTitle":"0","itemRelatedCategory":"0","itemRelatedImageSize":"0","itemRelatedIntrotext":"0","itemRelatedFulltext":"0","itemRelatedAuthor":"0","itemRelatedMedia":"0","itemRelatedImageGallery":"0","itemK2Plugins":"1","catMetaDesc":"","catMetaKey":"","catMetaRobots":"","catMetaAuthor":""}', 0, '', '*'),
(3, 'Coming Soon', 'coming-soon', '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', 1, 0, 1, 1, 2, '3.jpg', '{"inheritFrom":"2","theme":"","num_leading_items":"2","num_leading_columns":"1","leadingImgSize":"Large","num_primary_items":"4","num_primary_columns":"2","primaryImgSize":"Medium","num_secondary_items":"4","num_secondary_columns":"1","secondaryImgSize":"Small","num_links":"4","num_links_columns":"1","linksImgSize":"XSmall","catCatalogMode":"0","catFeaturedItems":"1","catOrdering":"","catPagination":"2","catPaginationResults":"1","catTitle":"1","catTitleItemCounter":"1","catDescription":"1","catImage":"1","catFeedLink":"1","catFeedIcon":"1","subCategories":"1","subCatColumns":"2","subCatOrdering":"","subCatTitle":"1","subCatTitleItemCounter":"1","subCatDescription":"1","subCatImage":"1","itemImageXS":"","itemImageS":"","itemImageM":"","itemImageL":"","itemImageXL":"","catItemTitle":"1","catItemTitleLinked":"1","catItemFeaturedNotice":"0","catItemAuthor":"1","catItemDateCreated":"1","catItemRating":"0","catItemImage":"1","catItemIntroText":"1","catItemIntroTextWordLimit":"","catItemExtraFields":"0","catItemHits":"0","catItemCategory":"1","catItemTags":"1","catItemAttachments":"0","catItemAttachmentsCounter":"0","catItemVideo":"0","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"0","catItemImageGallery":"0","catItemDateModified":"0","catItemReadMore":"1","catItemCommentsAnchor":"1","catItemK2Plugins":"1","itemDateCreated":"1","itemTitle":"1","itemFeaturedNotice":"1","itemAuthor":"1","itemFontResizer":"1","itemPrintButton":"1","itemEmailButton":"1","itemSocialButton":"1","itemVideoAnchor":"1","itemImageGalleryAnchor":"1","itemCommentsAnchor":"1","itemRating":"1","itemImage":"1","itemImgSize":"Large","itemImageMainCaption":"1","itemImageMainCredits":"1","itemIntroText":"1","itemFullText":"1","itemExtraFields":"1","itemDateModified":"1","itemHits":"1","itemCategory":"1","itemTags":"1","itemAttachments":"1","itemAttachmentsCounter":"1","itemVideo":"1","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"0","itemVideoCaption":"1","itemVideoCredits":"1","itemImageGallery":"1","itemNavigation":"1","itemComments":"1","itemTwitterButton":"1","itemFacebookButton":"1","itemGooglePlusOneButton":"1","itemAuthorBlock":"1","itemAuthorImage":"1","itemAuthorDescription":"1","itemAuthorURL":"1","itemAuthorEmail":"0","itemAuthorLatest":"1","itemAuthorLatestLimit":"5","itemRelated":"1","itemRelatedLimit":"5","itemRelatedTitle":"1","itemRelatedCategory":"0","itemRelatedImageSize":"0","itemRelatedIntrotext":"0","itemRelatedFulltext":"0","itemRelatedAuthor":"0","itemRelatedMedia":"0","itemRelatedImageGallery":"0","itemK2Plugins":"1","catMetaDesc":"","catMetaKey":"","catMetaRobots":"","catMetaAuthor":""}', 0, '', '*'),
(4, 'Category 3', 'category-3', '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', 1, 0, 1, 1, 3, '4.jpg', 'inheritFrom=2\ntheme=\nnum_leading_items=2\nnum_leading_columns=1\nleadingImgSize=Large\nnum_primary_items=4\nnum_primary_columns=2\nprimaryImgSize=Medium\nnum_secondary_items=4\nnum_secondary_columns=1\nsecondaryImgSize=Small\nnum_links=4\nnum_links_columns=1\nlinksImgSize=XSmall\ncatCatalogMode=0\ncatFeaturedItems=1\ncatOrdering=\ncatPagination=2\ncatPaginationResults=1\ncatTitle=1\ncatTitleItemCounter=1\ncatDescription=1\ncatImage=1\ncatFeedLink=1\nfeedLink=1\nsubCategories=1\nsubCatColumns=2\nsubCatOrdering=\nsubCatTitle=1\nsubCatTitleItemCounter=1\nsubCatDescription=1\nsubCatImage=1\nitemImageXS=\nitemImageS=\nitemImageM=\nitemImageL=\nitemImageXL=\ncatItemTitle=1\ncatItemTitleLinked=1\ncatItemFeaturedNotice=0\ncatItemAuthor=1\ncatItemDateCreated=1\ncatItemRating=0\ncatItemImage=1\ncatItemIntroText=1\ncatItemIntroTextWordLimit=\ncatItemExtraFields=0\ncatItemHits=0\ncatItemCategory=1\ncatItemTags=1\ncatItemAttachments=0\ncatItemAttachmentsCounter=0\ncatItemVideo=0\ncatItemVideoWidth=\ncatItemVideoHeight=\ncatItemAudioWidth=\ncatItemAudioHeight=\ncatItemVideoAutoPlay=0\ncatItemImageGallery=0\ncatItemDateModified=0\ncatItemReadMore=1\ncatItemCommentsAnchor=1\ncatItemK2Plugins=1\nitemDateCreated=1\nitemTitle=1\nitemFeaturedNotice=1\nitemAuthor=1\nitemFontResizer=1\nitemPrintButton=1\nitemEmailButton=1\nitemSocialButton=1\nitemVideoAnchor=1\nitemImageGalleryAnchor=1\nitemCommentsAnchor=1\nitemRating=1\nitemImage=1\nitemImgSize=Large\nitemImageMainCaption=1\nitemImageMainCredits=1\nitemIntroText=1\nitemFullText=1\nitemExtraFields=1\nitemDateModified=1\nitemHits=1\nitemCategory=1\nitemTags=1\nitemAttachments=1\nitemAttachmentsCounter=1\nitemVideo=1\nitemVideoWidth=\nitemVideoHeight=\nitemAudioWidth=\nitemAudioHeight=\nitemVideoAutoPlay=0\nitemVideoCaption=1\nitemVideoCredits=1\nitemImageGallery=1\nitemNavigation=1\nitemComments=1\nitemTwitterButton=1\nitemFacebookButton=1\nitemGooglePlusOneButton=1\nitemAuthorBlock=1\nitemAuthorImage=1\nitemAuthorDescription=1\nitemAuthorURL=1\nitemAuthorEmail=0\nitemAuthorLatest=1\nitemAuthorLatestLimit=5\nitemRelated=1\nitemRelatedLimit=5\nitemRelatedTitle=1\nitemRelatedCategory=0\nitemRelatedImageSize=0\nitemRelatedIntrotext=0\nitemRelatedFulltext=0\nitemRelatedAuthor=0\nitemRelatedMedia=0\nitemRelatedImageGallery=0\nitemK2Plugins=1\ncatMetaDesc=\ncatMetaKey=\ncatMetaRobots=\ncatMetaAuthor=\n\n', 1, '', ''),
(5, 'Best of 2012', 'best-of-2012', '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', 1, 0, 1, 1, 3, '5.jpg', '{"inheritFrom":"2","theme":"","num_leading_items":"2","num_leading_columns":"1","leadingImgSize":"Large","num_primary_items":"4","num_primary_columns":"2","primaryImgSize":"Medium","num_secondary_items":"4","num_secondary_columns":"1","secondaryImgSize":"Small","num_links":"4","num_links_columns":"1","linksImgSize":"XSmall","catCatalogMode":"0","catFeaturedItems":"1","catOrdering":"","catPagination":"2","catPaginationResults":"1","catTitle":"1","catTitleItemCounter":"1","catDescription":"1","catImage":"1","catFeedLink":"1","catFeedIcon":"1","subCategories":"1","subCatColumns":"2","subCatOrdering":"","subCatTitle":"1","subCatTitleItemCounter":"1","subCatDescription":"1","subCatImage":"1","itemImageXS":"","itemImageS":"","itemImageM":"","itemImageL":"","itemImageXL":"","catItemTitle":"1","catItemTitleLinked":"1","catItemFeaturedNotice":"0","catItemAuthor":"1","catItemDateCreated":"1","catItemRating":"0","catItemImage":"1","catItemIntroText":"1","catItemIntroTextWordLimit":"","catItemExtraFields":"0","catItemHits":"0","catItemCategory":"1","catItemTags":"1","catItemAttachments":"0","catItemAttachmentsCounter":"0","catItemVideo":"0","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"0","catItemImageGallery":"0","catItemDateModified":"0","catItemReadMore":"1","catItemCommentsAnchor":"1","catItemK2Plugins":"1","itemDateCreated":"1","itemTitle":"1","itemFeaturedNotice":"1","itemAuthor":"1","itemFontResizer":"1","itemPrintButton":"1","itemEmailButton":"1","itemSocialButton":"1","itemVideoAnchor":"1","itemImageGalleryAnchor":"1","itemCommentsAnchor":"1","itemRating":"1","itemImage":"1","itemImgSize":"Large","itemImageMainCaption":"1","itemImageMainCredits":"1","itemIntroText":"1","itemFullText":"1","itemExtraFields":"1","itemDateModified":"1","itemHits":"1","itemCategory":"1","itemTags":"1","itemAttachments":"1","itemAttachmentsCounter":"1","itemVideo":"1","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"0","itemVideoCaption":"1","itemVideoCredits":"1","itemImageGallery":"1","itemNavigation":"1","itemComments":"1","itemTwitterButton":"1","itemFacebookButton":"1","itemGooglePlusOneButton":"1","itemAuthorBlock":"1","itemAuthorImage":"1","itemAuthorDescription":"1","itemAuthorURL":"1","itemAuthorEmail":"0","itemAuthorLatest":"1","itemAuthorLatestLimit":"5","itemRelated":"1","itemRelatedLimit":"5","itemRelatedTitle":"1","itemRelatedCategory":"0","itemRelatedImageSize":"0","itemRelatedIntrotext":"0","itemRelatedFulltext":"0","itemRelatedAuthor":"0","itemRelatedMedia":"0","itemRelatedImageGallery":"0","itemK2Plugins":"1","catMetaDesc":"","catMetaKey":"","catMetaRobots":"","catMetaAuthor":""}', 0, '', '*');

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_comments`
--

DROP TABLE IF EXISTS `#__k2_comments`;
CREATE TABLE IF NOT EXISTS `#__k2_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `commentDate` datetime NOT NULL,
  `commentText` text NOT NULL,
  `commentEmail` varchar(255) NOT NULL,
  `commentURL` varchar(255) NOT NULL,
  `published` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `itemID` (`itemID`),
  KEY `userID` (`userID`),
  KEY `published` (`published`),
  KEY `latestComments` (`published`,`commentDate`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `#__k2_comments`
--

INSERT INTO `#__k2_comments` (`id`, `itemID`, `userID`, `userName`, `commentDate`, `commentText`, `commentEmail`, `commentURL`, `published`) VALUES
(1, 1, 0, 'mike', '2011-10-25 02:19:12', 'Curabitur lobortis, nisl rhoncus varius commodo, lorem quam luctus risus, non ultricies eros dui non arcu. Donec non libero massa, non tincidunt nunc', 'sss@ffff.com', '', 1),
(2, 4, 0, 'dino', '2011-10-25 17:06:25', 'Aliquam ornare placerat quam, ut egestas libero dignissim quis. Quisque euismod scelerisque tellus, ac elementum felis facilisis posuere.', 'ccc@f.com', '', 1),
(3, 33, 0, 'John', '2013-03-06 12:13:11', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dictum vestibulum turpis nec ullamcorper. Praesent imperdiet quam quis metus scelerisque vulputate.', 'john@gmail.com', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_extra_fields`
--

DROP TABLE IF EXISTS `#__k2_extra_fields`;
CREATE TABLE IF NOT EXISTS `#__k2_extra_fields` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` text NOT NULL,
  `type` varchar(255) NOT NULL,
  `group` int(11) NOT NULL,
  `published` tinyint(4) NOT NULL,
  `ordering` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group` (`group`),
  KEY `published` (`published`),
  KEY `ordering` (`ordering`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_extra_fields_groups`
--

DROP TABLE IF EXISTS `#__k2_extra_fields_groups`;
CREATE TABLE IF NOT EXISTS `#__k2_extra_fields_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_items`
--

DROP TABLE IF EXISTS `#__k2_items`;
CREATE TABLE IF NOT EXISTS `#__k2_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `catid` int(11) NOT NULL,
  `published` smallint(6) NOT NULL DEFAULT '0',
  `introtext` mediumtext NOT NULL,
  `fulltext` mediumtext NOT NULL,
  `video` text,
  `gallery` varchar(255) DEFAULT NULL,
  `extra_fields` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `extra_fields_search` text NOT NULL,
  `created` datetime NOT NULL,
  `created_by` int(11) NOT NULL DEFAULT '0',
  `created_by_alias` varchar(255) NOT NULL,
  `checked_out` int(10) unsigned NOT NULL,
  `checked_out_time` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `modified_by` int(11) NOT NULL DEFAULT '0',
  `publish_up` datetime NOT NULL,
  `publish_down` datetime NOT NULL,
  `trash` smallint(6) NOT NULL DEFAULT '0',
  `access` int(11) NOT NULL DEFAULT '0',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `featured` smallint(6) NOT NULL DEFAULT '0',
  `featured_ordering` int(11) NOT NULL DEFAULT '0',
  `image_caption` text NOT NULL,
  `image_credits` varchar(255) NOT NULL,
  `video_caption` text NOT NULL,
  `video_credits` varchar(255) NOT NULL,
  `hits` int(10) unsigned NOT NULL,
  `params` text NOT NULL,
  `metadesc` text NOT NULL,
  `metadata` text NOT NULL,
  `metakey` text NOT NULL,
  `plugins` text NOT NULL,
  `language` char(7) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item` (`published`,`publish_up`,`publish_down`,`trash`,`access`),
  KEY `catid` (`catid`),
  KEY `created_by` (`created_by`),
  KEY `ordering` (`ordering`),
  KEY `featured` (`featured`),
  KEY `featured_ordering` (`featured_ordering`),
  KEY `hits` (`hits`),
  KEY `created` (`created`),
  KEY `language` (`language`),
  FULLTEXT KEY `search` (`title`,`introtext`,`fulltext`,`extra_fields_search`,`image_caption`,`image_credits`,`video_caption`,`video_credits`,`metadesc`,`metakey`),
  FULLTEXT KEY `title` (`title`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `#__k2_items`
--

INSERT INTO `#__k2_items` (`id`, `title`, `alias`, `catid`, `published`, `introtext`, `fulltext`, `video`, `gallery`, `extra_fields`, `extra_fields_search`, `created`, `created_by`, `created_by_alias`, `checked_out`, `checked_out_time`, `modified`, `modified_by`, `publish_up`, `publish_down`, `trash`, `access`, `ordering`, `featured`, `featured_ordering`, `image_caption`, `image_credits`, `video_caption`, `video_credits`, `hits`, `params`, `metadesc`, `metadata`, `metakey`, `plugins`, `language`) VALUES
(1, '21 And Over', '21-and-over', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-23 19:43:17', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:57:37', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 1, 0, 0, '', '', '', '', 258, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(2, 'Snitch', 'snitch', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 13:36:53', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:50:49', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 10, 0, 0, '', '', '', '', 29, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(3, 'Dead Man Down', 'dead-man-down', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 13:36:57', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:57:28', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 11, 0, 0, '', '', '', '', 3, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(4, 'The ABCs of Death', 'the-abcs-of-death', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 13:36:57', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:51:42', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 12, 0, 0, '', '', '', '', 44, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(5, 'Jack the Giant Slayer', 'jack-the-giant-slayer', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 17:09:15', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:52:40', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 6, 0, 0, '', '', '', '', 14, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(6, 'Identity Thief', 'identity-thief', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 17:09:15', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:57:21', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 7, 0, 0, '', '', '', '', 1, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(7, 'The Last Exorcism', 'the-last-exorcism', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 17:09:15', 42, '', 0, '0000-00-00 00:00:00', '2013-03-12 19:18:46', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 8, 0, 0, '', '', '', '', 5, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(8, 'Escape From Planet Earth', 'escape-from-planet-earth', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-24 17:09:15', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:57:14', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 9, 0, 0, '', '', '', '', 9, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(9, 'Safe Haven', 'safe-haven', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:20:49', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:55:12', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 2, 0, 0, '', '', '', '', 8, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(10, 'Silver Linings Playbook', 'silver-linings-playbook', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:20:49', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:55:45', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 3, 0, 0, '', '', '', '', 5, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(11, 'A Good Day to Die Hard', 'a-good-day-to-die-hard', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:20:49', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 18:56:06', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 4, 0, 0, '', '', '', '', 3, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(12, 'Dark Skies', 'dark-skies', 2, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:20:49', 42, '', 0, '0000-00-00 00:00:00', '2013-03-08 21:06:17', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 5, 0, 0, '', '', '', '', 159, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(13, 'Oz the Great and Powerful', 'oz-the-great-and-powerful', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:21:44', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:17:03', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 1, 0, 0, '', '', '', '', 1, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(14, 'Admission', 'admission', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:21:44', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:17:50', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 4, 0, 0, '', '', '', '', 2, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(15, 'Bullet to the Head', 'bullet-to-the-head', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:21:44', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:19:17', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 7, 0, 0, '', '', '', '', 48, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(16, 'Drak Circles', 'drak-circles', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2011-10-25 00:21:44', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:18:33', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 10, 0, 0, '', '', '', '', 18, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(17, 'G.I. Joe: Retaliation', 'g-i-joe-retaliation', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 21:49:22', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 12, 0, 0, '', '', '', '', 7, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(18, 'The Place Beyond the Pines', 'the-place-beyond-the-pines', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:24:47', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 8, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(19, 'Star Trek into Darkness', 'star-trek-into-darkness', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:25:45', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 5, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*');
INSERT INTO `#__k2_items` (`id`, `title`, `alias`, `catid`, `published`, `introtext`, `fulltext`, `video`, `gallery`, `extra_fields`, `extra_fields_search`, `created`, `created_by`, `created_by_alias`, `checked_out`, `checked_out_time`, `modified`, `modified_by`, `publish_up`, `publish_down`, `trash`, `access`, `ordering`, `featured`, `featured_ordering`, `image_caption`, `image_credits`, `video_caption`, `video_credits`, `hits`, `params`, `metadesc`, `metadata`, `metakey`, `plugins`, `language`) VALUES
(20, 'Oblivion', 'oblivion', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:26:04', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 2, 0, 0, '', '', '', '', 1, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(21, 'Iron Man 3', 'iron-man-3', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:27:52', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 9, 0, 0, '', '', '', '', 5, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(22, 'Man of Steel', 'man-of-steel', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:26:52', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 11, 0, 0, '', '', '', '', 4, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(23, 'Hansel and Gretel: Witch Hunters', 'hansel-and-gretel-witch-hunters', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:49:37', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 6, 0, 0, '', '', '', '', 1, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(24, 'The Last Stand', 'the-last-stand', 3, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 19:23:10', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 19:27:37', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 3, 0, 0, '', '', '', '', 2, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(25, 'Abraham Lincoln: Vampire Hunter', 'abraham-lincoln-vampire-hunter', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:36:16', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 1, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(26, 'The Hobbit: An Unexpected Journey', 'the-hobbit-an-unexpected-journey', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:39:31', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 6, 0, 0, '', '', '', '', 8, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(27, 'The Dark Knight Rises', 'the-dark-knight-rises', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:37:30', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 3, 0, 0, '', '', '', '', 9, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(28, 'The Amazing Spider-Man', 'the-amazing-spider-man', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:37:54', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 4, 0, 0, '', '', '', '', 3, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(29, 'American Reunion', 'american-reunion', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:38:15', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 5, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(30, 'Argo', 'argo', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:43:03', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 7, 0, 0, '', '', '', '', 1, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(31, 'The Avengers', 'the-avengers', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:43:55', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 9, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(32, 'The Bourne Legacy', 'the-bourne-legacy', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-08 11:55:37', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 10, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(33, 'Battleship', 'battleship', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:45:23', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 11, 0, 0, '', '', '', '', 5, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(34, 'Wrath of the Titans', 'wrath-of-the-titans', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:45:53', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 12, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(35, 'ParaNorman', 'paranorman', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:46:23', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 8, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(36, 'Hotel Transylvania', 'hotel-transylvania', 5, 1, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-05 21:52:12', 42, '', 0, '0000-00-00 00:00:00', '2013-03-05 22:46:45', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 0, 1, 2, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*'),
(37, 'Copy of Hotel Transylvania', 'hotel-transylvania', 5, 0, '<p>Sed venenatis bibendum nisl, eget iaculis tortor imperdiet vel. In ut leo ut dui porta tincidunt. Aliquam erat volutpat. Vestibulum volutpat malesuada urna, in mollis tellus vehicula vitae. Fusce sed leo risus. Duis sagittis velit non lectus viverra cursus. Sed vel sagittis urna. Aliquam laoreet rutrum eros eu pretium. Vestibulum sit amet ullamcorper lorem. Vestibulum commodo massa a diam congue quis pharetra erat vulputate.</p>', '', NULL, NULL, '[]', '', '2013-03-06 12:29:09', 42, '', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 42, '2011-10-23 19:43:17', '0000-00-00 00:00:00', 1, 1, 2, 0, 0, '', '', '', '', 0, '{"catItemTitle":"","catItemTitleLinked":"","catItemFeaturedNotice":"","catItemAuthor":"","catItemDateCreated":"","catItemRating":"","catItemImage":"","catItemIntroText":"","catItemExtraFields":"","catItemHits":"","catItemCategory":"","catItemTags":"","catItemAttachments":"","catItemAttachmentsCounter":"","catItemVideo":"","catItemVideoWidth":"","catItemVideoHeight":"","catItemAudioWidth":"","catItemAudioHeight":"","catItemVideoAutoPlay":"","catItemImageGallery":"","catItemDateModified":"","catItemReadMore":"","catItemCommentsAnchor":"","catItemK2Plugins":"","itemDateCreated":"","itemTitle":"","itemFeaturedNotice":"","itemAuthor":"","itemFontResizer":"","itemPrintButton":"","itemEmailButton":"","itemSocialButton":"","itemVideoAnchor":"","itemImageGalleryAnchor":"","itemCommentsAnchor":"","itemRating":"","itemImage":"","itemImgSize":"","itemImageMainCaption":"","itemImageMainCredits":"","itemIntroText":"","itemFullText":"","itemExtraFields":"","itemDateModified":"","itemHits":"","itemCategory":"","itemTags":"","itemAttachments":"","itemAttachmentsCounter":"","itemVideo":"","itemVideoWidth":"","itemVideoHeight":"","itemAudioWidth":"","itemAudioHeight":"","itemVideoAutoPlay":"","itemVideoCaption":"","itemVideoCredits":"","itemImageGallery":"","itemNavigation":"","itemComments":"","itemTwitterButton":"","itemFacebookButton":"","itemGooglePlusOneButton":"","itemAuthorBlock":"","itemAuthorImage":"","itemAuthorDescription":"","itemAuthorURL":"","itemAuthorEmail":"","itemAuthorLatest":"","itemAuthorLatestLimit":"","itemRelated":"","itemRelatedLimit":"","itemRelatedTitle":"","itemRelatedCategory":"","itemRelatedImageSize":"","itemRelatedIntrotext":"","itemRelatedFulltext":"","itemRelatedAuthor":"","itemRelatedMedia":"","itemRelatedImageGallery":"","itemK2Plugins":""}', '', 'robots=\nauthor=', '', '', '*');

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_rating`
--

DROP TABLE IF EXISTS `#__k2_rating`;
CREATE TABLE IF NOT EXISTS `#__k2_rating` (
  `itemID` int(11) NOT NULL DEFAULT '0',
  `rating_sum` int(11) unsigned NOT NULL DEFAULT '0',
  `rating_count` int(11) unsigned NOT NULL DEFAULT '0',
  `lastip` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`itemID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `#__k2_rating`
--

INSERT INTO `#__k2_rating` (`itemID`, `rating_sum`, `rating_count`, `lastip`) VALUES
(6, 5, 1, '127.0.0.1'),
(2, 5, 1, '127.0.0.1'),
(1, 5, 1, '127.0.0.1'),
(4, 5, 1, '127.0.0.1');

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_tags`
--

DROP TABLE IF EXISTS `#__k2_tags`;
CREATE TABLE IF NOT EXISTS `#__k2_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `published` smallint(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `published` (`published`),
  FULLTEXT KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `#__k2_tags`
--

INSERT INTO `#__k2_tags` (`id`, `name`, `published`) VALUES
(1, 'tag1', 1),
(2, 'tag2', 1),
(3, 'tag3', 1),
(4, 'tag4', 1);

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_tags_xref`
--

DROP TABLE IF EXISTS `#__k2_tags_xref`;
CREATE TABLE IF NOT EXISTS `#__k2_tags_xref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagID` int(11) NOT NULL,
  `itemID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tagID` (`tagID`),
  KEY `itemID` (`itemID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=461 ;

--
-- Dumping data for table `#__k2_tags_xref`
--

INSERT INTO `#__k2_tags_xref` (`id`, `tagID`, `itemID`) VALUES
(232, 4, 1),
(231, 3, 1),
(230, 2, 1),
(229, 1, 1),
(176, 4, 2),
(175, 3, 2),
(174, 2, 2),
(173, 1, 2),
(228, 4, 3),
(227, 3, 3),
(226, 2, 3),
(225, 1, 3),
(184, 4, 4),
(183, 3, 4),
(182, 2, 4),
(181, 1, 4),
(188, 4, 5),
(187, 3, 5),
(186, 2, 5),
(185, 1, 5),
(224, 4, 6),
(223, 3, 6),
(222, 2, 6),
(221, 1, 6),
(460, 4, 7),
(459, 3, 7),
(458, 2, 7),
(457, 1, 7),
(220, 4, 8),
(219, 3, 8),
(218, 2, 8),
(217, 1, 8),
(204, 4, 9),
(203, 3, 9),
(202, 2, 9),
(201, 1, 9),
(208, 4, 10),
(207, 3, 10),
(206, 2, 10),
(205, 1, 10),
(212, 4, 11),
(211, 3, 11),
(210, 2, 11),
(209, 1, 11),
(456, 4, 12),
(455, 3, 12),
(454, 2, 12),
(453, 1, 12),
(236, 4, 13),
(235, 3, 13),
(234, 2, 13),
(233, 1, 13),
(240, 4, 14),
(239, 3, 14),
(238, 2, 14),
(237, 1, 14),
(252, 4, 15),
(251, 3, 15),
(250, 2, 15),
(249, 1, 15),
(248, 4, 16),
(247, 3, 16),
(246, 2, 16),
(245, 1, 16),
(324, 1, 17),
(323, 2, 17),
(322, 3, 17),
(321, 4, 17),
(292, 1, 18),
(291, 2, 18),
(290, 3, 18),
(289, 4, 18),
(296, 1, 19),
(295, 2, 19),
(294, 3, 19),
(293, 4, 19),
(300, 1, 20),
(299, 2, 20),
(298, 3, 20),
(297, 4, 20),
(320, 1, 21),
(319, 2, 21),
(318, 3, 21),
(317, 4, 21),
(308, 1, 22),
(307, 2, 22),
(306, 3, 22),
(305, 4, 22),
(436, 1, 23),
(435, 2, 23),
(434, 3, 23),
(433, 4, 23),
(316, 1, 24),
(315, 2, 24),
(314, 3, 24),
(313, 4, 24),
(376, 4, 25),
(375, 3, 25),
(374, 2, 25),
(373, 1, 25),
(396, 4, 26),
(395, 3, 26),
(394, 2, 26),
(393, 1, 26),
(384, 4, 27),
(383, 3, 27),
(382, 2, 27),
(381, 1, 27),
(388, 4, 28),
(387, 3, 28),
(386, 2, 28),
(385, 1, 28),
(392, 4, 29),
(391, 3, 29),
(390, 2, 29),
(389, 1, 29),
(400, 4, 30),
(399, 3, 30),
(398, 2, 30),
(397, 1, 30),
(404, 4, 31),
(403, 3, 31),
(402, 2, 31),
(401, 1, 31),
(452, 4, 32),
(451, 3, 32),
(450, 2, 32),
(449, 1, 32),
(412, 1, 33),
(411, 2, 33),
(410, 3, 33),
(409, 4, 33),
(416, 1, 34),
(415, 2, 34),
(414, 3, 34),
(413, 4, 34),
(420, 1, 35),
(419, 2, 35),
(418, 3, 35),
(417, 4, 35),
(424, 1, 36),
(423, 2, 36),
(422, 3, 36),
(421, 4, 36),
(437, 1, 37),
(438, 2, 37),
(439, 3, 37),
(440, 4, 37);

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_users`
--

DROP TABLE IF EXISTS `#__k2_users`;
CREATE TABLE IF NOT EXISTS `#__k2_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `gender` enum('m','f') NOT NULL DEFAULT 'm',
  `description` text NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `group` int(11) NOT NULL DEFAULT '0',
  `plugins` text NOT NULL,
  `ip` varchar(15) NOT NULL,
  `hostname` varchar(255) NOT NULL,
  `notes` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userID` (`userID`),
  KEY `group` (`group`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `#__k2_users`
--

INSERT INTO `#__k2_users` (`id`, `userID`, `userName`, `gender`, `description`, `image`, `url`, `group`, `plugins`, `ip`, `hostname`, `notes`) VALUES
(1, 42, 'Super User', 'm', 'Aliquam erat volutpat. Proin euismod laoreet feugiat. In pharetra nulla ut ipsum sodales non tempus quam condimentum. Duis consequat sollicitudin sapien, sit amet ultricies est elementum ac. Aliquam erat volutpat. Phasellus in mollis augue.', '1.jpg', 'http://www.youjoomla.com', 1, '', '127.0.0.1', 'activate.adobe.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `#__k2_user_groups`
--

DROP TABLE IF EXISTS `#__k2_user_groups`;
CREATE TABLE IF NOT EXISTS `#__k2_user_groups` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `permissions` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `#__k2_user_groups`
--

INSERT INTO `#__k2_user_groups` (`id`, `name`, `permissions`) VALUES
(1, 'Registered', '{"comment":"1","frontEdit":"0","add":"0","editOwn":"0","editAll":"0","publish":"0","inheritance":0,"categories":"all"}'),
(2, 'Site Owner', '{"comment":"1","frontEdit":"1","add":"1","editOwn":"1","editAll":"1","publish":"1","inheritance":1,"categories":"all"}');

-- --------------------------------------------------------

--
-- Table structure for table `#__languages`
--

DROP TABLE IF EXISTS `#__languages`;
CREATE TABLE IF NOT EXISTS `#__languages` (
  `lang_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lang_code` char(7) NOT NULL,
  `title` varchar(50) NOT NULL,
  `title_native` varchar(50) NOT NULL,
  `sef` varchar(50) NOT NULL,
  `image` varchar(50) NOT NULL,
  `description` varchar(512) NOT NULL,
  `metakey` text NOT NULL,
  `metadesc` text NOT NULL,
  `sitename` varchar(1024) NOT NULL DEFAULT '',
  `published` int(11) NOT NULL DEFAULT '0',
  `access` int(10) unsigned NOT NULL DEFAULT '0',
  `ordering` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`lang_id`),
  UNIQUE KEY `idx_sef` (`sef`),
  UNIQUE KEY `idx_image` (`image`),
  UNIQUE KEY `idx_langcode` (`lang_code`),
  KEY `idx_ordering` (`ordering`),
  KEY `idx_access` (`access`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `#__languages`
--

INSERT INTO `#__languages` (`lang_id`, `lang_code`, `title`, `title_native`, `sef`, `image`, `description`, `metakey`, `metadesc`, `sitename`, `published`, `access`, `ordering`) VALUES
(1, 'en-GB', 'English (UK)', 'English (UK)', 'en', 'en', '', '', '', '', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `#__menu`
--

DROP TABLE IF EXISTS `#__menu`;
CREATE TABLE IF NOT EXISTS `#__menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menutype` varchar(24) NOT NULL COMMENT 'The type of menu this item belongs to. FK to #__menu_types.menutype',
  `title` varchar(255) NOT NULL COMMENT 'The display title of the menu item.',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'The SEF alias of the menu item.',
  `note` varchar(255) NOT NULL DEFAULT '',
  `path` varchar(1024) NOT NULL COMMENT 'The computed path of the menu item based on the alias field.',
  `link` varchar(1024) NOT NULL COMMENT 'The actually link the menu item refers to.',
  `type` varchar(16) NOT NULL COMMENT 'The type of link: Component, URL, Alias, Separator',
  `published` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'The published state of the menu link.',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'The parent menu item in the menu tree.',
  `level` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'The relative level in the tree.',
  `component_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'FK to #__extensions.id',
  `ordering` int(11) NOT NULL DEFAULT '0' COMMENT 'The relative ordering of the menu item in the tree.',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'FK to #__users.id',
  `checked_out_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'The time the menu item was checked out.',
  `browserNav` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'The click behaviour of the link.',
  `access` int(10) unsigned DEFAULT NULL,
  `img` varchar(255) NOT NULL COMMENT 'The image of the menu item.',
  `template_style_id` int(10) unsigned NOT NULL DEFAULT '0',
  `params` text NOT NULL COMMENT 'JSON encoded data for the menu item.',
  `lft` int(11) NOT NULL DEFAULT '0' COMMENT 'Nested set lft.',
  `rgt` int(11) NOT NULL DEFAULT '0' COMMENT 'Nested set rgt.',
  `home` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Indicates if this menu item is the home or default page.',
  `language` char(7) NOT NULL DEFAULT '',
  `client_id` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_client_id_parent_id_alias_language` (`client_id`,`parent_id`,`alias`,`language`),
  KEY `idx_componentid` (`component_id`,`menutype`,`published`,`access`),
  KEY `idx_menutype` (`menutype`),
  KEY `idx_left_right` (`lft`,`rgt`),
  KEY `idx_alias` (`alias`),
  KEY `idx_path` (`path`(333)),
  KEY `idx_language` (`language`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=309 ;

--
-- Dumping data for table `#__menu`
--

INSERT INTO `#__menu` (`id`, `menutype`, `title`, `alias`, `note`, `path`, `link`, `type`, `published`, `parent_id`, `level`, `component_id`, `ordering`, `checked_out`, `checked_out_time`, `browserNav`, `access`, `img`, `template_style_id`, `params`, `lft`, `rgt`, `home`, `language`, `client_id`) VALUES
(1, '', 'Menu_Item_Root', 'root', '', '', '', '', 1, 0, 0, 0, 0, 0, '0000-00-00 00:00:00', 0, 0, '', 0, '', 0, 191, 0, '*', 0),
(2, 'menu', 'com_banners', 'Banners', '', 'Banners', 'index.php?option=com_banners', 'component', 0, 1, 1, 4, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:banners', 0, '', 1, 10, 0, '*', 1),
(3, 'menu', 'com_banners', 'Banners', '', 'Banners/Banners', 'index.php?option=com_banners', 'component', 0, 2, 2, 4, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:banners', 0, '', 2, 3, 0, '*', 1),
(4, 'menu', 'com_banners_categories', 'Categories', '', 'Banners/Categories', 'index.php?option=com_categories&extension=com_banners', 'component', 0, 2, 2, 6, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:banners-cat', 0, '', 4, 5, 0, '*', 1),
(5, 'menu', 'com_banners_clients', 'Clients', '', 'Banners/Clients', 'index.php?option=com_banners&view=clients', 'component', 0, 2, 2, 4, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:banners-clients', 0, '', 6, 7, 0, '*', 1),
(6, 'menu', 'com_banners_tracks', 'Tracks', '', 'Banners/Tracks', 'index.php?option=com_banners&view=tracks', 'component', 0, 2, 2, 4, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:banners-tracks', 0, '', 8, 9, 0, '*', 1),
(7, 'menu', 'com_contact', 'Contacts', '', 'Contacts', 'index.php?option=com_contact', 'component', 0, 1, 1, 8, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:contact', 0, '', 117, 122, 0, '*', 1),
(8, 'menu', 'com_contact', 'Contacts', '', 'Contacts/Contacts', 'index.php?option=com_contact', 'component', 0, 7, 2, 8, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:contact', 0, '', 118, 119, 0, '*', 1),
(9, 'menu', 'com_contact_categories', 'Categories', '', 'Contacts/Categories', 'index.php?option=com_categories&extension=com_contact', 'component', 0, 7, 2, 6, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:contact-cat', 0, '', 120, 121, 0, '*', 1),
(10, 'menu', 'com_messages', 'Messaging', '', 'Messaging', 'index.php?option=com_messages', 'component', 0, 1, 1, 15, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:messages', 0, '', 123, 128, 0, '*', 1),
(11, 'menu', 'com_messages_add', 'New Private Message', '', 'Messaging/New Private Message', 'index.php?option=com_messages&task=message.add', 'component', 0, 10, 2, 15, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:messages-add', 0, '', 124, 125, 0, '*', 1),
(12, 'menu', 'com_messages_read', 'Read Private Message', '', 'Messaging/Read Private Message', 'index.php?option=com_messages', 'component', 0, 10, 2, 15, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:messages-read', 0, '', 126, 127, 0, '*', 1),
(13, 'menu', 'com_newsfeeds', 'News Feeds', '', 'News Feeds', 'index.php?option=com_newsfeeds', 'component', 0, 1, 1, 17, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:newsfeeds', 0, '', 129, 134, 0, '*', 1),
(14, 'menu', 'com_newsfeeds_feeds', 'Feeds', '', 'News Feeds/Feeds', 'index.php?option=com_newsfeeds', 'component', 0, 13, 2, 17, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:newsfeeds', 0, '', 130, 131, 0, '*', 1),
(15, 'menu', 'com_newsfeeds_categories', 'Categories', '', 'News Feeds/Categories', 'index.php?option=com_categories&extension=com_newsfeeds', 'component', 0, 13, 2, 6, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:newsfeeds-cat', 0, '', 132, 133, 0, '*', 1),
(16, 'menu', 'com_redirect', 'Redirect', '', 'Redirect', 'index.php?option=com_redirect', 'component', 0, 1, 1, 24, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:redirect', 0, '', 143, 144, 0, '*', 1),
(17, 'menu', 'com_search', 'Search', '', 'Search', 'index.php?option=com_search', 'component', 0, 1, 1, 19, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:search', 0, '', 135, 136, 0, '*', 1),
(18, 'menu', 'com_weblinks', 'Weblinks', '', 'Weblinks', 'index.php?option=com_weblinks', 'component', 0, 1, 1, 21, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:weblinks', 0, '', 137, 142, 0, '*', 1),
(19, 'menu', 'com_weblinks_links', 'Links', '', 'Weblinks/Links', 'index.php?option=com_weblinks', 'component', 0, 18, 2, 21, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:weblinks', 0, '', 138, 139, 0, '*', 1),
(20, 'menu', 'com_weblinks_categories', 'Categories', '', 'Weblinks/Categories', 'index.php?option=com_categories&extension=com_weblinks', 'component', 0, 18, 2, 6, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:weblinks-cat', 0, '', 140, 141, 0, '*', 1),
(101, 'mainmenu', 'Home', 'home', '', 'home', 'index.php?option=com_content&view=featured', 'component', 1, 1, 1, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"featured_categories":[""],"layout_type":"blog","num_leading_articles":"0","num_intro_articles":"0","num_columns":"0","num_links":"0","multi_column_order":"1","orderby_pri":"","orderby_sec":"front","order_date":"created","show_pagination":"0","show_pagination_results":"0","show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"0","show_item_navigation":"","show_vote":"","show_readmore":"","show_readmore_title":"0","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"0","show_noauth":"","show_feed_link":"1","feed_summary":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"HD Channel - Joomla! Entertainment Template","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"YJSG Demo","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 11, 12, 1, '*', 0),
(308, 'main', 'K2_INFORMATION', 'k2-information', '', 'com-k2/k2-information', 'index.php?option=com_k2&view=info', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 188, 189, 0, '', 1),
(104, 'mainmenu', 'Grids Preview', 'grids-preview', '', 'grids-preview', 'index.php?option=com_content&view=article&id=8', 'component', 1, 1, 1, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"0","show_vote":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","urls_position":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Module Grids","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 155, 156, 0, '*', 0),
(106, 'mainmenu', 'User Pages', 'user-pages', '', 'jstuff/user-pages', '#', 'url', 1, 123, 2, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 78, 91, 0, '*', 0),
(107, 'mainmenu', 'Login Form', 'login-form', '', 'jstuff/user-pages/login-form', 'index.php?option=com_users&view=login', 'component', 1, 106, 3, 25, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"login_redirect_url":"","logindescription_show":"1","login_description":"","login_image":"","logout_redirect_url":"","logoutdescription_show":"1","logout_description":"Thank you for visiting.","logout_image":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"Joomla! User Login ","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Login Form demo","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 79, 80, 0, '*', 0),
(108, 'mainmenu', 'User Profile ', 'user-profile', '', 'jstuff/user-pages/user-profile', 'index.php?option=com_users&view=profile', 'component', 1, 106, 3, 25, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"User Profile ","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"User Profile Demo","yj_menu_custom_class":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 81, 82, 0, '*', 0),
(109, 'mainmenu', 'User Regisrtation', 'user-regisrtation', '', 'jstuff/user-pages/user-regisrtation', 'index.php?option=com_users&view=registration', 'component', 1, 106, 3, 25, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"Registration","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"User Regisrtation Demo","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 83, 84, 0, '*', 0),
(110, 'mainmenu', 'Username Reminder', 'username-reminder', '', 'jstuff/user-pages/username-reminder', 'index.php?option=com_users&view=remind', 'component', 1, 106, 3, 25, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"Forgot your Username? ","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Username Reminder Demo","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 85, 86, 0, '*', 0),
(111, 'mainmenu', 'Password reset', 'password-reset', '', 'jstuff/user-pages/password-reset', 'index.php?option=com_users&view=reset', 'component', 1, 106, 3, 25, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"Forgot your password?","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Password reset demo","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 87, 88, 0, '*', 0),
(112, 'mainmenu', 'Features', 'features', '', 'features', '#', 'url', 1, 1, 1, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"Template Features","yj_menu_custom_class":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 13, 32, 0, '*', 0),
(113, 'mainmenu', '404 page', '404-page', '', 'jstuff/user-pages/404-page', 'index.php?Itemid=8888', 'url', 1, 106, 3, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"404 page demo","yj_menu_custom_class":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 89, 90, 0, '*', 0),
(123, 'mainmenu', 'Jstuff', 'jstuff', '', 'jstuff', '#', 'url', 1, 1, 1, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"JDefaults","yj_menu_custom_class":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 77, 112, 0, '*', 0),
(125, 'mainmenu', 'Joomla Tutorials', 'joomla-tutorials', '', 'jstuff/joomla-tutorials', 'http://www.youjoomla.com/joomla-installation-tutorials.html', 'url', 1, 123, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 110, 111, 0, '*', 0),
(129, 'mainmenu', 'Content Views', 'content-views', '', 'jstuff/content-views', 'index.php?option=com_content&view=category&id=7', 'component', 1, 123, 2, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_category_title":"1","show_description":"","show_description_image":"","maxLevel":"","show_empty_categories":"","show_no_articles":"","show_subcat_desc":"","show_cat_num_articles":"","page_subheading":"","show_pagination_limit":"","filter_field":"","show_headings":"","list_show_date":"","date_format":"","list_show_hits":"","list_show_author":"","orderby_pri":"","orderby_sec":"","order_date":"","show_pagination":"","show_pagination_results":"","display_num":"10","show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_vote":"","show_readmore":"","show_readmore_title":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","show_feed_link":"","feed_summary":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0}', 92, 101, 0, '*', 0),
(132, 'mainmenu', 'Contacts', 'contacts', '', 'jstuff/contacts', 'index.php?option=com_contact&view=categories&id=0', 'component', 1, 123, 2, 8, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_base_description":"","categories_description":"","maxLevelcat":"","show_empty_categories_cat":"","show_subcat_desc_cat":"","show_cat_items_cat":"","show_category_title":"0","show_description":"","show_description_image":"","maxLevel":"","show_empty_categories":"","show_subcat_desc":"","show_cat_items":"","show_pagination_limit":"","show_headings":"","show_position_headings":"","show_email_headings":"","show_telephone_headings":"","show_mobile_headings":"","show_fax_headings":"","show_suburb_headings":"","show_state_headings":"","show_country_headings":"","show_pagination":"","show_pagination_results":"","presentation_style":"","show_contact_category":"","show_contact_list":"","show_name":"","show_position":"","show_email":"","show_street_address":"","show_suburb":"","show_state":"","show_postcode":"","show_country":"","show_telephone":"","show_mobile":"","show_fax":"","show_webpage":"","show_misc":"","show_image":"","allow_vcard":"","show_articles":"","show_links":"","linka_name":"","linkb_name":"","linkc_name":"","linkd_name":"","linke_name":"","show_email_form":"","show_email_copy":"","banned_email":"","banned_subject":"","banned_text":"","validate_session":"","custom_reply":"","redirect":"","show_feed_link":"","feed_summary":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"Site Contact","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0}', 104, 105, 0, '*', 0),
(131, 'mainmenu', 'Web links', 'web-links', '', 'jstuff/web-links', 'index.php?option=com_weblinks&view=categories&id=0', 'component', 1, 123, 2, 21, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_base_description":"","categories_description":"","maxLevelcat":"-1","show_empty_categories_cat":"","show_subcat_desc_cat":"","show_cat_num_links_cat":"","show_category_title":"0","show_description":"","show_description_image":"","maxLevel":"","show_empty_categories":"","show_subcat_desc":"","show_cat_num_links":"","show_pagination_limit":"1","show_headings":"1","show_link_description":"1","show_link_hits":"1","show_pagination":"1","show_pagination_results":"1","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0}', 102, 103, 0, '*', 0),
(133, 'mainmenu', 'News feeds', 'news-feeds', '', 'jstuff/news-feeds', 'index.php?option=com_newsfeeds&view=categories&id=0', 'component', 1, 123, 2, 17, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_base_description":"","categories_description":"","maxLevelcat":"","show_empty_categories_cat":"","show_subcat_desc_cat":"","show_cat_items_cat":"","show_category_title":"0","show_description":"","show_description_image":"","maxLevel":"","show_empty_categories":"","show_subcat_desc":"","show_cat_items":"","show_pagination_limit":"","show_headings":"","show_articles":"","show_link":"","show_pagination":"","show_pagination_results":"","show_feed_image":"","show_feed_description":"","show_item_description":"","feed_character_count":"0","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"Newsfeeds","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 106, 107, 0, '*', 0),
(134, 'mainmenu', 'Category Blog View', 'category-blog-view', '', 'jstuff/content-views/category-blog-view', 'index.php?option=com_content&view=category&layout=blog&id=2', 'component', 1, 129, 3, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"layout_type":"blog","show_category_title":"","show_description":"","show_description_image":"","maxLevel":"","show_empty_categories":"","show_no_articles":"","show_subcat_desc":"","show_cat_num_articles":"","page_subheading":"","num_leading_articles":"1","num_intro_articles":"4","num_columns":"2","num_links":"5","multi_column_order":"1","show_subcategory_content":"","orderby_pri":"","orderby_sec":"order","order_date":"","show_pagination":"","show_pagination_results":"","show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_vote":"","show_readmore":"","show_readmore_title":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","show_feed_link":"","feed_summary":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 93, 94, 0, '*', 0),
(135, 'mainmenu', 'Category List View', 'category-list-view', '', 'jstuff/content-views/category-list-view', 'index.php?option=com_content&view=category&id=2', 'component', 1, 129, 3, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_category_title":"1","show_description":"1","show_description_image":"1","maxLevel":"","show_empty_categories":"","show_no_articles":"","show_subcat_desc":"","show_cat_num_articles":"","page_subheading":"","show_pagination_limit":"1","filter_field":"title","show_headings":"1","list_show_date":"created","date_format":"","list_show_hits":"1","list_show_author":"1","orderby_pri":"","orderby_sec":"front","order_date":"","show_pagination":"","show_pagination_results":"","display_num":"10","show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_vote":"","show_readmore":"","show_readmore_title":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","show_feed_link":"","feed_summary":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 95, 96, 0, '*', 0),
(136, 'mainmenu', 'Categories View', 'categories-view', '', 'jstuff/content-views/categories-view', 'index.php?option=com_content&view=categories&id=0', 'component', 1, 129, 3, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_base_description":"","categories_description":"","maxLevelcat":"","show_empty_categories_cat":"","show_subcat_desc_cat":"0","show_cat_num_articles_cat":"","show_category_title":"","show_description":"","show_description_image":"","maxLevel":"","show_empty_categories":"","show_no_articles":"","show_subcat_desc":"","show_cat_num_articles":"","num_leading_articles":"1","num_intro_articles":"4","num_columns":"2","num_links":"4","multi_column_order":"","show_subcategory_content":"","orderby_pri":"","orderby_sec":"","order_date":"","show_pagination":"","show_pagination_results":"","show_pagination_limit":"","filter_field":"","show_headings":"","list_show_date":"","date_format":"","list_show_hits":"","list_show_author":"","display_num":"10","show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_vote":"","show_readmore":"","show_readmore_title":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","show_feed_link":"","feed_summary":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":1,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 97, 98, 0, '*', 0),
(137, 'mainmenu', 'Archived View', 'archived-view', '', 'jstuff/content-views/archived-view', 'index.php?option=com_content&view=archive', 'component', 1, 129, 3, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"orderby_sec":"alpha","order_date":"created","display_num":"5","filter_field":"hide","introtext_limit":"100","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_title":"","link_titles":"","show_intro":"","show_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_hits":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0}', 99, 100, 0, '*', 0),
(138, 'mainmenu', 'Menu Types', 'menu-types', '', 'features/menu-types', 'index.php?option=com_content&view=article&id=8', 'component', 1, 112, 2, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"0","show_vote":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Custom modules","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 14, 25, 0, '*', 0),
(139, 'mainmenu', 'YJ Mega CSS Dropdown', 'yj-mega-css-dropdown', '', 'features/menu-types/yj-mega-css-dropdown', '?change_menu=1', 'url', 1, 138, 3, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1}', 15, 16, 0, '*', 0),
(140, 'mainmenu', 'YJ Mega Smooth Dropdown', 'yj-mega-smooth-dropdown', '', 'features/menu-types/yj-mega-smooth-dropdown', '?change_menu=2', 'url', 1, 138, 3, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1}', 17, 18, 0, '*', 0),
(141, 'mainmenu', 'YJ Mega CSS Dropline', 'yj-mega-css-dropline', '', 'features/menu-types/yj-mega-css-dropline', '?change_menu=3', 'url', 1, 138, 3, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1}', 19, 20, 0, '*', 0),
(142, 'mainmenu', 'YJ Mega Smooth Dropline', 'yj-mega-smooth-dropline', '', 'features/menu-types/yj-mega-smooth-dropline', '?change_menu=4', 'url', 1, 138, 3, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1}', 21, 22, 0, '*', 0),
(143, 'mainmenu', 'Joomla Split Menu', 'joomla-split-menu', '', 'features/menu-types/joomla-split-menu', 'index.php?option=com_content&view=article&id=8&Itemid=138&change_menu=5', 'url', 1, 138, 3, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1}', 23, 24, 0, '*', 0),
(144, 'mainmenu', 'Typography', 'typography', '', 'features/typography', 'index.php?option=com_content&view=article&id=12', 'component', 1, 112, 2, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_title":"","link_titles":"","show_intro":"","show_category":"0","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"0","link_author":"","show_create_date":"0","show_modify_date":"0","show_publish_date":"0","show_item_navigation":"0","show_vote":"0","show_icons":"0","show_print_icon":"0","show_email_icon":"0","show_hits":"0","show_noauth":"","urls_position":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_icon_prefix":"","yj_menu_icon_suffix":"","yj_menu_sub_title":"Style your text","yj_hide_indicator":"0","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 26, 27, 0, '*', 0),
(145, 'mainmenu', 'Styles', 'template-styles', '', 'template-styles', '#', 'url', 1, 1, 1, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"CSS Styles","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 33, 42, 0, '*', 0),
(236, 'mainmenu', 'Module Styles', 'module-styles', '', 'features/module-styles', 'index.php?option=com_content&view=article&id=15', 'component', 1, 112, 2, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"0","show_vote":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Custom modules","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 30, 31, 0, '*', 0),
(146, 'mainmenu', 'Red', '2013-03-12-17-42-05', '', 'template-styles/2013-03-12-17-42-05', '?change_css=red', 'url', 1, 145, 2, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 34, 35, 0, '*', 0),
(147, 'mainmenu', 'Blue', '2013-03-12-17-42-13', '', 'template-styles/2013-03-12-17-42-13', '?change_css=blue', 'url', 1, 145, 2, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 36, 37, 0, '*', 0),
(148, 'mainmenu', 'Green', '2013-03-12-17-42-21', '', 'template-styles/2013-03-12-17-42-21', '?change_css=green', 'url', 1, 145, 2, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 38, 39, 0, '*', 0),
(149, 'mainmenu', 'Module Positions', 'module-positions', '', 'features/module-positions', 'index.php?option=com_content&view=article&id=13', 'component', 1, 112, 2, 22, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_title":"","link_titles":"","show_intro":"","show_category":"","link_category":"","show_parent_category":"","link_parent_category":"","show_author":"","link_author":"","show_create_date":"","show_modify_date":"","show_publish_date":"","show_item_navigation":"","show_vote":"","show_icons":"","show_print_icon":"","show_email_icon":"","show_hits":"","show_noauth":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"Over 50 positions!","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 28, 29, 0, '*', 0),
(156, 'footer-menu', 'YJSimpleGrid Features', 'yjsimplegrid-features', '', 'yjsimplegrid-features', 'http://www.yjsimplegrid.com', 'url', 1, 1, 1, 22, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 145, 146, 0, '*', 0),
(157, 'footer-menu', 'Joomla! News', 'joomla-news', '', 'joomla-news', 'index.php?option=com_newsfeeds&view=newsfeed&id=2', 'component', 1, 1, 1, 17, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"show_feed_image":"","show_feed_description":"","show_item_description":"","feed_character_count":"0","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0}', 147, 148, 0, '*', 0),
(307, 'main', 'K2_MEDIA_MANAGER', 'k2-media-manager', '', 'com-k2/k2-media-manager', 'index.php?option=com_k2&view=media', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 186, 187, 0, '', 1),
(306, 'main', 'K2_EXTRA_FIELD_GROUPS', 'k2-extra-field-groups', '', 'com-k2/k2-extra-field-groups', 'index.php?option=com_k2&view=extrafieldsgroups', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 184, 185, 0, '', 1),
(305, 'main', 'K2_EXTRA_FIELDS', 'k2-extra-fields', '', 'com-k2/k2-extra-fields', 'index.php?option=com_k2&view=extrafields', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 182, 183, 0, '', 1),
(304, 'main', 'K2_USER_GROUPS', 'k2-user-groups', '', 'com-k2/k2-user-groups', 'index.php?option=com_k2&view=usergroups', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 180, 181, 0, '', 1),
(303, 'main', 'K2_USERS', 'k2-users', '', 'com-k2/k2-users', 'index.php?option=com_k2&view=users', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 178, 179, 0, '', 1),
(302, 'main', 'K2_COMMENTS', 'k2-comments', '', 'com-k2/k2-comments', 'index.php?option=com_k2&view=comments', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 176, 177, 0, '', 1),
(301, 'main', 'K2_TAGS', 'k2-tags', '', 'com-k2/k2-tags', 'index.php?option=com_k2&view=tags', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 174, 175, 0, '', 1),
(300, 'main', 'K2_CATEGORIES', 'k2-categories', '', 'com-k2/k2-categories', 'index.php?option=com_k2&view=categories', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 172, 173, 0, '', 1),
(299, 'main', 'K2_ITEMS', 'k2-items', '', 'com-k2/k2-items', 'index.php?option=com_k2&view=items', 'component', 0, 298, 2, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, 'class:component', 0, '', 170, 171, 0, '', 1),
(223, 'mainmenu', 'K2', 'k2', '', 'extras/k2', '#', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 44, 61, 0, '*', 0),
(224, 'mainmenu', 'K2 Channel', 'k2-channel', '', 'extras/k2/k2-channel', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=1', 'component', 1, 223, 3, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["1"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 45, 52, 0, '*', 0),
(225, 'mainmenu', 'In Theaters', 'in-theaters', '', 'extras/k2/k2-channel/in-theaters', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=2', 'component', 1, 224, 4, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["2"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 46, 47, 0, '*', 0),
(226, 'mainmenu', 'Coming Soon', 'coming-soon', '', 'extras/k2/k2-channel/coming-soon', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=3', 'component', 1, 224, 4, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["3"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 48, 49, 0, '*', 0),
(227, 'mainmenu', 'Best of 2012', 'best-of-2012', '', 'extras/k2/k2-channel/best-of-2012', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=5', 'component', 1, 224, 4, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["5"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 50, 51, 0, '*', 0),
(229, 'mainmenu', 'K2 Item', 'k2-item', '', 'extras/k2/k2-item', 'index.php?option=com_k2&view=item&layout=item&id=1', 'component', 1, 223, 3, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"K2 Item","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 53, 54, 0, '*', 0),
(230, 'mainmenu', 'K2 Tag', 'k2-tag', '', 'extras/k2/k2-tag', 'index.php?option=com_k2&view=itemlist&layout=tag&tag=tag1&task=tag', 'component', 1, 223, 3, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"theme":"default","categoriesFilter":["1","2","3","4","5"],"tagOrdering":"order","feedLink":"0","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 55, 56, 0, '*', 0),
(231, 'mainmenu', 'K2 User', 'k2-user', '', 'extras/k2/k2-user', 'index.php?option=com_k2&view=itemlist&layout=user&id=42&task=user', 'component', 1, 223, 3, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"theme":"default","userCategoriesFilter":["1","2","3","4","5"],"feedLink":"0","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 57, 58, 0, '*', 0),
(232, 'mainmenu', 'K2 Latest Items', 'k2-latest-items', '', 'extras/k2/k2-latest-items', 'index.php?option=com_k2&view=latest&layout=latest', 'component', 1, 223, 3, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"theme":"default","source":"1","latestItemsCols":"1","latestItemsLimit":"2","latestItemsDisplayEffect":"all","userName":"1","userImage":"1","userDescription":"1","userURL":"1","userEmail":"1","userFeed":"1","categoryIDs":["2","3","4","5"],"categoryTitle":"0","categoryDescription":"0","categoryImage":"0","categoryFeed":"0","latestItemTitle":"1","latestItemTitleLinked":"1","latestItemDateCreated":"1","latestItemImage":"1","latestItemImageSize":"Medium","latestItemVideo":"0","latestItemVideoWidth":"","latestItemVideoHeight":"","latestItemAudioWidth":"","latestItemAudioHeight":"","latestItemVideoAutoPlay":"0","latestItemIntroText":"1","latestItemCategory":"0","latestItemTags":"0","latestItemReadMore":"1","latestItemCommentsAnchor":"0","feedLink":"0","latestItemK2Plugins":"0","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 59, 60, 0, '*', 0),
(234, 'mainmenu', 'Extras', 'extras', '', 'extras', '#', 'url', 1, 1, 1, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"Extensions Used","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 43, 76, 0, '*', 0),
(237, 'mainmenu', 'Joomla Search', 'joomla-search', '', 'jstuff/joomla-search', 'index.php?option=com_search&view=search&searchword=', 'component', 1, 123, 2, 19, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"search_areas":"","show_date":"","searchphrase":"0","ordering":"newest","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 108, 109, 0, '*', 0),
(21, 'menu', 'com_finder', 'Smart Search', '', 'Smart Search', 'index.php?option=com_finder', 'component', 0, 1, 1, 27, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:finder', 0, '', 115, 116, 0, '*', 1),
(22, 'menu', 'com_joomlaupdate', 'Joomla! Update', '', 'Joomla! Update', 'index.php?option=com_joomlaupdate', 'component', 0, 1, 1, 28, 0, 0, '0000-00-00 00:00:00', 0, 0, 'class:joomlaupdate', 0, '', 113, 114, 0, '*', 1),
(298, 'main', 'COM_K2', 'com-k2', '', 'com-k2', 'index.php?option=com_k2', 'component', 0, 1, 1, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '../media/k2/assets/images/system/k2_16x16.png', 0, '', 169, 190, 0, '', 1),
(249, 'smallmenu', 'In Theaters', 'in-theaters', '', 'in-theaters', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=2', 'component', 1, 1, 1, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["2"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 149, 150, 0, '*', 0),
(250, 'smallmenu', 'Coming Soon', 'coming-soon', '', 'coming-soon', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=3', 'component', 1, 1, 1, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["3"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 151, 152, 0, '*', 0),
(251, 'smallmenu', 'Best of 2012', 'best-of-2012', '', 'best-of-2012', 'index.php?option=com_k2&view=itemlist&layout=category&task=category&id=5', 'component', 1, 1, 1, 10014, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"categories":["5"],"singleCatOrdering":"","menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"page_title":"","show_page_heading":0,"page_heading":"","pageclass_sfx":"","menu-meta_description":"","menu-meta_keywords":"","robots":"","secure":0,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 153, 154, 0, '*', 0),
(252, 'mainmenu', 'YJ Live Search', '2013-02-23-15-08-45', '', 'extras/2013-02-23-15-08-45', 'http://extensions.youjoomla.info/yj-live-search.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 66, 67, 0, '*', 0),
(279, 'mainmenu', ' YJ Pop Login', '2013-03-11-19-04-57', '', 'extras/2013-03-11-19-04-57', 'http://extensions.youjoomla.info/yj-pop-login.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 62, 63, 0, '*', 0),
(281, 'mainmenu', 'YJ Image Slider', '2013-03-11-19-07-19', '', 'extras/2013-03-11-19-07-19', 'http://extensions.youjoomla.info/yj-image-slider.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 68, 69, 0, '*', 0),
(282, 'mainmenu', 'YJ Module Engine', '2013-03-11-19-07-36', '', 'extras/2013-03-11-19-07-36', 'http://extensions.youjoomla.info/yj-module-engine.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 72, 73, 0, '*', 0),
(283, 'mainmenu', ' YJ Multitabs', '2013-03-11-19-08-03', '', 'extras/2013-03-11-19-08-03', 'http://extensions.youjoomla.info/yj-multitabs.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 64, 65, 0, '*', 0),
(284, 'mainmenu', 'Youyork Module Slider', '2013-03-11-19-08-26', '', 'extras/2013-03-11-19-08-26', 'http://extensions.youjoomla.info/youyork-module-slider.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 74, 75, 0, '*', 0),
(285, 'mainmenu', ' YJ Latest Tweets', '2013-03-11-19-08-58', '', 'extras/2013-03-11-19-08-58', 'http://extensions.youjoomla.info/yj-latest-tweets.html', 'url', 1, 234, 2, 0, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 70, 71, 0, '*', 0),
(286, 'mainmenu', 'Orange', '2013-03-12-17-42-45', '', 'template-styles/2013-03-12-17-42-45', '?change_css=orange', 'url', 1, 145, 2, 0, 0, 0, '0000-00-00 00:00:00', 0, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_sub_title":"","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 40, 41, 0, '*', 0),
(287, 'mainmenu', 'Shortcodes', 'shortcodes', '', 'shortcodes', 'http://yjsimplegrid.com/shortcodes/', 'url', 1, 1, 1, 22, 0, 0, '0000-00-00 00:00:00', 1, 1, '', 0, '{"menu-anchor_title":"","menu-anchor_css":"","menu_image":"","menu_text":1,"yj_menu_icon_prefix":"","yj_menu_icon_suffix":"","yj_menu_sub_title":"Template Shortcodes","yj_hide_indicator":"0","yj_group_holder":"0","yj_menu_holder_width":"","yj_menu_groups_count":"0","yj_sub_group_width":"","yj_item_type":"0","yj_menu_show_title":"1"}', 157, 158, 0, '*', 0),
(297, 'main', 'WF_MENU_INSTALL', 'wf-menu-install', '', 'jce/wf-menu-install', 'index.php?option=com_jce&view=installer', 'component', 0, 293, 2, 10034, 0, 0, '0000-00-00 00:00:00', 0, 1, 'components/com_jce/media/img/menu/jce-install.png', 0, '', 166, 167, 0, '', 1),
(296, 'main', 'WF_MENU_PROFILES', 'wf-menu-profiles', '', 'jce/wf-menu-profiles', 'index.php?option=com_jce&view=profiles', 'component', 0, 293, 2, 10034, 0, 0, '0000-00-00 00:00:00', 0, 1, 'components/com_jce/media/img/menu/jce-profiles.png', 0, '', 164, 165, 0, '', 1),
(295, 'main', 'WF_MENU_CONFIG', 'wf-menu-config', '', 'jce/wf-menu-config', 'index.php?option=com_jce&view=config', 'component', 0, 293, 2, 10034, 0, 0, '0000-00-00 00:00:00', 0, 1, 'components/com_jce/media/img/menu/jce-config.png', 0, '', 162, 163, 0, '', 1),
(293, 'main', 'JCE', 'jce', '', 'jce', 'index.php?option=com_jce', 'component', 0, 1, 1, 10034, 0, 0, '0000-00-00 00:00:00', 0, 1, 'components/com_jce/media/img/menu/logo.png', 0, '', 159, 168, 0, '', 1);
INSERT INTO `#__menu` (`id`, `menutype`, `title`, `alias`, `note`, `path`, `link`, `type`, `published`, `parent_id`, `level`, `component_id`, `ordering`, `checked_out`, `checked_out_time`, `browserNav`, `access`, `img`, `template_style_id`, `params`, `lft`, `rgt`, `home`, `language`, `client_id`) VALUES
(294, 'main', 'WF_MENU_CPANEL', 'wf-menu-cpanel', '', 'jce/wf-menu-cpanel', 'index.php?option=com_jce', 'component', 0, 293, 2, 10034, 0, 0, '0000-00-00 00:00:00', 0, 1, 'components/com_jce/media/img/menu/jce-cpanel.png', 0, '', 160, 161, 0, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `#__menu_types`
--

DROP TABLE IF EXISTS `#__menu_types`;
CREATE TABLE IF NOT EXISTS `#__menu_types` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `menutype` varchar(24) NOT NULL,
  `title` varchar(48) NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_menutype` (`menutype`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `#__menu_types`
--

INSERT INTO `#__menu_types` (`id`, `menutype`, `title`, `description`) VALUES
(1, 'mainmenu', 'Main Menu', 'The main menu for the site'),
(2, 'footer-menu', 'Footer Menu', 'footer menu'),
(3, 'smallmenu', 'Small Menu', '');

-- --------------------------------------------------------

--
-- Table structure for table `#__modules`
--

DROP TABLE IF EXISTS `#__modules`;
CREATE TABLE IF NOT EXISTS `#__modules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `note` varchar(255) NOT NULL DEFAULT '',
  `content` text NOT NULL,
  `ordering` int(11) NOT NULL DEFAULT '0',
  `position` varchar(50) NOT NULL DEFAULT '',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_up` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_down` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `module` varchar(50) DEFAULT NULL,
  `access` int(10) unsigned DEFAULT NULL,
  `showtitle` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `params` text NOT NULL,
  `client_id` tinyint(4) NOT NULL DEFAULT '0',
  `language` char(7) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `published` (`published`,`access`),
  KEY `newsfeeds` (`module`,`published`),
  KEY `idx_language` (`language`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=134 ;

--
-- Dumping data for table `#__modules`
--

INSERT INTO `#__modules` (`id`, `title`, `note`, `content`, `ordering`, `position`, `checked_out`, `checked_out_time`, `publish_up`, `publish_down`, `published`, `module`, `access`, `showtitle`, `params`, `client_id`, `language`) VALUES
(2, 'Login', '', '', 1, 'login', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_login', 1, 1, '', 1, '*'),
(3, 'Popular Articles', '', '', 3, 'cpanel', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_popular', 3, 1, '{"count":"5","catid":"","user_id":"0","layout":"_:default","moduleclass_sfx":"","cache":"0","automatic_title":"1"}', 1, '*'),
(4, 'Recently Added Articles', '', '', 4, 'cpanel', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_latest', 3, 1, '{"count":"5","ordering":"c_dsc","catid":"","user_id":"0","layout":"_:default","moduleclass_sfx":"","cache":"0","automatic_title":"1"}', 1, '*'),
(6, 'Unread Messages', '', '', 1, 'header', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_unread', 3, 1, '', 1, '*'),
(7, 'Online Users', '', '', 2, 'header', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_online', 3, 1, '', 1, '*'),
(8, 'Toolbar', '', '', 1, 'toolbar', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_toolbar', 3, 1, '', 1, '*'),
(9, 'Quick Icons', '', '', 1, 'icon', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_quickicon', 3, 1, '', 1, '*'),
(10, 'Logged-in Users', '', '', 2, 'cpanel', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_logged', 3, 1, '{"count":"5","name":"1","layout":"_:default","moduleclass_sfx":"","cache":"0","automatic_title":"1"}', 1, '*'),
(12, 'Admin Menu', '', '', 1, 'menu', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_menu', 3, 1, '{"layout":"","moduleclass_sfx":"","shownew":"1","showhelp":"1","cache":"0"}', 1, '*'),
(13, 'Admin Submenu', '', '', 1, 'submenu', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_submenu', 3, 1, '', 1, '*'),
(14, 'User Status', '', '', 2, 'status', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_status', 3, 1, '', 1, '*'),
(15, 'Title', '', '', 1, 'title', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_title', 3, 1, '', 1, '*'),
(19, 'Main Menu', '', '', 2, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_menu', 1, 1, '{"menutype":"mainmenu","startLevel":"1","endLevel":"0","showAllChildren":"0","tag_id":"","class_sfx":"","window_open":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"itemid"}', 0, '*'),
(20, 'Top1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.   Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et  urna  id elit onvali', 1, 'top1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(21, 'Top5', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.   Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et  urna  id elit onvali', 1, 'top5', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(22, 'Top2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.   Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et  urna  id elit onvali', 1, 'top2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(23, 'Top4', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.   Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et  urna  id elit onvali', 1, 'top4', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(24, 'Top3', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.   Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et  urna  id elit onvali', 1, 'top3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(25, 'Header1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna.', 1, 'header1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(26, 'Header3', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna.', 1, 'header3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(27, 'Header2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna.', 1, 'header2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(28, 'User6', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user6', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(29, 'Adv1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'adv1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(30, 'Adv5', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'adv5', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(31, 'Adv2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'adv2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(32, 'Adv4', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'adv4', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(33, 'Adv3', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'adv3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(34, 'User1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(35, 'User2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(36, 'User3', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(37, 'User4', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user4', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(38, 'User5', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user5', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(39, 'User7', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user7', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(40, 'User11', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user11', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(41, 'User9', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user9', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(42, 'User10', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user10', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(43, 'User8', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user8', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(44, 'User12', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user12', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(45, 'User13', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user13', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(46, 'User14', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user14', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(47, 'User15', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user15', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(48, 'User16', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user16', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(49, 'User17', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum.', 1, 'user17', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(50, 'User18', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user18', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(51, 'User19', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user19', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(52, 'User20', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user20', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(53, 'User21', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac.', 1, 'user21', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(54, 'User22', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user22', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(55, 'User23', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user23', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(56, 'User24', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user24', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(57, 'User25', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali', 1, 'user25', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(58, 'Bodytop1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euismod convas. Nunc sodales lorem vitae magna ac vestibulum turpis interdum. Phasel lus et urna id elit euismod convallis', 1, 'bodytop1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(59, 'Bodytop2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euismod convas. Nunc sodales lorem vitae magna ac vestibulum turpis interdum. Phasel lus et urna id elit euismod convallis', 1, 'bodytop2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(60, 'Bodytop3', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euismod convas. Nunc sodales lorem vitae magna ac vestibulum turpis interdum. Phasel lus et urna id elit euismod convallis', 1, 'bodytop3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(61, 'Bodybottom1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euismod convas. Nunc sodales lorem vitae magna ac vestibulum turpis interdum. Phasel lus et urna id elit euismod convallis', 1, 'bodybottom1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(62, 'Bodybottom2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euismod convas. Nunc sodales lorem vitae magna ac vestibulum turpis interdum. Phasel lus et urna id elit euismod convallis', 1, 'bodybottom2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(63, 'Bodybottom3', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euismod convas. Nunc sodales lorem vitae magna ac vestibulum turpis interdum. Phasel lus et urna id elit euismod convallis', 1, 'bodybottom3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(64, 'InsetTop', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali,Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali,Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis inNunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali,Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpisinAv vestibulum turpis interdum.Pha sellus et urna id elit euism convas.Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna.', 1, 'insettop', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(65, 'InsetBottom', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali,Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali,Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpis inNunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et urna id elit onvali,Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum turpisinAv vestibulum turpis interdum.Pha sellus et urna id elit euism convas.Pha sellus et urna id elit euism convas. Nunc sodales lorem vit magna ac vestibulum.', 1, 'insetbottom', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(66, 'Left', '', 'Praesent posuere tempus nibh bibendum aliquam. Integer ullamcorper vestibulum nisl vitae ullamcorper. Donec fermentum ligula at tortor tincidunt rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque blandit tellus vitae justo rutrum viverra. Proin semper sapien faucibus dolor ultrices a tempus leo porta. Integer sed lorem nisl, ac tincidunt diam.ed ante felis, pellentesque in consectetur at, rutrum non turpis. Aenean id pellentesque lacus. Etiam egestas, ante. Etiam egestas, ante.', 1, 'left', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(67, 'Right', '', 'Praesent posuere tempus nibh bibendum aliquam. Integer ullamcorper vestibulum nisl vitae ullamcorper. Donec fermentum ligula at tortor tincidunt rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque blandit tellus vitae justo rutrum viverra. Proin semper sapien faucibus dolor ultrices a tempus leo porta. Integer sed lorem nisl, ac tincidunt diam.ed ante felis, pellentesque in consectetur at, rutrum non turpis. Aenean id pellentesque lacus. Etiam egestas, ante. Etiam egestas, ante.', 7, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(68, 'Inset', '', 'Praesent posuere tempus nibh bibendum aliquam. Integer ullamcorper vestibulum nisl vitae ullamcorper. Donec fermentum ligula at tortor tincidunt rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque blandit tellus vitae justo rutrum viverra. Proin semper sapien faucibus dolor ultrices a tempus leo porta. Integer sed lorem nisl, ac tincidunt diam.ed ante felis, pellentesque in consectetur at, rutrum non turpis. Aenean id pellentesque lacus. Etiam egestas, ante. Etiam egestas, ante.', 1, 'inset', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(73, 'Breadcrumbs', '', '', 1, 'breadcrumb', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_breadcrumbs', 1, 0, '{"showHere":"1","showHome":"1","homeText":"Home","showLast":"1","separator":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"itemid"}', 0, '*'),
(75, 'Footer Menu', '', '', 1, 'footer', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_menu', 1, 1, '{"menutype":"footer-menu","startLevel":"1","endLevel":"0","showAllChildren":"0","tag_id":"","class_sfx":"","window_open":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"itemid","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":"","yjsg_menu_module_type":"inline"}', 0, '*'),
(76, 'Menu Module Position Demo', '', '<img src="images/stories/modulepoz.jpg" border="0" alt="modulepoz" width="160" height="42" style="display: block; margin-left: auto; margin-right: auto;" />\r\n<p>This module is published in module position topmenu1.</p>', 1, 'topmenu1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 0, '{"prepare_content":"1","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(77, 'Multilanguage status', '', '', 1, 'status', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 'mod_multilangstatus', 3, 1, '{"layout":"_:default","moduleclass_sfx":"","cache":"0"}', 1, '*'),
(78, 'K2 Comments', '', '', 10, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_comments', 1, 1, '{"moduleclass_sfx":" hdck2com","module_usage":"0","catfilter":"1","category_id":["2","3","5"],"comments_limit":"5","comments_word_limit":"10","commenterName":"1","commentAvatar":"1","commentAvatarWidthSelect":"custom","commentAvatarWidth":"60","commentDate":"0","commentDateFormat":"relative","commentLink":"1","itemTitle":"0","itemCategory":"0","feed":"0","commenters_limit":"5","commenterNameOrUsername":"1","commenterAvatar":"1","commenterAvatarWidthSelect":"custom","commenterAvatarWidth":"50","commenterLink":"1","commenterCommentsCounter":"1","commenterLatestComment":"1","cache":"1","cache_time":"900"}', 0, '*'),
(79, 'K2 Content', '', '', 9, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_content', 1, 1, '{"moduleclass_sfx":" hdck2cm","getTemplate":"HDChannel","source":"filter","catfilter":"0","getChildren":"0","itemCount":"4","itemsOrdering":"rand","FeaturedItems":"1","popularityRange":"","videosOnly":"0","item":"0","itemTitle":"1","itemAuthor":"0","itemAuthorAvatar":"0","itemAuthorAvatarWidthSelect":"custom","itemAuthorAvatarWidth":"16","userDescription":"1","itemIntroText":"1","itemIntroTextWordLimit":"15","itemImage":"1","itemImgSize":"XSmall","itemVideo":"0","itemVideoCaption":"0","itemVideoCredits":"0","itemAttachments":"0","itemTags":"0","itemCategory":"0","itemDateCreated":"1","itemHits":"0","itemReadMore":"0","itemExtraFields":"0","itemCommentsCounter":"0","feed":"0","itemPreText":"","itemCustomLink":"0","itemCustomLinkTitle":"","itemCustomLinkURL":"http:\\/\\/","itemCustomLinkMenuItem":"","K2Plugins":"1","JPlugins":"1","cache":"1","cache_time":"900"}', 0, '*'),
(80, 'K2 Login', '', '', 0, '', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 'mod_k2_login', 1, 1, '', 0, '*'),
(81, 'K2 Category Menu', '', '', 8, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_tools', 1, 1, '{"moduleclass_sfx":" hdck2m","module_usage":"4","archiveItemsCounter":"1","archiveCategory":"0","authors_module_category":"0","authorItemsCounter":"1","authorAvatar":"1","authorAvatarWidthSelect":"custom","authorAvatarWidth":"50","authorLatestItem":"1","calendarCategory":"0","home":"","seperator":"","root_id":"1","end_level":"","categoriesListOrdering":"","categoriesListItemsCounter":"1","root_id2":"0","catfilter":"0","getChildren":"0","liveSearch":"","width":"20","text":"","button":"","imagebutton":"","button_text":"","min_size":"75","max_size":"300","cloud_limit":"30","cloud_category":["0"],"cloud_category_recursive":"0","customCode":"","parsePhp":"0","K2Plugins":"0","JPlugins":"0","cache":"1","cache_time":"900"}', 0, '*'),
(82, 'K2 Users', '', '', 0, '', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 'mod_k2_users', 1, 1, '', 0, '*'),
(83, 'K2 User', '', '', 0, '', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 'mod_k2_user', 1, 1, '', 0, '*'),
(84, 'K2 QuickIcons (admin)', '', '', 99, 'icon', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_quickicons', 1, 1, '', 1, '*'),
(85, 'K2 Stats (admin)', '', '', 0, 'cpanel', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_stats', 1, 1, '', 1, '*'),
(86, 'K2 Celendar', '', '', 11, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 'mod_k2_tools', 1, 1, '{"moduleclass_sfx":"","module_usage":"2","archiveItemsCounter":"1","archiveCategory":"0","authors_module_category":"0","authorItemsCounter":"1","authorAvatar":"1","authorAvatarWidthSelect":"custom","authorAvatarWidth":"50","authorLatestItem":"1","calendarCategory":"0","home":"","seperator":"","root_id":"1","end_level":"","categoriesListOrdering":"","categoriesListItemsCounter":"1","root_id2":"0","catfilter":"0","getChildren":"0","liveSearch":"","width":"20","text":"","button":"","imagebutton":"","button_text":"","min_size":"75","max_size":"300","cloud_limit":"30","cloud_category":["0"],"cloud_category_recursive":"0","customCode":"","parsePhp":"0","K2Plugins":"0","JPlugins":"0","cache":"1","cache_time":"900"}', 0, '*'),
(87, 'K2 Tag Cloud', '', '', 12, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_tools', 1, 1, '{"moduleclass_sfx":" hdck2tc","module_usage":"7","archiveItemsCounter":"1","archiveCategory":"0","authors_module_category":"0","authorItemsCounter":"1","authorAvatar":"1","authorAvatarWidthSelect":"custom","authorAvatarWidth":"50","authorLatestItem":"1","calendarCategory":"0","home":"","seperator":"","root_id":"1","end_level":"","categoriesListOrdering":"","categoriesListItemsCounter":"1","root_id2":"0","catfilter":"0","getChildren":"0","liveSearch":"","width":"20","text":"","button":"","imagebutton":"","button_text":"","min_size":"100","max_size":"300","cloud_limit":"30","cloud_category":["0","1","2","3","5"],"cloud_category_recursive":"0","customCode":"","parsePhp":"0","K2Plugins":"0","JPlugins":"0","cache":"1","cache_time":"900"}', 0, '*'),
(89, 'Sample yj1', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.       Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et      urna  id elit onvali.', 4, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" yj1","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(90, 'Sample yj2', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.       Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et      urna  id elit onvali.', 6, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" yj2","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(91, 'Sample Default', '', 'Av vestibulum turpis interdum. Pha sellus et urna id elit euism convas.       Nunc sodales lorem vit magna ac vestibulum turpis in. Phasel lus et      urna  id elit onvali.', 1, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(92, 'K2 breadcrumbs', '', '', 1, 'user6', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_k2_tools', 1, 0, '{"moduleclass_sfx":"","module_usage":"3","archiveItemsCounter":"1","archiveCategory":"0","authors_module_category":"0","authorItemsCounter":"1","authorAvatar":"1","authorAvatarWidthSelect":"custom","authorAvatarWidth":"50","authorLatestItem":"1","calendarCategory":"0","home":"","seperator":"","root_id":"1","end_level":"","categoriesListOrdering":"","categoriesListItemsCounter":"1","root_id2":"0","catfilter":"0","getChildren":"0","liveSearch":"","width":"20","text":"","button":"","imagebutton":"","button_text":"","min_size":"75","max_size":"300","cloud_limit":"30","cloud_category":["0","1","2","3","5"],"cloud_category_recursive":"0","customCode":"","parsePhp":"0","K2Plugins":"0","JPlugins":"0","cache":"1","cache_time":"900"}', 0, '*'),
(93, 'Joomla Version', '', '', 1, 'footer', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_version', 3, 1, '{"format":"short","product":"1","layout":"_:default","moduleclass_sfx":"","cache":"0"}', 1, '*'),
(94, 'Small Menu', '', '', 1, 'smallmenu', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_menu', 1, 1, '{"menutype":"smallmenu","startLevel":"1","endLevel":"0","showAllChildren":"0","tag_id":"","class_sfx":"","window_open":"","layout":"_:default","moduleclass_sfx":"","cache":"1","cache_time":"900","cachemode":"itemid"}', 0, '*'),
(95, 'YJ Pop Login', '', '', 1, 'header1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yj_pop_login', 1, 0, '{"cache":"0","moduleclass_sfx":" hdclm","pretext":"<span><\\/span>","posttext":"","login":"","logout":"","greeting":"1","name":"0","usesecure":"0"}', 0, '*'),
(96, 'YJ Live Search', '', '', 1, 'header1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yj_live_search', 1, 0, '{"live_status":"1","is_copy":"defaultfeed","feedtype":"customfeeds","index_items":"3","mysql_operator":"REGEXP","input_width":"260","search_limit":"5","intro_limit":"150","topDistance":"35","duration":"200","minLenght":"3","maxLenght":"10","minCharTxt1":"Minimum","minCharTxt2":"characters required","default_input":"Search","no_results":"Sorry, no results for","search_button":"1","show_image":"1","k2image_size":"XS","image_width":"75","Search_Content":"1","Search_Weblinks":"1","Search_Contacts":"1","Search_Categories":"1","Search_Newsfeeds":"1","Search_K2":"1","Search_K2_cat":"1","Search_K2_tags":"1","set_itemid":"252","moduleclass_sfx":"hdcls","module_subtitle":"","module_float":"none","module_floatwidth":"750px","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(97, 'YJ Image Slider V4', '', '', 1, 'top1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjis4', 1, 0, '{"moduleclass_sfx":" hdcis","module_template":"HDChannel","swidth":"1170","sheight":"430","sorient":"0","type_slider":"1","start_slide":"0","stime":"5000","sduration":"200","smenu":"1","tooltips":"0","order":"0","thumb_pagination":"0","beltOrientation":"0","thumb_width":"80","thumb_height":"80","visibleItems":"3","thumbsOpacity":"0.5","beltPosition":"bottom:20px;","thumbs_tooltips":"0","thumb_arrows":"1","pagination":"0","pagination_counter":"0","navs_tooltips":"0","mbox_theme":"Dark","over_opacity":"0.7","loop_image":"false","use_NB":"true","hide_all_intros":"0","hide_all_descriptions":"0","slide_image_1":"images\\/upload_slides\\/slide1.jpg","slide_genthumb_1":"images\\/upload_slides\\/thumbnails\\/thumb_slide1.jpg","slide_thumb_1":"","slide_title_1":"The Dark Knight Rises","slide_description_1":"","slide_intro_1":"<h4>The Dark Knight Rises<\\/h4>\\r\\n<div class=\\"is_introholder\\">\\r\\n<p>Morbi euismod lobortis justo, et fringilla quam cursus sit amet praesent non ipsum at metus aliquam consectetur...<\\/p>\\r\\n<a href=\\"index.php?option=com_k2&view=item&id=27:the-dark-knight-rises&Itemid=227\\" class=\\"is_rm\\">More<\\/a>\\r\\n<\\/div>","mboxlink_1":"http:\\/\\/www.youtube.com\\/watch?v=GokKUqLcvD8","mboxtype_1":"1","video_width_1":"1000","video_height_1":"500","image_group_1":"0","image_group_name_1":"yjgallery","slide_link_1":"","slide_open_1":"0","slide_image_2":"images\\/upload_slides\\/slide2.jpg","slide_genthumb_2":"images\\/upload_slides\\/thumbnails\\/thumb_slide2.jpg","slide_thumb_2":"","slide_title_2":"","slide_description_2":"","slide_intro_2":"<h4>The Hobbit: An Unexpected Journey<\\/h4>\\r\\n<div class=\\"is_introholder\\">\\r\\n<p>Phasellus a arcu lorem donec vestibulum risus est, sed justo convallis dui, phasellus felis est placerat blandit...<\\/p>\\r\\n<a href=\\"index.php?option=com_k2&view=item&id=26:the-hobbit-an-unexpected-journey&Itemid=227\\" class=\\"is_rm\\">More<\\/a>\\r\\n<\\/div>","mboxlink_2":"http:\\/\\/www.youtube.com\\/watch?v=T90Holdcrps","mboxtype_2":"1","video_width_2":"1000","video_height_2":"500","image_group_2":"0","image_group_name_2":"yjgallery","slide_link_2":"","slide_open_2":"0","slide_image_3":"images\\/upload_slides\\/slide3.jpg","slide_genthumb_3":"images\\/upload_slides\\/thumbnails\\/thumb_slide3.jpg","slide_thumb_3":"","slide_title_3":"","slide_description_3":"","slide_intro_3":"<h4>G.I. Joe: Retaliation<\\/h4>\\r\\n<div class=\\"is_introholder\\">\\r\\n<p>Quisque eu lectus nec nibh convallis suscipit quis et ipsum donec vehicula, nisi a venenatis imperdiet odio sapien...<\\/p>\\r\\n<a href=\\"index.php?option=com_k2&view=item&id=17:g-i-joe-retaliation&Itemid=226\\" class=\\"is_rm\\">More<\\/a>\\r\\n<\\/div>","mboxlink_3":"http:\\/\\/www.youtube.com\\/watch?v=QLR3HrV71yM","mboxtype_3":"1","video_width_3":"1000","video_height_3":"500","image_group_3":"0","image_group_name_3":"yjgallery","slide_link_3":"","slide_open_3":"0","slide_image_4":"images\\/upload_slides\\/slide4.jpg","slide_genthumb_4":"images\\/upload_slides\\/thumbnails\\/thumb_slide4.jpg","slide_thumb_4":"","slide_title_4":"","slide_description_4":"","slide_intro_4":"<h4>Man of Steel<\\/h4>\\r\\n<div class=\\"is_introholder\\">\\r\\n<p>Duis vitae dolor libero, sed aliquet mauris proin vestibulum dictum dui id dictum fusce suscipit turpis a quam...<\\/p>\\r\\n<a href=\\"index.php?option=com_k2&view=item&id=22:man-of-steel&Itemid=226\\" class=\\"is_rm\\">More<\\/a>\\r\\n<\\/div>","mboxlink_4":"http:\\/\\/www.youtube.com\\/watch?v=ll39CAovGrg","mboxtype_4":"1","video_width_4":"1000","video_height_4":"500","image_group_4":"0","image_group_name_4":"yjgallery","slide_link_4":"","slide_open_4":"0","slide_image_5":"images\\/upload_slides\\/slide5.jpg","slide_genthumb_5":"images\\/upload_slides\\/thumbnails\\/thumb_slide5.jpg","slide_thumb_5":"","slide_title_5":"","slide_description_5":"","slide_intro_5":"<h4>Iron Man 3<\\/h4>\\r\\n<div class=\\"is_introholder\\">\\r\\n<p>Aenean nunc turpis, sodales nec bibendum a, porta a velit quisque eu lectus nec nibh convallis suscipit quis et ipsum...<\\/p>\\r\\n<a href=\\"index.php?option=com_k2&view=item&id=21:iron-man-3&Itemid=226\\" class=\\"is_rm\\">More<\\/a>\\r\\n<\\/div>","mboxlink_5":"http:\\/\\/www.youtube.com\\/watch?v=aV8H7kszXqo","mboxtype_5":"1","video_width_5":"1000","video_height_5":"500","image_group_5":"0","image_group_name_5":"yjgallery","slide_link_5":"","slide_open_5":"0","slide_image_6":"","slide_genthumb_6":"","slide_thumb_6":"","slide_title_6":"","slide_description_6":"","slide_intro_6":"","mboxlink_6":"","mboxtype_6":"0","video_width_6":"640","video_height_6":"360","image_group_6":"0","image_group_name_6":"yjgallery","slide_link_6":"","slide_open_6":"0","slide_image_7":"","slide_genthumb_7":"","slide_thumb_7":"","slide_title_7":"","slide_description_7":"","slide_intro_7":"","mboxlink_7":"","mboxtype_7":"0","video_width_7":"640","video_height_7":"360","image_group_7":"0","image_group_name_7":"yjgallery","slide_link_7":"","slide_open_7":"0","slide_image_8":"","slide_genthumb_8":"","slide_thumb_8":"","slide_title_8":"","slide_description_8":"","slide_intro_8":"","mboxlink_8":"","mboxtype_8":"0","video_width_8":"640","video_height_8":"360","image_group_8":"0","image_group_name_8":"yjgallery","slide_link_8":"","slide_open_8":"0","slide_image_9":"","slide_genthumb_9":"","slide_thumb_9":"","slide_title_9":"","slide_description_9":"","slide_intro_9":"","mboxlink_9":"","mboxtype_9":"0","video_width_9":"640","video_height_9":"360","image_group_9":"0","image_group_name_9":"yjgallery","slide_link_9":"","slide_open_9":"0","slide_image_10":"","slide_genthumb_10":"","slide_thumb_10":"","slide_title_10":"","slide_description_10":"","slide_intro_10":"","mboxlink_10":"","mboxtype_10":"0","video_width_10":"640","video_height_10":"360","image_group_10":"0","image_group_name_10":"yjgallery","slide_link_10":"","slide_open_10":"0","slide_image_11":"","slide_genthumb_11":"","slide_thumb_11":"","slide_title_11":"","slide_description_11":"","slide_intro_11":"","mboxlink_11":"","mboxtype_11":"0","video_width_11":"640","video_height_11":"360","image_group_11":"0","image_group_name_11":"yjgallery","slide_link_11":"","slide_open_11":"0","slide_image_12":"","slide_genthumb_12":"","slide_thumb_12":"","slide_title_12":"","slide_description_12":"","slide_intro_12":"","mboxlink_12":"","mboxtype_12":"0","video_width_12":"640","video_height_12":"360","image_group_12":"0","image_group_name_12":"yjgallery","slide_link_12":"","slide_open_12":"0","slide_image_13":"","slide_genthumb_13":"","slide_thumb_13":"","slide_title_13":"","slide_description_13":"","slide_intro_13":"","mboxlink_13":"","mboxtype_13":"0","video_width_13":"640","video_height_13":"360","image_group_13":"0","image_group_name_13":"yjgallery","slide_link_13":"","slide_open_13":"0","slide_image_14":"","slide_genthumb_14":"","slide_thumb_14":"","slide_title_14":"","slide_description_14":"","slide_intro_14":"","mboxlink_14":"","mboxtype_14":"0","video_width_14":"640","video_height_14":"360","image_group_14":"0","image_group_name_14":"yjgallery","slide_link_14":"","slide_open_14":"0","slide_image_15":"","slide_genthumb_15":"","slide_thumb_15":"","slide_title_15":"","slide_description_15":"","slide_intro_15":"","mboxlink_15":"","mboxtype_15":"0","video_width_15":"640","video_height_15":"360","image_group_15":"0","image_group_name_15":"yjgallery","slide_link_15":"","slide_open_15":"0","slide_image_16":"","slide_genthumb_16":"","slide_thumb_16":"","slide_title_16":"","slide_description_16":"","slide_intro_16":"","mboxlink_16":"","mboxtype_16":"0","video_width_16":"640","video_height_16":"360","image_group_16":"0","image_group_name_16":"yjgallery","slide_link_16":"","slide_open_16":"0","slide_image_17":"","slide_genthumb_17":"","slide_thumb_17":"","slide_title_17":"","slide_description_17":"","slide_intro_17":"","mboxlink_17":"","mboxtype_17":"0","video_width_17":"640","video_height_17":"360","image_group_17":"0","image_group_name_17":"yjgallery","slide_link_17":"","slide_open_17":"0","slide_image_18":"","slide_genthumb_18":"","slide_thumb_18":"","slide_title_18":"","slide_description_18":"","slide_intro_18":"","mboxlink_18":"","mboxtype_18":"0","video_width_18":"640","video_height_18":"360","image_group_18":"0","image_group_name_18":"yjgallery","slide_link_18":"","slide_open_18":"0","slide_image_19":"","slide_genthumb_19":"","slide_thumb_19":"","slide_title_19":"","slide_description_19":"","slide_intro_19":"","mboxlink_19":"","mboxtype_19":"0","video_width_19":"640","video_height_19":"360","image_group_19":"0","image_group_name_19":"yjgallery","slide_link_19":"","slide_open_19":"0","slide_image_20":"","slide_genthumb_20":"","slide_thumb_20":"","slide_title_20":"","slide_description_20":"","slide_intro_20":"","mboxlink_20":"","mboxtype_20":"0","video_width_20":"640","video_height_20":"360","image_group_20":"0","image_group_name_20":"yjgallery","slide_link_20":"","slide_open_20":"0","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(98, 'Morbi facilisis nunc Etiam', '', '<p>Nam vel diam fringilla diam dictum ultricies et eget elit. Aliquam erat volutpat. Donec purus tortor, eleifend in hendrerit sit amet, malesuada quis dui. Vivamus vel pulvinar diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas...</p>\r\n<a class="template_readmore">Read more</a>', 1, 'bodytop1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" custom1","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(99, 'Recently Added', '', '', 3, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_youyork_slider', 1, 1, '{"is_copy":"","module_template":"HDChannel","slider_width":"385px","slider_height":"550px","module_pozi":"Right1,Right2,Right3","module_title":"Righttitle1,Righttitle2,Righttitle3","showtitle":"2","visible_modules":"1","effectDuration":"300","autoslide":"0","show_bottom_nav":"0","moduleclass_sfx":" hdcym","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(100, 'Youjoomla Multitabs Modules', '', '', 1, 'bodybottom1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yj_multitab', 1, 0, '{"is_copy":"","module_template":"Default","module_pozi":"tab1,tab2,tab3","module_title":"In Theaters,Coming Soon,Best of 2012","auto_width":"0","ulis_width2":"120px","transtype":"1","tabs_height":"","moduleclass_sfx":" hdcmt","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(101, 'Youyork Module Slider In Theaters ', '', '', 1, 'tab1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_youyork_slider', 1, 1, '{"is_copy":"_intheaters","module_template":"Default","slider_width":"770px","slider_height":"720px","module_pozi":"Slide1,Slide2","module_title":"Title1,Title2","showtitle":"2","visible_modules":"1","effectDuration":"300","autoslide":"5000","show_bottom_nav":"0","moduleclass_sfx":"_youyork_slider","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(102, 'Youyork Module Slider Coming Soon ', '', '', 1, 'tab2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_youyork_slider', 1, 1, '{"is_copy":"_comingsoon","module_template":"Default","slider_width":"770px","slider_height":"720px","module_pozi":"Slide3,Slide4","module_title":"Title3,Title4","showtitle":"2","visible_modules":"1","effectDuration":"300","autoslide":"5000","show_bottom_nav":"0","moduleclass_sfx":"_youyork_slider","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(103, 'Youyork Module Slider Best of 2012', '', '', 1, 'tab3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_youyork_slider', 1, 1, '{"is_copy":"_bestof2012","module_template":"Default","slider_width":"770px","slider_height":"720px","module_pozi":"Slide5,Slide6","module_title":"Title5,Title6","showtitle":"2","visible_modules":"1","effectDuration":"300","autoslide":"5000","show_bottom_nav":"0","moduleclass_sfx":"_youyork_slider","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*');
INSERT INTO `#__modules` (`id`, `title`, `note`, `content`, `ordering`, `position`, `checked_out`, `checked_out_time`, `publish_up`, `publish_down`, `published`, `module`, `access`, `showtitle`, `params`, `client_id`, `language`) VALUES
(105, 'YJ Module Engine Slide1', '', '', 1, 'Slide1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["2"],"k2item":"0","k2items":["12","11","10","9","8","7"],"k2image_size":"M","k2ordering":"1","access_redirect":"1","nitems":"6","chars":"97","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"1","show_intro":"2","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(116, 'YJ Module Engine Right4', '', '', 1, 'Right4', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel2","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["2"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"1","chars":"190","allow_tags":"","show_title":"1","show_author":"1","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"1","show_date":"1","show_intro":"1","show_read":"1","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(106, 'YJ Module Engine Slide2', '', '', 1, 'Slide2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["2"],"k2item":"0","k2items":["6","5","4","3","2","1"],"k2image_size":"M","k2ordering":"1","access_redirect":"1","nitems":"6","chars":"97","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"1","show_intro":"2","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(107, 'YJ Module Engine Slide3', '', '', 1, 'Slide3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["3"],"k2item":"0","k2items":["24","23","22","21","20","19"],"k2image_size":"M","k2ordering":"1","access_redirect":"1","nitems":"6","chars":"97","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"1","show_intro":"2","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(108, 'YJ Module Engine Slide4', '', '', 1, 'Slide4', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["3"],"k2item":"0","k2items":["18","17","16","15","14","13"],"k2image_size":"M","k2ordering":"1","access_redirect":"1","nitems":"6","chars":"97","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"1","show_intro":"2","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(109, 'YJ Module Engine Slide5', '', '', 1, 'Slide5', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["5"],"k2item":"0","k2items":["36","35","34","33","32","31"],"k2image_size":"M","k2ordering":"3","access_redirect":"1","nitems":"6","chars":"97","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"1","show_intro":"2","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(110, 'YJ Module Engine Slide6', '', '', 1, 'Slide6', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["5"],"k2item":"0","k2items":["30","28","27","29","26","25"],"k2image_size":"M","k2ordering":"6","access_redirect":"1","nitems":"6","chars":"97","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"1","show_intro":"2","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(111, 'YJ Module Engine Right1', '', '', 1, 'Right1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["2"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"4","chars":"63","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"2","show_date":"1","show_intro":"1","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(113, 'YJ Module Engine Right2', '', '', 1, 'Right2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["3"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"4","chars":"63","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"2","show_date":"1","show_intro":"1","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(114, 'YJ Module Engine Right3', '', '', 1, 'Right3', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["5"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"4","chars":"63","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"2","show_date":"1","show_intro":"1","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(115, 'Most Popular', '', '', 5, 'right', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_youyork_slider', 1, 1, '{"is_copy":"_right","module_template":"HDChannel","slider_width":"340px","slider_height":"265px","module_pozi":"Right4,Right5,Right6","module_title":"Righttitle4,Righttitle5,Righttitle6","showtitle":"2","visible_modules":"1","effectDuration":"300","autoslide":"0","show_bottom_nav":"1","moduleclass_sfx":" hdcym2 yj1","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(117, 'YJ Module Engine Right5', '', '', 1, 'Right5', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel2","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["3"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"1","chars":"190","allow_tags":"","show_title":"1","show_author":"1","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"1","show_date":"1","show_intro":"1","show_read":"1","moduleclass_sfx":" yjme"}', 0, '*'),
(118, 'YJ Module Engine Right6', '', '', 1, 'Right6', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel2","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["5"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"1","chars":"190","allow_tags":"","show_title":"1","show_author":"1","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"1","show_date":"1","show_intro":"1","show_read":"1","moduleclass_sfx":" yjme"}', 0, '*'),
(119, 'Youyork Module Bottom', '', '', 3, 'user11', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_youyork_slider', 1, 0, '{"is_copy":"_bottom","module_template":"HDChannel","slider_width":"1170px","slider_height":"380px","module_pozi":"Bottom1,Bottom2","module_title":"Bottomtitle1,Bottomtitle2","showtitle":"2","visible_modules":"1","effectDuration":"300","autoslide":"0","show_bottom_nav":"1","moduleclass_sfx":" hdcym3","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(120, 'YJ Module Engine Bottom', '', '', 1, 'Bottom1', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"0","k2item":"0","k2items":["22","2","35","9","10"],"k2image_size":"S","k2ordering":"3","access_redirect":"1","nitems":"5","chars":"58","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"2","show_intro":"1","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(121, 'YJ Module Engine Bottom2', '', '', 1, 'Bottom2', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yjme', 1, 0, '{"module_template":"HDChannel","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"0","k2item":"0","k2items":["34","33","30","16","3"],"k2image_size":"S","k2ordering":"3","access_redirect":"1","nitems":"5","chars":"58","allow_tags":"","show_title":"1","show_author":"2","author_name":"1","show_img":"1","img_align":"3","img_width":"100%","img_height":"auto","show_cat_title":"2","show_date":"2","show_intro":"1","show_read":"2","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(129, 'Consectetur adipiscing ullam corper ornaretellus mauris', '', '<p>Donec auctor eleifend lorem, at tincidunt sapien mollis nec. Nunc arcu nisl, pulvinar blandit euismod non, feugiat in elit. Sed tincidunt, leo nec placerat porta, velit magna scelerisque massa, ornare semper mi orci ut est. Donec lacinia tempus urna eget blandit. Aliquam vitae tincidunt nibh. Cras eu ultricies purus. Vivamus vitae magna est. Aenean porttitor justo ut sapien fringilla vestibulum. Morbi dictum porttitor venenatis.</p>', 1, 'user11', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" custom2","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(122, 'Subscribe', '', '<p>Subscribe to our newsletter</p>\r\n<form action="#" method="get">\r\n<input class="inputbox_email" type="text" onblur="if (this.value=='''') this.value = ''your email...'' " onfocus="if (this.value==''your email...'') this.value = '''' " name="email" value="your email..." size="18"  />\r\n</form>\r\n<input class="subscribe_button" type="submit" value="Submit" />\r\n', 1, 'user16', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 0, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" hdcsm","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(123, 'Socials', '', '<div class="socials">\r\n<a class="yj_facebook" href="#" target="_blank">Facebook</a> \r\n<a class="yj_youtube" href="#" target="_blank">Youtube</a> \r\n<a class="yj_twitter" href="#" target="_blank">Twitter</a> \r\n<a class="yj_skype" href="#" target="_blank">Skype</a> \r\n<a class="yj_linkedin" href="#" target="_blank">Linkedin</a> \r\n<a class="yj_flickr" href="#" target="_blank">Flickr</a> \r\n<a class="yj_googleplus" href="#" target="_blank">Google plus</a>\r\n</div>', 1, 'user17', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 0, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" hdcsim","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(124, 'Partners', '', '<ul class="template_list">\r\n<li><a href="#">Lorem ipsum dolor</a></li>\r\n<li><a href="#">Consectetur adipiscing</a></li>\r\n<li><a href="#">Phasellus tempor</a></li>\r\n<li><a href="#">Egestas aliquam</a></li>\r\n<li><a href="#">Nunc volutpat neque</a></li>\r\n<li><a href="#">Quis tellus ultricies</a></li>\r\n<li><a href="#">Nullam tempus</a></li>\r\n</ul>', 1, 'user21', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" hdcpm","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(125, 'Tweets', '', '', 1, 'user22', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_yj_twitter', 1, 1, '{"twitter_user":"youjoomla","twitter_consumerkey":"","twitter_consumersecret":"","twitter_accesstoken":"","twitter_accesstokensecret":"","nr_article":"2","tweet_limit":"","cache_limit":"60","tweet_link":"1","tweet_date":"0","tweet_follow":"0","tweet_image":"0","moduleclass_sfx":" hdctm","cache":"1","cache_time":"900","cachemode":"itemid","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*'),
(126, 'Contact us', '', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tempor, tortor a egestas aliquam, dui lectus sagittis mauris.<br /><br />\r\n\r\nImperdiet luctus urna libero eget ipsum. Nunc volutpat neque quis tellus ultricies commodo.</p>\r\n<a class="template_readmore">Read more</a>', 1, 'user23', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" hdccm","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(127, 'About us', '', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus tempor, tortor a egestas aliquam, dui lectus sagittis mauris, imperdiet luctus urna libero eget ipsum. Nunc volutpat neque quis tellus ultricies commodo. <br /><br />\r\n\r\nNullam tempus malesuada velit ut dictum. Donec vitae lectus ipsum, sit amet vulputate est. Pellentesque quis magna tellus...</p>', 1, 'user24', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 1, 'mod_custom', 1, 1, '{"prepare_content":"1","backgroundimage":"","layout":"_:default","moduleclass_sfx":" hdcam","cache":"1","cache_time":"900","cachemode":"static"}', 0, '*'),
(133, 'YJ Module Engine Right5 (copy)', '', '', 1, 'Right5', 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', -2, 'mod_yjme', 1, 0, '{"module_template":"HDChannel2","module_css":"stylesheet.css","item_source":"2","yjcatfilter":"0","ordering":"1","show_frontpage":"0","k2catfilter":"1","category_id":["3"],"k2item":"0","k2image_size":"XS","k2ordering":"3","access_redirect":"1","nitems":"1","chars":"190","allow_tags":"","show_title":"1","show_author":"1","author_name":"1","show_img":"1","img_align":"1","img_width":"95px","img_height":"80px","show_cat_title":"1","show_date":"1","show_intro":"1","show_read":"1","moduleclass_sfx":" yjme","module_subtitle":"","module_float":"none","module_floatwidth":"","module_icon_prefix":"","module_icon_suffix":""}', 0, '*');

-- --------------------------------------------------------

--
-- Table structure for table `#__modules_menu`
--

DROP TABLE IF EXISTS `#__modules_menu`;
CREATE TABLE IF NOT EXISTS `#__modules_menu` (
  `moduleid` int(11) NOT NULL DEFAULT '0',
  `menuid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`moduleid`,`menuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `#__modules_menu`
--

INSERT INTO `#__modules_menu` (`moduleid`, `menuid`) VALUES
(2, 0),
(3, 0),
(4, 0),
(6, 0),
(7, 0),
(8, 0),
(9, 0),
(10, 0),
(12, 0),
(13, 0),
(14, 0),
(15, 0),
(19, 138),
(20, 104),
(21, 104),
(22, 104),
(23, 104),
(24, 104),
(25, 104),
(26, 104),
(27, 104),
(28, 104),
(29, 104),
(30, 104),
(31, 104),
(32, 104),
(33, 104),
(34, 104),
(35, 104),
(36, 104),
(37, 104),
(38, 104),
(39, 104),
(40, 104),
(41, 104),
(42, 104),
(43, 104),
(44, 104),
(45, 104),
(46, 104),
(47, 104),
(48, 104),
(49, 104),
(50, 104),
(51, 104),
(52, 104),
(53, 104),
(54, 104),
(55, 104),
(56, 104),
(57, 104),
(58, 104),
(59, 104),
(60, 104),
(61, 104),
(62, 104),
(63, 104),
(64, 104),
(65, 104),
(66, 104),
(67, 104),
(68, 104),
(73, 102),
(73, 103),
(73, 104),
(73, 105),
(73, 106),
(73, 107),
(73, 108),
(73, 109),
(73, 110),
(73, 111),
(73, 112),
(73, 113),
(73, 115),
(73, 116),
(73, 117),
(73, 118),
(73, 119),
(73, 120),
(73, 121),
(73, 122),
(73, 123),
(73, 124),
(73, 125),
(73, 126),
(73, 129),
(73, 131),
(73, 132),
(73, 133),
(73, 134),
(73, 135),
(73, 136),
(73, 137),
(73, 138),
(73, 139),
(73, 140),
(73, 141),
(73, 142),
(73, 143),
(73, 144),
(73, 145),
(73, 146),
(73, 147),
(73, 148),
(73, 149),
(73, 150),
(73, 151),
(73, 152),
(73, 153),
(73, 154),
(73, 156),
(73, 157),
(73, 158),
(73, 159),
(73, 160),
(73, 161),
(73, 162),
(73, 163),
(73, 164),
(73, 165),
(73, 166),
(73, 167),
(73, 168),
(73, 169),
(73, 170),
(73, 171),
(73, 172),
(73, 173),
(73, 174),
(73, 175),
(73, 176),
(73, 177),
(73, 178),
(73, 179),
(73, 180),
(73, 181),
(73, 182),
(73, 183),
(73, 184),
(73, 185),
(73, 186),
(73, 187),
(73, 188),
(73, 189),
(73, 190),
(73, 191),
(73, 192),
(73, 193),
(73, 194),
(73, 195),
(73, 196),
(73, 197),
(73, 198),
(73, 200),
(73, 234),
(73, 235),
(73, 236),
(73, 237),
(75, 0),
(76, 0),
(77, 0),
(78, 225),
(78, 226),
(78, 227),
(78, 229),
(78, 230),
(78, 231),
(78, 232),
(78, 249),
(78, 250),
(78, 251),
(79, 225),
(79, 226),
(79, 227),
(79, 229),
(79, 230),
(79, 231),
(79, 232),
(79, 249),
(79, 250),
(79, 251),
(81, 225),
(81, 226),
(81, 227),
(81, 229),
(81, 230),
(81, 231),
(81, 232),
(81, 249),
(81, 250),
(81, 251),
(86, 225),
(86, 226),
(86, 227),
(86, 228),
(86, 229),
(86, 230),
(86, 231),
(86, 232),
(87, 225),
(87, 226),
(87, 227),
(87, 229),
(87, 230),
(87, 231),
(87, 232),
(87, 249),
(87, 250),
(87, 251),
(89, 236),
(90, 236),
(91, 236),
(92, 225),
(92, 226),
(92, 227),
(92, 229),
(92, 230),
(92, 231),
(92, 232),
(92, 249),
(92, 250),
(92, 251),
(93, 0),
(94, 101),
(94, 102),
(94, 103),
(94, 105),
(94, 106),
(94, 107),
(94, 108),
(94, 109),
(94, 110),
(94, 111),
(94, 112),
(94, 113),
(94, 115),
(94, 116),
(94, 117),
(94, 123),
(94, 125),
(94, 126),
(94, 129),
(94, 131),
(94, 132),
(94, 133),
(94, 134),
(94, 135),
(94, 136),
(94, 137),
(94, 138),
(94, 139),
(94, 140),
(94, 141),
(94, 142),
(94, 143),
(94, 144),
(94, 145),
(94, 146),
(94, 147),
(94, 148),
(94, 149),
(94, 150),
(94, 151),
(94, 152),
(94, 153),
(94, 154),
(94, 156),
(94, 157),
(94, 200),
(94, 223),
(94, 224),
(94, 225),
(94, 226),
(94, 227),
(94, 229),
(94, 230),
(94, 231),
(94, 232),
(94, 234),
(94, 235),
(94, 236),
(94, 237),
(94, 249),
(94, 250),
(94, 251),
(95, 101),
(95, 102),
(95, 106),
(95, 107),
(95, 108),
(95, 109),
(95, 110),
(95, 111),
(95, 112),
(95, 113),
(95, 123),
(95, 125),
(95, 129),
(95, 131),
(95, 132),
(95, 133),
(95, 134),
(95, 135),
(95, 136),
(95, 137),
(95, 138),
(95, 139),
(95, 140),
(95, 141),
(95, 142),
(95, 143),
(95, 144),
(95, 145),
(95, 146),
(95, 147),
(95, 148),
(95, 149),
(95, 156),
(95, 157),
(95, 223),
(95, 224),
(95, 225),
(95, 226),
(95, 227),
(95, 229),
(95, 230),
(95, 231),
(95, 232),
(95, 234),
(95, 236),
(95, 237),
(95, 249),
(95, 250),
(95, 251),
(95, 252),
(95, 279),
(95, 281),
(95, 282),
(95, 283),
(95, 284),
(95, 285),
(95, 286),
(95, 287),
(96, 101),
(96, 106),
(96, 107),
(96, 108),
(96, 109),
(96, 110),
(96, 111),
(96, 112),
(96, 113),
(96, 123),
(96, 125),
(96, 129),
(96, 131),
(96, 132),
(96, 133),
(96, 134),
(96, 135),
(96, 136),
(96, 137),
(96, 138),
(96, 139),
(96, 140),
(96, 141),
(96, 142),
(96, 143),
(96, 144),
(96, 145),
(96, 146),
(96, 147),
(96, 148),
(96, 149),
(96, 156),
(96, 157),
(96, 223),
(96, 224),
(96, 225),
(96, 226),
(96, 227),
(96, 229),
(96, 230),
(96, 231),
(96, 232),
(96, 234),
(96, 236),
(96, 237),
(96, 249),
(96, 250),
(96, 251),
(96, 252),
(96, 279),
(96, 281),
(96, 282),
(96, 283),
(96, 284),
(96, 285),
(96, 286),
(96, 287),
(97, 101),
(98, 101),
(99, 101),
(100, 101),
(101, 0),
(102, 0),
(103, 0),
(105, 0),
(106, 0),
(107, 0),
(108, 0),
(109, 0),
(110, 0),
(111, 0),
(113, 0),
(114, 0),
(115, 101),
(116, 0),
(117, 0),
(118, 0),
(119, 101),
(120, 0),
(121, 0),
(122, 101),
(122, 102),
(122, 103),
(122, 105),
(122, 106),
(122, 107),
(122, 108),
(122, 109),
(122, 110),
(122, 111),
(122, 112),
(122, 113),
(122, 115),
(122, 116),
(122, 117),
(122, 118),
(122, 119),
(122, 120),
(122, 121),
(122, 122),
(122, 123),
(122, 124),
(122, 125),
(122, 126),
(122, 129),
(122, 131),
(122, 132),
(122, 133),
(122, 134),
(122, 135),
(122, 136),
(122, 137),
(122, 138),
(122, 139),
(122, 140),
(122, 141),
(122, 142),
(122, 143),
(122, 144),
(122, 145),
(122, 146),
(122, 147),
(122, 148),
(122, 149),
(122, 150),
(122, 151),
(122, 152),
(122, 153),
(122, 154),
(122, 156),
(122, 157),
(122, 158),
(122, 159),
(122, 160),
(122, 161),
(122, 162),
(122, 163),
(122, 164),
(122, 165),
(122, 166),
(122, 167),
(122, 168),
(122, 169),
(122, 170),
(122, 171),
(122, 172),
(122, 173),
(122, 174),
(122, 175),
(122, 176),
(122, 177),
(122, 178),
(122, 179),
(122, 180),
(122, 181),
(122, 182),
(122, 183),
(122, 184),
(122, 185),
(122, 186),
(122, 187),
(122, 188),
(122, 189),
(122, 190),
(122, 191),
(122, 192),
(122, 193),
(122, 194),
(122, 195),
(122, 196),
(122, 197),
(122, 198),
(122, 200),
(122, 223),
(122, 224),
(122, 225),
(122, 226),
(122, 227),
(122, 228),
(122, 229),
(122, 230),
(122, 231),
(122, 232),
(122, 234),
(122, 235),
(122, 236),
(122, 237),
(122, 249),
(122, 250),
(122, 251),
(122, 252),
(123, 101),
(123, 102),
(123, 103),
(123, 105),
(123, 106),
(123, 107),
(123, 108),
(123, 109),
(123, 110),
(123, 111),
(123, 112),
(123, 113),
(123, 115),
(123, 116),
(123, 117),
(123, 123),
(123, 125),
(123, 126),
(123, 129),
(123, 131),
(123, 132),
(123, 133),
(123, 134),
(123, 135),
(123, 136),
(123, 137),
(123, 138),
(123, 139),
(123, 140),
(123, 141),
(123, 142),
(123, 143),
(123, 144),
(123, 145),
(123, 146),
(123, 147),
(123, 148),
(123, 149),
(123, 150),
(123, 151),
(123, 152),
(123, 153),
(123, 154),
(123, 156),
(123, 157),
(123, 200),
(123, 223),
(123, 224),
(123, 225),
(123, 226),
(123, 227),
(123, 229),
(123, 230),
(123, 231),
(123, 232),
(123, 234),
(123, 235),
(123, 236),
(123, 237),
(123, 249),
(123, 250),
(123, 251),
(123, 252),
(124, 101),
(124, 102),
(124, 103),
(124, 105),
(124, 106),
(124, 107),
(124, 108),
(124, 109),
(124, 110),
(124, 111),
(124, 112),
(124, 113),
(124, 115),
(124, 116),
(124, 117),
(124, 118),
(124, 119),
(124, 120),
(124, 121),
(124, 122),
(124, 123),
(124, 124),
(124, 125),
(124, 126),
(124, 129),
(124, 131),
(124, 132),
(124, 133),
(124, 134),
(124, 135),
(124, 136),
(124, 137),
(124, 138),
(124, 139),
(124, 140),
(124, 141),
(124, 142),
(124, 143),
(124, 144),
(124, 145),
(124, 146),
(124, 147),
(124, 148),
(124, 149),
(124, 150),
(124, 151),
(124, 152),
(124, 153),
(124, 154),
(124, 156),
(124, 157),
(124, 158),
(124, 159),
(124, 160),
(124, 161),
(124, 162),
(124, 163),
(124, 164),
(124, 165),
(124, 166),
(124, 167),
(124, 168),
(124, 169),
(124, 170),
(124, 171),
(124, 172),
(124, 173),
(124, 174),
(124, 175),
(124, 176),
(124, 177),
(124, 178),
(124, 179),
(124, 180),
(124, 181),
(124, 182),
(124, 183),
(124, 184),
(124, 185),
(124, 186),
(124, 187),
(124, 188),
(124, 189),
(124, 190),
(124, 191),
(124, 192),
(124, 193),
(124, 194),
(124, 195),
(124, 196),
(124, 197),
(124, 198),
(124, 200),
(124, 223),
(124, 224),
(124, 225),
(124, 226),
(124, 227),
(124, 228),
(124, 229),
(124, 230),
(124, 231),
(124, 232),
(124, 234),
(124, 235),
(124, 236),
(124, 237),
(124, 249),
(124, 250),
(124, 251),
(124, 252),
(125, 101),
(125, 106),
(125, 107),
(125, 108),
(125, 109),
(125, 110),
(125, 111),
(125, 112),
(125, 113),
(125, 123),
(125, 125),
(125, 129),
(125, 131),
(125, 132),
(125, 133),
(125, 134),
(125, 135),
(125, 136),
(125, 137),
(125, 138),
(125, 139),
(125, 140),
(125, 141),
(125, 142),
(125, 143),
(125, 144),
(125, 145),
(125, 146),
(125, 147),
(125, 148),
(125, 149),
(125, 156),
(125, 157),
(125, 223),
(125, 224),
(125, 225),
(125, 226),
(125, 227),
(125, 229),
(125, 230),
(125, 231),
(125, 232),
(125, 234),
(125, 236),
(125, 237),
(125, 249),
(125, 250),
(125, 251),
(125, 252),
(126, 101),
(126, 102),
(126, 103),
(126, 105),
(126, 106),
(126, 107),
(126, 108),
(126, 109),
(126, 110),
(126, 111),
(126, 112),
(126, 113),
(126, 115),
(126, 116),
(126, 117),
(126, 118),
(126, 119),
(126, 120),
(126, 121),
(126, 122),
(126, 123),
(126, 124),
(126, 125),
(126, 126),
(126, 129),
(126, 131),
(126, 132),
(126, 133),
(126, 134),
(126, 135),
(126, 136),
(126, 137),
(126, 138),
(126, 139),
(126, 140),
(126, 141),
(126, 142),
(126, 143),
(126, 144),
(126, 145),
(126, 146),
(126, 147),
(126, 148),
(126, 149),
(126, 150),
(126, 151),
(126, 152),
(126, 153),
(126, 154),
(126, 156),
(126, 157),
(126, 158),
(126, 159),
(126, 160),
(126, 161),
(126, 162),
(126, 163),
(126, 164),
(126, 165),
(126, 166),
(126, 167),
(126, 168),
(126, 169),
(126, 170),
(126, 171),
(126, 172),
(126, 173),
(126, 174),
(126, 175),
(126, 176),
(126, 177),
(126, 178),
(126, 179),
(126, 180),
(126, 181),
(126, 182),
(126, 183),
(126, 184),
(126, 185),
(126, 186),
(126, 187),
(126, 188),
(126, 189),
(126, 190),
(126, 191),
(126, 192),
(126, 193),
(126, 194),
(126, 195),
(126, 196),
(126, 197),
(126, 198),
(126, 200),
(126, 223),
(126, 224),
(126, 225),
(126, 226),
(126, 227),
(126, 228),
(126, 229),
(126, 230),
(126, 231),
(126, 232),
(126, 234),
(126, 235),
(126, 236),
(126, 237),
(126, 249),
(126, 250),
(126, 251),
(126, 252),
(127, 101),
(127, 102),
(127, 103),
(127, 105),
(127, 106),
(127, 107),
(127, 108),
(127, 109),
(127, 110),
(127, 111),
(127, 112),
(127, 113),
(127, 115),
(127, 116),
(127, 117),
(127, 118),
(127, 119),
(127, 120),
(127, 121),
(127, 122),
(127, 123),
(127, 124),
(127, 125),
(127, 126),
(127, 129),
(127, 131),
(127, 132),
(127, 133),
(127, 134),
(127, 135),
(127, 136),
(127, 137),
(127, 138),
(127, 139),
(127, 140),
(127, 141),
(127, 142),
(127, 143),
(127, 144),
(127, 145),
(127, 146),
(127, 147),
(127, 148),
(127, 149),
(127, 150),
(127, 151),
(127, 152),
(127, 153),
(127, 154),
(127, 156),
(127, 157),
(127, 158),
(127, 159),
(127, 160),
(127, 161),
(127, 162),
(127, 163),
(127, 164),
(127, 165),
(127, 166),
(127, 167),
(127, 168),
(127, 169),
(127, 170),
(127, 171),
(127, 172),
(127, 173),
(127, 174),
(127, 175),
(127, 176),
(127, 177),
(127, 178),
(127, 179),
(127, 180),
(127, 181),
(127, 182),
(127, 183),
(127, 184),
(127, 185),
(127, 186),
(127, 187),
(127, 188),
(127, 189),
(127, 190),
(127, 191),
(127, 192),
(127, 193),
(127, 194),
(127, 195),
(127, 196),
(127, 197),
(127, 198),
(127, 200),
(127, 223),
(127, 224),
(127, 225),
(127, 226),
(127, 227),
(127, 228),
(127, 229),
(127, 230),
(127, 231),
(127, 232),
(127, 234),
(127, 235),
(127, 236),
(127, 237),
(127, 249),
(127, 250),
(127, 251),
(127, 252),
(129, 101),
(133, 0);

-- --------------------------------------------------------

--
-- Table structure for table `#__newsfeeds`
--

DROP TABLE IF EXISTS `#__newsfeeds`;
CREATE TABLE IF NOT EXISTS `#__newsfeeds` (
  `catid` int(11) NOT NULL DEFAULT '0',
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `link` varchar(200) NOT NULL DEFAULT '',
  `filename` varchar(200) DEFAULT NULL,
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `numarticles` int(10) unsigned NOT NULL DEFAULT '1',
  `cache_time` int(10) unsigned NOT NULL DEFAULT '3600',
  `checked_out` int(10) unsigned NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `rtl` tinyint(4) NOT NULL DEFAULT '0',
  `access` int(10) unsigned DEFAULT NULL,
  `language` char(7) NOT NULL DEFAULT '',
  `params` text NOT NULL,
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` int(10) unsigned NOT NULL DEFAULT '0',
  `created_by_alias` varchar(255) NOT NULL DEFAULT '',
  `modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by` int(10) unsigned NOT NULL DEFAULT '0',
  `metakey` text NOT NULL,
  `metadesc` text NOT NULL,
  `metadata` text NOT NULL,
  `xreference` varchar(50) NOT NULL COMMENT 'A reference to enable linkages to external data sets.',
  `publish_up` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_down` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `idx_access` (`access`),
  KEY `idx_checkout` (`checked_out`),
  KEY `idx_state` (`published`),
  KEY `idx_catid` (`catid`),
  KEY `idx_createdby` (`created_by`),
  KEY `idx_language` (`language`),
  KEY `idx_xreference` (`xreference`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `#__newsfeeds`
--

INSERT INTO `#__newsfeeds` (`catid`, `id`, `name`, `alias`, `link`, `filename`, `published`, `numarticles`, `cache_time`, `checked_out`, `checked_out_time`, `ordering`, `rtl`, `access`, `language`, `params`, `created`, `created_by`, `created_by_alias`, `modified`, `modified_by`, `metakey`, `metadesc`, `metadata`, `xreference`, `publish_up`, `publish_down`) VALUES
(5, 1, 'Youjoomla', 'youjoomla', 'http://www.youjoomla.com/rss/', NULL, 1, 5, 3600, 0, '0000-00-00 00:00:00', 1, 0, 1, '*', '{"show_feed_image":"","show_feed_description":"","show_item_description":"","feed_character_count":"0","newsfeed_layout":""}', '2011-01-29 15:25:11', 42, '', '0000-00-00 00:00:00', 0, '', '', '{"robots":"","rights":""}', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5, 2, 'Joomla', 'joomla', 'http://feeds.joomla.org/JoomlaAnnouncements', NULL, 1, 5, 3600, 0, '0000-00-00 00:00:00', 2, 0, 1, '*', '{"show_feed_image":"0","show_feed_description":"","show_item_description":"","feed_character_count":"200","newsfeed_layout":""}', '2011-01-29 15:29:04', 42, '', '2011-01-29 15:29:40', 42, '', '', '{"robots":"","rights":""}', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `#__overrider`
--

DROP TABLE IF EXISTS `#__overrider`;
CREATE TABLE IF NOT EXISTS `#__overrider` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `constant` varchar(255) NOT NULL,
  `string` text NOT NULL,
  `file` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__schemas`
--

DROP TABLE IF EXISTS `#__schemas`;
CREATE TABLE IF NOT EXISTS `#__schemas` (
  `extension_id` int(11) NOT NULL,
  `version_id` varchar(20) NOT NULL,
  PRIMARY KEY (`extension_id`,`version_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `#__schemas`
--

INSERT INTO `#__schemas` (`extension_id`, `version_id`) VALUES
(700, '2.5.24');

-- --------------------------------------------------------

--
-- Table structure for table `#__template_styles`
--

DROP TABLE IF EXISTS `#__template_styles`;
CREATE TABLE IF NOT EXISTS `#__template_styles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `template` varchar(50) NOT NULL DEFAULT '',
  `client_id` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `home` char(7) NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL DEFAULT '',
  `params` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_template` (`template`),
  KEY `idx_home` (`home`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `#__template_styles`
--

INSERT INTO `#__template_styles` (`id`, `template`, `client_id`, `home`, `title`, `params`) VALUES
(2, 'bluestork', 1, '1', 'Bluestork - Default', '{"useRoundedCorners":"1","showSiteName":"0"}'),
(3, 'atomic', 0, '0', 'Atomic - Default', '{}'),
(4, 'beez_20', 0, '0', 'Beez2 - Default', '{"wrapperSmall":53,"wrapperLarge":72,"logo":"images\\/joomla_black.gif","sitetitle":"Joomla!","sitedescription":"Open Source Content Management","navposition":"left","templatecolor":"personal"}'),
(5, 'hathor', 1, '0', 'Hathor - Default', '{"showSiteName":"0","colourChoice":"","boldText":"0"}'),
(6, 'beez5', 0, '0', 'Beez5 - Default-Fruit Shop', '{"wrapperSmall":"53","wrapperLarge":"72","logo":"images\\/sampledata\\/fruitshop\\/fruits.gif","sitetitle":"Matuna Market ","sitedescription":"Fruit Shop Sample Site","navposition":"left","html5":"0"}'),
(10, 'hdchannel', 0, '1', 'HDChannel - Default', '{"custom_css":"2","yjsg_get_styles":"orange|121212","default_font":"14px","default_font_family":"6","selectors_override":"1","selectors_override_type":"3","fontfacekit_font_family":"Play","top_menu_location":"0","menuName":"mainmenu","default_menu_style":"2","menuendLevel":"0","sub_width":"240px","yjsg_menu_offset":"95","spbox_width":"350","spbtn_poz":"45%","sptran_speed":"500","sidepanel_module_style":"YJsgxhtml","tpopen_text":"Open","tpclose_text":"Close","tpbtn_height":"30","tpbtn_width":"100","tptran_speed":"500","tpan1_custom_chrome":"YJsgxhtml","yjsg_toppanel_width":"20|20|20|20|20|0|0|0|0|0|","tpan2_custom_chrome":"YJsgxhtml","tpan3_custom_chrome":"YJsgxhtml","tpan4_custom_chrome":"YJsgxhtml","tpan5_custom_chrome":"YJsgxhtml","logo_image":"","logo_width":"300px","logo_height":"110px","turn_logo_off":"2","turn_header_block_off":"2","header1_custom_chrome":"YJsgxhtml","yjsg_header_width":"33|33|33|0|0|0|","header2_custom_chrome":"YJsgxhtml","header3_custom_chrome":"YJsgxhtml","top1_custom_chrome":"YJsgxhtml","yjsg_1_width":"20|20|20|20|20|0|0|0|0|0|","top2_custom_chrome":"YJsgxhtml","top3_custom_chrome":"YJsgxhtml","top4_custom_chrome":"YJsgxhtml","top5_custom_chrome":"YJsgxhtml","adv1_custom_chrome":"YJsgxhtml","yjsg_2_width":"20|20|20|20|20|0|0|0|0|0|","adv2_custom_chrome":"YJsgxhtml","adv3_custom_chrome":"YJsgxhtml","adv4_custom_chrome":"YJsgxhtml","adv5_custom_chrome":"YJsgxhtml","user1_custom_chrome":"YJsgxhtml","yjsg_3_width":"20|20|20|20|20|0|0|0|0|0|","user2_custom_chrome":"YJsgxhtml","user3_custom_chrome":"YJsgxhtml","user4_custom_chrome":"YJsgxhtml","user5_custom_chrome":"YJsgxhtml","user6_custom_chrome":"YJsgxhtml","yjsg_4_width":"20|20|20|20|20|0|0|0|0|0|","user7_custom_chrome":"YJsgxhtml","user8_custom_chrome":"YJsgxhtml","user9_custom_chrome":"YJsgxhtml","user10_custom_chrome":"YJsgxhtml","define_itemid":["104"],"css_width":"1200","site_layout":"2","bodytop1_custom_chrome":"YJsgxhtml","yjsg_bodytop_width":"33|33|33|0|0|0|","bodytop2_custom_chrome":"YJsgxhtml","bodytop3_custom_chrome":"YJsgxhtml","insettop_custom_chrome":"YJsgxhtml","maincolumn":"32","insetcolumn_custom_chrome":"YJsgxhtml","insetcolumn":"0","leftcolumn_custom_chrome":"YJsgxhtml","leftcolumn":"34","rightcolumn_custom_chrome":"YJsgxhtml","rightcolumn":"34","yjsg_mainbody_width":"32|0|34|34|0|0|0|0|","bodybottom1_custom_chrome":"YJsgxhtml","yjsg_yjsgbodytbottom_width":"33|33|33|0|0|0|","bodybottom2_custom_chrome":"YJsgxhtml","bodybottom3_custom_chrome":"YJsgxhtml","insetbottom_custom_chrome":"YJsgxhtml","css_width-itemid104":"1200","site_layout-itemid104":"2","bodytop1_custom_chrome-itemid104":"YJsgxhtml","yjsg_bodytop_width-itemid104":"33|33|33|0|0|0|","bodytop2_custom_chrome-itemid104":"YJsgxhtml","bodytop3_custom_chrome-itemid104":"YJsgxhtml","insettop_custom_chrome-itemid104":"YJsgxhtml","maincolumn-itemid104":"46","insetcolumn_custom_chrome-itemid104":"YJsgxhtml","insetcolumn-itemid104":"18","leftcolumn_custom_chrome-itemid104":"YJsgxhtml","leftcolumn-itemid104":"18","rightcolumn_custom_chrome-itemid104":"YJsgxhtml","rightcolumn-itemid104":"18","yjsg_mainbody_width-itemid104":"46|18|18|18|0|0|0|0|","bodybottom1_custom_chrome-itemid104":"YJsgxhtml","yjsg_yjsgbodytbottom_width-itemid104":"33|33|33|0|0|0|","bodybottom2_custom_chrome-itemid104":"YJsgxhtml","bodybottom3_custom_chrome-itemid104":"YJsgxhtml","insetbottom_custom_chrome-itemid104":"YJsgxhtml","user11_custom_chrome":"YJsgxhtml","yjsg_5_width":"20|20|20|20|20|0|0|0|0|0|","user12_custom_chrome":"YJsgxhtml","user13_custom_chrome":"YJsgxhtml","user14_custom_chrome":"YJsgxhtml","user15_custom_chrome":"YJsgxhtml","user16_custom_chrome":"YJsgxhtml","yjsg_6_width":"22|18|20|20|20|0|0|0|0|0|","user17_custom_chrome":"YJsgxhtml","user18_custom_chrome":"YJsgxhtml","user19_custom_chrome":"YJsgxhtml","user20_custom_chrome":"YJsgxhtml","user21_custom_chrome":"YJsgxhtml","yjsg_7_width":"15|20|23|22|20|0|0|0|0|0|","user22_custom_chrome":"YJsgxhtml","user23_custom_chrome":"YJsgxhtml","user24_custom_chrome":"YJsgxhtml","user25_custom_chrome":"YJsgxhtml","bpopen_text":"Open","bpclose_text":"Close","bpbtn_height":"30","bpbtn_width":"100","bptran_speed":"500","bpan1_custom_chrome":"YJsgxhtml","yjsg_botpanel_width":"20|20|20|20|20|0|0|0|0|0|","bpan2_custom_chrome":"YJsgxhtml","bpan3_custom_chrome":"YJsgxhtml","bpan4_custom_chrome":"YJsgxhtml","bpan5_custom_chrome":"YJsgxhtml","ga_switch":"0","ie6notice":"0","nonscript":"0","show_tools":"1","show_fres":"1","show_rtlc":"1","text_direction":"2","turn_seo_off":"2","yjsg_media_on":"0","branding_off":"2","joomlacredit_off":"1","custom_cp":"","joomla_generator_off":"1","validators_off":"1","totop_off":"1","responsive_on":"1","scalable_on":"0","offcanvas_width":"250px","offcanvas_visible":"979px","bootstrap_version":"bootstrap3","compile_css":"0","compiler_compressed":"0","ajax_front_recompile":"1","fp_controll_switch":"2","fp_chars_limit":"3000","fp_after_text":"","component_switch":["101"],"admin_css_time":"1407321485","yjsg_assigements":"","cc_css_headtag":"","cc_js_headtag":"","cc_js_footer":"","cc_after_headtag":"","cc_before_cheadtag":"","cc_after_body":"","cc_before_cbody":""}');

-- --------------------------------------------------------

--
-- Table structure for table `#__updates`
--

DROP TABLE IF EXISTS `#__updates`;
CREATE TABLE IF NOT EXISTS `#__updates` (
  `update_id` int(11) NOT NULL AUTO_INCREMENT,
  `update_site_id` int(11) DEFAULT '0',
  `extension_id` int(11) DEFAULT '0',
  `categoryid` int(11) DEFAULT '0',
  `name` varchar(100) DEFAULT '',
  `description` text NOT NULL,
  `element` varchar(100) DEFAULT '',
  `type` varchar(20) DEFAULT '',
  `folder` varchar(20) DEFAULT '',
  `client_id` tinyint(3) DEFAULT '0',
  `version` varchar(10) DEFAULT '',
  `data` text NOT NULL,
  `detailsurl` text NOT NULL,
  `infourl` text NOT NULL,
  PRIMARY KEY (`update_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Available Updates' AUTO_INCREMENT=63 ;

--
-- Dumping data for table `#__updates`
--

INSERT INTO `#__updates` (`update_id`, `update_site_id`, `extension_id`, `categoryid`, `name`, `description`, `element`, `type`, `folder`, `client_id`, `version`, `data`, `detailsurl`, `infourl`) VALUES
(1, 3, 0, 0, 'Armenian', '', 'pkg_hy-AM', 'package', '', 0, '2.5.8.1', '', 'http://update.joomla.org/language/details/hy-AM_details.xml', ''),
(2, 3, 0, 0, 'Bahasa Indonesia', '', 'pkg_id-ID', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/id-ID_details.xml', ''),
(3, 3, 0, 0, 'Danish', '', 'pkg_da-DK', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/da-DK_details.xml', ''),
(4, 3, 0, 0, 'Khmer', '', 'pkg_km-KH', 'package', '', 0, '2.5.7.1', '', 'http://update.joomla.org/language/details/km-KH_details.xml', ''),
(5, 3, 0, 0, 'Swedish', '', 'pkg_sv-SE', 'package', '', 0, '2.5.10.1', '', 'http://update.joomla.org/language/details/sv-SE_details.xml', ''),
(6, 3, 0, 0, 'Hungarian', '', 'pkg_hu-HU', 'package', '', 0, '2.5.14.1', '', 'http://update.joomla.org/language/details/hu-HU_details.xml', ''),
(7, 3, 0, 0, 'Bulgarian', '', 'pkg_bg-BG', 'package', '', 0, '2.5.7.1', '', 'http://update.joomla.org/language/details/bg-BG_details.xml', ''),
(8, 3, 0, 0, 'French', '', 'pkg_fr-FR', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/fr-FR_details.xml', ''),
(9, 3, 0, 0, 'Italian', '', 'pkg_it-IT', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/it-IT_details.xml', ''),
(10, 3, 0, 0, 'Spanish', '', 'pkg_es-ES', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/es-ES_details.xml', ''),
(11, 3, 0, 0, 'Dutch', '', 'pkg_nl-NL', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/nl-NL_details.xml', ''),
(12, 3, 0, 0, 'Turkish', '', 'pkg_tr-TR', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/tr-TR_details.xml', ''),
(13, 3, 0, 0, 'Ukrainian', '', 'pkg_uk-UA', 'package', '', 0, '2.5.13.11', '', 'http://update.joomla.org/language/details/uk-UA_details.xml', ''),
(14, 3, 0, 0, 'Slovak', '', 'pkg_sk-SK', 'package', '', 0, '2.5.22.1', '', 'http://update.joomla.org/language/details/sk-SK_details.xml', ''),
(15, 3, 0, 0, 'Belarusian', '', 'pkg_be-BY', 'package', '', 0, '2.5.8.1', '', 'http://update.joomla.org/language/details/be-BY_details.xml', ''),
(16, 3, 0, 0, 'Latvian', '', 'pkg_lv-LV', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/lv-LV_details.xml', ''),
(17, 3, 0, 0, 'Estonian', '', 'pkg_et-EE', 'package', '', 0, '2.5.21.1', '', 'http://update.joomla.org/language/details/et-EE_details.xml', ''),
(18, 3, 0, 0, 'Romanian', '', 'pkg_ro-RO', 'package', '', 0, '2.5.11.1', '', 'http://update.joomla.org/language/details/ro-RO_details.xml', ''),
(19, 3, 0, 0, 'Flemish', '', 'pkg_nl-BE', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/nl-BE_details.xml', ''),
(20, 3, 0, 0, 'Macedonian', '', 'pkg_mk-MK', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/mk-MK_details.xml', ''),
(21, 3, 0, 0, 'Japanese', '', 'pkg_ja-JP', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/ja-JP_details.xml', ''),
(22, 3, 0, 0, 'Serbian Latin', '', 'pkg_sr-YU', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/sr-YU_details.xml', ''),
(23, 3, 0, 0, 'Arabic Unitag', '', 'pkg_ar-AA', 'package', '', 0, '2.5.23.1', '', 'http://update.joomla.org/language/details/ar-AA_details.xml', ''),
(24, 3, 0, 0, 'German', '', 'pkg_de-DE', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/de-DE_details.xml', ''),
(25, 3, 0, 0, 'Norwegian Bokmal', '', 'pkg_nb-NO', 'package', '', 0, '2.5.16.1', '', 'http://update.joomla.org/language/details/nb-NO_details.xml', ''),
(26, 3, 0, 0, 'English AU', '', 'pkg_en-AU', 'package', '', 0, '2.5.22.1', '', 'http://update.joomla.org/language/details/en-AU_details.xml', ''),
(27, 3, 0, 0, 'English US', '', 'pkg_en-US', 'package', '', 0, '2.5.22.1', '', 'http://update.joomla.org/language/details/en-US_details.xml', ''),
(28, 3, 0, 0, 'Serbian Cyrillic', '', 'pkg_sr-RS', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/sr-RS_details.xml', ''),
(29, 3, 0, 0, 'Lithuanian', '', 'pkg_lt-LT', 'package', '', 0, '2.5.7.1', '', 'http://update.joomla.org/language/details/lt-LT_details.xml', ''),
(30, 3, 0, 0, 'Albanian', '', 'pkg_sq-AL', 'package', '', 0, '2.5.1.5', '', 'http://update.joomla.org/language/details/sq-AL_details.xml', ''),
(31, 3, 0, 0, 'Czech', '', 'pkg_cs-CZ', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/cs-CZ_details.xml', ''),
(32, 3, 0, 0, 'Persian', '', 'pkg_fa-IR', 'package', '', 0, '2.5.22.1', '', 'http://update.joomla.org/language/details/fa-IR_details.xml', ''),
(33, 3, 0, 0, 'Galician', '', 'pkg_gl-ES', 'package', '', 0, '2.5.7.4', '', 'http://update.joomla.org/language/details/gl-ES_details.xml', ''),
(34, 3, 0, 0, 'Polish', '', 'pkg_pl-PL', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/pl-PL_details.xml', ''),
(35, 3, 0, 0, 'Syriac', '', 'pkg_sy-IQ', 'package', '', 0, '2.5.19.1', '', 'http://update.joomla.org/language/details/sy-IQ_details.xml', ''),
(36, 3, 0, 0, 'Portuguese', '', 'pkg_pt-PT', 'package', '', 0, '2.5.8.1', '', 'http://update.joomla.org/language/details/pt-PT_details.xml', ''),
(37, 3, 0, 0, 'Russian', '', 'pkg_ru-RU', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/ru-RU_details.xml', ''),
(38, 3, 0, 0, 'Hebrew', '', 'pkg_he-IL', 'package', '', 0, '2.5.7.1', '', 'http://update.joomla.org/language/details/he-IL_details.xml', ''),
(39, 3, 0, 0, 'Catalan', '', 'pkg_ca-ES', 'package', '', 0, '2.5.21.1', '', 'http://update.joomla.org/language/details/ca-ES_details.xml', ''),
(40, 3, 0, 0, 'Laotian', '', 'pkg_lo-LA', 'package', '', 0, '2.5.6.1', '', 'http://update.joomla.org/language/details/lo-LA_details.xml', ''),
(41, 3, 0, 0, 'Afrikaans', '', 'pkg_af-ZA', 'package', '', 0, '2.5.16.1', '', 'http://update.joomla.org/language/details/af-ZA_details.xml', ''),
(42, 3, 0, 0, 'Chinese Simplified', '', 'pkg_zh-CN', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/zh-CN_details.xml', ''),
(43, 3, 0, 0, 'Greek', '', 'pkg_el-GR', 'package', '', 0, '2.5.6.1', '', 'http://update.joomla.org/language/details/el-GR_details.xml', ''),
(44, 3, 0, 0, 'Finnish', '', 'pkg_fi-FI', 'package', '', 0, '2.5.20.1', '', 'http://update.joomla.org/language/details/fi-FI_details.xml', ''),
(45, 3, 0, 0, 'Portuguese Brazil', '', 'pkg_pt-BR', 'package', '', 0, '2.5.9.1', '', 'http://update.joomla.org/language/details/pt-BR_details.xml', ''),
(46, 3, 0, 0, 'Chinese Traditional', '', 'pkg_zh-TW', 'package', '', 0, '2.5.19.1', '', 'http://update.joomla.org/language/details/zh-TW_details.xml', ''),
(47, 3, 0, 0, 'Vietnamese', '', 'pkg_vi-VN', 'package', '', 0, '2.5.8.1', '', 'http://update.joomla.org/language/details/vi-VN_details.xml', ''),
(48, 3, 0, 0, 'Kurdish Sorani', '', 'pkg_ckb-IQ', 'package', '', 0, '2.5.9.1', '', 'http://update.joomla.org/language/details/ckb-IQ_details.xml', ''),
(49, 3, 0, 0, 'Bengali', '', 'pkg_bn-BD', 'package', '', 0, '2.5.0.1', '', 'http://update.joomla.org/language/details/bn-BD_details.xml', ''),
(50, 3, 0, 0, 'Bosnian', '', 'pkg_bs-BA', 'package', '', 0, '2.5.22.1', '', 'http://update.joomla.org/language/details/bs-BA_details.xml', ''),
(51, 3, 0, 0, 'Croatian', '', 'pkg_hr-HR', 'package', '', 0, '2.5.13.1', '', 'http://update.joomla.org/language/details/hr-HR_details.xml', ''),
(52, 3, 0, 0, 'Azeri', '', 'pkg_az-AZ', 'package', '', 0, '2.5.7.1', '', 'http://update.joomla.org/language/details/az-AZ_details.xml', ''),
(53, 3, 0, 0, 'Norwegian Nynorsk', '', 'pkg_nn-NO', 'package', '', 0, '2.5.8.1', '', 'http://update.joomla.org/language/details/nn-NO_details.xml', ''),
(54, 3, 0, 0, 'Tamil India', '', 'pkg_ta-IN', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/ta-IN_details.xml', ''),
(55, 3, 0, 0, 'Scottish Gaelic', '', 'pkg_gd-GB', 'package', '', 0, '2.5.7.1', '', 'http://update.joomla.org/language/details/gd-GB_details.xml', ''),
(56, 3, 0, 0, 'Thai', '', 'pkg_th-TH', 'package', '', 0, '2.5.23.1', '', 'http://update.joomla.org/language/details/th-TH_details.xml', ''),
(57, 3, 0, 0, 'Basque', '', 'pkg_eu-ES', 'package', '', 0, '1.7.0.1', '', 'http://update.joomla.org/language/details/eu-ES_details.xml', ''),
(58, 3, 0, 0, 'Uyghur', '', 'pkg_ug-CN', 'package', '', 0, '2.5.7.2', '', 'http://update.joomla.org/language/details/ug-CN_details.xml', ''),
(59, 3, 0, 0, 'Korean', '', 'pkg_ko-KR', 'package', '', 0, '2.5.11.1', '', 'http://update.joomla.org/language/details/ko-KR_details.xml', ''),
(60, 3, 0, 0, 'Hindi', '', 'pkg_hi-IN', 'package', '', 0, '2.5.6.1', '', 'http://update.joomla.org/language/details/hi-IN_details.xml', ''),
(61, 3, 0, 0, 'Welsh', '', 'pkg_cy-GB', 'package', '', 0, '2.5.6.1', '', 'http://update.joomla.org/language/details/cy-GB_details.xml', ''),
(62, 3, 0, 0, 'Swahili', '', 'pkg_sw-KE', 'package', '', 0, '2.5.24.1', '', 'http://update.joomla.org/language/details/sw-KE_details.xml', '');

-- --------------------------------------------------------

--
-- Table structure for table `#__update_categories`
--

DROP TABLE IF EXISTS `#__update_categories`;
CREATE TABLE IF NOT EXISTS `#__update_categories` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '',
  `description` text NOT NULL,
  `parent` int(11) DEFAULT '0',
  `updatesite` int(11) DEFAULT '0',
  PRIMARY KEY (`categoryid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Update Categories' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `#__update_sites`
--

DROP TABLE IF EXISTS `#__update_sites`;
CREATE TABLE IF NOT EXISTS `#__update_sites` (
  `update_site_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '',
  `type` varchar(20) DEFAULT '',
  `location` text NOT NULL,
  `enabled` int(11) DEFAULT '0',
  `last_check_timestamp` bigint(20) DEFAULT '0',
  PRIMARY KEY (`update_site_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='Update Sites' AUTO_INCREMENT=8 ;

--
-- Dumping data for table `#__update_sites`
--

INSERT INTO `#__update_sites` (`update_site_id`, `name`, `type`, `location`, `enabled`, `last_check_timestamp`) VALUES
(1, 'Joomla Core', 'collection', 'http://update.joomla.org/core/list.xml', 1, 1407322763),
(2, 'Joomla Extension Directory', 'collection', 'http://update.joomla.org/jed/list.xml', 1, 1407322763),
(3, 'Accredited Joomla! Translations', 'collection', 'http://update.joomla.org/language/translationlist.xml', 1, 1407322763),
(4, 'K2 Updates', 'extension', 'http://getk2.org/update.xml', 1, 1407322763),
(5, 'JCE Editor Updates', 'extension', 'https://www.joomlacontenteditor.net/index.php?option=com_updates&view=update&format=xml&id=1\n        ', 1, 1407322763),
(6, 'JCE Editor Updates', 'extension', 'https://www.joomlacontenteditor.net/index.php?option=com_updates&view=update&format=xml&id=1&file=extension.xml', 1, 1407322763),
(7, 'YJSG Updates', 'extension', 'http://yjsimplegrid.com/tracker/yjsgupdate.xml', 1, 1407322763);

-- --------------------------------------------------------

--
-- Table structure for table `#__update_sites_extensions`
--

DROP TABLE IF EXISTS `#__update_sites_extensions`;
CREATE TABLE IF NOT EXISTS `#__update_sites_extensions` (
  `update_site_id` int(11) NOT NULL DEFAULT '0',
  `extension_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`update_site_id`,`extension_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Links extensions to update sites';

--
-- Dumping data for table `#__update_sites_extensions`
--

INSERT INTO `#__update_sites_extensions` (`update_site_id`, `extension_id`) VALUES
(1, 700),
(2, 700),
(3, 600),
(4, 10014),
(5, 10034),
(6, 10034),
(7, 10036);

-- --------------------------------------------------------

--
-- Table structure for table `#__weblinks`
--

DROP TABLE IF EXISTS `#__weblinks`;
CREATE TABLE IF NOT EXISTS `#__weblinks` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `catid` int(11) NOT NULL DEFAULT '0',
  `sid` int(11) NOT NULL DEFAULT '0',
  `title` varchar(250) NOT NULL DEFAULT '',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `url` varchar(250) NOT NULL DEFAULT '',
  `description` text NOT NULL,
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `hits` int(11) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `checked_out` int(11) NOT NULL DEFAULT '0',
  `checked_out_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ordering` int(11) NOT NULL DEFAULT '0',
  `archived` tinyint(1) NOT NULL DEFAULT '0',
  `approved` tinyint(1) NOT NULL DEFAULT '1',
  `access` int(11) NOT NULL DEFAULT '1',
  `params` text NOT NULL,
  `language` char(7) NOT NULL DEFAULT '',
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `created_by` int(10) unsigned NOT NULL DEFAULT '0',
  `created_by_alias` varchar(255) NOT NULL DEFAULT '',
  `modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by` int(10) unsigned NOT NULL DEFAULT '0',
  `metakey` text NOT NULL,
  `metadesc` text NOT NULL,
  `metadata` text NOT NULL,
  `featured` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'Set if link is featured.',
  `xreference` varchar(50) NOT NULL COMMENT 'A reference to enable linkages to external data sets.',
  `publish_up` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `publish_down` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `idx_access` (`access`),
  KEY `idx_checkout` (`checked_out`),
  KEY `idx_state` (`state`),
  KEY `idx_catid` (`catid`),
  KEY `idx_createdby` (`created_by`),
  KEY `idx_featured_catid` (`featured`,`catid`),
  KEY `idx_language` (`language`),
  KEY `idx_xreference` (`xreference`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `#__weblinks`
--

INSERT INTO `#__weblinks` (`id`, `catid`, `sid`, `title`, `alias`, `url`, `description`, `date`, `hits`, `state`, `checked_out`, `checked_out_time`, `ordering`, `archived`, `approved`, `access`, `params`, `language`, `created`, `created_by`, `created_by_alias`, `modified`, `modified_by`, `metakey`, `metadesc`, `metadata`, `featured`, `xreference`, `publish_up`, `publish_down`) VALUES
(1, 6, 0, 'Youjoomla.com', 'youjoomlacom', 'http://www.youjoomla.com', 'Youjoomla.com is Web Development Company specializing in Joomla Templates and Extensions Development. We provide Joomla Quality Service since 2006. Beside our Joomla portfolio we have good experience in other CMS templating like WordPress. and we also offer  clean XHTML/CSS web templates services.', '0000-00-00 00:00:00', 2, 1, 0, '0000-00-00 00:00:00', 1, 0, 1, 1, '{"target":"1","width":"","height":"","count_clicks":""}', '*', '2011-01-29 14:02:16', 42, '', '2011-01-29 14:04:41', 42, '', '', '', 0, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2, 6, 0, 'Joomla!', 'joomla', 'http://www.joomla.org', 'Joomla! - the dynamic portal engine and content management system.', '0000-00-00 00:00:00', 0, 1, 0, '0000-00-00 00:00:00', 2, 0, 1, 1, '{"target":"1","width":"","height":"","count_clicks":""}', '*', '2011-01-29 14:04:02', 42, '', '2011-01-29 14:04:31', 42, '', '', '', 0, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `#__wf_profiles`
--

DROP TABLE IF EXISTS `#__wf_profiles`;
CREATE TABLE IF NOT EXISTS `#__wf_profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `users` text NOT NULL,
  `types` text NOT NULL,
  `components` text NOT NULL,
  `area` tinyint(3) NOT NULL,
  `device` varchar(255) NOT NULL,
  `rows` text NOT NULL,
  `plugins` text NOT NULL,
  `published` tinyint(3) NOT NULL,
  `ordering` int(11) NOT NULL,
  `checked_out` tinyint(3) NOT NULL,
  `checked_out_time` datetime NOT NULL,
  `params` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `#__wf_profiles`
--

INSERT INTO `#__wf_profiles` (`id`, `name`, `description`, `users`, `types`, `components`, `area`, `device`, `rows`, `plugins`, `published`, `ordering`, `checked_out`, `checked_out_time`, `params`) VALUES
(1, 'Default', 'Default Profile for all users', '', '3,4,5,6,7,8', '', 0, 'desktop,tablet,phone', 'help,newdocument,undo,redo,spacer,bold,italic,underline,strikethrough,justifyfull,justifycenter,justifyleft,justifyright,spacer,blockquote,formatselect,styleselect,removeformat,cleanup;fontselect,fontsizeselect,forecolor,backcolor,spacer,clipboard,indent,outdent,lists,sub,sup,textcase,charmap,hr;directionality,fullscreen,preview,source,print,searchreplace,spacer,table;visualaid,visualchars,visualblocks,nonbreaking,style,xhtmlxtras,anchor,unlink,link,imgmanager,spellchecker,article', 'contextmenu,browser,inlinepopups,media,help,clipboard,searchreplace,directionality,fullscreen,preview,source,table,textcase,print,style,nonbreaking,visualchars,visualblocks,xhtmlxtras,imgmanager,anchor,link,spellchecker,article,lists,charmap,formatselect,styleselect,fontselect,fontsizeselect,fontcolor', 1, 1, 0, '0000-00-00 00:00:00', ''),
(2, 'Front End', 'Sample Front-end Profile', '', '3,4,5', '', 1, 'desktop,tablet,phone', 'help,newdocument,undo,redo,spacer,bold,italic,underline,strikethrough,justifyfull,justifycenter,justifyleft,justifyright,spacer,formatselect,styleselect;clipboard,searchreplace,indent,outdent,lists,cleanup,charmap,removeformat,hr,sub,sup,textcase,nonbreaking,visualchars,visualblocks;fullscreen,preview,print,visualaid,style,xhtmlxtras,anchor,unlink,link,imgmanager,spellchecker,article', 'contextmenu,inlinepopups,help,clipboard,searchreplace,fullscreen,preview,print,style,textcase,nonbreaking,visualchars,visualblocks,xhtmlxtras,imgmanager,anchor,link,spellchecker,article,lists,charmap,formatselect,styleselect', 0, 2, 0, '0000-00-00 00:00:00', ''),
(3, 'Blogger', 'Simple Blogging Profile', '', '3,4,5,6,7,8', '', 0, 'desktop,tablet,phone', 'bold,italic,strikethrough,lists,blockquote,spacer,justifyleft,justifycenter,justifyright,spacer,link,unlink,imgmanager,article,spellchecker,fullscreen,kitchensink;formatselect,underline,justifyfull,forecolor,clipboard,removeformat,charmap,indent,outdent,undo,redo,help', 'link,imgmanager,article,spellchecker,fullscreen,kitchensink,clipboard,contextmenu,inlinepopups,lists,charmap,formatselect,fontcolor', 0, 3, 0, '0000-00-00 00:00:00', '{"editor":{"toggle":"0"}}'),
(4, 'Mobile', 'Sample Mobile Profile', '', '3,4,5,6,7,8', '', 0, 'tablet,phone', 'undo,redo,spacer,bold,italic,underline,formatselect,spacer,justifyleft,justifycenter,justifyfull,justifyright,spacer,fullscreen,kitchensink;styleselect,lists,spellchecker,article,link,unlink', 'fullscreen,kitchensink,spellchecker,article,link,inlinepopups,lists,formatselect,styleselect', 0, 4, 0, '0000-00-00 00:00:00', '{"editor":{"toolbar_theme":"mobile","resizing":"0","resize_horizontal":"0","resizing_use_cookie":"0","toggle":"0","links":{"popups":{"default":"","jcemediabox":{"enable":"0"},"window":{"enable":"0"}}}}}');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
