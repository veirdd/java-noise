package vd.render.view;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

import vd.ui.std.Canvas;
import vd.util.Content;
import vd.util.Util;
import vd.util.Vector3d;

public class CustomWorld extends View//todo tiles render through each other lol
{
    private class Triangle3d
    {
        public Triangle3d(Vector3d vertex1, Vector3d vertex2, Vector3d vertex3, Color color)
        {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.vertex3 = vertex3;

            this.color = color;
        }

        public Vector3d vertex1;
        public Vector3d vertex2;
        public Vector3d vertex3;

        public Color color;
    }

    public CustomWorld()
    { this(new Vector3d(-200, 100, 0), -22, -18, 80); }
    // -200 80 0 -22 -18 80 default

    public CustomWorld(Vector3d camera_pos, float camera_angle_h, float camera_angle_v, float fov)
    {
        this.camera_pos = camera_pos;
        this.camera_angle_h = (float)Math.toRadians(camera_angle_h);
        this.camera_angle_v = (float)Math.toRadians(camera_angle_v);
        this.fov = (float)Math.toRadians(fov);

        this.mesh = new ArrayList<>();
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
        this.content = content;
        this.canvas = canvas;
        this.mesh.clear();

        // Create the mesh
        for(int i = 0; i < this.content.getSize(); ++i)
        {
            int pos[] = this.content.mapIndexToIndices(i);
            // First layer (heightmap)
            if(pos[2] == 0)
            {
                if(pos[0] < this.content.dimensions[0] - 1 && pos[1] < this.content.dimensions[1] - 1)
                { this.mesh.add(makeTriangle(pos, true)); }

                if(pos[0] > 0 && pos[1] > 0)
                { this.mesh.add(makeTriangle(pos, false)); }
            }
        }

        // Sort the mesh elements from furthest to nearest to camera for overlaying
        this.mesh.sort((Triangle3d a, Triangle3d b)->
        { return (int)getDistanceFromCamera(b) - (int)getDistanceFromCamera(a); });

        for(Triangle3d triangle : this.mesh)
        { drawTriangle(triangle); }
    }

    private Triangle3d makeTriangle(int[] pos, boolean forward)
    {
        // Get vertices

        Vector3d vertex1 = new Vector3d(pos[0], this.content.get(pos), pos[1]);

        int[] pos2 = Util.arraySum(pos, new int[]{forward ? 1 : -1, 0,                0});
        int[] pos3 = Util.arraySum(pos, new int[]{               0, forward ? 1 : -1, 0});
        Vector3d vertex2 = new Vector3d(pos2[0], this.content.get(pos2), pos2[1]);
        Vector3d vertex3 = new Vector3d(pos3[0], this.content.get(pos3), pos3[1]);

        // Decode color from content's second layer data

        int encoded_color = Math.round(this.content.get(new int[]{pos[0], pos[1], 1}));
                   // Scraps last 6 digits
        int blue = Math.round(encoded_color / 1000 / 1000);
        encoded_color -= blue * 1000 * 1000;
                    // Scraps last 3 digits
        int green = Math.round(encoded_color / 1000);
        encoded_color -= green * 1000;
        int red = Math.round(encoded_color);

        Color color = new Color(
            Math.clamp(red, 0, 255), 
            Math.clamp(green, 0, 255), 
            Math.clamp(blue, 0, 255));

        // If water is present and height = water level

        if(this.content.get(new int[]{0, 0, 2}) == 1 &&
           vertex1.y == vertex2.y && 
           vertex2.y == vertex3.y && 
           vertex3.y == this.content.get(new int[]{1, 0, 2}))
        { color = water_color; }

        return new Triangle3d(vertex1, vertex2, vertex3, color);
    }

