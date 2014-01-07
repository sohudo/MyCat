/**
 * 
 */
package com.talent.balance.conf;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.balance.common.JsonWrap;

/**
 * 
 * @filename:	 com.talent.balance.conf.ServerConf
 * @copyright:   Copyright (c)2010
 * @company:     talent
 * @author:      谭耀武
 * @version:     1.0
 * @create time: 2013年12月20日 下午4:39:18
 * @record
 * <table cellPadding="3" cellSpacing="0" style="width:600px">
 * <thead style="font-weight:bold;background-color:#e3e197">
 * 	<tr>   <td>date</td>	<td>author</td>		<td>version</td>	<td>description</td></tr>
 * </thead>
 * <tbody style="background-color:#ffffeb">
 * 	<tr><td>2013年12月20日</td>	<td>谭耀武</td>	<td>1.0</td>	<td>create</td></tr>
 * </tbody>
 * </table>
 */
public class FrontendConf
{
	private static Logger log = LoggerFactory.getLogger(FrontendConf.class);

	private String bindIp;

	private int bindPort = 9898;
	
	private String protocol = "BalanceFrontendServer";
	
	private byte byteOrder;
	
	private HttpServerConf httpServerConf;

	private static FrontendConf instance = null;

	/**
	 * 
	 * @return
	 */
	public static FrontendConf getInstance()
	{
		if (instance == null)
		{
			synchronized (log)
			{
				if (instance == null)
				{
					try
					{
						instance = JsonWrap.toBean(FileUtils.readFileToString(new File("./conf/frontend-conf.json"), "utf-8"), FrontendConf.class);
					} catch (Exception e)
					{
						log.error("", e);
						throw new RuntimeException(e);
					}
				}
			}
		}

		return instance;
	}

	/**
	 * 
	 */
	public FrontendConf()
	{

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FrontendConf ii = FrontendConf.getInstance();
		System.out.println();
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

	public HttpServerConf getHttpServerConf()
	{
		return httpServerConf;
	}

	public void setHttpServerConf(HttpServerConf httpServerConf)
	{
		this.httpServerConf = httpServerConf;
	}

	public byte getByteOrder()
	{
		return byteOrder;
	}

	public void setByteOrder(byte byteOrder)
	{
		this.byteOrder = byteOrder;
	}

}
