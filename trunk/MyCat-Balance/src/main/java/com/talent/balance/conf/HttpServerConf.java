/**
 * 
 */
package com.talent.balance.conf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @filename:	 com.talent.balance.conf.HttpServerConf
 * @copyright:   Copyright (c)2010
 * @company:     talent
 * @author:      谭耀武
 * @version:     1.0
 * @create time: 2013年12月25日 上午9:31:00
 * @record
 * <table cellPadding="3" cellSpacing="0" style="width:600px">
 * <thead style="font-weight:bold;background-color:#e3e197">
 * 	<tr>   <td>date</td>	<td>author</td>		<td>version</td>	<td>description</td></tr>
 * </thead>
 * <tbody style="background-color:#ffffeb">
 * 	<tr><td>2013年12月25日</td>	<td>谭耀武</td>	<td>1.0</td>	<td>create</td></tr>
 * </tbody>
 * </table>
 */
public class HttpServerConf
{
	private static Logger log = LoggerFactory.getLogger(HttpServerConf.class);
	
	private String bindIp = null;
	private int bindPort;
	private String protocol = null;

	/**
	 * 
	 */
	public HttpServerConf()
	{
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
	}

	public String getBindIp()
	{
		return bindIp;
	}

	public void setBindIp(String bindIp)
	{
		this.bindIp = bindIp;
	}

	public int getBindPort()
	{
		return bindPort;
	}

	public void setBindPort(int bindPort)
	{
		this.bindPort = bindPort;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}
}


