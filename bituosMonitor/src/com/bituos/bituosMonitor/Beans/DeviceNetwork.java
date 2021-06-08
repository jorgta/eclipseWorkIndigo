/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.bituosMonitor.Beans;


/**
 * @author dsa@bituos.com
*/

public class DeviceNetwork{

	private String adapterName;
	private String adapterDesc;
	private String adapterType;
	private String iPAddress;
	private String iPMask;
	private String macAddress;
	private String gateway;
	private String dhcpEnabled;
	private String dhcpServer;
	private long bytesReceived;
	private long bytesSent;
	private long speed;
	
	public String getAdapterName() {
		return adapterName;
	}
	public void setAdapterName(String adapterName) {
		this.adapterName = adapterName;
	}
	public String getAdapterDesc() {
		return adapterDesc;
	}
	public void setAdapterDesc(String adapterDesc) {
		this.adapterDesc = adapterDesc;
	}
	public String getAdapterType() {
		return adapterType;
	}
	public void setAdapterType(String adapterType) {
		this.adapterType = adapterType;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public String getiPMask() {
		return iPMask;
	}
	public void setiPMask(String iPMask) {
		this.iPMask = iPMask;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getDhcpEnabled() {
		return dhcpEnabled;
	}
	public void setDhcpEnabled(String dhcpEnabled) {
		this.dhcpEnabled = dhcpEnabled;
	}
	public String getDhcpServer() {
		return dhcpServer;
	}
	public void setDhcpServer(String dhcpServer) {
		this.dhcpServer = dhcpServer;
	}
	public long getBytesReceived() {
		return bytesReceived;
	}
	public void setBytesReceived(long bytesReceived) {
		this.bytesReceived = bytesReceived;
	}
	public long getBytesSent() {
		return bytesSent;
	}
	public void setBytesSent(long bytesSent) {
		this.bytesSent = bytesSent;
	}
	public long getSpeed() {
		return speed;
	}
	public void setSpeed(long speed) {
		this.speed = speed;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	
	
	
	
}
