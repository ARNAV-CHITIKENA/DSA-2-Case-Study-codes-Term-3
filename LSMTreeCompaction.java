import java.util.ArrayList;
import java.util.List;
// C02 - LSM Tree Compaction and Write Amplification
public class LSMTreeCompaction {

    public static void main(String[] args) {

        int memtableSize = 1; // 1 GB
        int level0Tables = 8;
        int sstableSize = 1; // each SSTable = 1 GB

        List<Integer> level0 = new ArrayList<>();

        // Create 8 SSTables in Level-0
        for (int i = 1; i <= level0Tables; i++) {
            level0.add(sstableSize);
        }

        System.out.println("Level-0 SSTables: " + level0.size());
        System.out.println("Each SSTable Size: " + sstableSize + " GB");

        // Compaction
        int totalLevel0Data = 0;

        for (int size : level0) {
            totalLevel0Data += size;
        }

        int level1Data = totalLevel0Data;

        System.out.println("\nCompaction Started...");
        System.out.println("Merging " + level0.size() + " SSTables");

        level0.clear();

        System.out.println("Level-1 Data After Compaction: "
                + level1Data + " GB");

        // Write Amplification Calculation
        double logicalData = totalLevel0Data;
        double rewrittenData = totalLevel0Data;

        double writeAmplification =
                (logicalData + rewrittenData) / logicalData;

        System.out.println("\nLogical Data Written : "
                + logicalData + " GB");
        System.out.println("Data Rewritten During Compaction : "
                + rewrittenData + " GB");

        System.out.println("Write Amplification = "
                + writeAmplification + "x");
    }
}