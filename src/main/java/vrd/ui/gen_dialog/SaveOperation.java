package vrd.ui.gen_dialog;

import vrd.gen.Generator;

@FunctionalInterface
public interface SaveOperation
{
    public void run(Generator generator);
}