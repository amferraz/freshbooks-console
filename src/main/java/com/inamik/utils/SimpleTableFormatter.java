/*
 * iNamik TableFormatter
 * Copyright (C) 2005 David Farrell (davidpfarrell@yahoo.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.inamik.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * SimpleTableFormatter
 * Created on Oct 18, 2005
 * @author Dave
 */
public final class SimpleTableFormatter extends AbstractTableFormatter implements TableFormatter
{
	private boolean border = false;

	/**
	 * Constructor
	 */
	public SimpleTableFormatter()
	{
		super();
	}

	/**
	 * Constructor
	 * @param border Print rows/tables with borders
	 */
	public SimpleTableFormatter(boolean border)
	{
		this();

		this.border = border;
	}

	/*
	 * (non-Javadoc)
	 * @see com.inamik.utils.TableFormatter#getTableWidth()
	 */
	public int getTableWidth()
	{
		int width = super.getTableWidth();
		
		if (border == true)
		{
			width += 2;
			
			if (getColumnCount() > 1)
			{
				width += getColumnCount() - 1;
			}
		}

		return width;
	}

	/*
	 * (non-Javadoc)
	 * @see com.inamik.utils.TableFormatter#getTableHeight()
	 */
	public int getTableHeight()
	{
		int height = super.getTableHeight();
		
		if (border == true)
		{
			height += 2;
			
			if (getRowCount() > 1)
			{
				height += getRowCount() - 1;
			}
		}

		return height;
	}

	/*
	 * (non-Javadoc)
	 * @see com.inamik.utils.TableFormatter#getFormattedRow(int)
	 */
	public String[] getFormattedRow(int rowIndex)
	{
		if (rowIndex < 0 || rowIndex >= getRowCount())
		{
			throw new IllegalArgumentException("rowIndex");
		}

		int cellHeight = getRowHeight(rowIndex);

		List rowLines = new ArrayList(cellHeight);

		for (int i = 0; i < cellHeight; ++i)
		{
			StringBuffer buffer = new StringBuffer();

			if (border == true)
			{
				buffer.append('|');
			}

			rowLines.add(buffer);
		}

		if (rowLines.size() != cellHeight)
		{
			throw new IllegalStateException("rowLines.size()");
		}

		for (int columnIndex = 0, columnCount = getColumnCount(); columnIndex < columnCount; ++columnIndex)
		{
			String[] cell = getFormattedCell(rowIndex, columnIndex);

			if (cell.length != cellHeight)
			{
				throw new IllegalStateException("cell.size()");
			}

			for (int i = 0; i < cellHeight; ++i)
			{
				StringBuffer buffer = (StringBuffer)rowLines.get(i);

				if (columnIndex > 0)
				{
					if (border == true)
					{
						buffer.append('|');
					}
//					else
//					{
//						buffer.append(' ');
//					}
				}

				buffer.append(cell[i]);
			}
		}

		if (border == true)
		{
			for (int i = 0; i < cellHeight; ++i)
			{
				StringBuffer buffer = (StringBuffer)rowLines.get(i);

				buffer.append('|');
			}
		}

		if (rowLines.size() != cellHeight)
		{
			throw new IllegalStateException("rowLines.size()");
		}

		List result = new ArrayList(cellHeight);

		for (int i = 0; i < cellHeight; ++i)
		{
			StringBuffer buffer = (StringBuffer)rowLines.get(i);

			result.add(buffer.toString());
		}

		if (result.size() != cellHeight)
		{
			throw new IllegalStateException("result.size()");
		}

		return (String[])result.toArray(new String[result.size()]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.inamik.utils.TableFormatter#getFormattedTable()
	 */
	public String[] getFormattedTable()
	{
		List result = new ArrayList();

		String borderText = null;

		if (border == true)
		{
			borderText = getFormattedBorder();
			result.add(borderText);
		}

		for (int rowIndex = 0, rowCount = getRowCount(); rowIndex < rowCount; ++rowIndex)
		{
			if (rowIndex > 0)
			{
				if (border == true)
				{
					result.add(borderText);
				}
			}

			String[] row = getFormattedRow(rowIndex);

			for (int i = 0, size=row.length; i < size; ++i)
			{
				result.add(row[i]);
			}
		}

		if (border == true)
		{
			result.add(borderText);
		}

		return (String[])result.toArray(new String[result.size()]);
	}

	/**
	 * getFormattedBorder
	 */
	private String getFormattedBorder()
	{
		StringBuffer result = new StringBuffer();

		result.append('+');

		for (int columnIndex = 0, columnCount = getColumnCount(); columnIndex < columnCount; ++columnIndex)
		{
			if (columnIndex > 0)
			{
				result.append('+');
			}

			for (int i = 0; i < getColumnWidth(columnIndex); ++i)
			{
				result.append('-');
			}
		}

		result.append('+');

		return result.toString();
	}
}
