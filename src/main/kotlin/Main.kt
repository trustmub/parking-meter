import java.math.BigDecimal
import java.math.RoundingMode


fun main() {
    val changeOne = calculateParkingChange(AmountCurrency(BigDecimal(50), "ZAR"), AmountCurrency(BigDecimal(10), "ZAR"))
    println(changeOne)
}

data class AmountCurrency(val amount: BigDecimal, val currency: String = "ZAR")

fun calculateParkingChange(paymentAmount: AmountCurrency, chargeAmount: AmountCurrency): List<AmountCurrency> {
    val denominations = listOf<BigDecimal>(
        BigDecimal(0.01).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(0.20).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(0.50).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(2.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(5.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(10.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(50.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP),
        BigDecimal(200.00).setScale(2, RoundingMode.HALF_UP),
    )
    val sortedDenomination = denominations.sortedDescending()
    val disbursementDenominations = mutableListOf<BigDecimal>()

    val totalChangeAmount = paymentAmount.amount - chargeAmount.amount

    var changeHolder: BigDecimal = totalChangeAmount

    while (changeHolder.setScale(2) != BigDecimal.ZERO.setScale(2)) {
        denominationLoop@ for (denomination in sortedDenomination) {
            if (changeHolder >= denomination) {
                disbursementDenominations.add(denomination)
                changeHolder -= denomination
                break@denominationLoop
            }
        }
    }
    if (disbursementDenominations.isEmpty()) {
        disbursementDenominations.add(BigDecimal.ZERO.setScale(2))
    }
    return disbursementDenominations.map { AmountCurrency(it, "ZAR") }
}
