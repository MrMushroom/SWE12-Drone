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

package at.tugraz.ist.catroid.plugin.Drone.bricks;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import at.tugraz.ist.catroid.ProjectManager;
import at.tugraz.ist.catroid.R;
import at.tugraz.ist.catroid.common.Consts;
import at.tugraz.ist.catroid.content.Sprite;
import at.tugraz.ist.catroid.content.bricks.Brick;
import at.tugraz.ist.catroid.plugin.Drone.DroneHandler;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class DroneStartVideoRecorderBrick implements Brick {
	private static final long serialVersionUID = 1L;
	private Sprite sprite;

	@XStreamOmitField
	private transient View view;

	public DroneStartVideoRecorderBrick(Sprite sprite) {
		this.sprite = sprite;
	}

	public void execute() {
		String path = null;
		try {
			path = Consts.DEFAULT_ROOT + "/" + ProjectManager.getInstance().getCurrentProject().getName() + "/video_"
					+ new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".mp4";
		} catch (Exception e) {
			// TODO: handle exception
		}

		DroneHandler.getInstance().getDrone().startVideoRecorder(path);
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public View getView(Context context, int brickId, BaseAdapter adapter) {
		if (view == null) {
			view = View.inflate(context, R.layout.toolbox_brick_drone_start_video_recorder, null);
		}
		return view;
	}

	public View getPrototypeView(Context context) {
		return View.inflate(context, R.layout.toolbox_brick_drone_start_video_recorder, null);
	}

	@Override
	public Brick clone() {
		return new DroneStartVideoRecorderBrick(getSprite());
	}

	public int getRequiredResources() {
		return WIFI_DRONE;
	}
}
