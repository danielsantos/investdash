package com.teoali.investdash

import org.jsoup.Jsoup
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(model: Model): String {
        val dollar = getDollar()
        model.addAttribute("result", dollar)
        //val bitcoin = getBitcoin()
        return "index"
    }

    fun getDollar(): String {
        val url = "https://wise.com/br/currency-converter/dolar-hoje"
        val document = Jsoup.connect(url).get()
        val dollarValueElement = document.select("span.text-success").first()

        if (dollarValueElement != null) {
            val dollarValue = dollarValueElement.text()
                .replace(",", ".")
                .toBigDecimal()
                .setScale(2, java.math.RoundingMode.HALF_UP)
                .toDouble()
            return "Cotação do Dólar em Real: R$ $dollarValue"
        }

        return "ERROR"
    }

    fun getBitcoin(): String {
        val url = "https://br.tradingview.com/symbols/BTCUSD/"
        val document = Jsoup.connect(url).get()
        val dollarValueElement = document.select("span.text-success").first()

        if (dollarValueElement != null) {
            val dollarValue = dollarValueElement.text()
                .replace(",", ".")
                .toBigDecimal()
                .setScale(2, java.math.RoundingMode.HALF_UP)
                .toDouble()
            return "Cotação do Dólar em Real: R$ $dollarValue"
        }

        return "ERROR"
    }
}