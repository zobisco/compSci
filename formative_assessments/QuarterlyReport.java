package formative_assessments;

public class QuarterlyReport {
  public static void main(String[] args) {
    // Create a new instance of the QuarterlyReport class
    QuarterlyReport report = new QuarterlyReport();
    // Call the method to generate the quarterly sales report
    report.generateSalesReport();
    // Call the method to generate the department performance report
    report.getDepartmentPerformanceReport();
    // Call the method to generate the sales targets for next quarter
    report.getSalesTarget();
    // Call the method to find the most effective month in terms of sales
    report.getHighestPerformingMonth();
    // Call the method to generate the tax report
    report.calculateTax();
  }

  double[][] quarterSales = {
      {67.0, 63.0, 78.0, 78.0, 104.0, 103.0}, // Electrical
      {65.0, 67.0, 56.0, 45.0, 56.0, 72.0},   // Kitchen
      {63.0, 63.0, 65.0, 71.0, 73.0, 69.0},   // Bathroom
      {18.0, 24.0, 22.0, 19.0, 17.0, 16.0},   // Soft Furnishings
      {16.0, 23.0, 21.0, 19.0, 20.0, 19.0}    // Accessories
  };

  public static String[] departments = {
      "Electrical",
      "Kitchen",
      "Bathroom",
      "Soft Furnishings",
      "Accessories"
  };

  String[] months = { "April", "May", "June", "July", "August", "September" };

  double mostEffectiveMonthQ2 = 0;
  double mostEffectiveMonthQ3 = 0;
  int depIdx = 0;
  int monthIdx = 0;
  double monthlySales = 0;
  double quarterlyAverage = 0;
  double[] salesTarget = new double[5];
  double performanceForQuarterTwo[] = new double[5];
  double performanceForQuarterThree[] = new double[5];
  int quarterTwo = 0;                 // index of 2nd quarter
  int quarterThree = 3;               // index of 3rd quarter
  double quarterTwoTotals = 0;        // initialise Q2 total to zero
  double quarterThreeTotals = 0;      // initialise Q3 total to zero
  double quarterTwoPreTaxTotal = 0;   // initialise Q2 pre-tax total to zero
  double quarterThreePreTaxTotal = 0; // initialise Q3 pre-tax total to zero

  public void generateSalesReport() {
    // Loop through the sales array and print out the total sales for each department per quarter
    // Start with quarter two
    for (int i = 0; i < quarterSales.length; i++) {
      for (int j = quarterTwo; j < quarterThree; j++) {
        monthlySales = quarterSales[i][j];
        monthlySales *= 1000;
        quarterTwoTotals += monthlySales;
        if (monthlySales > mostEffectiveMonthQ2) {
          mostEffectiveMonthQ2 = monthlySales;
          depIdx = j;
          monthIdx = i;
        }
      }
      quarterTwoPreTaxTotal += quarterTwoTotals;
      performanceForQuarterTwo[i] = quarterTwoTotals;
      System.out.println("2nd Quarter totals: " + departments[i] + ", GBP " + quarterTwoTotals);
      // Reset values
      quarterTwoTotals = 0;
      monthlySales = 0;
    }
    // Now loop through sales array again and print out the total sales for each department
    // We are now looping through quarter three
    System.out.println("");
    for (int i = 0; i < quarterSales.length; i++) {
      for (int j = quarterThree; j < 6; j++) {
        monthlySales = quarterSales[i][j];
        monthlySales *= 1000;
        quarterThreeTotals += monthlySales;
        if (monthlySales > mostEffectiveMonthQ3) {
          mostEffectiveMonthQ3 = monthlySales;
          depIdx = i;
          monthIdx = j;
        }
      }
      quarterThreePreTaxTotal += quarterThreeTotals;
      performanceForQuarterThree[i] = quarterThreeTotals;
      System.out.println("3rd Quarter totals: " + departments[i] + ", GBP " + quarterThreeTotals);
      // Reset values
      quarterThreeTotals = 0;
      monthlySales = 0;
    }
  }

