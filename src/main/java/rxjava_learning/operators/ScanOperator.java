package rxjava_learning.operators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

@Profile("scan")
@Component
public class ScanOperator {

    @PostConstruct
    public void init() {

        Observable<BigInteger> factorials = Observable
                .range(2, 100)
                .scan(BigInteger.ONE, (big, cur) -> big.multiply(BigInteger.valueOf(cur)));

        factorials.subscribe(Logger::log);
    }
}
