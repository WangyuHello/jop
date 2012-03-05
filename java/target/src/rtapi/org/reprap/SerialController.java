/*
  This file is part of JOP, the Java Optimized Processor
    see <http://www.jopdesign.com/>

  Copyright (C) 2001-2008, Martin Schoeberl (martin@jopdesign.com)

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
	Author: T�rur Biskopst� Str�m (torur.strom@gmail.com)
*/
package org.reprap;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RelativeTime;
import javax.safetycritical.PeriodicEventHandler;
import javax.safetycritical.StorageParameters;
import com.jopdesign.io.*;

public class SerialController extends PeriodicEventHandler
{
	private static SerialController instance;
	
	public static SerialController getInstance()
	{
		if(instance == null)
		{
			instance = new SerialController();
		}
		return instance;
	}
	
	private SerialController()
	{
		super(new PriorityParameters(1),
			  new PeriodicParameters(null, new RelativeTime(1,1000)),
			  new StorageParameters(10, null, 0, 0), 5);
	}
	
	char[] chars = new char[32];
	// First int contains number and second contains the length of number in char[]
	int[] intParseResults = new int[2];
	boolean cmdRdy = false;
	boolean comment = false;
	CharacterBuffer buffer;
	boolean initialized = false;
	
	@Override
	public void handleAsyncEvent()
	{
		if(!initialized)
		{
			buffer = CharacterBuffer.getEmptyBuffer();
			System.out.println("start");
			initialized = true;
		}
		//Fill a buffer if possible, but no while loop to avoid missing deadline
		for (int i = buffer.length; i < CharacterBuffer.BUFFER_WIDTH; i++) 
		{
			if(buffer == null)
			{
				buffer = CharacterBuffer.getEmptyBuffer();
				if(buffer == null)
				{
					//No empty buffers so cannot store read characters
					return;
				}
			}
			char character;
			try
			{
				if(System.in.available() == 0)
				{
					//No input
					return;
				}
				character = (char)System.in.read();
			}
			catch(Exception e)
			{
				System.out.print("ERROR:");
				System.out.print(e.getMessage());
				return;
			}
			if(character == ';')
			{
				comment = true;
			}
			else if(character == '\n' || character == '\r')
			{
				comment = false;
				if(buffer.length > 0)
				{
					buffer.returnToPool();
					buffer = null;
				}
			}
			else if(buffer.length < CharacterBuffer.BUFFER_WIDTH && !comment)
			{
				//Ignore too long command lines. Hopefully full of comments
				buffer.chars[buffer.length++] = character;
			}
		}
	}
}