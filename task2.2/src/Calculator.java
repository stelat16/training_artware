import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Calculator {

  private int first;
  private int second;
  private ArithmeticOperation operation;

  public double calculate() {
    return operation.apply(first, second);
  }

}