  public void getHighestPerformingMonth() {
    // Find the most effective month for each department
    for (int i = 0; i < quarterSales.length; i++) {
      for (int j = quarterTwo; j < quarterThree; j++) {
        monthlySales = quarterSales[i][j];
        monthlySales *= 1000;
        quarterTwoTotals += monthlySales;
        if (monthlySales > mostEffectiveMonthQ2) {
          mostEffectiveMonthQ2 = monthlySales;
          depIdx = j;
          monthIdx = i;
        }
      }
      // Reset values
      quarterTwoTotals = 0;
      monthlySales = 0;
    }
    // Q2
    System.out.println("\n2nd Quarter, most effective department: " + departments[monthIdx] + ", " + months[monthIdx] +
        ", GBP " + mostEffectiveMonthQ2);
    mostEffectiveMonthQ2 = 0;
    // Q3
    System.out.println("3rd Quarter, most effective department: " + departments[depIdx] + ", " + months[monthIdx] +
        ", GBP " + mostEffectiveMonthQ3);
    mostEffectiveMonthQ3 = 0;
  }
  public void getDepartmentPerformanceReport() {
    // Declare variables to figure out the best performing departments for quarter two and three
    double maxQuarterTwo = 0;
    String bestDepartmentSecondQuarter = "";
    int indexQ2 = 0;
    String monthlySalesForBestDepartmentQ2 = "";
    double maxQuarterThree = 0;
    String bestDepartmentThirdQuarter = "";
    int indexQ3 = 0;
    String monthlySalesForBestDepartmentQ3 = "";

    // We need to loop through the total sales values to find the maximum value
    // Loop through quarter two values
    for (int i = 0; i < performanceForQuarterTwo.length; i++) {
      if (performanceForQuarterTwo[i] > maxQuarterTwo) {
        maxQuarterTwo = performanceForQuarterTwo[i];
        bestDepartmentSecondQuarter = departments[i];
        indexQ2 = i;
      }
    }

    // Now loop through quarter three values
    for (int i = 0; i < performanceForQuarterThree.length; i++) {
      if (performanceForQuarterThree[i] > maxQuarterThree) {
        maxQuarterThree = performanceForQuarterThree[i];
        bestDepartmentThirdQuarter = departments[i];
        indexQ3 = i;
      }
    }

    // Create string containing all the sales figures for best performing department in 2nd quarter
    for (int i = 0; i < 3; i++) {
      double salesFigure = quarterSales[indexQ2][i];
      salesFigure *= 1000;
      salesFigure = Double.parseDouble(String.valueOf(salesFigure));
      monthlySalesForBestDepartmentQ2 += salesFigure;
      monthlySalesForBestDepartmentQ2 += ", ";
    }

    // Do the same for third quarter
    for (int i = 3; i < 6; i++) {
      double salesFigure = quarterSales[indexQ3][i];
      salesFigure *= 1000;
      salesFigure = Double.parseDouble(String.valueOf(salesFigure));
      monthlySalesForBestDepartmentQ3 += salesFigure;
      monthlySalesForBestDepartmentQ3 += ", ";
    }

    System.out.println("\n2nd Quarter best: " + bestDepartmentSecondQuarter + ", "
        + monthlySalesForBestDepartmentQ2.substring(0, monthlySalesForBestDepartmentQ2.trim().length() - 1));
    System.out.println("3rd Quarter best: " + bestDepartmentThirdQuarter + ", "
        + monthlySalesForBestDepartmentQ3.substring(0, monthlySalesForBestDepartmentQ3.trim().length() - 1));

    // Declare variables to figure out the worst performing departments for quarter two and three
    double minQuarterTwo = maxQuarterTwo;
    String worstDepartmentSecondQuarter = "";
    int idxQ2 = 0;
    String monthlySalesForWorstDepartmentQ2 = "";
    double minQuarterThree = maxQuarterThree;
    String worstDepartmentThirdQuarter = "";
    int idxQ3 = 0;
    String monthlySalesForWorstDepartmentQ3 = "";

    // Iterate over the sales figures for the second quarter and find the worst performing department
    for (int i = 0; i < performanceForQuarterTwo.length; i++) {
      if (performanceForQuarterTwo[i] < minQuarterTwo) {
        minQuarterTwo = performanceForQuarterTwo[i];
        worstDepartmentSecondQuarter = departments[i];
        idxQ2 = i;
      }
    }

    // Do the same for third quarter
    for (int i = 0; i < performanceForQuarterThree.length; i++) {
      if (performanceForQuarterThree[i] < minQuarterThree) {
        minQuarterThree = performanceForQuarterThree[i];
        worstDepartmentThirdQuarter = departments[i];
        idxQ3 = i;
      }
    }

    // Create string containing all the sales figures for worst performing department in 2nd quarter
    for (int i = 0; i < 3; i++) {
      double salesFigure = quarterSales[idxQ2][i];
      salesFigure *= 1000;
      salesFigure = Double.parseDouble(String.valueOf(salesFigure));
      monthlySalesForWorstDepartmentQ2 += salesFigure;
      monthlySalesForWorstDepartmentQ2 += ", ";
    }

    // Do the same for third quarter
    for (int i = 3; i < 6; i++) {
      double salesFigure = quarterSales[idxQ3][i];
      salesFigure *= 1000;
      salesFigure = Double.parseDouble(String.valueOf(salesFigure));
      monthlySalesForWorstDepartmentQ3 += salesFigure;
      monthlySalesForWorstDepartmentQ3 += ", ";
    }

    System.out.println("2nd Quarter worst: " + worstDepartmentSecondQuarter + ", "
        + monthlySalesForWorstDepartmentQ2.substring(0, monthlySalesForWorstDepartmentQ2.trim().length() - 1));
    System.out.println("3rd Quarter worst: " + worstDepartmentThirdQuarter + ", "
        + monthlySalesForWorstDepartmentQ3.substring(0, monthlySalesForWorstDepartmentQ3.trim().length() - 1));;
  }

