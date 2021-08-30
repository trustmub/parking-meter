import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class MainKtTest {

    @Test
    fun `calculateParkingChange should return change with zero if the note entered is the same as the charge`() {
        val submittedAmount = AmountCurrency(BigDecimal(10), "ZAR")
        val charge = AmountCurrency(BigDecimal(10), "ZAR")
        val changeList: List<AmountCurrency> = calculateParkingChange(submittedAmount, charge)

        val changeTotal = changeList.sumOf { it.amount }

        assertEquals(1, changeList.size)
        assertEquals(BigDecimal.ZERO.setScale(2), changeTotal)
        assertEquals(BigDecimal(0.00).setScale(2), changeList.first().amount)
    }

    @Test
    fun `calculateParkingChange should return denomination for 50 note for a charge if 10`() {
        val submittedAmount = AmountCurrency(BigDecimal(50), "ZAR")
        val charge = AmountCurrency(BigDecimal(10), "ZAR")
        val changeList: List<AmountCurrency> = calculateParkingChange(submittedAmount, charge)

        val changeTotal = changeList.sumOf { it.amount }

        assertEquals(2, changeList.size)
        assertEquals(BigDecimal(40).setScale(2), changeTotal)
        assertEquals(BigDecimal(20.00).setScale(2), changeList.first().amount)
        assertEquals(BigDecimal(20.00).setScale(2), changeList.last().amount)
    }

    @Test
    fun `calculateParkingChange should return denomination for 200 note for a charge of 20`() {
        val submittedAmount = AmountCurrency(BigDecimal(200), "ZAR")
        val charge = AmountCurrency(BigDecimal(20), "ZAR")
        val changeList: List<AmountCurrency> = calculateParkingChange(submittedAmount, charge)

        val changeTotal = changeList.sumOf { it.amount }

        assertEquals(4, changeList.size)
        assertEquals(BigDecimal(180).setScale(2), changeTotal)
        assertEquals(BigDecimal(100.00).setScale(2), changeList.first().amount)
        assertEquals(BigDecimal(10.00).setScale(2), changeList.last().amount)
    }

    @Test
    fun `calculateParkingChange should return denomination for 100 note for a charge of 12,50`() {
        val submittedAmount = AmountCurrency(BigDecimal(100), "ZAR")
        val charge = AmountCurrency(BigDecimal(12.50), "ZAR")
        val changeList: List<AmountCurrency> = calculateParkingChange(submittedAmount, charge)

        val changeTotal = changeList.sumOf { it.amount }

        assertEquals(6, changeList.size)
        assertEquals(BigDecimal(87.50).setScale(2), changeTotal)
        assertEquals(BigDecimal(50).setScale(2), changeList.first().amount)
        assertEquals(BigDecimal(0.50).setScale(2), changeList.last().amount)
    }
}
