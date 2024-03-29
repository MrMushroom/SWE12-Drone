/**
 *  Catroid: An on-device graphical programming language for Android devices
 *  Copyright (C) 2010-2011 The Catroid Team
 *  (<http://code.google.com/p/catroid/wiki/Credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://www.catroid.org/catroid_license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *   
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.tugraz.ist.catroid.content.bricks;

import java.io.Serializable;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import at.tugraz.ist.catroid.content.Sprite;

public interface Brick extends Serializable {

	//use bitwise | for using multiple ressources in a brick
	public static final int NO_RESOURCES = 0x0;
	//public static final int SOUND_MANAGER = 0x1;
	public static final int TEXT_TO_SPEECH = 0x2;
	public static final int BLUETOOTH_LEGO_NXT = 0x4;
	public static final int BLUETOOTH_ARDUINO = 0x8;

	public static final int WIFI_DRONE = 0x10;

	public void execute();

	public Sprite getSprite();

	public View getView(Context context, int brickId, BaseAdapter adapter);

	public View getPrototypeView(Context context);

	public Brick clone();

	public int getRequiredResources();

}