    private void drawTriangle(Triangle3d triangle)
    {
        Vector3d edge_vector1 = new Vector3d(
            triangle.vertex2.x - triangle.vertex1.x, 
            triangle.vertex2.y - triangle.vertex1.y, 
            triangle.vertex2.z - triangle.vertex1.z);
        Vector3d edge_vector2 = new Vector3d(
            triangle.vertex3.x - triangle.vertex1.x, 
            triangle.vertex3.y - triangle.vertex1.y, 
            triangle.vertex3.z - triangle.vertex1.z);
        Vector3d surface_vector = Vector3d.crossProduct(edge_vector1, edge_vector2);

        Color color = applyLight(triangle.color, surface_vector);

        Point pos1 = project(triangle.vertex1);
        Point pos2 = project(triangle.vertex2);
        Point pos3 = project(triangle.vertex3);

        int min_x = Math.min(Math.min(pos1.x, pos2.x), pos3.x);
        int min_y = Math.min(Math.min(pos1.y, pos2.y), pos3.y);
        int max_x = Math.max(Math.max(pos1.x, pos2.x), pos3.x);
        int max_y = Math.max(Math.max(pos1.y, pos2.y), pos3.y);

        for(int x = min_x; x <= max_x; ++x)
        for(int y = min_y; y <= max_y; ++y)
        {
            if(Util.isPointInsideTriangle(new Point(x, y), pos1, pos2, pos3) &&
               x >= 0 && x < this.canvas.getWidth() && y >= 0 && y < this.canvas.getHeight())
            { this.canvas.set(new int[]{x, y}, color); }
        }
    }

    private Color applyLight(Color color, Vector3d surface_vector)
    {
        int luminance = Math.round(this.luminance_multipler * Math.abs(Vector3d.dotProduct(surface_vector.getUnitVector(), light)));

        return new Color(
            Math.clamp(color.getRed() + luminance, 0, 255), 
            Math.clamp(color.getGreen() + luminance, 0, 255), 
            Math.clamp(color.getBlue() + luminance, 0, 255));
    }

    private float getDistanceFromCamera(Triangle3d triangle)
    {
        float avg_x = (triangle.vertex1.x + triangle.vertex2.x + triangle.vertex3.x) / 3;
        float avg_y = (triangle.vertex1.y + triangle.vertex2.y + triangle.vertex3.y) / 3;
        float avg_z = (triangle.vertex1.z + triangle.vertex2.z + triangle.vertex3.z) / 3;

        float dx = avg_x - this.camera_pos.x;
        float dy = avg_y - this.camera_pos.y;
        float dz = avg_z - this.camera_pos.z;

        return (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    private Point project(Vector3d point)
    {
        // Get position of input point relative to the camera

        Vector3d relative_point = Vector3d.subtract(point, this.camera_pos);

        // Get the angles between the observed point vector and camera direction vector

        relative_point.rotateY(-this.camera_angle_h);
        relative_point.rotateZ(-this.camera_angle_v);

        float relative_point_r = (float)Math.sqrt(
            relative_point.x * relative_point.x +
            relative_point.z * relative_point.z);

        float h_observed_angle = (float)Math.atan(relative_point.z / relative_point.x);
        float v_observed_angle = (float)Math.atan(relative_point.y / relative_point_r);

        // Distribute the point to screen coordinates based on observation angle

        int screen_x = Math.round(this.object_scale * ((this.canvas.getWidth() / 2) + ((h_observed_angle / (fov / 2)) * (this.canvas.getWidth() / 2))));
        int screen_y = Math.round(this.object_scale * ((this.canvas.getHeight() / 2) + ((v_observed_angle / (fov / 2)) * (this.canvas.getWidth() / 2))));

        // Leave for debugging, renders white pixels
        // if(screen_x >= 0 && screen_x < this.canvas.getWidth() && screen_y >= 0 && screen_y < this.canvas.getHeight()) { this.canvas.set(new int[]{screen_x, screen_y}, Color.white); }

        return new Point(screen_x, screen_y);
    }

    private Vector3d camera_pos;
    // = 0 for camera pointing at [1, 0, 0], + goes left
    private float camera_angle_h;
    // = 0 for camera pointing at [1, 0, 0], + goes up
    private float camera_angle_v;
    private float fov;
    // Describes the direction and intensity
    private final Vector3d light = new Vector3d(-10, -10, 10);

    private ArrayList<Triangle3d> mesh;

    // I got bored of passing this
    private Content content;
    private Canvas canvas;
    
    final float object_scale = 1;
    final float luminance_multipler = 5;
    final Color water_color = new Color(20, 50, 100);
}