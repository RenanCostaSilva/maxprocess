package br.com.renancsdev.mascara.mask

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


abstract class Mask {

    companion object {

        val FORMATO_CPF                         = "###.###.###-##"
        val FORMATO_FONE_CELULAR_DDD            = "(##) #####-####"
        val FORMATO_FONE_FIXO_DDD               = "(##) ####-####"
        val FORMATO_DATE                        = "##/##/####"
        val FORMATO_DATE_HOUR                   = "##/##/#### ##:##:##"

        fun unmask(s: String): String {
            return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "")
                .replace("[:]".toRegex(), "").replace("[)]".toRegex(), "")
        }

        fun mask(ediTxt: EditText, mask: String): TextWatcher? {
            return object : TextWatcher {
                var isUpdating = false
                var old = ""
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str: String = unmask(s.toString())
                    var mascara = ""
                    if (isUpdating) {
                        old = str
                        isUpdating = false
                        return
                    }
                    var i = 0
                    for (m in mask.toCharArray()) {
                        if (m != '#' && str.length > old.length) {
                            mascara += m
                            continue
                        }
                        mascara += try {
                            str[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }
                    isUpdating = true
                    ediTxt.setText(mascara)
                    ediTxt.setSelection(mascara.length)
                }
            }
        }
    }

}