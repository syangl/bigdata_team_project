package com.nd.sl.analysis;

import com.nd.sl.analysis.tool.AnalysisTextTool;
import org.apache.hadoop.util.ToolRunner;

public class AnalysisData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new AnalysisTextTool(), args);
    }
}
