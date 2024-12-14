package demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import utils.Dijkstra;
import utils.GraphGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class Graph {

        @Param({"1000", "2000", "4000", "8000", "16000", "32000"})
        public int elementCount;

        @Param({"4000", "8000", "16000", "32000", "64000", "128000"})
        public int edgesCount;
        EuclideanGraph graph;

        @Setup(Level.Iteration)
        public void generateGraph(BenchmarkParams params) {
            GraphGenerator generator = new GraphGenerator();
            graph = generator.getGeneratedGraph(Integer.parseInt(params.getParam("elementCount")),Integer.parseInt(params.getParam("edgesCount")));
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void dijkstraAlgorithm(Graph map) {
        Dijkstra dijkstra = new Dijkstra(map.graph);
        dijkstra.runDijkstra(map.graph.getStart());
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void dijkstraAlgorithm1(Graph map) {
        Dijkstra dijkstra = new Dijkstra(map.graph);
        dijkstra.runDijkstra1(map.graph.getStart());
    }

//    @org.openjdk.jmh.annotations.Benchmark
//    public void graphGeneration(Graph map) {
//        GraphGenerator generator = new GraphGenerator();
//        EuclideanGraph generatedGraph = generator.getGeneratedGraph(map.elementCount, map.edgesCount);
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
/*
Benchmark                    (edgesCount)  (elementCount)  Mode  Cnt      Score       Error  Units
Benchmark.dijkstraAlgorithm          4000            1000  avgt    5    230.337 ±    18.963  us/op
Benchmark.dijkstraAlgorithm          4000            2000  avgt    5    358.035 ±    22.649  us/op
Benchmark.dijkstraAlgorithm          4000            4000  avgt    5    453.239 ±    20.437  us/op
Benchmark.dijkstraAlgorithm          4000            8000  avgt    5     13.643 ±     4.929  us/op
Benchmark.dijkstraAlgorithm          4000           16000  avgt    5     23.291 ±     7.262  us/op
Benchmark.dijkstraAlgorithm          4000           32000  avgt    5     42.206 ±     8.175  us/op
Benchmark.dijkstraAlgorithm          8000            1000  avgt    5    373.211 ±    23.297  us/op
Benchmark.dijkstraAlgorithm          8000            2000  avgt    5    552.380 ±    43.885  us/op
Benchmark.dijkstraAlgorithm          8000            4000  avgt    5    839.724 ±    26.250  us/op
Benchmark.dijkstraAlgorithm          8000            8000  avgt    5   1159.292 ±   181.269  us/op
Benchmark.dijkstraAlgorithm          8000           16000  avgt    5     31.911 ±    72.185  us/op
Benchmark.dijkstraAlgorithm          8000           32000  avgt    5     36.952 ±     2.858  us/op
Benchmark.dijkstraAlgorithm         16000            1000  avgt    5    596.689 ±   145.632  us/op
Benchmark.dijkstraAlgorithm         16000            2000  avgt    5    854.740 ±    86.226  us/op
Benchmark.dijkstraAlgorithm         16000            4000  avgt    5   1232.175 ±    51.582  us/op
Benchmark.dijkstraAlgorithm         16000            8000  avgt    5   1847.473 ±   141.166  us/op
Benchmark.dijkstraAlgorithm         16000           16000  avgt    5   2016.185 ±  4296.014  us/op
Benchmark.dijkstraAlgorithm         16000           32000  avgt    5     38.171 ±     4.618  us/op
Benchmark.dijkstraAlgorithm         32000            1000  avgt    5   1141.323 ±   413.569  us/op
Benchmark.dijkstraAlgorithm         32000            2000  avgt    5   1402.746 ±   128.180  us/op
Benchmark.dijkstraAlgorithm         32000            4000  avgt    5   1770.653 ±   163.076  us/op
Benchmark.dijkstraAlgorithm         32000            8000  avgt    5   2942.140 ±   423.355  us/op
Benchmark.dijkstraAlgorithm         32000           16000  avgt    5   4372.427 ±   221.388  us/op
Benchmark.dijkstraAlgorithm         32000           32000  avgt    5   4868.239 ± 10417.047  us/op
Benchmark.dijkstraAlgorithm         64000            1000  avgt    5   1855.902 ±   754.607  us/op
Benchmark.dijkstraAlgorithm         64000            2000  avgt    5   2391.384 ±   360.890  us/op
Benchmark.dijkstraAlgorithm         64000            4000  avgt    5   2979.987 ±   429.725  us/op
Benchmark.dijkstraAlgorithm         64000            8000  avgt    5   4063.237 ±   174.741  us/op
Benchmark.dijkstraAlgorithm         64000           16000  avgt    5   6372.934 ±   142.265  us/op
Benchmark.dijkstraAlgorithm         64000           32000  avgt    5  10085.827 ±   379.329  us/op
Benchmark.dijkstraAlgorithm        128000            1000  avgt    5   3156.053 ±  1077.526  us/op
Benchmark.dijkstraAlgorithm        128000            2000  avgt    5   3549.859 ±   832.864  us/op
Benchmark.dijkstraAlgorithm        128000            4000  avgt    5   4848.008 ±  1175.969  us/op
Benchmark.dijkstraAlgorithm        128000            8000  avgt    5   6299.254 ±  1529.999  us/op
Benchmark.dijkstraAlgorithm        128000           16000  avgt    5   9593.900 ±   957.443  us/op
Benchmark.dijkstraAlgorithm        128000           32000  avgt    5  19452.997 ±  4621.119  us/op
Benchmark.graphGeneration            4000            1000  avgt    5    220.031 ±    33.383  us/op
Benchmark.graphGeneration            4000            2000  avgt    5    254.566 ±    54.134  us/op
Benchmark.graphGeneration            4000            4000  avgt    5    340.405 ±    61.493  us/op
Benchmark.graphGeneration            4000            8000  avgt    5    457.629 ±     2.848  us/op
Benchmark.graphGeneration            4000           16000  avgt    5    728.308 ±    12.501  us/op
Benchmark.graphGeneration            4000           32000  avgt    5   1205.512 ±    61.332  us/op
Benchmark.graphGeneration            8000            1000  avgt    5    441.263 ±    15.103  us/op
Benchmark.graphGeneration            8000            2000  avgt    5    419.489 ±    15.428  us/op
Benchmark.graphGeneration            8000            4000  avgt    5    485.697 ±    36.149  us/op
Benchmark.graphGeneration            8000            8000  avgt    5    646.420 ±    11.520  us/op
Benchmark.graphGeneration            8000           16000  avgt    5   1066.643 ±   143.484  us/op
Benchmark.graphGeneration            8000           32000  avgt    5   1570.683 ±   119.322  us/op
Benchmark.graphGeneration           16000            1000  avgt    5   1203.931 ±   277.703  us/op
Benchmark.graphGeneration           16000            2000  avgt    5    980.247 ±    11.481  us/op
Benchmark.graphGeneration           16000            4000  avgt    5    964.846 ±   302.707  us/op
Benchmark.graphGeneration           16000            8000  avgt    5   1117.102 ±   171.390  us/op
Benchmark.graphGeneration           16000           16000  avgt    5   1568.985 ±   410.265  us/op
Benchmark.graphGeneration           16000           32000  avgt    5   2217.575 ±   130.412  us/op
Benchmark.graphGeneration           32000            1000  avgt    5   2726.944 ±   108.883  us/op
Benchmark.graphGeneration           32000            2000  avgt    5   2390.160 ±   322.803  us/op
Benchmark.graphGeneration           32000            4000  avgt    5   2199.366 ±    74.673  us/op
Benchmark.graphGeneration           32000            8000  avgt    5   2134.866 ±   324.615  us/op
Benchmark.graphGeneration           32000           16000  avgt    5   2311.004 ±    64.429  us/op
Benchmark.graphGeneration           32000           32000  avgt    5   3226.525 ±    48.996  us/op
Benchmark.graphGeneration           64000            1000  avgt    5   8505.810 ±   359.930  us/op
Benchmark.graphGeneration           64000            2000  avgt    5   6605.375 ±   147.560  us/op
Benchmark.graphGeneration           64000            4000  avgt    5   5698.522 ±   267.534  us/op
Benchmark.graphGeneration           64000            8000  avgt    5   4985.358 ±   763.106  us/op
Benchmark.graphGeneration           64000           16000  avgt    5   4596.456 ±   197.769  us/op
Benchmark.graphGeneration           64000           32000  avgt    5   5645.994 ±   368.466  us/op
Benchmark.graphGeneration          128000            1000  avgt    5  39194.800 ±  1847.845  us/op
Benchmark.graphGeneration          128000            2000  avgt    5  26020.874 ±  1145.598  us/op
Benchmark.graphGeneration          128000            4000  avgt    5  20001.257 ±   773.682  us/op
Benchmark.graphGeneration          128000            8000  avgt    5  19098.510 ±  7166.964  us/op
Benchmark.graphGeneration          128000           16000  avgt    5  15247.424 ±   830.609  us/op
Benchmark.graphGeneration          128000           32000  avgt    5  16144.263 ±  2085.422  us/op
*/

/*

Benchmark                     (edgesCount)  (elementCount)  Mode  Cnt      Score       Error  Units
Benchmark.dijkstraAlgorithm           4000            1000  avgt    5     40.160 ±   125.297  us/op
Benchmark.dijkstraAlgorithm           4000            2000  avgt    5     38.664 ±    50.319  us/op
Benchmark.dijkstraAlgorithm           4000            4000  avgt    5    267.757 ±  1128.546  us/op
Benchmark.dijkstraAlgorithm           4000            8000  avgt    5     31.662 ±     1.011  us/op
Benchmark.dijkstraAlgorithm           4000           16000  avgt    5     53.840 ±    10.120  us/op
Benchmark.dijkstraAlgorithm           4000           32000  avgt    5    165.872 ±   135.402  us/op
Benchmark.dijkstraAlgorithm           8000            1000  avgt    5     34.857 ±    51.025  us/op
Benchmark.dijkstraAlgorithm           8000            2000  avgt    5    122.057 ±   514.093  us/op
Benchmark.dijkstraAlgorithm           8000            4000  avgt    5    527.637 ±  2622.837  us/op
Benchmark.dijkstraAlgorithm           8000            8000  avgt    5    404.984 ±  2990.546  us/op
Benchmark.dijkstraAlgorithm           8000           16000  avgt    5     58.301 ±    43.124  us/op
Benchmark.dijkstraAlgorithm           8000           32000  avgt    5     96.721 ±     5.670  us/op
Benchmark.dijkstraAlgorithm          16000            1000  avgt    5     18.244 ±    44.194  us/op
Benchmark.dijkstraAlgorithm          16000            2000  avgt    5    180.664 ±   568.826  us/op
Benchmark.dijkstraAlgorithm          16000            4000  avgt    5    138.198 ±   373.931  us/op
Benchmark.dijkstraAlgorithm          16000            8000  avgt    5   1600.890 ±  4951.819  us/op
Benchmark.dijkstraAlgorithm          16000           16000  avgt    5   2102.886 ±  6498.709  us/op
Benchmark.dijkstraAlgorithm          16000           32000  avgt    5     94.450 ±     7.440  us/op
Benchmark.dijkstraAlgorithm          32000            1000  avgt    5     64.076 ±   257.385  us/op
Benchmark.dijkstraAlgorithm          32000            2000  avgt    5    136.095 ±   492.948  us/op
Benchmark.dijkstraAlgorithm          32000            4000  avgt    5    128.619 ±   448.247  us/op
Benchmark.dijkstraAlgorithm          32000            8000  avgt    5   1050.563 ±  5426.630  us/op
Benchmark.dijkstraAlgorithm          32000           16000  avgt    5   2114.429 ±  6193.459  us/op
Benchmark.dijkstraAlgorithm          32000           32000  avgt    5   3565.151 ± 14391.432  us/op
Benchmark.dijkstraAlgorithm          64000            1000  avgt    5     51.158 ±   114.304  us/op
Benchmark.dijkstraAlgorithm          64000            2000  avgt    5     47.580 ±    98.336  us/op
Benchmark.dijkstraAlgorithm          64000            4000  avgt    5    152.674 ±   613.466  us/op
Benchmark.dijkstraAlgorithm          64000            8000  avgt    5    296.920 ±   918.706  us/op
Benchmark.dijkstraAlgorithm          64000           16000  avgt    5   3101.342 ± 21670.770  us/op
Benchmark.dijkstraAlgorithm          64000           32000  avgt    5   2722.889 ±  8001.237  us/op
Benchmark.dijkstraAlgorithm         128000            1000  avgt    5     26.788 ±    63.653  us/op
Benchmark.dijkstraAlgorithm         128000            2000  avgt    5     77.207 ±   244.751  us/op
Benchmark.dijkstraAlgorithm         128000            4000  avgt    5    125.631 ±   358.104  us/op
Benchmark.dijkstraAlgorithm         128000            8000  avgt    5    902.127 ±  4074.131  us/op
Benchmark.dijkstraAlgorithm         128000           16000  avgt    5   1413.794 ±  4910.905  us/op
Benchmark.dijkstraAlgorithm         128000           32000  avgt    5   5985.120 ± 27086.076  us/op
Benchmark.dijkstraAlgorithm1          4000            1000  avgt    5     80.395 ±   160.835  us/op
Benchmark.dijkstraAlgorithm1          4000            2000  avgt    5     82.324 ±   263.963  us/op
Benchmark.dijkstraAlgorithm1          4000            4000  avgt    5    285.471 ±   944.054  us/op
Benchmark.dijkstraAlgorithm1          4000            8000  avgt    5     46.141 ±   110.535  us/op
Benchmark.dijkstraAlgorithm1          4000           16000  avgt    5     61.984 ±    39.404  us/op
Benchmark.dijkstraAlgorithm1          4000           32000  avgt    5     95.780 ±     2.559  us/op
Benchmark.dijkstraAlgorithm1          8000            1000  avgt    5     75.171 ±   193.815  us/op
Benchmark.dijkstraAlgorithm1          8000            2000  avgt    5    209.700 ±   409.072  us/op
Benchmark.dijkstraAlgorithm1          8000            4000  avgt    5    437.555 ±  1048.175  us/op
Benchmark.dijkstraAlgorithm1          8000            8000  avgt    5    575.021 ±  1958.942  us/op
Benchmark.dijkstraAlgorithm1          8000           16000  avgt    5     51.376 ±     9.317  us/op
Benchmark.dijkstraAlgorithm1          8000           32000  avgt    5     93.195 ±     9.540  us/op
Benchmark.dijkstraAlgorithm1         16000            1000  avgt    5    144.760 ±   291.328  us/op
Benchmark.dijkstraAlgorithm1         16000            2000  avgt    5    192.920 ±   411.181  us/op
Benchmark.dijkstraAlgorithm1         16000            4000  avgt    5    393.428 ±  1374.320  us/op
Benchmark.dijkstraAlgorithm1         16000            8000  avgt    5   1198.223 ±  2264.395  us/op
Benchmark.dijkstraAlgorithm1         16000           16000  avgt    5    997.874 ±  2952.979  us/op
Benchmark.dijkstraAlgorithm1         16000           32000  avgt    5     99.919 ±    22.060  us/op
Benchmark.dijkstraAlgorithm1         32000            1000  avgt    5    378.802 ±   794.799  us/op
Benchmark.dijkstraAlgorithm1         32000            2000  avgt    5    464.370 ±   441.858  us/op
Benchmark.dijkstraAlgorithm1         32000            4000  avgt    5    704.167 ±   584.934  us/op
Benchmark.dijkstraAlgorithm1         32000            8000  avgt    5    834.009 ±  2155.212  us/op
Benchmark.dijkstraAlgorithm1         32000           16000  avgt    5   2288.946 ±  5343.557  us/op
Benchmark.dijkstraAlgorithm1         32000           32000  avgt    5   5367.565 ± 11155.510  us/op
Benchmark.dijkstraAlgorithm1         64000            1000  avgt    5    827.149 ±  1781.245  us/op
Benchmark.dijkstraAlgorithm1         64000            2000  avgt    5    783.303 ±  1801.991  us/op
Benchmark.dijkstraAlgorithm1         64000            4000  avgt    5   1020.309 ±  3397.510  us/op
Benchmark.dijkstraAlgorithm1         64000            8000  avgt    5   1592.537 ±  2922.601  us/op
Benchmark.dijkstraAlgorithm1         64000           16000  avgt    5   2228.576 ±  4691.353  us/op
Benchmark.dijkstraAlgorithm1         64000           32000  avgt    5  13076.359 ± 16813.630  us/op
Benchmark.dijkstraAlgorithm1        128000            1000  avgt    5   2679.663 ±  5156.284  us/op
Benchmark.dijkstraAlgorithm1        128000            2000  avgt    5   2569.923 ±  8892.127  us/op
Benchmark.dijkstraAlgorithm1        128000            4000  avgt    5   1036.463 ±  2738.988  us/op
Benchmark.dijkstraAlgorithm1        128000            8000  avgt    5   5439.861 ±  9989.060  us/op
Benchmark.dijkstraAlgorithm1        128000           16000  avgt    5   2757.445 ±  6932.002  us/op
Benchmark.dijkstraAlgorithm1        128000           32000  avgt    5   7899.965 ±  9123.782  us/op

* */