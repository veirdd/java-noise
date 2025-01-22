package vd.ui.gen_dialog;

import vd.gen.Generator;

@FunctionalInterface
public interface SaveOperation
{
    public void run(Generator generator);
}