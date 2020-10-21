/*
 * File    : IPBanned.java
 * Created : 08-Jan-2007
 * By      : jstockall
 * Copyright (C) Azureus Software, Inc, All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details ( see the LICENSE file ).
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.biglybt.pifimpl.local.ipfilter;

/**
 * @author jstockall
 *
 */

import com.biglybt.core.ipfilter.BannedIp;
import com.biglybt.pif.ipfilter.IPBanned;

public class
IPBannedImpl
	implements IPBanned
{
	protected BannedIp	banned;

	protected
	IPBannedImpl(
		BannedIp	_blocked )
	{
		banned	= _blocked;
	}

	@Override
	public String
	getBannedIP()
	{
		return( banned.getIp());
	}

	@Override
	public String
	getBannedTorrentName()
	{
		return(banned.getTorrentName());
	}

	@Override
	public long
	getBannedTime()
	{
		return( banned.getBanningTime());
	}
}
