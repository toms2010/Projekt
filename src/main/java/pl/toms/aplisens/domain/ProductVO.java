package pl.toms.aplisens.domain;

import java.util.List;

import pl.toms.aplisens.util.PresureUnits;

public class ProductVO
{
    private double                 rangeLow;
    private double                 rangeHigh;
    private PresureUnits           unit = null;
    private List<ProductParameter> productParameter;

    public double getRangeLow()
    {
        return rangeLow;
    }

    public void setRangeLow(double rangeLow)
    {
        this.rangeLow = rangeLow;
    }

    public double getRangeHigh()
    {
        return rangeHigh;
    }

    public void setRangeHigh(double rangeHigh)
    {
        this.rangeHigh = rangeHigh;
    }

    public List<ProductParameter> getProductParameter()
    {
        return productParameter;
    }

    public void setProductParameter(List<ProductParameter> productParameter)
    {
        this.productParameter = productParameter;
    }

    public PresureUnits getUnit()
    {
        return unit;
    }

    public void setUnit(PresureUnits unit)
    {
        this.unit = unit;
    }

    @Override
    public String toString()
    {
        return "ProductVO [rangeLow=" + rangeLow + ", rangeHigh=" + rangeHigh + ", unit=" + unit + ", productParameter=" + productParameter + "]";
    }

}
