import java.time.LocalDate;
import java.util.Date;

public class PaymentContext {
    private LocalDate date;
    private String paymentInfo;

    public PaymentContext(String paymentInfo)
    {
        this.paymentInfo = paymentInfo;
        this.date = LocalDate.now();
    }
}
