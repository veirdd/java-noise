// package vrd.gen;

// import java.util.ArrayList;

// import vrd.noise.Noise1d;
// import vrd.util.Content;

// public class Generator1d extends Generator {
//     public Generator1d(Noise1d algorithm, BlendMode blend_mode)
//     {
//         super(algorithm, blend_mode);
//     }

//     @Override
//     public Content generate(int[] size, int[] offset)
//     {
//         // Validate dimensionality of arguments (this is a 1D generator)
//         assert size.length == 1;
//         assert offset.length == 1;

//         Content content = new Content(size);

//         for(int i[] = {0}; i[0] < size[0]; ++i[0])
//         {
//             content.set(i, algorithm.get(i[0] + offset[0])); // todo: apply scale in noise alg and offset here (scale is a noise property and offset is a preview property)
//         }

//         return content;
//     }
// }