  public void getSalesTarget() {
    // Loop through the sales array, find the quarterly average for each department
    // and print out the sales target for each department
    // Start with quarter two
    System.out.println("");
    for (int i = 0; i < quarterSales.length; i++) {
      for (int j = quarterTwo; j < quarterThree; j++) {
        monthlySales = quarterSales[i][j];
        monthlySales *= 1000;
        quarterTwoTotals += monthlySales;
      }
      quarterlyAverage = quarterTwoTotals / 3;
      salesTarget[i] = quarterlyAverage * 1.12;
      System.out.println("Q3 Sales target for " + departments[i] + " is: GBP " + salesTarget[i]);
      // Reset values
      quarterTwoTotals = 0;
      monthlySales = 0;
    }
    // We are now looping through quarter three
    for (int i = 0; i < quarterSales.length; i++) {
      for (int j = quarterThree; j < 6; j++) {
        monthlySales = quarterSales[i][j];
        monthlySales *= 1000;
        quarterThreeTotals += monthlySales;
      }
      // We now calculate the sales target for the next quarter
      quarterlyAverage = quarterThreeTotals / 3;
      salesTarget[i] = quarterlyAverage * 1.12;
      System.out.println("Q4 Sales target for " + departments[i] + " is: GBP " + salesTarget[i]);
      // Reset values
      quarterThreeTotals = 0;
      monthlySales = 0;
    }
  }

  public void calculateTax() {
    // Calculate the tax for each quarter
    double tax = 17;
    double quarterTwoTax = quarterTwoPreTaxTotal * tax / 100;
    double quarterThreeTax = quarterThreePreTaxTotal * tax / 100;
    System.out.println("\n2nd Quarter tax: GBP " + quarterTwoTax);
    System.out.println("3rd Quarter tax: GBP " + quarterThreeTax);
  }
}

// EOF
