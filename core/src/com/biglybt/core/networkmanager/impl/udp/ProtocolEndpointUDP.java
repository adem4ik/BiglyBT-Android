/*
 * Created on 21 Jun 2006
 * Created by Paul Gardner
 * Copyright (C) Azureus Software, Inc, All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package com.biglybt.core.networkmanager.impl.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import com.biglybt.core.networkmanager.*;
import com.biglybt.core.networkmanager.Transport.ConnectListener;
import com.biglybt.core.util.AddressUtils;

public class
ProtocolEndpointUDP
	implements ProtocolEndpoint
{
	public static void
	register()
	{
		ProtocolEndpointFactory.registerHandler(
			new ProtocolEndpointHandler()
			{
				@Override
				public int
				getType()
				{
					return( ProtocolEndpoint.PROTOCOL_UDP );
				}

				@Override
				public ProtocolEndpoint
				create(
					InetSocketAddress		address )
				{
					return( new ProtocolEndpointUDP( address ));
				}

				@Override
				public ProtocolEndpoint
				create(
					ConnectionEndpoint		connection_endpoint,
					InetSocketAddress		address )
				{
					return( new ProtocolEndpointUDP( connection_endpoint, address ));
				}
			});
	}

	private ConnectionEndpoint		ce;
	private final InetSocketAddress		address;

	ProtocolEndpointUDP(
		ConnectionEndpoint		_ce,
		InetSocketAddress		_address )
	{
		ce		= _ce;
		address	= _address;

		ce.addProtocol( this );
	}

	ProtocolEndpointUDP(
		InetSocketAddress		_address )
	{
		ce		= new ConnectionEndpoint(_address );
		address	= _address;

		ce.addProtocol( this );
	}

	@Override
	public void
	setConnectionEndpoint(
		ConnectionEndpoint		_ce )
	{
		ce	= _ce;

		ce.addProtocol( this );
	}

	@Override
	public int
	getType()
	{
		return( PROTOCOL_UDP );
	}

	@Override
	public InetSocketAddress
	getAddress()
	{
		return( address );
	}

	@Override
	public InetSocketAddress
	getAdjustedAddress(
		boolean to_lan )
	{
		return( AddressUtils.adjustUDPAddress( address, to_lan ));
	}

	@Override
	public ConnectionEndpoint
	getConnectionEndpoint()
	{
		return( ce );
	}

	@Override
	public Transport
	connectOutbound(
		boolean				connect_with_crypto,
		boolean 			allow_fallback,
		byte[][]			shared_secrets,
		ByteBuffer			initial_data,
		int					priority,
		ConnectListener 	listener )
	{
		UDPTransport t = new UDPTransport( this, shared_secrets );

		t.connectOutbound( initial_data, listener, priority );

		return( t );
	}

	@Override
	public String
	getDescription()
	{
		return( address.toString());
	}
}
