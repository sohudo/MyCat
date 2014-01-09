/**
 * 
 */
package com.talent.mysql.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.talent.mysql.packet.response.HandshakePacket;
import com.talent.nio.communicate.ChannelContext;

/**
 * 
 * @filename:	 com.talent.mysql.ext.MysqlExt
 * @copyright:   Copyright (c)2010
 * @company:     talent
 * @author:      谭耀武
 * @version:     1.0
 * @create time: 2013年12月26日 下午3:34:40
 * @record
 * <table cellPadding="3" cellSpacing="0" style="width:600px">
 * <thead style="font-weight:bold;background-color:#e3e197">
 * 	<tr>   <td>date</td>	<td>author</td>		<td>version</td>	<td>description</td></tr>
 * </thead>
 * <tbody style="background-color:#ffffeb">
 * 	<tr><td>2013年12月26日</td>	<td>谭耀武</td>	<td>1.0</td>	<td>create</td></tr>
 * </tbody>
 * </table>
 */
public class MysqlExt
{
	private static Logger log = LoggerFactory.getLogger(MysqlExt.class);

	private static final String HandshakePacketKey = "HandshakePacketKey";

	public static void setHandshakePacket(ChannelContext backendChannelContext, HandshakePacket handshakePacket)
	{
		backendChannelContext.addProperty(HandshakePacketKey, handshakePacket);
	}

	public static HandshakePacket getHandshakePacket(ChannelContext backendChannelContext)
	{
		return (HandshakePacket) backendChannelContext.getProperty(HandshakePacketKey);
	}

	/**
	 * 
	 */
	public MysqlExt()
	{

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

	}
}