/*-
 * Copyright (C) 2008 Erik Larsson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catacombae.hfsexplorer.types.finder;

import java.io.PrintStream;
import org.catacombae.csjc.StructElements;
import org.catacombae.csjc.structelements.Dictionary;
import org.catacombae.csjc.structelements.DictionaryBuilder;
import org.catacombae.hfsexplorer.Util;
import org.catacombae.hfsexplorer.types.carbon.Point;
import org.catacombae.hfsexplorer.types.carbon.Rect;

/** This class was generated by CStructToJavaClass. */
public class DInfo implements StructElements {
    /*
     * struct DInfo
     * size: 2 bytes
     * description: 
     * 
     * BP  Size  Type    Identifier  Description                                                                                                       
     * ------------------------------------------------------------------------------------------------------------------------------------------------
     * 0   8     Rect    frRect      The rectangle for the window that the Finder displays when the user opens the folder.                             
     * -1  2     UInt16  frFlags     Reserved.                                                                                                         
     * 1   4     Point   frLocation  Location of the folder in the parent window.                                                                      
     * 0   2     SInt16  frView      The manner in which folders are displayed; this is set by the user with commands from the View menu of the Finder.
     */
    
    public static final int STRUCTSIZE = 2;
    
    private final Rect frRect;
    private final byte[] frFlags = new byte[2];
    private final Point frLocation;
    private final byte[] frView = new byte[2];
    
    public DInfo(byte[] data, int offset) {
	frRect = new Rect(data, offset+0);
	System.arraycopy(data, offset+-1, frFlags, 0, 2);
	frLocation = new Point(data, offset+1);
	System.arraycopy(data, offset+0, frView, 0, 2);
    }
    
    public static int length() { return STRUCTSIZE; }
    
    /** The rectangle for the window that the Finder displays when the user opens the folder. */
    public Rect getFrRect() { return frRect; }
    /** Reserved. */
    public short getFrFlags() { return Util.readShortBE(frFlags); }
    /** Location of the folder in the parent window. */
    public Point getFrLocation() { return frLocation; }
    /** The manner in which folders are displayed; this is set by the user with commands from the View menu of the Finder. */
    public short getFrView() { return Util.readShortBE(frView); }
    
    public void printFields(PrintStream ps, String prefix) {
	ps.println(prefix + " frRect: ");
	getFrRect().print(ps, prefix+"  ");
	ps.println(prefix + " frFlags: " + getFrFlags());
	ps.println(prefix + " frLocation: ");
	getFrLocation().print(ps, prefix+"  ");
	ps.println(prefix + " frView: " + getFrView());
    }
    
    public void print(PrintStream ps, String prefix) {
	ps.println(prefix + "DInfo:");
	printFields(ps, prefix);
    }
    
    public byte[] getBytes() {
	byte[] result = new byte[STRUCTSIZE];
	byte[] tempData;
	int offset = 0;
	tempData = frRect.getBytes();
	System.arraycopy(tempData, 0, result, offset, tempData.length); offset += tempData.length;
	System.arraycopy(frFlags, 0, result, offset, frFlags.length); offset += frFlags.length;
	tempData = frLocation.getBytes();
	System.arraycopy(tempData, 0, result, offset, tempData.length); offset += tempData.length;
	System.arraycopy(frView, 0, result, offset, frView.length); offset += frView.length;
	return result;
    }

    @Override
    public Dictionary getStructElements() {
        DictionaryBuilder db = new DictionaryBuilder(DInfo.class.getSimpleName());
        db.add("frRect", frRect.getStructElements());
        db.addUIntBE("frFlags", frFlags);
        db.add("frLocation", frLocation.getStructElements());
        db.addUIntBE("frView", frView);
        
        return db.getResult();
    }
}
