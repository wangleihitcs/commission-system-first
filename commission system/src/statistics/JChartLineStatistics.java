package statistics;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class JChartLineStatistics {
	private String gunsmith;
	
	public CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		List<GunsmithSoldInTownStatistics> list = new GunsmithSoldInTownStatistics().getStatistics(gunsmith);
		Collections.sort(list,new Comparator<GunsmithSoldInTownStatistics>(){  
            public int compare(GunsmithSoldInTownStatistics arg0, GunsmithSoldInTownStatistics arg1) {  
                return arg0.getDate().compareTo(arg1.getDate());  
            }  
        });
		int i5 = list.size() - 1;
		int i4 = list.size() - 2;
		int i3 = list.size() - 3;
		int i2 = list.size() - 4;
		int i1 = list.size() - 5;
		dataset.setValue(list.get(i1).getLocks(), "locks", list.get(i1).getDate());
		dataset.setValue(list.get(i2).getLocks(), "locks", list.get(i2).getDate());
		dataset.setValue(list.get(i3).getLocks(), "locks", list.get(i3).getDate());
		dataset.setValue(list.get(i4).getLocks(), "locks", list.get(i4).getDate());
		dataset.setValue(list.get(i5).getLocks(), "locks", list.get(i5).getDate());
		
		dataset.setValue(list.get(i1).getStocks(), "stocks", list.get(i1).getDate());
		dataset.setValue(list.get(i2).getStocks(), "stocks", list.get(i2).getDate());
		dataset.setValue(list.get(i3).getStocks(), "stocks", list.get(i3).getDate());
		dataset.setValue(list.get(i4).getStocks(), "stocks", list.get(i4).getDate());
		dataset.setValue(list.get(i5).getStocks(), "stocks", list.get(i5).getDate());
		
		dataset.setValue(list.get(i1).getBarrels(), "barrels", list.get(i1).getDate());
		dataset.setValue(list.get(i2).getBarrels(), "barrels", list.get(i2).getDate());
		dataset.setValue(list.get(i3).getBarrels(), "barrels", list.get(i3).getDate());
		dataset.setValue(list.get(i4).getBarrels(), "barrels", list.get(i4).getDate());
		dataset.setValue(list.get(i5).getBarrels(), "barrels", list.get(i5).getDate());
		
		return dataset;
	}
	
	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createLineChart3D("", "five months", "sold num",dataset, PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
	
	public void execute() {
		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		try {
			String path = System.getProperty("user.dir");
			OutputStream os = new FileOutputStream(path + "/line.jpeg");// 图片是文件格式的，故要用到FileOutputStream用来输出。
			ChartUtilities.writeChartAsJPEG(os, chart, 480, 260);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getGunsmith() {
		return gunsmith;
	}

	public void setGunsmith(String gunsmith) {
		this.gunsmith = gunsmith;
	}
}
