package vrd.render.view;

import java.awt.Color;

import vrd.ui.std.Canvas;
import vrd.util.Content;
import vrd.util.Vector3d;

public class CustomWorld extends View
{
    public CustomWorld()
    { this(new Vector3d(-200, 200, 0), new Vector3d(150, -80, -60), 70); }

    public CustomWorld(Vector3d camera_pos, Vector3d camera_direction, float fov_degrees)
    {
        this.camera_pos = camera_pos;
        this.camera_direction = camera_direction;
        this.fov = (float)Math.toRadians(fov_degrees);
    }

    @Override
    public Signature getSignature()
    { return new Signature(ViewList.Id.CustomWorld, "CustomWorld [3D]", 3); }

    @Override
    public int[] getRequiredContentSize(Canvas canvas)
    { return new int[]{256, 256, 3}; }

    @Override
    public void renderImpl(Content content, Canvas canvas)
    {
        for(int i = 0; i < content.getSize(); ++i)
        {
            int pos[] = content.mapIndexToIndices(i);
            // First layer (heightmap)
            if(pos[2] == 0)
            {
                Vector3d point = new Vector3d(pos[0], content.get(pos), pos[1]);
                project(point, canvas);
            }
            
        }
        
    }

    private void project(Vector3d point, Canvas canvas)
    {
        Vector3d relative_point = Vector3d.subtract(point, this.camera_pos);

        float camera_direction_r = (float)Math.sqrt(
            this.camera_direction.x * this.camera_direction.x +
            this.camera_direction.z * this.camera_direction.z);

        float h_viewing_angle = (float)Math.atan(this.camera_direction.z / this.camera_direction.x);
        float v_viewing_angle = (float)Math.atan(this.camera_direction.y / camera_direction_r);

        relative_point.rotateY(-h_viewing_angle);
        relative_point.rotateZ(-v_viewing_angle);

        float relative_point_r = (float)Math.sqrt(
            relative_point.x * relative_point.x +
            relative_point.z * relative_point.z);

        float h_observed_angle = (float)Math.atan(relative_point.z / relative_point.x);
        float v_observed_angle = (float)Math.atan(relative_point.y / relative_point_r);

        int screen_x = Math.round((canvas.getWidth() / 2) + ((h_observed_angle / (fov / 2)) * (canvas.getWidth() / 2)));
        int screen_y = Math.round((canvas.getHeight() / 2) + ((v_observed_angle / (fov / 2)) * (canvas.getHeight() / 2)));//todo does this stretch

        if(screen_x >= 0 && screen_x < canvas.getWidth() && screen_y >= 0 && screen_y < canvas.getHeight())
        { canvas.set(new int[]{screen_x, screen_y}, Color.white); }
    }

    private Vector3d camera_pos;
    private Vector3d camera_direction;
    private float fov;
}