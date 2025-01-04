package vd.render.view;

import java.util.ArrayList;
import java.util.List;

public class ViewList
{
    public enum Id
    {
        HeightMap1d,
        FadeMap1d,
        HeightMap2d,
        CustomWorld
    }

    public static String[] getViewNames()
    {
        ArrayList<String> names_list = new ArrayList<>();

        for(View view : viewList())
        { names_list.add(view.getSignature().name); }

        return names_list.toArray(new String[0]);
    }

    public static View makeViewFromId(Id id)
    {
        for(View view : viewList())
        {
            if(view.getSignature().id == id)
            { return view; }
        }
        
        throw new IllegalArgumentException("No algorithm with ID '" + id.toString() + "' was found");
    }

    // List of Views that should be included in runtime
    private static List<View> viewList()
    {
        return List.of
        (
            // Should be same order as Id
            new HeightMap1d(),
            new FadeMap1d(),
            new HeightMap2d(),
            new CustomWorld()
        );
    }
}