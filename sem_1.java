Calculator.java:

import java.math.BigDecimal;

public class Calculator {
    public BigDecimal calculateDiscount(BigDecimal purchaseAmount, int discountPercentage) {
        if (purchaseAmount.compareTo(BigDecimal.ZERO) < 0 || discountPercentage < 0 || discountPercentage > 100) {
            throw new ArithmeticException("Invalid arguments");
        }
        
        BigDecimal discount = purchaseAmount.multiply(BigDecimal.valueOf(discountPercentage)).divide(BigDecimal.valueOf(100));
        return purchaseAmount.subtract(discount);
    }
}

CalculatorTest.java:

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CalculatorTest {
    private Calculator calculator = new Calculator();
    
    public void testCalculateDiscountWithValidArguments() {
        BigDecimal purchaseAmount = BigDecimal.valueOf(100);
        int discountPercentage = 20;
        
        BigDecimal expectedDiscountedAmount = BigDecimal.valueOf(80);
        
        BigDecimal actualDiscountedAmount = calculator.calculateDiscount(purchaseAmount, discountPercentage);
        
        Assertions.assertThat(actualDiscountedAmount).isEqualByComparingTo(expectedDiscountedAmount);
    }
    
    public void testCalculateDiscountWithZeroPurchaseAmount() {
        BigDecimal purchaseAmount = BigDecimal.ZERO;
        int discountPercentage = 10;
        
        BigDecimal expectedDiscountedAmount = BigDecimal.ZERO;
        
        BigDecimal actualDiscountedAmount = calculator.calculateDiscount(purchaseAmount, discountPercentage);
        
        Assertions.assertThat(actualDiscountedAmount).isEqualByComparingTo(expectedDiscountedAmount);
    }
    
    public void testCalculateDiscountWithInvalidArguments() {
        BigDecimal purchaseAmount = BigDecimal.valueOf(100);
        int discountPercentage = -10;
        
        Assertions.assertThatThrownBy(() -> calculator.calculateDiscount(purchaseAmount, discountPercentage))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Invalid arguments");
    }
}