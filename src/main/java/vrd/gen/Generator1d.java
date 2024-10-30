package vrd.gen;

import java.util.ArrayList;

import vrd.noise.Noise1d;

public class Generator1d extends Generator {
    public Generator1d(Noise1d algorithm, BlendMode blend_mode)
    {
        super(algorithm, blend_mode);
    }

    @Override
    public ArrayList<Float> generate(Object size, Object offset)
    {
        assert size instanceof Integer;
        assert offset instanceof Integer;

        ArrayList<Float> list = new ArrayList((int)size);
        final int int_offset = (int)offset;
        final int int_size = (int)size;

        for(int i = 0; i < int_size; ++i)
        {
            list.set(i, algorithm.get(i + int_offset)); // todo: apply scale in noise alg and offset here (scale is a noise property and offset is a preview property)
        }

        return list;
    }
}