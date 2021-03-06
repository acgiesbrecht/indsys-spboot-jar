/*
 * DummyLogger.java
 * 
 * Copyright (c) 2013, AlternativeVision. All rights reserved.
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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package org.alternativevision.gpx.log;

/**
 * This class is used as default Logging Wrapper in case no other wrapper is specified.
 * It doesn't output anything. It is just used as a failback mechanism for all the Logging API.
 * <br/>
 * This Logging Wrapper can also be used in production, where no logs are necessary to be collected.  
 */
public class DummyLogger implements ILogger {
	
	public void debug(Object log) {}

	public void info(Object log) {}

	public void error(Object log) {}

	public void error(Object log, Throwable th) {}

	public void warn(Object log) {}

	public void setTag(String tag) {}

}
