/*
 * NewCategoryToken.java
 * Copyright 2013 (C) James Dempsey <jdempsey@users.sourceforge.net>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Created on 01/06/2013
 *
 * $Id$
 */
package plugin.lsttokens.gamemode.migrate;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import pcgen.cdom.base.Constants;
import pcgen.core.system.MigrationRule;
import pcgen.persistence.lst.MigrationLstToken;
import pcgen.util.Logging;

/**
 * NewCategoryToken
 * 
 * 
 * @author James Dempsey <jdempsey@users.sourceforge.net>
 * @version $Revision$
 */
public class NewCategoryToken implements MigrationLstToken
{
	private Pattern invalidKeyPattern = Pattern.compile(".*[,|\\||\\\\|:|;|\\.|%|\\*|=|\\[|\\]].*");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTokenName()
	{
		return "NEWCATEGORY";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean parse(MigrationRule migrationRule, String value,
		String gameModeName)
	{
		if (StringUtils.isBlank(value))
		{
			Logging.log(Logging.LST_ERROR, "Invalid empty " + getTokenName() + " value."); 
			return false;
		}
		if (invalidKeyPattern.matcher(value).matches())
		{
			Logging.log(Logging.LST_ERROR, "Invalid characters in value '"
				+ value + "' of " + getTokenName() + Constants.COLON + value);
			return false;
		}
		migrationRule.setNewCategory(value);
		return true;
	}

}
