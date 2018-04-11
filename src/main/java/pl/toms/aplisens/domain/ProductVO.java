package pl.toms.aplisens.domain;

import java.util.List;

import pl.toms.aplisens.service.ProductDetailsService;

public class ProductVO
{
    double rangeLow;
    double rangeHigh;
    ProductDetailsService.PresureUnits unit = null;
    List<ProductParameter> productParameter;
    
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
    public ProductDetailsService.PresureUnits getUnit()
    {
        return unit;
    }
    public void setUnit(ProductDetailsService.PresureUnits unit)
    {
        this.unit = unit;
    }
    public List<ProductParameter> getProductParameter()
    {
        return productParameter;
    }
    public void setProductParameter(List<ProductParameter> productParameter)
    {
        this.productParameter = productParameter;
    }
    @Override
    public String toString()
    {
        return "ProductVO [rangeLow=" + rangeLow + ", rangeHigh=" + rangeHigh + ", unit=" + unit + ", productParameter=" + productParameter + "]";
    } 
    
    
    
}
